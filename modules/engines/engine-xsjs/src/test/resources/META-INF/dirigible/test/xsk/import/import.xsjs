// Importing xsjs lib and adding the module to the $ object.
$.import("test.xsk.import.sap.test.lib", "basic");

// Assigning the module to a variable from the $ object.
var mathbasic = $.test.xsk.import.sap.test.lib.basic;

// Importing xsjs lib, adding the module to the $ object and also assigning to a variable.
var mathlib = $.import("test.xsk.import.sap.test.lib", "math");

// Using methods from the first imported module.
var sum = mathbasic.sum(5, 5);
var sub = mathbasic.sub(10, 5);

// Using methods from the second imported module.
var square = mathlib.square(6);
var multiply = mathlib.multiply(4, 9);
var division = mathlib.division(9, 3);

// Verifying the results.
square === 36 && multiply === 36 && division === 3 && sum === 10 && sub === 5

