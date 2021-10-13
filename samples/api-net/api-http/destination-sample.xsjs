let http = $.net.http;

/*
Read service.xshttpdest inside the Demo package that contains:
host=https://services.odata.org;
pathPrefix=/V4/Northwind/Northwind.svc/;
*/
let dest = http.readDestination("demo", "service");
// Check if the file has been read properly
$.response.setBody("Host: " + dest.host + " Path Prefix: " + dest.pathPrefix);