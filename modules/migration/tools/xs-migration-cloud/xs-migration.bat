@echo off
set NODE_MIN_VERSION=6

setlocal ENABLEEXTENSIONS
set TMPDIR=%tmp%
set EXENAME=%~f0
for %%F in ("%EXENAME%") do set DIRNAME=%%~dpF
set JAVAEXE="%DIRNAME%\sapjvm_8_jre\bin\java"

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
%NODE_EXE% --max_old_space_size=4096 "%DIRNAME%\xs-migration.js" %*
set RETURN_CODE=%ERRORLEVEL%
if exist %unique% (rd /s /q %unique%)
exit /B %RETURN_CODE%

endlocal
