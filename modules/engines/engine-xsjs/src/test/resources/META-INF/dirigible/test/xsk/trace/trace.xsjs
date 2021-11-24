var assertTrue = require('utils/assert').assertTrue;
let trace = $.trace;

let debugBool = trace.isDebugEnabled();
let errorBool = trace.isErrorEnabled();
let fatalBool = trace.isFatalEnabled();
let infoBool = trace.isInfoEnabled();
let warnBool = trace.isWarningEnabled();

if (debugBool) {
    trace.debug("Debug message!");
}
if (errorBool) {
    trace.error("Error message!");
}
if (fatalBool) {
    trace.error("Fatal message!");
}
if (infoBool) {
    trace.info("Info message!");
}
if (warnBool) {
    trace.warning("Warning message!");
}

assertTrue(debugBool && errorBool && fatalBool && infoBool && warnBool);