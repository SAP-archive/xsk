var http = $.net.http;

function createUUID() {
	return $.util.createUuid();
}

function saveData(data){
     try {
        var customers = [];
        var products = [];
        var orderLines = [];
        var shippingAddresses = [];
        var orders = [];
        for(var order of data){
            var orderDetails = order.Order_Details;
            var customer = order.Customer;
            var newAddress = {
                "Id": createUUID(),
                "Country": customer.Country,
                "City": customer.City,
                "PostalCode": customer.PostalCode
            };
            shippingAddresses.push(newAddress);

            var names = customer.ContactName.split(" ");
            var newCustomer = {
              "Id": customer.CustomerID,
              "Username": "username_"+customer.CustomerID,
              "FirstName": names[0],
              "LastName": names[1],
              "Phone": customer.Phone,
              "Email": "email@example.com",
              "ShippingAddressId": newAddress.Id
            };
            customers.push(newCustomer);

            var newOrder = {
                "Id": order.OrderID,
                "Status": "New",
                "CreatedAt": order.OrderDate,
                "DeliveryDate": order.RequiredDate,
                "Description": order.ShipName,
                "CustomerId": newCustomer.Id,
            };
            orders.push(newOrder);

            for (var line of orderDetails){
                var product = line.Product;
                var newProduct = {
                    "Id": product.ProductID,
                    "Name": product.ProductName,
                    "Type": product.Category.CategoryName,
                    "Price": product.UnitPrice,
                    "Currency": "USD",
                    "Comment": product.QuantityPerUnit
                };
                products.push(newProduct);

                var newOrderLine = {
                    "Id": createUUID(),
                    "OrderId": newOrder.Id,
                    "ItemId": newProduct.Id,
                    "Quantity": line.Quantity
                };

                orderLines.push(newOrderLine);
            }
        }
        var conn = $.hdb.getConnection();
        for(var product of products){
            // ID taken from response - conflict on existing
            var checkQuery = 'SELECT COUNT(*) AS "Amount" FROM "XSK_SAMPLES_PRODUCTS"."products.db::Products.Item" WHERE "Id" = \'' + product.Id + '\'';
            var exists = parseInt(conn.executeQuery(checkQuery)[0].Amount, 0) > 0;
            if(!exists){
                $.trace.debug("Attempting to insert item with ID " + product.Id);
                conn.executeUpdate('INSERT INTO "XSK_SAMPLES_PRODUCTS"."products.db::Products.Item" VALUES(?,?,?,?,?,?)', product.Id, product.Name, product.Type, product.Price, product.Currency, product.Comment);
            }else{
                $.trace.debug("Item with ID " + product.Id + " already exists");
            }
        }
        for(var address of shippingAddresses){
            // ID generated - check for existing
            var checkQuery = 'SELECT COUNT(*) AS "Amount" FROM "XSK_SAMPLES_PRODUCTS"."products.db::Products.ShippingAddresses" WHERE "Country.Name" = \'' + address.Country + '\' AND "City" = \'' + address.City + '\' AND "PostalCode" = \'' + address.PostalCode + '\'';
            var exists = parseInt(conn.executeQuery(checkQuery)[0].Amount, 0) > 0;
            if(!exists){
                $.trace.debug("Attempting to insert shipping address with ID " + address.Id);
                conn.executeUpdate('INSERT INTO "XSK_SAMPLES_PRODUCTS"."products.db::Products.ShippingAddresses" VALUES(?,?,?,?,?)', address.Id, 'unknown', address.Country, address.City, address.PostalCode);
            }else{
                $.trace.info("ShippingAddress for Country " + address.Country + " City " + address.City + " and PostalCode " + address.PostalCode + " already created");
            }
        }
        for(var customer of customers){
            // ID taken from response - conflict on existing
            var checkQuery = 'SELECT COUNT(*) AS "Amount" FROM "XSK_SAMPLES_PRODUCTS"."products.db::Products.Customers" WHERE "Id" = \'' + customer.Id + '\'';
            var exists = parseInt(conn.executeQuery(checkQuery)[0].Amount, 0) > 0;
            if(!exists){
                $.trace.debug("Attempting to insert customer with ID " + customer.Id);
                conn.executeUpdate('INSERT INTO "XSK_SAMPLES_PRODUCTS"."products.db::Products.Customers" VALUES(?,?,?,?,?,?,?)', customer.Id, customer.Username, customer.FirstName, customer.LastName, customer.Phone, customer.Email, customer.ShippingAddressId);
            }else{
                $.trace.debug("Customer with ID " + customer.Id + " already exists");
            }
        }
        for(var line of orderLines){
            // ID generated - check for existing
            var checkQuery = 'SELECT COUNT(*) AS "Amount" FROM "XSK_SAMPLES_PRODUCTS"."products.db::Products.OrderLine" WHERE "OrderId" = \'' + line.OrderId + '\' AND "Item.Id" = \'' + line.ItemId + '\'';
            var exists = parseInt(conn.executeQuery(checkQuery)[0].Amount, 0) > 0;
            if(!exists){
                $.trace.debug("Attempting to insert order line with ID " + line.Id);
                conn.executeUpdate('INSERT INTO "XSK_SAMPLES_PRODUCTS"."products.db::Products.OrderLine" VALUES(?,?,?,?)', line.Id, line.OrderId, line.ItemId, line.Quantity);
            }else{
                $.trace.debug("OrderLine for Order " + line.OrderId + "and Item " + line.ItemId + " already exists");
            }
        }
        for(var order of orders){
            // ID taken from response - conflict on existing
            var checkQuery = 'SELECT COUNT(*) AS "Amount" FROM "XSK_SAMPLES_PRODUCTS"."products.db::Products.Orders" WHERE "Id" = \'' + order.Id + '\'';
            var exists = parseInt(conn.executeQuery(checkQuery)[0].Amount, 0) > 0;
            if(!exists){
                $.trace.debug("Attempting to insert order with ID " + order.Id);
                conn.executeUpdate('INSERT INTO "XSK_SAMPLES_PRODUCTS"."products.db::Products.Orders" VALUES(?,?,?,?,?,?)', order.Id, order.Status, order.CreatedAt, order.DeliveryDate, order.Description, order.CustomerId);
            }else{
                $.trace.debug("Order with ID " + order.Id + " already exists");
            }
        }
        conn.commit();
        $.trace.info("Successfully imported data from external source")
    } catch(e) {
        $.trace.error(e);
        conn.rollback();
    } finally{
        conn.close();
    }
}

function loadExternalData(param){;
    var destination = http.readDestination("products.xsjob", "products");
    var client = new http.Client();
    var request = new http.Request(http.GET, "/Orders");
    request.parameters.set("$expand", "Customer,Order_Details($expand=Product($expand=Category))");
    request.parameters.set("$skiptoken", param.skiptoken);
    client.request(request, destination);
    var response = client.getResponse();
    var resp = JSON.parse(response.body.asString());
    saveData(resp["value"]);
}
