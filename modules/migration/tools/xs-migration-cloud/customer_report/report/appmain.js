var Views = {
		main:"main",
		detail:"detail",
		list:"filelist",
		statistics:"statistics",
};

var tool_name = "XS Migration Report " + main_data.project.name;

function ifIE(){
	var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE ");

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)){
    	return true;
    }
    return false;
}

function clearContent(contentId){
	$("#" + contentId).empty();
	$("#" + contentId).remove();
}

function compatibilityCode(){
	if (typeof(UserAgentInfo) != 'undefined' && !window.addEventListener) 
    {
        UserAgentInfo.strBrowser=1; 
    } 
}

function start_the_fun()
{
	//test code only
	compatibilityCode();
	var baseId = "mainView";
	var parent_selector = "#content";
	
	var qstr = getQueryString();
	if(!qstr.view || qstr.view == "main"){
		//global view
		createTitle(parent_selector, "mainview_title", tool_name, {icon:"migration/report/icons/sap.gif", icon_width: "100px", icon_height: "50px"});
		createTabs(parent_selector, "mainview_tabs", ["Summary", "File statistics", "File List", "Steps Detail"], [baseId + "_summaryTab", baseId + "_statisticsTab", baseId + "_FilesTab", baseId + "_detailTab"]);
		//summary view
		initSummaryView("#mainview_tabs", baseId + "_summaryTab");
		initStatisticsView("#mainview_tabs", baseId + "_statisticsTab");
		initFileListView("#mainview_tabs", baseId + "_FilesTab",{newtab:false});
		initDetailView("#mainview_tabs", baseId + "_detailTab", main_data, {newtab: false});
	}
	else if(qstr.view == "detail"){
		createTitle(parent_selector, "stepdetail_title", tool_name, {icon:"migration/report/icons/sap.gif", icon_width:"100px", icon_height:"50px" });
		initDetailView_standalone(parent_selector, "stepdetail_body", main_data, {newtab: true, step:qstr.step});
	}
	else if(qstr.view == "filelist"){
		createTitle(parent_selector, "filelist_title", tool_name, {icon:"migration/report/icons/sap.gif", icon_width:"100px", icon_height:"50px" });
		initFileListView(parent_selector, "filelist_body", {newtab:true});
	}
	if(ifIE()){
		//
		createDialog(parent_selector, "_IE_NotSupportDlg", {
			title:"Browser Not Fully Supported", 
			width: 500,
			content: "We recomment to use another Web Browser. We still have some issues for showing all content correctly in IE. If you are checking only very generic information, it might not be a big issue. However if you would like to investigate issues, it is very unlikely to work with IE."
			}, null);
	}
}

function getQueryString() {
	
	var queryStringParameters = {};
	var queryString = window.location.search.substring(1);
	var parameters = queryString.split("&");

	for (var i = 0; i < parameters.length; i++) {

		var keyValuePair = parameters[i].split("=");
		var parameterKey = keyValuePair[0];
		var parameterValue = keyValuePair[1];
		var queryStringParameter = queryStringParameters[parameterKey];


		if (typeof queryStringParameter === "undefined") {
			queryStringParameters[parameterKey] = decodeURIComponent(parameterValue);
			
		} else if (typeof queryStringParameter === "string") {
			queryStringParameters[parameterKey] = [queryStringParameters[parameterKey], decodeURIComponent(parameterValue)];

		} else if (Array.isArray(queryStringParameter)){
			queryStringParameters[parameterKey].push(decodeURIComponent(parameterValue));
		}
		
	}

	return queryStringParameters;
}

