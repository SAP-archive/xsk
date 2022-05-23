import { assertEquals } from '../utils/utils.mjs'
import { getParams } from '../utils/stateTableParamsProvider.mjs'
import { fetchAllEntriesInTable } from '../utils/utils.mjs'
import { XSJSLibExportsGenerator } from '/exports/XSJSLibExportsGenerator.mjs'
import { repository } from '@dirigible-v4/platform'
import { digest } from '@dirigible-v4/utils'


function testMultiFileFolderExportGeneration() {
  const stateTableParams = getParams();
  const baseInput = "function asd(){ return 'asd'; }\nvar bcd = 3;\n function gfh(g){ return g; }";
  const baseExpectedContent = baseInput + "\n\n" + "exports.asd = asd;\n" + "exports.gfh = gfh;\n" + "exports.bcd = bcd;\n";

  const childInput = "var bcd = 3;";
  const childExpectedContent = childInput + "\n\n" + "exports.bcd = bcd;\n";

  // create a new resources with some xsjslib content
  const baseLocation = "/base/base.xsjslib";
  const childLocation = "/base/child/child.xsjslib";
  const baseCollection = repository.createCollection("base");
  const childCollection = baseCollection.createCollection("child");
  const baseResource = repository.createResource(baseLocation, baseInput, "text/html");
  const childResource = repository.createResource(childLocation, childInput, "text/html");

  // run generation and assert content is valid for all resources
  const generator = new XSJSLibExportsGenerator(stateTableParams);
  generator.run(baseCollection.getPath(), "ExistentFolder");
  assertEquals(baseInput, baseResource.getText(), "Unexpected xsjslib content after exports generation.");
  assertEquals(childInput, childResource.getText(), "Unexpected xsjslib content after exports generation.");
  const baseGeneratedExports = repository.getResource(baseResource.getPath() + ".generated_exports");
  const childGeneratedExports = repository.getResource(childResource.getPath() + ".generated_exports");
  assertEquals(baseExpectedContent, baseGeneratedExports.getText(), "Unexpected xsjslib content after exports generation.");
  assertEquals(childExpectedContent, childGeneratedExports.getText(), "Unexpected xsjslib content after exports generation.");


  // assert state table entries are with expected count
  const entries = fetchAllEntriesInTable(stateTableParams);
  const expected = [
    {"ID":0, "LOCATION": baseLocation, "HASH": digest.md5Hex(baseInput)},
    {"ID":1, "LOCATION": childLocation, "HASH": digest.md5Hex(childInput)}
  ];
  assertEquals(2, entries.length, "Unexpected count of entries in DB.");

  // assert state table entries content is as expected
  const baseActual = generator.processedArtefactsTable.findEntryByResourceLocation(baseLocation);
  const childActual = generator.processedArtefactsTable.findEntryByResourceLocation(childLocation);

  assertEquals(JSON.stringify(Object.keys(expected[0])), JSON.stringify(Object.keys(baseActual)), "Unexpected entry keys.");
  assertEquals(expected[0].LOCATION, baseActual.LOCATION, "Unexpected entry location.");
  assertEquals(expected[0].HASH, baseActual.HASH, "Unexpected entry hash.");

  assertEquals(JSON.stringify(Object.keys(expected[1])), JSON.stringify(Object.keys(childActual)), "Unexpected entry keys.");
  assertEquals(expected[1].LOCATION, childActual.LOCATION, "Unexpected entry location.");
  assertEquals(expected[1].HASH, childActual.HASH, "Unexpected entry hash.");
}

testMultiFileFolderExportGeneration();