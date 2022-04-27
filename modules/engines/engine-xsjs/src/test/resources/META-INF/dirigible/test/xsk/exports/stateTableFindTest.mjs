import { assertEquals } from './utils/utils.mjs'
import { XSJSLibArtefactStateTable } from '/exports/XSJSLibArtefactStateTable.mjs'
import { digest } from '@dirigible-v4/utils'
import { getParams } from './utils/stateTableParamsProvider.mjs'

function findTableTest() {
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
  table.createEntryForResource("asd/asd.xsjslib", content);

  // find the entry
  let actual = table.findEntryByResourceLocation("asd/asd.xsjslib");
  let expected = {"ID":0, "LOCATION":"asd/asd.xsjslib", "HASH": digest.md5Hex(content)};

  // assert entry content is as expected
  assertEquals(JSON.stringify(Object.keys(expected)), JSON.stringify(Object.keys(actual)), "Unexpected entry keys.");
  assertEquals(expected.LOCATION, actual.LOCATION, "Unexpected entry location.");
  assertEquals(expected.HASH, actual.HASH, "Unexpected entry hash.");
}

findTableTest();