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

    $scope.initJSONcontent = function () {
      if (!('allowMethods' in $scope.data.cors))
        $scope.data.cors = {
          ...$scope.data.cors,
          allowMethods: $scope.allAllowedMethods,
          allowOrigin: ["*", "*.sap.com"],
          maxAge: 3600,
          allowHeaders: [''],
          exposeHeaders: ['']
        };
      if ($scope.data.headers.customHeaders && $scope.data.headers.customHeaders.length)
        $scope.data.headers.customHeaders = $scope.data.headers.customHeaders.map((x) => {
          return {
            ...x,
            thevalue: x.value.substr(0, 10) == 'ALLOW-FROM' ? 'ALLOW-FROM' : x.value,
            url: x.value.substr(0, 10) == 'ALLOW-FROM' ? x.value.substr(11) : ''
          }
        })
    }

    function load() {
      getViewParameters();
      contents = loadContents($scope.file);
      $scope.data = JSON.parse(contents);
      $scope.anonymous_connection_allowed = $scope.data.anonymous_connection ? true : false;
      $scope.allAllowedMethods = ["GET", "POST", "HEAD", "OPTIONS"];
      $scope.initJSONcontent();

    }

    load();

    function updateObjectNestedProp(obj, value, propPath) {
      const [head, ...rest] = propPath.split('.');
      if (!rest.length) {
        obj[head] = value;
      } else {
        updateObjectNestedProp(obj[head], value, rest.join('.'));
      }
    }

    function getNestedValue(obj, path) {
      return path.split('.').reduce(function (prev, curr) {
        return prev[curr];
      }, obj || this);
    }

    $scope.deleteInList = function (path, index) {
      let newKeyValue = getNestedValue($scope.data, path).filter((_val, num) => num != index);
      if (!newKeyValue.length) newKeyValue.push('');
      updateObjectNestedProp(
        $scope.data,
        newKeyValue,
        path);
    }

    $scope.addInList = function (path, value) {
      let newKeyValue = getNestedValue($scope.data, path);
      newKeyValue.push(value);
      updateObjectNestedProp($scope.data,
        newKeyValue,
        path);
    }

    $scope.trigCorsMethod = function (method) {
      if (!$scope.data.cors.allowMethods.includes(method))
        $scope.data.cors.allowMethods.push(method);
      else
        $scope.data.cors.allowMethods = $scope.data.cors.allowMethods.filter((x) => x != method);
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
        messageHub.post({ data: $scope.file }, 'editor.file.saved');
        messageHub.post({ data: 'File [' + $scope.file + '] saved.' }, 'status.message');
      } else {
        console.error('file parameter is not present in the request');
      }
    }

    $scope.createSaveContent = function () {
      let contentsJSON = JSON.parse(angular.toJson($scope.data));
      if (contentsJSON.cors.enabled) {
        contentsJSON.cors.allowOrigin = contentsJSON.cors.allowOrigin.filter((x) => x.trim());
        contentsJSON.cors.allowHeaders = contentsJSON.cors.allowHeaders.filter((x) => x.trim());
        contentsJSON.cors.exposeHeaders = contentsJSON.cors.exposeHeaders.filter((x) => x.trim());
      } else {
        contentsJSON.cors = { enabled: false };
      }
      if (!contentsJSON.headers.enabled) {
        contentsJSON.headers = { enabled: false };
      } else {
        contentsJSON.headers.customHeaders = contentsJSON.headers.customHeaders.map((x) => {
          return { name: x.name, value: x.thevalue + (x.thevalue == 'ALLOW-FROM' ? " " + x.url : '') };
        }).filter((x) => x.name.trim());
      }
      contentsJSON.authorization = contentsJSON.authorization.filter((x) => x.trim());
      contentsJSON.mime_mapping = contentsJSON.mime_mapping.filter((x) => x.extension.trim() && x.mimetype.trim());
      contentsJSON.rewrite_rules = contentsJSON.rewrite_rules.filter((x) => x.source.trim() && x.target.trim());
      return JSON.stringify(contentsJSON, null, 4);
    }

    $scope.previewJson = function () {
      $scope.formToJSON = $scope.createSaveContent();
    }

    $scope.save = function () {
      let content = $scope.createSaveContent();
      saveContents(content);
    };


    $(function () {
      $('[data-toggle="tooltip"]').tooltip()
    })
  });

