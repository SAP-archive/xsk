import { XSJSLibContentModifier } from '/exports/XSJSLibContentModifier.mjs'
import { XSJSLibArtefactStateTable } from '/exports/XSJSLibArtefactStateTable.mjs'
import { digest } from '@dirigible-v4/utils'

export class XSJSLibExportsGenerator {

  processedArtefactsTable = null;

  constructor(stateTableParams) {
    this.processedArtefactsTable = new XSJSLibArtefactStateTable(
      stateTableParams.name,
      stateTableParams.schema
    );
  }

  run(targetRegistryCollection) {
    this._generateExportsRecursively(targetRegistryCollection);
  }

  _generateExportsRecursively(parentCollection) {
    if (!parentCollection.exists() || !this.processedArtefactsTable) {
      return;
    }

    const resourceNames = this._toJsArray(parentCollection.getResourcesNames());
    const collectionNames = this._toJsArray(parentCollection.getCollectionsNames());

    resourceNames
      .filter(resourceName => resourceName.endsWith(".xsjslib"))
      .map(resourceName => parentCollection.getResource(resourceName))
      .forEach(resource => {
        try {
          this._checkTableAndGenerateExportsIfNeeded(resource);
        } catch (e) {
          throw new Error("Cannot check if synchronisation is needed. Reason: ", e);
        }
      });

    collectionNames
      .map(collectionName => parentCollection.getCollection(collectionName))
      .forEach(collection => this._generateExportsRecursively(collection));
  }

  _toJsArray(array) {
    return [
      ...array
    ];
  }

  _checkTableAndGenerateExportsIfNeeded(resource) {
    const content = resource.getText();
    const location = resource.getPath();

    const contentModifier = new XSJSLibContentModifier();

    const foundEntry = this.processedArtefactsTable.findEntryByResourceLocation(location);

    if (!foundEntry) {
        const contentWithExports = contentModifier.writeExportsToResource(resource, content);
        this.processedArtefactsTable.createEntryForResource(location, contentWithExports);
    }
    else if (this._checkForContentChange(foundEntry, content)) {
      const contentWithExports = contentModifier.writeExportsToResource(resource, content);
      this.processedArtefactsTable.updateEntryForResource(foundEntry, location, contentWithExports);
    }
  }

  _checkForContentChange(foundEntry, content) {
    return foundEntry.HASH !== digest.md5Hex(content);
  }
}
