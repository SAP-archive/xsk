var mathlib = $.import("test.xsk.import.sap.test.lib","math");

var square = mathlib.square(6);
var multiply = mathlib.multiply(4,9);
var division = mathlib.division(9,3);


square === 36 && multiply === 36 && division === 3
