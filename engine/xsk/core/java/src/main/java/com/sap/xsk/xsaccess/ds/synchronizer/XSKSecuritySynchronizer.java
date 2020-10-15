package com.sap.xsk.xsaccess.ds.synchronizer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.core.security.synchronizer.SecuritySynchronizer;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class XSKSecuritySynchronizer extends AbstractSynchronizer {
    private static final Logger logger = LoggerFactory.getLogger(SecuritySynchronizer.class);

    private static final Map<String, List<XSKPrivilegeDefinition>> PRIVILEGES_PREDELIVERED = Collections.synchronizedMap(new HashMap<>());

    private static final Map<String, XSKAccessDefinition> ACCESS_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<>());

    private static final Set<String> PRIVILEGES_SYNCHRONIZED = Collections.synchronizedSet(new HashSet<>());

    private static final Set<String> ACCESS_SYNCHRONIZED = Collections.synchronizedSet(new HashSet<>());

    @Inject
    private XSKAccessCoreService xscAccessCoreService;

    @Inject
    private XSKPrivilegeCoreService xscPrivilegeCoreService;

    /**
     * Force synchronization.
     */
    public static final void forceSynchronization() {
        SecuritySynchronizer securitySynchronizer = StaticInjector.getInjector().getInstance(SecuritySynchronizer.class);
        securitySynchronizer.synchronize();
    }

    /**
     * Register pre-delivered roles.
     *
     * @param rolesPath
     *            the roles path
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void registerPredeliveredRoles(String rolesPath) throws IOException {
        InputStream in = SecuritySynchronizer.class.getResourceAsStream(rolesPath);
        try {
            String json = IOUtils.toString(in, StandardCharsets.UTF_8);
            List<XSKPrivilegeDefinition> xscPrivilegeDefinitions = XSKPrivilegeArtifact.parse(json).divide();

            PRIVILEGES_PREDELIVERED.put(rolesPath, xscPrivilegeDefinitions);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * Register pre-delivered access.
     *
     * @param xscAccessPath
     *            the access path
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void registerPredeliveredAccess(String xscAccessPath) throws IOException {
        InputStream in = SecuritySynchronizer.class.getResourceAsStream(xscAccessPath);
        try {
            String json = IOUtils.toString(in, StandardCharsets.UTF_8);
            XSKAccessDefinition xscAccessDefinition = XSKAccessArtifact.parse(json).toXSKAccessDefinition();

            xscAccessDefinition.setPath(xscAccessPath);

            ACCESS_PREDELIVERED.put(xscAccessPath, xscAccessDefinition);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.ISynchronizer#synchronize()
     */
    @Override
    public void synchronize() {
        synchronized (SecuritySynchronizer.class) {
            logger.trace("Synchronizing Roles and Access artifacts...");
            try {
                clearCache();
                synchronizePredelivered();
                synchronizeRegistry();
                cleanup();
                clearCache();
            } catch (Exception e) {
                logger.error("Synchronizing process for Roles and Access artifacts failed.", e);
            }
            logger.trace("Done synchronizing Roles and Access artifacts.");
        }
    }

    /**
     * Clear cache.
     */
    private void clearCache() {
        PRIVILEGES_SYNCHRONIZED.clear();
        ACCESS_SYNCHRONIZED.clear();
        xscAccessCoreService.clearCache();
    }

    /**
     * Synchronize predelivered.
     *
     * @throws SynchronizationException
     *             the synchronization exception
     */
    private void synchronizePredelivered() throws SynchronizationException {
        logger.trace("Synchronizing predelivered Roles and Access artifacts...");

        // Roles
        for (List<XSKPrivilegeDefinition> roleDefinitions : PRIVILEGES_PREDELIVERED.values()) {
            for (XSKPrivilegeDefinition xscPrivilegeDefinition : roleDefinitions) {
                synchronizeRole(xscPrivilegeDefinition);
            }
        }

        // Access
        for (XSKAccessDefinition xscAccessDefinition : ACCESS_PREDELIVERED.values()) {
            xscAccessDefinition.setHash("" + xscAccessDefinition.hashCode());
            synchronizeAccess(xscAccessDefinition);
        }

        logger.trace("Done synchronizing predelivered Roles and Access artifacts.");
    }

    /**
     * Synchronize role.
     *
     * @param xscPrivilegeDefinition
     *            the role definition
     * @throws SynchronizationException
     *             the synchronization exception
     */
    private void synchronizeRole(XSKPrivilegeDefinition xscPrivilegeDefinition) throws SynchronizationException {
        try {
            if (!xscPrivilegeCoreService.xscPrivilegeExists(xscPrivilegeDefinition.getName())) {
                xscPrivilegeCoreService.createXSKPrivilege(xscPrivilegeDefinition.getName(), xscPrivilegeDefinition.getDescription());
                logger.info("Synchronized a new XSK Privilege [{}]", xscPrivilegeDefinition.getName());
            } else {
                XSKPrivilegeDefinition existingXscPrivilegeDefinition = xscPrivilegeCoreService.getXSKPrivilegeByName(xscPrivilegeDefinition.getName());
                if (!existingXscPrivilegeDefinition.getDescription().equals(xscPrivilegeDefinition.getDescription())) {
                    xscPrivilegeCoreService.updateXSKPrivileges(xscPrivilegeDefinition.getName(), xscPrivilegeDefinition.getDescription());
                    logger.info("Synchronized a modified XSK Privilege [{}]", xscPrivilegeDefinition.getName());
                }

            }

            ACCESS_SYNCHRONIZED.add(xscPrivilegeDefinition.getName());
        } catch (XSKPrivilegeException e) {
            throw new SynchronizationException(e);
        }
    }

    /**
     * Synchronize access.
     *
     * @param xscAccessDefinition
     *            the access definition
     * @throws SynchronizationException
     *             the synchronization exception
     */
    private void synchronizeAccess(XSKAccessDefinition xscAccessDefinition) throws SynchronizationException {
        try {
            XSKAccessDefinition existingXSKAccessDefinition = xscAccessCoreService.getXSKAccessDefinition(xscAccessDefinition.getPath());
            if (existingXSKAccessDefinition == null) {
                xscAccessCoreService.createXSKAccessDefinition(xscAccessDefinition.getPath(), xscAccessDefinition.getAuthenticationMethod(), xscAccessDefinition.getHash(),
                        xscAccessDefinition.isExposed(), xscAccessDefinition.getAuthorizationRolesAsList());
                logger.info("Synchronized a new XSK Access definition [[{}]-[{}]] from location: {}",
                        xscAccessDefinition.getAuthenticationMethod(), String.join(",", 
                        		(xscAccessDefinition.getAuthorizationRolesAsList() != null ? xscAccessDefinition.getAuthorizationRolesAsList() : new ArrayList())), xscAccessDefinition.getPath());
            } else if (!existingXSKAccessDefinition.getHash().equals(xscAccessDefinition.getHash())) {
                xscAccessCoreService.updateXSKAccessDefinition(xscAccessDefinition.getPath(), xscAccessDefinition.getAuthenticationMethod(), xscAccessDefinition.getHash(),
                        xscAccessDefinition.isExposed(), xscAccessDefinition.getAuthorizationRolesAsList());
                logger.info("Synchronized a modified XSK Access definition [[{}]-[{}]] from location: {}",
                        xscAccessDefinition.getAuthenticationMethod(), String.join(",", 
                        		(xscAccessDefinition.getAuthorizationRolesAsList() != null ? xscAccessDefinition.getAuthorizationRolesAsList() : new ArrayList())), xscAccessDefinition.getPath());
            }

            ACCESS_SYNCHRONIZED.add(xscAccessDefinition.getPath());
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
            XSKPrivilegeArtifact xscPrivilegeArtifact = XSKPrivilegeArtifact.parse(resource.getContent());
            for (XSKPrivilegeDefinition xscPrivilegeDefinition : xscPrivilegeArtifact.divide()) {
                synchronizeRole(xscPrivilegeDefinition);
            }

        }
        if (resourceName.equals(IXSKAccessCoreService.XSK_FILE_EXTENSION_ACCESS)) {
            XSKAccessDefinition xscAccessDefinition = xscAccessCoreService.parseXSAccessArtifact(resource.getContent());

            xscAccessDefinition.setPath(removeXscFileFromPath(getRegistryPath(resource)));
            String hash = DigestUtils.md5Hex(resource.getContent());
            xscAccessDefinition.setHash(hash);

            synchronizeAccess(xscAccessDefinition);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#cleanup()
     */
    @Override
    protected void cleanup() throws SynchronizationException {
        logger.trace("Cleaning up Roles and Access artifacts...");

        try {
            List<XSKPrivilegeDefinition> roleDefinitions = xscPrivilegeCoreService.getXSKPrivileges();
            for (XSKPrivilegeDefinition xscPrivilegeDefinition : roleDefinitions) {
                if (!ACCESS_SYNCHRONIZED.contains(xscPrivilegeDefinition.getName())) {

                    xscPrivilegeCoreService.removeXSKPrivilegeByName(xscPrivilegeDefinition.getName());
                    logger.warn("Cleaned up XSK Privilege [{}]", xscPrivilegeDefinition.getName());
                }
            }

            List<XSKAccessDefinition> accessDefinitions = xscAccessCoreService.getAccessXSKDefinitions();
            for (XSKAccessDefinition xscAccessDefinition : accessDefinitions) {
                if (!ACCESS_SYNCHRONIZED.contains(xscAccessDefinition.getPath())) {
                    xscAccessCoreService.removeXSKAccessDefinition(xscAccessDefinition.getPath());
                    logger.warn("Cleaned up XSK Access definition [[{}]-[{}]-[{}]] from location: {}", xscAccessDefinition.getAuthenticationMethod(),
                            String.join(",", xscAccessDefinition.getAuthorizationRolesAsList()), xscAccessDefinition.getPath());
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
