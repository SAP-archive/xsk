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

|Annotation Element            | Annotation Supportability |
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

| HANA TableType           | Supported type for OData |
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
