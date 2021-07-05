grammar Hdbschema;

hdbschemaDefinition: schemaNameProp;
schemaNameProp: 'schema_name' EQ STRING SEMICOLON;

STRING:  '"' (ESC|.)*? '"';
EQ : '=' ;
SEMICOLON : ';' ;
COMMA : ',' ;
WS  :   [ \t\r\n\u000C]+ -> skip;   // toss out whitespace
ESC : '\\"' | '\\\\';   // 2-char sequences \" and \\
LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ; // Match "//" stuff '\n'
COMMENT : '/*' .*? '*/' -> skip ; // Match "/*" stuff "*/"