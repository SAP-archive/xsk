var path = require('path');
var fs = require('fs');
var infoHandler = require('./information-handler');
var mc = require('./message-categories');
var cliColor = require('cli-color');

var config = {};
try{
    var configPath = path.join(__dirname, "../", "config", "trace.json");
    var content = fs.readFileSync(configPath);
    config = JSON.parse(content);

}
catch(err){
    console.log("default trace file trace.json does not exist.");
    //file not exists
}


//default trace settings
var traceConfig = config;
var forcemode = (Object.keys(traceConfig).length == 0);
var project_dir = path.resolve(__dirname + "/../");
var progress = 0;
var executionStep = 0;

const windowWidth = cliColor.windowSize.width;
const stepCount = 19;
const tickSize = ((windowWidth - 16) / stepCount) | 0;
const barSize = stepCount * tickSize;

const loadingIndicatorText = ['|', '/', '-', '\\'];
var currentLoadingIndicator = 0;

var traceLevel = 
{
    trace: 0,
	debug: 1,
	info:2,
	warning:3,
	error:4,
	fatal:5,
	none: 6,
	empty:7
};

function isBasEnvironment() {
	return process.env.NODE_ENV && process.env.NODE_ENV === 'bas';
}

const stubLogger = {
	debug: () => {},
	trace: () => {},
	info: () => {},
	error: () => {},
	warn: () => {},
	fatal: () => {}
};

let vscodeLogger = null;

/**
 * @return {{warn: function(), trace: function(), debug: function(), error: function(), info: function(), fatal: function()}|IChildLogger}
 */
function getVsCodeLogger() {
	if (vscodeLogger !== null) {
		return vscodeLogger;
	}

	// this dynamic require used due to "vscode" dependency injection error
	// so if the vscode context is not available, then use the stubbed logger
	if (isBasEnvironment()) {
		vscodeLogger = require("../lib/bas/logging/logger").getLogger();
	} else {
		vscodeLogger = stubLogger;
	}

	return vscodeLogger;
}

function _getCallerFile() {
	var originalStackTrace = Error.prepareStackTrace;
    try {
        var err = new Error();
        var callerfile;
        var currentfile;

        Error.prepareStackTrace = function (err, stack) { return stack; };

        currentfile = err.stack.shift().getFileName();

        while (err.stack.length) {
            callerfile = err.stack.shift().getFileName();

            if(currentfile !== callerfile) return callerfile;
        }
    } catch (err) {}
    finally { Error.prepareStackTrace = originalStackTrace; }
    return undefined;
}
function _getFileLevel(callerPath)
{
	if(!traceConfig){
		traceConfig = {};
	}
	if(!traceConfig.level){
		traceConfig.level = "trace";
	}
	var propName = (callerPath.substring(project_dir.length, callerPath.length)).replace(/\\/g, '/');
	var fileLevel =  (traceConfig["file-specific-level"][propName] && traceConfig["file-specific-level"][propName]["level"]) ? traceLevel[traceConfig["file-specific-level"][propName]["level"]]:  traceLevel["empty"];
	var genericLevel = traceLevel[traceConfig["level"]];
	return fileLevel < genericLevel ? fileLevel : (fileLevel == traceLevel["empty"]? genericLevel : fileLevel);
}

function getMessageFromArray(msg){
	var ret = msg[0];
	for(var iRep = 0; iRep < msg.length - 1; iRep++){
		var s = "{*" + iRep + "}";
		var myReg = new RegExp(s, 'g');
		ret = ret.replace(myReg, msg[iRep + 1]);
	}
	return ret;
}

function _getLoadingIndicator() {
	if(currentLoadingIndicator > 3){
		currentLoadingIndicator = 0;
	}
	var loadingIndicator = '[' + loadingIndicatorText[currentLoadingIndicator] + ']';
	
	currentLoadingIndicator++;
	
	return loadingIndicator;
}


function _getStep() {
	var stepString = "00" + executionStep;
	var step = stepString.substr(stepString.length - 2);
	
	var completeString = '[Step ' + step + '/' + stepCount + ']';
	return completeString;
}


function clearTerminal(){
	var windowWidth = cliColor.windowSize.width;	
	process.stdout.write(cliColor.move.left(windowWidth) + cliColor.erase.lineRight);
}

function _log(message, options)
{
	clearTerminal();
	if(typeof message === 'string')
		console.log(message);
	else if(Object.prototype.toString.call(message) === '[object Array]' ){
		var msg = getMessageFromArray(message);
		console.log(msg);
	}
	else if(typeof message === 'object'){
		console.log(getMessageFromArray(message.message));

	}
	return true;
}

function endsWith(str, suffix){
	return str.indexOf(suffix, this.length - suffix.length) !== -1;
}

function logUtil()
{
	var that = this;
	
    this.resetTraceConfig = function(){
		var configPath = process.env.traceConfig;
		if(configPath){

			if(endsWith(configPath, ".json")){
                // just read normally to ensure it is being found
                traceConfig = JSON.parse(fs.readFileSync(configPath, {"encoding": "utf-8"}));
                that.info("trace config reset to: \n" + JSON.stringify(traceConfig, null, 4));
			}
			else{
                that.info("trace reset failed, only json file is accepted");
			}
			forcemode = (Object.keys(traceConfig).length == 0);
		}
	};

	this.success = function(message, optionObj)
	{
		console.log(message);
	};

    this.trace = function(message, optionObj)
    {
    	getVsCodeLogger().trace(message);

        if(forcemode)
        {
            return _log(message);
        }
        var functionLevel = traceLevel["trace"];
        var callerPath = _getCallerFile();
        var fileLevel = _getFileLevel(callerPath);
        if(functionLevel >= fileLevel)
        {
            return _log(message);
        }
        return false;
    };

	this.debug = function(message, optionObj)
	{
		getVsCodeLogger().debug(message);

		if(forcemode)
		{
			return _log(message);
		}
		var functionLevel = traceLevel["debug"];
		var callerPath = _getCallerFile();
		var fileLevel = _getFileLevel(callerPath);
		if(functionLevel >= fileLevel)
		{
			return _log(message);
		}
		return false;
	};

	this.info = function(message, optionObj)
	{
		getVsCodeLogger().info(message);

		if(forcemode)
		{
			return _log(message);
		}
		var functionLevel = traceLevel["info"];
		var callerPath = _getCallerFile();
		var fileLevel = _getFileLevel(callerPath);
		if(functionLevel >= fileLevel)
		{
			return _log(message);
		}
		return false;
	};

	this.warning = function(message, optionObj)
	{
		getVsCodeLogger().warn(message);

		if(forcemode)
		{
			return _log(message);
		}
		var functionLevel = traceLevel["warning"];
		var callerPath = _getCallerFile();
		var fileLevel = _getFileLevel(callerPath);
		if(functionLevel >= fileLevel)
		{
			return _log(message);
		}
		return false;
	};


	
	this.error = function(message, optionObj)
	{
		getVsCodeLogger().error(message);

		if(optionObj && optionObj.report == true){
			var msg = that.makeReportObject(message, {level: mc.ERROR});
			infoHandler.logMessage(msg);
		}
		if(forcemode)
		{
			return _log(message);
		}
		var functionLevel = traceLevel["error"];
		var callerPath = _getCallerFile();
		var fileLevel = _getFileLevel(callerPath);
		if(functionLevel >= fileLevel)
		{
			return _log(message);
		}
		return false;
	};

	this.fatal = function(message, optionObj)
	{
		getVsCodeLogger().fatal(message);

		if(optionObj && optionObj.report == true){
			var msg = that.makeReportObject(message, {level: mc.FATAL});
			infoHandler.logMessage(msg);
		}
		if(forcemode)
		{
			return _log(message);
		}
		var functionLevel = traceLevel["fatal"];
		var callerPath = _getCallerFile();
		var fileLevel = _getFileLevel(callerPath);
		if(functionLevel >= fileLevel)
		{
			return _log(message);
		}
		return false;
	};
	

	this.makeReportObject = function(message, options){
		if(typeof message === 'object'){
			return message;
		}
		else if(typeof message === 'string'){
			return {
                type: options.level,
                message: [message],
                category: mc.OTHERS,
                id: mc.OTHERS + '_0',
                file: ""
            };
		}
		else if(Object.prototype.toString.call(message) === '[object Array]'){
			return {
                type: options.level,
                message: message,
                category: mc.OTHERS,
                id: mc.OTHERS + '_0',
                file: ""
            };
		}
	};
	
	this.log = console.log;
    this.setTraceConfig = function(traceObj){
        traceConfig = traceObj;
        forcemode = (Object.keys(traceConfig).length == 0);
    };

	this.logProgress = function(amount = 0) {
		
		if(amount === -1) {
			progress = barSize;
			executionStep = 19;
		} else {
			executionStep += amount;
			amount = (amount * tickSize) | 0;
			progress += amount;
		}
		
		var symbol = '#';
		var currentprogressBar = (new Array(progress)).join(symbol);

		var rest = (barSize - progress) | 0;
		var remainingProgressBar = (new Array(rest)).join('-');

		var loadingIndicator = _getLoadingIndicator();
		var step = _getStep();
		var progressBar = loadingIndicator + ' [' + currentprogressBar + remainingProgressBar + '] ' + step;

		process.stdout.write(cliColor.move.left(windowWidth) + progressBar + cliColor.erase.lineRight);
	};


}

module.exports = new logUtil();


