#!/bin/bash

#####################################
#  Copyright: SAP SE, Walldorf 2015 #
#  Author: SAP SE                   #
#####################################

# [[ $_ != $0 ]] && echo "Sourcing $BASH_SOURCE" || echo "Script is meant to be sourced, executing it directly will do nothing!"

LOGFILE=/tmp/xsa-migration.log
LOGFILE_DEBUG=/tmp/xsa-q-migration.log

# special log for logging of logging infrastructure
LOGFILE_LOGGING=/tmp/xsa-migration-logging.log

# The higher the value of VERBOSE, the more talkative the log file is.
# Low values of VERBOSE mean a shorter log file;
# High values of VERBOSE mean a longer log file.
VERBOSE=10

function __xsa_logging_print_stack_line()
{
    printf "\tfrom: %s \tfile: %s \tline: %s\n" "$1" "$2" "$3" >> $LOGFILE
}

# prints the stacktrace except this function
function __xsa_logging_print_stack()
{
    linLen=${#BASH_LINENO[$i]}
    funLen=${#FUNCNAME[@]}

    printf "BASH_LINENO length: %d\n" $linLen >> $LOGFILE_LOGGING
    printf "FUNCNAME length: %d\n" $funLen >> $LOGFILE_LOGGING

    ignore_levels="0" # can be used to exempt logging functions from beeing printed in stack
    # use for loop read all nameservers
    for (( i=$ignore_levels; i<${funLen}; i++ ));
    do
        __xsa_logging_print_stack_line ${FUNCNAME[$i]} ${BASH_SOURCE[$i]} ${BASH_LINENO[$(($i-1))]}
    done
}

function __xsa_logging_date()
{
    printf "`date`"
}

function __xsa_logging_log_error()
{
    printf "%s : ERROR:: %s\n" "$(__xsa_logging_date)" "$(echo $@)" >> $LOGFILE
}

function __xsa_logging_log_msg()
{
    printf "%s : MESSAGE:: %s\n" "$(__xsa_logging_date)" "$(echo $@)" >> $LOGFILE
}

function __xsa_logging_log_debug()
{
    local verbosity=$1

    # consume first arg via shift, if it is a number, otherwise set verbosity to 1 implicitely
    [ ! -z "${verbosity##*[!0-9]*}" ]  && shift || verbosity=1;

    if [ "$VERBOSE" -gt "$verbosity" ]; then
        printf "%s :: DEBUG (level %d) :: %s\n" "$(__xsa_logging_date)" ${verbosity} "$(echo $@)" >> $LOGFILE_DEBUG
    fi
}

function __xsa_logging_log_fatal()
{
    local MSG=$(echo $@)
    printf "FATAL ERROR: %s. Check log file %s for details.\n" "$MSG" "$LOGFILE"
    printf "%s: FATAL:: %s\n" "$(__xsa_logging_date)" "$(echo $@)" >> $LOGFILE
    __xsa_logging_print_stack
    exit 1
}
