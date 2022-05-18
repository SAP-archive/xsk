grammar Hdbtable;

hdbtableDefinition: hdbtableProperties+;
hdbtableProperties: schemaNameProp | temporaryProp | tableTypeProp | publicProp | loggingTypeProp | tableColumnsProp | tableIndexesProp | tablePrimaryKeyProp | tablePrimaryKeyIndexTypeProp | descriptionProp;
schemaNameProp: TABLE DOT 'schemaName' EQ STRING SEMICOLON ;
temporaryProp: TABLE DOT 'temporary' EQ BOOLEAN SEMICOLON;
tableTypeProp: TABLE DOT 'tableType' EQ TABLETYPE SEMICOLON ;
publicProp: TABLE DOT 'public' EQ BOOLEAN SEMICOLON;
loggingTypeProp: TABLE DOT 'loggingType' EQ TABLELOGGINGTYPE SEMICOLON;
tableColumnsProp: TABLE DOT 'columns' EQ '[' (columnsObject (',' columnsObject)*)? ']' SEMICOLON ;
tableIndexesProp: TABLE DOT 'indexes' EQ '[' (indexesObject (',' indexesObject)*)? ']' SEMICOLON ;
tablePrimaryKeyProp: TABLE DOT 'primaryKey' DOT 'pkcolumns' EQ  tablePrimaryKeyColumnsProp SEMICOLON ;
tablePrimaryKeyColumnsProp: '['  STRING(',' STRING)* ']';
tablePrimaryKeyIndexTypeProp: TABLE DOT 'primaryKey' DOT 'indexType' EQ INDEXTYPE SEMICOLON;
descriptionProp: TABLE DOT 'description' EQ STRING SEMICOLON ;
columnsObject: '{' columnsProperties+ '}';
columnsProperties:
        columnAssignName |
        columnAssignSQLType |
        columnAssignUnique |
        columnAssignLength |
        columnAssignNullable |
        columnAssignComment |
        columnAssignDefaultValue |
        columnAssignPrecision |
        columnAssignScale;
indexesObject: '{' indexProperties+ '}';
indexProperties:
        indexAssignName |
        indexAssignUnique |
        indexAssignOrder |
        indexAssignIndexColumns |
        indexAssignIndexType;
columnAssignName: 'name' EQ STRING SEMICOLON;
columnAssignSQLType: 'sqlType' EQ SQLTYPES SEMICOLON;
columnAssignNullable: 'nullable' EQ BOOLEAN SEMICOLON;
columnAssignUnique: 'unique' EQ BOOLEAN SEMICOLON;
columnAssignLength: 'length' EQ INT SEMICOLON;
columnAssignComment: 'comment' EQ STRING SEMICOLON;
columnAssignDefaultValue: 'defaultValue' EQ (STRING | INT | DATETIMEDEFAULTVALUES) SEMICOLON;
columnAssignPrecision: 'precision' EQ INT SEMICOLON;
columnAssignScale: 'scale' EQ INT SEMICOLON;
indexAssignName: 'name' EQ STRING SEMICOLON;
indexAssignUnique: 'unique' EQ BOOLEAN SEMICOLON;
indexAssignOrder: 'order' EQ ORDER SEMICOLON;
indexAssignIndexColumns: 'indexColumns' EQ indexColumnsArray ;
indexAssignIndexType: 'indexType' EQ INDEXTYPE SEMICOLON;
indexColumnsArray: '[' (STRING(',' STRING)*)? ']' SEMICOLON;

fragment ESCAPED_QUOTE : '\\"';
STRING : '"' ( ESCAPED_QUOTE | ~('\n'|'\r') )*? '"'; //match anything in "..." including escapted quotes

WS : [ \t\r\n]+ -> skip ; //toss out whitespace
TABLE : 'table';
DOT : '.' ;
EQ : '=' ;
SEMICOLON : ';' ;
SQLTYPES: 'VARCHAR' | 'INTEGER' | 'NVARCHAR' | 'DECIMAL' | 'DATE' | 'TIME' | 'TIMESTAMP' | 'SECONDDATE' |
            'TINYINT' | 'SMALLINT' | 'BIGINT' | 'REAL' | 'DOUBLE' | 'FLOAT' | 'SMALLDECIMAL' |
            'CLOB' | 'NCLOB' | 'ALPHANUM' | 'TEXT' | 'SHORTTEXT' | 'BLOB' | 'VARBINARY' ;
BOOLEAN: 'true' | 'false' ;
ORDER: 'ASC' | 'DSC' ;
INDEXTYPE: 'B_TREE' | 'CPB_TREE' ;
INT: [0-9]+;
TABLETYPE: 'COLUMNSTORE'|'ROWSTORE';
TABLELOGGINGTYPE: 'LOGGING' | 'NOLOGGING';
DATETIMEDEFAULTVALUES: 'CURRENT_DATE'
                        | 'CURRENT_TIME'
                        | 'CURRENT_TIMESTAMP'
                        | 'CURRENT_UTCDATE'
                        | 'CURRENT_UTCTIME'
                        | 'CURRENT_UTCTIMESTAMP';

LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ; // Match "//" stuff '\n'
COMMENT : '/*' .*? '*/' -> skip ; // Match "/*" stuff "*/"