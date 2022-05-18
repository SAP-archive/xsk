#!/bin/bash

while read STATUS ADDR1 ADDR2
do
    echo "$ADDR1===$ADDR2===$STATUS"
done  < <(git diff --name-status $1/ $2/)
