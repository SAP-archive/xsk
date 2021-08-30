var repositoryManager = require("platform/v4/repository");
var response = require('http/v4/response');
var update = require("db/v4/update");

repositoryManager.createResource("/registry/public/project/localStore.xssecurestore", "{}", "application/json");
// var resource = repositoryManager.getResource("/registry/public/localStore.xssecurestore");
// var content = resource.getText();
//
//
// response.println(content)
// response.flush()
// response.close()

// content !== undefined && content !== null;


update.execute("CREATE TABLE XSK_SECURE_STORE (key bigint(19), store_id varchar(255), user_id varchar(255), data_id varchar(255), data_value blob)");

update.execute("CREATE TABLE XSK_SECURE_STORE_LIST (location varchar(60), is_active boolean)");
update.execute("INSERT INTO XSK_SECURE_STORE_LIST values ('/registry/public/project/localStore.xssecurestore', true)");

var config = {
  name: "foo",
  value: "bar"
};

var aStore = new $.security.Store("project/localStore.xssecurestore");
aStore.store(config);

org.eclipse.dirigible.api.v3.security.UserFacade.setName("test");
var user = require("security/v4/user");

console.error(user.getName());
response.println("dasdsadsa" + user.getName());
response.flush();
response.close();

user.getName() === "test";