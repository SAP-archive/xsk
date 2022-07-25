## products

This is a complex sample containing the following aspects:
* `hdbdd` - defines the model with associations
* `hdbsequence` - auto increment primary key for `Item` entity
* `hdbti` - predefined content
* `hdbtablefunction` - purchased items by current customer of specified item type
* `analyticprivilege` - protects a `calculationview` by checking `Username` to be the current user 
* `calculationview`
  * one general purpose for everyone (total items ordered of item type)
  * one based on the `hdbtablefunction`
  * one protected by an `analyticprivilege` to return the total ordered items
* `xsodata` - exposes some of the entities, has event handlers and navigation
* `xsjs` services 
  * one for creating and retrieving complete orders with items
  * one querying a `calculationview` 
* `protected` resources secured with application privileges
  * `xsprivileges` - defines two application privileges
  * `xsaccess` - restricts access to Administrators
  * `xsjs` - simple service to check access
  * `xsodata` - exposing one calculation view
* `xsjob` - regularly loads data from a specified destination
* `webapp` - `UI5` user interface
* `PRODUCTS_DU.tgz` - DU exported from HANA 2 containing the original XS Classic code


## Deployment guide
1. Follow the deployment guide for [XSK on Kyma](https://www.xsk.io/setup/kyma/)
   1. Use the provided xs-security.json which includes an additional role collection
2. Create a SAP BTP Destination service instance and bind it to XSK with prefix `destination_`

## Preparation
1. Grant the analytic privilege (samples/products/views/items_ordered_total_ap.analyticprivilege) to the HANA Cloud user for XSK
2. Modify/Create record in samples/products/data/Products_Customers.csv where `Username` is your username used to login to XSK
3. Assign yourself the `XSKProductsAdmin` role from SAP BTP Cockpit
4. Create a destination in SAP BTP Cockpit by importing the file `products.properties`



