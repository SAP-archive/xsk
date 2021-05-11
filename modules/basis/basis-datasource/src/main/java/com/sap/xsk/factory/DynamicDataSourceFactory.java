package com.sap.xsk.factory;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameNotFoundException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.naming.spi.ObjectFactory;
import javax.sql.DataSource;
import org.apache.catalina.util.ServerInfo;
import org.apache.naming.ResourceRef;

public class DynamicDataSourceFactory implements ObjectFactory {

  public static final String ENV_PREFIX_DELIMITER_DEFAULT = "_";
  public static final String ENV_PREFIX_DELIMITER_KEY = "smartobjectfactory_delimiter";

  private static final String DEFAULT_TOMCAT7_OF_CLASS_NAME = "org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory";
  private static final String DEFAULT_TOMCAT8_OF_CLASS_NAME = "org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory";

  private static final Logger logger = Logger.getLogger(DynamicDataSourceFactory.class.getName());
  private String envPrefixDelimiter;
  private ObjectFactory tomcatObjectFactory;

  @Override
  public Object getObjectInstance(Object obj, Name name, Context ctx, Hashtable<?, ?> environment) throws Exception {
    logger.fine("getObjectInstance method entry");

    if (shouldProcessResourceRef(obj)) {
      enhanceResourceRef((ResourceRef) obj, name.toString());
    }

    logger.fine("getObjectInstance method exit - delegating to default tomcat OF");
    return getTomcatDefaultObjectFactory().getObjectInstance(obj, name, ctx, environment);
  }

  boolean shouldProcessResourceRef(Object obj) throws NameNotFoundException {
    if (!(obj instanceof Reference)) {
      throw new NameNotFoundException("Object [" + obj + "] is not instance of [" + Reference.class.getName() + "]");
    }
    var ref = (Reference) obj;
    var refClassName = ref.getClassName();
    if (DataSource.class.getName().equals(refClassName)) {
      logger.fine("Resource ref [ " + refClassName + "] will be processed.");
      return true;
    }
    logger.fine("Resource ref [ " + refClassName + "] is discarded.");
    return false;
  }

  ObjectFactory getTomcatDefaultObjectFactory() throws InstantiationException, IllegalAccessException {
    if (tomcatObjectFactory != null) {
      return tomcatObjectFactory;
    }

    int tomcatVersion = getTomcatVersion();
    setTomcatObjectFactory(tomcatVersion);
    return tomcatObjectFactory;
  }

  private int getTomcatVersion() {
    String tomcatVersionString = ServerInfo.getServerNumber();
    logger.fine("Tomcat version string " + tomcatVersionString);
    String tomcatVersionPattern = "[0-9].[0-9].([0-9]+)";
    Pattern p = Pattern.compile(tomcatVersionPattern);
    Matcher match = p.matcher(tomcatVersionString);
    int noVersion = Integer.MAX_VALUE;

    if (match.find()) {
      return Integer.parseInt(match.group(0).substring(0, 1));
    }

    return noVersion;
  }

  private void setTomcatObjectFactory(int tomcatVersion) throws IllegalAccessException, InstantiationException {
    Class<?> factoryClass;
    int maxTomcatSupportedVersion = 7;
    try {
      if (tomcatVersion <= maxTomcatSupportedVersion) {
        logger.fine("Major version " + tomcatVersion);
        factoryClass = this.getClass().getClassLoader().loadClass(DEFAULT_TOMCAT7_OF_CLASS_NAME);
      } else {
        logger.fine("Version not found, trying to load " + DEFAULT_TOMCAT8_OF_CLASS_NAME);
        factoryClass = this.getClass().getClassLoader().loadClass(DEFAULT_TOMCAT8_OF_CLASS_NAME);
      }
      tomcatObjectFactory = (ObjectFactory) factoryClass.newInstance();
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("Unable to load tomcat default object factory");
    }
  }
  void enhanceResourceRef(ResourceRef ref, String name) {
    enhanceResourceRef(ref, name, System.getenv());
  }

  void enhanceResourceRef(ResourceRef ref, String name, Map<String, String> envVars) {
    logger.fine("Enhancing resource ref...");

    String prefix = name + getPrefixDelimiter();

    Set<String> allEnvVarKeys = envVars.keySet();
    logger.info(" environment variables " + allEnvVarKeys);

    for (String key : allEnvVarKeys) {
      if (key.startsWith(prefix)) {
        logger.info("Processing key [" + key + "]");

        String refAddrKey = key.substring(prefix.length());
        String refAddrValue = envVars.get(key);
        StringRefAddr refAddr = new StringRefAddr(refAddrKey, refAddrValue);
        logger.info("Adding [" + refAddrKey + " / " + refAddrValue + "]");

        ref.add(refAddr);
      }
    }
    logger.fine("Resource ref enhanced");
  }

  String getPrefixDelimiter() {
    return getPrefixDelimiter(System.getenv());
  }

  String getPrefixDelimiter(Map<String, String> envVariables) {
    if (envPrefixDelimiter == null) {
      String value = envVariables.get(ENV_PREFIX_DELIMITER_KEY);
      if (value == null || value.equals("")) {
        envPrefixDelimiter = ENV_PREFIX_DELIMITER_DEFAULT;
      } else {
        envPrefixDelimiter = value;
      }
    }
    logger.fine("Setting delimiter " + envPrefixDelimiter);
    return envPrefixDelimiter;
  }
}
