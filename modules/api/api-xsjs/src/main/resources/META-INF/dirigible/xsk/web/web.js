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
/*
 * HANA XS Classic Bridge for Request API
 */

var dRequest = require('http/v4/request');
var dResponse = require('http/v4/response');
var dUpload = require('http/v4/upload');
var http = require('xsk/http/http');
var session = require('xsk/session/session');
var zip = require('io/v4/zip');
var bytes = require("io/v4/bytes");

exports.TupelList = function (dArrayOfContents) {
  var internalDArrayOfContents = dArrayOfContents ? dArrayOfContents : [];
  syncWithContentsArray.call(this);

  this.get = function (name) {
    var requestedElement = internalDArrayOfContents.find(function (element) {
      return element.name === name;
    });
    return requestedElement ? requestedElement.value : undefined;
  };

  this.remove = function (name) {
    var length = internalDArrayOfContents.length;
    internalDArrayOfContents = internalDArrayOfContents.filter(function (element) {
      return element.name !== name;
    });

    if (this.length === length) {
      syncWithContentsArray.call(this);
      throw new Error("No tuple was removed");
    }
  };

  this.set = function (name, value, options) {
    let element = internalDArrayOfContents.find(function (element) {
      return element.name.toUpperCase() === name.toUpperCase();
    });
    if (element) {
      element.value = value;
      if (options) {
        assignOptionsToCookie(element, options)
      }
    } else {
      element = {};
      element.name = name;
      element.value = value;
      if (options) {
        assignOptionsToCookie(element, options);
      }
      internalDArrayOfContents.push(element);
    }
    syncWithContentsArray.call(this);
  };

  function assignOptionsToCookie(cookie, options) {
    cookie.path = options.path;
    cookie.domain = options.domain;
    cookie.expires = options.expires;
    cookie.secure = options.secure;
    cookie.httpOnly = options.httpOnly;
  }

  function syncWithContentsArray() {
    for (var i = 0; i < internalDArrayOfContents.length; i++) {
      this[i] = internalDArrayOfContents[i];
    }
    this.length = internalDArrayOfContents.length;
  }
};

EntityList = function (webEntitiesArray, parentClassName) {
  syncWithWebEntitiesArray.call(this);

  this.create = function () {
    if (parentClassName === '$.response' || parentClassName === '$.net.http.Request') {
      webEntitiesArray.push(new exports.WebEntityResponse());
      syncWithWebEntitiesArray.call(this);
    } else {
      throw new Error("Method only available for $.response and $.net.http.Request");
    }
  };

  function syncWithWebEntitiesArray() {
    for (var i = 0; i < webEntitiesArray.length; i++) {
      this[i] = webEntitiesArray[i];
    }

    this.length = webEntitiesArray.length;
  }
};

Body = function (bodyValue) {
  this.asArrayBuffer = function () {
    if (bodyValue) {
      return bodyValue;
    } else {
      return dRequest.getBytes();
    }
  };

  this.asString = function () {
    if (bodyValue) {
      return String.fromCharCode.apply(null, bodyValue);
    } else {
      return dRequest.getText();
    }
  };

  this.asWebRequest = function () {
    if (bodyValue) {
      // ToDo
    } else {
      throw new Error("Cannot be used on root request.")
    }
  };
};

exports.WebRequest = function (method, queryPath) {
  const XSJS_FILE_EXTENSION_LENGTH = 5;
  const XSJS_FILE_EXTENSION = ".xsjs";

  this.body = function () {
    var wXscBody = new Body();
    return wXscBody;
  }();

  this.contentType = dRequest.getContentType();

  this.cookies = function () {
    var dCookiesArray = dRequest.getCookies();
    var wXscTupelList = new exports.TupelList(dCookiesArray);
    return wXscTupelList;
  }();

  this.entities = function () {
    var dEntitiesArray = [];

    if (dRequest.getMethod() === "POST") {
      if (dUpload.isMultipartContent()) {
        var fileItems = dUpload.parseRequest();
        for (i = 0; i < fileItems.size(); i++) {
          var fileItem = fileItems.get(i);
          if (fileItem.isFormField()) {
            var requestEntity = new WebEntityRequest();
            requestEntity.setBody(fileItem);

            var fieldName = fileItem.getFieldName();
            var fieldValue = fileItem.getText();

            var itemHeaders = fileItem.getHeaders();
            var headerNames = itemHeaders.getHeaderNames();

            for (j = 0; j < headerNames.size(); j++) {
              var headerName = headerNames.get(j);
              var headerValue = itemHeaders.getHeader(headerName);
              requestEntity.headers.set(headerName, headerValue);
            }

            requestEntity.parameters.set(fieldName, fieldValue);
            requestEntity.contentType = fileItem.getContentType();

            dEntitiesArray.push(requestEntity);
          }
        }
      }
    }

    return new EntityList(dEntitiesArray);
  }();

  this.headers = function () {
    var dHeadersArray = [];
    var headerNamesArray = dRequest.getHeaderNames();
    headerNamesArray.forEach(element =>
        dHeadersArray.push(
            {
              "name": element,
              "value": dRequest.getHeader(element)
            }));

    var wXscTupelList = new exports.TupelList(dHeadersArray);
    return wXscTupelList;
  }();

  this.language = session.language;

  if (method) {
    this.method = method;
  } else if (dRequest.getMethod() === 'OPTIONS') {
    this.method = http.OPTIONS;
  } else if (dRequest.getMethod() === 'GET') {
    this.method = http.GET;
  } else if (dRequest.getMethod() === 'HEAD') {
    this.method = http.HEAD;
  } else if (dRequest.getMethod() === 'POST') {
    this.method = http.POST;
  } else if (dRequest.getMethod() === 'PUT') {
    this.method = http.PUT;
  } else if (dRequest.getMethod() === 'DEL') {
    this.method = http.DEL;
  } else if (dRequest.getMethod() === 'TRACE') {
    this.method = http.TRACE;
  } else if (dRequest.getMethod() === 'CONNECT') {
    this.method = http.CONNECT;
  } else if (dRequest.getMethod() === 'PATCH') {
    this.method = http.PATCH;
  }

  this.parameters = function () {
    var dArrayOfPairs = dRequest.getParameters();
    dArrayOfPairs = transformParametersObject(dArrayOfPairs);
    var wXscTupelList = new exports.TupelList(dArrayOfPairs);
    return wXscTupelList;
  }();

  this.path = function () {
    var dRequestURI = dRequest.getRequestURI();
    var filePlaceInURI = dRequestURI.search(XSJS_FILE_EXTENSION);
    return dRequestURI.substring(0, filePlaceInURI + XSJS_FILE_EXTENSION_LENGTH);
  }();

  if (queryPath) {
    this.queryPath = queryPath;
  } else {
    this.queryPath = function () {
      var dRequestURI = dRequest.getRequestURI();
      var filePlaceInURI = dRequestURI.search(XSJS_FILE_EXTENSION);
      return dRequestURI.substring(filePlaceInURI + XSJS_FILE_EXTENSION_LENGTH, dRequestURI.length);
    }();
  }

  this.contentType = dRequest.getContentType();

  this.setBody = function (body, index) {
    this.body = body;
  };

  function transformParametersObject(dParametersObject) {
    var arrayToReturn = [];
    Object.keys(dParametersObject).map(key => {
      for (var i = 0; i < dParametersObject[key].length; i++) {
        arrayToReturn.push({"name": key, "value": dParametersObject[key][i]});
      }
    });
    return arrayToReturn;
  };
};

exports.WebResponse = function () {
  var responseHeaders = getResponseHeaders();

  this.body = new Body();
  this.cacheControl;
  this.contentType;
  this.cookies = new exports.TupelList([]);
  this.entities = new EntityList([], "$.response");
  this.headers = function () {
    var wXscTupelList = new exports.TupelList(responseHeaders);
    return wXscTupelList;
  }();
  this.status; // from $.net.http

  this.followUp = function (followUpObject) {
    var {
      uri: uri,
      functionName: functionName,
      parameter: parameters
    } = {...followUpObject}

    if (uri && functionName) {
      try {
        var params = new Array();

        if (parameters && typeof parameters === 'object') {
          for (var param in parameters) {
            params.push(parameters[param]);
          }
        }

        var splitUri = uri.split(":");
        var pathToFile = splitUri[0].replace(".", "/");
        var fileName = splitUri[1];

        var parsedUri = pathToFile + "/" + fileName;

        var module = require(parsedUri);
        var func = module[functionName];
        func.apply(this, params);
      } catch (error) {
        throw new Error(error);
      }
    }
  };

  this.setBody = function (content) {
    if (this.contentType) {
      dResponse.setContentType(this.contentType);
    }

    if (this.status) {
      dResponse.setStatus(this.status);
    }

    syncHeaders();

    if (content instanceof Array && this.contentType === 'application/zip') {
      var parsedContent = JSON.parse(bytes.byteArrayToText(content));
      var outputStream = dResponse.getOutputStream();
      if (outputStream.isValid()) {
        try {
          var zipOutputStream = zip.createZipOutputStream(outputStream);
          for (let file in parsedContent) {
            zipOutputStream.createZipEntry(file);
            zipOutputStream.writeText(parsedContent[file]);
          }
        } finally {
          zipOutputStream.close();
        }
      }
    } else {
      dResponse.print(content);
      dResponse.flush();
      dResponse.close();
    }
  };

  function syncHeaders() {
    for (var headerId = 0; headerId < responseHeaders.length; headerId++) {
      var header = responseHeaders[headerId];
      dResponse.setHeader(header.name, header.value);
    }
  }

  function getResponseHeaders() {
    var dHeadersArray = [];
    var headerNamesArray = dResponse.getHeaderNames();
    headerNamesArray.forEach(element =>
        dHeadersArray.push(
            {
              "name": element,
              "value": dRequest.getHeader(element)
            }
        )
    );

    return dHeadersArray;
  }
};

WebEntityRequest = function () {
  this.body = new Body();
  this.contentType;
  this.entities = new EntityList([]);
  this.headers = new exports.TupelList([]);
  this.parameters = new exports.TupelList([]);

  this.setBody = function (body, index) {
    this.body = new Body(body.getBytes());
  };
};

exports.WebEntityResponse = function () {
  this.body = new Body();
  this.contentType;
  this.entities = new EntityList([]);
  this.headers = new exports.TupelList([]);

  this.setBody = function (body, index) {
    this.body = new Body(body.getBytes());
  };
};
