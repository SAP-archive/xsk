import { XSJSLibContentModifier } from '/exports/XSJSLibContentModifier.mjs'
import { XSJSLibArtefactStateTable } from '/exports/XSJSLibArtefactStateTable.mjs'
import { digest } from '@dirigible-v4/utils'
import { repository } from '@dirigible-v4/platform'

export class XSJSLibExportsGenerator {

  processedArtefactsTable = null;

  constructor(stateTableParams) {
    this.processedArtefactsTable = new XSJSLibArtefactStateTable(
      stateTableParams.name,
      stateTableParams.schema
    );
  }

  run(targetPath) {
    const targetResource = repository.getResource(targetPath);
    const targetCollection = repository.getCollection(targetPath);

    if(targetResource.exists()) {
      this._checkTableAndGenerateExportsIfNeeded(targetResource);
    }
    else if(targetCollection.exists()) {
      this._generateExportsRecursively(targetCollection);
    }
    else {
      throw new Error("XSJSLibSynchronizer repository target not found at: " + targetPath);
    }
  }

  _generateExportsRecursively(parentCollection) {
    if (!parentCollection.exists()) {
      throw new Error("Collection not found: " + parentCollection.getPath());
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
    if(!resource.exists()) {
      throw new Error("XSJSLibSynchronizer resource not found.");
    }

    if(!resource.getPath().endsWith(".xsjslib")) {
      return;
    }

    if (!this.processedArtefactsTable) {
      throw new Error("XSJSLibSynchronizer state table not found.");
    }

    const content = resource.getText();
    const location = resource.getPath();
    const foundEntry = this.processedArtefactsTable.findEntryByResourceLocation(location);

    const contentModifier = new XSJSLibContentModifier();

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
