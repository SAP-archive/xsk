---
title: HDBProcedure
---

HDBProcedure
===

## Overview

The information on this page will help you learn how to develop the design-time data-persistence model for an XSK application using the HDBProcedure syntax.

### Reference

* Additional information on SAP Help Portal

For more information, see [Procedures (.hdbprocedure)](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/2.0.02/en-US/93de88bf2c8242179647e40f958c24e5.html).

* Sample artifact code:

```
PROCEDURE "MYSCHEMA"."hdb_view::OrderProcedure" ()
   LANGUAGE SQLSCRIPT
   SQL SECURITY INVOKER
   --DEFAULT SCHEMA <default_schema_name>
   READS SQL DATA AS
BEGIN
   /*************************************
       Write your procedure logic
   *************************************/

      SELECT * FROM "hdb_view::Item";
END
```
