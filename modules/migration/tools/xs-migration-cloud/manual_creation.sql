CREATE COLUMN TABLE "erp_schema"."ERP_TABLE" ("Col1" DATE CS_DAYDATE, "Col2" NVARCHAR(50), "Col3" NVARCHAR(50)) UNLOAD PRIORITY 5  AUTO MERGE ;
CREATE SCHEMA MINI;
insert into "_SYS_BI"."M_SCHEMA_MAPPING" values('TEST_MAPPING','SIMPLE_SCHEMA');
insert into "_SYS_BI"."M_SCHEMA_MAPPING" values('SAP_ERP','erp_schema');
insert into "_SYS_BI"."M_SCHEMA_MAPPING" values('SAP_CUAN','SIMPLE_SCHEMA');

CREATE ROW TABLE "SIMPLE_SCHEMA"."TEST_TAB"  ( "ID" INT CS_INT ) ;

insert into "_SYS_REPO"."ACTIVE_TAGS" values('com.sap.migration.testing.db','calcviewtest','calculationview','TEST','TESTValue');

insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('com.sap.migration.testing.i18n', 'simple_bundle', 'hdbtextbundle', 'welcome', 'de_DE',  'Willkommen');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('com.sap.migration.testing.i18n', 'simple_bundle', 'hdbtextbundle', 'console', 'de_DE',  'Bestellungskonsole');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('com.sap.migration.testing.i18n', 'simple_bundle', 'hdbtextbundle', 'attendee', 'de_DE',  'TechEd Besucher');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('com.sap.migration.testing.i18n', 'simple_bundle', 'hdbtextbundle', 'personalize', 'de_DE',  'Personalisieren');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('com.sap.migration.testing.i18n', 'simple_bundle', 'hdbtextbundle', 'help', 'de_DE',  'Hilfe');


insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'Longitude', 'en',  'Longitude (en)');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'CustomerCategory', '',  'CustomerCategory Desc fr_CA');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'CustomerGroup', '',  'CustomerGroup');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'CustomerWithSuffix', '',  'CustomerWithSuffix');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'Join_1', '',  ' Text Join 1');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'Latitude', '',  'Latitude Desc en');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'Customer', '',  'Customer Desc fr_CA');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'CustomerText', '',  'CustomerText Desc fr');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'Longitude', '',  'Longitude (en)');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'P_Suffix', '',  'Customer Suffix');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'Region', '',  'Region Desc fr');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'VAR_Customer', '',  'VAR_Customer');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'VAR_CustomerCategory', '',  'VAR_CustomerCategory');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'VAR_CustomerGroup', '',  'VAR_CustomerGroup');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'VAR_Region', '',  'VAR_Region');



insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'Customer', 'XCOL', 120);
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'CustomerCategory', 'XCOL', 120);
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'CustomerGroup', 'XCOL', 120);
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'CustomerText', 'XCOL', 120);
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'CustomerWithSuffix', 'XCOL', 120);
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'Join_1', 'XCOL', 120);
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'Latitude', 'XCOL', 120);
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'Longitude', 'XCOL', 120);
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'P_Suffix', 'XCOL', 120);
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'Region', 'XCOL', 120);
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'VAR_Customer', 'XCOL', 120);
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'VAR_CustomerCategory', 'XCOL', 120);
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'VAR_CustomerGroup', 'XCOL', 120);
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'VAR_Region', 'XCOL', 120);

update "_SYS_REPO"."PACKAGE_CATALOG" set "ORIG_LANG" = 'en_US' where "PACKAGE_ID" = 'com.sap.migration.testing.db.hdbdd';

insert into "_SYS_REPO"."ACTIVE_OBJECT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('mini', 'CvCustomer', 'calculationview', 'caption', '',  'CvCustomer Desc  en_US');
insert into "_SYS_REPO"."ACTIVE_OBJECT_TEXT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "TEXT_TYPE", "MAX_LENGTH") values ('mini', 'CvCustomer', 'calculationview', 'caption', 'XTIT', 255);
insert into "_SYS_REPO"."ACTIVE_OBJECT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'caption', 'de',  '');




insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'DATETIMESTAMP', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'DATE_SQL', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'DATETIME_SAP', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'DATE_SAP', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'QUARTER', 'de', 'This is \nsome multiline \nstuff');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'MONTH', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'WEEK', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'DATETIMESTAMP_1', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'DATE_SQL_1', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'DATETIME_SAP_1', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'DATE_SAP_1', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'YEAR_1', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'QUARTER_1', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'MONTH_1', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'WEEK_1', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'DAY_OF_WEEK', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'DAY', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'WEEK_YEAR', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'HOUR_1', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'MINUTE_1', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'SECOND', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'CALQUARTER', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'CALMONTH', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'CALWEEK', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'YEAR_INT', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'QUARTER_INT', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'MONTH_INT', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'WEEK_INT', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'WEEK_YEAR_INT', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'DAY_OF_WEEK_INT', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'HOUR_INT', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'MINUTE_INT', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'SECOND_INT', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'MONTH_LAST_DAY', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'TZNTSTMPS', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'TZNTSTMPL', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'MINUTE', 'de', 'This \r also \r third line');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'HOUR', 'de', 'This \r\n has \r\n four \n\r lines');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'DAY_INT', 'de', '');
insert into "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT"("PACKAGE_ID", "OBJECT_NAME", "OBJECT_SUFFIX", "TEXT_ID", "LANG", "CONTENT") values ('migration.timedim', 'time_dim_view', 'calculationview', 'YEAR', 'de', 'This stuff \n has some \n lines');
