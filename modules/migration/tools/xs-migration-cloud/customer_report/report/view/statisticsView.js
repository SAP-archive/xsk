var statisticObject = null;
function initStatisticsView(parent_selector, baseId){
	//createAccordion
	if(!statisticObject)
		statisticObject = generateFileListObject();
	createSummaryInfo("#" + baseId , "statistics_projectInfo", statisticObject);
	/*createMigrationStep("#" + baseId + "_statisticsTab", "statistics_migrationSteps", fakeData);
	createDocsArea("#" + baseId + "_statisticsTab", "statistics_docs", fakeData)*/
	
}

function createSummaryInfo(parent_selector, my_id, data, options){
	createSection(parent_selector, my_id, "Generated Result");
	createStatisticsSumInfo("#" + my_id, my_id + "_infopanel", data.sum, options);
	for(var i = 0; i < statisticObject.detail.length; i ++){
		var containerData = statisticObject.detail[i];
		createContainerInfo("#" + my_id, my_id + "_container" + i + "_" + containerData.name, containerData, options);
	}
}
function createStatisticsSumInfo(parent_selector, my_id, data, options){
	var names = [];
	var values = [];
	for(var i = 0; i < data.length; i++){
		names.push(data[i].name);
		values.push(data[i].value);
	}
	createFormPanel(parent_selector, my_id, null, names, values, {width:"180px"});
}

function createContainerInfo(parent_selector, my_id, data, options){
	var containerTitle = data.name;
	containerTitle += (spaces(2) + "(" + data.number + space + "files)"); 
	createSection(parent_selector, my_id, containerTitle);
	createContainerInfoDetail("#" + my_id, my_id + "_detail", data.detail, options);
}

function createContainerInfoDetail(parent_selector, my_id, data, options){
	var setting = {width:"800px", columns:2};
	var names1 = [];
	var values1 = [];
	var names2 = [];
	var values2 = [];
	var s = true;
	for(var prop in data){
		if(s){
			s = false;
			names1.push(prop);
			values1.push(data[prop]);
		}
		else{
			s = true;
			names2.push(prop);
			values2.push(data[prop]);
		}
		
	}
	if(names1.length > names2.length){
		names2.push("&nbsp;");
		values2.push("&nbsp;");
	}
	$(parent_selector).append(generateTag("div", {id:my_id}));
	
	createFormPanel(parent_selector, my_id, null, names1, values1,{style:[{name:"float", value:"left"}]});
	createFormPanel(parent_selector, my_id + "_right", null, names2, values2, {table_style:[{name:"padding-left", value:"100px"}]});
	
}