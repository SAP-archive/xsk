grammar Hdbsynonym;

hdbsynonymDefinition:'{' location COLON synonymBody '}';
location: STRING;
synonymBody:'{' '"target"' COLON  synonymTarget ',' synonymSchema '}';
synonymTargetProp:  ('"object"'|'"schema"') COLON  STRING COMMA?;
synonymSchema: '"schema"' COLON  STRING;
synonymTarget: '{' synonymTargetProp synonymTargetProp? '}';
AA : ('"object"'|'"schema"');

STRING : '"' (ESC | ~[{}])*? '"' ;
COMMA : ',' ;
COLON : ':';
WS  :   [ \t\r\n\u000C]+ -> skip;   // toss out whitespace
ESC : '\\"' | '\\\\';   // 2-char sequences \" and \\
LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ; // Match "//" stuff '\n'
COMMENT : '/*' .*? '*/' -> skip ; // Match "/*" stuff "*/"