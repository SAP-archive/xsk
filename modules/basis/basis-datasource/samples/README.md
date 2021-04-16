### Sample app for JNDI in Kyma

## Deploy

In order to deploy it you have to create a hanadb instance. 
When creating the `db` make sure to allow all incoming ip traffic. 
Then take its sql endpoint and place in kube.yml in the value
associated with `DefaultDB_url`, and change the values `DefaultDB_username ` and `DefaultDB_password` according to what you set.
  
You can now go to the Kyma dashoboard and deploy the kube.yml file. Create an API rule for the `dynamic-db` service. 

As this is based on tomcat when using the API you have to suffix it with the name of the war file in this case `<api-rule-url>/test_postgre`.