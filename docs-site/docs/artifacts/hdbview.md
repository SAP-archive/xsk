---
title: hdbview
---

hdbview
===

## Overview

Here you will learn how to develop the design-time data-persistence model for an XSK application using the HDBView syntax.

### Reference

* Additional information on SAP Help Portal

For more information, see [Create an SQL View](https://help.sap.com/viewer/cc2b23beaa3344aebffa2f6e717df049/2.0.03/en-US/78dcb8e2f4f14b53b0d333bcd24f8721.html).

* Sample HANA version1 syntax

```
schema="MYSCHEMA";
query="SELECT T1.\"Column2\" FROM \"MYSCHEMA\".\"acme.com.test.views::MY_VIEW1\" AS T1 LEFT JOIN \"MYSCHEMA\".\"acme.com.test.views::MY_VIEW2\" AS T2 ON T1.\"Column1\" = T2.\"Column1\"";
depends_on=["acme.com.test.views::MY_VIEW1", "acme.com.test.views::MY_VIEW2"];
```

!!! Note
    There's currently an [issue](https://github.com/SAP/xsk/issues/108) in the behavior of the HANA version1 of the parser:
    - Currently, the Parser takes into account if a given property is mandatory.
    - If the property order is misplaced, the parser will still parse the values.
    - If more than one value of one property is provided, then only the last one is taken.       

* Sample HANA version2 syntax

```
VIEW "hdb_view.db::ItemsByOrderHANAv2"
AS SELECT "Id" FROM "hdb_view::Item";
```

* Which syntax does the parser support?

| hdbview syntax (Hana v1) | hdbview syntax (XSKViewParser) | Comments    |
|-------------------------|--------------------------------|--------------|
| schema                  | schema                         |              |
| query                   | query                          |              |
| public                  | public                         | default=true |
| depends_on              | depends_on                     |              |
| depends_on_table        | depends_on_table               |              |
| depends_on_view         | depends_on_view                |              |
