var http = $.net.http;
var customerName = $.request.parameters.get("customerName");
if (!customerName) {
    $.response.status = http.BAD_REQUEST;
    $.response.setBody("Query parameter customerName required");
} else {
    var client = new http.Client();
    var request = new http.Request(http.GET, "/Customers");
    request.parameters.set("$filter", "ContactName eq '" + customerName + "'");
    request.headers.set("Accept", "*/*");
    var destination = http.readDestination("products.protected", "customers");
    client.request(request, destination);
    var response = client.getResponse();
    client.close();
    $.response.setBody(response.body.asString());
}