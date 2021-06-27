/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
exports.debug = function(message) {
  if(this.isDebugEnabled()) {
    console.debug(message);
  }
}

exports.error = function(message) {
  if(this.isErrorEnabled()) {
    console.error(message);
  }
}

exports.fatal = function(message) {
  if(this.isFatalEnabled()) {
    console.error(message);
  }
}

exports.info = function(message) {
  if(this.isInfoEnabled()) {
    console.info(message);
  }
}

exports.warning = function(message) {
  if(this.isWarningEnabled()) {
    console.warn(message);
  }
}

exports.isDebugEnabled = function() {
	return com.sap.xsk.api.TraceFacade.isDebugEnabled().toLowerCase() === "true";
}

exports.isErrorEnabled = function() {
  return com.sap.xsk.api.TraceFacade.isErrorEnabled().toLowerCase() === "true";
}

exports.isFatalEnabled = function() {
  return com.sap.xsk.api.TraceFacade.isFatalEnabled().toLowerCase() === "true";
}

exports.isInfoEnabled = function() {
  return com.sap.xsk.api.TraceFacade.isInfoEnabled().toLowerCase() === "true";
}

exports.isWarningEnabled = function() {
  return com.sap.xsk.api.TraceFacade.isWarningEnabled().toLowerCase() === "true";
}

