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

		//     $scope.deleteSchedule = function (scheduleIndex) {
		//       console.log("Schedule to delete" + scheduleIndex);
		//       $scope.hdi.schedules.splice(scheduleIndex, 1);
		//       console.log("Schedule to delete" + scheduleIndex);
		//       $scope.save();
		//     }
		//     $scope.addScheduler = function () {
		//       var schedule = {
		//         "description": "",
		//         "xscron": "",
		//         "parameter": {}
		//       };
		//       $scope.hdi.schedules.push(schedule);
		//       $scope.save();
		//     };

		//     $scope.hoverIn = function () {
		//       this.hoverEdit = true;
		//     };

		//     $scope.hoverOut = function () {
		//       this.hoverEdit = false;
		//     };

		//     $scope.create = function () {
		//       let exists = $scope.roles.filter(function (e) {
		//         return e.name === $scope.entity.name;
		//       });
		//       if (exists.length === 0) {
		//         $scope.roles.push($scope.entity);
		//         toggleEntityModal();
		//       } else {
		//         $scope.error = "Role with a name [" + $scope.entity.name + "] already exists!";
		//       }
		//     };

		//     $scope.update = function (key, value) {
		//       console.log("UPDATE ", key, value);
		//       $scope.deleteParameter(scheduleIndex, oldKey)
		//       $scope.addParameter(key, value);
		//     };

		//     $scope.delete = function () {
		//       $scope.roles = $scope.roles.filter(function (e) {
		//         return e !== $scope.entity;
		//       });
		//       toggleEntityModal();
		//     };


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
				messageHub.post({data: $scope.file}, 'editor.file.saved');
				messageHub.post({data: 'File [' + $scope.file + '] saved.'}, 'status.message');
			} else {
				console.error('file parameter is not present in the request');
			}
		}

		function removeNull(array) {
			return array.filter(x => x !== null)
		};

		$scope.save = function () {
			contents = angular.toJson($scope.hdi);
			console.log(contents)
			saveContents(contents);
		};

		$scope.$watch(function () {
			let hdi = angular.toJson($scope.hdi);
			if (contents !== hdi) {
				messageHub.post({data: $scope.file}, 'editor.file.dirty');
			}
		});


		// $(function () {
		//   $('[data-toggle="tooltip"]').tooltip()
		// })
	});

