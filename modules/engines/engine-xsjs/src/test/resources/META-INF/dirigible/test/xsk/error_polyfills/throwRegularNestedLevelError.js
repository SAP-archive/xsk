const Assert = Java.type("org.junit.Assert");

try {
  function someNestedFunction() {
    throw new Error("some error");
  }
  someNestedFunction();
} catch (e) {
  Assert.assertEquals("Unexpected exception fileName", "/throwRegularNestedLevelError.js", e.fileName);
}