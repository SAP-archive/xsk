import { XSJSLibArtefactStateTable } from '/exports/XSJSLibArtefactStateTable.mjs'

let stateTableParams = {
  name: "PROCESSED_XSJSLIB_ARTEFACTS",
  schema: "PUBLIC"
}

let table = new XSJSLibArtefactStateTable(
  stateTableParams.name,
  stateTableParams.schema
);

table.createEntryForResource("testFolder/abc.xsjslib", "abc");