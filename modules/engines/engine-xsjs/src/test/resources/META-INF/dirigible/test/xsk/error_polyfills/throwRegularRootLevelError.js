const Assert = Java.type("org.junit.Assert");

try {
  throw new Error("some error");
} catch (e) {
//  Assert.assertEquals("Unexpected exception fileName", "/throwRegularRootLevelError.js", e.fileName);
  Assert.assertEquals("Unexpected exception fileName", "Unknown", e.fileName); // replace with line above once Dirigible 6.2.13 is released
}