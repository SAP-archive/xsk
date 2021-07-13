var filelist_view_columns = [ "container", "file name"];
var fileFilter = {
		container: "all",
		extension: "all",
};
var filter_container = {
		"all": true,
		"xsjs":false,
		"web":false,
		"db":false,
		"todo":false,
		"delete":false,
};
var filterExt = [];
function initFileListView(parent_selector, baseId, options){
	if(!statisticObject)
		statisticObject = generateFileListObject();
	if($("#" + baseId).length == 0)
		$(parent_selector).append(generateTag("div", {id: baseId,properties:[{name:"class", value:"ui-widget-content"}]}));
	createFilterArea("#" + baseId, baseId + "_filters", statisticObject.detail, options);
	/*var v = $("#" + baseId).css("display") + "-" + "inline-block"
	$("#" + baseId).css("display", v);*/
	$("#" + baseId).append("<hr>");
	createFileListContent("#" + baseId, baseId + "_content", options);
	
}
function createFilterArea(parent_selector, my_id, data, options){
	if($("#" + my_id).length == 0)
		$(parent_selector).append(generateTag("div", {id: my_id}));
	if(options && options.newtab && options.newtab == true){
		//list_area.header.parent().css("border-style", "solid");
		$("#" + my_id).css("padding-left", "8px");
		$("#" + my_id).css("padding-right", "40px");
		//list_area.header.parent().css("border-width", "1px");
		//list_area.header.parent().css("border-radius", "8px");
	}
	//container filter
	var myContainerFilter = [{text:"All", key: "all"}];
	filterExt = [{text:"All", key: "all"}];
	for(var i = 0; i < data.length; i ++){
		myContainerFilter.push({text: data[i].name, key: data[i].name});
		for(var prop in data[i].detail){
			filterExt.push({text:prop, key: prop, container: data[i].name});
		}
	}

	var f1 = {text: "filter for container: "};
	f1.change = function(){
		containerFilterUpdate(my_id);
	};
	var f2 = {text: "filter for extension: "};
	f2.change = function(){extensionFilterUpdate(my_id);}
	createFilter("#" + my_id, my_id + "_con", myContainerFilter, f1);
	createFilter("#" + my_id, my_id + "_ext", filterExt, f2);
	
	//ext filter
	
}

function containerFilterUpdate(my_id) {
	var newExtFilter = [];
	var selectedItem = $("#" + my_id + "_con_section_list option:selected");
	if (selectedItem.length == 1) {
		fileFilter.container = selectedItem.attr("key");
		if (fileFilter.container != "all") {
			newExtFilter = [
			{
			    text : "All",
			    key : "all"
			} ];
			for (var i = 0; i < filterExt.length; i++) {
				if (filterExt[i].container == fileFilter.container) {
					newExtFilter.push(filterExt[i]);
				}
			}
		}
		else{
			newExtFilter = filterExt;
		}
		fileFilter.extension = "all";
		list_area.body.empty();
		$("#" + my_id + "_ext_section").empty();
		$("#" + my_id + "_ext_section").remove();
		var f2 = {text: "filter for extension: "};
		f2.change = function() {
			extensionFilterUpdate(my_id);
		}
		createFilter("#" + my_id, my_id + "_ext", newExtFilter, f2);
		appendCreatedFileList(list_area.body, main_filelist, fileFilter);
	} else {
		//error happens
	}
}

function extensionFilterUpdate(my_id){
	var selectedItem = $( "#" + my_id + "_ext_section_list option:selected");
	if(selectedItem.length == 1){
		fileFilter.extension = selectedItem.attr("key");
		list_area.body.empty();
		appendCreatedFileList(list_area.body, main_filelist, fileFilter);
	}
	else{
		//error happens
	}
}

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

function createFilter(parent_selector, my_id, data, options){
	var p = $(generateTag("p", {id:my_id + "_section"})).appendTo(parent_selector);

	p.append(generateTag("lable", {text: sanitizeHtmlString(options.text) ,properties:[{name:"for", value:my_id + "_section_list"}]}));
	p.append(generateTag("select", {id:my_id + "_section_list", properties:[{name:"name", value:my_id + "_section_list"}]}));
	for(var i = 0; i < data.length; i ++){
		$("#" + my_id + "_section_list").append(generateTag("option", {text: sanitizeHtmlString(data[i].text), properties:[{name:"key", value:data[i].key}]}));
	}
	
	$("#" + my_id + "_section_list").change(options.change);
}

var list_area = null;

function createFileListContent(parent_selector, my_id, options){
	
	list_area = createListArea(parent_selector, my_id, options);
	if(options && options.newtab && options.newtab == true){
		//list_area.header.parent().css("border-style", "solid");
		list_area.header.parent().css("padding-left", "8px");
		list_area.header.parent().css("padding-right", "40px");
		//list_area.header.parent().css("border-width", "1px");
		//list_area.header.parent().css("border-radius", "8px");
	}
	var filter = {
			container: "all",
			extension: "all",
	};
	if(options && options.filter){
		filter = options.filter;
	}
	appendColumes(list_area.header, filelist_view_columns);
	appendCreatedFileList(list_area.body, main_filelist, filter);
}

function appendCreatedFileList(tbodyPtr, list, filter){
	var container = (filter.container == "all"? null: filter.container);
	var ext = (filter.extension == "all"? null: filter.extension);
	var inconsist = false;
	if(container && ext){
		for(var i = 0; i < filterExt.length; i++){
			if(filterExt[i].key == ext){
				if(filterExt[i].container != container){
					inconsist = true;
				}
				else{
					inconsist = false;
					break;
				}
			}
		}
		if(inconsist){
			ext = null;
		}
	}
	for(var i = 0; i < list.length; i++){
		var fileObj = list[i];
		var match = true;
		if(container && list[i].container != container){
			match = false;
		}
		if(ext && list[i].name.indexOf(ext, list[i].name.length - ext.length) == -1){
			match = false;
		}
		if(match){
			var tr = $(generateTag("tr", {properties:[{name:"class", value:"ui-widget-content"}]})).appendTo(tbodyPtr);
			tr.append(generateTag("td", {text: fileObj.container}));
			var textArea = generateTag("a", {text: generateTag("u", {text:fileObj.name}), properties:[{name:"class", value:"FileListView"}]});
			tr.append(generateTag("td", {text:textArea}));
		}
	}
	enableOpenHighlighter("FileListView", true);
}

function createFileListFilterArea(parent_selector, my_id, data, options){
	
}

function createFileListSearchArea(parent_selector, my_id, data, options){
	
}