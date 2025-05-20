@ECHO OFF

REM ##########################################################################
REM
REM  Gradle start up script for Windows
REM
REM ##########################################################################

SET DEFAULT_JVM_OPTS=

SET APP_BASE_NAME=%~n0
SET APP_HOME=%~dp0

REM Find Java
IF NOT "%JAVA_HOME%"=="" (
  SET JAVA_EXE="%JAVA_HOME%\bin\java.exe"
) ELSE (
  SET JAVA_EXE=java
)

REM Locate the gradle-wrapper.jar file
SET WRAPPER_JAR=%APP_HOME%gradle\wrapper\gradle-wrapper.jar

IF NOT EXIST "%WRAPPER_JAR%" (
  ECHO Wrapper jar not found: %WRAPPER_JAR%
  EXIT /B 1
)

"%JAVA_EXE%" %DEFAULT_JVM_OPTS% -jar "%WRAPPER_JAR%" %*