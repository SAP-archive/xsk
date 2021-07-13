var space = "&nbsp;";
function spaces(n){
	var ret = "";
	for(var i = 0; i < n; i ++){
		ret += space;
	}
	return ret;
}
function createSection(parent_selector, my_id, title, desc){
	var root = $(generateTag("div", {id: my_id})).appendTo(parent_selector);
	root.append(generateTag(Constant.title_m, {text:/*"<u>" + */title/* + "</u>"*/, }));
	if(desc){
		root.append(generateTag("p", {text:desc}));
	}
}

function createTabs(parent_selector,my_id, tabNames, tabIDs,options){
	
	$(parent_selector).append(generateTag("div", {id: my_id}));
	
	createTabItems("#" + my_id, tabNames, tabIDs, options);
	
	$("#" + my_id).tabs({
		activate: function( event, ui ) {
			if(ui.newPanel.attr("id").indexOf("FilesTab") >= 0)
				$("#" + ui.newPanel.attr("id")).css("display","inline-block");
		}
	});
}

function createDialog(parent_selector, my_id, data, options){
	if($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div", {id: my_id, properties:[{name:"title", value: data.title}]}));
	$("#" + my_id).append(generateTag("p",{
		text: generateTag("span", {properties:[{name:"class", value: "ui-icon ui-icon-alert"}, {name:"style", value:"float:left; margin:0 7px 20px 0;"}]}) + data.content
	}));
	$(function() {
	    $( "#" + my_id ).dialog({
	      modal: true,
	      width:data.width,
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );
	        }
	      }
	    });
	  });
}

function createTabItems(parent_selector, tabNames, tabIDs, options){
	$(parent_selector).append(generateTag("ul"));
	for(var i = 0; i < tabNames.length; i ++){
		$(generateTag("li")).appendTo(parent_selector + " > ul")
		/*$(parent_selector + " > ul > li")*/.append(generateTag("a", {
			text: tabNames[i], 
			properties: [
			             {
			            	 name:"href",
			            	 value:"#" + tabIDs[i]}
			             ]
		}));
		if(options && options.contents){
			$(parent_selector).append(options.contents[i]);
		}
		else{
			$(generateTag("div", {id: tabIDs[i]})).appendTo(parent_selector);
		}
		//test only
		
	}
	
}



function createStepsArea(parent_selector,my_id, data,options){
	if($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div", {id: my_id, properties:[{name:"style",value:/*"width:80%; "*/"display: inline-block;"}]}));
	createStepItems("#" + my_id, data, options);
		
	//$("#" + my_id);
}

function createMigrationStepSection(parent_selector,my_id, data, options){
	if ($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div",
		{
			id : my_id
		}));
	$("#" + my_id).css("border-style", "solid");
	$("#" + my_id).css("padding-left", "40px");
	$("#" + my_id).css("padding-right", "40px");
	$("#" + my_id).css("border-width", "1px");
	$("#" + my_id).css("border-radius", "8px");

	data.link.text = "more information";
	createDescriptionArea("#" + my_id, my_id + "_desc", data.desc, data.link, options);
	//createReferenceLinksArea("#" + my_id, my_id + "_reflink", data.link, options);
	createMigrationStepListArea("#" + my_id, my_id + "_detail", data.list,  options);
}

function createDescriptionArea(parent_selector,my_id, desc, linkObj, options){
	if($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div", {id: my_id}));
	var alltext = desc /*+ "&nbsp;" + generateTag("a", {text:(linkObj.text? linkObj.text:linkObj.url), properties:[{name:"target", value:"_blank"},{name:"href", value: linkObj.url}]})*/;
	$("#" + my_id).append(generateTag("p", {text: /*"Description:&nbsp;&nbsp;" + */alltext}));
}

/*function createReferenceLinksArea(parent_selector,my_id, data, options){
	if($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div", {id: my_id}));
	$("#" + my_id).append(generateTag("p", {text: "<u>" + (data.name?data.name:"reference") +"</u>: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + data.info + "&nbsp;&nbsp;" + generateTag("a", {text:(data.text? data.text:data.url), properties:[{name:"target", value:"_blank"},{name:"href", value: data.url}]})}));
}*/

/**
 * Function returns a sanitized value for the passed attribute.
 *
 * The string can be encoded using jQuery html() method by getting
 * the HTML content of the element (pseudo div block in our case).
 *
 * @param stringToSanitize - value which shall be encoded and sanitized
 * @returns {*|jQuery}
 */
function sanitizeHtmlString(stringToSanitize) {
	return $('<div/>').text(stringToSanitize).html();
}

function createMigrationStepListArea(parent_selector,my_id, data, options){
	if($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div", {id: my_id}));
	var texts = [];
	var values = [];
	for(var i = 0; i < data.length; i++){
		texts.push(sanitizeHtmlString(data[i].text));
		values.push(data[i].value);
	}
	
	var infotext = getStepInfoText(texts, values, options);
	var  currentURL = window.location.protocol + "//" + window.location.hostname + (window.location.hostname != ""?":":"") + window.location.port + (window.location.port!=""?"/":"") + window.location.pathname + "?view=detail&step=" + options.step;
	var linkText = infotext + generateTag("a", {id: my_id + "listlink",text:"Detail List", properties:[{name:"href", value: currentURL/*TODO*/},{ name:"target", value:"_blank"}]});
	$("#" + my_id).append(generateTag("p", {text:linkText/*, properties:[{name:"style", value:"margin-left : 150px"}]*/}));
}
function getStepInfoText(names, values, options){
	/*if($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div", {id: my_id}));*/
	var ret = "";
	for(var i = 0; i < names.length; i ++){
		if(i != 0){
			ret += ",&nbsp;&nbsp;&nbsp;&nbsp;"
		}
		var cat = names[i].toLowerCase() + (values[i] > 1? "s":"");
		ret += (values[i] + "&nbsp;" + cat);
	}
	if(ret != "")
		ret += "&nbsp;&nbsp;&nbsp;&nbsp;"
	return ret;
}
function createStepItems(parent_selector, data, options){

	
	//var itemNames = data.stepNames;
	var steps = null; 
	if(data && data.steps){
		steps = data.steps;
	}
	else{
		return;
	}
	
	var itemIDs = data.stepContentIds;
	for(var i = 0; i < steps.length; i ++){
		var step = steps[i];
		step.id = parent_selector.slice(1, parent_selector.length) + "_step" + i;
		$(parent_selector).append(generateTag(Constant.title_s, {text: /*"<u>" + */"Step " + (i + 1) + " -  " + step.name/* + "</u>"*/, id: step.id + "_title"}));
		//$(parent_selector).append(generateTag("div"));
		$(generateTag("div", {id: step.id})).appendTo(parent_selector);
		createMigrationStepSection("#" + step.id, step.id + "_section", step, {step:i});
	}
}

function createFormPanel(parent_selector,my_id, columns, names, values, options){
	if($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div", {id: my_id}));
	if(options && options.style){
			for(var i = 0; i < options.style.length; i ++){
				$("#" + my_id).css(options.style[i].name, options.style[i].value);
			}
	}
	//$("#" + my_id).append(generateTag("hr"));
	var tableRoot = $(generateTag("table", {id: my_id + "_table",properties:[]})).appendTo("#" + my_id);
	if(options && options.table_style){
		for(var i = 0; i < options.table_style.length; i ++){
			$("#" + my_id + "_table").css(options.table_style[i].name, options.table_style[i].value);
		}
}
	var cNum = 1;
	if(columns && columns > 0){
		cNum = columns;
	}
	
	$(generateTag("col", {properties:[{name: "width", value: ((options&&options.width)? options.width:"140px")}]})).appendTo("#" + my_id + "_table");
	for(var i = 0; i < names.length; i++){
		var tr = $(generateTag("tr")).appendTo("#" + my_id + "_table");
		tr.append(generateTag("td", {text:names[i] + ((names[i].trim() == "" || names[i].trim() == space)? "": ":")/*, properties:[{name:"style", value:"text-align:right"}]*/}));
		
		tr.append(generateTag("td", {text:values[i]}));
	}
}

function createProjectInfoArea(parent_selector,my_id, data, options){
	//testStr = "<table style='width:80%'>  <tr> <td>Jill</td> <td>Smith</td><td>50</td></tr>	  <tr><td>Eve</td> <td>Jackson</td><td>94</td></tr> <tr> <td>John</td><td>Doe</td><td>80</td></tr>	</table>";
	if($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div", {id: my_id}));
	createFormPanel("#" + my_id, my_id + "_form", 4, data.names, data.values, options);
}

function createTitle(parent_selector, my_id, title, options){
	$(parent_selector).append("<div id = '" + my_id + "'></div>");
	var iconHTML = "";
	if(options && options.icon){
		iconHTML+= "<img src='" + options.icon + (options.icon_width?( "' width='" + options.icon_width + "") :"") + (options.icon_height?( "' height='" + options.icon_height + "") :"") + "'/>"
	}
	
	var titleTSize = "h1";
	if(options && options.size){
		titleTSize = options.size;
	}
	var titleText = generateTag(titleTSize, {text: iconHTML +  title});
	if(options && options.addText){
		titleText += options.addText;
	}
		
	$("#" + my_id).append(titleText);
}

function createListArea(parent_selector, my_id, options){
	if($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div", {id: my_id}));
	$("#" + my_id).append(generateTag("table", {id: my_id + "_t"}));
	var theader = $(generateTag("thead", {id: my_id + "_t_hd", properties:[{name:"class", value:"ui-widget-header"}]})).appendTo("#" + my_id + "_t");
	var tbody = $(generateTag("tbody", {id: my_id + "_t_bd", properties:[{name:"class", value:"ui-widget-content"}]})).appendTo("#" + my_id + "_t");
	return {header: theader, body: tbody};
}

function appendColumes(headerPtr, columns){
	//test
	var tr = $(generateTag("tr")).appendTo(headerPtr);
	for(var i = 0; i < columns.length; i++){
		tr.append(generateTag("th",{text:columns[i]}));
	}
}

function appendRecord(tbodyPtr, records, columnKeys){
	
	for(var i = 0; i < records.length; i ++){
		var rec = records[i];
		var tr = $(generateTag("tr", {properties:[{name:"class", value:"ui-widget-content"}]})).appendTo(tbodyPtr);
		for(var j = 0; j < columnKeys.length; j ++){
			var key = columnKeys[j];
			tr.append(generateTag("td", {text: rec[key]}));
		}
	}
	

}
