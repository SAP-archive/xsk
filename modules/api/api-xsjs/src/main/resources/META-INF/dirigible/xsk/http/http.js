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
var web = require("xsk/web/web");
var config = require("core/v4/configurations");

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

exports.readDestination = function (destinationPackage, destinationName) {
  let readDestination = com.sap.xsk.api.destination.CloudPlatformDestinationFacade.getDestination(destinationName);
  let destination = new exports.Destination();

  destination.host = readDestination.getHost();
  destination.port = readDestination.getPort();
  destination.pathPrefix = readDestination.getPathPrefix();
  destination.name = destinationName;

  let properties = JSON.parse(readDestination.getPropertiesAsJSON());

  return Object.assign(destination, properties);
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
    // TODO: No dirigible functionality
  };

  this.setTimeout = function (timeout) {
    connectionTimeout = timeout;
  };

  function sendRequestObjToDestination(requestObj, destination) {
    let options = {};

    if (requestObj.contentType) {
      requestObj.headers.set("Content-Type", requestObj.contentType);
    }

    if (destination.ProxyType === "OnPremise") {
      options.proxyHost = config.get("DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_HOST");
      options.proxyPort = config.get("DIRIGIBLE_CONNECTIVITY_ONPREMISE_PROXY_PORT");
    }

    var requestHeaders = tupelObjectToArray(requestObj.headers);
    addCookieToHeadersFromTupel(requestObj.cookies, requestHeaders);
    requestObj.headers = requestHeaders;

    if (requestObj.parameters !== undefined) {
      requestObj.path = addQueryParametersToUrl(requestObj.path, requestObj.parameters);
    }

    if (requestObj.body) {
      options.text = requestObj.body.asString();
    }

    clientResponse = com.sap.xsk.api.destination.CloudPlatformDestinationFacade.executeRequest(JSON.stringify(requestObj), destination.name, JSON.stringify(options));
  }

  function sendRequestObjToUrl(requestObj, url, proxy) {
    var fullUrl = url + requestObj.path;

    var options = {};
    if (proxy) {
      options = proxyUrlToOptionsObject(proxy);
    }

    var requestHeaders = tupelObjectToArray(requestObj.headers);
    addCookieToHeadersFromTupel(requestObj.cookies, requestHeaders);
    options.headers = requestHeaders;

    addTimeoutToOptions(options);

    if (requestObj.contentType) {
      options.contentType = requestObj.contentType;
    }

    options.params = tupelObjectToArray(requestObj.parameters);
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
      options.text = requestBody.asString();
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
    if (typeof dResponse === 'string' || dResponse instanceof String) {
      dResponse = JSON.parse(dResponse);
    }

    var webResponse = new web.WebResponse(dResponse);
    return webResponse;
  }

  function tupelObjectToArray(tupelObject) {
    if (!tupelObject) {
      return;
    }

    var arr = [];
    for (var i = 0; i < tupelObject.length; i++) {
      arr.push(tupelObject[i]);
    }

    return arr;
  }

  function getUrlParametersFromTupel(tupelParameters) {
    var queryParameterPairs = [];
    for (var i = 0; i < tupelParameters.length; i++) {
      var name = tupelParameters[i].name;
      var value = tupelParameters[i].value;

      if (value instanceof Array) {
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

    headersArray.push({ name: "Cookie", value: cookiesArray.join(" ") });
  }
};

exports.Request = web.WebRequest;

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

exports.Destination = function () { }
