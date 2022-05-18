const Assert = Java.type("org.junit.Assert");

try {
  const err = new Error("some error");
  err.stack = null;
  throw err;
} catch (e) {
  Assert.assertEquals("Unexpected exception fileName", "Unknown", e.fileName);
}