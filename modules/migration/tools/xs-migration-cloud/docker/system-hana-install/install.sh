#!/bin/bash

##############################
# Copyright SAP SE, 2015     #
# Author: SAP SE             #
##############################

if [ -z "$1" ]
then
    echo "please provide instance number, e.g. 01"
    exit -1
else
    INSTNUM=$1
	SID="D$1"
fi

/hanainst/lcm/linuxx86_64/SAP_HANA_LCM/hdblcm  -n $INSTNUM -s $SID --batch --configfile=/root/hdblcm-template.cfg
