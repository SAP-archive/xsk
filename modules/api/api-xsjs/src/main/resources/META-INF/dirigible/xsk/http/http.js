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
var dClient = require("http/v4/client");
var repositoryManager = require("platform/v4/repository");
var streams = require("io/v4/streams");
var web = require("xsk/web/web");

exports.OPTIONS = 0;
exports.GET = 1;
exports.HEAD = 2;
exports.POST = 3;
exports.PUT = 4;
exports.DEL = 5;
exports.TRACE = 6;
exports.CONNECT = 7;
exports.PATCH = 8;

exports.CONTINUE = 100;
exports.SWITCH_PROTOCOL = 101;
exports.OK = 200;
exports.CREATED = 201;
exports.ACCEPTED = 202;
exports.NON_AUTHORITATIVE = 203;
exports.NO_CONTENT = 204;
exports.RESET_CONTENT = 205;
exports.PARTIAL_CONTENT = 206;
exports.MULTIPLE_CHOICES = 300;
exports.MOVED_PERMANENTLY = 301;
exports.FOUND = 302;
exports.SEE_OTHER = 303;
exports.NOT_MODIFIED = 304;
exports.USE_PROXY = 305;
exports.TEMPORARY_REDIRECT = 307;
exports.BAD_REQUEST = 400;
exports.UNAUTHORIZED = 401;
exports.PAYMENT_REQUIRED = 402;
exports.FORBIDDEN = 403;
exports.NOT_FOUND = 404;
exports.METHOD_NOT_ALLOWED = 405;
exports.NOT_ACCEPTABLE = 406;
exports.PROXY_AUTH_REQUIRED = 407;
exports.REQUEST_TIMEOUT = 408;
exports.CONFLICT = 409;
exports.GONE = 410;
exports.LENGTH_REQUIRED = 411;
exports.PRECONDITION_FAILED = 412;
exports.REQUEST_ENTITY_TOO_LARGE = 413;
exports.REQUEST_URI_TOO_LONG = 414;
exports.UNSUPPORTED_MEDIA_TYPE = 415;
exports.REQUESTED_RANGE_NOT_SATISFIABLE = 416;
exports.EXPECTATION_FAILED = 417;
exports.INTERNAL_SERVER_ERROR = 500;
exports.NOT_YET_IMPLEMENTED = 501;
exports.BAD_GATEWAY = 502;
exports.SERVICE_UNAVAILABLE = 503;
exports.GATEWAY_TIMEOUT = 504;
exports.HTTP_VERSION_NOT_SUPPORTED = 505;

var SET_COOKIE_HEADER = "Set-Cookie";
var CONTENT_LENGTH_HEADER = "Content-Length";

exports.readDestination = function (destinationPackage, destinationName) {
  const DESTINATION_EXTENSION = ".xshttpdest";
  var destinationFullFileName = destinationName + DESTINATION_EXTENSION;
  var validPackage = destinationPackage.split('.').join('/') + '/';
  var resource = repositoryManager.getResource('/registry/public/' + validPackage + destinationFullFileName);
  var resourceByteArray = resource.getContent();
  var resourceInputStream = streams.createByteArrayInputStream(resourceByteArray);

  var destResourceContent = resourceInputStream.readText();
  resourceInputStream.close();

  var destResourceLineArray = destResourceContent.split(";");

  var destination = new exports.Destination();

  destResourceLineArray.forEach(destResourceLine => {
    var splitKeyValueArray = destResourceLine.split("=");

    var processedDestValue = processDestValue(splitKeyValueArray[1]);
    var destKey = splitKeyValueArray[0].trim();
    destination[destKey] = processedDestValue;

  });

  return destination;
};

exports.Client = function () {

  var clientResponse;
  var connectionTimeout;

  this.request = function (param1, param2, param3) {
    if (typeof param1 === "string") {
      sendRequestWithHttpMethod(param1, param2, param3);
      return;
    } else if (typeof param2 === "string") {
      sendRequestObjToUrl(param1, param2, param3);
      return;
    }

    sendRequestObjToDestination(param1, param2);
  };

  this.close = function () {
    // Empty. Called automatically inside HttpClientFacade
  };

  this.getResponse = function () {
    return getWebResponseByDirigibleResponse(clientResponse);
  };

  this.setTrustStore = function (trustStore) {
    // TODO No dirigible functionality
  };

  this.setTimeout = function (timeout) {
    connectionTimeout = timeout;
  };

  function sendRequestObjToDestination(requestObj, destination) {
    var fullUrl = destination.host + destination.pathPrefix + requestObj.queryPath;

    destination.headers = [];
    var requestHeaders = getHeadersArrFormTupel(requestObj.headers);
    requestHeaders = removeContentLengthHeaderIfPost(requestHeaders, requestObj.method);
    destination.headers = requestHeaders;
    addCookieToHeadersFromTupel(requestObj.cookies, destination.headers);
    addTimeoutToOptions(destination);

    if (requestObj.contentType) {
      destination.contentType = requestObj.contentType;
    }

    if (requestObj.parameters !== undefined) {
      fullUrl = addQueryParametersToUrl(fullUrl, requestObj.parameters);
    }

    clientResponse = executeRequest(fullUrl, requestObj.method, destination, requestObj.body);
  }

  function sendRequestObjToUrl(requestObj, url, proxy) {
    var fullUrl = url + requestObj.queryPath;

    var options = {};
    if (proxy) {
      options = proxyUrlToOptionsObject(proxy);
    }

    options.headers = [];
    var requestHeaders = getHeadersArrFormTupel(requestObj.headers);
    requestHeaders = removeContentLengthHeaderIfPost(requestHeaders, requestObj.method);
    options.headers = requestHeaders;
    addCookieToHeadersFromTupel(requestObj.cookies, options.headers);
    addTimeoutToOptions(options);

    if (requestObj.contentType) {
      options.contentType = requestObj.contentType;
    }

    if (requestObj.parameters !== undefined) {
      fullUrl = addQueryParametersToUrl(fullUrl, requestObj.parameters);
    }

    clientResponse = executeRequest(fullUrl, requestObj.method, options, requestObj.body);
  }

  function sendRequestWithHttpMethod(requestHttpMethod, url, proxy) {
    var options = {};

    if (proxy) {
      options = proxyUrlToOptionsObject(proxy);
    }

    addTimeoutToOptions(options);
    clientResponse = executeRequest(url, requestHttpMethod, options);
  }

  function proxyUrlToOptionsObject(proxyUrl) {
    var options = {};

    var proxyHostPortPair = proxyUrl.split(":");
    options.proxyHost = proxyHostPortPair[0].trim();
    options.proxyPort = Number.parseInt(proxyHostPortPair[1]).trim();

    return options;
  }

  function addTimeoutToOptions(options) {
    options.connectionRequestTimeout = connectionTimeout;
  }

  function executeRequest(url, requestMethod, options, requestBody) {
    if (requestBody) {
      if(typeof requestBody === 'string' || requestBody instanceof String)
        options.text = requestBody;
      else
        options.text = JSON.stringify(requestBody);
    }

    switch (requestMethod) {
      case exports.GET:
        return dClient.get(url, options);
      case exports.POST:
        return dClient.post(url, options);
      case exports.PUT:
        return dClient.put(url, options);
      case exports.DEL:
        return dClient.delete(url, options);
      default:
        throw new Error("Not supported request method.");
    }
  }

  function getWebResponseByDirigibleResponse(dResponse) {
    var webResponse = new web.WebEntityResponse();
    webResponse.headers = new web.TupelList(dResponse.headers);
    webResponse.status = dResponse.statusCode;
    webResponse.cookies = getTupelCookieFromDirigibleResponse(dResponse);
    webResponse.setBody(dResponse.text);

    return webResponse;
  }

  function getHeadersArrFormTupel(headersTupel) {
    if (!headersTupel) {
      return;
    }

    var headersArr = [];
    for (var i = 0; i < headersTupel.length; i++) {
      headersArr.push(headersTupel[i]);
    }

    return headersArr;
  }

  function getUrlParametersFromTupel(tupelParameters) {
    var queryParameterPairs = [];
    for (var i = 0; i < tupelParameters.length; i++) {
      var name = tupelParameters[i].name;
      var value = tupelParameters[i].value;

      if (typeof value === "array") {
        value = value.join(",");
      }

      var queryPair = name + "=" + value;

      queryParameterPairs.push(queryPair);
    }

    return queryParameterPairs.join("&");
  }

  function addQueryParametersToUrl(url, parametersTupel) {
    if (parametersTupel.length === 0) {
      return url;
    }

    var urlQueryParameters = getUrlParametersFromTupel(parametersTupel);
    return url + "?" + urlQueryParameters;
  }

  function addCookieToHeadersFromTupel(tupelCookie, headersArray) {
    if (tupelCookie === undefined && tupelCookie.length === 0) {
      return;
    }

    var cookiesArray = [];
    for (var i = 0; i < tupelCookie.length; i++) {
      var cookieName = tupelCookie[i].name;
      var cookieValue = tupelCookie[i].value;

      cookiesArray.push(cookieName + "=" + cookieValue + ";");
    }

    headersArray.push({name: "Cookie", value: cookiesArray.join(" ")});
  }

  function getTupelCookieFromDirigibleResponse(dResponse) {
    var headers = dResponse.headers;
    var cookieObjArray = [];
    headers.forEach(header => {
      if (header.name === SET_COOKIE_HEADER) {
        var cookieKeyValue = header.value.split(";")[0].trim();
        var cookieKeyValueArray = cookieKeyValue.split("=");
        var cookieObj = {name: cookieKeyValueArray[0], value: cookieKeyValueArray[1]};
        cookieObjArray.push(cookieObj);
      }
    });

    return new web.TupelList(cookieObjArray);
  }

  function removeContentLengthHeaderIfPost(headers, method) {
    if (method === exports.POST) {
      headers = headers.filter(header => header.name.toUpperCase() !== CONTENT_LENGTH_HEADER.toUpperCase())
    }
    return headers;
  }
};

exports.Request = function (method, queryPath) {
  this.parameters = new web.TupelList([]);
  this.cookies = new web.TupelList([]);
  this.body;
  this.contentType;
  this.entities;
  this.headers = new web.TupelList([]);
  this.method = method;
  this.queryPath = queryPath;

  this.setBody = function (newBody) {
    this.body = newBody;
  }.bind(this);
};

function processDestValue(destValueArg) {
  if (!destValueArg) {
    return;
  }

  var splitDestValueArray = destValueArg.split('"');
  var destValue;
  if (splitDestValueArray.length > 1) {
    destValue = splitDestValueArray[1].trim();
  } else {
    destValue = splitDestValueArray[0].trim();
  }

  return parseDestValue(destValue);

}

function parseDestValue(destValue) {
  if (Number.isInteger(destValue)) {
    return Number.parseInt(destValue);
  } else if (destValue === "true" || destValue === "false") {
    return new Boolean(destValue);
  }

  return destValue;
}

exports.Destination = function () {

}
