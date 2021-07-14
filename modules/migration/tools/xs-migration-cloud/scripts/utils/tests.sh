#!/bin/bash

#####################################
#  Copyright: SAP SE, Walldorf 2015 #
#  Author: SAP SE                   #
#####################################

# [[ $_ != $0 ]] && echo "Sourcing $BASH_SOURCE" || echo "Script is meant to be sourced, executing it directly will do nothing!"

function is_empty() {
    local var=$1

    [[ -z $var ]]
}

function is_not_empty() {
    local var=$1

    [[ -n $var ]]
}

function is_file() {
    local file=$1

    [[ -f $file ]]
}

function is_dir() {
    local dir=$1

    [[ -d $dir ]]
}

function is_not_ok() {
    local ret=$1

    [[  $ret -ne 0 ]]
}

function is_empty_array
{
    [[ $1 -eq 0 ]]
}
