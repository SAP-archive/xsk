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
			let filesDetails = [];
			let localFiles = []
			console.log("Processing zip by path : " + path)
			let resources = repositoryManager.getCollection(path);
			let zipProjectName = resources.getName();

			getAllFiles(resources);

			function getAllFiles(resources) {
				getResourcesFromFOlder(resources)
			}


			function getResourcesFromFOlder(dir) {
				if (!dir.getResourcesNames().isEmpty()) {
					for (const nameRes of dir.getResourcesNames()) {
						localFiles.push(dir.getResource(nameRes))
					}
				}
				if (!dir.getCollectionsNames().isEmpty()) {
					for (const folderName of dir.getCollectionsNames()) {
						getResourcesFromFOlder(dir.getCollection(folderName))
					}
				}
			}

			for (const localFile of localFiles) {
				const repositoryPath = localFile.getPath();
				const runLocation = repositoryPath.split("/").slice(4).join("/");
				const relativePath = repositoryPath.split("/").slice(5).join("/");

				let fileDetails = {
					repositoryPath: repositoryPath,
					relativePath: "/" + relativePath,
					projectName: zipProjectName,
					runLocation: "/" + runLocation
				};

				filesDetails.push(fileDetails)
			}

			userData.du.push(composeJson(zipProjectName, filesDetails))
		}
		process.setVariable(this.execution.getId(), 'userData', JSON.stringify(userData));

		function composeJson(projectName, filesDetails) {
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
			duObject.name = projectName
			duObject.locals = filesDetails;
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

