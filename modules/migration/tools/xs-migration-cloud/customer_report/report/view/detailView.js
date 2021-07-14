
function initDetailView(parent_selector, my_id, data, options){
	if ($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div",	{id : my_id,properties:[{name:"class", value:"ui-widget-content"}]}));
	createDetailContent("#" + my_id, my_id + "_content", data, options);
}
function initDetailView_standalone(parent_selector, my_id, data, options){
	if ($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div",	{id : my_id,properties:[{name:"class", value:"ui-widget-content"}]}));
	createDetailContent_standalone("#" + my_id, my_id + "_content", data, options);
}

function createDetailContent_standalone(parent_selector, my_id, data, options){
	if ($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div",	{id : my_id,properties:[{name:"class", value:"ui-widget-content"}]}));
	var tabName = null;
	var tabID = null;
	var step = data.steps[options.step];
	tabName = ("Step " + (parseInt(options.step) + 1) + ": " + step.name);
	tabID = (my_id + "_tabs");
	$("#" + my_id).append(generateTag(my_id + "_tabs"));
    if(!options){
        options = {};
    }
    options.desc = step.desc;
    options.standalone = true;
	createDetailSection(my_id + "_tabs", tabName, tabID, step, options);
	enableOpenHighlighter("FileOpen", true);
	//createDetailSectionTabs("#" + my_id, my_id + "_tabs", tabNames, tabIDs, options);
}

function createDetailContent(parent_selector, my_id, data, options){
	if ($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div",	{id : my_id,properties:[{name:"class", value:"ui-widget-content"}]}));
	var tabNames = [];
	var tabIDs = [];
	for(var i = 0; i < data.steps.length; i ++){
		var step = data.steps[i];
		tabNames.push("Step " + (i + 1) + ": " + step.name);
		tabIDs.push(my_id + "_tabs_sections_" + i);
	}
	createDetailSectionTabs("#" + my_id, my_id + "_tabs", tabNames, tabIDs, options);
	//createFilterArea(parent_selector, my_id, data, options);
	/*var list_area = createListArea(parent_selector, my_id, data, options);
	appendColumes(list_area.header, ["t0", "t1"]);*/
	for(var i = 0; i < data.steps.length; i ++){
		createDetailSection(my_id + "_tabs", tabNames[i], tabIDs[i], data.steps[i], options);
	}
	enableOpenHighlighter("FileOpen", true);
}

function createDetailSectionTabs(parent_selector, my_id, tabNames, tabIDs, options){
	$(function() {
		$("#" + my_id).addClass( "ui-tabs-vertical ui-helper-clearfix" );
		$( "#" + my_id + " li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
	  });
	createTabs(parent_selector, my_id, tabNames, tabIDs, options);
	
	
	/*<ul>
    <li><a href="#fragment-1"><span>One</span></a></li>
    <li><a href="#fragment-2"><span>Two</span></a></li>
    <li><a href="#fragment-3"><span>Three</span></a></li>
  </ul>*/
}

function createDetailSection(parent_selector, title, my_id, data, options){
	//createTitle("#" + my_id, my_id + "_title", data.name, options);
	if(options && options.standalone == true && Object.keys(data.messages).length == 0){
		window.location.href = data.link.url;
	}
	if ($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div",	{id : my_id}));
	generateDetailFromMessages("#" + my_id, title, my_id + "_content", data.messages, options);
}

function generateDetailFromMessages(parent_selector, title, my_id, messages, options){
	if ($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div",	{id : my_id}));
	$("#" + my_id).append(generateTag(Constant.title_m, {text:title}));
	$("#" + my_id).append(generateTag("p", {text:((options && options.desc) ? options.desc : "description area")}));
	if(!options){
		options = {};
	}
	options.style = {name:"style", value:"padding-left:20px"};
	if(messages.ERROR){
		createDetailSectionTitle(parent_selector, my_id + "_title", "Error" + " (" + messages.ERROR.length + ")", options);
		var values  = getDetailTableMessages(messages.ERROR);
		generateDetailTable(parent_selector, my_id + "table" + "_error", ["type", "category", "file"], values,  ["type", "category", "file"], options);
	}
	if(messages.WARNING){
		createDetailSectionTitle(parent_selector, my_id + "_title", "Warning" + " (" + messages.WARNING.length + ")", options);
		var values  = getDetailTableMessages(messages.WARNING);
		generateDetailTable(parent_selector, my_id + "table" + "_warning", ["type", "category", "file"], values,  ["type", "category", "file"], options);
	}
	if(messages.INFO){
		createDetailSectionTitle(parent_selector, my_id + "_title", "Info" + " (" + messages.INFO.length + ")", options);
		var values  = getDetailTableMessages(messages.INFO);
		generateDetailTable(parent_selector, my_id + "table" + "_info", ["type", "category", "file"], values,  ["type", "category", "file"], options);
	}
}

function getDetailTableMessages(messageArray){

	
	var values = [];
	for(var i = 0; i < messageArray.length; i ++){
		var msg = messageArray[i];
		var m= null;
		if(msg.message && (Object.prototype.toString.call( msg.message ) === '[object Array]') && msg.message.length > 1){
			m = msg.message[0];
			for(var iRep = 0; iRep < msg.message.length - 1; iRep++){
				var s = "{*" + iRep + "}";
				var myReg = RegExp(s, 'g');
				m = m.replace(myReg, msg.message[iRep + 1]);
			}
		}
		else{
			m = msg.message.toString();
		}
		if(msg.description){
			m += '<br>' + msg.description.toString();
		}
		var location = "";
		var highlightStr = "";
		if(msg.loc){
			if(msg.loc.length > 5){
				location += "<br>";
			}
			location += " (in line: ";
            if(Object.prototype.toString.call(msg.loc) === '[object Array]'){
                for(var index in msg.loc){
				    location += msg.loc[index].start.line;
				    highlightStr += msg.loc[index].start.line;
				    if(index != msg.loc.length - 1){
					   location += ", ";
					   highlightStr += ", ";
				    }
                }
            }
			else{
                location += msg.loc.start.line;
				highlightStr += msg.loc.start.line;
            }
			
			location += ") ";
		}
		var filetext =  ((!msg.file || msg.file == "")? "":("<a class='FileOpen' highlight = '" + highlightStr + "'><u>" + msg.file + "</u></a> " + location + "<br>"));
		values.push({type: msg.type, category: msg.category, file:filetext + m});
		
	}
	return values;
}

function generateDetailTable(parent_selector, my_id, columns, values, columnKeys, options){
	detail_area = createListArea(parent_selector, my_id, options);
		//list_area.header.parent().css("border-style", "solid");
	detail_area.header.parent().css("padding-left", "8px");
	detail_area.header.parent().css("padding-right", "40px");
		//list_area.header.parent().css("border-width", "1px");
		//list_area.header.parent().css("border-radius", "8px");
	/*var filter = {
			container: "all",
			extension: "all",
	};
	if(options && options.filter){
		filter = options.filter;
	}*/
	appendColumes(detail_area.header, columns);
	appendRecord(detail_area.body, values, columnKeys)
}

function createDetailSectionTitle(parent_selector, my_id, text, options){
	var para = {text:text};
	para.properties = [];
	if(options && options.style){
		para.properties.push(options.style);
	}
	var utext = generateTag("u", para);
	$(parent_selector).append(generateTag(Constant.title_m, {text:utext}));
}

function createDetailSectionTable(parent_selector, my_id, data, options){
	
}

function setSectionBorderStyle(my_id) {
	//list_area.header.parent().css("border-style", "solid");
	$("#" + my_id).css("padding-left", "8px");
	$("#" + my_id).css("padding-right", "40px");
	//list_area.header.parent().css("border-width", "1px");
	//list_area.header.parent().css("border-radius", "8px");
}
