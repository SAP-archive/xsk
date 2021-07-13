
var stepMeta = require('./step-metadata');
var log = require('./log-util');
var objectClone = require('./object-clone');


function StepGenerator() {

    function addCount(steps) {

        for (var i=0; i < steps.length; i++) {
            var step = steps[i];
            step.list = [];
            for (var k in step.messages) {
                var msgNum = step.messages[k].length;
                step.list.push({
                    text: k,
                    value: msgNum
                });
            }
        }
        return steps;
    }

    function sortSteps(stepMap) {

        var stepArray = [];
        for(var k in stepMap) {
            stepArray.push(objectClone.cloneObject(stepMap[k]));
        }
        return stepArray.sort(function (a,b) {
            return a.priority - b.priority;
        });
    }

    function extractMesssages(list) {

        var steps = {};
        for (var i=0; i < list.length; i++) {
            var msg = list[i];
            // DEBUG code
            if (!("category" in msg)) {
                log.error("Uncategorized error " + JSON.stringify(msg));
                continue;
            } 
            var cat = msg.category;

            if (!(cat in stepMeta)) {
                log.error("Unknown message category: " + cat + " in " + JSON.stringify(msg, null, 4));
                continue;
            }

            if (!("type" in msg)) {
                log.error("no type set in message: " + JSON.stringify(msg, null, 4));
                continue;
            }
            var sev = msg.type;

            if (!(cat in steps)) {
                steps[cat] = stepMeta[cat];
            }
            if(!steps[cat].messages){
            	steps[cat].messages = {};
            }
            if (!steps[cat].messages[sev]) {
                steps[cat].messages[sev] = [];
            }
            steps[cat].messages[sev].push(objectClone.cloneObject(msg));
        }
        return steps;
    }

    function fixUrls(context, stepArray) {

        stepArray.forEach(function(step) {
            var newLink = {
                info: step.link.info,
                url: context.config.urls[step.link.urlPrefixKey] + step.link.urlSuffix
            }
            step.link = newLink;
        });
    }

    function ensureAlwaysShwonAded(stepMap) {
        for (k in stepMeta) {
            if ("always-shown" in stepMeta[k] && stepMeta[k]["always-shown"] === true) {
                if(!(k in stepMap)) {
                    stepMap[k] = stepMeta[k];
                }
            }
        }
        return stepMap;
    }

    this.generateMigrationSteps = function(context, list) {
        
        var stepMap = extractMesssages(list);
        stepMap = ensureAlwaysShwonAded(stepMap);
        var stepArray = sortSteps(stepMap);
        fixUrls(context, stepArray);
        
        return addCount(stepArray);;
    }
}

module.exports = new StepGenerator();