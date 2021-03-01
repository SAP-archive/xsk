## hdi-ext

This sample shows the hybrid scenario where the `hdbtable` and its data - `hdbti` and `csv`, are deployed on the target schema 
(e.g. DBADMIN) and the `hdbcalculationview` is deployed as a HDI container.
There is a need of a `hdbsynonym` artefact within the HDI container as well to make the table visible to the hdbcalculationview.
