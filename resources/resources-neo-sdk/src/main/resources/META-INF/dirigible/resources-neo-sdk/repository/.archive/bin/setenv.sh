
#readProperties reads a portion of a file after a token and concatenates 
#   the read lines, separating them with a specified character
#   -- $1: token after which lines should be read
#   -- $2: token after which lines should not be read
#   -- $3: variable to pass return value 
#   -- $4: character to use as delimiter when concatenating lines

readProperties() {
	local accum=
	local current=
	local read=false
	while read line;
	do 
		addToResult=true
		if [ "$line" = "$2" ]
		then
			read="false";
		fi
		if [ "$read" = "true" ]
		then
			if test -z "$line"
			then
			echo "skip" >> /dev/null
			else
				current="$(echo "$line" | sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//')"
				if case "$line" in *CATALINA_HOME*) true;; *) false;; esac;
				then
					temp="$(echo "$CATALINA_HOME" | sed -e 's/[\/&]/\\&/g')"
					current="$(echo "$current" | sed -e "s/CATALINA_HOME/$temp/")"
				fi
				
				if case "$line" in *SAPJVM_HOME*) true;; *) false;; esac;
				then
					temp="$(echo "$JAVA_HOME" | sed -e 's/[\/&]/\\&/g')"
					temp="$(echo "$current" | sed -e "s/SAPJVM_HOME/$temp/")"
					if [ ! -e "$temp" ]
					then
						temp="$(echo "$JAVA_HOME/.." | sed -e 's/[\/&]/\\&/g')"
						temp="$(echo "$current" | sed -e "s/SAPJVM_HOME/$temp/")"
						if [ ! -e "$temp" ]
						then addToResult=false
						fi
					fi
				    current=$temp
				fi
				
				if [ "$5" = "stop" ] ; then
					if echo "$current" | grep jmxremote > /dev/null 2>&1 ; then
						addToResult=false
					fi
				fi
				
				if $addToResult
				then 
					if [ "$1" = "#options" ]
					then 
						accum="$accum\"$current\"$4"
					else
						accum="$accum$current$4"
					fi
				fi
			fi
		fi
		if [ "$line" = "$1" ]
		then
			read="true";
		fi	
	done < "$CATALINA_HOME"/props.ini;
	accum=${accum%?}
	eval "$3='$accum'"
	return 0
}

readProperties "#classpath" "#options" CLASSPATH ":" "$1"

readProperties "#options" "#classpath" RUNTIME_OPTS " " "$1"

if [ ! -e "$CATALINA_HOME"/log ] ; then
  mkdir "$CATALINA_HOME"/log
fi

if [ -z "$LOGGING_MANAGER" ]; then
  LOGGING_MANAGER="-Djava.util.logging.manager=java.util.logging.LogManager"
fi

CATALINA_OUT="$CATALINA_HOME"/log/catalina.out

JAVA_OPTS="$RUNTIME_OPTS $JAVA_OPTS"

