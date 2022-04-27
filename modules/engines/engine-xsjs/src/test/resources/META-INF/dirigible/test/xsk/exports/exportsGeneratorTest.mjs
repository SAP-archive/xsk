import { assertEquals } from './prettyAssert.mjs'
import { XSJSLibExportsGenerator } from '/exports/XSJSLibExportsGenerator.mjs'
import { XSJSLibArtefactStateTable } from '/exports/XSJSLibArtefactStateTable.mjs'
import { repository } from '@dirigible-v4/platform'
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

function _clearTable() {
  let table = new XSJSLibArtefactStateTable(
    stateTableParams.name,
    stateTableParams.schema,
    stateTableParams.location,
    stateTableParams.db
  );

  table.clear();
}

function testSingleFileExportGeneration() {
  let input = "function asd(){ return 'asd'; }\nvar bcd = 3;\n function gfh(g){ return g; }";
  let expectedContent = input + "\n\n" + "exports.asd = asd;\n" + "exports.gfh = gfh;\n" + "exports.bcd = bcd;\n";

  // create a new resource with some xsjslib content
  let collection = repository.createCollection("asd");
  let resource = repository.createResource("asd/asd.xsjslib", input, "text/html");

  // run generation and assert content is valid
  let generator = new XSJSLibExportsGenerator(collection, stateTableParams);
  assertEquals(expectedContent, resource.getText(), "Unexpected xsjslib content after exports generation.");

  // assert state table entries are okay
  let actual = _fetchAllEntriesInTable();
  let expected = {"ID":0, "LOCATION":"/asd/asd.xsjslib", "HASH": digest.md5Hex(expectedContent)};

  assertEquals(1, actual.length, "Unexpected count of entries in DB.");
  actual = actual.shift();

  assertEquals(JSON.stringify(Object.keys(expected)), JSON.stringify(Object.keys(actual)), "Unexpected entry keys.");
  assertEquals(expected.LOCATION, actual.LOCATION, "Unexpected entry location.");
  assertEquals(expected.HASH, actual.HASH, "Unexpected entry hash.");
}

function testSingleFileExportUpdate() {
  // get an existing resource
  let collection = repository.getCollection("asd");
  let resource = repository.getResource("asd/asd.xsjslib");

  let input = "function asd(){ return 'asd'; }";
  let expectedContent = input + "\n\n" + "exports.asd = asd;\n";

  // modify existing resource's content
  resource.setText(input);

  // run generation to update the content and state table and assert content is valid
  let generator = new XSJSLibExportsGenerator(collection, stateTableParams);
  assertEquals(expectedContent, resource.getText(), "Unexpected xsjslib content after exports generation.");

  // assert state table entries are okay
  let actual = _fetchAllEntriesInTable();
  let expected = {"ID":0, "LOCATION":"/asd/asd.xsjslib", "HASH": digest.md5Hex(expectedContent)};

  assertEquals(1, actual.length, "Unexpected count of entries in DB.");
  actual = actual.shift();

  assertEquals(JSON.stringify(Object.keys(expected)), JSON.stringify(Object.keys(actual)), "Unexpected entry keys.");
  assertEquals(expected.LOCATION, actual.LOCATION, "Unexpected entry location.");
  assertEquals(expected.HASH, actual.HASH, "Unexpected entry hash.");

  resource.delete();
  collection.delete();
}

function testMultiFileFolderExportGeneration() {
  let baseInput = "function asd(){ return 'asd'; }\nvar bcd = 3;\n function gfh(g){ return g; }";
  let baseExpectedContent = baseInput + "\n\n" + "exports.asd = asd;\n" + "exports.gfh = gfh;\n" + "exports.bcd = bcd;\n";

  let childInput = "var bcd = 3;";
  let childExpectedContent = childInput + "\n\n" + "exports.bcd = bcd;\n";

  // create a new resources with some xsjslib content
  let baseLocation = "/base/base.xsjslib";
  let childLocation = "/base/child/child.xsjslib";
  let baseCollection = repository.createCollection("base");
  let childCollection = baseCollection.createCollection("child");
  let baseResource = repository.createResource(baseLocation, baseInput, "text/html");
  let childResource = repository.createResource(childLocation, childInput, "text/html");

  // run generation and assert content is valid for all resources
  let generator = new XSJSLibExportsGenerator(baseCollection, stateTableParams);
  assertEquals(baseExpectedContent, baseResource.getText(), "Unexpected xsjslib content after exports generation.");
  assertEquals(childExpectedContent, childResource.getText(), "Unexpected xsjslib content after exports generation.");

  // assert state table entries are with expected count
  let entries = _fetchAllEntriesInTable();
  let expected = [
    {"ID":0, "LOCATION": baseLocation, "HASH": digest.md5Hex(baseExpectedContent)},
    {"ID":1, "LOCATION": childLocation, "HASH": digest.md5Hex(childExpectedContent)}
  ];
  assertEquals(2, entries.length, "Unexpected count of entries in DB.");

  // assert state table entries content is as expected
  let baseActual = generator.processedArtefactsTable.findEntryByResourceLocation(baseLocation);
  let childActual = generator.processedArtefactsTable.findEntryByResourceLocation(childLocation);

  assertEquals(JSON.stringify(Object.keys(expected[0])), JSON.stringify(Object.keys(baseActual)), "Unexpected entry keys.");
  assertEquals(expected[0].LOCATION, baseActual.LOCATION, "Unexpected entry location.");
  assertEquals(expected[0].HASH, baseActual.HASH, "Unexpected entry hash.");

  assertEquals(JSON.stringify(Object.keys(expected[1])), JSON.stringify(Object.keys(childActual)), "Unexpected entry keys.");
  assertEquals(expected[1].LOCATION, childActual.LOCATION, "Unexpected entry location.");
  assertEquals(expected[1].HASH, childActual.HASH, "Unexpected entry hash.");

  childResource.delete();
  baseResource.delete();
  childCollection.delete();
  baseCollection.delete();
}

_clearTable();
testSingleFileExportGeneration();
_clearTable();
testSingleFileExportUpdate();
_clearTable();
testMultiFileFolderExportGeneration();
_clearTable();