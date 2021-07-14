FROM xsa/system-node-0127

USER root

RUN mkdir /root/.ssh && \
    mkdir -p /root/src

COPY id_rsa known_hosts /root/.ssh/

WORKDIR /root/src

RUN git clone git@github.wdf.sap.corp:XSA-Migration/xs-migration.git
