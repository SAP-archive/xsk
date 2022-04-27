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

import { XSJSLibExportsGenerator } from '/exports/XSJSLibExportsGenerator.mjs'
const repository = require('platform/v4/repository');

const targetRegistryCollection = repository.getCollection(__context.targetRegistryPath);
const stateTableParams = {
  name: __context.stateTableName,
  schema: "PUBLIC"
}

const generator = new XSJSLibExportsGenerator(stateTableParams);
generator.run(targetRegistryCollection);