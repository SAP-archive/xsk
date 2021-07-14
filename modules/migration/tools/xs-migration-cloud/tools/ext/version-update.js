const fse = require('fs-extra');
const path = require('path');

const extensionFiles = [
    'extension-dev.json',
    'extension-prod.json'
];
const VSCODE_EXTENSIONS_FIELD = 'vscodeExtensions';

function incrementVersionPatch(version) {
    let splitVersion = version.split('.');

    if (!splitVersion.length || version.match(/[^\d.]+/g)) {
        throw new Error(`The "${version}" version is not valid`);
    }

    splitVersion[splitVersion.length - 1] = parseInt(splitVersion[splitVersion.length - 1]) + 1;

    return splitVersion.join('.');
}

const PACKAGE_JSON_FILE_PATH = path.resolve(__dirname, '../../package.json');

if (!fse.existsSync(PACKAGE_JSON_FILE_PATH)) {
    throw new Error(`The ${PACKAGE_JSON_FILE_PATH} file not found`);
}

const packageJsonFile = require(PACKAGE_JSON_FILE_PATH);

if (!packageJsonFile.name) {
    throw new Error(`The "name" has not been found in the ${PACKAGE_JSON_FILE_PATH} file`);
}
if (!packageJsonFile.version) {
    throw new Error(`The "version" has not been found in the ${PACKAGE_JSON_FILE_PATH} file`);
}

const packageName = packageJsonFile.name;
const packageVersion = packageJsonFile.version;
const updatedPackageVersion = incrementVersionPatch(packageVersion);

function validateExtensionFile(extensionFileName, extensionFileJson) {
    if (!extensionFileJson) {
        throw new Error(`The provided json content of the "${extensionFileName}" file is not valid`);
    }
    if (!extensionFileJson.version) {
        throw new Error(`The "version" has not been found in the "${extensionFileName}" file`);
    }
    if (!extensionFileJson[VSCODE_EXTENSIONS_FIELD]) {
        throw new Error(`The "${VSCODE_EXTENSIONS_FIELD}" has not been found in the "${extensionFileName}" file`);
    }
}

const originalFileContents = [];

function writeJsonToFile(filePath, fileJsonContent) {
    fse.writeFileSync(filePath, JSON.stringify(fileJsonContent, null, 2));
}

function rollbackOriginalContents() {
    originalFileContents.forEach(rollbackOriginalContent);
}

function rollbackOriginalContent(contentContext) {
    writeJsonToFile(contentContext.path, contentContext.jsonContent);
}

function pushOriginalFileContent(filePath, fileContent) {
    originalFileContents.push({
        path: filePath,
        jsonContent: fileContent
    });
}

try {
    extensionFiles.forEach(extensionFile => {
        const extensionFileAbsolutePath = path.resolve(__dirname, '../../', extensionFile);

        if (!fse.existsSync(extensionFileAbsolutePath)) {
            throw new Error(`The "${extensionFileAbsolutePath}" file not found`);
        }

        const extensionFileJson = require(extensionFileAbsolutePath);
        validateExtensionFile(extensionFile, extensionFileJson);

        pushOriginalFileContent(extensionFileAbsolutePath, extensionFileJson);

        const neededExtensionIndex = extensionFileJson[VSCODE_EXTENSIONS_FIELD].findIndex(vsCodeExt => {
            return vsCodeExt.name && vsCodeExt.name === packageName;
        });

        if (neededExtensionIndex < 0) {
            throw new Error(`The "${packageName}" extension not found in the "${extensionFile}" file`);
        }

        const vsCodeExtensionJson = extensionFileJson[VSCODE_EXTENSIONS_FIELD][neededExtensionIndex];
        vsCodeExtensionJson.versionRange = updatedPackageVersion;

        extensionFileJson[VSCODE_EXTENSIONS_FIELD][neededExtensionIndex] = vsCodeExtensionJson;
        extensionFileJson.version = incrementVersionPatch(extensionFileJson.version);

        writeJsonToFile(extensionFileAbsolutePath, extensionFileJson);
    });

    pushOriginalFileContent(PACKAGE_JSON_FILE_PATH, packageJsonFile);

    packageJsonFile.version = updatedPackageVersion;

    writeJsonToFile(PACKAGE_JSON_FILE_PATH, packageJsonFile);
} catch (e) {
    console.error('Error during the version update, try rollback to the original statements', e.message);
    rollbackOriginalContents();
}