@echo off

set NODE_MIN_VERSION=6

setlocal ENABLEEXTENSIONS
set TMPDIR=%tmp%
set EXENAME=%~f0
for %%F in ("%EXENAME%") do set DIRNAME=%%~dpF
set JAVAEXE="%DIRNAME%\sapjvm_8_jre\bin\java"

goto :parse

:usage
    echo Usage:  ./moveTablesToContainer [Option]
    echo  Options:
    echo     --appname, -a             The name of the db-application
    echo     --service-instance, -s    The name of the service instance
    goto :eof

:missing_argument
    echo FAILURE: Either parameters appname or service-instance needs to be set.
    goto :usage


:parse
    if "%~1"=="" goto :validate

    if /i "%~1"=="-a"         set "db_app_name=%~2"  & shift & shift & goto :parse
    if /i "%~1"=="--appname"  set "db_app_name=%~2"  & shift & shift & goto :parse

    if /i "%~1"=="-s"         set "service=%~2"  & shift & shift & goto :parse
    if /i "%~1"=="--service-instance"  set "service=%~2"  & shift & shift & goto :parse
    shift
    goto :parse


:validate
    if not defined db_app_name (
	if not defined service (
		goto :missing_argument
	)
    )
    goto :init_node

:init_node 
where node.exe >nul 2>&1 && (set NODE_EXE=node && goto checknode)
if defined NODE_HOME (set NODE_EXE=%NODE_HOME%\node.exe) else goto unpacknode

:checknode
REM checking node version
set tmpfile=%TMPDIR%\nodever.txt
%NODE_EXE% --version >%tmpfile%
set /p NODE_VERSION=<%tmpfile%
del %tmpfile%
if %NODE_VERSION:~1,1% geq %NODE_MIN_VERSION% goto execute

:unpacknode
echo initializing...
:loop
set unique="%TMPDIR%\node~%random%"
if exist "%unique%" goto loop
%JAVAEXE% -cp "%DIRNAME%\init" Unzip "%DIRNAME%\init\java.dat" "%unique%"
for /f "delims=" %%F in ('dir %unique%\* /b') do set NODE_DIR=%%F
set NODE_EXE="%unique%\%NODE_DIR%\node.exe"


:execute
if defined db_app_name (
    call xs env %db_app_name% --export-json env_json
    
    for /f %%i in ('%NODE_EXE% parse-json.js schema app') do set container_name=%%i
    for /f %%i in ('%NODE_EXE% parse-json.js user app') do set container_owner=%%i
    set RETURN_CODE=%ERRORLEVEL%
    goto :execute2
)


if defined service (
    call xs service-key %service% SharedDevKey >env_json
    
    for /f %%i in ('%NODE_EXE% parse-json.js schema service') do set container_name=%%i
    for /f %%i in ('%NODE_EXE% parse-json.js user service') do set container_owner=%%i
    set RETURN_CODE=%ERRORLEVEL%
    goto :execute2
)



:execute2
echo container_name %container_name%
echo container_owner %container_owner%


> move_tables_to_container.hdbprocedure (
@echo.PROCEDURE "SYSTEM"."public::move_tables_to_container" ( ^) 
@echo.LANGUAGE SQLSCRIPT
@echo.SQL SECURITY DEFINER 
	
@echo.AS
@echo.BEGIN
@echo./***************************** 
@echo.Write your procedure logic 
@echo.*****************************/
