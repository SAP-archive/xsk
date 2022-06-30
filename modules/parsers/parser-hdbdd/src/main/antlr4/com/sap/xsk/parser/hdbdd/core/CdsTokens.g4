lexer grammar CdsTokens;

NAMESPACE: N A M E S P A C E;

AS: A S;
ON: O N;
SELECT: S E L E C T;
FROM: F R O M;
WHERE: W H E R E;
DEFINE: D E F I N E;
UNION: U N I O N;
DISTINCT: D I S T I N C T;
HANA: 'hana';

JOIN_TYPES: (INNER_JOIN) | (LEFT_JOIN) | (LEFT_OUTER_JOIN) | (RIGHT_OUTER_JOIN) | (FULL_OUTER_JOIN) | (REFERNTIAL_JOIN) | (TEXT_JOIN) | (J O I N);

INNER_JOIN: (I N N E R ' ' J O I N);
LEFT_JOIN: (L E F T ' ' J O I N);
LEFT_OUTER_JOIN: (L E F T ' ' O U T E R ' ' J O I N);
RIGHT_OUTER_JOIN: (R I G H T ' ' O U T E R ' ' J O I N);
FULL_OUTER_JOIN: (F U L L ' ' O U T E R ' ' J O I N);
REFERNTIAL_JOIN: (R E F E R E N T I A L ' ' J O I N);
TEXT_JOIN: (T E X T ' ' J O I N);

DATETIME_VALUE_FUNCTION: ( 'CURRENT_DATE' | 'CURRENT_TIME' | 'CURRENT_TIMESTAMP' | 'CURRENT_UTCDATE' | 'CURRENT_UTCTIME' | 'CURRENT_UTCTIMESTAMP' );

USING: U S I N G;
NULL: 'null';

CONCATENATION: '||';
NOT_EQUAL_TO: '<>';

DEFAULT: D E F A U L T;
ASSOCIATION_MIN: INTEGER '..';
BOOLEAN: 'true' | 'false';

CONTEXT: C O N T E X T;
ENTITY: E N T I T Y;
VIEW : V I E W;
TYPE: T Y P E;

ASSOCIATION: A S S O C I A T I O N;
TO: T O;

ID: IdCharacters | EscapedIdCharactes;

SEMICOLUMN: ';';
INTEGER: SignedInteger;
DECIMAL: DecimalFloatingPointLiteral;
LOCAL_TIME: LocalTime;
LOCAL_DATE: LocalDate;
UTC_DATE_TIME: UTCDateTime;
UTC_TIMESTAMP: UTCTimestamp;
STRING: '\'' (~["\\\r\n] | EscapeSequence)*? '\'' { setText(getText().substring(1, getText().length() - 1)); };
VARBINARY: X '\'' ((A | B | C | D | E | F) | INTEGER)* '\'' { setText(getText().substring(1, getText().length() - 1)); };
TYPE_OF: 'type' WS 'of';
WS : [ \\\t\r\n]+ -> skip;

LINE_COMMENT1        : '//' .*? '\r'? '\n' -> skip ; // Match "//" stuff '\n'
LINE_COMMENT2        : '/*' .*? '*/' -> skip ; // Match "/* */" stuff

fragment IdCharacters : ([a-z] | [A-Z])(([a-z] | [A-Z])+ | INTEGER | '_' | '-')*;
fragment EscapedIdCharactes: '"' (~["\\\r\n] | EscapeSequence)*? '"';
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
fragment Digits: [0-9]+;
fragment Digit: [0-9];
fragment Sign: '-';

fragment
DecimalFloatingPointLiteral
	:	SignedInteger '.' Digits? ExponentPart?
	|	SignedInteger ExponentPart
	;

fragment
ExponentPart
	:	ExponentIndicator SignedInteger
	;

fragment
ExponentIndicator
	:	[eE]
	;

fragment
SignedInteger
	:	Sign? Digits
	;
fragment LocalDate: 'date' '\'' Date '\'';
fragment LocalTime: 'time' '\'' Time '\'';
fragment UTCDateTime: 'timestamp' '\'' Date Time '\'';
fragment UTCTimestamp: 'timestamp' '\'' Date TimeWithPrecision '\'';

fragment Date: Digit[4] '-' Digit[2] '-' Digit[2];
fragment Time: Digit[2] ':' Digit[2] (':' Digit[2])?;
fragment TimeWithPrecision: Digit[2] ':' Digit[2] ':' Digit[2] ('.' Digit[1-7])?;

A : 'A'|'a';
B : 'B'|'b';
C : 'C'|'c';
D : 'D'|'d';
E : 'E'|'e';
F : 'F'|'f';
G : 'G'|'g';
H : 'H'|'h';
I : 'I'|'i';
J : 'J'|'j';
K : 'K'|'k';
L : 'L'|'l';
M : 'M'|'m';
N : 'N'|'n';
O : 'O'|'o';
P : 'P'|'p';
Q : 'Q'|'q';
R : 'R'|'r';
S : 'S'|'s';
T : 'T'|'t';
U : 'U'|'u';
V : 'V'|'v';
W : 'W'|'w';
X : 'X'|'x';
Y : 'Y'|'y';
Z : 'Z'|'z';