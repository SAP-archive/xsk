/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.parser.hdbsequence.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class XSKHDBSEQUENCEModel {

    private String schema;
    private Integer increment_by;
    private Integer start_with;
    private Integer maxvalue;
    private Boolean nomaxvalue;
    private  Integer minvalue;
    private Boolean nominvalue;
    private Boolean cycles;
    private String reset_by;

    @SerializedName(value = "public")
    private Boolean publicc;

    private String depends_on_table;
    private String depends_on_view;
    private List<String> depends_on;

    public XSKHDBSEQUENCEModel() {
    }

    public XSKHDBSEQUENCEModel(String schema, Integer increment_by, Integer start_with, Integer maxvalue,
                               Boolean nomaxvalue, Integer minvalue, Boolean nominvalue, Boolean cycles, String reset_by,
                               Boolean publicc, String depends_on_table, String depends_on_view, List<String> depends_on) {
        this.schema = schema;
        this.increment_by = increment_by;
        this.start_with = start_with;
        this.maxvalue = maxvalue;
        this.nomaxvalue = nomaxvalue;
        this.minvalue = minvalue;
        this.nominvalue = nominvalue;
        this.cycles = cycles;
        this.reset_by = reset_by;
        this.publicc = publicc;
        this.depends_on_table = depends_on_table;
        this.depends_on_view = depends_on_view;
        this.depends_on = depends_on;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public Integer getIncrement_by() {
        return increment_by;
    }

    public void setIncrement_by(Integer increment_by) {
        this.increment_by = increment_by;
    }

    public Integer getStart_with() {
        return start_with;
    }

    public void setStart_with(Integer start_with) {
        this.start_with = start_with;
    }

    public Integer getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(Integer maxvalue) {
        this.maxvalue = maxvalue;
    }

    public Boolean getNomaxvalue() {
        return nomaxvalue;
    }

    public void setNomaxvalue(Boolean nomaxvalue) {
        this.nomaxvalue = nomaxvalue;
    }

    public Integer getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(Integer minvalue) {
        this.minvalue = minvalue;
    }

    public Boolean getNominvalue() {
        return nominvalue;
    }

    public void setNominvalue(Boolean nominvalue) {
        this.nominvalue = nominvalue;
    }

    public Boolean getCycles() {
        return cycles;
    }

    public void setCycles(Boolean cycles) {
        this.cycles = cycles;
    }

    public String getReset_by() {
        return reset_by;
    }

    public void setReset_by(String reset_by) {
        this.reset_by = reset_by;
    }

    public Boolean getPublicc() {
        return publicc;
    }

    public void setPublicc(Boolean publicc) {
        this.publicc = publicc;
    }

    public String getDepends_on_table() {
        return depends_on_table;
    }

    public void setDepends_on_table(String depends_on_table) {
        this.depends_on_table = depends_on_table;
    }

    public String getDepends_on_view() {
        return depends_on_view;
    }

    public void setDepends_on_view(String depends_on_view) {
        this.depends_on_view = depends_on_view;
    }

    public List<String> getDepends_on() {
        return depends_on;
    }

    public void setDepends_on(List<String> depends_on) {
        this.depends_on = depends_on;
    }
}
