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
* `xsjob` - regularly prints the number of orders
* `webapp` - `UI5` user interface


## Folder content

- `xs-classic` - contains files with syntax native to SAP HANA XS Classic developed in SAP HANA 2
- `xsk` - contains files after migration of the `xs-classic` project via project XSK 