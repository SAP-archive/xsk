#!/bin/bash

. ./scripts/utils/tests.sh
. ./scripts/utils/logging.sh

echo "Trying to migrate shine"

MIGRATION_GEN_DIR="$HOME/gen/xs-migration-result"

is_dir $MIGRATION_GEN_DIR \
    && __xsa_logging_log_fatal "gen dir already exists, please delete prior to run"

docker run  -ti \
            --rm \
            -v "$MIGRATION_GEN_DIR":/root/xs-migration/result \
            xsa/system-xs-migration MINI_SCENARIO --name Mini --target-dir /root/xs-migration/result/mini
