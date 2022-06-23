/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
import { process } from "@dirigible/bpm"
import { repository as repositoryManager } from "@dirigible/platform"
import { MigrationTask } from "./task.mjs";
import { MigrationService } from "../api/migration-service.mjs";

export class UnzipToTemporaryFolder extends MigrationTask {
	execution = process.getExecutionContext();

	constructor() {
		super("FROM_LOCAL_ZIP", "FROM_LOCAL_ZIP_EXECUTED", "FROM_LOCAL_ZIP_FAILED");
	}

	run() {
		const userDataJson = process.getVariable(this.execution.getId(), 'userData');
		const userData = JSON.parse(userDataJson);
		let paths = userData.zipPath;

		userData["du"] = [];

		for (const path of paths) {
			console.log("Processing zip by path : " + path)
			let collection = repositoryManager.getCollection(path);

			let zipProjectName = collection.getName();

			const duObject = composeJson(zipProjectName);

			const workspaceName = userData.workspace;

			const migrationService = new MigrationService();
			try {
				const { projectNames, synonyms } = migrationService.generateSynonymsForProject(workspaceName, zipProjectName);
				duObject.projectNames = projectNames;
				duObject.synonyms = synonyms;
			} catch (err) {
				console.log(`Error generating synonyms for zip: ${err.message}`);
				console.log(err.stack)
			}


			userData.du.push(duObject);
		}
		process.setVariable(this.execution.getId(), 'userData', JSON.stringify(userData));

		function composeJson(projectName) {
			let duObject = {}
			duObject.ach = "";
			duObject.caption = "";
			duObject.lastUpdate = getFormattedDate()
			duObject.ppmsID = "";
			duObject.responsible = "";
			duObject.sp_PPMS_ID = "";
			duObject.vendor = "migration.sap.com";
			duObject.version = "";
			duObject.version_patch = "";
			duObject.version_sp = "";
			duObject.name = projectName;
			duObject.projectNames = [projectName];
			duObject.fromZip = true;
			duObject.synonyms = [];
			return duObject;
		}

		function getFormattedDate() {
			let date = new Date();
			let dateStr = date.getFullYear() + "-" +
				("00" + (date.getMonth() + 1)).slice(-2) + "-" +
				("00" + date.getDate()).slice(-2) + " " +
				("00" + date.getHours()).slice(-2) + ":" +
				("00" + date.getMinutes()).slice(-2) + ":" +
				("00" + date.getSeconds()).slice(-2);
			return dateStr
		}
	}

}

