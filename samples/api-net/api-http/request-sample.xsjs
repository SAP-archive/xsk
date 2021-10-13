let http = $.net.http;

let dest = http.readDestination("demo", "service");
// create client
let client = new http.Client();
// create Request class with the HTTP method constants as a first argument and the path of the resource as a second
let request = new http.Request(http.GET, "/"); // new Request(METHOD, PATH)
// the PATH will be prefixed by destination's pathPrefix, e.g. "/search?" on the request

// Use the Request class to send an http request with the net.http.Client class
client.request(request, dest);