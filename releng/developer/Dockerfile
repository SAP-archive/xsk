# Docker descriptor for Dirigible
# License - http://www.eclipse.org/legal/epl-v10.html

# (Optional) JDK_TYPE could be `default-jdk` or `external-jdk`
ARG JDK_TYPE=default-jdk

FROM tomcat:8.5.78-jdk17-openjdk AS base

FROM base AS xsk-external-jdk
# If JDK_TYPE is set to `external-jdk`, then the JDK_HOME is required and it should point to the home directory of the jdk
ARG JDK_HOME
RUN echo "Using external JDK"
COPY $JDK_HOME /usr/local/jdk
ENV JAVA_HOME=/usr/local/jdk
ENV JRE_HOME=/usr/local/jdk

FROM base AS xsk-hotswap-jdk
ARG USE_HOTSWAP_JDK
RUN echo "Using HotSwap enabled JDK"
RUN mkdir /usr/local/tomcat/hotswap-jdk
RUN wget https://github.com/TravaOpenJDK/trava-jdk-11-dcevm/releases/download/dcevm-11.0.11%2B1/Openjdk11u-dcevm-linux-x64.tar.gz -P /usr/local/hotswap-jdk/
RUN tar -xf /usr/local/hotswap-jdk/Openjdk11u-dcevm-linux-x64.tar.gz -C /usr/local/tomcat/hotswap-jdk
ENV JAVA_HOME=/usr/local/tomcat/hotswap-jdk/dcevm-11.0.11+1
ENV JRE_HOME=/usr/local/tomcat/hotswap-jdk/dcevm-11.0.11+1
ENV PATH=/usr/local/tomcat/hotswap-jdk/dcevm-11.0.11+1/bin:$PATH

FROM base AS xsk-default-jdk
RUN echo "The default OpenJDK will be used"

FROM xsk-${JDK_TYPE} AS dependencies-base

COPY target/ROOT.war $CATALINA_HOME/webapps/

RUN rm /usr/local/tomcat/conf/tomcat-users.xml
RUN wget http://www.dirigible.io/help/conf/tomcat-users.xml -P /usr/local/tomcat/conf/  --no-check-certificate

RUN wget https://repo1.maven.org/maven2/org/postgresql/postgresql/42.1.4/postgresql-42.1.4.jar -P /usr/local/tomcat/lib/
RUN wget https://repo1.maven.org/maven2/com/sap/cloud/db/jdbc/ngdbc/2.8.12/ngdbc-2.8.12.jar -P /usr/local/tomcat/lib/

COPY target/*.jar $CATALINA_HOME/lib/
RUN sed -i /'<Context>'/c'<Context>\n<Listener className=\"com.sap.xsk.listener.NamingResourcesListener\" factoryClassName=\"com.sap.xsk.factory.DynamicDataSourceFactory\"/>' $CATALINA_HOME/conf/context.xml

#Activate DEBUG mode
#ENV JPDA_ADDRESS=0.0.0.0:8000
#ENV JPDA_TRANSPORT=dt_socket

ENV DIRIGIBLE_JAVASCRIPT_GRAALVM_DEBUGGER_PORT=0.0.0.0:8081
ENV CATALINA_OPTS="--add-opens java.base/java.lang.reflect=ALL-UNNAMED"

EXPOSE 8080 8081 8000

CMD ["catalina.sh", "jpda", "run"]