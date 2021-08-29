@echo OFF

call :ReadProperties "#classpath" "#options" CLASSPATH ";"
call :ReadProperties "#options" "#classpath" RUNTIME_OPTS " "

if not "%LOGGING_MANAGER%" == "" goto noJuliManager
set LOGGING_MANAGER=-Djava.util.logging.manager=java.util.logging.LogManager
:noJuliManager

set CLASSPATH=%CLASSPATH:"=%

set JAVA_OPTS=%RUNTIME_OPTS% %JAVA_OPTS%

goto end

::--------------------------------------------------------
::-- Function section starts below here
::--------------------------------------------------------

::   ReadProperties reads a portion of a file after a token and concatenates 
::   the read lines, separating them with a specified character
::   -- %~1: token after which lines should be read
::   -- %~2: token after which lines should not be read
::   -- %~3: variable to pass return value 
::   -- %~4: character to use as delimiter when concatenating lines
:ReadProperties  
	set ACCUM=
	set CURRENT=
	set read=false
	setlocal enabledelayedexpansion
	FOR /F "tokens=* delims=." %%1 in ('type "%CATALINA_HOME%"\props.ini') do (
		if "%%1" equ "%~2" (
			set read=false
		)
		set addToResult=true
		if "!read!" equ "true" (
			if "%%1" equ " " (
				rem skip
			) else (
				set CURRENT=%%1
				call set CURRENT=!CURRENT: =!
				rem Replace entries with the actual CATALINA_HOME
				echo "%%1" | FINDSTR /I "CATALINA_HOME" > nul
				if NOT errorlevel 1 (
					call set CURRENT=!CURRENT:CATALINA_HOME=%CATALINA_HOME%!
				)
				
				rem  Replace entries with actual SAPJVM_HOME and add to classpath only if such files exist. 
				rem  Beside the JDK home, it's possible that the JAVA_HOME points to the JRE home - in this case we provide a fallback check. 
				rem  If such file does not exist (e.g. on Oracle JVM) it is not added to the classpath.
				echo "%%1" | FINDSTR /I "SAPJVM_HOME" > nul
				if NOT errorlevel 1 (
				        call set CURRENT="!CURRENT!"
					call set CURRENTTEMP=!CURRENT:SAPJVM_HOME="%JAVA_HOME%"!
					if NOT exist "!CURRENTTEMP!" (
						call set CURRENTTEMP=!CURRENT:SAPJVM_HOME="%JAVA_HOME%"/..!
						if NOT exist "!CURRENTTEMP!" (
							set addToResult=false
						)
					)
					call set CURRENT=!CURRENTTEMP!
				)

				if "!addToResult!" equ "true" (
					if "%~1" equ "#options" (
						set "ACCUM=!ACCUM!"!CURRENT!"%~4"
					) else (
						set "ACCUM=!ACCUM!!CURRENT!%~4"
					)
				)
			)
		)
	
	    if "%%1" equ "%~1" (
			set read=true
		)
	
	)
	call set ACCUM=%ACCUM:~0,-1%
	( endlocal
	  set "%~3=%ACCUM%" )
	
goto:eof

:end