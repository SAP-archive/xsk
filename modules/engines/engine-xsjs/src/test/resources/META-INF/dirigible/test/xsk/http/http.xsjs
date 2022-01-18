let http = $.net.http;
var assertTrue = require('utils/assert').assertTrue;

let client = new http.Client();
var request = new $.net.http.Request($.net.http.GET, "/V4/Northwind/Northwind.svc/");

client.setTimeout(10);
client.request(request, "https://services.odata.org");

let response = client.getResponse();

assertTrue(response.status === http.OK && response.cookies != null && response.headers != null && response.body != null);
