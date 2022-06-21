import { assertEquals } from '../utils/utils.mjs'
import { getParams } from '../utils/stateTableParamsProvider.mjs'
import { fetchAllEntriesInTable } from '../utils/utils.mjs'
import { XSJSLibStateTable } from '/exports/XSJSLibStateTable.mjs'
import { digest } from '@dirigible-v4/utils'

function updateTableTest() {
  // create new state table
  const stateTableParams = getParams();
  const table = new XSJSLibStateTable(
    stateTableParams.name,
    stateTableParams.schema
  );

  // create new entry in the state table
  const content = "function asd(){}";
  table.createEntryForResource("asd/asd.xjslib", content);

  // assert entry count is as expected
  const result = fetchAllEntriesInTable(stateTableParams);
  assertEquals(1, result.length, "Unexpected result length");

  // update the entry
  const oldEntry = result.shift();
  const newContent = "function asd2(){}";
  table.updateEntryForResource(oldEntry, "asd2/asd2.xsjslib", newContent);

  // assert the entry count is as expected
  const entries = fetchAllEntriesInTable(stateTableParams);
  assertEquals(1, entries.length, "Unexpected result length");
  const actual = entries.shift();

  // assert the updated entry content is as expected
  const expected = {"ID":0, "LOCATION":"asd2/asd2.xsjslib", "HASH": digest.md5Hex(newContent)};
  assertEquals(JSON.stringify(Object.keys(expected)), JSON.stringify(Object.keys(actual)), "Unexpected entry keys.");
  assertEquals(expected.LOCATION, actual.LOCATION, "Unexpected entry location.");
  assertEquals(expected.HASH, actual.HASH, "Unexpected entry hash.");
}

updateTableTest();