---
title: hdbdd
---

hdbdd
===

## Overview

The information on how to develop the design-time data-persistence model for an XSK application using the HDBDD syntax a.k.a HDBCDS

### Reference

* SAP Help

> [https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/2.0.03/en-US/36257a5f611540f9b2f8e13110ddf97a.html](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/2.0.03/en-US/36257a5f611540f9b2f8e13110ddf97a.html)

* Example:

```
namespace products.db;

@Schema: 'DBADMIN'

context Products {

 entity  Orders{
        key Id               : String(32);
        CustomerName         : String(500);
        CustomerSurname      : String(500);
        Status               : String(100);
        CreatedAt            : UTCTimestamp;
   		CreatedBy            : String(5000);
        Description          : String(100);
        Address              : String(5000);
        Phone                : String(200);
        Email                : String(300);
        Country              : association to Products.Country { Id };
		items                : Association[*] to Item on items.OrderId = Id;
    };
     
    entity Item {
        key ItemId          : String(32);
    	OrderId             : String(32);
	    Name                : String(500);
        Type                : String(100);
        Price               : String(100);
        Currency            : String(100);
        Quantity            : String(100);
        Comment             : String(1000);
    };
    
     entity Country {
        key Id              : String(32);
        Name                : String(32);
    };
};
```


#### Parser specific details
The following words are **_key symbols_** and they are 
- case insensitive: _**key, namespace, entity, context, using, assiciation**_
- case sensitive: _**as, type, hana, to, on**_



