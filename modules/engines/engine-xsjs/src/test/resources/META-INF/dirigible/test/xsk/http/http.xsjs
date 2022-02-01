let http = $.net.http;
var assertTrue = require('utils/assert').assertTrue;
let client = new http.Client();
let request = new http.Request(http.GET, "/");
let destination = http.readDestination("test", "test-destination");

client.setTimeout(10);

client.request(request, destination);
let response = client.getResponse();

assertTrue(response.status === http.OK && response.cookies != null && response.headers != null && response.body != null);