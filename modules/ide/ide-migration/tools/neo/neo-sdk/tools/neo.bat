@echo off
rem :-----------------------------------------------------------------------------
rem :
rem : Verify if it's possible to setlocal with enableExtensions and
rem : enableDelayedExpansion
rem :
rem :-----------------------------------------------------------------------------
VERIFY activateExtensions 2>nul
SETLOCAL enableExtensions enableDelayedExpansion
IF ERRORLEVEL 1 echo Cannot enable delayed expansion&& exit /b 1

set BASE_PATH=%~dp0
set LOG_LOCATION=%BASE_PATH%log
set PARAM_OUTPUT=-output
echo %*| FINDSTR /C:%PARAM_OUTPUT% >NUL || (type "%BASE_PATH%lib\logo.txt")

for /f "usebackq delims=" %%L in ("%BASE_PATH%lib\logo.txt") do title %%L

rem :-----------------------------------------------------------------------------
rem :
rem : check if java is installed and if version is compatible with the platform
rem : If java_home is defined it will try to use the java located there instead of
rem : the default java installed on the system.
rem :
rem :-----------------------------------------------------------------------------

if defined JAVA_HOME if exist "%JAVA_HOME%\bin\java.exe" PATH "%JAVA_HOME%\bin";%PATH%
java.exe -version >nul 2>&1 || ( echo Java cannot be found on the system! & endlocal & exit /b 2 )
for /f  tokens^=2^,3^,4^ delims^=_.^"  %%J in ('java.exe -version 2^>^&1') do set "jver=%%J%%K%%L" && goto :break_loop
:break_loop
IF %jver%0 LSS 1600 ( echo Current Java version is older than 1.6.0 & endlocal & exit /b 2 )

rem :-----------------------------------------------------------------------------
rem :
rem : Initialization of the java arguments.
rem :
rem :-----------------------------------------------------------------------------

set ARGS=-Dneo.base.location="%BASE_PATH%." -Dneo.base.command=%0 -Dneo.command.name=%1

rem : HTTP
if defined HTTP_PROXY_HOST for /f "delims=" %%A in ("!HTTP_PROXY_HOST!") do set "ARGS=!ARGS! -Dhttp.proxyHost="%%~A""
if defined HTTP_PROXY_PORT (
  echo !HTTP_PROXY_PORT!| FINDSTR /R /X /C:"[0-9][0-9]*" >NUL || ( echo HTTP_PROXY_PORT has non-numeric value and will be ignored & GOTO :SKIP_HTTP_PORT )
  set "ARGS=!ARGS! -Dhttp.proxyPort="!HTTP_PROXY_PORT!""
)
:SKIP_HTTP_PORT
if defined HTTP_PROXY_USER for /f "delims=" %%A in ("!HTTP_PROXY_USER!") do set "ARGS=!ARGS! -Dhttp.proxyUser="%%~A""
if defined HTTP_PROXY_USER if defined HTTP_PROXY_PASSWORD set "ARGS=!ARGS! -Dhttp.proxyPassword="!HTTP_PROXY_PASSWORD!""
if defined HTTP_NON_PROXY_HOSTS for /f "delims=" %%A in ("!HTTP_NON_PROXY_HOSTS!") do set "ARGS=!ARGS! -Dhttp.nonProxyHosts="%%~A""

rem : HTTPS
if defined HTTPS_PROXY_HOST for /f "delims=" %%A in ("!HTTPS_PROXY_HOST!") do set "ARGS=!ARGS! -Dhttps.proxyHost="%%~A""
if defined HTTPS_PROXY_PORT (
  echo !HTTPS_PROXY_PORT!| FINDSTR /R /X /C:"[0-9][0-9]*" >NUL || ( echo HTTPS_PROXY_PORT has non-numeric value and will be ignored & GOTO :SKIP_HTTPS_PORT )
  set "ARGS=!ARGS! -Dhttps.proxyPort="!HTTPS_PROXY_PORT!""
)
:SKIP_HTTPS_PORT
if defined HTTPS_PROXY_USER for /f "delims=" %%A in ("!HTTPS_PROXY_USER!") do set "ARGS=!ARGS! -Dhttps.proxyUser="%%~A""
if defined HTTPS_PROXY_USER if defined HTTPS_PROXY_PASSWORD set "ARGS=!ARGS! -Dhttps.proxyPassword="!HTTPS_PROXY_PASSWORD!""

rem : Process name
if defined neo_process_name for /f "delims=" %%A in ("!neo_process_name!") do set "ARGS=!ARGS! -Dneo.process.name="%%~A""

rem : Logging location
if defined neo_logging_location for /f "delims=" %%A in ("!neo_logging_location!") do set "LOG_LOCATION=%%~A"
set "ARGS=!ARGS! -Dneo.logging.location="%LOG_LOCATION%""

rem :-----------------------------------------------------------------------------
rem :
rem : Calling the console client
rem :
rem :-----------------------------------------------------------------------------

for %%G in ("%BASE_PATH%lib\cmd\com.sap.jpaas.infrastructure.console.classloader*.jar") do set "CP=%%~fG;"

set MEMORY_ARGS=-Xms16m -Xmx128m
set COMMAND=java.exe !ARGS! !MEMORY_ARGS! -cp "%CP%" -Djava.system.class.loader=com.sap.jpaas.infrastructure.console.CmdJarsClassLoader com.sap.jpaas.infrastructure.console.ConsoleClient
setlocal DisableDelayedExpansion
rem echo Launching: %COMMAND% %*
cmd /q /s /c %COMMAND% %*
endlocal

if %ERRORLEVEL% neq 0 (
  exit /b %ERRORLEVEL%
)