#!/bin/bash

#####################################
#  Copyright: SAP SE, Walldorf 2015 #
#  Author: SAP SE                   #
#####################################

. ./scripts-common/tests/test-utils.sh

TESTEE="./scripts-common/docker/container-run-utils.sh"

## unit tests
function test__generate_sysname_for_instnum_no_params
{
    local TESTED_FUNC="__xsa_generate_sysname_for_instnum"

    xsa__run_test_expect_failure "$TESTEE" "$TESTED_FUNC";
}

function test__generate_sysname_for_instnum_no_prefix_param
{
    local TESTED_FUNC="__xsa_generate_sysname_for_instnum"
    local TESTED_FUNC_PARAM1="01"

    xsa__run_test_expect_failure "$TESTEE" "$TESTED_FUNC" "$TESTED_FUNC_PARAM1";
}

function test__generate_sysname_for_instnum
{
    local TESTED_FUNC="__xsa_generate_sysname_for_instnum"
    local TESTED_FUNC_PARAM1="01"
    local TESTED_FUNC_PARAM2="hx"
    local EXPECTED_RETURN="hx-d01-test"

    xsa__run_test_compare_return_and_expect_success "$TESTEE" "$EXPECTED_RETURN" "$TESTED_FUNC" "$TESTED_FUNC_PARAM1" "$TESTED_FUNC_PARAM2";
}

function main
{
    test__generate_sysname_for_instnum_no_params
    test__generate_sysname_for_instnum_no_prefix_param
    test__generate_sysname_for_instnum
}
main
