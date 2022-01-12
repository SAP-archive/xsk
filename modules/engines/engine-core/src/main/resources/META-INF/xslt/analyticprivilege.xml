<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:analyticPrivilegeTransformer="com.sap.xsk.modificators.XSKProjectFilesModificator">

    <xsl:template match="node()|@*">
        <xsl:copy>
          <xsl:apply-templates select="node()|@*"/>
      </xsl:copy>
    </xsl:template>

    <xsl:template match="securedModels/modelUri">
        <xsl:copy>
         <xsl:value-of select="analyticPrivilegeTransformer:processModelUri(string(.))"/>
      </xsl:copy>
    </xsl:template>

    <xsl:template match="whereSql">
        <xsl:copy>
         <xsl:value-of select="analyticPrivilegeTransformer:processWhereSql(string(.))"/>
      </xsl:copy>
    </xsl:template>
</xsl:stylesheet>