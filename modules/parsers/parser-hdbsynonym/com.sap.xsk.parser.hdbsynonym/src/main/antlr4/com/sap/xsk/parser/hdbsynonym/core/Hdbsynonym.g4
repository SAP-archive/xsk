grammar Hdbsynonym;

hdbsynonymDefinition:'{' location COLON synonymBody '}';
location: STRING;
synonymBody:'{' synonymElement COMMA synonymElement'}';
synonymElement: (synonymTarget | synonymSchema);
synonymTarget : '"target"' COLON  '{' synonymTargetProp+ '}' COMMA?;
synonymTargetProp:  ('"object"'|'"schema"') COLON  STRING COMMA?;
synonymSchema: '"schema"' COLON  STRING COMMA?;

STRING : '"' (ESC | ~[{}])*? '"' ;
COMMA : ',' ;
COLON : ':';
ESC : '\\"' | '\\\\';   // 2-char sequences \" and \\

WS  :   [ \t\r\n\u000C]+ -> skip;   // toss out whitespace
LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ; // Match "//" stuff '\n'
COMMENT : '/*' .*? '*/' -> skip ; // Match "/*" stuff "*/"