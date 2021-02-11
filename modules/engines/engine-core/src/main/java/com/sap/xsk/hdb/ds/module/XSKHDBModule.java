package com.sap.xsk.hdb.ds.module;

import com.google.inject.multibindings.MapBinder;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.manager.*;
import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;

public class XSKHDBModule extends AbstractDirigibleModule {

    @Override
    protected void configure() {
        MapBinder<String, XSKDataStructureManager> mapBinder
                = MapBinder.newMapBinder(binder(), String.class, XSKDataStructureManager.class);

        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_ENTITIES).to(XSKEntityManagerService.class);
        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_TABLE).to(XSKTableManagerService.class);
        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_VIEW).to(XSKViewManagerService.class);
        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_HDBTABLEFUNCTION).to(XSKTableFunctionManagerService.class);
        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_HDBSCHEMA).to(XSKSchemaManagerService.class);
        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_HDBPROCEDURE).to(XSKProceduresManagerService.class);
        mapBinder.addBinding(IXSKDataStructureModel.FILE_EXTENSION_HDI).to(XSKHDIContainerManagerService.class);
    }

    @Override
    public String getName() {
        return null;
    }
}
