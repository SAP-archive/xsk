<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:exsl="http://exslt.org/common">

  <!-- Remove from specified children. -->
  <xsl:variable name="remove">
  </xsl:variable>

  <!-- Match "type" attribute. -->
  <xsl:template match="DataSource/@type">
    <xsl:if test="not(exsl:node-set($remove))">
      <xsl:copy/>
    </xsl:if>
  </xsl:template>

  <!-- Copy all nodes and attributes unless another rule indicates otherwise. -->
  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
</xsl:stylesheet>
