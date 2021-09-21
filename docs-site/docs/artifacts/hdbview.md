---
title: hdbview
---

hdbview
===

## Overview

The information on how to develop the design-time data-persistence model for an XSK application using the HDBView syntax

### Reference

* SAP Help

> [https://help.sap.com/viewer/cc2b23beaa3344aebffa2f6e717df049/2.0.03/en-US/78dcb8e2f4f14b53b0d333bcd24f8721.html](https://help.sap.com/viewer/cc2b23beaa3344aebffa2f6e717df049/2.0.03/en-US/78dcb8e2f4f14b53b0d333bcd24f8721.html)


* Sample Hana version1 syntax

```
schema="MYSCHEMA";
query="SELECT T1.\"Column2\" FROM \"MYSCHEMA\".\"acme.com.test.views::MY_VIEW1\" AS T1 LEFT JOIN \"MYSCHEMA\".\"acme.com.test.views::MY_VIEW2\" AS T2 ON T1.\"Column1\" = T2.\"Column1\"";
depends_on=["acme.com.test.views::MY_VIEW1", "acme.com.test.views::MY_VIEW2"];
```
* Parser Hana version1 behavior ([issue](https://github.com/SAP/xsk/issues/108))
1. Currently the Parser take into account if a given property is mandatory.
1. If the property order is misplaced the parser will still parse the values.
1. If more than one value of one property is provided then only the last one is taken.       

* Sample HANA version2 syntax

```
VIEW "hdb_view.db::ItemsByOrderHANAv2"
AS SELECT "Id" FROM "hdb_view::Item";
```

* Which syntax is supported from the parser

| hdbview syntax (Hana v1) | hdbview syntax (XSKViewParser) | Comments    |
|-------------------------|--------------------------------|--------------|
| schema                  | schema                         |              |
| query                   | query                          |              |
| public                  | public                         | default=true |
| depends_on              | depends_on                     |              |
| depends_on_table        | depends_on_table               |              |
| depends_on_view         | depends_on_view                |              |
