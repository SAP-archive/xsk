#!/bin/bash

set -e

chown -R dirigible:dirigible $CATALINA_HOME

exec gosu dirigible $@
