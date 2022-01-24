# HDI - HANA Deployment Infrastructure

SAP HANA Deployment Infrastructure (HDI) is a service that enables you to deploy database development artifacts to so-called containers. HDI is supported in XSK via the `*.hdi` and `*.hdiconfig` files.


!!! note "SAP Help Portal"

    For more information, see [The SAP HANA Deployment Infrastructure Reference](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/4077972509f5437c85d6a03e01509417.html).

## Overview

To support the deployment of database development artifacts via HDI, create `*.hdi` and `*.hdiconfig` files in your project:

=== "*.hdi"

    ```json
    {
        "configuration": "/hdi-ext/config.hdiconfig",
        "users": ["XSK_SAMPLES_HDI_EXT"],
        "group": "XSK_HDI_EXT_GROUP",
        "container": "XSK_HDI_EXT",
        "deploy": [
        	"/hdi-ext/Customers.hdbsynonym",
        	"/hdi-ext/CustomersCalcView.hdbcalculationview"
        ],
        "undeploy": []
    }
    ```

=== "*.hdiconfig"

    ```json
    {
       "file_suffixes":{
          "hdbsynonym":{
             "plugin_name":"com.sap.hana.di.synonym"
          },
          "hdbpublicsynonym":{
             "plugin_name":"com.sap.hana.di.publicsynonym"
          },
          "hdbcalculationview":{
             "plugin_name":"com.sap.hana.di.calculationview"
          }
       }
    }
    ```

## HDI Plugins

List of HDI supported database development artifacts:

#### [Application Time-Period Table Plug-in (.hdbapplicationtime)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/73c7b80318ba4405a8769e6ceb41ec64.html)
    
```json
"hdbapplicationtime" : {
   "plugin_name" : "com.sap.hana.di.applicationtime"
}
```

#### [AFL Language Procedures (.hdbafllangprocedure)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/7f630904dfe045beb114a6c25896649f.html)

```json
"hdbafllangprocedure" : {
   "plugin_name" : "com.sap.hana.di.afllangprocedure"
}
```

#### [Analytic Privileges (.hdbanalyticprivilege)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/2d3056363053436898fcf5a3141b482f.html)

```json
"hdbanalyticprivilege" : {
   "plugin_name" : "com.sap.hana.di.analyticprivilege"
}
```

#### [Calculation Views (.hdbcalculationview)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/3db2a35e714e4f6e9711fb62997d0c5c.html)

```json
"hdbcalculationview" : {
   "plugin_name" : "com.sap.hana.di.calculationview"
}
```

#### [Constraints (.hdbconstraint)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/bda54706fbda4910908871743b675ad1.html)

```json
"hdbconstraint" : {
   "plugin_name" : "com.sap.hana.di.constraint"
}
```

#### [Copy Only (.txt)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/64459f1b9aa24163bbac5d9229e05aac.html)

```json
"txt" : {
   "plugin_name" : "com.sap.hana.di.copyonly"
}
```

#### [Core Data Services (.hdbcds)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/36257a5f611540f9b2f8e13110ddf97a.html)

```json
"hdbcds" : {
   "plugin_name" : "com.sap.hana.di.cds"
}
```

#### [Document Store Collections (.hdbcollection)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/fe16b635277c4aea825c72973f159359.html)

```json
"hdbcollection" : {
   "plugin_name" : "com.sap.hana.di.collection"
}
```

#### [Enterprise Search Configurations (.hdbeshconfig)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/eb019bb757404a0591182ac7acf05400.html)

```json
"hdbeshconfig": {
   "plugin_name": "com.sap.hana.di.eshconfig"
}
```

#### [Flowgraph (.hdbflowgraph)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/6d4fc4a30e1d428bbb2b3dc5a73ab786.html)

```json
"hdbflowgraph" : {
  "plugin_name"   : "com.sap.hana.di.flowgraph"
}
```

#### [Full-Text Indexes (.hdbfulltextindex)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/c4c24a7b9d174cec8012d832e4c060a6.html)
    
```json
"hdbfulltextindex" : {
   "plugin_name" : "com.sap.hana.di.fulltextindex"
}
```

#### [Functions (.hdbfunction)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/cbf136986c98430ea50ddf4b95bc1efe.html)

```json
"hdbfunction" : {
   "plugin_name" : "com.sap.hana.di.function"
}
```

#### [Graph Workspaces (.hdbgraphworkspace)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/ff893731c8514b098223e4a47a6d5f39.html)

```json
"hdbgraphworkspace" : {
   "plugin_name" : "com.sap.hana.di.graphworkspace"
}
```

#### [Indexes (.hdbindex)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/58fdf2d2ffae44b6a3dd0e9a3f5ae8c5.html)

```json
"hdbindex" : {
   "plugin_name" : "com.sap.hana.di.index"
}
```

#### [Libraries (.hdblibrary)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/7475cf480ad8498c9991c8a9392d6fc7.html)

```json
"hdblibrary" : {
   "plugin_name" : "com.sap.hana.di.library"
}
```

#### [Logical Schema Definition (.hdblogicalschema)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/fa9cda8b540a486dacd12e06f9a60330.html)

```json
"hdblogicalschema" : { 
   "plugin_name" : "com.sap.hana.di.logicalschema"
}
```

#### [Migration Tables (.hdbmigrationtable)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/52d1f5acfa754a7887e21226641eb261.html)

```json
"hdbmigrationtable" : {
   "plugin_name" : "com.sap.hana.di.table.migration"
}
```

#### [Procedures (.hdbprocedure)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/93de88bf2c8242179647e40f958c24e5.html)

```json
"hdbprocedure" : {
   "plugin_name" : "com.sap.hana.di.procedure"
}
```

#### [Projection Views (.hdbprojectionview)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/d8a3392c1287420ca82ac3090cd5049b.html)
    
```json
"hdbprojectionview" : { 
   "plugin_name" : "com.sap.hana.di.projectionview"
}, 
"hdbprojectionviewconfig" : { 
   "plugin_name" : "com.sap.hana.di.projectionview.config"
}
```

#### [Public Synonym (.hdbpublicsynonym)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/d131415cbf7349158d2654e0fcf73487.html)

```json
"hdbpublicsynonym": { 
   "plugin_name" : "com.sap.hana.di.publicsynonym"
}
```

#### [Replication Task (.hdbreptask)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/0194ba9e421148dd9712400e449fc61f.html)

```json
"hdbreptask" : {
   "plugin_name"   : "com.sap.hana.di.reptask"
}
```

#### [Result Cache (.hdbresultcache)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/a3e2b70fc35845e58aff72f992bafca7.html)

```json
"hdbresultcache": { 
   "plugin_name" : "com.sap.hana.di.resultcache"
}
```

#### [Roles (.hdbrole)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/625d7733c30b4666b4a522d7fa68a550.html)

```json
"hdbrole" : { 
   "plugin_name" : "com.sap.hana.di.role"
}, 
"hdbroleconfig" : { 
   "plugin_name" : "com.sap.hana.di.role.config"
}
```

#### [Search Rule Set (.hdbsearchruleset)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/e9d52ba816b04577a450f87032d7dbda.html)

```json
"hdbsearchruleset" : {
   "plugin_name" : "com.sap.hana.di.searchruleset"
}
```

#### [Sequence (.hdbsequence)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/b295c2e0a5d547f8b1717ad7dd52cc90.html)

```json
"hdbsequence" : {
   "plugin_name" : "com.sap.hana.di.sequence"
}
```

#### [SQL Views (.hdbview)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/2bf9a6f2db824fbd84315196a9c318d5.html)

```json
"hdbview" : {
   "plugin_name" : "com.sap.hana.di.view"
}
```

#### [Statistics (.hdbstatistics)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/435423d28f2d40f5979ec17b6141d3f1.html)

```json
"hdbstatistics" : {
   "plugin_name" : "com.sap.hana.di.statistics"
}
```

#### [Structured Privilege (.hdbstructuredprivilege)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/c3827df3a9dc4c45b5b4e2f7b1070b08.html)

```json
"hdbstructuredprivilege" : {
   "plugin_name" : "com.sap.hana.di.structuredprivilege"
}
```

#### [Synonyms (.hdbsynonym and .hdbsynonymconfig)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/aad1653a9b95422089fec53f48c2899e.html)

```json
"hdbsynonym" : { 
   "plugin_name" : "com.sap.hana.di.synonym"
}, 
"hdbsynonymconfig" : { 
   "plugin_name" : "com.sap.hana.di.synonym.config"
}
```

#### [_SYS_BIC Synonym (.hdbsysbicsynonym)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/0bf88516d12b40ee9605b9f75fae568e.html)

```json
"hdbsysbicsynonym": { 
   "plugin_name" : "com.sap.hana.di.sysbicsynonym"
}
```

#### [System Versioning Table (.hdbsystemversioning)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/5794b348711f4963999266a2d2e6f8de.html)

```json
"hdbsystemversioning" : { 
   "plugin_name" : "com.sap.hana.di.systemversioning"
}
```

#### [Tables (.hdbtable and .hdbdropcreatetable)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/453d48e28f6747799546236b4b432e58.html)

```json
"hdbtable" : { 
    "plugin_name" : "com.sap.hana.di.table"
}, 
"hdbdropcreatetable" : {
    "plugin_name" : "com.sap.hana.di.dropcreatetable"
}
```

#### [Table Data (.hdbtabledata)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/35c4dd829d2046f29fc741505302f74d.html)

```json
"hdbtabledata" : {
  "plugin_name" : "com.sap.hana.di.tabledata.properties"
}, 
"csv" : { 
  "plugin_name" : "com.sap.hana.di.tabledata.properties"
}
```

#### [Table Data Properties (.properties)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/f4da2181909f48a6a36904b7f5599780.html)

```json
"properties" : {
   "plugin_name" : "com.sap.hana.di.tabledata.properties"
}, 
"tags" : { 
   "plugin_name" : "com.sap.hana.di.tabledata.properties"
}
```

#### [Table Type (.hdbtabletype)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/83275bd31c1e4acba9285813e64ee12f.html)

```json
"hdbtabletype" : {
   "plugin_name" : "com.sap.hana.di.tabletype"
}
```

#### [Text Analysis Configuration (.hdbtextconfig)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/1c566b54558f497f8f02ecffc96a074b.html)

```json
"hdbtextconfig" : {
   "plugin_name" : "com.sap.hana.di.textconfig" 
}
```

#### [Text Analysis Dictionaries (.hdbtextdict)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/ceb52314393a446cab213789e9638b9a.html)

```json
"hdbtextdict" : {
   "plugin_name" : "com.sap.hana.di.textdictionary"
}
```

#### [Text Analysis Extraction Rules (.hdbtextrule)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/6e69b4ac56214191a93e16e8fb19c296.html)

```json
"hdbtextrule" : {
   "plugin_name" : "com.sap.hana.di.textrule"
}
```

#### [Text Analysis Extraction Rules Includes (.hdbtextinclude)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/d33d03e8e7254c74b3c4fb5029afcb7e.html)

```json
"hdbtextinclude" : {
   "plugin_name" : "com.sap.hana.di.textrule.include"
}
```

#### [Text Analysis Extraction Rules Lexicon (.hdbtextlexicon)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/71f4a8deb2e94f7985770d9d72b10116.html)

```json
"hdbtextlexicon" : {
   "plugin_name" : "com.sap.hana.di.textrule.lexicon"
}
```

#### [Text Mining Configurations (.hdbtextminingconfig)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/00ef3767a5cd474bb71ce955bf6c539a.html)

```json
"hdbtextminingconfig" : {
   "plugin_name" : "com.sap.hana.di.textminingconfig"
}
```

#### [Triggers (.hdbtrigger)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/bbd06f5b88eb4d70a03a25a5c4274ec5.html)

```json
"hdbtrigger" : {
   "plugin_name" : "com.sap.hana.di.trigger"
}
```

#### [Virtual Functions (.hdbvirtualfunction)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/73669ebdcef44bd9ab0eb57de6a8cf2a.html)

```json
"hdbvirtualfunction" : {
   "plugin_name" : "com.sap.hana.di.virtualfunction"
}
"hdbvirtualfunctionconfig" : {
   "plugin_name" : "com.sap.hana.di.virtualfunction.config"
}
```

#### [Virtual Procedures (.hdbvirtualprocedure)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/6c8e6c1b62d54197bdaa786910295665.html)

```json
"hdbvirtualprocedure" : { 
   "plugin_name" : "com.sap.hana.di.virtualprocedure"
}, 
"hdbprojectionviewconfig" : { 
   "plugin_name" : "com.sap.hana.di.virtualprocedure.config"
}
```

#### [Virtual Tables (.hdbvirtualtable)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/08191144d5804c86af9395b110ac37be.html)

```json
"hdbvirtualtable" : {
   "plugin_name" : "com.sap.hana.di.virtualtable"
}, 
"hdbvirtualtableconfig" : { 
   "plugin_name" : "com.sap.hana.di.virtualtable.config"
}
```

#### [Virtual Packages (.hdbvirtualpackage)](https://help.sap.com/viewer/3823b0f33420468ba5f1cf7f59bd6bd9/2.0.06/en-US/28fe43b7c5cc4374b85c67cadc8e9b3f.html)

```json
"hdbvirtualpackagehadoop" : {
   "plugin_name" : "com.sap.hana.di.virtualpackage.hadoop" 
},
"hdbvirtualpackagesparksql" : { 
   "plugin_name" : "com.sap.hana.di.virtualpackage.sparksql"
}
```
