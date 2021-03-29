grammar Hdbsequence;

hdbsequence: schema
             increment_by
             start_with
             maxvalue?
             nomaxvalue
             minvalue?
             nominvalue
             cycles?
             reset_by?
             publicc
             depends_on_table?
             depends_on_view?
             depends_on? ;

schema: 'schema' EQ STRING SC;
increment_by: 'increment_by' EQ  INT SC;

start_with: 'start_with' EQ INT SC;
maxvalue: 'maxvalue' EQ INT SC;
nomaxvalue:'nomaxvalue' EQ BOOLEAN SC;
minvalue:'minvalue' EQ INT SC;
nominvalue:'nominvalue' EQ BOOLEAN SC;
cycles:'cycles' EQ BOOLEAN SC;
reset_by:'reset_by' EQ STRING SC;
publicc:'public' EQ BOOLEAN SC;
depends_on_table: 'depends_on_table' EQ STRING SC;
depends_on_view:'depends_on_view' EQ STRING SC;
depends_on: 'depends_on' EQ depends_on_list SC;
depends_on_list: LB (STRING (',' STRING)*)? RB;

STRING: '"' (~["\\\r\n] | EscapeSequence)* '"';
INT :  SIGNED_INT? [0-9]+;
BOOLEAN: TRUE | FALSE;

TRUE: 'true';
FALSE: 'false';

WS  :   [ \t\\\r\n]+ -> skip ; // toss out whitespace
LB: '[';
RB: ']';
EQ: '=';
SC: ';';
SIGNED_INT: '-';
LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ; // Match "//" stuff '\n'
COMMENT : '/*' .*? '*/' -> skip ; // Match "/*" stuff "*/"

fragment EscapeSequence
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;
fragment HexDigits
    : HexDigit ((HexDigit | '_')* HexDigit)?
    ;
fragment HexDigit
    : [0-9a-fA-F]
    ;