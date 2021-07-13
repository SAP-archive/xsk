function generateTag(tagName, options){
	var ret = "";
	ret += "<" + tagName;
	if(options && options.id){
		ret += " id= " + "'" + options.id + "'";
	}
	if(options && options.properties){
		for(var i = 0; i < options.properties.length; i++){
			ret += " " + options.properties[i].name + "= " + "'" + options.properties[i].value + "' ";
		}
		
	}
	ret += ">";
	if(options && options.text){
		ret += options.text;
	}
	ret +=  "</" + tagName + ">";
	return ret;
}

var CON_TEXT = {
		Migration_Desc: "The objects from the provided delivery units have been exported from the system, analyzed, migrated, and have been written into an XS Advanced folder structure. Follow the steps shown below in order to complete the migration."
};

var Constant = {
	title_l : "h3",
	title_m : "h4",
	title_s : "h5",
};

function load_local_file(filename, callback) {
	$.ajax(
	{
	    url : filename.replace("/\"/g", ""),
	    dataType : "text",
	}).done(function(data) {
		//console.log(data);
		if (callback) {
			callback(data);
		}
	}).fail(function(jqXHR, textStatus, errorThrown) {
        alert("Unable to read file " + filename + ": " + errorThrown);
		// TODO
	});
}

function enableOpenHighlighter(classname, unbind){
	//var load_local_file("\highlighter\shCore.js", callback, false);
	if(unbind)
		$( "." + classname).unbind( "click" );
	$("." + classname).click(function() {
		var that = this;
		var highlightStr = $(this).attr("highlight");
		load_local_file(that.text.replace(/\\/g, "\/"), function(codelines){
			var lines = [];
			var myCode = "";
			myCode = "<plaintext>" + codelines;//.replace(/\\n/g, '<br>');
			var tagBaseId = "line";
			
			//$.each(lines, function(index, item) {
			//	myCode += ("<pre id='" + tagBaseId + (index + 1) + "' class='WrapLine' ><a name='line" + (index + 1) + "'class='fileLine_1'>" + item + "</a></pre>");
			//});
		    var t = window.open("report/content.html", '_blank', 'noopener,noreferrer,resizable,scrollbars');
            t.filename = that.text;
		    var ua = window.navigator.userAgent;
	        var msie = ua.indexOf("MSIE ");

	        if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)){
            	//IE workaround
            	setTimeout(function(){checkReadyState(function(){
    			    var tag = t.document.getElementById('codecontent');
    			    tag.innerHTML = myCode;
    			    t.focus();
            	}, t)}, 1000);   
            	
            }
            else{
            	$(t).on("load", function(){
    		    	var tag = t.document.getElementById('codecontent');
    			    tag.innerHTML = myCode;
    			    t.focus();
    		    });
            }
		});
	    
	    
	});
}


function checkReadyState(callback, currentWindow) {
    if (currentWindow.document.readyState !== "complete") {
        setTimeout(function(){checkReadyState(callback, currentWindow)}, 100);
    }
    else {
        callback();
    }
}
function generateFileListObject() {

	if (!main_filelist) {
		return null;
	}
	var ret =
	{
	    sum :
	    [
	     {
	        name : "total generated files",
	        value : main_filelist.length,
	    }
	    ],
	    detail : [

	    {
	        name : "xsjs",
	        number : 0,
	        detail : {}
	    },
	    {
	        name : "db",
	        number : 0,
	        detail : {}
	    },
	    /*
		 * { name : "odata", number : 0, detail:{} },
		 */
	    {
	        name : "web",
	        number : 0,
	        detail : {}
	    },
	    {
	        name : "todo",
	        number : 0,
	        detail : {}
	    }, ]

	};
	for (var i = 0; i < main_filelist.length; i++) {
		var file = main_filelist[i];
		var found = false;
		for (var index_ret = 0; index_ret < ret.detail.length; index_ret++) {
			var container = ret.detail[index_ret];
			if (file.container == container.name) {
				found = true;
				container.number++;
				if (container.detail["." + file.ext]) {
					container.detail["." + file.ext]++;
				} else {
					container.detail["." + file.ext] = 1;
				}
				break;
			}
		}
		if (found === false) {
			var temp =
			{
			    name : file.container,
			    number : 1,
			    detail : {

			    },
			};
			temp.detail["." + file.ext] = 1;

			ret.detail.push(temp);
		}
	}
	var name = "content containers";
	var values = [];
	for(var i = 0; i < ret.detail.length; i++){
		if(ret.detail[i].number > 0){
			 values.push('"'+ret.detail[i].name+'"');
		}
	}
	ret.sum.push({name:name, value:values.toString()});
	return ret;
}
