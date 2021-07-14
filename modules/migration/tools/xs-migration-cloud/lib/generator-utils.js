var fs = require('fs');
var path = require('path');
var logUtil = require('./log-util');
var mkdirp = require('mkdirp');
var async = require('async');

var supported_tags = [
    "devx",
    "non-synonym"
];

var ignoreFile = {
    ".png":"true",
    ".gif":"true"
};

function replace(srcFileB, replacements) {
    if (replacements === undefined) {
        return srcFileB;
    }
    var srcFile = srcFileB.toString('utf8');
    for (var rp in replacements) {
        var pt = new RegExp("\\$\\{" + rp + "\\}", "g");
        srcFile = srcFile.replace(pt, replacements[rp]);
    }
    return new Buffer(srcFile, 'utf8');
}

function updateContent(fileContent, tags) {
    var content = fileContent.toString('utf8');
    for (var i = 0; i < tags.length; i++) {
        var tag = tags[i];
        var regex1 = new RegExp("\\${" + tag + "\\-" + "}\\s?\\n?(.|\n|\s)*?\\s?\\n?\\${\\/" + tag + "}", "g");
        content = content.replace(regex1, "");
        var regex2 = new RegExp("\\${\\/*" + tag + "\\+*" + "}", "g");
        content = content.replace(regex2, "");
    }
    for (var i = 0; i < supported_tags.length; i++) {
        var tag = supported_tags[i];
        var regex1 = new RegExp("\\${" + tag + "\\+" + "}\\s?\\n?(.|\n|\s)*?\\s?\\n?\\${\\/" + tag + "}", "g");
        content = content.replace(regex1, "");
        var regex2 = new RegExp("\\${\\/*" + tag + "\\-*" + "}", "g");
        content = content.replace(regex2, "");
    }
    return new Buffer(content, 'utf8');

}


function copyTemplateFiles(templateDir, targetDir, files, replacements, context) {
    files.forEach(function (f) {
        var fileToWrite = path.join(targetDir, f[1], f[0]);
        var fileToReadName = f[0];
        if(f[2]){
            fileToReadName += '-' + f[2];
        }
        var fileToRead = path.join(templateDir, fileToReadName);
        try {
            var tmpFile = fs.readFileSync(fileToRead);
            tmpFile = replace(tmpFile, replacements);
            var checkTags = [];
            
            checkTags.push("devx");
            
            var ext = path.extname(fileToWrite);
            if(!ignoreFile[ext]){
                tmpFile = updateContent(tmpFile, checkTags);
            }
            fs.writeFileSync(fileToWrite, tmpFile);
        } catch (e) {
            logUtil.error("Unable to copy from " + fileToRead + " to " + fileToWrite + ": " + e);
        }
    });
}

function makeDirs(targetDir, dirs) {
    dirs.forEach(function (dir) {
        mkdirp.sync(path.join(targetDir, dir));
    });
}


function writeFile(fpath, filename, content, outerCallback) {

    async.waterfall([
        function (callback) {
            logUtil.debug('Generating directory: ' + fpath);
            mkdirp(fpath, callback);
        },
        function (made, callback) {
            logUtil.debug('Directory generated: ' + made);
            logUtil.debug('Writing file: ' + filename);
            
            fs.writeFile(path.join(fpath, filename), content, 'utf-8', callback);
            
        }],
        function(error){
            if(error){
                logUtil.debug('Error during generator-utils.writeFile: ' + error);
                return outerCallback(error);
            }
            return outerCallback();
        }
    );

}


function writeFileSync(dpath, filename, content) {
    mkdirp.sync(dpath);
    fs.writeFileSync(path.join(dpath, filename), content);
}


function getPathForPackage(name, delimiter) {

    function ensure(b, msg) {
        if (!b) {
            throw msg;
        }
    }

    var isInQuote = false;
    var current = "";
    var ps = [];
    var i = 0;
    while (i < name.length) {
        var c = name.charAt(i);
        if (c === '.') {
            if (isInQuote) {
                current += c;
            } else {
                ps.push(current);
                current = '';
            }
        } else if (c === '"') {
            if (isInQuote) {
                // end of quote
                ps.push(current);
                current = '';
                isInQuote = false;
                i++;
                if (i < name.length) {
                    ensure(name.charAt(i) === '.');
                }
            } else {
                ensure(current.length === 0);
                isInQuote = true;
            }
        } else {
            current += c;
        }
        i++;
    }
    if (current.length !== 0) {
        ps.push(current);
    }
    return ps.join(delimiter);
}



function format(msgArrary) {

    var str = msgArrary[0];
    var replacements = msgArrary.slice(1);
    return str.replace(/\{(\d+)\}/g, function () {
        return replacements[arguments[1]];
    });
}


function generateTargetDirectory(projectName){
    let currentTime = new Date(Date.now());    
    let month = currentTime.getMonth() + 1;
    let timestamp = '' + currentTime.getFullYear() + month + currentTime.getDate() + '-' + currentTime.getHours() + currentTime.getMinutes() + currentTime.getSeconds();
    let targetDir = path.join('results', (projectName ? projectName + '_' : '') + timestamp);
    
    return targetDir;
}

module.exports.copyTemplateFiles = copyTemplateFiles;
module.exports.makeDirs = makeDirs;
module.exports.writeFile = writeFile;
module.exports.writeFileSync = writeFileSync;
module.exports.getPathForPackage = getPathForPackage;
module.exports.updateContent = updateContent;
module.exports.format = format;
module.exports.generateTargetDirectory = generateTargetDirectory;
