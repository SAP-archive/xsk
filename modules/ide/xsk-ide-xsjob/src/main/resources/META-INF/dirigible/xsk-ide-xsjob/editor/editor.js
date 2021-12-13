/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
angular.module('page', [])
  .controller('PageController', function ($scope) {

    let messageHub = new FramesMessageHub();
    let contents;
    let scheduleIndex;
    let oldKey;

    $scope.openNewDialog = function (index) {
      $scope.actionType = 'new';
      $scope.entity = {};
      toggleEntityModal();
      console.log("LOG Parameter will be add to shedule with index " + index)
      scheduleIndex = index;

    };

    $scope.openEditDialog = function (index, key, value) {
      console.log("OpenEditDialog", index, key, value);
      $scope.actionType = 'update';
      oldKey = key;
      scheduleIndex = index;
      $scope.entity = { name: key, description: value };
      toggleEntityModal();
    };

    function toggleEntityModal() {
      $('#entityModal').modal('toggle');
      $scope.error = null;
    }

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

    function load() {
      let searchParams = new URLSearchParams(window.location.search);
      $scope.file = searchParams.get('file');
      contents = loadContents($scope.file);
      $scope.job = JSON.parse(contents);
    }

    load();

    $scope.addParameter = function (key, value) {
      console.log("LOG Index ", scheduleIndex, "  KEY. ", key, "  Value ", value)
      $scope.job.schedules[scheduleIndex].parameter[key] = value;
      toggleEntityModal();
    }
    $scope.deleteParameter = function (index, key) {
      console.log("Shedule: " + index + " Key " + key);
      delete $scope.job.schedules[index].parameter[key];
      $scope.save();
    }

    $scope.deleteSchedule = function (scheduleIndex) {
      console.log("Schedule to delete" + scheduleIndex);
      $scope.job.schedules.splice(scheduleIndex, 1);
      console.log("Schedule to delete" + scheduleIndex);
      $scope.save();
    }
    $scope.addScheduler = function () {
      var schedule = {
        "description": "",
        "xscron": "",
        "parameter": {}
      };
      $scope.job.schedules.push(schedule);
      $scope.save();
    };

    $scope.hoverIn = function () {
      this.hoverEdit = true;
    };

    $scope.hoverOut = function () {
      this.hoverEdit = false;
    };

    $scope.create = function () {
      let exists = $scope.roles.filter(function (e) {
        return e.name === $scope.entity.name;
      });
      if (exists.length === 0) {
        $scope.roles.push($scope.entity);
        toggleEntityModal();
      } else {
        $scope.error = "Role with a name [" + $scope.entity.name + "] already exists!";
      }
    };

    $scope.update = function (key, value) {
      console.log("UPDATE ", key, value);
      $scope.deleteParameter(scheduleIndex, oldKey)
      $scope.addParameter(key, value);
    };

    $scope.delete = function () {
      $scope.roles = $scope.roles.filter(function (e) {
        return e !== $scope.entity;
      });
      toggleEntityModal();
    };


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
        messageHub.post({ data: $scope.file }, 'editor.file.saved');
        messageHub.post({ data: 'File [' + $scope.file + '] saved.' }, 'status.message');
      } else {
        console.error('file parameter is not present in the request');
      }
    }

    $scope.save = function () {
      contents = angular.toJson($scope.job);
      saveContents(contents);
    };

    $scope.$watch(function () {
      let xsjob = angular.toJson($scope.job);
      if (contents !== xsjob) {
        messageHub.post({ data: $scope.file }, 'editor.file.dirty');
      }
    });


    $(function () {
      $('[data-toggle="tooltip"]').tooltip()
    })
  });

