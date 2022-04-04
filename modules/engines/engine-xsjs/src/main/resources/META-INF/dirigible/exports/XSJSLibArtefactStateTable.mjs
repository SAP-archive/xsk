var dao = require("db/v4/dao");
var query = require("db/v4/query");
var digest = require("utils/v4/digest");

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
    var sql = "SELECT * FROM \"" + this.tableSchema + "\".\"" + this.tableName + "\" WHERE LOCATION = ?";
    return query.execute(sql, [location], null, this.tableDB, this.tableLocation).shift();
  }

  createEntryForResource(location, content) {
    this.table.insert({
      location: location,
      hash: digest.md5Hex(content)
    });
  }

  updateEntryForResource(oldEntry, location, content) {
    oldEntry.LOCATION = location;
    oldEntry.HASH = digest.md5Hex(content);
    this.table.update(oldEntry);
  }

  checkForContentChange(foundEntry, content) {
    return foundEntry.HASH !== digest.md5Hex(content);
  }

  _getOrCreateXSJSLibArtefactStateTable() {
    try {
      var newTable = dao.create({
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

      if (!newTable.existsTable()) {
        newTable.createTable();
      }

      return newTable;
    } catch (e) {
      throw new Error("Cannot check if synchronisation is needed. Reason: " + e);
    }
  }
}