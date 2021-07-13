const fs = require('fs');
const path = require('path');

const PACKAGE_JSON_PATH = path.resolve(__dirname, '../../package.json');

const json = require(PACKAGE_JSON_PATH);

if (!json.hasOwnProperty('name')) {
    throw new Error('The "name" is not defined in the package.json');
}

if (!json.hasOwnProperty('name-scope')) {
    throw new Error('The "name-scope" is not defined in the package.json');
}

json.name = `${json["name-scope"]}/${json.name}`;

fs.writeFileSync(PACKAGE_JSON_PATH, JSON.stringify(json, null, 2));