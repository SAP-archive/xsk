const Assert = Java.type("org.junit.Assert");
const Integer = Java.type("java.lang.Integer");
const System = Java.type("java.lang.System");

try {
  Integer.parseInt("some string to simulate a NumberFormatException");
} catch (e) {
  Assert.assertNull("Unexpected exception fileName", e.fileName);
}