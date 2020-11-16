/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsjob.ds.model;

import java.util.List;

public class XSKJobArtifact {

    private String description;

    private String action;

    private List<XSKJobSchedule> schedules;

    public XSKJobArtifact() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<XSKJobSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<XSKJobSchedule> schedules) {
        this.schedules = schedules;
    }
}
