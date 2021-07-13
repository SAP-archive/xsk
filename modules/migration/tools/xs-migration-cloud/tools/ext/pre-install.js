const fs = require('fs');
const path = require('path');

const PACKAGE_JSON_PATH = path.resolve(__dirname, '../../package.json');

const json = require(PACKAGE_JSON_PATH);

if (!json.hasOwnProperty('name')) {
    throw new Error('The "name" is not defined in the package.json');
}

const splitPackageName = json.name.split('/');

json.name = splitPackageName[splitPackageName.length - 1];

fs.writeFileSync(PACKAGE_JSON_PATH, JSON.stringify(json, null, 2));