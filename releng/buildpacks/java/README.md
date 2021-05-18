## Overview
- The (k)neo buildpack should consist of a tomcat together with the kneo libraries and the application dependencies.
- The (k)neo application should get same env variables like the old neo applications. For more informations see [here](https://github.tools.sap/unified-runtimes/architecture/blob/main/compatibility/neo/README.md#environment).
- It is not yet defined how to request a service (e.g. hana db)
- Service Binding will be injected to the neo pod and JNDI will use it.

## Buildpacks TODO
- Create buildpack, which contains a tomcat together with some SAP specific libraries.

## Steps to reproduce

Create neo-stack
```bash
cd neo-stack
# Build build image
docker build . -t neo-stack-build:bionic --target build
# Build run image
docker build . -t neo-stack-run:bionic --target run
```

Build neo-builder (include `unzip` used in the neo-java-buildpack)
```bash
cd neo-builder
pack builder create neo-builder:bionic --config ./builder.toml
```


Build neo app with `neo-java-buildpack`
```bash
cd cki
pack build neo-test --builder neo-builder:bionic --buildpack ./neo-java-buildpack --path <path-to-war-directory> --network host
```

## Neo persistence application example

Prerequisites:
- Hana instance

Go to https://tools.hana.ondemand.com/ and download the `neo-java-web-sdk-3.127.13.3.zip` and unzip it.

```bash
# Navigate to sample app
cd neo-java-web-sdk-3.127.13.3/samples/persistence-with-jdbc
# Create DB resource. In NEO this is provided by the runtime.
cat > WebContent/META-INF/context.xml <<EOF
<Context>
  <Resource name="jdbc/DefaultDB" auth="Container" type="javax.sql.DataSource"
               maxTotal="100" maxIdle="30" maxWaitMillis="10000"
               username="<username>" password="<password>" driverClassName="com.sap.db.jdbc.Driver"
               url="jdbc:sap://<server>:<port>/<database>"/>
</Context>
EOF
# build war
mvn clean install
# build app with buildpacks
pack build neo-test --builder neo-builder:bionic --buildpack ~/workspace/neo-buildpack/cki/neo-java-buildpack --path target/ --network host
# run app 
docker run -p 8080:8080 neo-test
```
Visit http://localhost:8080/persistence-with-jdbc/ to see if everything works fine.

## Notes
To improve dev efficiency, you can run:
```bash
# download neo-runtime to cwd
wget http://nexus.wdf.sap.corp:8081/nexus/content/groups/build.milestones/com/sap/cloud/runtime/kotyo/com.sap.cloud.runtime.kotyo/2.174.1/com.sap.cloud.runtime.kotyo-2.174.1.zip

# start http server
python3 -m http.server

# uncomment this line in bin/build
# wget --quiet host.docker.internal:8000/com.sap.cloud.runtime.kotyo-2.174.1.zip
```
