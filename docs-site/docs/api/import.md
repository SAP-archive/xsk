---
title: $.import
---

$.import
===

`$.import` namespace provides means for importing server-side JavaScript library artifacts. These are design-time artifacts located in the repository. JavaScript library design-time artifacts have the suffix `*.xsjslib`.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/18](https://github.com/SAP/xsk/issues/18)
- Module: [import/import.js](https://github.com/SAP/xsk/blob/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/import/import.js)
- Status: `alpha`

## Sample Usage



!!! Note
    If you want to use sample usage code, file structure of project in your workspace browser should look like this:

    ![img](/assets/images/api/import-filestructure.png)

=== "import.xsjs"

     ```javascript
     // Import .xsjslib to our file using $.import api ("package","library")
     // In our case package equals to "sap.myapp.lib"

     let mathlib = $.import("sap.myapp.lib","math");

     let square = mathlib.square(6);
     let multiply = mathlib.multiply(4,9);
     let division = mathlib.division(9,3);

     let result = `Square is: ${square}`;
     result += `\nMultiply is: ${multiply}`;
     result += `\nDivision is: ${division}`;

     $.response.setBody(result);   
     ```

=== "math.xsjslib"
     

     ```javascript
     
     function square(x) {
         return x * x;
     }

     function multiply(x, y) {
         return x * y;
     }

     function division(x, y) {
         return x / y;
     }
     ```

## Parameters

| Name  | type | Description |
| ------------- | ------------- | ------------- |
| **package**  | string  | _`The name of the package in which the library object is located`_ |
| **library**  | string  | _`The name of the library object in the repository (without the suffix .xsjslib)`_  |




 
