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
/*
 * HANA XS Classic Bridge for Request API
 */

var dRequest = require('http/v4/request');
var dResponse = require('http/v4/response');
var http = require('xsk/http/http');

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
      return element.name === name;
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

EntityList = function () {
  this.get = function (name) {

  };
};

Body = function () {

  this.asArrayBuffer = function () {
    //ToDo
  };

  this.asString = function () {
    return dRequest.getText();
  };

  this.asWebRequest = function () {
    //ToDo
  };
};


exports.WebRequest = function () {
  const XSJS_FILE_EXTENSION_LENGTH = 5;
  const XSJS_FILE_EXTENSION = ".xsjs";

  this.body = function () {
    var wXscBody = new Body(dRequest);
    return wXscBody;
  }();

  this.contentType = dRequest.getContentType();

  this.cookies = function () {
    var dCookiesArray = dRequest.getCookies();
    var wXscTupelList = new exports.TupelList(dCookiesArray);
    return wXscTupelList;
  }();

  this.entities; //ToDo

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

  this.language; //ToDo

  if (dRequest.getMethod() === 'OPTIONS') {
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

  this.queryPath = function () {
    var dRequestURI = dRequest.getRequestURI();
    var filePlaceInURI = dRequestURI.search(XSJS_FILE_EXTENSION);
    return dRequestURI.substring(filePlaceInURI + XSJS_FILE_EXTENSION_LENGTH, dRequestURI.length);
  }();

  this.contentType = dRequest.getContentType();

  this.setBody = function (body, index) {
    //ToDo;
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
  this.body = new Body();
  this.cacheControl;
  this.contentType;
  this.cookies = new exports.TupelList([]);
  this.entities; //ToDo
  this.headers = function () {
    var dHeadersArray = [];
    var headerNamesArray = dResponse.getHeaderNames();
    headerNamesArray.forEach(element =>
        dHeadersArray.push(
            {
              "name": element,
              "value": dRequest.getHeader(element)
            }));

    var wXscTupelList = new exports.TupelList(dHeadersArray);
    return wXscTupelList;
  }();

  this.status; // from $.net.http

  this.followUp; //ToDo

  this.setBody = function (text) {
    if (this.contentType) {
      dResponse.setContentType(this.contentType);
    }

    if (this.status) {
      dResponse.setStatus(this.status);
    }

    dResponse.println(text);
    dResponse.flush();
    dResponse.close();
  };

  this.followUpObject; //ToDo
};

WebEntityRequest = function () {
  this.body = new Body();
  this.contentType;
  this.entities; //ToDo
  this.headers = new exports.TupelList([]);
  this.parameters = new exports.TupelList([]);
  this.setBody = function () {
    //ToDo;
  };

};

exports.WebEntityResponse = function () {
  this.body = new Body();
  this.contentType;
  this.entities; //ToDo
  this.headers = new exports.TupelList([]);
  this.setBody = function (body, index) {
    this.body = body;
  };

};
