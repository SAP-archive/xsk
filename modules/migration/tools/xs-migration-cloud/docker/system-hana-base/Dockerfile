FROM registry.access.redhat.com/rhel6.7

MAINTAINER SAP SE

USER root
ENV PATH $PATH:/sbin

RUN chkconfig; # fail early in case this still fails

RUN echo "alias l='ls -lah'; \
          alias ..='cd ..'" >> /root/.bashrc

RUN . /root/.bashrc

WORKDIR /

# satellite registration
COPY rhnpkgs.repo /etc/yum.repos.d/rhnpkgs.repo

RUN yum install rhn-setup -y; \
    rpm -Uvh http://rhnproxy.wdf.sap.corp/pub/rhn-org-trusted-ssl-cert.noarch.rpm; \
    sed -i s/device\ \=\ device.lower\(\)/#device\ \=\ device.lower\(\)/g /usr/share/rhn/up2date_client/hwdata.py; \
    rhnreg_ks --force --serverUrl "https://rhnproxy.wdf.sap.corp/XMLRPC"  --sslCACert=/usr/share/rhn/RHN-ORG-TRUSTED-SSL-CERT --activationkey="1-rhel6.7-hana-x86_64-container-key"; \
    /bin/rm -f /etc/yum.repos.d/rhnpkgs.repo; \
    yum clean all; \
    yum repolist; \

RUN yum update -y; \
    yum install -y  wget \
                    git \
                    openssh-server \
                    unzip \
                    openssl098e \
                    nfs-utils \
                    libaio \
                    numactl \
                    libtool-ltdl \
                    which \
                    glibc-devel \
                    glibc-headers \
                    kernel-headers \
                    compat-sap-c++ \
                    sudo.x86_64 \
                    libgomp \
                    perl-Net-SSLeay \
                    lynx \
                    tar; \
    yum reinstall -y cracklib-dicts; \
    yum clean all

WORKDIR /usr/lib64

# libcrypto version required for xs2 nginx
RUN ln -s libcrypto.so.0.9.8e libcrypto.so.0.9.8; \
    /sbin/ldconfig

RUN ln -s /usr/lib64/libssl.so.0.9.8e /usr/lib64/libssl.so.0.9.8

RUN wget --no-check-certificate https://nexus.wdf.sap.corp:8443/nexus/content/groups/build.releases.xmake/com/sap/tools/sapcar/linuxx86_64/opt/sapcar/721.510.510/sapcar-721.510.510.bin; \
    chmod +x sapcar-721.510.510.bin;

# ssh
COPY sshd_config /etc/ssh/sshd_config

COPY sudoers /etc/sudoers

RUN chkconfig sshd on &&\
    ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key -N '' &&\
    ssh-keygen -t dsa -f /etc/ssh/ssh_host_dsa_key -N ''

RUN echo 'root:quality' | chpasswd

EXPOSE 22
