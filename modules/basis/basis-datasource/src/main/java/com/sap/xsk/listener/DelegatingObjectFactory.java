package com.sap.xsk.listener;

import static java.text.MessageFormat.format;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

public class DelegatingObjectFactory implements ObjectFactory {

  private static final Logger LOGGER = Logger.getLogger(DelegatingObjectFactory.class.getName());

  @Override
  public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws NamingException {
    if (obj instanceof Reference) {
      final Reference reference = (Reference) obj;
      final String referenceType = reference.getClassName();
      DelegatingObjectFactory.class.getClassLoader();
      Object objectInstance = getObjectInstanceFromFactory(obj, name, nameCtx, environment, referenceType);
      if (objectInstance != null) {
        return objectInstance;
      }
    }
    throw new NamingException("Object instance with name [" + name + "] could not be created.");
  }

  private Object getObjectInstanceFromFactory(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment,
      final String referenceType) throws NamingException {

    final List<ObjectFactory> factories = getObjectFactories(referenceType);
    for (ObjectFactory factory : factories) {
      var res = createObject(obj, name, nameCtx, environment, factory);
      if (res != null) {
        LOGGER.fine(format("Object created for name {0} with factory {1}", name, factory));
        return res;
      }
    }
    return getObjectInstanceFromDefaults(obj, name, nameCtx, environment);
  }

  private Object getObjectInstanceFromDefaults(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws NamingException {
    final Reference reference = (Reference) obj;
    RefAddr removedFactory = removeDelegatingObjectFactoryFromReference(reference);
    var factory = createFactory(reference);

    if (removedFactory != null) {
      LOGGER.info("Adding removed factory");
      reference.add(removedFactory);
    }

    return createObject(obj, name, nameCtx, environment, factory);
  }

  private Object createObject(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment, ObjectFactory factory)
      throws NamingException {
    Object objectInstance = null;
    try {
      objectInstance = factory.getObjectInstance(obj, name, nameCtx, environment);
    } catch (NameNotFoundException nne) {
      LOGGER.fine(format("Factory {0} couldn't retrieve object {1}", factory, name));
    } catch (Exception e) {
      LOGGER.severe("Exception is thrown by factory [" + factory + "] during retrieving object instance. Exception is: " + e);
      throw new NamingException("Cannot create resource  object instance due to exception in the object factory");
    }
    return objectInstance;
  }

  private ObjectFactory createFactory(Reference reference) throws NamingException {
    String factoryClassName = reference.getFactoryClassName();
    if (factoryClassName == null) {
      return null;
    }

    try {
      return (ObjectFactory) getClass().getClassLoader().loadClass(factoryClassName).newInstance();
    } catch (Exception e) {
      LOGGER.severe("Exception is thrown by factory [" + factoryClassName + "] during retrieving object instance. Exception is: " + e);
      throw new NamingException("Cannot create resource  object instance due to exception in the object factory");
    }
  }

  private RefAddr removeDelegatingObjectFactoryFromReference(Reference reference) {
    RefAddr removed = null;
    for (int i = 0; i < reference.size(); i++) {
      final RefAddr refAddr = reference.get(i);
      Object content = refAddr.getContent();
      if (isDelegatedFactoryReference(content)) {
        reference.remove(i);
        removed = refAddr;
        break;
      }
    }
    return removed;
  }

  private boolean isDelegatedFactoryReference(Object content) {
    return content != null && this.getClass().getName().equals(content.toString());
  }

  protected List<ObjectFactory> getObjectFactories(final String referenceType) {
    LOGGER.fine(this + " - Searching for object factories... ");

    ClassLoader appClassLoader = Thread.currentThread().getContextClassLoader();
    LOGGER.fine("Context classloader is set, we expect to be app classloader " + appClassLoader);
    List<ObjectFactory> factoriesVisibleFromApp = getObjectFactories(referenceType, appClassLoader);

    LOGGER.fine(this + " - Found object factories visible from app loader " + factoriesVisibleFromApp);
    return factoriesVisibleFromApp;
  }

  static List<ObjectFactory> getObjectFactories(final String referenceType, ClassLoader classLoader) {
    ServiceLoader<ObjectFactory> serviceLoader = ServiceLoader.load(ObjectFactory.class, classLoader);
    List<ObjectFactory> result = new ArrayList<>();
    try {
      for (ObjectFactory factory : serviceLoader) {
        result.add(factory);
      }
    } catch (ServiceConfigurationError e) {
      LOGGER.log(Level.SEVERE, "Cannot obtain ObjectFactory for reference " + referenceType, e);
    }
    return result;
  }


}
