import { XSJSLibContentModifier } from '/exports/XSJSLibContentModifier.mjs'
import { XSJSLibArtefactStateTable } from '/exports/XSJSLibArtefactStateTable.mjs'

export class XSJSLibExportsGenerator {

  processedArtefactsTable = null;

  constructor(targetRegistryCollection, stateTableParams) {
    this.processedArtefactsTable = new XSJSLibArtefactStateTable(
      stateTableParams.name,
      stateTableParams.schema,
      stateTableParams.location,
      stateTableParams.db
    );

    this._generateExportsRecursively(targetRegistryCollection);
  }

  _generateExportsRecursively(parentCollection) {
    if (!parentCollection.exists() || !this.processedArtefactsTable) {
      return;
    }

    let resourceNames = this._toJsArray(parentCollection.getResourcesNames());
    let collectionNames = this._toJsArray(parentCollection.getCollectionsNames());

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
    let result = [];
    array.forEach(element => result.push(element));
    return result;
  }

  _checkTableAndGenerateExportsIfNeeded(resource) {
    let content = resource.getText();
    let location = resource.getPath();

    let contentModifier = new XSJSLibContentModifier();

    let foundEntry = this.processedArtefactsTable.findEntryByResourceLocation(location);

    if (!foundEntry) {
        let contentWithExports = contentModifier.writeExportsToResource(resource, content);
        this.processedArtefactsTable.createEntryForResource(location, contentWithExports);
    }
    else if (this.processedArtefactsTable.checkForContentChange(foundEntry, content)) {
      let contentWithExports = contentModifier.writeExportsToResource(resource, content);
      this.processedArtefactsTable.updateEntryForResource(foundEntry, location, contentWithExports);
    }
  }
}
