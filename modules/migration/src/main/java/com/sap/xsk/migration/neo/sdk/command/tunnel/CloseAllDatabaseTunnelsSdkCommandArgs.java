package com.sap.xsk.migration.neo.sdk.command.tunnel;

import java.util.ArrayList;
import java.util.List;

public class CloseAllDatabaseTunnelsSdkCommandArgs extends CloseDatabaseTunnelSdkCommandArgs {

  public CloseAllDatabaseTunnelsSdkCommandArgs() {
    super(null);
  }

  @Override
  public List<String> commandLineArgs() {
    var commandLineArgs = new ArrayList<String>();
    commandLineArgs.add("--all");
    commandLineArgs.add("--output json");
    return commandLineArgs;
  }
}