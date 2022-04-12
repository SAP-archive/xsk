var assert = require('utils/assert');
export function assertEquals(expected, actual, message) {
  assert.assertEquals(expected, actual, message
   + "\nExpected: " + expected
   + "\nActual: " + actual);
}