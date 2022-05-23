import { XSJSLibParser } from '/exports/XSJSLibParser.mjs'
import { XSJSLibStateTable } from '/exports/XSJSLibStateTable.mjs'
import { digest } from '@dirigible-v4/utils'
import { repository } from '@dirigible-v4/platform'

export class XSJSLibExportsGenerator {

  processedArtefactsTable = null;

  constructor(stateTableParams) {
    this.processedArtefactsTable = new XSJSLibStateTable(
      stateTableParams.name,
      stateTableParams.schema
    );
  }

  run(targetRegistryPath, targetRegistryPathType) {
    if(targetRegistryPathType == "ExistentXSJSLibFile") {
      const targetResource = repository.getResource(targetRegistryPath);
      this._checkTableAndGenerateExportsIfNeeded(targetResource, false);
    }
    else if(targetRegistryPathType == "ExistentFolder") {
      const targetCollection = repository.getCollection(targetRegistryPath);
      this._generateExportsRecursively(targetCollection);
    }
    else {
      throw new Error(
        "XSJSLibExportsGenerator: Unrecognized target path type "
         + targetRegistryPathType
         + " for path "
         + targetRegistryPath
       );
    }
  }

  _generateExportsRecursively(parentCollection) {
    if (!parentCollection.exists()) {
      throw new Error("XSJSLibExportsGenerator: Collection not found: " + parentCollection.getPath()); // TODO: Log warning instead of error?
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
          throw new Error("XSJSLibExportsGenerator: Cannot check if synchronisation is needed. Reason: ", e);
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

  _checkTableAndGenerateExportsIfNeeded(resource, isResourceCheckRequired = true) {
    if(isResourceCheckRequired) {
      if(!resource.exists()) {
        throw new Error("XSJSLibExportsGenerator: Resource not found."); // TODO: Log warning instead of error?
      }

      if(!resource.getPath().endsWith(".xsjslib")) {
        return;
      }
    }

    if (!this.processedArtefactsTable) {
      throw new Error("XSJSLibExportsGenerator: State table not found.");
    }

    const content = resource.getText();
    const location = resource.getPath();
    const foundEntry = this.processedArtefactsTable.findEntryByResourceLocation(location);

    if (!foundEntry) {
      // No entry in state table => generate.
      this._generateExportsFile(location, content);
      this.processedArtefactsTable.createEntryForResource(location, content);
    }
    else if (this._isContentChanged(foundEntry, content)) {
      // Entry in state table found, but content changed => generate.
      this._generateExportsFile(location, content);
      this.processedArtefactsTable.updateEntryForResource(foundEntry, location, content);
    }
    else {
      // No entry in state table and no change in content => do not generate.
    }
  }

  _generateExportsFile(location, content) {
    const parser = new XSJSLibParser();
    const contentWithExports = parser.appendExports(content);
    const generatedExportsFilePath = location + ".generated_exports";
    const exportsResource = repository.createResource(
      generatedExportsFilePath,
      contentWithExports,
      "application/javascript"
    );
  }

  _isContentChanged(foundEntry, content) {
      return foundEntry.HASH !== digest.md5Hex(content);
  }
}
