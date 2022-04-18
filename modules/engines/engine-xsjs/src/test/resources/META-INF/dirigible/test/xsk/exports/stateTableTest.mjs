import { assertEquals } from './prettyAssert.mjs'
import { XSJSLibArtefactStateTable } from '/exports/XSJSLibArtefactStateTable.mjs'
import { query } from '@dirigible-v4/db'
import { digest } from '@dirigible-v4/utils'

let stateTableParams = {
  name: "XSJSLIB_EXPORT_TEST_TABLE",
  schema: "PUBLIC",
  location: "local",
  db: "SystemDB"
}

function _fetchAllEntriesInTable() {
  let sql = "SELECT * FROM " + stateTableParams.name;
  return query.execute(sql, null, stateTableParams.location, stateTableParams.db);
}

function writeTableTest() {
  // create new state table
  let table = new XSJSLibArtefactStateTable(
    stateTableParams.name,
    stateTableParams.schema,
    stateTableParams.location,
    stateTableParams.db
  );
  table.clear();

  // write in state table
  let content = "function asd(){}";
  table.createEntryForResource("asd/asd.xsjslib", content);

  // assert entry count is as expected
  let actual = _fetchAllEntriesInTable();
  assertEquals(1, actual.length, "Unexpected result length");

  // assert entry content is as expected
  actual = actual.shift(); // get first element;
  let expected = {"ID":0, "LOCATION":"asd/asd.xsjslib", "HASH": digest.md5Hex(content)};
  assertEquals(JSON.stringify(Object.keys(expected)), JSON.stringify(Object.keys(actual)), "Unexpected entry keys.");
  assertEquals(expected.LOCATION, actual.LOCATION, "Unexpected entry location.");
  assertEquals(expected.HASH, actual.HASH, "Unexpected entry hash.");

  table.clear();
}

function updateTableTest() {
  // create new state table
  let table = new XSJSLibArtefactStateTable(
    stateTableParams.name,
    stateTableParams.schema,
    stateTableParams.location,
    stateTableParams.db
  );
  table.clear();

  // create new entry in the state table
  let content = "function asd(){}";
  table.createEntryForResource("asd/asd.xjslib", content);

  // assert entry count is as expected
  let result = _fetchAllEntriesInTable();
  assertEquals(1, result.length, "Unexpected result length");

  // update the entry
  let oldEntry = result.shift();
  content = "function asd2(){}";
  table.updateEntryForResource(oldEntry, "asd2/asd2.xsjslib", content);

  // assert the entry count is as expected
  let actual = _fetchAllEntriesInTable();
  assertEquals(1, actual.length, "Unexpected result length");
  actual = actual.shift();

  // assert the updated entry content is as expected
  let expected = {"ID":0, "LOCATION":"asd2/asd2.xsjslib", "HASH": digest.md5Hex(content)};
  assertEquals(JSON.stringify(Object.keys(expected)), JSON.stringify(Object.keys(actual)), "Unexpected entry keys.");
  assertEquals(expected.LOCATION, actual.LOCATION, "Unexpected entry location.");
  assertEquals(expected.HASH, actual.HASH, "Unexpected entry hash.");

  table.clear();
}

function findTableTest() {
  // create new state table
  let table = new XSJSLibArtefactStateTable(
    stateTableParams.name,
    stateTableParams.schema,
    stateTableParams.location,
    stateTableParams.db
  );
  table.clear();

  // create new entry in the state table
  let content = "function asd(){}";
  table.createEntryForResource("asd/asd.xsjslib", content);

  // find the entry
  let actual = table.findEntryByResourceLocation("asd/asd.xsjslib");
  let expected = {"ID":0, "LOCATION":"asd/asd.xsjslib", "HASH": digest.md5Hex(content)};

  // assert entry content is as expected
  assertEquals(JSON.stringify(Object.keys(expected)), JSON.stringify(Object.keys(actual)), "Unexpected entry keys.");
  assertEquals(expected.LOCATION, actual.LOCATION, "Unexpected entry location.");
  assertEquals(expected.HASH, actual.HASH, "Unexpected entry hash.");

  table.clear();
}

function checkContentChangeTest() {
  let table = new XSJSLibArtefactStateTable(
    stateTableParams.name,
    stateTableParams.schema,
    stateTableParams.location,
    stateTableParams.db
  );
  table.clear();

  let content = "function asd(){}"
  let changedContent = "function asd22{}";
  let foundEntry = {"ID":0, "LOCATION":"asd/asd.xsjslib", "HASH": digest.md5Hex(content)};

  assertEquals(false, table.checkForContentChange(foundEntry, content), "Unexpected content change check result.");
  assertEquals(true, table.checkForContentChange(foundEntry, changedContent), "Unexpected content change check result.");

  table.clear();
}

writeTableTest();
updateTableTest();
findTableTest();
checkContentChangeTest();