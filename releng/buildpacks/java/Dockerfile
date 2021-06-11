FROM tomcat:8.5.43-jdk11-openjdk as base

ENV CNB_USER_ID=1000
ENV CNB_GROUP_ID=1000
ENV CNB_STACK_ID="com.sap.kneo.java"
LABEL io.buildpacks.stack.id="com.sap.kneo.java"

RUN groupadd cnb --gid ${CNB_GROUP_ID} && \
  useradd --uid ${CNB_USER_ID} --gid ${CNB_GROUP_ID} -m -s /bin/bash cnb

RUN chmod -R 777 /usr/local/tomcat

FROM base as run

RUN chmod -R 777 /usr/local/tomcat

USER ${CNB_USER_ID}:${CNB_GROUP_ID}

FROM base as build

RUN chmod -R 777 /usr/local/tomcat

RUN sed -i /'<Context>'/c'<Context>\n<Listener className=\"com.sap.xsk.listener.NamingResourcesListener\" factoryClassName=\"com.sap.xsk.factory.DynamicDataSourceFactory\"/>' /usr/local/tomcat/conf/context.xml

RUN wget https://repo1.maven.org/maven2/org/postgresql/postgresql/42.1.4/postgresql-42.1.4.jar -P /usr/local/tomcat/lib/
RUN wget https://repo1.maven.org/maven2/com/sap/cloud/db/jdbc/ngdbc/2.8.12/ngdbc-2.8.12.jar -P /usr/local/tomcat/lib/
RUN wget https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.5/slf4j-api-1.7.5.jar -P /usr/local/tomcat/lib/

COPY target/*.jar /usr/local/tomcat/lib

USER ${CNB_USER_ID}:${CNB_GROUP_ID}