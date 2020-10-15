package com.sap.xsk.xscjob.ds.model;

import java.util.Map;

public class XSKJobSchedule {

    private String description;

    private String signature_version;

    private String xscron;

    private Map<String, String> parameter;

    public XSKJobSchedule() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSignature_version() {
        return signature_version;
    }

    public void setSignature_version(String signature_version) {
        this.signature_version = signature_version;
    }

    public String getXscron() {
        return xscron;
    }

    public void setXscron(String xscron) {
        this.xscron = xscron;
    }

    public Map<String, String> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, String> parameter) {
        this.parameter = parameter;
    }
}
