var repositoryManager = require("platform/v4/repository");
var streams = require("io/v4/streams");
var cache = require("core/v4/context");
var acorn = require("acornjs/acorn");

exports.import = function (namespace, name) {
    var validPackages = namespace.split('.');
    var validPackage = validPackages.join('/') + '/';
    var resourceName = validPackage + name + '.xsjslib';
    var resource = repositoryManager.getResource('/registry/public/' + resourceName);
    var passed = cache.get(resourceName);
    var allpassed = cache.get("xsk-imported");
    if (!allpassed) {
        allpassed = [];
    } else {
        allpassed = JSON.parse(allpassed);
    }
    if (passed !== undefined && passed !== null) {
//        console.error("Cyclic dependency using: " + resourceName + " in " + JSON.stringify(allpassed));
        return passed;
    }

    var resourceByteArray = resource.getContent();
    var resourceInputStream = streams.createByteArrayInputStream(resourceByteArray);

    var resourceContent = resourceInputStream.readText();
    resourceInputStream.close();

    var context = { 'exports': {} };

    cache.set(resourceName, context);
    allpassed.push(resourceName);
    cache.set("xsk-imported", JSON.stringify(allpassed));

    console.info("Importing: " + resourceName);
    //console.error(resourceContent);

    // dirty hack to avoid double wrapping of  imported library
    var n = resourceContent.indexOf("(function(exports) {");
    if (n >= 0) {
        resourceContent = resourceContent.substring(0, n) + resourceContent.substring(n + 20, resourceContent.length);
        n = resourceContent.lastIndexOf("}(this));");
        if (n >= 0) {
            resourceContent = resourceContent.substring(0, n) + resourceContent.substring(n + 9, resourceContent.length);
        }
    }
    //---

    var exports = getExports(resourceContent);
    resourceContent += "\n\n";
    exports.forEach(e => resourceContent += "exports." + e + " = " + e + ";\n");

    with (context) {
        eval(resourceContent);
    }

    for (var propertyName in context.exports) {
        context[propertyName] = context.exports[propertyName];
    }

    var parent = null;
    validPackages.forEach(function(segment) {
        if (parent === null) {
            if (!$[segment]) {
                $[segment] = {};
            }
            parent = $[segment];
        } else {
            if (!parent[segment]) {
                parent[segment] = {};
            }
            parent = parent[segment];
        }
    });
    parent[name] = context;

    return context;
}

function getExports(code) {
    var nodes = acorn.parse(code);
    var functionDeclarations = nodes.body.filter(e => e.type === "FunctionDeclaration").map(e => e.id.name);
    var variableDeclarations = nodes.body.filter(e => e.type === "VariableDeclaration").flatMap(e => e.declarations.filter(d => d.type === "VariableDeclarator").flatMap(d => d.id.name));
    var exports = functionDeclarations.concat(variableDeclarations);
    return exports;
}