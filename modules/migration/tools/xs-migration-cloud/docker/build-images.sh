#!/bin/bash

#####################################
#  Copyright: SAP SE, Walldorf 2015 #
#  Author: SAP SE                   #
#####################################

BUILD_CMD="docker build --no-cache -t xsa/"

function run_build()
{
    CMD="$BUILD_CMD$(basename $(pwd)) .";
    echo "Build Command: $CMD" && eval "$CMD"
}

cd system-node-base && run_build && cd ..
cd system-node-n && run_build && cd ..
cd system-node-0127 && run_build && cd ..
cd system-xsc-migration && run_build && cd ..

cd system-hana-base && run_build && cd ..
cd system-hana-install && run_build && cd ..
#cd system-hana-shine && run_build && cd ..
