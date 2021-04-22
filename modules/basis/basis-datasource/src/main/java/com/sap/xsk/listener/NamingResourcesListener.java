package com.sap.xsk.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.deploy.NamingResourcesImpl;
import org.apache.naming.ContextAccessController;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.apache.tomcat.util.descriptor.web.ResourceBase;

public class NamingResourcesListener implements LifecycleListener, PropertyChangeListener {

  private static final Logger LOGGER = Logger.getLogger(NamingResourcesListener.class.getName());

  private static final String RESOURCE_STR = "resource";

  private static final String RESOURCE_ENV_REF_STR = "resourceEnvRef";

  private static final String FACTORY_CLASS_NAME = "DelegatingObjectFactory";

  @Deprecated
  private static final String PERSISTENCE_FACTORY_CLASS_NAME = "com.sap.jpaas.service.persistence.core.JNDIDataSourceFactory";

  private static final String FACTORY_PROPERTY = "factory";

  private Object container;

  private NamingResourcesImpl namingResources;

  private boolean initialized = false;

  private static final String name = "/";

  private String factoryClassName;

  static final String RESOURCE_REF_NAME = "ResourceRefName";

  private static final String DATA_SOURCE = "DataSource";

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    if (!this.initialized) {
      return;
    }

    final Object source = event.getSource();
    if (source.equals(this.namingResources)) {

      // Setting the context in read/write mode
      ContextAccessController.setWritable(name, this.container);

      final String name = event.getPropertyName();
      final Object newValue = event.getNewValue();

      if (newValue != null && (RESOURCE_STR.equals(name) || RESOURCE_ENV_REF_STR.equals(name))) {
        ResourceBase resource = (ResourceBase) newValue;
        updateResource(resource);
      }

      // Setting the context in read only mode
      ContextAccessController.setReadOnly(name);

    }
  }

  @Override
  public void lifecycleEvent(LifecycleEvent event) {
    this.container = event.getLifecycle();

    if (!(this.container instanceof Context)) {
      return;
    }

    this.namingResources = ((Context) this.container).getNamingResources();

    if (Lifecycle.CONFIGURE_START_EVENT.equals(event.getType()) && !this.initialized) {
      this.namingResources.addPropertyChangeListener(this);

      // handle resource-ref

      ResourceBase[] resources = this.namingResources.findResources();
      updateResources(resources);

      // handle resource-env-ref
      resources = this.namingResources.findResourceEnvRefs();
      updateResources(resources);

      this.initialized = true;
    } else if (Lifecycle.CONFIGURE_STOP_EVENT.equals(event.getType()) && this.initialized) {

      this.namingResources.removePropertyChangeListener(this);
      this.initialized = false;
    }
  }

  private void updateResources(ResourceBase[] resources) {
    if (resources != null) {
      for (ResourceBase resource : resources) {
        updateResource(resource);
      }
    }
  }

  private void updateResource(ResourceBase resource) {
    if (resource.getName() != null) {
      addFactoryClassAsResourceProperty(resource);
      if (resource.getType() != null && resource.getType().contains(DATA_SOURCE)) {
        addResourceNameAsProperty(resource);
      }
    }
  }

  private void addResourceNameAsProperty(ResourceBase resource) {
    if (!(resource instanceof ContextResource)) {
      return;
    }

    if (resource.getProperty(RESOURCE_REF_NAME) == null) {
      resource.setProperty(RESOURCE_REF_NAME, resource.getName());
      LOGGER.log(Level.FINE, "Injecting resource ref name as property to " + resource);

    }
  }

  private void addFactoryClassAsResourceProperty(ResourceBase resource) {
    if (resource.getProperty(FACTORY_PROPERTY) == null || resource.getProperty(FACTORY_PROPERTY).equals(PERSISTENCE_FACTORY_CLASS_NAME)) {
      resource.setProperty(FACTORY_PROPERTY, getFactoryClassName());
      LOGGER.log(Level.FINE, "Injecting factory " + getFactoryClassName() + " to " + resource);
    }
  }

  public void setFactoryClassName(String factoryClassName) {
    this.factoryClassName = factoryClassName;
  }

  public String getFactoryClassName() {
    if (this.factoryClassName == null) {
      return FACTORY_CLASS_NAME;
    }
    return this.factoryClassName;
  }

}
