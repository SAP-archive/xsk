var session = $.session;

var username = session.getUsername()
var timeout = session.getTimeout()
var token = session.getSecurityToken()
var authType = session.authType

// Check the language of the session
response.println("Session language: " + session.language)

// Check if a particular user has the "Administrator" role
if (username === "dirigible" && session.hasAppPrivilege("Administrator")) {
  // Check a specific system privilege for that user
  if (session.hasSystemPrivilege("Dirigible")) {
    // Perform some operation with his session's information
    $.response.setBody("Username: " + username + " with session authentication type: " + authType + " token: " + token + " and timeout " + timeout);
  }
} else {
  // Assert that the user is a Developer in all other cases
  try {
    session.assertAppPrivilege("Developer");
    // Check the authentification type
    if (authType === "BASIC") {
      // Use the information from the current session
      $.response.setBody("Username: " + username + " with session authentication type: " + authType + " token: " + token + " and timeout " + timeout);
    }
  } catch (error) {
    //Display the missing role that was being asserted
    $.response.setBody("User does not have the role: " + error.privilege);
  }
}

// After all calls are complete, check the invocation count of the current session
$.response.setBody("Invocation count: " + session.getInvocationCount());