---
title: XSODATA
---

XSODATA
===

## Overview
---

The information on this page will help you learn how to develop the design-time data-persistence model for an XSK application using the XSODATA syntax.

## Reference
---

* Additional information on SAP Help Portal

    * [Tutorial: Use the SAP HANA OData Interface](https://help.sap.com/viewer/b3d0daf2a98e49ada00bf31b7ca7a42e/2.0.03/en-US/502dbde2cbeb4f27b0e9b9887b0097b7.html)

    * [OData Service-Definition Examples (XS Advanced)](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/2.0.03/en-US/02e91608eb174dcea6d544aad6ea2e12.html)

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

Inside an `*.xsodata` file we can specify which properties can be exposed using the 'with' and 'without' section:

```
service namespace "np"{
   "sample.odata::table1" as "Table1" without ("COLUMN1");
   "sample.odata::table2" as "Table2" with ("COLUMN1", "COLUMN2");
}
```

### XSK XSODATA Annotations Support
---

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

  
If you want to check the whole list of annotations, visit the official [SAP OData Annotations v2.X](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/2.0.03/en-US/b7fb60b91ee54a75bb03e54af1316229.html) documentation.

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
