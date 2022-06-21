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

angular.module('page', [])
	.controller('PageController', function ($scope) {

		let messageHub = new FramesMessageHub();
		let contents;

		function getResource(resourcePath) {
			let xhr = new XMLHttpRequest();
			xhr.open('GET', resourcePath, false);
			xhr.send();
			if (xhr.status === 200) {
				return xhr.responseText;
			}
		}

		function loadContents(file) {
			if (file) {
				return getResource('/services/v4/ide/workspaces' + file);
			}
			console.error('file parameter is not present in the URL');
		}

		function getViewParameters() {
			if (window.frameElement.hasAttribute("data-parameters")) {
				let params = JSON.parse(window.frameElement.getAttribute("data-parameters"));
				$scope.file = params["file"];
			} else {
				let searchParams = new URLSearchParams(window.location.search);
				$scope.file = searchParams.get('file');
			}
		}

		function load() {
			getViewParameters();
			contents = loadContents($scope.file);
			$scope.hdi = JSON.parse(contents);

		}

		load();

		$scope.addUser = function () {
			$scope.hdi.users.push("");
		}
		$scope.deleteUser = function (index) {
			delete $scope.hdi.users[index]
			$scope.hdi.users = removeNull($scope.hdi.users);
			$scope.save();
		}

		$scope.deleteDeploy = function (index) {
			delete $scope.hdi.deploy[index]
			$scope.hdi.deploy = removeNull($scope.hdi.deploy);
			$scope.save();
		}
		$scope.deleteUnDeploy = function (index) {
			delete $scope.hdi.undeploy[index]
			$scope.hdi.undeploy = removeNull($scope.hdi.undeploy);
			$scope.save();
		}
		$scope.showAdditionalTextField = function () {
			$scope.showTextField = true;
		}
		$scope.showUndeployPathField = function () {
			$scope.showUndeployField = true;
		}
		$scope.showUserField = function () {
			$scope.showUserInputFileds = true;
		}

		$scope.addDeployPath = function (event, path) {
			$scope.hdi.deploy.push(path);
			event.path = "";
			$scope.showTextField = false;
			$scope.save()
		}

		$scope.addUser = function (event, user) {
			$scope.hdi.users.push(user);
			event.user = "";
			$scope.showUserInputFileds = false;
			$scope.save()
		}
		$scope.addUnDeployPath = function (event, undeployPath) {
			$scope.hdi.undeploy.push(undeployPath);
			event.undeployPath = "";
			$scope.showUndeployField = false;
			$scope.save()
		}

		function saveContents(text) {
			console.log('Save called...');
			if ($scope.file) {
				let xhr = new XMLHttpRequest();
				xhr.open('PUT', '/services/v4/ide/workspaces' + $scope.file);
				xhr.onreadystatechange = function () {
					if (xhr.readyState === 4) {
						console.log('file saved: ' + $scope.file);
					}
				};
				xhr.send(text);
				messageHub.post({
					name: $scope.file.substring($scope.file.lastIndexOf('/') + 1),
					path: $scope.file.substring($scope.file.indexOf('/', 1)),
					contentType: 'application/json+hdi', // TODO: Take this from data-parameters
					workspace: $scope.file.substring(1, $scope.file.indexOf('/', 1)),
				}, 'ide.file.saved');
				messageHub.post({ message: `File '${$scope.file}' saved` }, 'ide.status.message');
			} else {
				console.error('file parameter is not present in the request');
			}
		}

		function removeNull(array) {
			return array.filter(x => x !== null)
		}

		$scope.save = function () {
			contents = angular.toJson($scope.hdi);
			console.log(contents)
			saveContents(contents);
		};

		messageHub.subscribe(
			function () {
				if (isFileChanged) {
					$scope.save();
				}
			},
			"editor.file.save.all",
		);

		messageHub.subscribe(
			function (msg) {
				let file = msg.data && typeof msg.data === 'object' && msg.data.file;
				let hdi = angular.toJson($scope.hdi);
				if (file && file === $scope.file && contents !== hdi)
					$scope.save();
			},
			"editor.file.save",
		);

		$scope.$watch(function () {
			let hdi = angular.toJson($scope.hdi);
			if (contents !== hdi) {
				messageHub.post({ resourcePath: $scope.file, isDirty: true }, 'ide-core.setEditorDirty');
			} else {
				messageHub.post({ resourcePath: $scope.file, isDirty: false }, 'ide-core.setEditorDirty');
			}
		});
	});

