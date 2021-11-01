package com.sap.xsk.xsodata.ds.filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

final class WrappedHttpServletRequest extends HttpServletRequestWrapper {

  private final Map<String, String> customHeaders;

  public WrappedHttpServletRequest(HttpServletRequest request) {
    super(request);
    this.customHeaders = new HashMap<String, String>();
  }

  public void putHeader(String name, String value) {
    this.customHeaders.put(name, value);
  }

  public String getHeader(String name) {
    String headerValue = customHeaders.get(name);

    if (headerValue != null) {
      return headerValue;
    }

    return ((HttpServletRequest) getRequest()).getHeader(name);
  }

  public Enumeration<String> getHeaderNames() {
    List<String> names = Collections.list(((HttpServletRequest) getRequest()).getHeaderNames());
    for (String name : customHeaders.keySet()) {
      names.add(name);
    }
    return Collections.enumeration(names);
  }
}