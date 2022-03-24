const Assert = Java.type("org.junit.Assert");

try {
  throw new Error("some error");
} catch (e) {
  Assert.assertEquals("Unexpected exception fileName", "/throwRegularRootLevelError.js", e.fileName);
}