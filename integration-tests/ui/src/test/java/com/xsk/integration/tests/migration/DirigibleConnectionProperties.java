package com.xsk.integration.tests.migration;

class DirigibleConnectionProperties {

  static final String HOST = "127.0.0.1";
  static final String PORT = "8080";
  static final String IDE_URI = "/services/v4/web/ide/";
  static final String AUTH_USERNAME = "dirigible";
  static final String AUTH_PASSWORD = "dirigible";
  static final String AUTH = AUTH_USERNAME + ":" + AUTH_PASSWORD;
  static final String BASE_URL = "http://" + AUTH + "@" + HOST + ":" + PORT + IDE_URI;
}
