# Docker descriptor for XSK
# Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors

# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Apache License, v2.0
# which accompanies this distribution, and is available at
# http://www.apache.org/licenses/LICENSE-2.0
  
# SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
# SPDX-License-Identifier: Apache-2.0

ARG XSK_VERSION=latest
FROM dirigiblelabs/xsk:$XSK_VERSION as base

ENV CNB_USER_ID=1001
ENV CNB_GROUP_ID=1001
ENV CNB_STACK_ID="com.sap.kneo.xsk"
LABEL io.buildpacks.stack.id="com.sap.kneo.xsk"


RUN chmod -R 777 /usr/local/tomcat

FROM base as run

RUN chmod -R 777 /usr/local/tomcat

FROM base as build

RUN chmod -R 777 /usr/local/tomcat

