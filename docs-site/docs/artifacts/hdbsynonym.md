---
title: HDBSynonym
---

HDBSynonym
===

## Overview

The information on this page will help you learn how to develop the design-time data-persistence model for an XSK application using the HDBSynonym syntax.

### Reference

* Additional information on SAP Help Portal

For more information, see [Create a Synonym](https://help.sap.com/viewer/cc2b23beaa3344aebffa2f6e717df049/2.0.03/en-US/5278b5979128444cb6fffe0f8c2bf1e3.html).


* Sample HANA v1 and v2 syntax:
```
{ "acme.com.app1::MySynonym1" : { 
       "target" : { 
            "schema": "DEFAULT_SCHEMA", 
            "object": "MY_ERP_TABLE_1"
                  },
       "schema": "SCHEMA_2"
       }
}
```

* Which syntax does the parser support?

| hdbsynonym syntax (HANA 1)                | hdbview syntax (XSKSynonymParser) | Comments |
|-------------------------------------------|-----------------------------------|----------|
| "<full.path.to.package>::<MySynonymName>" | location                          |          |
| "target" : {"schema": "..."}              | targetSchema                      |          |
| "target" : {"object": "..."}              | targetObject                      |          |
| "schema"                                  | synonymSchema                     |          |

* Example:
  
```
{
  "acme.test.db::Test.dvMySynonym1": {
    "target": {
      "schema": "SYS",
      "object": "DUMMY"
    },
    "schema": "TONI"
  }
}
```    
``` 
{
  "acme.test.db::Test.dvMySynonym1": {
    "schema": "TONI",
    "target": {
        "object": "DUMMY",
        "schema": "SYS"
    }
  }
}
```  
