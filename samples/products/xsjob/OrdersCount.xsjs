function countOrders() {
	var connection = $.db.getConnection();
	try {
		var pstmt = connection.prepareStatement('select count(*) from "products.db::Products.Orders"');
		var res = pstmt.executeQuery();
		if (res.next()) {
			$.trace.info('Count: ' + res.getInteger(1));
		}
	} finally {
		connection.close();
	}
}
