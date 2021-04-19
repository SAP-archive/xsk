## Deploy XSK

### JNDI Datasource

JNDI Datasource is a database resource that can give you connection to a db.
The way you use it is you specify a resource name in `web.xml`, by default most people use `jdbc/DefaultDB`.
This resource is then created by a factory class that is inside Tomcat.
The factory itself can be default Tomcat factory, or a custom factory created by us.

### Listener

A listener is simply a class that is waiting for a certain kind of lifecycle event(stop or start of Tomcat).
On startup, we create a delegate factory, that is simply a class which collects all the factories it can find in the Runtime.
Everytime there is a resource coming from the `web.xml` the delegate factory tries to find the factory that is able to create it.

### Dockerfile

Currently, if we want to deploy `xsk` with `dynamic db`, that is using JNDI Datasource,
we need to add `KneoSmartDataSource` and `KneoWARListener` as jars to the `xsk`
docker file, build it and push it to a docker repo. Then we must change its `kube.yml`(which can be found in the readme of [xsk](https://github.com/SAP/xsk/tree/dynamic-db#kyma-2))
to include the newly created docker image. 
In the  same `kube.yml` we need to add the env variables needed for the creation of a db.
All of these envs need to be prefixed with `DefaultDB_`, for example the username for the db would be `DefaultDB_username`.
