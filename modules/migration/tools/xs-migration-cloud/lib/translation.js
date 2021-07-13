// --------------------------------------------------------------------------
//
// Translation related functionality
//
// --------------------------------------------------------------------------

var path = require('path');
var gutils = require('./generator-utils');
var putils = require('./plugin_handlers/plugin-utils');
var msgs = require('./messages');



function Translation() {


    var calcviewHeader =
'#!tabledata\n' +
'#{\n' +
'#  "target_table"   : "#BIMC_DESCRIPTIONS",\n' +
'#  "column_mappings" : {\n' +
'#      "QUALIFIED_NAME" : { "type" : "constant", "value" : "${packageId}::${objectName}" },\n' +
'#      "LANG"           : { "type" : "function", "name" : "extractLanguageCodeFromFileName", "parameters" : {"file_name":"${longObjectName}"} },\n' +
'#      "ID"             : 1,\n' +
'#      "DESCRIPTION"    : 2\n' +
'#  }\n' +
'#}\n' +
'#!tabledata\n';

    function replacer(doc, name, value) {
        var pt = new RegExp('\\$\\{' + name + '\\}', "g");
        return doc.replace(pt, value);
    }

    this.exportCalcviewTranslation = function(globalContext, sourceDb, file, packageId, objectName, objectSuffix, callback) {

        var _this = this;

        var dirName = gutils.getPathForPackage(packageId, "/");
        var dirNamePlatform = gutils.getPathForPackage(packageId, path.sep);

        var modelerArtifactSuffixes = ['calculationview', 'analyticview', 'attributeview'];

        sourceDb.getObjectTranslation(globalContext.system, packageId, objectName, modelerArtifactSuffixes, function(err, text) {

            if (err) {
                var msg = msgs.getMessage('INFRASTRUCTURE', 10);
                msg.file = path.join(globalContext.pathMap['db-relative-path'], file.RunLocation);
                msg.message.push(err);
                putils.pushMessage(file, msg);
            } else {
                for (var lang in text["object"]) {
                    var objectc = text["object"][lang];
                    var content = text["content"][lang];

                    if (lang === "default") {
                        lang = "";
                    }
                    var langf = lang === "" ? "" : "_" + lang;
                    var header = calcviewHeader;
                    header = replacer(header, "packageId", packageId);
                    header = replacer(header, "longObjectName",objectName + '.hdbcalculationview');
                    header = replacer(header, "objectName",objectName);
                    header = replacer(header, "lang", langf);
                    header = replacer(header, "dirName",dirName);

                    var calcviewProperties = header;


                    var caption = '';
                    for(let i = 0; i < objectc.length; i++) {
                        // this should always be the first and only element
                        // here
                        var h = objectc[i];
                        if (h.TEXT_ID === "caption") {
                            caption = h.CONTENT;
                        }
                    }
                    calcviewProperties += '# XTIT, 255\n';
                    calcviewProperties += '&&VIEW_NAME&&=' + caption + '\n';

                    if (content !== undefined) {
                        for(var i=0; i < content.length; i++) {
                            var tt = content[i];
                            calcviewProperties += '#' + tt.TEXT_TYPE + ", " + tt.MAX_LENGTH + "\n";

                            let text = putils.escapeMultilineContent(tt.CONTENT);
                            calcviewProperties += tt.TEXT_ID + "=" + text + "\n";
                        }
                    }
                    var fn = globalContext.pathMap["db-dev-object-folder"];

                    fn = path.join(fn, dirNamePlatform, objectName + ".hdbcalculationview" + langf + ".properties");
                    file.toBeCreated.push({
                        filename: fn,
                        update_content: calcviewProperties,
                        file_container: 'db'
                    });
                }
                //do not need this as synonym deployment does not need to have this privilege
            }
            callback(null, file);
        });
    };



}

module.exports = new Translation();
