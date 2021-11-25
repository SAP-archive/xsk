var hdbConnection = $.hdb.getConnection({
	treatDateAsUTC: true
});

var schema = "XSK_SAMPLES_PRODUCTS";
var PRODUCTS_TABLE = "products.db::Products.Orders";
var ITEMS_TABLE = "products.db::Products.Item";

function Products(connection, schema) {
	connection = connection || $.hdb.getConnection({
		treatDateAsUTC: true
	});
	schema = schema || "XSK_SAMPLES_PRODUCTS";

	/** Create a new UUID */
	function createUUID() {
		return $.util.createUuid();
	}

	this.handlePostRequest = function(oData) {
		try {
			connection.setAutoCommit(0);
			var OrderId = createUUID();
			var oRs = connection.executeUpdate('INSERT INTO "' + schema + '"."' + PRODUCTS_TABLE +
				'" ("Id", "CustomerName", "CustomerSurname", "Status", "CreatedAt", "CreatedBy", "Description", "Address", "Phone", "Email") values (?,?,?,?, CURRENT_UTCTIMESTAMP, SESSION_CONTEXT(\'APPLICATIONUSER\'), ?, ?, ?, ?)',
				OrderId, oData.CustomerName, oData.CustomerSurname, oData.Status, oData.Description, oData.Address, oData.Phone, oData.Email);
			connection.commit();

			return oRs;
		} catch (e) {
			connection.rollback();
		}
	};

	this.handleGetRequest = function() {
		try {

			var res = connection.executeQuery('select * from "' + schema + '"."' + PRODUCTS_TABLE + '"');
			connection.commit();
			return res;
		} catch (e) {
			connection.rollback();
		}
	};

}

function deleteOrder(paramObject) {
	try {
		var oConnection = paramObject.connection;
		var sQuery = 'delete from "XSK_SAMPLES_PRODUCTS"."products.db::Products.Orders" where "Id" = (select "Id" from "' + paramObject.beforeTableName +
			'")';

		var pstmt = oConnection.prepareStatement(sQuery);
		pstmt.executeUpdate();
		pstmt.close();
	} catch (e) {
		oConnection.rollback();
	}

}

function handleOdataPostRequest(paramObject) {
	try {
		var oConnection = paramObject.connection;
		var OrderId = $.util.createUuid();
		var sQuery = 'select * from "' + paramObject.afterTableName + '"';
		var pstmt = oConnection.prepareStatement(sQuery);
		var oEntry = pstmt.executeQuery();

		oConnection.executeUpdate('INSERT INTO "' + schema + '"."' + PRODUCTS_TABLE +
			'" ("Id", "CustomerName", "CustomerSurname", "Status", "CreatedAt", "CreatedBy", "Description", "Address", "Phone", "Email") values (?,?,?,?, CURRENT_UTCTIMESTAMP, SESSION_CONTEXT(\'APPLICATIONUSER\'), ?, ?, ?, ?)',
			OrderId, oEntry.CustomerName, oEntry.CustomerSurname, oEntry.Status, oEntry.Description, oEntry.Address, oEntry.Phone, oEntry.Email);

		oConnection.commit();
		oConnection.close();

	} catch (e) {
		oConnection.rollback();
	}
}

function beforeCreateOrder(paramObject) {

	var oConnection = paramObject.connection;
	var sTableName = paramObject.afterTableName;
	var sOrderId = $.util.createUuid();
	var pstmt = oConnection.prepareStatement("update \"" + sTableName + "\" set \"Id\" = '" + sOrderId +
		"', \"CreatedAt\" =  CURRENT_UTCTIMESTAMP, \"CreatedBy\" = SESSION_CONTEXT(\'APPLICATIONUSER\') ");
	pstmt.executeUpdate();
	pstmt.close();
}

function beforeCreateItem(paramObject) {

	var oConnection = paramObject.connection;
	var sTableName = paramObject.afterTableName;
	var sItemId = $.util.createUuid();
	var pstmt = oConnection.prepareStatement("update \"" + sTableName + "\" set \"ItemId\" = '" + sItemId + "'");
	pstmt.executeUpdate();
	pstmt.close();
}


function updateOrder(paramObject) {

	var oConnection = paramObject.connection;
	var sTableName = paramObject.afterTableName;
	var sOrderId = $.util.createUuid();
	var pstmt = oConnection.prepareStatement("update \"" + sTableName + "\" set \"Id\" = '" + sOrderId +
		"', \"CreatedAt\" =  CURRENT_UTCTIMESTAMP, \"CreatedBy\" = SESSION_CONTEXT(\'APPLICATIONUSER\') ");
	pstmt.executeUpdate();
	pstmt.close();
}

