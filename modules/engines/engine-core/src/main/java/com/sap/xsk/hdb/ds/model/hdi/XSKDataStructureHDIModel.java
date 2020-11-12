package com.sap.xsk.hdb.ds.model.hdi;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

public class XSKDataStructureHDIModel extends XSKDataStructureModel {
	
	private String configuration;
	
	private String[] users;
	
	private String group;
	
	private String container;
	
	private String[] deploy;
	
	private String[] undeploy;
	
	private String content;
	
	public String getConfiguration() {
		return configuration;
	}
	
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public String[] getUsers() {
		return users;
	}

	public void setUsers(String[] users) {
		this.users = users;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	public String[] getDeploy() {
		return deploy;
	}

	public void setDeploy(String[] deploy) {
		this.deploy = deploy;
	}

	public String[] getUndeploy() {
		return undeploy;
	}

	public void setUndeploy(String[] undeploy) {
		this.undeploy = undeploy;
	}
    
	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
