---
title: HDBTable
---

HDBTable
===

## Overview

The information on this page will help you learn how to develop the design-time data-persistence model for an XSK application using the HDBTable syntax.

### Reference

* Additional information on SAP Help Portal

  For more information, see [Create a Table](https://help.sap.com/viewer/cc2b23beaa3344aebffa2f6e717df049/2.0.03/en-US/89cbf999e6004be3a5324b8f9ef0c53f.html).

* Sample HANA XS Classic syntax:

```
table.schemaName = "MYSCHEMA";
table.tableType = COLUMNSTORE;
table.columns = [
	{name = "Col1"; sqlType = VARCHAR; nullable = false; length = 20; comment = "dummy comment";},
	{name = "Col2"; sqlType = INTEGER; nullable = false;},
	{name = "Col3"; sqlType = NVARCHAR; nullable = true; length = 20; defaultValue = "Defaultvalue";},
	{name = "Col4"; sqlType = DECIMAL; nullable = false; precision = 2; scale = 3;}];
table.indexes =  [
	{name = "MYINDEX1"; unique = true; indexColumns = ["Col2"];},
	{name = "MYINDEX2"; unique = true; indexColumns = ["Col1", "Col4"];}];
table.primaryKey.pkcolumns = ["Col1", "Col2"];
```

* Sample HANA XS Advanced syntax:
```
COLUMN TABLE "MYSCHEMA::MYTABLE" ( 
   "ID" INTEGER DEFAULT 555, "NAME" NVARCHAR(256),
   "ACTIVE" TINYINT,
   "COUNTRY" NVARCHAR(256),
   PRIMARY KEY ("ID") )
```

* Parser specific details

!!! tip "Caution"
	The parser does not allow duplicate properties.

The order of the table's, column's and index's properties is not taken into account when the table definition is parsed. 

An exception will be thrown if a property is defined more than once in the *.hdbtable file. An additional exception will be thrown if a mandatory field is missing.
