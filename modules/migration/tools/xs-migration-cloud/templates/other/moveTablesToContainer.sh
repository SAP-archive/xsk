#!/bin/bash


for i in "$@"
do
case $i in
        -a=* | --appname=*)
        APP_NAME="${i#*=}"
        shift
        ;;
        -s=* | --service-instance=*)
        SERVICE_INSTANCE="${i#*=}"
        shift
        ;;
esac
done




#check if one of the mandatory parameters is set
if [[ ! $APP_NAME && ! $SERVICE_INSTANCE ]]
then
        echo "FAILURE: Either parameters appname or service-instance needs to be set."
        echo "Usage:  ./moveTablesToContainer [Option]"
        echo " Options:"
        echo "    --appname, -a             The name of the db-application"
        echo "    --service-instance, -s    The name of the service instance"
        exit
fi


if [ $APP_NAME ]
then
    xs env $APP_NAME --export-json env_json;
    MODE='app'
fi      

if [ $SERVICE_INSTANCE ]
then
    xs service-key $SERVICE_INSTANCE SharedDevKey > env_json;
    MODE='service'
fi


DIRNAME="$( cd "$(dirname "${BASH_SOURCE[0]}")" && pwd )"
JAVA_EXE="$DIRNAME/sapjvm_8_jre/bin/java"

path_to_executable=$(which node 2>/dev/null)
if [ -x "$path_to_executable" ] ; then
    FOUND_NODE="$path_to_executable"
elif [[ -n "$NODE_HOME" && -x $NODE_HOME/bin/node ]] ; then
    FOUND_NODE=$NODE_HOME/bin/node
fi

found_node_version=$($FOUND_NODE --version 2>/dev/null | cut -d 'v' -f 2 | cut -d '.' -f 1)
if [[ -z "$found_node_version" || $found_node_version -lt $NODE_MIN_VERSION ]] ; then
    echo initializing...
    TMPDIR=$(mktemp -d)
    trap "rm -rf $TMPDIR" EXIT
    $JAVA_EXE -cp $DIRNAME/init Unzip $DIRNAME/init/java.dat $TMPDIR
    NODE_EXE="$TMPDIR/$(ls -1 $TMPDIR)/bin/node"
else
    NODE_EXE=$FOUND_NODE
fi


container_name=$("$NODE_EXE" "$DIRNAME/parse-json.js" "schema" "$MODE");
container_owner=$("$NODE_EXE" "$DIRNAME/parse-json.js" "user" "$MODE");


cat << EOF > move_tables_to_container.hdbprocedure
PROCEDURE "SYSTEM"."public::move_tables_to_container" ( ) 
        LANGUAGE SQLSCRIPT
        SQL SECURITY DEFINER 
        
         AS
BEGIN
/***************************** 
        Write your procedure logic 
 *****************************/
