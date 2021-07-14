#!/bin/bash

##################################
##                              ##
##  Copyright: SAP SE, 2015     ##
##                              ##
##  Author: SAP SE              ##
##                              ##
##################################

. ./scripts/utils/tests.sh
. ./scripts/utils/logging.sh

function generate_hana_external_ports
{
    local INSTNUM="$1"
    is_empty "$INSTNUM" \
        && __xsa_logging_log_fatal "no instance number provided" \
        || echo "-p 3${1}15:3${1}15 -p 5${1}14:5${1}14 -p 5${1}13:5${1}13"
}

function generate_xsa_external_ports
{
    # 30 -> external controller port
    # 32 -> external uaa port
    # 33 -> external router port
    local INSTNUM="$1"
    is_empty "$INSTNUM" \
        && __xsa_logging_log_fatal "no instance number provided" \
        || echo "-p 3${1}30:3${1}30 -p 3${1}32:3${1}32 -p 3${1}33:3${1}33"
}

function generate_apps_external_ports
{
    local INSTNUM="$1"

    is_empty "$INSTNUM" \
        && __xsa_logging_log_fatal "no instance number provided" \
        ||  echo    "-p 5${INSTNUM}22-5${INSTNUM}99:5${INSTNUM}22-5${INSTNUM}99"
}

# generates the string used as hostname and container name of a container
function __xsa_generate_sysname_for_instnum
{
    local INSTNUM="$1"
    is_empty "$INSTNUM" \
        && __xsa_logging_log_fatal "no instance number provided"

    local NAME_PREFIX="$2"
    is_empty "$NAME_PREFIX" \
        && __xsa_logging_log_fatal "no hostname/image name prefix provided"

    printf "%s-d%s-test\n" "$NAME_PREFIX" "$INSTNUM"
}

# generates the image name of a commited container
function __xsa_generate_image_name
{
    local INSTNUM="$1"
    is_empty "$INSTNUM" \
        && __xsa_logging_log_fatal "no instance number provided"

    local NAME_PREFIX="$2"
    is_empty "$NAME_PREFIX" \
        && __xsa_logging_log_fatal "no hostname/image name prefix provided"

    local SYSTEM_TYPE="$3"
    is_empty "$SYSTEM_TYPE" \
        && __xsa_logging_log_fatal "no system type provided"

    echo "xsa/${NAME_PREFIX}-d${INSTNUM}-test"
}

# generates the image name with a tag
# TODO use timestamp and codeline here?
function __xsa_generate_image_name_with_tag
{
    echo "$(__xsa_generate_image_name $1 $2 $3):latest"
}

# check if the instnum ($1) is already used in a container name
function __xsa_is_instnum_in_use
{
    return $(docker ps -a | grep -E -q -cim1 "(h-d$1|hx-d$1|hxa-d$1)");
}

# check if a image of a container already exists
function __xsa_image_already_exists
{
    return $(docker images | grep -E -q -cim1 "$1");
}

LOG_VERBOSITY=8

function __xsa_get_container_id_by_instnum_and_prefix
{
    local INSTNUM="$1"
    is_empty "$INSTNUM" \
        && __xsa_logging_log_fatal "no instance number provided"

    local NAME_PREFIX="$2"
    is_empty "$NAME_PREFIX" \
        && __xsa_logging_log_fatal "no hostname/image name prefix provided"

    local SYSNAME=$(__xsa_generate_sysname_for_instnum "$INSTNUM" "$NAME_PREFIX")

    __xsa_logging_log_debug "$LOG_VERBOSITY" "SYSNAME for instnum $INSTNUM is $SYSNAME"

    # TODO use docker filter param or better own defined alias with fixed output
    local CONTAINER_ID=$(docker ps -a | tail -n +2 | grep "$SYSNAME" | awk '{print $1}')

    __xsa_logging_log_debug "$LOG_VERBOSITY" "CONTAINER_ID is $CONTAINER_ID"

    echo "$CONTAINER_ID"
}

function generate_hana_xsa_external_ports
{
    local INSTNUM="$1"
    is_empty "$INSTNUM" \
        && __xsa_logging_log_fatal "no instance number provided" \
        || echo "$(generate_hana_external_ports $1) \
        $(generate_xsa_external_ports $1)"
}

function generate_hana_xsa_apps_external_ports
{
    local INSTNUM="$1"
    is_empty "$INSTNUM" \
        && __xsa_logging_log_fatal "no instance number provided" \
        || echo "$(generate_hana_xsa_external_ports $1) \
        $(generate_apps_external_ports $1)"
}
