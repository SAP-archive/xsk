import { dao } from '@dirigible-v4/db'
import { query } from '@dirigible-v4/db'
import { digest } from '@dirigible-v4/utils'

export class XSJSLibArtefactStateTable {
  tableName = "";
  tableSchema = "";
  table = null;

  constructor(tableName, tableSchema) {
    this.tableName = tableName;
    this.tableSchema = tableSchema;
    this.table = this._getOrCreateXSJSLibArtefactStateTable();
  }

  findEntryByResourceLocation(location) {
    const sql = "SELECT * FROM \"" + this.tableSchema + "\".\"" + this.tableName +"\" WHERE LOCATION = ?";
    return query.execute(sql, [location], "local", "SystemDB").shift();
  }

  createEntryForResource(location, content) {
    this.table.insert({
      location: location,
      hash: digest.md5Hex(content)
    });
  }

  updateEntryForResource(oldEntry, location, content) {
    this.table.update({
      id: oldEntry.ID,
      location: location,
      hash: digest.md5Hex(content)
    });
  }

  _getOrCreateXSJSLibArtefactStateTable() {
    try {
      const table = dao.create({
        table: this.tableName,
        properties: [{
          name: "id",
          column: "ID",
          type: "BIGINT",
          id: true
        }, {
          name: "location",
          column: "LOCATION",
          type: "VARCHAR",
          required: true,
          unique: true
        }, {
          name: "hash",
          column: "HASH",
          type: "VARCHAR",
          required: true
        }]
      },
      "XSJSLibArtefactStateTable",
      "SystemDB",
      "local"
      );

      if (!table.existsTable()) {
        table.createTable();
      }

      return table;
    } catch(e) {
      throw new Error("Cannot check if synchrnoisation is needed. Reason: " + e);
    }
  }

  clear() {
    this.table.remove();
  }
}