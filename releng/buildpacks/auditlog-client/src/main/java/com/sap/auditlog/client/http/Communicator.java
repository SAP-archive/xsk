package com.sap.auditlog.client.http;

import com.sap.auditlog.client.exceptions.ServiceException;

public interface Communicator {

  void send(String apiUrl, String payload, String oauthToken) throws ServiceException;

  String get(String apiUrl, String oauthToken) throws ServiceException;

  String retrieveOAuthToken() throws ServiceException;
}
