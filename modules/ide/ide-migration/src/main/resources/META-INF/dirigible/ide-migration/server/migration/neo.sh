#!/bin/bash

EXECUTION_TYPE=open
JAVA_HOME=$JAVA8_HOME 
PATH=$JAVA8_HOME/bin:$PATH

while getopts ":a:h:u:p:i:t:e" option; do
  case "${option}" in
    a) ACCOUNT=${OPTARG};;
    h) HOST=${OPTARG};;
    u) USER=${OPTARG};;
    p) PASSWD=${OPTARG};;
    i) SCHEMA=${OPTARG};;
    e) EXECUTION_TYPE=${OPTARG};;
    t) TUNNEL_SESSION_ID=${OPTARG};;
    *) echo "${OPTARG}: Unrecognized option" >&2
      exit 2;;
  esac
done

if [[ $EXECUTION_TYPE = "open" ]]; then

  # echo "password=$PASSWD" > $TEMP_PROPERTIES_FILE_NAME

  tunnel_response=$(bash $NEO_CLIENT_PATH open-db-tunnel -h "$HOST" -a "$ACCOUNT" -u "$USER" --password "$PASSWD" -i "$SCHEMA" "$TEMP_PROPERTIES_FILE_NAME" --background --output json)

  #rm $TEMP_PROPERTIES_FILE_NAME

  tunnel_response_error_message=$(echo "$tunnel_response" | grep -o '"errorMsg":*"[^"]*"' | grep -o '"[^"]*"$')

  if [ -z "$tunnel_response_error_message" ]
  then
    tunnel_response_regex='.*"host":"([^"]*)".*"port":([^,]*),.*"jdbcUrl":"([^"]*)".*"sessionId":"([^"]*)".*'

    [[ $tunnel_response =~ $tunnel_response_regex ]]

    dbHost=${BASH_REMATCH[1]}
    dbPort=${BASH_REMATCH[2]}
    dbJdbcUrl=${BASH_REMATCH[3]}
    tunnelSessionId=${BASH_REMATCH[4]}

    printf '{"host":"%s", "port":"%s", "url":"%s", "tunnelId":"%s"}\n' "$dbHost" "$dbPort" "$dbJdbcUrl" "$tunnelSessionId"
  else
    printf '{"errorMsg": %s}' "$tunnel_response_error_message"
    exit 0
  fi
else
  tunnel_response=$(bash $NEO_CLIENT_PATH close-db-tunnel --session-id "$TUNNEL_SESSION_ID" --output json)

  echo tunnel_response
fi

exit 0
