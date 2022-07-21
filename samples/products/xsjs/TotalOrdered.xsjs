function doGet(){
    try{
        var connection = $.hdb.getConnection();
        var st = connection.executeQuery('SELECT * FROM "_SYS_BIC"."products.views/ordered_items_type_fn"');
        $.response.status = $.net.http.OK;
    	$.response.contentType = "application/json";
        $.response.setBody(JSON.stringify(st));
    } catch (e){
        $.trace.error(e);
    }finally{
        connection.close();
    }
}

(function doService() {
	var method = $.request.method;

	if (method === $.net.http.GET) {
		doGet();
	} else {
		$.response.status = $.net.http.BAD_REQUEST;
		$.response.setBody("Only GET requests can be executed");
	}
}());