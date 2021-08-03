# Docker descriptor for Dirigible
# License - http://www.eclipse.org/legal/epl-v20.html

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

FROM xsk-${JDK_TYPE} AS dependencies-base
# Install OpenJDK-8
RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get install -y ant && \
    apt-get clean;

# Fix certificate issues
RUN apt-get update && \
    apt-get install ca-certificates-java && \
    apt-get clean && \
    update-ca-certificates -f;

# Install NodeJS
RUN apt-get install -y curl \
  && curl -sL https://deb.nodesource.com/setup_12.x | bash - \
  && apt-get install -y nodejs \
  && curl -L https://www.npmjs.com/install.sh | sh

FROM dependencies-base AS migration-tools-tooling-setup
COPY target/migration-tools/neo $CATALINA_HOME/migration-tools/neo
COPY target/migration-tools/xs-migration-cloud/package.json $CATALINA_HOME/migration-tools/xs-migration-cloud/
RUN cd $CATALINA_HOME/migration-tools/xs-migration-cloud && npm install

FROM migration-tools-tooling-setup AS migration-tools-node-app-copy-code
COPY target/migration-tools/xs-migration-cloud $CATALINA_HOME/migration-tools/xs-migration-cloud/

FROM migration-tools-node-app-copy-code AS war-setup

RUN rm -R /usr/local/tomcat/webapps/*
COPY target/ROOT.war $CATALINA_HOME/webapps/

RUN wget https://repo1.maven.org/maven2/org/postgresql/postgresql/42.1.4/postgresql-42.1.4.jar -P /usr/local/tomcat/lib/
RUN wget https://repo1.maven.org/maven2/com/sap/cloud/db/jdbc/ngdbc/2.8.12/ngdbc-2.8.12.jar -P /usr/local/tomcat/lib/

FROM war-setup AS final
ENV DIRIGBLE_JAVASCRIPT_GRAALVM_DEBUGGER_PORT=0.0.0.0:8081

EXPOSE 8080

CMD ["catalina.sh", "jpda", "run"]