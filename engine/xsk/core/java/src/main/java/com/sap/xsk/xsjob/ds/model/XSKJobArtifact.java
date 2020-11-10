package com.sap.xsk.xsjob.ds.model;

import java.util.List;

public class XSKJobArtifact {

    private String description;

    private String action;

    private List<XSKJobSchedule> schedules;

    public XSKJobArtifact() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<XSKJobSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<XSKJobSchedule> schedules) {
        this.schedules = schedules;
    }
}
