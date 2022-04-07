const dao = require("db/v4/dao");
const query = require("db/v4/query");
const digest = require("utils/v4/digest");

export class XSJSLibArtefactStateTable {
  tableName = "";
  tableSchema = "";
  tableLocation = "";
  tableDB = "";

  table = null;

  constructor(tableName, tableSchema, tableLocation, tableDB) {
    this.tableName = tableName;
    this.tableSchema = tableSchema;
    this.tableLocation = tableLocation;
    this.tableDB = tableDB;
    this.table = this._getOrCreateXSJSLibArtefactStateTable();
  }

  findEntryByResourceLocation(location) {
    let sql = "SELECT * FROM \"" + this.tableSchema + "\".\"" + this.tableName +"\" WHERE LOCATION = ?";
    return query.execute(sql, [location], this.tableLocation, this.tableDB).shift();
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

  checkForContentChange(foundEntry, content) {
    return foundEntry.HASH !== digest.md5Hex(content);
  }

  _getOrCreateXSJSLibArtefactStateTable() {
    try {
        let table = dao.create({
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
        null,
        this.tableDB,
        this.tableLocation
        );

        if (!table.existsTable()) {
          table.createTable();
        }

        return table;
      } catch(e) {
        throw new Error("Cannot check if synchrnoisation is needed. Reason: " + e);
      }
  }
}