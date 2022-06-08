#!/bin/bash

set -e

chown -R xsk:xsk $CATALINA_HOME

exec gosu xsk $@
