import { assertEquals } from './utils/utils.mjs'
import { XSJSLibArtefactStateTable } from '/exports/XSJSLibArtefactStateTable.mjs'
import { digest } from '@dirigible-v4/utils'
import { getParams } from './utils/stateTableParamsProvider.mjs'
import { fetchAllEntriesInTable } from './utils/utils.mjs'

function writeTableTest() {
  // create new state table
  let stateTableParams = getParams();
  let table = new XSJSLibArtefactStateTable(
    stateTableParams.name,
    stateTableParams.schema,
    stateTableParams.location,
    stateTableParams.db
  );

  // write in state table
  let content = "function asd(){}";
  table.createEntryForResource("asd/asd.xsjslib", content);

  // assert entry count is as expected
  let actual = fetchAllEntriesInTable(stateTableParams);
  assertEquals(1, actual.length, "Unexpected result length");

  // assert entry content is as expected
  actual = actual.shift(); // get first element;
  let expected = {"ID":0, "LOCATION":"asd/asd.xsjslib", "HASH": digest.md5Hex(content)};
  assertEquals(JSON.stringify(Object.keys(expected)), JSON.stringify(Object.keys(actual)), "Unexpected entry keys.");
  assertEquals(expected.LOCATION, actual.LOCATION, "Unexpected entry location.");
  assertEquals(expected.HASH, actual.HASH, "Unexpected entry hash.");
}

writeTableTest();