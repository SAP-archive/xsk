package com.sap.xsk.migration.tooling;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

class InputStreamStringReader {

  public String readToString(InputStream inputStream, Charset charset) throws IOException {
    return IOUtils.toString(inputStream, charset);
  }
}
