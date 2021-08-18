#!/bin/sh

#
# identify cygwin
#
cygwin=false
case "$(uname)" in
  CYGWIN*) cygwin=true ;;
esac
export cygwin

#
# resolve script path
#
# Use base *nix tools since readlink seems to be a problem on Mac:
# 1) comes with XCode
# 2) does not support most linux arguments
#
script="$0"
basePath=$(dirname "${script}")
while test -L "${script}"
do
  script=$(ls -l "${script}" | sed 's/^.*-> //')
  if echo "${script}" | grep -q -v '^/'
  then
    script="${basePath}/${script}"
  fi
  basePath=$(dirname "${script}")
done

#
# Output tool logo
# Printed out only in case "output" is not set
#

if echo $@ | grep -q "\-output"; then
	:
	# Machine readable output enabled
else
	cat "$basePath/lib/logo.txt"
fi

#
# Detect JAVA_HOME
#
if $cygwin; then
       JAVA_HOME=$(cygpath -up "$JAVA_HOME")
fi
if [ -z "$JAVA_HOME" ]; then                               # if JAVA_HOME is undefined
  if [ -f /usr/share/java-utils/java-functions ]; then
     . /usr/share/java-utils/java-functions; set_jvm      # JPackage standard method to set JAVA_HOME
   elif [ -n "$(type -t setJava)" ]; then
     . setJava ""; fi                                     # old SUSE method to set JAVA_HOME
fi
if [ ! -z "$JAVA_HOME" ]; then
       javaCommand="java"                                         # name of the Java launcher without the path
       javaExe="$JAVA_HOME/bin/$javaCommand"                      # file name of the Java application launcher executable
 elif [ ! -z $(which java) ]; then
       javaExe=$(which java)
fi
if [ -z "$JAVA_HOME" ] | [ -z "$javaExe" ]; then echo Unable to set JAVA_HOME environment variable; exit 1; fi

#
# Build proxy arguments for the VM
#
if [ -n "$http_proxy" ] ; then
    echo $http_proxy | grep -q "http://"
    if [ $? -eq 0 ]; then
        http_proxy_string=$http_proxy
    else
        http_proxy_string="http://$http_proxy"
    fi

    echo $http_proxy | grep -q "@"
    if [ $? -eq 0 ]; then # variable has username and password
        http_proxy_host=$(echo $http_proxy_string | sed 's/http:\/\/.*@\(.*\):.*/\1/')
        http_proxy_port=$(echo $http_proxy_string  | sed 's/http:\/\/.*@.*:\(.*\)/\1/' | cut -d "/" -f1)
        http_proxy_username=$(echo $http_proxy_string  | sed 's/http:\/\/\(.*\)@.*/\1/'|awk -F: '{print $1}')
        http_proxy_password=$(echo $http_proxy_string  | sed 's/http:\/\/\(.*\)@.*/\1/'|awk -F: '{print $2}')
        args="$args -Dhttp.proxyHost=$http_proxy_host -Dhttp.proxyPort=$http_proxy_port -Dhttp.proxyUser=$http_proxy_username -Dhttp.proxyPassword=$http_proxy_password"
    else # no user and password
        http_proxy_host=$(echo $http_proxy_string  | sed 's/http:\/\/\(.*\):.*/\1/')
        http_proxy_port=$(echo $http_proxy_string  | sed 's/http:\/\/.*:\(.*\)/\1/' | cut -d "/" -f1)
        args="$args -Dhttp.proxyHost=$http_proxy_host -Dhttp.proxyPort=$http_proxy_port"
    fi
fi
if [ -n "$https_proxy" ] ; then
    echo $https_proxy | grep -q "https://"
    if [ $? -eq 0 ]; then
        https_proxy_string=$https_proxy
    else
        echo $https_proxy | grep -q "http://"
        if [ $? -ne 0 ]; then
            https_proxy_string="https://$https_proxy"
        else
            https_proxy_string=$https_proxy
        fi
    fi

    echo $https_proxy | grep -q "@"
    if [ $? -eq 0 ]; then # variable has username and password
        echo $https_proxy_string | grep -q "https://"
        if [ $? -eq 0 ]; then
            https_proxy_host=$(echo $https_proxy_string | sed 's/https:\/\/.*@\(.*\):.*/\1/')
            https_proxy_port=$(echo $https_proxy_string | sed 's/https:\/\/.*@.*:\(.*\)/\1/' | cut -d "/" -f1)
            https_proxy_username=$(echo $https_proxy_string | sed 's/https:\/\/\(.*\)@.*/\1/'|awk -F: '{print $1}')
            https_proxy_password=$(echo $https_proxy_string | sed 's/https:\/\/\(.*\)@.*/\1/'|awk -F: '{print $2}')
            args="$args -Dhttps.proxyHost=$https_proxy_host -Dhttps.proxyPort=$https_proxy_port -Dhttps.proxyUser=$https_proxy_username -Dhttps.proxyPassword=$https_proxy_password"
         else
            https_proxy_host=$(echo $https_proxy_string | sed 's/http:\/\/.*@\(.*\):.*/\1/')
            https_proxy_port=$(echo $https_proxy_string | sed 's/http:\/\/.*@.*:\(.*\)/\1/' | cut -d "/" -f1)
            https_proxy_username=$(echo $https_proxy_string | sed 's/http:\/\/\(.*\)@.*/\1/'|awk -F: '{print $1}')
            https_proxy_password=$(echo $https_proxy_string | sed 's/http:\/\/\(.*\)@.*/\1/'|awk -F: '{print $2}')
            args="$args -Dhttps.proxyHost=$https_proxy_host -Dhttps.proxyPort=$https_proxy_port -Dhttps.proxyUser=$https_proxy_username -Dhttps.proxyPassword=$https_proxy_password"
         fi
    else # no user and password
        echo $https_proxy_string | grep -q "https://"
        if [ $? -eq 0 ]; then
            https_proxy_host=$(echo $https_proxy_string | sed 's/https:\/\/\(.*\):.*/\1/')
            https_proxy_port=$(echo $https_proxy_string | sed 's/https:\/\/.*:\(.*\)/\1/' | cut -d "/" -f1)
            args="$args -Dhttps.proxyHost=$https_proxy_host -Dhttps.proxyPort=$https_proxy_port"
        else
            https_proxy_host=$(echo $https_proxy_string | sed 's/http:\/\/\(.*\):.*/\1/')
            https_proxy_port=$(echo $https_proxy_string | sed 's/http:\/\/.*:\(.*\)/\1/' | cut -d "/" -f1)
            args="$args -Dhttps.proxyHost=$https_proxy_host -Dhttps.proxyPort=$https_proxy_port"
        fi
    fi
fi
if [ -n "$no_proxy" ]
then
  # replace <whitespace>;<whitespace> with | 
  no_proxy_hosts=$(echo $no_proxy|sed 's/\(\s*\),\(\s*\)/|/g') 
  args="$args -Dhttp.nonProxyHosts=\"$no_proxy_hosts\""
fi

#
# Add process name
#
if [ -n "$neo_process_name" ]
then
  args="$args -Dneo.process.name=$neo_process_name"
fi

#
# Build paths
#
for i in "$basePath"/lib/cmd/com.sap.jpaas.infrastructure.console.classloader*.jar; do
    toolcp=${i}
done

#
# Add logging location
#

logPath="$basePath"/log
if [ -n "$neo_logging_location" ]
then
  logPath=$neo_logging_location
fi
if $cygwin; then
    toolcp=$(cygpath -wp $toolcp)
    logPath=$(cygpath -w $logPath)
    basePath=$(cygpath -w $basePath)
fi

#
# Execute JAVA
#
#echo Launching: "$javaExe" $args -Dneo.logging.location="$logPath" -Dneo.base.location="$basePath" -cp "$toolcp" -Djava.system.class.loader=com.sap.jpaas.infrastructure.console.CmdJarsClassLoader com.sap.jpaas.infrastructure.console.ConsoleClient "$@"
echo ""
exec "$javaExe" $args -Xms16m -Xmx128m -Dneo.logging.location="$logPath" -Dneo.base.location="$basePath" -Dneo.base.command="$0" -Dneo.command.name="$1" -cp "$toolcp" -Djava.system.class.loader=com.sap.jpaas.infrastructure.console.CmdJarsClassLoader com.sap.jpaas.infrastructure.console.ConsoleClient "$@"

