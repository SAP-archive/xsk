import { assertEquals } from '../utils/utils.mjs'
import { getParams } from '../utils/stateTableParamsProvider.mjs'
import { fetchAllEntriesInTable } from '../utils/utils.mjs'
import { XSJSLibStateTable } from '/exports/XSJSLibStateTable.mjs'
import { digest } from '@dirigible-v4/utils'

function writeTableTest() {
  // create new state table
  const stateTableParams = getParams();
  const table = new XSJSLibStateTable(
    stateTableParams.name,
    stateTableParams.schema
  );

  // write in state table
  const content = "function asd(){}";
  table.createEntryForResource("asd/asd.xsjslib", content);

  // assert entry count is as expected
  const entries = fetchAllEntriesInTable(stateTableParams);
  assertEquals(1, entries.length, "Unexpected result length");

  // assert entry content is as expected
  const actual = entries.shift(); // get first element;
  const expected = {"ID":0, "LOCATION":"asd/asd.xsjslib", "HASH": digest.md5Hex(content)};
  assertEquals(JSON.stringify(Object.keys(expected)), JSON.stringify(Object.keys(actual)), "Unexpected entry keys.");
  assertEquals(expected.LOCATION, actual.LOCATION, "Unexpected entry location.");
  assertEquals(expected.HASH, actual.HASH, "Unexpected entry hash.");
}

writeTableTest();