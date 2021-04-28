# 1. Set a common base
FROM dirigiblelabs/dirigible-base-platform-sap-kyma:latest as base

# ========== ADDED ===========
# 2. Set required CNB information
ENV CNB_USER_ID=1000
ENV CNB_GROUP_ID=1000
ENV CNB_STACK_ID="com.sap.kneo.xsk"
LABEL io.buildpacks.stack.id="com.sap.kneo.xsk"

# 3. Create the user
RUN groupadd cnb --gid ${CNB_GROUP_ID} && \
  useradd --uid ${CNB_USER_ID} --gid ${CNB_GROUP_ID} -m -s /bin/bash cnb

RUN chmod -R 777 /usr/local/tomcat

# ========== ADDED ===========
# 5. Start a new run stage
FROM base as run

RUN chmod -R 777 /usr/local/tomcat

# 6. Set user and group (as declared in base image)
USER ${CNB_USER_ID}:${CNB_GROUP_ID}

# ========== ADDED ===========
# 7. Start a new build stage
FROM base as build

RUN chmod -R 777 /usr/local/tomcat

# ========== ADDED ===========
# 9. Set user and group (as declared in base image)
USER ${CNB_USER_ID}:${CNB_GROUP_ID}

