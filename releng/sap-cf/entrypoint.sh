#!/bin/bash

set -e

chown -R dirigible:dirigible $CATALINA_HOME

exec su dirigible -c "$@" 
