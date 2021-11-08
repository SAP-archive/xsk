package com.xsk.integration.tests.migration;

class ExpectedContent {

  private final String filePath;
  private final byte[] content;

  ExpectedContent(String filePath, byte[] content) {
    this.filePath = filePath;
    this.content = content;
  }

  String getFilePath() {
    return filePath;
  }

  byte[] getContent() {
    return content;
  }
}
