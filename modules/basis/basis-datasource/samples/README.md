### Sample app for JNDI in Kyma

## Deploy

In order to deploy it you have to create a hanadb instance. 
When creating the `db` make sure to allow all incoming ip traffic. 
Then take its sql endpoint and place in `kube.yml` in the value
associated with `DefaultDB_url`, and change the values `DefaultDB_username ` and `DefaultDB_password` according to what you set when creating the `db`.
  
You can now go to the Kyma dashoboard and deploy the kube.yml file. Create an API rule for the `dynamic-db` service. 

As this is based on tomcat when using the API you have to suffix it with the name of the war file in this case `<api-rule-url>/test_db`.

## Customisation 

If you want to build your own version of the sample you would need jars compiled from KneoWARListener and KneoSmartDataSource and added in the contents of `samples` dir.
You would also need a jar with the driver your preferred database uses added again in `samples`.
You would need to rebuild the Dockerfile and push the image to a Docker repo, then that image should be used in `kube.yml`.

