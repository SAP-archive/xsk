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
var repository = require('platform/v4/repository');

var targetRegistryCollection = repository.getCollection(__context.registry_path);
new XSJSLibExportsGenerator(targetRegistryCollection);