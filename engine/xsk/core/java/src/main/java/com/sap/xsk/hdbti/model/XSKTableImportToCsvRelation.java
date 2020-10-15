package com.sap.xsk.hdbti.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "XSK_TABLE_IMPORT_TO_CSV")
public class XSKTableImportToCsvRelation {
    @Id
    @GeneratedValue
    @Column(name = "ID", columnDefinition = "BIGINT", length = 32, nullable = false)
    private Long id;

    @Column(name = "CSV_LOCATION", columnDefinition = "VARCHAR", nullable = false)
    private String csv;

    @Column(name = "HDBTI_LOCATION", columnDefinition = "VARCHAR", nullable = false)
    private String hdbti;

    @Column(name="CSV_HASH", columnDefinition = "VARCHAR", nullable = false)
    private String csvHash;

    public XSKTableImportToCsvRelation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCsv() {
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }

    public String getHdbti() {
        return hdbti;
    }

    public void setHdbti(String hdbti) {
        this.hdbti = hdbti;
    }

    public String getCsvHash() {
        return csvHash;
    }

    public void setCsvHash(String csvHash) {
        this.csvHash = csvHash;
    }
}
