---
title: $.jobs
---

$.jobs
===

`$.jobs` represents an XS scheduled job.


## Overview


- Definition: [https://github.com/SAP/xsk/issues/388](https://github.com/SAP/xsk/issues/388)
- Module: [jobs/jobs.js](https://github.com/SAP/xsk/blob/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/jobs/jobs.js)
- Status: `alpha`


## Sample usage:


=== "definition.xsjob"

     Create a `definition.xsjob` file in a project named `xsjob_demo`:

     ```json
     {
         "description": "My Job configuration",
             "action": "xsjob_demo:handler.xsjslib::writeInDB",
             "schedules": [
             {
                 "description": "Execute every 5 seconds",
                 "xscron": "* * * * * * */5",
                 "parameter": {
                 }
             }
         ]
     }
     ```

=== "handler.xsjslib"

     Create a `handler.xsjslib` file in the `xsjob_demo` project:

     ```javascript
     function writeInDB() {
         let connection;
         try {
             connection = $.db.getConnection();

             let insertStatement = connection.prepareStatement("INSERT INTO XSJOB_DEMO (EXECUTED_AT) VALUES (CURRENT_TIMESTAMP)");
             insertStatement.executeUpdate();
             insertStatement.close();
         } catch(e) {
             connection.rollback();
             console.log("Transaction was rolled back: " + e.message);
         } finally {
             connection.close();
         }
     }
     ```

=== "trigger.xsjs"

     And now, to use the API, create a `.xsjs` file:

     ```javascript
     let job = new $.jobs.Job({
         uri: "xsjob_demo/definition.xsjob"
     });

     // execute the job for 60 seconds, starting from now
     job.configure({
         status: true,
         start_time: new Date(),
         end_time: new Date(Date.now() + 60000)
     });
     ```

## Functions


| Function    | Description                                                     | Returns |
|------------|-----------------------------------------------------------------|:--------:|
| **activate()** | Activates an XS Job that has already been configured.          |   _`-`_   |
| **configure(config)** | Configure an XS Job. The function provides additional means to programmatically configure an XS Job. Configuration must be done prior to activate/deactivate an XS Job.           |   _`-`_   |
| **deactivate()** | Deactivates an XS Job that has already been configured.          |   _`-`_   |
| **getConfiguration()** | Retrieve current configuration of an XS Job as object.           |    _`Object`_   |

