#!/bin/bash

#####################################
#  Copyright: SAP SE, Walldorf 2015 #
#  Author: SAP SE                   #
#####################################

su - "$(IFS='-'; tokens=( $(hostname) ); echo ${tokens[1]})adm" <<EOF
    ./HDB start &
    echo "starting bash"
EOF

printf "HANA system should be started.\n"
/bin/bash
