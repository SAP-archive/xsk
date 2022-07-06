# Docker descriptor for XSK
# Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors

# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Apache License, v2.0
# which accompanies this distribution, and is available at
# http://www.apache.org/licenses/LICENSE-2.0
  
# SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
# SPDX-License-Identifier: Apache-2.0

# (Optional) JDK_TYPE could be `default-jdk` or `external-jdk`
ARG JDK_TYPE=default-jdk
ARG DIRIGBLE_JAVASCRIPT_GRAALVM_DEBUGGER_PORT
ARG JPDA_ADDRESS
ARG JPDA_TRANSPORT

FROM dirigiblelabs/xsk-kyma-runtime-base as base

COPY target/ROOT.war /usr/local/tomcat/webapps/

FROM base AS xsk-external-jdk
# If JDK_TYPE is set to `external-jdk`, then the JDK_HOME is required and it should point to the home directory of the jdk
ARG JDK_HOME
RUN echo "Using external JDK"
COPY $JDK_HOME /usr/local/jdk
ENV JAVA_HOME=/usr/local/jdk
ENV JRE_HOME=/usr/local/jdk

FROM base AS xsk-default-jdk
RUN echo "The default OpenJDK will be used"

COPY --from=base /usr/local/jdk /usr/local/jdk

FROM xsk-${JDK_TYPE} AS dependencies-base

FROM gcr.io/distroless/base-debian11:debug

ENV CATALINA_HOME=/usr/local/tomcat
ENV JAVA_HOME=/usr/local/jdk
ENV PATH $PATH:$JAVA_HOME/bin
ENV CATALINA_OPTS="--add-opens java.base/java.lang.reflect=ALL-UNNAMED"

#Activate DEBUG mode
ENV DIRIGBLE_JAVASCRIPT_GRAALVM_DEBUGGER_PORT=$DIRIGBLE_JAVASCRIPT_GRAALVM_DEBUGGER_PORT
ENV JPDA_ADDRESS=$JPDA_ADDRESS
ENV JPDA_TRANSPORT=$JPDA_TRANSPORT

USER nonroot
COPY --from=dependencies-base --chown=nonroot:nonroot $CATALINA_HOME $CATALINA_HOME
COPY --from=dependencies-base --chown=nonroot:nonroot $JAVA_HOME $JAVA_HOME
COPY --from=dependencies-base --chown=nonroot:nonroot /bin/sh /bin/sh
COPY --from=dependencies-base --chown=nonroot:nonroot /usr/bin/git /usr/bin/git
COPY --from=dependencies-base --chown=nonroot:nonroot /bin/bash /bin/bash
COPY --from=dependencies-base /lib/x86_64-linux-gnu/libz.so.1 /lib/x86_64-linux-gnu/libz.so.1
COPY --from=dependencies-base /lib/x86_64-linux-gnu/libpcre2-8.so.0 /lib/x86_64-linux-gnu/libpcre2-8.so.0
COPY --from=dependencies-base /lib/x86_64-linux-gnu/libtinfo.so.6 /lib/x86_64-linux-gnu/libtinfo.so.6
WORKDIR $CATALINA_HOME

EXPOSE 8080

ENTRYPOINT [ ]

CMD [ "/bin/sh","/usr/local/tomcat/bin/catalina.sh","jpda","run" ]