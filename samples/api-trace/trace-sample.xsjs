let trace = $.trace;

// Check trace methods availability in the "Preview" tab
$.response.setBody("Debug logging enabled: " + trace.isDebugEnabled()
    + ", Error logging enabled: " + trace.isErrorEnabled()
    + ", Fatal logging enabled: " + trace.isFatalEnabled()
    + ", Info logging enabled: " + trace.isInfoEnabled()
    + ", Warning logging enabled: " + trace.isWarningEnabled());

// If the trace method is enabled, it will print the message in the "Console" tab
trace.debug("Debug message!");
trace.error("Error message!");
trace.error("Fatal message!");
trace.info("Info message!");
trace.warning("Warning message!");