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
let editorView = angular.module('hdbti-editor', []);

editorView.factory('$messageHub', [function () {
    let messageHub = new FramesMessageHub();
    let announceAlert = function (title, message, type) {
        messageHub.post({
            data: {
                title: title,
                message: message,
                type: type
            }
        }, 'ide.alert');
    };
    let announceAlertError = function (title, message) {
        announceAlert(title, message, "error");
    };
    let message = function (evtName, data) {
        messageHub.post({ data: data }, evtName);
    };
    let on = function (topic, callback) {
        messageHub.subscribe(callback, topic);
    };
    return {
        announceAlert: announceAlert,
        announceAlertError: announceAlertError,
        message: message,
        on: on
    };
}]);

editorView.directive('validateInput', () => {
    return {
        restrict: 'A',
        require: 'ngModel',
        scope: {
            regex: '@validateInput'
        },
        link: (scope, element, attrs, controller) => {
            controller.$validators.forbiddenName = value => {
                if (attrs.hasOwnProperty("id")) {
                    if (attrs["id"] === "table") {
                        let correctSchema = scope.$parent.validateSchema();
                        let correctTable = scope.$parent.validateTable(value);
                        scope.$parent.setSaveEnabled(correctSchema && correctTable);
                        return correctTable;
                    } else if (attrs["id"] === "schema") {
                        let correctTable = scope.$parent.validateTable();
                        let correctSchema = scope.$parent.validateSchema(value);
                        scope.$parent.setSaveEnabled(correctSchema && correctTable);
                        return correctSchema;
                    } else if (attrs["id"] === "filepath") {
                        return scope.$parent.validateFilepath(element, value, scope.regex);
                    } else {
                        return scope.$parent.validateInput(element, value, scope.regex);
                    }
                }
                return scope.$parent.validateInput(element, value, scope.regex);
            };
        }
    };
});

editorView.directive('uniqueField', () => {
    return {
        restrict: 'A',
        require: 'ngModel',
        scope: {
            regex: '@uniqueField'
        },
        link: (scope, element, attrs, controller) => {
            controller.$validators.forbiddenName = value => {
                let unique = true;
                let correct = RegExp(scope.regex, 'g').test(value);
                if (correct) {
                    if ("index" in attrs) {
                        for (let i = 0; i < scope.$parent.jsonData[scope.$parent.activeItemId].keys.length; i++) {
                            if (i != attrs.index) {
                                if (value === scope.$parent.jsonData[scope.$parent.activeItemId].keys[i].column) {
                                    unique = false;
                                    break;
                                }
                            }
                        }
                    } else if ("kindex" in attrs && "vindex" in attrs) {
                        for (let i = 0; i < scope.$parent.jsonData[scope.$parent.activeItemId].keys[attrs.kindex].values.length; i++) {
                            if (i != attrs.vindex) {
                                if (value === scope.$parent.$parent.jsonData[scope.$parent.activeItemId].keys[attrs.kindex].values[i]) {
                                    unique = false;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (correct && unique) {
                    element.removeClass("error-input");
                } else {
                    element.addClass('error-input');
                }
                scope.$parent.setSaveEnabled(correct && unique);
                return unique;
            };
        }
    };
});

editorView.controller('EditorViewController', ['$scope', '$http', '$messageHub', '$window', function ($scope, $http, $messageHub, $window) {
    let isFileChanged = false;
    const ctrlKey = 17;
    let ctrlDown = false;
    let isMac = false;
    let workspace = 'workspace'; // This needs to be replace with an API.
    let emptyHdbti = ["", "import=[]", "import=[];", "import=[{}]", "import=[{}];"];
    let tableValidationList = [
        { regex: '^[A-Za-z0-9_\\-$.]+::[A-Za-z0-9_\\-$.]+$', contains: '::' },
        { regex: '^[A-Za-z0-9_\\-$.]+$' }
    ];
    let schemaValidation = '^[A-Za-z0-9_\\-$.]+$';
    let csrfToken;
    let tableField = document.getElementById("table");
    let schemaField = document.getElementById("schema");
    $scope.schemaError = { hasError: false, msg: '' };
    $scope.tableError = { hasError: false, msg: '' };
    $scope.filepathError = {
        hasError: false,
        msg: 'Path can only contain letters (a-z, A-Z), numbers (0-9), hyphens ("-"), forward slashes ("/"), dots ("."), underscores ("_"), and dollar signs ("$")'
    };
    $scope.fileExists = true;
    $scope.saveEnabled = true;
    $scope.editEnabled = false;
    $scope.dataEmpty = true;
    $scope.dataLoaded = false;
    $scope.jsonData = [];
    $scope.activeItemId = 0;
    $scope.delimiterList = [',', '\\t', '|', ';', '#'];
    $scope.quoteCharList = ["'", "\"", "#"];

    $scope.openFile = function () {
        if ($scope.checkResource($scope.jsonData[$scope.activeItemId].file)) {
            let msg = {
                "file": {
                    "name": $scope.jsonData[$scope.activeItemId].name,
                    "path": `/${workspace}${$scope.jsonData[$scope.activeItemId].file}`,
                    "type": "file",
                    "contentType": "text/csv",
                    "label": $scope.jsonData[$scope.activeItemId].name
                },
                "extraArgs": {
                    "header": $scope.jsonData[$scope.activeItemId].header,
                    "delimiter": $scope.jsonData[$scope.activeItemId].delimField,
                    "quotechar": $scope.jsonData[$scope.activeItemId].delimEnclosing
                }
            };
            $messageHub.message('ide-core.openEditor', msg);
        }
    };

    $scope.validateTable = function (value = null) {
        if (value === null) value = tableField.value;
        let correct = false;
        if (value) {
            for (let i = 0; i < tableValidationList.length; i++) {
                if (tableValidationList[i].contains) {
                    if (value.includes(tableValidationList[i].contains)) {
                        correct = RegExp(tableValidationList[i].regex, 'g').test(value);
                        break;
                    }
                } else {
                    correct = RegExp(tableValidationList[i].regex, 'g').test(value);
                    break;
                }
            }
        }
        $scope.showTableError(!correct);
        return correct;
    };

    $scope.validateSchema = function (value = null) {
        if (value === null) value = schemaField.value;
        let correct = false;
        if (!value) {
            if (tableField.value.trim().length > 0 && tableField.value.includes('::')) {
                correct = true;
                $scope.showSchemaError(false);
            } else {
                correct = false;
                $scope.showSchemaError(
                    true,
                    'Schema must be specified either in the table name ("schemaName::tableName") or in the schema field.'
                );
            }
            $scope.setSaveEnabled(correct);
            return correct;
        }
        correct = RegExp(schemaValidation, 'g').test(value);
        $scope.showSchemaError(!correct);
        $scope.setSaveEnabled(correct);
        return correct;
    };

    $scope.validateFilepath = function (element, value, regex) {
        let correct = false;
        if (value) {
            correct = RegExp(regex, 'g').test(value);
        }
        $scope.fileExists = true;
        if (correct) {
            element.removeClass("error-input");
            $scope.filepathError.hasError = false;
        }
        else {
            element.addClass('error-input');
            $scope.filepathError.hasError = true;
        }
        $scope.setSaveEnabled(correct);
        return correct;
    };

    $scope.validateInput = function (element, value, regex) {
        let correct = false;
        if (!value) return correct;
        correct = RegExp(regex, 'g').test(value);
        if (correct) element.removeClass("error-input");
        else element.addClass('error-input');
        $scope.setSaveEnabled(correct);
        return correct;
    };

    $scope.showTableError = function (hasError, msg) {
        $scope.tableError.hasError = hasError;
        if (msg !== undefined) {
            $scope.tableError.msg = msg;
        } else $scope.tableError.msg = 'Table can only contain letters (a-z, A-Z), numbers (0-9), hyphens ("-"), dots ("."), underscores ("_"), and dollar signs ("$"). Two colons ("::") are permitted only when table name contains schema ("schemaName::tableName").';
        if (hasError) tableField.classList.add('error-input');
        else tableField.classList.remove('error-input');
    };

    $scope.showSchemaError = function (hasError, msg = '') {
        $scope.schemaError.hasError = hasError;
        if (msg !== undefined) {
            $scope.schemaError.msg = msg;
        } else $scope.schemaError.msg = 'Schema can only contain letters (a-z, A-Z), numbers (0-9), hyphens ("-"), dots ("."), underscores ("_"), and dollar signs ("$")';
        if (hasError) schemaField.classList.add('error-input');
        else schemaField.classList.remove('error-input');
    };

    $scope.inputsHaveErrors = function () {
        let inputs = document.getElementsByClassName("form-control");
        for (let i = 0; i < inputs.length; i++) {
            if (inputs[i].classList.contains('error-input')) return true;
        }
        return false;
    };

    $scope.setSaveEnabled = function (enabled) {
        if (enabled && !$scope.inputsHaveErrors()) $scope.saveEnabled = true;
        else $scope.saveEnabled = false;
    };

    $scope.setEditEnabled = function (enabled) {
        if (enabled != undefined) {
            $scope.editEnabled = enabled;
        } else {
            $scope.editEnabled = !$scope.editEnabled;
        }
    };

    $scope.addNew = function () {
        let newCsv = {
            "name": "Untitled",
            "visible": true,
            "table": "",
            "schema": "",
            "file": "",
            "header": false,
            "useHeaderNames": false,
            "delimField": ";",
            "delimEnclosing": "\"",
            "distinguishEmptyFromNull": true,
            "keys": []
        };
        // Clean search bar
        $scope.filesSearch = "";
        $scope.filterFiles();
        $scope.jsonData.push(newCsv);
        $scope.activeItemId = $scope.jsonData.length - 1;
        $scope.dataEmpty = false;
        $scope.setEditEnabled(false);
        $scope.fileChanged();
    };

    $scope.getFileName = function (str, canBeEmpty = true) {
        if (canBeEmpty) {
            return str.split('\\').pop().split('/').pop();
        }
        let title = str.split('\\').pop().split('/').pop();
        if (title) return title;
        else return "Untitled";
    };

    $scope.fileSelected = function (id) {
        if (!$scope.inputsHaveErrors()) {
            $scope.setEditEnabled(false);
            $scope.fileExists = true;
            $scope.activeItemId = id;
        }
    };

    $scope.isDelimiterSupported = function (delimiter) {
        return $scope.delimiterList.includes(delimiter);
    };

    $scope.isQuoteCharSupported = function (quoteChar) {
        return $scope.quoteCharList.includes(quoteChar);
    };

    $scope.delimiterChanged = function (delimiter) {
        $scope.jsonData[$scope.activeItemId].delimField = delimiter;
        $scope.fileChanged();
    };

    $scope.quoteCharChanged = function (quoteChar) {
        $scope.jsonData[$scope.activeItemId].delimEnclosing = quoteChar;
        $scope.fileChanged();
    };

    $scope.addValueToKey = function (column) {
        let entry_num = 1;
        for (let i = 0; i < $scope.jsonData[$scope.activeItemId].keys.length; i++) {
            if ($scope.jsonData[$scope.activeItemId].keys[i].column === column) {
                for (let k = 0; k < $scope.jsonData[$scope.activeItemId].keys[i].values.length; k++) {
                    let num = getNumber(
                        $scope.jsonData[$scope.activeItemId].keys[i].values[k].replace("NEW_ENTRY_", '')
                    );
                    if (!isNaN(num) && num >= entry_num) {
                        entry_num = num + 1;
                    }
                }
                $scope.jsonData[$scope.activeItemId].keys[i].values.push(`NEW_ENTRY_${entry_num}`);
                break;
            }
        }
        $scope.fileChanged();
    };

    $scope.removeValueFromKey = function (columnIndex, valueIndex) {
        $scope.jsonData[$scope.activeItemId].keys[columnIndex].values.splice(valueIndex, 1);
        $scope.fileChanged();
    };

    $scope.addKeyColumn = function () {
        let num = 1;
        for (let i = 0; i < $scope.jsonData[$scope.activeItemId].keys.length; i++) {
            if ($scope.jsonData[$scope.activeItemId].keys[i].column === `NEW_ENTRY_${num}`) {
                num++;
            }
        }
        $scope.jsonData[$scope.activeItemId].keys.push(
            {
                "column": `NEW_ENTRY_${num}`,
                "values": []
            }
        );
        $scope.fileChanged();
    };

    $scope.removeKeyColumn = function (index) {
        $scope.jsonData[$scope.activeItemId].keys.splice(index, 1);
        $scope.fileChanged();
    };

    $scope.save = function () {
        if (isFileChanged && $scope.saveEnabled) {
            $scope.checkResource($scope.jsonData[$scope.activeItemId].file);
            $scope.jsonData[$scope.activeItemId].name = $scope.getFileName($scope.jsonData[$scope.activeItemId].file, false);
            parseJsonToHdbti(JSON.stringify($scope.jsonData, cleanForOutput, 2));
        }
    };

    $scope.deleteFile = function () {
        // Clean search bar
        $scope.jsonData.splice($scope.activeItemId, 1);
        $scope.setEditEnabled(false);
        $scope.fileExists = true;
        if ($scope.jsonData.length > 0) {
            $scope.dataEmpty = false;
            $scope.activeItemId = $scope.jsonData.length - 1;
        } else {
            $scope.dataEmpty = true;
            $scope.activeItemId = 0;
        }
        $scope.fileChanged();
    };

    $scope.filterFiles = function () {
        if ($scope.filesSearch) {
            for (let i = 0; i < $scope.jsonData.length; i++) {
                if ($scope.jsonData[i].name.toLowerCase().includes($scope.filesSearch.toLowerCase())) {
                    $scope.jsonData[i].visible = true;
                } else {
                    $scope.jsonData[i].visible = false;
                }
            }
        } else {
            for (let i = 0; i < $scope.jsonData.length; i++) {
                $scope.jsonData[i].visible = true;
            }
        }
    };

    $scope.fileChanged = function () {
        isFileChanged = true;
        $messageHub.message('editor.file.dirty', $scope.file);
    };

    $scope.keyDownFunc = function ($event) {
        if (
            ctrlDown &&
            String.fromCharCode($event.which).toLowerCase() == 's'
        ) {
            $event.preventDefault();
            if (isFileChanged)
                $scope.save();
        }
    };

    angular.element($window).bind("keyup", function (/*$event*/) {
        ctrlDown = false;
    });

    angular.element($window).bind("keydown", function ($event) {
        if (isMac && "metaKey" in $event && $event.metaKey)
            ctrlDown = true;
        else if ($event.keyCode == ctrlKey)
            ctrlDown = true;
    });

    $scope.checkResource = function (resourcePath) {
        if (resourcePath != "") {
            let xhr = new XMLHttpRequest();
            xhr.open('HEAD', `/services/v4/ide/workspaces/${workspace}${resourcePath}`, false);
            xhr.setRequestHeader('X-CSRF-Token', 'Fetch');
            xhr.send();
            if (xhr.status === 200) {
                csrfToken = xhr.getResponseHeader("x-csrf-token");
                $scope.fileExists = true;
            } else {
                $scope.fileExists = false;
            }
        } else {
            $scope.fileExists = false;
        }
        return $scope.fileExists;
    };

    function getNumber(str) {
        if (typeof str != "string") return NaN;
        let strNum = parseFloat(str);
        // use type coercion to parse the _entirety_ of the string (`parseFloat` alone does not do this) and ensure strings of whitespace fail
        let isNumber = !isNaN(str) && !isNaN(strNum);
        if (isNumber) return strNum;
        else return NaN;
    }

    /**
     * Used for removing some keys from the object before turning it into a string.
     */
    function cleanForOutput(key, value) {
        if (key === "name" || key === "visible") return undefined;
        else if (key === "schema" && value === "") return undefined;
        return value;
    }

    /**
     * Sends hdbti file, receives json file
     */
    function parseHdbti(hdbti) {
        let blob = new Blob([hdbti], { type: "application/hdbti" });
        let formData = new FormData();
        formData.append('file', blob);

        $http.post(`/services/v4/parse/hdbti?location="${$scope.file}"`, formData, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(function (response) {
            $scope.jsonData = response.data;
            for (let i = 0; i < $scope.jsonData.length; i++) {
                $scope.jsonData[i]["name"] = $scope.getFileName($scope.jsonData[i].file, false);
                $scope.jsonData[i]["visible"] = true;
            }
            $scope.activeItemId = 0;
            if ($scope.jsonData.length > 0) {
                $scope.dataEmpty = false;
            } else {
                $scope.dataEmpty = true;
            }
            $scope.dataLoaded = true;
        }, function (response) {
            console.error(response);
            $messageHub.announceAlertError(
                "Error while loading file",
                response.data.error.message
            );
        });
    }

    /**
     * Sends json in text form, receives hdbti string
     */
    function parseJsonToHdbti(json) {
        $http.post("/services/v4/parse/csvim", json, {
            headers: { 'Content-Type': 'application/json' }
        }).then(function (response) {
            saveContents(response.data);
        }, function (response) {
            if (response.data === "Missing schema property") {
                $scope.showSchemaError(
                    true,
                    'Schema must be specified either in the table name ("schemaName::tableName") or in the schema field.'
                );
                $scope.setSaveEnabled(false);
            }
            $messageHub.announceAlertError(
                "Error while saving the file",
                "Please look at the console for more information"
            );
            console.error(response);
        });
    }

    function loadFileContents() {
        let searchParams = new URLSearchParams(window.location.search);
        $scope.file = searchParams.get('file');
        if ($scope.file) {
            $http.get('/services/v4/ide/workspaces' + $scope.file)
                .then(function (response) {
                    let data = response.data;
                    if (
                        emptyHdbti.includes(
                            data.replaceAll(" ", '').replaceAll('\n', '').replaceAll('\t', '')
                        )
                    ) {
                        data = "import = [];";
                    }
                    parseHdbti(data);
                }, function (response) {
                    if (response.data) {
                        $messageHub.announceAlertError(
                            "Error while loading file",
                            response.data.error.message
                        );
                        if ("error" in response.data) {
                            console.error("Loading file:", response.data.error.message);
                        }
                    } else {
                        console.error("Error loading file.");
                    }
                });
        } else {
            console.error('file parameter is not present in the URL');
        }
    }

    function saveContents(text) {
        console.log('Save called...');
        if ($scope.file) {
            let xhr = new XMLHttpRequest();
            xhr.open('PUT', '/services/v4/ide/workspaces' + $scope.file);
            xhr.setRequestHeader('X-Requested-With', 'Fetch');
            xhr.setRequestHeader('X-CSRF-Token', csrfToken);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    console.log('file saved: ' + $scope.file);
                }
            };
            xhr.send(text);
            isFileChanged = false;
            $messageHub.message('editor.file.saved', $scope.file);
            $messageHub.message('status.message', 'File [' + $scope.file + '] saved.');
        } else {
            console.error('file parameter is not present in the request');
        }
    }

    function checkPlatform() {
        let platform = window.navigator.platform; // This needs improvement
        let macosPlatforms = ['Macintosh', 'MacIntel', 'MacPPC', 'Mac68K', 'darwin', 'Mac', 'mac', 'macOS'];
        if (macosPlatforms.indexOf(platform) !== -1) isMac = true;
    }

    function getCurrentWorkspace() { // This needs to be replaced with an API
        let storedWorkspace = JSON.parse(localStorage.getItem('DIRIGIBLE.workspace') || '{}');
        if ('name' in storedWorkspace) workspace = storedWorkspace.name;
    }

    getCurrentWorkspace();
    checkPlatform();
    loadFileContents();

}]);