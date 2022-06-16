# Docker descriptor for XSK
# Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors

# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Apache License, v2.0
# which accompanies this distribution, and is available at
# http://www.apache.org/licenses/LICENSE-2.0
  
# SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
# SPDX-License-Identifier: Apache-2.0

FROM dirigiblelabs/xsk-kyma-runtime-base AS base

ENV DIRIGIBLE_HOME_URL=/services/v4/web/
ENV CATALINA_HOME=/usr/local/tomcat

ENV CATALINA_OPTS="--add-opens java.base/java.lang.reflect=ALL-UNNAMED"

EXPOSE 8080

CMD ["/usr/local/tomcat/bin/catalina.sh","run"]