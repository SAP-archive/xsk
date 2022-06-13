# Docker descriptor for Dirigible
# License - http://www.eclipse.org/legal/epl-v10.html

# (Optional) JDK_TYPE could be `default-jdk` or `external-jdk`
ARG JDK_TYPE=default-jdk

FROM sapmachine:17.0.2 as jdk

FROM tomcat:9.0.56-jre17-temurin  AS base

RUN rm -fr /opt/java/openjdk
RUN rm /usr/local/tomcat/conf/tomcat-users.xml

RUN mkdir -p /usr/local/tomcat/target/dirigible/repository/root/registry/public/
COPY target/ROOT.war $CATALINA_HOME/webapps/

RUN apt-get update && \
    apt-get install wget git -y && \
    apt-get clean

RUN wget --no-check-certificate https://www.dirigible.io/help/conf/tomcat-users.xml -P /usr/local/tomcat/conf/

RUN wget https://repo1.maven.org/maven2/org/postgresql/postgresql/42.1.4/postgresql-42.1.4.jar -P /usr/local/tomcat/lib/
RUN wget https://repo1.maven.org/maven2/com/sap/cloud/db/jdbc/ngdbc/2.8.12/ngdbc-2.8.12.jar -P /usr/local/tomcat/lib/


FROM base AS xsk-external-jdk
# If JDK_TYPE is set to `external-jdk`, then the JDK_HOME is required and it should point to the home directory of the jdk
ARG JDK_HOME
RUN echo "Using external JDK"
COPY $JDK_HOME /usr/local/jdk
ENV JAVA_HOME=/usr/local/jdk
ENV JRE_HOME=/usr/local/jdk

FROM base AS xsk-default-jdk
RUN echo "The default OpenJDK will be used"

RUN mkdir -p /usr/local/jdk
COPY --from=jdk /usr/lib/jvm/sapmachine-17/ /usr/local/jdk

FROM xsk-${JDK_TYPE} AS dependencies-base

FROM gcr.io/distroless/base-debian11:debug

ENV CATALINA_HOME=/usr/local/tomcat

ENV JAVA_HOME=/usr/local/jdk
ENV PATH $PATH:$JAVA_HOME/bin

ENV DIRIGBLE_JAVASCRIPT_GRAALVM_DEBUGGER_PORT=0.0.0.0:8081
#Activate DEBUG mode
#ENV JPDA_ADDRESS=0.0.0.0:8000
#ENV JPDA_TRANSPORT=dt_socket

USER nonroot
COPY --from=dependencies-base --chown=nonroot:nonroot $CATALINA_HOME $CATALINA_HOME
WORKDIR $CATALINA_HOME

COPY --from=dependencies-base $JAVA_HOME $JAVA_HOME
COPY --from=dependencies-base /lib/x86_64-linux-gnu/libz.so.1 /lib/x86_64-linux-gnu/libz.so.1
COPY --from=dependencies-base /bin/sh /bin/sh

EXPOSE 8080 8081 8000

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