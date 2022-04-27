import { assertEquals } from './utils/utils.mjs'
import { XSJSLibExportsGenerator } from '/exports/XSJSLibExportsGenerator.mjs'
import { repository } from '@dirigible-v4/platform'
import { digest } from '@dirigible-v4/utils'
import { getParams } from './utils/stateTableParamsProvider.mjs'
import { fetchAllEntriesInTable } from './utils/utils.mjs'

function testSingleFileExportGeneration() {
  const stateTableParams = getParams();
  const input = "function asd(){ return 'asd'; }\nvar bcd = 3;\n function gfh(g){ return g; }";
  const expectedContent = input + "\n\n" + "exports.asd = asd;\n" + "exports.gfh = gfh;\n" + "exports.bcd = bcd;\n";

  // create a new resource with some xsjslib content
  const collection = repository.createCollection("asd");
  const resource = repository.createResource("asd/asd.xsjslib", input, "text/html");

  // run generation and assert content is valid
  const generator = new XSJSLibExportsGenerator(stateTableParams);
  generator.run(collection);
  assertEquals(expectedContent, resource.getText(), "Unexpected xsjslib content after exports generation.");

  // assert state table entries are okay
  const entries = fetchAllEntriesInTable(stateTableParams);
  const expected = {"ID":0, "LOCATION":"/asd/asd.xsjslib", "HASH": digest.md5Hex(expectedContent)};

  assertEquals(1, entries.length, "Unexpected count of entries in DB.");
  const actual = entries.shift();

  assertEquals(JSON.stringify(Object.keys(expected)), JSON.stringify(Object.keys(actual)), "Unexpected entry keys.");
  assertEquals(expected.LOCATION, actual.LOCATION, "Unexpected entry location.");
  assertEquals(expected.HASH, actual.HASH, "Unexpected entry hash.");
}

testSingleFileExportGeneration();