import { assertEquals } from '../utils/utils.mjs'
import { getParams } from '../utils/stateTableParamsProvider.mjs'
import { XSJSLibStateTable } from '/exports/XSJSLibStateTable.mjs'
import { digest } from '@dirigible-v4/utils'

function findTableTest() {
  // create new state table
  const stateTableParams = getParams();
  const table = new XSJSLibStateTable(
    stateTableParams.name,
    stateTableParams.schema
  );

  // create new entry in the state table
  const content = "function asd(){}";
  table.createEntryForResource("asd/asd.xsjslib", content);

  // find the entry
  const actual = table.findEntryByResourceLocation("asd/asd.xsjslib");
  const expected = {"ID":0, "LOCATION":"asd/asd.xsjslib", "HASH": digest.md5Hex(content)};

  // assert entry content is as expected
  assertEquals(JSON.stringify(Object.keys(expected)), JSON.stringify(Object.keys(actual)), "Unexpected entry keys.");
  assertEquals(expected.LOCATION, actual.LOCATION, "Unexpected entry location.");
  assertEquals(expected.HASH, actual.HASH, "Unexpected entry hash.");
}

findTableTest();