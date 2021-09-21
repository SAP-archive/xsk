---
title: xsodata
---

xsodata
===

## Overview

The information on how to develop the design-time data-persistence model for an XSK application using the XSODATA syntax

### Reference

SAP Help

[Tutorial: Use the SAP HANA OData Interface](https://help.sap.com/viewer/b3d0daf2a98e49ada00bf31b7ca7a42e/2.0.03/en-US/502dbde2cbeb4f27b0e9b9887b0097b7.html)

[OData Service-Definition Examples (XS Advanced)](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/2.0.03/en-US/02e91608eb174dcea6d544aad6ea2e12.html)

###  Supported syntax from the parser
| Supportable 	| xsodata syntax 	| (XSKXsodataParser) 	| XTransformer 	| MTransfrmer 	| HTransformer 	| Comments 	|
|-	|-	|-	|-	|-	|-	|-	|
| HANA v1/v2 	| odata service definition 	| SUPPORTED 	| SUPPORTED 	| not relevant 	| not relevant 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/51d401d204a54909abebcca6d0a8e058.html) 	|
| HANA v1/v2 	| odata namespace definition 	| SUPPORTED 	| SUPPORTED 	| SUPPORTED 	| not relevant 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/f9f5f227e9f54cea9d9548c97860b7c0.html) 	|
| HANA v1/v2 	| odata object exposure 	| SUPPORTED 	| SUPPORTED 	| SUPPORTED 	| not relevant 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/d08f928ac047443e8c2b0a7070ac1d0b.html) 	|
| HANA v1/v2 	| odata property projection 	| SUPPORTED 	| SUPPORTED 	| SUPPORTED 	| not relevant 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/471609f56e354fe1b8f8e65b183202fa.html) 	|
| HANA v1/v2 	| odata key specification 	| SUPPORTED 	| SUPPORTED using  sap:filterable 	| NOT SUPPORTED 	| not relevant 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/e86a01a7699a46528624d44678d37ea5.html) 	|
| HANA v1/v2 	| odata associations 	| SUPPORTED 	| SUPPORTED 	| SUPPORTED 	| not relevant 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/595f0a124602449695bf15711032186c.html) 	|
| HANA v1/v2 	| odata aggregations 	| SUPPORTED 	| SUPPORTED  using  sap:semantics="aggregate" 	| NOT SUPPORTED 	| not relevant 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/64560b807f004d8189b94385e5d02f1e.html) 	|
| HANA v1/v2 	| odata parameters entitysets 	| SUPPORTED 	| No odata  representation exist 	| not relevant 	| not relevant 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/2ec97095dcbd420794670912e3bc9cd6.html) 	|
| HANA v1/v2 	| odata Etag support 	| NOT SUPPORTED 	| NOT SUPPORTED 	| NOT SUPPORTED 	| not relevant 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/17b479e1e928465baa07a5688fd5e355.html) 	|
| HANA v1/v2 	| odata nullable properties 	| SUPPORTED 	| No odata representation exist 	| NOT SUPPORTED 	| not relevant 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/79b338c0296c4518b83aa6b19133bba5.html) 	|
| HANA v1/v2 	| odata configurable cash settings 	| SUPPORTED 	| No odata representation exist 	| NOT SUPPORTED 	| not relevant 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/a5d3bea8a6ae43779ffaaae925554d0b.html) 	|
| HANA v2 	| OData Hints for SQL Select Statements 	| SUPPORTED 	| No odata representation exist 	| NOT SUPPORTED 	| not relevant 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/2.0.05/en-US/34c4b422010c45a795362d8ac6a32638.html) 	|
| HANA v2 	| OData Entity Limits 	| SUPPORTED 	| NOT SUPPORTED 	| NOT SUPPORTED 	| not relevant 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/2.0.05/en-US/b6efb153ac8a4b96bb48baa14aa3e358.html) 	|
| HANA v1/v2 	| modifications 	| SUPPORTED 	| SUPPORTED  [forbidden: using  sap:creatable, sap:updatable,  sap:deletable], [events - no odata  representation exist] 	| not relevant 	| SUPPORTED 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/68c5a3bca9294e4098454dccbc73f7a9.html) 	|
| HANA v1/v2 	| validation scrips with XS JavaScript 	| SUPPORTED 	| no odata  representation exist 	| not relevant 	| SUPPORTED 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/74dcb4480e80455eb0cfaacc4c739068.html) 	|
| HANA v1/v2 	| validation exit with SQL script 	| SUPPORTED 	| no odata  representation exist 	| not relevant 	| SUPPORTED 	| [documentation](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/1.0.12/en-US/3621f082a10241759f0ec01f56319ae3.html) 	|

* Example:

```
service namespace "products.odata"  {
 
   "products.db::Products.Orders" as "Orders"
   		navigates("Items4Orders" as "Items")

    create events(before "products.xsjs:ProductsLib.xsjslib::beforeCreateOrder")
    delete using "products.xsjs:ProductsLib.xsjslib::deleteOrder";
 
   
  "products.db::Products.Item" as "Items"
    create events(before "products.xsjs:ProductsLib.xsjslib::beforeCreateItem") 
    update forbidden;

    association "Items4Orders" principal "Orders"("Id")
       multiplicity "1" dependent "Items"("OrderId") multiplicity "*";
      
 }
 
 annotations {
   enable OData4SAP;
}
```

* More details

Inside xsodata file we can specify which properties can be exposed using the 'with' and 'without' section:

```
service namespace "np"{
   "sample.odata::table1" as "Table1" without ("COLUMN1");
   "sample.odata::table2" as "Table2" with ("COLUMN1", "COLUMN2");
}
```

# XSK XSODATA Annotations Support
[SAP ODATA Annotations for XSOData XS2](https://github.wdf.sap.corp/xs2/xsodata/blob/cb734da393e83e9e893c10e720af53bd5e43c481/documentation/annotations.md)

[SAP Annotations for OData Version 2.0](https://wiki.scn.sap.com/wiki/display/EmTech/SAP+Annotations+for+OData+Version+2.0)

|Annotation Element            | Dirigible Annotation Supportability |
| ------------------------------ | ---- |
| Element edm:Schema             | :x:|
| Element edm:EntityContainer    | :x:|
| Element edm:EntitySet          | :heavy_check_mark:|
| Element edm:EntityType         | :heavy_check_mark:|
| Element edm:Property           | :heavy_check_mark:|
| Element edm:NavigationProperty | :heavy_check_mark:|
| Element edm:FunctionImport     | :x:|
| Element sap:value-constraint   | :x:|
| Element edm:Parameter          | :x:|
| Element edm:AssociationSet     | :heavy_check_mark:|

  
For all the list of annotations check the official documentation [SAP OData Annotations v2.X](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/2.0.03/en-US/b7fb60b91ee54a75bb03e54af1316229.html)


|XSODATA Supportability|Annotation Element|Parameter Name|Parameter Value|The following values are supported in the following cases|
|:----|:----|:----|:----|:----|
| |edm:EntityType|sap:semantics|Undefined|Defaul Value is Undefined|
|:heavy_check_mark:| | |aggregate|Where aggregation is defined for the entity set of the entity type using "aggregates" expression in .xsodata file|
|:x:| | |aggregate|Where the entity type represents a calculation view, which has a measure attribute|
|:x:| | |parameters| if the entity type represents input parameters for a calculation view|
|:x:|edm:EntitySet|sap:addressable|TRUE|Default Value is true|
|:x:| | |FALSE|“false” if the entity set represents either a calculation view or input parameters for a calculation view.|
|:x:| |sap:creatable|TRUE|Default Value is true|
|:heavy_check_mark:| | |FALSE|The "create forbidden" setting is defined for the entity set in the OData service definition (.xsodata) file|
|:x:| | |FALSE|The entity set represents a database view, for example, a table or a calculation view.|
|:x:| | |FALSE|The entity set represents input parameters for a calculation view.|
|:x:| | |FALSE|“aggregation” is defined for the entity set, for example, using the “aggregates always” expression in the OData service definition (.xsodata) file.|
|:x:| |sap:updatable|TRUE|Default Value is true|
|:heavy_check_mark:| | |FALSE|The "update forbidden" setting is defined for the entity set in the OData service definition (.xsodata) file|
|:x:| | |FALSE|The entity set represents a database view, for example, a table or a calculation view.|
|:x:| | |FALSE|The entity set represents input parameters for a calculation view.|
|:x:| | |FALSE|“aggregation” is defined for the entity set, for example, using the “aggregates always” expression in the OData service definition (.xsodata) file.|
|:x:| | |FALSE|A generated key is defined for the entity set.|
|:x:| |sap:deletable|TRUE|Default Value is true|
|:heavy_check_mark:| | |FALSE|The "delete forbidden" setting is defined for the entity set in the OData service definition (.xsodata) file|
|:x:| | |FALSE|The entity set represents a database view, for example, a table or a calculation view.|
|:x:| | |FALSE|The entity set represents input parameters for a calculation view.|
|:x:| | |FALSE|“aggregation” is defined for the entity set, for example, using the “aggregates always” expression in the OData service definition (.xsodata) file.|
|:x:| | |FALSE|A generated key is defined for the entity set.|
|:x:|Entity-Type edm:Property|sap:semantics|Undefined|Undefined; there is no defined default value|
|:x:| | |currency-code|check official docmentation for more detail|
|:x:| | |unit-of-measure|check official docmentation for more detail|
|:x:| | |dtstart|check official docmentation for more detail|
|:x:| | |dtend|check official docmentation for more detail|
|:x:| |sap:parameter|Undefined|Undefined; there is no defined default value|
|:x:| | |mandatory|check official docmentation for more detail|
|:x:| | |optional|check official docmentation for more detail|
|:x:| |sap:label|Undefined|Undefined; there is no defined default value|
|:x:| | |Additional Supported Values|check official docmentation for more detail|
|:x:| |sap:filterable|TRUE|Default Value is true|
|:x:| | |FALSE|The property represents a generated key|
|:x:| | |FALSE|The property represents a measure attribute of a calculation view|
|:x:| | |FALSE|The property is used in the aggregation, defined as the “aggregates always” expression in the XS OData service-definition (.xsodata) file.|
|:x:| |sap:display-format|Undefined|Undefined; there is no defined default value|
|:x:| | |Date|The “Date” value can be used if the SQL DATE type is used for the property on the database side.|
|:x:| |sap:aggregation-role|Undefined|Undefined; there is no defined default value|
|:x:| | |measure|The “measure” value can be used if the property represents a measure attribute of a calculation view or is used in the aggregation that is defined in the “aggregates always” expression in the XS advanced OData service-definition (.xsodata) file.|
|:x:| |sap:unit|Undefined|Undefined; there is no defined default value|
|:x:| | |Additional Supported Values|check official docmentation for more detail|
|:x:| |sap:filter-restriction|Undefined|Undefined; there is no defined default value|
|:x:| | |single-value|check official docmentation for more detail|
|:x:| | |multi-value|check official docmentation for more detail|
|:x:| | |interval|check official docmentation for more detail|
|:x:|edm:NavigationProperty|sap:creatable|TRUE|Default Value is true|
|:x:| | |FALSE|The annotation value is always “false” because neither “deep insert” (POST request payload containing data for both parent and related entity) nor POST request for the .../EntitySet(key)/navPropertyName URL is supported in XS OData for XS advanced.|
|:x:| |sap:filterable|TRUE|Default Value is true|
|:x:| | |FALSE|The annotation value is always “false” because navigation properties cannot be used in a $filter system query option in XS OData for XS advanced.|
|:x:|edm:AssociationSet|sap:creatable|TRUE|Default Value is true|
|:x:| | |FALSE|“false” if the association set connects entity sets for calculation view results and calculation view input parameters.|
|:x:| |sap:updatable|TRUE|Default Value is true|
|:x:| | |FALSE|“false” if the association set connects entity sets for calculation view results and calculation view input parameters.|
|:x:| |sap:deletable|TRUE|Default Value is true|
|:x:| | |FALSE|“false” if the association set connects entity sets for calculation view results and calculation view input parameters.|

- Supportable [OData Service-Definition Features](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/2.0.03/en-US/fda42888439142dc9984d3560bc68206.html)
     
| FEATURES              | SUPPORTED IN XSK | REMARKS |
| ---------------------  | ---------------- |-----|
| Aggregation            |   :x:     |
| Association              |  :heavy_check_mark:     |DONE|
| Key Specification     |  :heavy_check_mark:    |DONE|
| Parameter Entity Sets |  :x:                 ||
| Projection                |  :heavy_check_mark:   |DONE|

| HANA TableType           | Dirigible supported type for odata|
| -------------------- | - |
| CALC VIEW            | :heavy_check_mark: |
| GLOBAL TEMPORARARY   | :x: |
| SHARED TEMPORARARY   | :x: |
| NO LOGGING TEMPORARY | :x: |
| SYNONYM              | :x: |
| SYSTEM TABLE         | :x: |
| TABLE                |  :heavy_check_mark: |
| USER DEFINED         | :x: |
| COLUMN VIEW         | :x: |
| VIEW                 |  :heavy_check_mark: |
| JOIN VIEW            | :x: |
| OLAP VIEW            | :x: |
| HIERARCHY VIEW       | :x: |
