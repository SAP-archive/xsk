# Docker descriptor for Dirigible
# License - http://www.eclipse.org/legal/epl-v20.html

FROM tomcat:8.5.43-jdk11-openjdk

RUN rm -R /usr/local/tomcat/webapps/*
COPY releng/sap-kyma/target/ROOT.war $CATALINA_HOME/webapps/

RUN wget https://repo1.maven.org/maven2/org/postgresql/postgresql/42.1.4/postgresql-42.1.4.jar -P /usr/local/tomcat/lib/
RUN wget https://repo1.maven.org/maven2/com/sap/cloud/db/jdbc/ngdbc/2.8.12/ngdbc-2.8.12.jar -P /usr/local/tomcat/lib/

COPY ../../modules/basis/basis-datasource/target/xsk-modules-basis-datasource-0.4.0-SNAPSHOT.jar $CATALINA_HOME/lib/
RUN sed -i /'<Context>'/c'<Context>\n<Listener className=\"com.sap.xsk.listener.NamingResourcesListener\" factoryClassName=\"com.sap.xsk.factory.DynamicDataSourceFactory\"/>' $CATALINA_HOME/conf/context.xml

ENV DIRIGBLE_JAVASCRIPT_GRAALVM_DEBUGGER_PORT=0.0.0.0:8081

EXPOSE 8080

CMD ["catalina.sh", "jpda", "run"]

