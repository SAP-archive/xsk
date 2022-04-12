import { XSJSLibArtefactStateTable } from '/exports/XSJSLibArtefactStateTable.mjs'

let tableName = "XSJSLIB_EXPORT_TEST_TABLE";
let tableSchema = "PUBLIC";
let tableLocation = "local";
let tableDB = "DefaultDB";

function writeTableTest() {
  let table = new XSJSLibArtefactStateTable(tableName, tableSchema, tableLocation, tableDB);

//   let sql = "SELECT * FROM \"" + tableSchema + "\".\"" + tableName +"\" WHERE LOCATION = ?";
//   let result = query.execute(sql, [location], tableLocation, tableDB).shift();
//   throw new Error(JSON.stringify(result));
}

writeTableTest();