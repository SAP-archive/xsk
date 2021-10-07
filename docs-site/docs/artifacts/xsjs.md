---
title: XS Javascript (XSJS)
---

XS Javascript (XSJS)
===

## Overview
---

SAP HANA XS Javascript (XSJS) is a programming language that can be used by application developers to create server-side business logic as `*.xsjs` files. You can import libraries in XSJS services as separate files with the `*.xsjslib` extension.

## Reference
---

!!! note "SAP Help Portal"

    For more information, see [Getting Started with XS JavaScript](https://help.sap.com/viewer/d89d4595fae647eabc14002c0340a999/2.0.04/en-US/3459a57b68f440448350aea1bec1d4f8.html).


## Sample
---

=== "Sample XSJS service (`*.xsjs`)"

    ```javascript
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
        var productsService = new ProductsService(connection, "DBADMIN");
        var response = productsService.handlePostRequest(request);
        connection.close();
        positiveResult(response);
    }

    function doGet() {
        var response;
        var productsService = new ProductsService(connection, "DBADMIN");
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
    ```

=== "Sample XSJS library (`*.xsjslib`)"

    ```javascript
    var hdbConnection = $.hdb.getConnection({
        treatDateAsUTC: true
    });

    var schema = "DBADMIN";
    var PRODUCTS_TABLE = "products.db::Products.Orders";
    var ITEMS_TABLE = "products.db::Products.Item";

    function Products(connection, schema) {
        connection = connection || $.hdb.getConnection({
            treatDateAsUTC: true
        });
        schema = schema || "DBADMIN";

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
            var sQuery = 'delete from "DBADMIN"."products.db::Products.Orders" where "Id" = (select "Id" from "' + paramObject.beforeTableName + '")';

            var pstmt = oConnection.prepareStatement(sQuery);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (e) {
            oConnection.rollback();
        }
    }
    ```
