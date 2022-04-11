# Docker descriptor for XSK
# License - https://github.com/SAP/xsk/blob/main/LICENSE

FROM tomcat:9.0.56-jre11-openjdk-slim AS base

COPY target/ROOT.war $CATALINA_HOME/webapps/

# Copy application folder
ARG APP_PATH
COPY $APP_PATH /usr/local/tomcat/target/dirigible/repository/root/registry/public/
ENV DIRIGIBLE_HOME_URL=/services/v4/web/

# Install wget 
RUN apt-get update && \
    apt-get install wget -y

RUN wget https://repo1.maven.org/maven2/org/postgresql/postgresql/42.1.4/postgresql-42.1.4.jar -P /usr/local/tomcat/lib/
RUN wget https://repo1.maven.org/maven2/com/sap/cloud/db/jdbc/ngdbc/2.8.12/ngdbc-2.8.12.jar -P /usr/local/tomcat/lib/

ENV DIRIGIBLE_JAVASCRIPT_GRAALVM_DEBUGGER_PORT=0.0.0.0:8081

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