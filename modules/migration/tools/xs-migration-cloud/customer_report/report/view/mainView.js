function initSummaryView(parent_selector, baseId){
	//createAccordion
	
	var projectInfo = {names:[], values:[]};
	projectInfo.names.push("Project");
	projectInfo.values.push(main_data.project.name + " - " + main_data.project.version);
	projectInfo.names.push("Content");
	var contentText = "";
	contentText +=(main_data.task.dus.length + (main_data.task.dus.length > 1? " DUs, ": " DU, "));
	for(var i = 0; i < main_data.task.dus.length; i++){
		contentText += (main_data.task.dus[i].name + " (" + main_data.task.dus[i].vendor + ") -" + main_data.task.dus[i].version);
	}
	contentText += (", including " + main_data.task["packages"].length + " packages and " + main_data.sum[0].number + " objects")
	projectInfo.values.push(contentText);
	projectInfo.names.push("System");
	projectInfo.values.push(main_data.system.protocol + "://" + main_data.system.host + ":" + main_data.system.port +", SID:" + main_data.system.sid + ", version " + main_data.system.hana_version);
	projectInfo.names.push("HALM version");
	projectInfo.values.push(main_data.system.halm_version);
	projectInfo.names.push("migration tool");
	projectInfo.values.push(main_data["mig-tool-version"]);
	createProjectInfo("#" + baseId, "summary_projectInfo", projectInfo);
	createMigrationStep("#" + baseId, "summary_migrationSteps", main_data);
	//createDocsArea("#" + baseId, "summary_docs", main_data.task);
	//content of tabs
	
}

function createProjectInfo(parent_selector, my_id, data, options){
	createSection(parent_selector, my_id, "Project Information");
	createProjectInfoArea(parent_selector, my_id, data, options);
}

function createMigrationStep(parent_selector, my_id, data, options){
	createSection(parent_selector, my_id, "Migration Steps", CON_TEXT.Migration_Desc);
	createStepsArea("#" + my_id, "migration_steps_accordion", data);
	//createMigrationStepSection(parent_selector, my_id, data, options)
}

function createDocsArea(parent_selector, my_id, data, options){
	createSection(parent_selector, my_id, "How to find useful docs");
	if(data.docs && data.docs.link){
		for(var i = 0; i < data.docs.link.length; i++){
			createReferenceLinksArea("#" + my_id, my_id + "_link", data.docs.link[i], options);
		}
			
	}
	
	//createMigrationStepSection(parent_selector, my_id, data, options)
}