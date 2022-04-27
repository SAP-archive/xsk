import { assertEquals } from './utils/utils.mjs'
import { XSJSLibExportsGenerator } from '/exports/XSJSLibExportsGenerator.mjs'
import { repository } from '@dirigible-v4/platform'
import { digest } from '@dirigible-v4/utils'
import { getParams } from './utils/stateTableParamsProvider.mjs'
import { fetchAllEntriesInTable } from './utils/utils.mjs'

function testSingleFileExportGeneration() {
  let stateTableParams = getParams();
  let input = "function asd(){ return 'asd'; }\nvar bcd = 3;\n function gfh(g){ return g; }";
  let expectedContent = input + "\n\n" + "exports.asd = asd;\n" + "exports.gfh = gfh;\n" + "exports.bcd = bcd;\n";

  // create a new resource with some xsjslib content
  let collection = repository.createCollection("asd");
  let resource = repository.createResource("asd/asd.xsjslib", input, "text/html");

  // run generation and assert content is valid
  let generator = new XSJSLibExportsGenerator(collection, stateTableParams);
  assertEquals(expectedContent, resource.getText(), "Unexpected xsjslib content after exports generation.");

  // assert state table entries are okay
  let actual = fetchAllEntriesInTable(stateTableParams);
  let expected = {"ID":0, "LOCATION":"/asd/asd.xsjslib", "HASH": digest.md5Hex(expectedContent)};

  assertEquals(1, actual.length, "Unexpected count of entries in DB.");
  actual = actual.shift();

  assertEquals(JSON.stringify(Object.keys(expected)), JSON.stringify(Object.keys(actual)), "Unexpected entry keys.");
  assertEquals(expected.LOCATION, actual.LOCATION, "Unexpected entry location.");
  assertEquals(expected.HASH, actual.HASH, "Unexpected entry hash.");
}

testSingleFileExportGeneration();