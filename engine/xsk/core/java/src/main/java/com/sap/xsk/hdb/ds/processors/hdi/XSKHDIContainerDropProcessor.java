package com.sap.xsk.hdb.ds.processors.hdi;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdi.XSKDataStructureHDIModel;

public class XSKHDIContainerDropProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(XSKHDIContainerDropProcessor.class);
	
	private static XSKHDIContainerDropProcessor INSTANCE = new XSKHDIContainerDropProcessor();
	
	private XSKGrantPrivilegesContainerGroupAPIProcessor grantPrivilegesContainerGroupAPIProcessor = new XSKGrantPrivilegesContainerGroupAPIProcessor();
	private XSKCreateContainerGroupProcessor createContainerGroupProcessor = new XSKCreateContainerGroupProcessor();
	private XSKGrantPrivilegesContainerGroupProcessor grantPrivilegesContainerGroupProcessor = new XSKGrantPrivilegesContainerGroupProcessor();
	private XSKCreateContainerProcessor createContainerProcessor = new XSKCreateContainerProcessor();
	private XSKGrantPrivilegesContainerAPIProcessor grantPrivilegesContainerAPIProcessor = new XSKGrantPrivilegesContainerAPIProcessor();
	private XSKWriteContainerContentProcessor writeContainerContentProcessor = new XSKWriteContainerContentProcessor();
	private XSKConfigureLibrariesProcessor configureLibrariesProcessor = new XSKConfigureLibrariesProcessor();
	private XSKDeployContainerContentProcessor deployContainerContentProcessor = new XSKDeployContainerContentProcessor();
	private XSKGrantPrivilegesContainerSchemaProcessor grantPrivilegesContainerSchemaProcessor = new XSKGrantPrivilegesContainerSchemaProcessor();
	
	
	private XSKHDIContainerDropProcessor() {}

    public static void execute(Connection connection, List<XSKDataStructureHDIModel> hdiModels) {
    	
    	if (hdiModels.isEmpty()) {
    		return;
    	}
    	
    	logger.info("Start processing DROP of HDI Containers...");
    	
    	// TODO
    	
    	logger.info("Done processing DROP of HDI Containers");
    }

}
