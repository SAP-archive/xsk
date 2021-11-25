## Samples

This folder contains all the available sample applications, that can be used as reference.

* non-HDI related
  * [hdb-simple](hdb-simple) - a single `hdbtable` artefact
  * [hdb-view](hdb-view) - a single `hdbview` artefact
  * [xsjs-simple](xsjs-simple) - a single `xsjs` service of type *hello world*
  * [xsodata-simple](xsodata-simple) - a single `xsodata` service with one entity and a table underneath
  * [products](products) - a more complex sample containing `hdbdd` and `hdbti` for data structure and content, `xsodata` for REST services exposure and `UI5` user interface. It contains also `xsaccess` definition for authorization management. `xsjob` is showing how to schedule a job which can regularly can call an operation from a xsjs service. `xsjs` and `xsjslib` dependency management is also presented.
* HDI related
  * [hdi-simple](hdi-simple) - a group of artefacts - `hdbtable` (new format) and `hdbcalculationview` deployed as a HDI container
  * [hdi-ext](hdi-ext) - hybrid scenario where the `hdbtable` and its data - `hdbti` and `csv`, are deployed on the target schema (e.g. XSK_SAMPLES_HDI_EXT) and the `hdbcalculationview` is deployed as a HDI container. There is a need of a `hdbsynonym` artefact within the HDI container as well to make the table visible to the hdbcalculationview
  * [hdi-cube](hdi-cube) - similar as the above sample, but the `hdbcalculationview` is of type *CUBE*, so that it can be visible and used in SAP Analytics Cloud
