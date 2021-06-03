grammar Hdbschema;

hdbschemaDefinition: property+;
property: schemaProp;

schemaProp: 'schema' EQ STRING SEMICOLON;

STRING:  '"' (ESC|.)*? '"';
EQ : '=' ;
SEMICOLON : ';' ;
COMMA : ',' ;
WS  :   [ \t\r\n\u000C]+ -> skip;   // toss out whitespace
ESC : '\\"' | '\\\\';   // 2-char sequences \" and \\