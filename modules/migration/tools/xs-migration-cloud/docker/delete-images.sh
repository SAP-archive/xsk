#!/bin/bash

#####################################
#  Copyright: SAP SE, Walldorf 2015 #
#  Author: SAP SE                   #
#####################################

docker rmi xsa/system-xsc-migration || exit -1
docker rmi xsa/system-node-0127 || exit -1
docker rmi xsa/system-node-n || exit -1
docker rmi xsa/system-node-base || exit -1
docker rmi xsa/system-hana-shine || exit -1
docker rmi xsa/system-hana-install || exit -1
docker rmi xsa/system-hana-shine || exit -1
