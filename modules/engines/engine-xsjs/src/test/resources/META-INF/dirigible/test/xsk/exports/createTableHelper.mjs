import { XSJSLibArtefactStateTable } from '/exports/XSJSLibArtefactStateTable.mjs'

let stateTableParams = {
  name: "PROCESSED_XSJSLIB_ARTEFACTS",
  schema: "PUBLIC",
  location: "local",
  db: "SystemDB"
}

let table = new XSJSLibArtefactStateTable(
  stateTableParams.name,
  stateTableParams.schema,
  stateTableParams.location,
  stateTableParams.db
);

table.createEntryForResource("bbb/abc.xsjslib", "abc");