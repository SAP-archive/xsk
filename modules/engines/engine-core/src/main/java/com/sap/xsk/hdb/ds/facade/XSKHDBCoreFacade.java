package com.sap.xsk.hdb.ds.facade;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.manager.XSKDataStructureManager;
import com.sap.xsk.hdb.ds.manager.XSKEntityManagerService;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntitiesModel;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Singleton
public class XSKHDBCoreFacade {

    private static final Logger logger = LoggerFactory.getLogger(XSKHDBCoreFacade.class);

    @Inject
    private Map<String, XSKDataStructureManager<XSKDataStructureModel>> managerServices;
    @Inject
    private XSKDataStructuresCoreService dataStructuresCoreService;
    @Inject
    private XSKEntityManagerService xskEntityManagerService;

    public void handleResourceSynchronization(IResource resource) throws SynchronizationException, XSKDataStructuresException {
        String resourceName = resource.getName();
        String registryPath = getRegistryPath(resource);

        String contentAsString = getContent(resource);
        XSKDataStructureModel dataStructureModel;
        try {
            dataStructureModel = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_ENTITIES, registryPath, contentAsString);
        } catch (XSKDataStructuresException e) {
            logger.error("Synchronized artifact is not valid");
            logger.error(e.getMessage());
            return;
        } catch (Exception e) {
            throw new SynchronizationException(e);
        }

        dataStructureModel.setLocation(registryPath);
        managerServices.get(IXSKDataStructureModel.FILE_EXTENSION_ENTITIES).synchronizeRuntimeMetadata(dataStructureModel);
        this.xskEntityManagerService.synchronizeRuntimeMetadata((XSKDataStructureEntitiesModel) dataStructureModel);
    }

    private String getRegistryPath(IResource resource) {
        String resourcePath = resource.getPath();
        return resourcePath.startsWith("/registry/public") ? resourcePath.substring("/registry/public".length()) : resourcePath;
    }

    private String getContent(IResource resource) throws SynchronizationException {
        byte[] content = resource.getContent();
        String contentAsString;
        try {
            contentAsString = IOUtils.toString(new InputStreamReader(new ByteArrayInputStream(content), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new SynchronizationException(e);
        }
        return contentAsString;
    }
}
