import { XSJSLibContentModifier } from '/exports/XSJSLibContentModifier.mjs'
import { XSJSLibArtefactStateTable } from '/exports/XSJSLibArtefactStateTable.mjs'

export class XSJSLibExportsGenerator {

  processedArtefactsTable = null;

  constructor(targetRegistryCollection) {
    this.processedArtefactsTable = new XSJSLibArtefactStateTable("PROCESSED_XSJSLIB_ARTEFATCTS", "PUBLIC", "local", "SystemDB");
    this._generateExportsRecursively(targetRegistryCollection);
  }

  _generateExportsRecursively(parentCollection) {
    if (!parentCollection.exists() || !this.processedArtefactsTable) {
      return;
    }

    var resourceNames = this._toJsArray(parentCollection.getResourcesNames());
    var collectionNames = this._toJsArray(parentCollection.getCollectionsNames());

    resourceNames
      .filter(resourceName => resourceName.endsWith(".xsjslib"))
      .map(resourceName => parentCollection.getResource(resourceName))
      .forEach(resource => {
        try {
          this._checkTableAndGenerateExportsIfNeeded(resource);
        } catch (e) {
          throw new Error("Cannot check if synchrnoisation is needed. Reason: " + e);
        }
      });

    collectionNames
      .map(collectionName => parentCollection.getCollection(collectionName))
      .forEach(collection => this._generateExportsRecursively(collection));
  }

  _toJsArray(array) {
    var result = [];
    array.forEach(element => result.push(element));
    return result;
  }

  _checkTableAndGenerateExportsIfNeeded(resource) {
    var content = resource.getText();
    var location = resource.getPath();

    var contentModifier = new XSJSLibContentModifier();

    var foundEntry = this.processedArtefactsTable.findEntryByResourceLocation(location);

    if (!foundEntry) {
      var contentWithExports = contentModifier.writeExportsToResource(resource, content);
      this.processedArtefactsTable.createEntryForResource(location, contentWithExports);
    }
    else if (this.processedArtefactsTable.checkForContentChange(foundEntry, content)) {
      var contentWithExports = contentModifier.writeExportsToResource(resource, content);
      this.processedArtefactsTable.updateEntryForResource(foundEntry, location, contentWithExports);
    }
  }
}
