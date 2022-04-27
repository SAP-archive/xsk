import { assertEquals } from './utils/utils.mjs'
import { XSJSLibArtefactStateTable } from '/exports/XSJSLibArtefactStateTable.mjs'
import { digest } from '@dirigible-v4/utils'
import { getParams } from './utils/stateTableParamsProvider.mjs'
import { fetchAllEntriesInTable } from './utils/utils.mjs'

function updateTableTest() {
  // create new state table
  let stateTableParams = getParams();
  let table = new XSJSLibArtefactStateTable(
    stateTableParams.name,
    stateTableParams.schema,
    stateTableParams.location,
    stateTableParams.db
  );

  // create new entry in the state table
  let content = "function asd(){}";
  table.createEntryForResource("asd/asd.xjslib", content);

  // assert entry count is as expected
  let result = fetchAllEntriesInTable(stateTableParams);
  assertEquals(1, result.length, "Unexpected result length");

  // update the entry
  let oldEntry = result.shift();
  content = "function asd2(){}";
  table.updateEntryForResource(oldEntry, "asd2/asd2.xsjslib", content);

  // assert the entry count is as expected
  let actual = fetchAllEntriesInTable(stateTableParams);
  assertEquals(1, actual.length, "Unexpected result length");
  actual = actual.shift();

  // assert the updated entry content is as expected
  let expected = {"ID":0, "LOCATION":"asd2/asd2.xsjslib", "HASH": digest.md5Hex(content)};
  assertEquals(JSON.stringify(Object.keys(expected)), JSON.stringify(Object.keys(actual)), "Unexpected entry keys.");
  assertEquals(expected.LOCATION, actual.LOCATION, "Unexpected entry location.");
  assertEquals(expected.HASH, actual.HASH, "Unexpected entry hash.");
}

updateTableTest();