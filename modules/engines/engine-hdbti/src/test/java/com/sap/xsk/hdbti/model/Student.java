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
package com.sap.xsk.hdbti.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "STUDENTS")
public class Student {
    @Id
    @Column(name = "ID", columnDefinition = "BIGINT", nullable = false)
    private Long id;
    @Column(name = "FIRST_NAME", columnDefinition = "VARCHAR", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", columnDefinition = "VARCHAR", nullable = false)
    private String lastName;
    @Column(name = "AGE", columnDefinition = "BIGINT", nullable = false)
    private Long age;
    @Column(name = "SIGNED", columnDefinition = "TIMESTAMP", nullable = false)
    private Timestamp signed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Timestamp getSigned() {
        return signed;
    }

    public void setSigned(Timestamp signed) {
        this.signed = signed;
    }
}
