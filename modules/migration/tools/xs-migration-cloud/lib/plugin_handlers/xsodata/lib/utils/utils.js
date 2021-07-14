'use strict';

exports.endsWith = function (hay, needle) {
    return hay.indexOf(needle, hay.length - needle.length) !== -1;
};

exports.startsWith = function (hay, needle) {
    return hay.indexOf(needle) === 0;
};

exports.contains = function (hay, needle) {
    return hay.indexOf(needle) > -1;
};

exports.logDB = function (text, dbSegment) {
    var filter = function (key, value) {
        if (key === '__metadata' || key === 'previousDBSegment') {
            return undefined;
        }
        return value;
    };
    console.log(text + " " + JSON.stringify(dbSegment, filter, 4));
};

exports.ensureSlashEnding = function (path) {
    if (!exports.endsWith(path, '/')) {
        return path + '/';
    }
    return path;
};

exports.getObjectPropertiesAsList = function (object) {
    var list = [];
    for (var property in object) {
        if (object.hasOwnProperty(property)) {
            list.push(object[property]);
        }
    }
    return list;
};

exports.wrap = function (text) {
    return '"' + text + '"';
};
