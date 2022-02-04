var hdbConnection = $.hdb.getConnection({
	treatDateAsUTC: true
});

var schema = "XSK_SAMPLES_PRODUCTS";
var ORDERS_TABLE = "products.db::Products.Orders";
var ORDER_LINES_TABLE = "products.db::Products.OrderLine";
var CUSTOMERS_TABLE = "products.db::Products.Customers";
var ITEMS_TABLE = "products.db::Products.Item";

function getCustomerId(oConn){
    var customerIdResult = oConn.executeQuery('SELECT * FROM "XSK_SAMPLES_PRODUCTS"."products.db::Products.Customers" WHERE "Username" = SESSION_USER');
    if(customerIdResult[0] !== undefined){
        return customerIdResult[0].Id;
    }
    return "";
}

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
		    var customerId = getCustomerId(connection);
		    $.trace.error(customerId);
			connection.setAutoCommit(0);
			var orderId = createUUID();
			var result = {Id: orderId, Status: oData.Status, Description: oData.Description, Items:[]};
			var ordersRs = connection.executeUpdate('INSERT INTO "' + schema + '"."' + ORDERS_TABLE +
				'" ("Id", "Status", "CreatedAt", "DeliveryDate", "Description", "Customer.Id") VALUES (?,?,current_utctimestamp,ADD_DAYS(current_utctimestamp,7),?,?)',
				orderId, oData.Status, oData.Description, customerId);
			for(var item of oData.Items){
			    var lineId = createUUID();
    			var linesRs = connection.executeUpdate('INSERT INTO "' + schema + '"."' + ORDER_LINES_TABLE +
    				'" ("Id", "OrderId", "Item.Id", "Quantity") VALUES (?,?,?,?)', lineId, orderId, item.Id, item.Quantity);    
    			result.Items.push({Id: lineId, ItemId: item.Id, Quantity: item.Quantity});
			}
			connection.commit();
			return result;
		} catch (e) {
			connection.rollback();
			$.trace.error(e);
		}
	};

	this.handleGetRequest = function() {
		try {
			var resOrders = connection.executeQuery('SELECT * FROM "' + schema + '"."' + ORDERS_TABLE + '"');
			let result = {orders: []};
			let iterator = resOrders.getIterator();
			while(iterator.next()){
			    var row = iterator.value();
			    var order = {
			        Id: row.Id,
			        Status: row.Status,
			        Description: row.Description,
			        CreatedAt: row.CreatedAt,
			        DeliveryDate: row.DeliveryDate,
			        Items:[]
			    };
			    // OrderLines
			    var resLines = connection.executeQuery('SELECT ol."Item.Id", ol."Quantity", i."Name", i."Type" FROM "' + schema + '"."' + ORDER_LINES_TABLE +
			        '" ol JOIN "' + schema + '"."' + ITEMS_TABLE + '" i ON ol."Item.Id" = i."Id" WHERE ol."OrderId"=\''+order.Id+'\'');
		        var linesIterator = resLines.getIterator();
		        while(linesIterator.next()){
		            var lineRow = linesIterator.value();
		            var line = {
		                Item: {
		                    Id: lineRow["Item.Id"],
		                    Name: lineRow.Name,
		                    Type: lineRow.Type
		                },
		                Quantity: lineRow.Quantity
		            };
		            order.Items.push(line);
		        }
		        
		        // Customer
			    var resCustomers = connection.executeQuery('SELECT "Username", "FirstName", "LastName" FROM "' + schema + '"."' + CUSTOMERS_TABLE +
			        '" WHERE "Id"=\''+row["Customer.Id"]+'\'');
		        var customersIterator = resCustomers.getIterator();
		        customersIterator.next();
		        var customerRow = customersIterator.value();
		        order.Buyer = {
		            Username: customerRow.Username,
		            FirstName: customerRow.FirstName,
		            LastName: customerRow.LastName
		        };
		        result.orders.push(order);
			}
			connection.commit();
			return result;
		} catch (e) {
			connection.rollback();
		}
	};

}

// Deletes the order and the associated order lines
function deleteOrder(paramObject) {
	try {
	    var oConnection = paramObject.connection;
		var deleteOrderLineQuery = 'delete from "XSK_SAMPLES_PRODUCTS"."products.db::Products.OrderLine" where "OrderId" = (select "Id" from "' + paramObject.beforeTableName +
		'")';
		var deleteOrderQuery = 'delete from "XSK_SAMPLES_PRODUCTS"."products.db::Products.Orders" where "Id" = (select "Id" from "' + paramObject.beforeTableName +'")';

		var pstmtOrderLine = oConnection.prepareStatement(deleteOrderLineQuery);
		pstmtOrderLine.executeUpdate();
		pstmtOrderLine.close();
		
		var pstmtOrder = oConnection.prepareStatement(deleteOrderQuery);
		pstmtOrder.executeUpdate();
		pstmtOrder.close();
	} catch (e) {
		oConnection.rollback();
	}

}

function beforeCreateOrder(paramObject) {
	var oConnection = paramObject.connection;
	var sTableName = paramObject.afterTableName;
	var sOrderId = $.util.createUuid();
    let customerId = getCustomerId(oConnection);
	var pstmt = oConnection.prepareStatement("update \"" + sTableName + "\" set \"Id\" = '" + sOrderId +
		"', \"CreatedAt\" =  CURRENT_UTCTIMESTAMP, \"Customer.Id\" = '"+ customerId + "'");
	pstmt.executeUpdate();
	pstmt.close();
}

function beforeCreateItem(paramObject) {
	var oConnection = paramObject.connection;
	var sTableName = paramObject.afterTableName;
	var sItemId = $.util.createUuid();
	var pstmt = oConnection.prepareStatement("update \"" + sTableName + "\" set \"Id\" = '" + sItemId + "'");
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


