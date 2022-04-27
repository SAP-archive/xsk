import { assertEquals } from './utils/utils.mjs'
import { XSJSLibArtefactStateTable } from '/exports/XSJSLibArtefactStateTable.mjs'
import { digest } from '@dirigible-v4/utils'
import { getParams } from './utils/stateTableParamsProvider.mjs'

function checkContentChangeTest() {
  let stateTableParams = getParams();
  let table = new XSJSLibArtefactStateTable(
    stateTableParams.name,
    stateTableParams.schema,
    stateTableParams.location,
    stateTableParams.db
  );

  let content = "function asd(){}"
  let changedContent = "function asd22{}";
  let foundEntry = {"ID":0, "LOCATION":"asd/asd.xsjslib", "HASH": digest.md5Hex(content)};

  assertEquals(false, table.checkForContentChange(foundEntry, content), "Unexpected content change check result.");
  assertEquals(true, table.checkForContentChange(foundEntry, changedContent), "Unexpected content change check result.");
}

checkContentChangeTest();