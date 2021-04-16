FROM tomcat:8.5.38-jre8

RUN rm -fR $CATALINA_HOME/webapps/ROOT

COPY *.war $CATALINA_HOME/webapps/

COPY *.jar $CATALINA_HOME/lib/

RUN sed -i /'<Context>'/c'<Context>\n<Listener className=\"io.dirigible.tomcat.listener.NamingResourcesListener\" factoryClassName=\"io.dirigible.smart.datasource.object.factory.SmartDatasourceObjectFactory\"/>' $CATALINA_HOME/conf/context.xml

