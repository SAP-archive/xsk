# Docker descriptor for Dirigible
# License - http://www.eclipse.org/legal/epl-v20.html

# (Optional) JDK_TYPE could be `default-jdk` or `external-jdk`
ARG JDK_TYPE=default-jdk

FROM tomcat:9.0.56-jre17-openjdk AS base

FROM base AS xsk-external-jdk
# If JDK_TYPE is set to `external-jdk`, then the JDK_HOME is required and it should point to the home directory of the jdk
ARG JDK_HOME
RUN echo "Using external JDK"
COPY $JDK_HOME /usr/local/jdk
ENV JAVA_HOME=/usr/local/jdk
ENV JRE_HOME=/usr/local/jdk
ENV CATALINA_OPTS="--add-opens java.base/java.lang.reflect=ALL-UNNAMED" # used for XSODataInitializer

FROM base AS xsk-default-jdk
RUN echo "The default OpenJDK will be used"

FROM xsk-${JDK_TYPE} AS dependencies-base

COPY target/ROOT.war $CATALINA_HOME/webapps/

RUN wget https://repo1.maven.org/maven2/org/postgresql/postgresql/42.1.4/postgresql-42.1.4.jar -P /usr/local/tomcat/lib/
RUN wget https://repo1.maven.org/maven2/com/sap/cloud/db/jdbc/ngdbc/2.8.12/ngdbc-2.8.12.jar -P /usr/local/tomcat/lib/

ENV DIRIGIBLE_JAVASCRIPT_GRAALVM_DEBUGGER_PORT=0.0.0.0:8081
ENV CATALINA_OPTS="--add-opens java.base/java.lang.reflect=ALL-UNNAMED"

EXPOSE 8080

ADD entrypoint.sh /entrypoint.sh

RUN useradd -rm -d $CATALINA_HOME -s /bin/bash -u 1001 dirigible

# Add gosu utility for privilege drop
RUN apt-get update && \
    apt-get install gosu && \
    apt-get install -y git && \
    apt-get clean

ENTRYPOINT ["/entrypoint.sh"]

CMD ["/usr/local/tomcat/bin/catalina.sh","jpda","run"]