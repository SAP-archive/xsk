# Docker descriptor for XSK
# Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors

# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Apache License, v2.0
# which accompanies this distribution, and is available at
# http://www.apache.org/licenses/LICENSE-2.0
  
# SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
# SPDX-License-Identifier: Apache-2.0
FROM dirigiblelabs/xsk-kyma-runtime-base as base

COPY target/ROOT.war /usr/local/tomcat/webapps/

FROM gcr.io/distroless/base-debian11

ENV DIRIGIBLE_HOME_URL=/services/v4/web/
ENV CATALINA_HOME=/usr/local/tomcat

USER nonroot
COPY --from=base --chown=nonroot:nonroot $CATALINA_HOME $CATALINA_HOME
COPY --from=base --chown=nonroot:nonroot /usr/lib/jvm/sapmachine-17/ /usr/local/jdk
COPY --from=base --chown=nonroot:nonroot /bin/bash /bin/bash
COPY --from=base --chown=nonroot:nonroot /bin/mkdir /bin/mkdir
COPY --from=base --chown=nonroot:nonroot /bin/cp /bin/cp
COPY --from=base --chown=nonroot:nonroot /bin/cat /bin/cat
COPY --from=base --chown=nonroot:nonroot /bin/rm /bin/rm
COPY --from=base /lib/x86_64-linux-gnu/libz.so.1 /lib/x86_64-linux-gnu/libz.so.1
COPY --from=base /lib/x86_64-linux-gnu/libselinux.so.1 /lib/x86_64-linux-gnu/libselinux.so.1
COPY --from=base /lib/x86_64-linux-gnu/libpcre2-8.so.0 /lib/x86_64-linux-gnu/libpcre2-8.so.0
COPY --from=base /lib/x86_64-linux-gnu/libtinfo.so.6 /lib/x86_64-linux-gnu/libtinfo.so.6
COPY --from=base /lib/x86_64-linux-gnu/libacl.so.1 /lib/x86_64-linux-gnu/libacl.so.1
COPY --from=base /lib/x86_64-linux-gnu/libattr.so.1 /lib/x86_64-linux-gnu/libattr.so.1
WORKDIR $CATALINA_HOME


ENV JAVA_HOME /usr/local/jdk
ENV PATH $PATH:$JAVA_HOME/bin

EXPOSE 8080

CMD ["java", \
 "-Djava.util.logging.config.file=/usr/local/tomcat/conf/logging.properties", \
 "--add-opens=java.base/java.lang=ALL-UNNAMED", \
 "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED", \
 "-Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager", \
 "-Djdk.tls.ephemeralDHKeySize=2048", \
 "-Djava.protocol.handler.pkgs=org.apache.catalina.webresources", \
 "-Dorg.apache.catalina.security.SecurityListener.UMASK=0027", \
 "-Dignore.endorsed.dirs=", \
 "-classpath", \
 "/usr/local/tomcat/bin/bootstrap.jar:/usr/local/tomcat/bin/tomcat-juli.jar", \
 "-Dcatalina.base=/usr/local/tomcat/", \
 "-Dcatalina.home=/usr/local/tomcat/", \
 "-Djava.io.tmpdir=/usr/local/tomcat/temp", \
 "org.apache.catalina.startup.Bootstrap", \
 "start" \
]

ENTRYPOINT []