function countOrders() {
	var connection = $.db.getConnection();
	try {
		var pstmt = connection.prepareStatement('SELECT COUNT(*) FROM "XSK_SAMPLES_PRODUCTS"."products.db::Products.Orders"');
		var res = pstmt.executeQuery();
		if (res.next()) {
			$.trace.info('Count: ' + res.getInteger(1));
		}
	} finally {
		connection.close();
	}
}
