## Samples

This folder contains all the available sample applications, that can be used as reference.

* [hdb-simple](hdb-simple) - a single hdbtable artefact
* [xsjs-simple](xsjs-simple) - a single xsjs service of type `hello world`
* [hdi-simple](hdi-simple) - a group of artefacts - hdbtable (new format) and hdbcalculationview deployed as a HDI container
* [hdi-ext](hdi-ext) - hybrid scenario where the hdbtable and its data - hdbti and csv, are deployed on the target schema (e.g. DBADMIN) and the hdbcalculationview is deployed as a HDI container. There is a need of a hdbsynonym artefact within the HDI container as well to make the table visible to the hdbcalculationview
* [hdi-cube](hdi-cube) - similar as the above sample, but the hdbcalculationview is of type CUBE, so that it can be visible and used in SAP Analytics Cloud
