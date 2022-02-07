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
class ObjectTypeFilter {
    static filterObjects(includedTypes, excludedTypes, objectList) {
        let filteredObjects = objectList;

        if (includedTypes.length > 0) {
            filteredObjects = objectList.filter((object) => {
                return includedTypes.includes(object.suffix);
            });
        }

        if (excludedTypes.length > 0) {
            filteredObjects = filteredObjects.filter((object) => {
                return !excludedTypes.includes(object.suffix);
            });
        }

        return filteredObjects;
    }

    static typeIsIncluded(includedTypes, excludedTypes, type) {
        if (excludedTypes.includes(type)) {
            return false;
        }

        if (includedTypes.length > 0 && !includedTypes.includes(type)) {
            return false;
        }

        return true;
    }
}

module.exports = ObjectTypeFilter;
