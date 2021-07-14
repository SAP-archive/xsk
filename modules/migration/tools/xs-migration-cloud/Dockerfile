FROM xsa/system-java-base

USER root

RUN mkdir -p /root/xs-migration/result 

WORKDIR /root/xs-migration

RUN yum install -y gcc-c++ make &&\
    wget "http://nexus.wdf.sap.corp:8081/nexus/service/local/repositories/deploy.releases.xmake/content/com/sap/xs/nodejs/nodejs_linuxx86_64/10.15.2/nodejs_linuxx86_64-10.15.2.zip" &&\
    unzip nodejs_linuxx86_64-10.15.2.zip &&\
    mv node-v10.15.3-linuxx86_64/ node

ENV PATH /root/xs-migration/node/bin:$PATH

ENV NODE_HOME /root/xs-migration/node 

COPY package.json Gruntfile.js /root/xs-migration/

RUN npm config set registry http://nexus.wdf.sap.corp:8081/nexus/content/groups/build.milestones.npm &&\
    npm run install-dev 

ENV HANA_HOST=asdasd.wdf.sap.corp \
    HANA_PORT=8000 \
    HANA_USER=SYSTEM \
    HANA_PASSWD=asdasd \
    HANA_PROTOCOL=http \
    HANA_SQL_PORT=30015 \
    NO_PROXY='nexus.wdf.sap.corp,asdasd.wdf.sap.corp,localhost' \
    no_proxy='nexus.wdf.sap.corp,asdasd.wdf.sap.corp,localhost'
   
VOLUME /root/xs-migration/result

COPY . /root/xs-migration

ENTRYPOINT ["/root/xs-migration/xs-migration"]
