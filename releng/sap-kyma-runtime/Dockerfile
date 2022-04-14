# Docker descriptor for XSK
# Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors

# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Apache License, v2.0
# which accompanies this distribution, and is available at
# http://www.apache.org/licenses/LICENSE-2.0
  
# SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
# SPDX-License-Identifier: Apache-2.0

FROM tomcat:9.0.56-jre11-openjdk-slim AS base

COPY target/ROOT.war $CATALINA_HOME/webapps/

ENV DIRIGIBLE_HOME_URL=/services/v4/web/

# Install wget 
RUN apt-get update && \
    apt-get install wget -y

RUN wget https://repo1.maven.org/maven2/org/postgresql/postgresql/42.1.4/postgresql-42.1.4.jar -P /usr/local/tomcat/lib/
RUN wget https://repo1.maven.org/maven2/com/sap/cloud/db/jdbc/ngdbc/2.8.12/ngdbc-2.8.12.jar -P /usr/local/tomcat/lib/

ENV DIRIGIBLE_JAVASCRIPT_GRAALVM_DEBUGGER_PORT=0.0.0.0:8081

EXPOSE 8080

ADD entrypoint.sh /entrypoint.sh

RUN useradd -rm -d $CATALINA_HOME -s /bin/bash -u 1001 xsk

# Add gosu utility for privilege drop
RUN apt-get update && \
    apt-get install gosu && \
    apt-get install -y git && \
    apt-get clean

ENTRYPOINT ["/entrypoint.sh"]

CMD ["/usr/local/tomcat/bin/catalina.sh","run"]