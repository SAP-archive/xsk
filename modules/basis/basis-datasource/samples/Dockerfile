FROM tomcat:8.5.43-jdk11-openjdk

RUN rm -fR $CATALINA_HOME/webapps/ROOT

COPY *.war $CATALINA_HOME/webapps/

COPY *.jar $CATALINA_HOME/lib/

RUN sed -i /'<Context>'/c'<Context>\n<Listener className=\"com.sap.xsk.listener.NamingResourcesListener\" factoryClassName=\"com.sap.xsk.factory.DynamicDataSourceFactory\"/>' $CATALINA_HOME/conf/context.xml

