# Docker descriptor for XSK
# Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors

# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Apache License, v2.0
# which accompanies this distribution, and is available at
# http://www.apache.org/licenses/LICENSE-2.0
  
# SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
# SPDX-License-Identifier: Apache-2.0

FROM dirigiblelabs/xsk-kyma-runtime as base

ENV CNB_USER_ID=1000
ENV CNB_GROUP_ID=1000
ENV CNB_STACK_ID="com.sap.kneo.xsk"
LABEL io.buildpacks.stack.id="com.sap.kneo.xsk"

RUN groupadd xsk --gid ${CNB_GROUP_ID} && \
  useradd --uid ${CNB_USER_ID} --gid ${CNB_GROUP_ID} -m -s /bin/bash xsk
RUN chown -R xsk:xsk /usr/local/tomcat

FROM base as run

USER ${CNB_USER_ID}:${CNB_GROUP_ID}
RUN chown -R xsk:xsk /usr/local/tomcat

FROM base as build

USER ${CNB_USER_ID}:${CNB_GROUP_ID}
RUN chown -R xsk:xsk /usr/local/tomcat