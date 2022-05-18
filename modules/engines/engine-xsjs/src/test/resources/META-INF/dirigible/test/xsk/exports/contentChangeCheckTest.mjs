import { assertEquals } from './utils/utils.mjs'
import { XSJSLibExportsGenerator } from '/exports/XSJSLibExportsGenerator.mjs'
import { digest } from '@dirigible-v4/utils'
import { getParams } from './utils/stateTableParamsProvider.mjs'
import { repository } from '@dirigible-v4/platform'

function checkContentChangeTest() {
  const stateTableParams = getParams();
  const collection = repository.createCollection("asd");
  const generator = new XSJSLibExportsGenerator(stateTableParams);

  const content = "function asd(){}"
  const changedContent = "function asd22{}";
  const foundEntry = {"ID":0, "LOCATION":"asd/asd.xsjslib", "HASH": digest.md5Hex(content)};

  assertEquals(false, generator._checkForContentChange(foundEntry, content), "Unexpected content change check result.");
  assertEquals(true, generator._checkForContentChange(foundEntry, changedContent), "Unexpected content change check result.");
}

checkContentChangeTest();