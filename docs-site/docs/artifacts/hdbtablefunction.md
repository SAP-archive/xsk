---
title: HDBTableFunction
---

HDBTableFunction
===

## Overview
---

The information on this page will help you learn how to develop the design-time data-persistence model for an XSK application using the HDBTableFunction syntax.

## Reference
---

* Additional information on SAP Help Portal

For more information, see [Tutorial: Create a Table User-Defined Function](https://help.sap.com/viewer/b3d0daf2a98e49ada00bf31b7ca7a42e/2.0.04/en-US/18a94543fe3f41f1b29e7c439f255b95.html)

* Sample artifact code:

```
FUNCTION "MYSCHEMA"."hdb_view::OrderTableFunction" ()
	RETURNS TABLE (
		"Id" NVARCHAR(32),
		"CustomerName" NVARCHAR(500)
	)
	LANGUAGE SQLSCRIPT
	SQL SECURITY INVOKER AS
BEGIN

RETURN  SELECT "Id", "CustomerName" FROM "hdb_view::Item";

END;
```
