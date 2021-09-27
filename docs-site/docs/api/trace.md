---
title: $.trace
---

$.trace
===

`$.trace` represents the trace namespace with its methods.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/17](https://github.com/SAP/xsk/issues/17)
- Module: [trace/trace.js](https://github.com/SAP/xsk/tree/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/trace/trace.js)
- Status: `alpha`

## Sample Usage

```javascript
let trace = $.trace;

// Check trace methods availability in the "Preview" tab
$.response.setBody("Debug logging enabled: " + trace.isDebugEnabled()
    + ", Error logging enabled: " + trace.isErrorEnabled()
    + ", Fatal logging enabled: " + trace.isFatalEnabled()
    + ", Info logging enabled: " + trace.isInfoEnabled()
    + ", Warning logging enabled: " +trace.isWarningEnabled());

// If the trace method is enabled, it will print the message in the "Console" tab
trace.debug("Debug message!");
trace.error("Error message!");
trace.error("Fatal message!");
trace.info("Info message!");
trace.warning("Warning message!");
```

## Constants


| Name                        | Description                   | Type      | Default |
|-----------------------------|-------------------------------|-----------|---------|
| **XSK_LOG_DEBUG_ENABLED**   | Enables debug level logging.  |_`boolean`_|_`true`_ |
| **XSK_LOG_ERROR_ENABLED**   | Enables error level logging.  |_`boolean`_|_`true`_ |
| **XSK_LOG_FATAL_ENABLED**   | Enables fatal level logging.  |_`boolean`_|_`true`_ |
| **XSK_LOG_INFO_ENABLED**    | Enables log level logging.    |_`boolean`_|_`true`_ |
| **XSK_LOG_WARNING_ENABLED** | Enables warning level logging.|_`boolean`_|_`true`_ |

## Functions


| Function                | Description                                                                                                                      | Returns     |
|------------------------|-----------------------------------------------------------------------------------------------------------------------------------|-------------|
| **debug(message)**     | Writes the given message with the trace-level debug to the application trace file.                                                |  _`-`_      |
| **error(message)**     | Writes the given message with the trace-level error to the application trace file.                                                |  _`-`_      |
| **fatal(message)**     | Writes the given message with the trace-level fatal to the application trace file.                                                |  _`-`_      |
| **info(message)**      | Writes the given message with the trace-level info to the application trace file.                                                 |  _`-`_      |
| **warning(message)**   | Writes the given message with the trace-level warning to the application trace file.                                              |  _`-`_      |
| **isDebugEnabled()**   | Returns true or false to show whether or not the tracer writes an entry in the application trace file for the trace-level debug.  | _`boolean`_ |
| **isErrorEnabled()**   | Returns true or false to show whether or not the tracer writes an entry in the application trace file for the trace-level error.  | _`boolean`_ |
| **isFatalEnabled()**   | Returns true or false to show whether or not the tracer writes an entry in the application trace file for the trace-level fatal.  | _`boolean`_ |
| **isInfoEnabled()**    | Returns true or false to show whether or not the tracer writes an entry in the application trace file for the trace-level info.   | _`boolean`_ |
| **isWarningEnabled()** | Returns true or false to show whether or not the tracer writes an entry in the application trace file for the trace-level warning.| _`boolean`_ |
