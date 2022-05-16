/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
const j = require("jasmine/jasmine");
//const console_reporter = require("jasmine/reporters/console_reporter");

const jasmine = j.core(j);
const env = jasmine.getEnv();
//env.addReporter(console_reporter.jasmine_console_reporter);

const $$j = j.interface(jasmine, env);

const api = require("/xsk/api");

console.log("!!!! VM: 1");

$$j.describe("A suite is just a function", function() {

    console.log("!!!! VM: 2");

    $$j.it("and has a positive case", function() {
        console.log("!!!! VM: 3");
        let actual;
        try {
          console.log("!!!! API add: " + api.add);
          actual = api.add(1,2);
        } catch (e) {
            console.log("!!!! VM: e1: " + e.message);
        }

        console.log("!!!! VM: actual: " + actual);

        try {
          $$j.expect(actual).toBe(3);
        } catch(e) {
          console.log("!!!! VM: e: " + e.message);
        }

        console.log("!!!! VM: 4");
    });

    $$j.it("and has a asd case", function() {
            console.log("!!!! VM: 5");
            $$j.expect(api.add(1,2)).toBe(3222);
            console.log("!!!! VM: 6");
        });

});

env.execute();
//require("jasmine/test-runner").run(env);