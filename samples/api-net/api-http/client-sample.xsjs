let http = $.net.http;

/*
Read service.xshttpdest inside the Demo package that contains:
host=https://services.odata.org;
pathPrefix=/V4/Northwind/Northwind.svc/;
*/
let destination = http.readDestination("demo", "service");

// create client
let client = new http.Client();
let request = new http.Request(http.GET, "/"); // new Request(METHOD, PATH)
// the PATH will be prefixed by destination's pathPrefix, e.g. "/search?" on the request
// set the timeout in seconds
client.setTimeout(10);
// send the request and synchronously get the response
client.request(request, destination);
let response = client.getResponse();

// get all the cookies and headers from the response
let cookies = [], headers = [];

for (let i = 0; i < response.cookies.length; i++) {
  cookies.push(response.cookies[i]);
}

for (let i = 0; i < response.headers.length; i++) {
  headers.push(response.headers[i]);
}

// check the contents of the response
$.response.setBody("status: " + response.status + " cookies: " + JSON.stringify(cookies) + " headers: " + JSON.stringify(headers) + " body: " + response.body.asString());