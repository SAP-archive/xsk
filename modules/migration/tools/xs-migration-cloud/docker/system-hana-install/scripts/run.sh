#!/bin/bash

#####################################
#  Copyright: SAP SE, Walldorf 2015 #
#  Author: SAP SE                   #
#####################################

set -e

. ./scripts/utils/tests.sh
. ./scripts/utils/logging.sh

. ./scripts/utils/hana/hana-run-utils.sh

export HANA_BASEDIR="/sapmnt/production/makeresults/newdb_archive/HANA_WS_COR/released_weekstones"
export IMAGE_NAME="xsa/system-hana:latest"

LOG_VERBOSITY=8

function run_job
{
    is_empty "$INSTNUM" \
        && __xsa_logging_log_fatal "no instnum provided"

    is_empty "$WEEKSTONE_DIR" && WEEKSTONE_DIR="LastWS"
    is_empty "$SYSNAME" && SYSNAME="h-d${INSTNUM}-test"

    local PORTS="$(generate_hana_external_ports ${INSTNUM})"
    local HANA_DIR="${HANA_BASEDIR}/${WEEKSTONE_DIR}"

    __xsa_logging_log_msg "Hana Share Directory: $HANA_DIR"

    printf "\nHana Share Directory: %s\n" $HANA_DIR

	docker run  ${PORTS} \
                --name="${SYSNAME}" \
                --hostname="${SYSNAME}" \
                -t \
                -v ${HANA_DIR}:/hanainst $IMAGE_NAME $INSTNUM;
}

function main
{
    run_job
}

main
