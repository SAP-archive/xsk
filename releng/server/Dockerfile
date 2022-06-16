# Docker descriptor for Dirigible
# License - http://www.eclipse.org/legal/epl-v10.html

# (Optional) JDK_TYPE could be `default-jdk` or `external-jdk`
ARG JDK_TYPE=default-jdk

FROM sapmachine:17.0.2 as jdk

FROM tomcat:9.0.64-jre17-temurin AS base

RUN rm -fr /opt/java/openjdk
RUN rm /usr/local/tomcat/conf/tomcat-users.xml

RUN apt-get update && \
    apt-get install wget git gosu -y && \
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

COPY --from=jdk /usr/lib/jvm/sapmachine-17/ /usr/local/jdk

FROM xsk-${JDK_TYPE} AS dependencies-base

COPY target/ROOT.war $CATALINA_HOME/webapps/

ENV CATALINA_HOME=/usr/local/tomcat

ENV JAVA_HOME=/usr/local/jdk
ENV PATH $PATH:$JAVA_HOME/bin

#Activate DEBUG mode
#ENV JPDA_ADDRESS=0.0.0.0:8000
#ENV JPDA_TRANSPORT=dt_socket

ENV DIRIGIBLE_JAVASCRIPT_GRAALVM_DEBUGGER_PORT=0.0.0.0:8081
ENV CATALINA_OPTS="--add-opens java.base/java.lang.reflect=ALL-UNNAMED"

EXPOSE 8080 8081 8000

ADD entrypoint.sh /entrypoint.sh

RUN useradd -rm -d $CATALINA_HOME -s /bin/bash -u 1001 xsk

ENTRYPOINT ["/entrypoint.sh"]

CMD ["/usr/local/tomcat/bin/catalina.sh","jpda","run"]