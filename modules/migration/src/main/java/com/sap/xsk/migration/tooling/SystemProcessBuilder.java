package com.sap.xsk.migration.tooling;

import java.io.File;
import java.io.IOException;
import java.util.List;

class SystemProcessBuilder {

  public Process startProcess(File directory, List<String> command) throws IOException {
    var processBuilder = new ProcessBuilder();
    processBuilder.directory(directory);
    processBuilder.command(command);
    return processBuilder.start();
  }
}
