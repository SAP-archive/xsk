var objectClone = require('../lib/object-clone');

var fileUtils = require('./file-utils');
var mc = require('./message-categories');
var msgs = require('./messages');
var path = require('path');

var messageFormat =
{
    "xsjs" : [],
    "db" : [],
    "odata" : [],
    "web" : [],
    "todo" : [],
    "delete" : [],
    "stat" :
    {
        "xsjs" :
        {
            number : 0,
            ext : {}
        },
        
        "db" :
        {
            number : 0,
            ext : {}
        },

        "odata" :
        {
            number : 0,
            ext : {}
        },

        "web" :
        {
            number : 0,
            ext : {}
        },
        "todo" :
        {
            number : 0,
            ext : {}
        },
        "delete" :
        {
        	number : 0,
            ext : {}
        }

    },
    total : 0
};

var statusFormat =
{
	number : 0,
	list : []
};


var containers = [];


class InformationHandler {

	constructor() {
		this.messages = {
			total  : 0,
			known  : objectClone.cloneObject(messageFormat),
			error  : objectClone.cloneObject(statusFormat),
			warning: objectClone.cloneObject(statusFormat),
			info   : objectClone.cloneObject(statusFormat),
			unknown: {
				ext   : {},
				number: 0
			}
		};

	}


		logMessage(message){

        var mtype = message.type.toLowerCase();
		switch (mtype) {
		case 'error':
			this.messages.error.number++;
			this.messages.error.list.push(objectClone.cloneObject(message));
			break;
		case 'warning':
			this.messages.warning.number++;
			this.messages.warning.list.push(objectClone.cloneObject(message));
			break;
		case 'info':
			this.messages.info.number++;
			this.messages.info.list.push(objectClone.cloneObject(message));
			break;
		}
	};
	
	
	analyzeMessage(globalContext, fileObj , containerName ) {

		if (fileObj.messages) {
			for ( var m_Index in fileObj.messages) {
				var message = fileObj.messages[m_Index];
				this.logMessage(message);
			}
		}
        // todo: probably not needed anymore
		if(containerName == "todo" && !fileObj.messages){
			this.logMessage({
	            type: mc.WARNING,
	            message: ['File required to be reviewed and proceed manually'],
	            file: path.join(globalContext.pathMap['todo-relative-path'], fileObj.RunLocation),
	            category: mc.TODO,
	            id: mc.TODO + "_4"
	        });
		}
	};
	
	
	updateMessages(globalContext, container, fileObj, found) {
		
		var container_type = null;
        if (fileObj.toBeCreated && fileObj.toBeCreated[0]) {
            var obj = fileObj.toBeCreated[0];
            if (obj.file_container) {
                container_type = obj.file_container;
            }
            else if (container.containerType) {
                container_type = container.containerType;
            }
            else {
                container_type = "todo";
            }
        }
        else if (container && container.containerType) {
            container_type = container.containerType;
        }
        else {
            container_type = "todo";
        }

		this.messages.known[container_type] = this.messages.known[container_type].concat(fileObj.Name);
		this.messages.known["stat"][container_type].number++;
		
		if (!this.messages.known["stat"][container_type].ext[this._getExt(fileObj.Name)]) {
			this.messages.known["stat"][container_type].ext[this._getExt(fileObj.Name)] = 1;
		} else {
			this.messages.known["stat"][container_type].ext[this._getExt(fileObj.Name)]++;
		}
		this.messages.known.total++;

		this.analyzeMessage(globalContext, fileObj, container_type);
	};

	
	reset() {
		containers = [];
	};

	
	updateToDoList(fileObj, globalContext) {
		this.messages.unknown.number++;
		
		if (!this.messages.unknown.ext[this._getExt(fileObj.Name)]) {
			this.messages.unknown.ext[this._getExt(fileObj.Name)] = 1;
		} else {
			this.messages.unknown.ext[this._getExt(fileObj.Name)]++
		}
		
        var msg = msgs.getMessage(mc.TODO, 4);
        msg.file = path.join(globalContext.config.directories['xs1-src'], fileUtils.sanitizeFilename(fileObj.RunLocation));
		this.logMessage(msg);
	};

	
	formatMessages (filelist, containers) {
		var f_msg =
		{
			sum : []
		};
		f_msg.sum.push(
		{
		    name : "total",
		    number : filelist.length
		});
		for (var prop in this.messages.known.stat) {
			if (this.messages.known.stat[prop].number > 0) {
				var ret = this._extArraytoString(this.messages.known.stat[prop].ext);
				f_msg.sum.push(
				{
				    name : prop,
				    number : this.messages.known.stat[prop].number,
				    detail: ret.number
				});
			}
		}

		if (this.messages.unknown.number > 0) {
			var ret = this._extArraytoString(this.messages.unknown.ext);
			f_msg.sum.push(
			{
			    name : 'unknown',
			    detail : ret.number,
			    number: this.messages.unknown.number,
			});
		}

		f_msg.errors = this.messages.error;
		f_msg.warnings = this.messages.warning;
		f_msg.infos = this.messages.info;
		return f_msg;
	};


	_getExt(fileName) {
		if (fileName.lastIndexOf(".") < 0) {
			return "unknown";
		}
		return fileName.substring(fileName.lastIndexOf("."), fileName.length);
	}


	_extArraytoString(myObject) {
		var ret = "";
		var number = {};
		for ( var prop in myObject) {
			if(ret === "")
				ret +="['";
			number[prop] = myObject[prop];
			ret += prop + "' - " + myObject[prop] + (myObject[prop] > 1 ? " files" : "file") + ",  '"
		}
		if(ret === ""){
			ret = "none";
			return ret;
		}
		else{
			return {detail:ret.substring(0, ret.length - 2) + ' ]', number: number};
		}
	}

		
	getMessages() {
		return this.messages;
	}
	
	
}



module.exports = new InformationHandler();
