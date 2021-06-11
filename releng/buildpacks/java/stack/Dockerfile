# 1. Set a common base
FROM tomcat:8.5-jdk8-openjdk-slim as base

# 2. Set required CNB information
ENV CNB_USER_ID=1000
ENV CNB_GROUP_ID=1000
ENV CNB_STACK_ID="com.sap.kneo.java"
LABEL io.buildpacks.stack.id="com.sap.kneo.java"
LABEL io.buildpacks.stack.mixins: ["build:unzip"]
ARG TMP=/tmp
ARG tomcatdir=/usr/local/tomcat
ARG MAVEN_VERSION=3.6.3
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries


# 3. Create the user 8.5-jdk8-openjdk-slim, 8-jdk8-openjdk-slim
RUN groupadd cnb --gid ${CNB_GROUP_ID} && \
  useradd --uid ${CNB_USER_ID} --gid ${CNB_GROUP_ID} -m -s /bin/bash cnb

# 4. Install common packages
RUN apt-get update && \
  apt-get install -y xz-utils ca-certificates vim && \
  rm -rf /var/lib/apt/lists/*

RUN apt-get update && \
  apt-get install -y git wget jq unzip zip tar curl && \
  rm -rf /var/lib/apt/lists/* && \
  wget https://github.com/sclevine/yj/releases/download/v5.0.0/yj-linux -O /usr/local/bin/yj && \
  chmod +x /usr/local/bin/yj

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
 && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
 && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
 && rm -f /tmp/apache-maven.tar.gz \
 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# 5. Start a new run stage
FROM base as run
RUN wget --quiet https://github.com/SAP/SapMachine/releases/download/sapmachine-11.0.11%2B8/sapmachine-jdk-11.0.11-ea.8_linux-x64_bin.tar.gz -O sapjvm-11.tar.gz
RUN rm -rf /usr/local/openjdk-8/

RUN mkdir -p ${TMP} \
 && tar -xf sapjvm-11.tar.gz -C ${TMP} \
 && rm sapjvm-11.tar.gz \
 && chmod -R 777 "${TMP}"

ENV PATH=${TMP}/sapmachine-jdk-11.0.11/bin:$PATH
ENV JAVA_HOME="${TMP}"/sapmachine-jdk-11.0.11
RUN chmod -R 777 /usr/local/tomcat
ENV CATALINA_HOME="/layers/dirigiblelabs_kneo-java-buildpack/tomcat"

# 6. Set user and group (as declared in base image)
USER ${CNB_USER_ID}:${CNB_GROUP_ID}

# 7. Start a new build stage
FROM base as build

# 8. Install packages that we want to make available at build time

RUN wget --quiet https://github.com/SAP/SapMachine/releases/download/sapmachine-11.0.11%2B8/sapmachine-jdk-11.0.11-ea.8_linux-x64_bin.tar.gz -O sapjvm-11.tar.gz
RUN mkdir -p ${TMP} \
 && tar -xf sapjvm-11.tar.gz -C ${TMP} \
 && rm sapjvm-11.tar.gz \
 && chmod -R 777 "${TMP}"

ENV PATH=${TMP}/sapmachine-jdk-11.0.11/bin:$PATH
ENV JAVA_HOME="${TMP}"/sapmachine-jdk-11.0.11
RUN chmod -R 777 /usr/local/tomcat

RUN sed -i /'<Context>'/c'<Context>\n<Listener className=\"com.sap.xsk.listener.NamingResourcesListener\" factoryClassName=\"com.sap.xsk.factory.DynamicDataSourceFactory\"/>' ${tomcatdir}/conf/context.xml

RUN wget --quiet -O "${tomcatdir}"/lib/ngdbc-2.8.12.jar https://repo1.maven.org/maven2/com/sap/cloud/db/jdbc/ngdbc/2.8.12/ngdbc-2.8.12.jar
RUN wget --quiet -O "${tomcatdir}"/lib/slf4j-api-1.7.5.jar https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.5/slf4j-api-1.7.5.jar

COPY xsk-modules-basis-datasource-0.4.0-SNAPSHOT.jar "${tomcatdir}"/lib

# ========== ADDED ===========
# 9. Set user and group (as declared in base image)
USER ${CNB_USER_ID}:${CNB_GROUP_ID}