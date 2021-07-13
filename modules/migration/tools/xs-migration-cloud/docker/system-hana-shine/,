#!/bin/bash

#####################################
#  Copyright: SAP SE, Walldorf 2015 #
#  Author: SAP SE                   #
#####################################

# [[ $_ != $0 ]] && echo "Sourcing ./scripts-common/docker/container-run-utils.sh" || echo "Script is meant to be sourced, executing it directly will do nothing!"

. ./scripts/utils/hana/hana-run-utils.sh
. ./scripts/utils/logging.sh

# restarts a container executing restart script
function __xsa_create_and_start_container 
{ 
    local INSTNUM="$1"
    is_empty "$INSTNUM" && __xsa_logging_log_fatal "no instance number provided"

    local SYSTEM_TYPE_PREFIX="$2"   
    is_empty "$SYSTEM_TYPE_PREFIX" &&__xsa_logging_log_fatal "no system type prefix provided"

    local SYSTEM_TYPE="$3"
    is_empty "$SYSTEM_TYPE" && __xsa_logging_log_fatal "no system type provided"

    local PORTS="$4"           
    is_empty "$PORTS" && __xsa_logging_log_fatal "no ports to exposed provided"

    printf "\n\nCONTAINER START:\n\n"

    CONTAINER_HOSTNAME="$(__xsa_generate_sysname_for_instnum $INSTNUM $SYSTEM_TYPE_PREFIX)"
    printf "\tContainer hostname: %s\n" $CONTAINER_HOSTNAME

    CONTAINER_NAME="${CONTAINER_HOSTNAME}"
    printf "\tContainer name: %s\n" $CONTAINER_NAME

    IMAGE_NAME=$(__xsa_generate_image_name_with_tag $INSTNUM $SYSTEM_TYPE_PREFIX $SYSTEM_TYPE)
    printf "\tContainer base image: %s\n\n" $IMAGE_NAME

    __xsa_logging_log_debug 8 "docker run -i --entrypoint=/root/restart.sh ${PORTS} --hostname=${CONTAINER_HOSTNAME} --name=${CONTAINER_NAME} ${IMAGE_NAME}"

    # the docker run is used like a docker create (which does not exist explicitely) here
    docker run -i --entrypoint=/root/restart.sh ${PORTS} --hostname=${CONTAINER_HOSTNAME} --name=${CONTAINER_NAME} ${IMAGE_NAME} # &> /dev/null
    local CONTAINER_ID=$(__xsa_get_container_id_by_instnum_and_prefix "$INSTNUM" "$SYSTEM_TYPE_PREFIX")

    __xsa_logging_log_debug 8 "container id of container to be started: ${CONTAINER_ID}"

    docker start $CONTAINER_ID &> /dev/null
    printf "\n\tStarted container %s\n\n" $CONTAINER_ID
}

function __xsa_restart_hana_container 
{
    is_empty "$INSTNUM" \
        && __xsa_logging_log_fatal "no instance number provided"

    PORTS="$(generate_hana_external_ports $INSTNUM)"

    __xsa_create_and_start_container "$INSTNUM" "h" "system-hana" "$PORTS"
}

function main
{
    __xsa_restart_hana_container
}

main
