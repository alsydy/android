#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS=""

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Check if the application home is set
if [ -z "$APP_HOME" ]; then
  APP_HOME=`dirname "$0"`
  APP_HOME=`cd "$APP_HOME" && pwd`
fi

# Find Java
if [ -z "$JAVA_HOME" ]; then
  JAVA_EXE=java
else
  JAVA_EXE="$JAVA_HOME/bin/java"
fi

# Locate the gradle-wrapper.jar file
WRAPPER_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

if [ ! -r "$WRAPPER_JAR" ]; then
  echo "Wrapper jar not found: $WRAPPER_JAR"
  exit 1
fi

exec "$JAVA_EXE" $DEFAULT_JVM_OPTS -jar "$WRAPPER_JAR" "$@"