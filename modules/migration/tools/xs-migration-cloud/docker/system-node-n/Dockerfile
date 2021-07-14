FROM xsa/system-node-base

USER root

RUN mkdir -p /root/downloads

WORKDIR /root/downloads

RUN curl -L http://git.io/n-install > n-install.sh && chmod +x n-install.sh

RUN ./n-install.sh -y -
