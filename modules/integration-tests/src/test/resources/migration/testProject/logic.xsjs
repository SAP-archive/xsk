function getUsername(){
   var username =  $.session.getUsername();
   return username;
}
var result = "Hello World from User " + getUsername();
$.response.setBody(result);
