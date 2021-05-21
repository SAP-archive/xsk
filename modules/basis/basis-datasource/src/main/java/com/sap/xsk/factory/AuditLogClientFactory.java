package com.sap.xsk.factory;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

public class AuditLogClientFactory implements ObjectFactory {

  @Override
  public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
    return null;
  }
}
