FROM xsa/system-hana-base

# test incl/excl

MAINTAINER SAP SE

USER root

COPY hdblcm-template.cfg /root/
COPY install.sh /root/
COPY restart.sh /root/

WORKDIR /root

ENTRYPOINT ["/root/install.sh"]
