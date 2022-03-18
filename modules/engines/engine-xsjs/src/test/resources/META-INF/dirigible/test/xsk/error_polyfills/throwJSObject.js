const Assert = Java.type("org.junit.Assert");
const System = Java.type("java.lang.System");

try {
  throw "some string";
} catch (e) {
//  Assert.assertEquals("Unexpected exception fileName", "throwJSObject.js", e.fileName);
System.out.println("!!! VM: message: " + e);
  Assert.assertNull("Unexpected exception fileName", e.fileName);
}