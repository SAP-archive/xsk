package com.sap.xsk.xsjob.ds.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "XSK_JOBS")
public class XSKJobDefinition {
    @Id
    @Column(name = "JOB_NAME", columnDefinition = "VARCHAR", nullable = false, length = 255)
    private String name;

    @Column(name = "JOB_GROUP", columnDefinition = "VARCHAR", nullable = false, length = 255)
    private String group;

    @Column(name = "JOB_DESCRIPTION", columnDefinition = "VARCHAR", nullable = false, length = 1024)
    private String description;

    @Column(name = "MODULE", columnDefinition = "VARCHAR", nullable = false, length = 255)
    private String module;

    @Column(name = "FUNCTION", columnDefinition = "VARCHAR", nullable = false, length = 255)
    private String function;

    @Column(name = "CRON_EXPRESSION", columnDefinition = "VARCHAR", nullable = false, length = 255)
    private String cronExpression;

    @Column(name = "JOB_CREATED_BY", columnDefinition = "VARCHAR", nullable = false, length = 32)
    private String createdBy;

    @Column(name = "JOB_CREATED_AT", columnDefinition = "TIMESTAMP", nullable = false)
    private Timestamp createdAt;

    @Column(name = "PARAMETERS", columnDefinition = "BLOB", nullable = false, length = 2000)
    private byte[] parameters;

    private Map<String, String> parametersAsMap;

    public XSKJobDefinition() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public byte[] getParameters() {
        return parameters;
    }

    public void setParameters(byte[] parameters) {
        this.parameters = parameters;
    }

    public Map<String, String> getParametersAsMap() {
        return parametersAsMap;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public void setParametersAsMap(Map<String, String> parametersAsMap) {
        this.parametersAsMap = parametersAsMap;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cronExpression == null) ? 0 : cronExpression.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((function == null) ? 0 : function.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(parameters);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XSKJobDefinition other = (XSKJobDefinition) obj;
		if (cronExpression == null) {
			if (other.cronExpression != null)
				return false;
		} else if (!cronExpression.equals(other.cronExpression))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (function == null) {
			if (other.function != null)
				return false;
		} else if (!function.equals(other.function))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.equals(parameters, other.parameters))
			return false;
		return true;
	}

}
