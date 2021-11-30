## hdi-cube

This sample representing the hybrid scenario where the `hdbtable` and its data - `hdbti` and `csv`, are deployed on the target schema 
(e.g. XSK_SAMPLES_HDI_CUBE) and the `hdbcalculationview` is deployed as a HDI container. 
There is a need of a `hdbsynonym` artefact within the HDI container as well to make the table visible to the hdbcalculationview.
the `hdbcalculationview` is of type *CUBE*, so that it can be visible and used in SAP Analytics Cloud
