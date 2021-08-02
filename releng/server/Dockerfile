# Docker descriptor for Dirigible
# License - http://www.eclipse.org/legal/epl-v10.html

# (Optional) JDK_TYPE could be `default-jdk` or `external-jdk`
ARG JDK_TYPE=default-jdk

FROM tomcat:8.5.43-jdk11-openjdk AS base

FROM base AS xsk-external-jdk
# If JDK_TYPE is set to `external-jdk`, then the JDK_HOME is required and it should point to the home directory of the jdk
ARG JDK_HOME
RUN echo "Using external JDK"
COPY $JDK_HOME /usr/local/jdk
ENV JAVA_HOME=/usr/local/jdk
ENV JRE_HOME=/usr/local/jdk

FROM base AS xsk-default-jdk
RUN echo "The default OpenJDK will be used"

FROM xsk-${JDK_TYPE} AS final

RUN rm -R /usr/local/tomcat/webapps/*
COPY target/ROOT.war $CATALINA_HOME/webapps/

RUN rm /usr/local/tomcat/conf/tomcat-users.xml
RUN wget http://www.dirigible.io/help/conf/tomcat-users.xml -P /usr/local/tomcat/conf/

RUN wget https://repo1.maven.org/maven2/org/postgresql/postgresql/42.1.4/postgresql-42.1.4.jar -P /usr/local/tomcat/lib/
RUN wget https://repo1.maven.org/maven2/com/sap/cloud/db/jdbc/ngdbc/2.8.12/ngdbc-2.8.12.jar -P /usr/local/tomcat/lib/

#Activate DEBUG mode
#ENV JPDA_ADDRESS=0.0.0.0:8000
#ENV JPDA_TRANSPORT=dt_socket

ENV DIRIGBLE_JAVASCRIPT_GRAALVM_DEBUGGER_PORT=0.0.0.0:8081

EXPOSE 8080 8081 8000

CMD ["catalina.sh", "jpda", "run"]