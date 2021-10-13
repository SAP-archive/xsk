// Import .xsjslib to our file using $.import api ("package","library")
// In our case package equals to "sap.myapp.lib"

let mathlib = $.import("demo.lib","math");

let square = mathlib.square(6);
let multiply = mathlib.multiply(4,9);
let division = mathlib.division(9,3);

let result = `Square is: ${square}`;
result += `\nMultiply is: ${multiply}`;
result += `\nDivision is: ${division}`;

$.response.setBody(result);