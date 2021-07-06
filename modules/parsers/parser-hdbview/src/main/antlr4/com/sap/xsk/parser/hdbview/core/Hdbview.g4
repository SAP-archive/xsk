grammar Hdbview;

hdbviewDefinition: property+;
property: schemaProp | publicProp | queryProp | dependsOnProp | dependsOnTable | dependsOnView;

schemaProp: 'schema' EQ STRING SEMICOLON;
publicProp: 'public' EQ BOOLEAN SEMICOLON;
queryProp:  'query' EQ STRING SEMICOLON;
dependsOnProp:  'depends_on' EQ '[' (STRING (',' STRING)*)? ']' SEMICOLON ;
dependsOnTable: 'depends_on_table' EQ '[' (STRING (',' STRING)*)? ']' SEMICOLON ;
dependsOnView:  'depends_on_view'  EQ '[' (STRING (',' STRING)*)? ']' SEMICOLON ;

BOOLEAN: 'true' | 'false' ;
STRING:  '"' (ESC|.)*? '"';
EQ : '=' ;
SEMICOLON : ';' ;
WS  :   [ \t\r\n\u000C]+ -> skip;   // toss out whitespace
ESC : '\\"' | '\\\\';   // 2-char sequences \" and \\
LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ; // Match "//" stuff '\n'
COMMENT : '/*' .*? '*/' -> skip ; // Match "/*" stuff "*/"