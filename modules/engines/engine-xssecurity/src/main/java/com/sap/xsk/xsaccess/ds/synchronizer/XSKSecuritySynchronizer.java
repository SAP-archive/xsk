/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsaccess.ds.synchronizer;

import com.sap.xsk.xsaccess.ds.api.IXSKAccessCoreService;
import com.sap.xsk.xsaccess.ds.api.IXSKPrivilegeCoreService;
import com.sap.xsk.xsaccess.ds.api.XSKAccessException;
import com.sap.xsk.xsaccess.ds.api.XSKPrivilegeException;
import com.sap.xsk.xsaccess.ds.model.access.XSKAccessArtifact;
import com.sap.xsk.xsaccess.ds.model.access.XSKAccessDefinition;
import com.sap.xsk.xsaccess.ds.model.privilege.XSKPrivilegeArtifact;
import com.sap.xsk.xsaccess.ds.model.privilege.XSKPrivilegeDefinition;
import com.sap.xsk.xsaccess.ds.service.XSKAccessCoreService;
import com.sap.xsk.xsaccess.ds.service.XSKPrivilegeCoreService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.core.security.synchronizer.SecuritySynchronizer;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKSecuritySynchronizer extends AbstractSynchronizer {

  private static final Logger logger = LoggerFactory.getLogger(SecuritySynchronizer.class);

  private static final Map<String, List<XSKPrivilegeDefinition>> PRIVILEGES_PREDELIVERED = Collections.synchronizedMap(new HashMap<>());

  private static final Map<String, XSKAccessDefinition> ACCESS_PREDELIVERED = Collections
      .synchronizedMap(new HashMap<>());

  private static final Set<XSKPrivilegeDefinition> PRIVILEGES_TO_BE_PROCESSED = Collections.synchronizedSet(
      new HashSet<>());

  private static final Set<XSKAccessDefinition> ACCESS_TO_BE_PROCESSED = Collections
      .synchronizedSet(new HashSet<>());

  private static final Set<String> PRIVILEGES_SYNCHRONIZED = Collections.synchronizedSet(new HashSet<>());

  private static final Set<String> ACCESS_SYNCHRONIZED = Collections.synchronizedSet(new HashSet<>());
  private final String SYNCHRONIZER_NAME = this.getClass().getCanonicalName();

  private XSKAccessCoreService xskAccessCoreService = new XSKAccessCoreService();

  private XSKPrivilegeCoreService xskPrivilegeCoreService = new XSKPrivilegeCoreService();

  /**
   * Force synchronization.
   */
  public static final void forceSynchronization() {
    SecuritySynchronizer synchronizer = new SecuritySynchronizer();
    synchronizer.setForcedSynchronization(true);
    try {
      synchronizer.synchronize();
    } finally {
      synchronizer.setForcedSynchronization(false);
    }
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.core.scheduler.api.ISynchronizer#synchronize()
   */
  @Override
  public void synchronize() {
    synchronized (SecuritySynchronizer.class) {
      if (beforeSynchronizing()) {
        logger.trace("Synchronizing Privileges and Access artifacts...");
        try {
          startSynchronization(SYNCHRONIZER_NAME);
          clearCache();
          synchronizePredelivered();
          synchronizeRegistry();
          int immutablePrivilegesCount = PRIVILEGES_PREDELIVERED.size();
          int immutableAccessCount = ACCESS_PREDELIVERED.size();

          int mutablePrivilegesCount = PRIVILEGES_SYNCHRONIZED.size();
          int mutableAccessCount = ACCESS_SYNCHRONIZED.size();
          processArtifacts();
          cleanup();
          clearCache();
          successfulSynchronization(SYNCHRONIZER_NAME,
              String.format("Immutable: [Privileges: {0}, Access: {1}], Mutable: [Privileges: {2}, Access: {3}]",
                  immutablePrivilegesCount, immutableAccessCount, mutablePrivilegesCount, mutableAccessCount));
        } catch (Exception e) {
          logger.error("Synchronizing process for Privileges and Access artifacts failed.", e);
          try {
            failedSynchronization(SYNCHRONIZER_NAME, e.getMessage());
          } catch (SchedulerException e1) {
            logger.error("Synchronizing process for Privileges and Access artifacts failed in registering the state log.", e);
          }
        }
        logger.trace("Done synchronizing Privileges and Access artifacts.");
        afterSynchronizing();
      }
    }
  }

  /**
   * Register pre-delivered privileges.
   *
   * @param privilegesPath the privileges path
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void registerPredeliveredPrivileges(String privilegesPath) throws IOException {
    InputStream in = SecuritySynchronizer.class.getResourceAsStream(privilegesPath);
    try {
      String json = IOUtils.toString(in, StandardCharsets.UTF_8);
      List<XSKPrivilegeDefinition> xskPrivilegeDefinitions = XSKPrivilegeArtifact.parse(json).divide();

      PRIVILEGES_PREDELIVERED.put(privilegesPath, xskPrivilegeDefinitions);
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }

  /**
   * Register pre-delivered access.
   *
   * @param xskAccessPath the access path
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void registerPredeliveredAccess(String xskAccessPath) throws IOException {
    InputStream in = SecuritySynchronizer.class.getResourceAsStream(xskAccessPath);
    try {
      String json = IOUtils.toString(in, StandardCharsets.UTF_8);
      XSKAccessDefinition xskAccessDefinition = XSKAccessArtifact.parse(json).toXSKAccessDefinition();

      xskAccessDefinition.setPath(xskAccessPath);

      ACCESS_PREDELIVERED.put(xskAccessPath, xskAccessDefinition);
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }

  /**
   * Clear cache.
   */
  private void clearCache() {
    PRIVILEGES_SYNCHRONIZED.clear();
    PRIVILEGES_TO_BE_PROCESSED.clear();
    ACCESS_SYNCHRONIZED.clear();
    ACCESS_TO_BE_PROCESSED.clear();
    xskAccessCoreService.clearCache();
  }

  /**
   * Synchronize predelivered.
   *
   * @throws SynchronizationException the synchronization exception
   */
  private void synchronizePredelivered() throws SynchronizationException {
    logger.trace("Synchronizing predelivered Privileges and Access artifacts...");

    // Privileges
    for (List<XSKPrivilegeDefinition> privilegeDefinitions : PRIVILEGES_PREDELIVERED.values()) {
      for (XSKPrivilegeDefinition xskPrivilegeDefinition : privilegeDefinitions) {
        synchronizePrivilege(xskPrivilegeDefinition);
      }
    }

    // Access
    for (XSKAccessDefinition xskAccessDefinition : ACCESS_PREDELIVERED.values()) {
      xskAccessDefinition.setHash("" + xskAccessDefinition.hashCode());
      synchronizeAccess(xskAccessDefinition);
    }

    logger.trace("Done synchronizing predelivered Privileges and Access artifacts.");
  }

  /**
   * Process the privilege and access artifacts.
   *
   * @throws SynchronizationException the synchronization exception
   */
  private void processArtifacts() throws SynchronizationException {
    for (XSKPrivilegeDefinition xskPrivilegeDefinition : PRIVILEGES_TO_BE_PROCESSED) {
      synchronizePrivilege(xskPrivilegeDefinition);
    }

    for (XSKAccessDefinition xskAccessDefinition : ACCESS_TO_BE_PROCESSED) {
      synchronizeAccess(xskAccessDefinition);
    }
  }

  /**
   * Synchronize privileges.
   *
   * @param xskPrivilegeDefinition the privilege definition
   * @throws SynchronizationException the synchronization exception
   */
  private void synchronizePrivilege(XSKPrivilegeDefinition xskPrivilegeDefinition) throws SynchronizationException {
    try {
      if (!xskPrivilegeCoreService.xskPrivilegeExists(xskPrivilegeDefinition.getName())) {
        xskPrivilegeCoreService.createXSKPrivilege(xskPrivilegeDefinition.getName(), xskPrivilegeDefinition.getDescription());
        logger.info("Synchronized a new XSK Privilege [{}]", xskPrivilegeDefinition.getName());
      } else {
        XSKPrivilegeDefinition existingXscPrivilegeDefinition = xskPrivilegeCoreService
            .getXSKPrivilegeByName(xskPrivilegeDefinition.getName());
        if (!existingXscPrivilegeDefinition.getDescription().equals(xskPrivilegeDefinition.getDescription())) {
          xskPrivilegeCoreService.updateXSKPrivileges(xskPrivilegeDefinition.getName(), xskPrivilegeDefinition.getDescription());
          logger.info("Synchronized a modified XSK Privilege [{}]", xskPrivilegeDefinition.getName());
        }

      }

      PRIVILEGES_SYNCHRONIZED.add(xskPrivilegeDefinition.getName());
    } catch (XSKPrivilegeException e) {
      throw new SynchronizationException(e);
    }
  }

  /**
   * Synchronize access.
   *
   * @param xskAccessDefinition the access definition
   * @throws SynchronizationException the synchronization exception
   */
  private void synchronizeAccess(XSKAccessDefinition xskAccessDefinition) throws SynchronizationException {
    try {
      XSKAccessDefinition existingXSKAccessDefinition = xskAccessCoreService.getXSKAccessDefinition(xskAccessDefinition.getPath());
      if (existingXSKAccessDefinition == null) {
        xskAccessCoreService.createXSKAccessDefinition(xskAccessDefinition.getPath(), xskAccessDefinition.getAuthenticationMethodsAsList(),
            xskAccessDefinition.getHash(),
            xskAccessDefinition.isExposed(), xskAccessDefinition.getAuthorizationRolesAsList());
        logger.info("Synchronized a new XSK Access definition [[{}]-[{}]] from location: {}",
            xskAccessDefinition.getAuthenticationMethodsAsList(), String.join(",",
                (xskAccessDefinition.getAuthorizationRolesAsList() != null ? xskAccessDefinition.getAuthorizationRolesAsList()
                    : new ArrayList())), xskAccessDefinition.getPath());
      } else if (!existingXSKAccessDefinition.getHash().equals(xskAccessDefinition.getHash())) {
        xskAccessCoreService.updateXSKAccessDefinition(xskAccessDefinition.getPath(), xskAccessDefinition.getAuthenticationMethodsAsList(),
            xskAccessDefinition.getHash(),
            xskAccessDefinition.isExposed(), xskAccessDefinition.getAuthorizationRolesAsList());
        logger.info("Synchronized a modified XSK Access definition [[{}]-[{}]] from location: {}",
            xskAccessDefinition.getAuthenticationMethodsAsList(), String.join(",",
                (xskAccessDefinition.getAuthorizationRolesAsList() != null ? xskAccessDefinition.getAuthorizationRolesAsList()
                    : new ArrayList())), xskAccessDefinition.getPath());
      }

      ACCESS_SYNCHRONIZED.add(xskAccessDefinition.getPath());
    } catch (XSKAccessException e) {
      throw new SynchronizationException(e);
    }
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#synchronizeRegistry()
   */
  @Override
  protected void synchronizeRegistry() throws SynchronizationException {
    logger.trace("Synchronizing Extension Points and Extensions from Registry...");

    super.synchronizeRegistry();

    logger.trace("Done synchronizing Extension Points and Extensions from Registry.");
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#synchronizeResource(com.sap.xsk.hdb.ds.parser.XSKDataStructureParser
   * repository.api.IResource)
   */
  @Override
  protected void synchronizeResource(IResource resource) throws SynchronizationException {
    String resourceName = resource.getName();
    if (resourceName.equals(IXSKPrivilegeCoreService.XSK_FILE_EXTENSION_PRIVILEGE)) {
      XSKPrivilegeArtifact xskPrivilegeArtifact = XSKPrivilegeArtifact.parse(resource.getContent());

      // set name to use "path.to::Privilege" syntax
      String[] splitPath = resource.getPath().split("/");
      String path = String.join(".", Arrays.copyOfRange(splitPath, 3, splitPath.length - 1)); // remove /registry/public

      for (XSKPrivilegeDefinition privilege : xskPrivilegeArtifact.divide()) {
        privilege.setName(path + "::" + privilege.getName());
        PRIVILEGES_TO_BE_PROCESSED.add(privilege);
      }
    }
    if (resourceName.equals(IXSKAccessCoreService.XSK_FILE_EXTENSION_ACCESS)) {
      XSKAccessDefinition xskAccessDefinition = xskAccessCoreService.parseXSAccessArtifact(resource.getContent());

      xskAccessDefinition.setPath(removeXscFileFromPath(getRegistryPath(resource)));
      String hash = DigestUtils.md5Hex(resource.getContent());
      xskAccessDefinition.setHash(hash);

      ACCESS_TO_BE_PROCESSED.add(xskAccessDefinition);
    }
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#cleanup()
   */
  @Override
  protected void cleanup() throws SynchronizationException {
    logger.trace("Cleaning up Privileges and Access artifacts...");

    try {
      List<XSKPrivilegeDefinition> privilegeDefinitions = xskPrivilegeCoreService.getXSKPrivileges();
      for (XSKPrivilegeDefinition xskPrivilegeDefinition : privilegeDefinitions) {
        if (!PRIVILEGES_SYNCHRONIZED.contains(xskPrivilegeDefinition.getName())) {

          xskPrivilegeCoreService.removeXSKPrivilegeByName(xskPrivilegeDefinition.getName());
          logger.warn("Cleaned up XSK Privilege [{}]", xskPrivilegeDefinition.getName());
        }
      }

      List<XSKAccessDefinition> accessDefinitions = xskAccessCoreService.getAccessXSKDefinitions();
      for (XSKAccessDefinition xskAccessDefinition : accessDefinitions) {
        if (!ACCESS_SYNCHRONIZED.contains(xskAccessDefinition.getPath())) {
          xskAccessCoreService.removeXSKAccessDefinition(xskAccessDefinition.getPath());
          logger.warn("Cleaned up XSK Access definition [[{}]-[{}]-[{}]] from location: {}",
              xskAccessDefinition.getAuthenticationMethodsAsList(),
              String.join(",", xskAccessDefinition.getAuthorizationRolesAsList()), xskAccessDefinition.getPath());
        }
      }
    } catch (XSKAccessException | XSKPrivilegeException e) {
      throw new SynchronizationException(e);
    }

    logger.trace("Done cleaning up XSK Privileges and  XSK Access artifacts.");
  }

  private String removeXscFileFromPath(String path) {
    return path.split("\\.")[0];
  }
}
