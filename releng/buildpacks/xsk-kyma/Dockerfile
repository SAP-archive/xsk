# Docker descriptor for XSK
# Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors

# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Apache License, v2.0
# which accompanies this distribution, and is available at
# http://www.apache.org/licenses/LICENSE-2.0
  
# SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
# SPDX-License-Identifier: Apache-2.0

ARG XSK_VERSION=latest
FROM dirigiblelabs/xsk-kyma:$XSK_VERSION as base

ENV CNB_USER_ID=65532
ENV CNB_GROUP_ID=65532
ENV CNB_STACK_ID="com.sap.kneo.xsk"
LABEL io.buildpacks.stack.id="com.sap.kneo.xsk"

USER ${CNB_USER_ID}:${CNB_GROUP_ID}

FROM base as run

USER ${CNB_USER_ID}:${CNB_GROUP_ID}

FROM base as build

USER ${CNB_USER_ID}:${CNB_GROUP_ID}