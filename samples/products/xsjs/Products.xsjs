var ProductsService = $.import("products.xsjs", "ProductsLib").Products;
var connection = $.hdb.getConnection({
	treatDateAsUTC: true
});

function error(msg, statusCode) {
	$.response.status = statusCode;
	$.response.contentType = "text/plain";
	$.response.setBody(msg);
}

/**
 * respond with a result with content type 'application/json' with the status code 'OK' (200) and specified message
 */
function positiveResult(oResponse) {
	$.response.status = $.net.http.OK;
	$.response.contentType = "application/json";
	var sResponse = JSON.stringify(oResponse);
	$.response.setBody(sResponse);
}

/**
 * Handle the post-request.
 *
 * See above for the expected format of a request-entity
 */
function doPost() {
    
	var request = JSON.parse($.request.body.asString());
	var productsService = new ProductsService(connection, "XSK_SAMPLES_PRODUCTS");
	var response = productsService.handlePostRequest(request);
	connection.close();
	positiveResult(response);
}

function doGet() {
    
	var response;
	var productsService = new ProductsService(connection, "XSK_SAMPLES_PRODUCTS");
	response = productsService.handleGetRequest();
	connection.close();
	positiveResult(response);
}

(function doService() {
	var method = $.request.method;

	if (method === $.net.http.GET) {
		doGet();
	} else if (method === $.net.http.POST) {
		doPost();
	} else {
		$.response.status = $.net.http.BAD_REQUEST;
	}
}());