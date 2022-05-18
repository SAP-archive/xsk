var assertTrue = require('utils/assert').assertTrue;
let username = $.session.getUsername()
let appPrivileges = $.session.hasAppPrivilege("Developer")
if (appPrivileges) {
  $.session.assertAppPrivilege("Developer")
}

let sysPrivileges = $.session.hasSystemPrivilege("All")
if (sysPrivileges) {
  $.session.assertSystemPrivilege("All")
}

let timeout = $.session.getTimeout()
let token = "None"
let authType = $.session.authType
if (authType) {
  token = $.session.getSecurityToken()
} else {
  authType = "None"
}
// Parameter values in console
$.response.setBody("Username: " +username);
$.response.setBody("App Privileges: " +appPrivileges);
$.response.setBody("Sys Privileges: " +sysPrivileges);
$.response.setBody("Timeout: " +timeout);
$.response.setBody("Token: " +token);
$.response.setBody("Auth Type: " +authType);

assertTrue(username === "TestUser" && appPrivileges !== null && sysPrivileges != null && timeout !== null && authType !== null && token != null);