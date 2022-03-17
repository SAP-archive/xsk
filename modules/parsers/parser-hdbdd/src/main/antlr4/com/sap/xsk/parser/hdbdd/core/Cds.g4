grammar Cds;
cdsFile: namespaceRule
         usingRule*
         topLevelSymbol?;

namespaceRule: NAMESPACE members+=ID ('.' members+=ID)* ';';
usingRule: USING pack+=ID ('.' pack+=ID)* '::' members+=ID ('.' members+=ID)* (AS alias=ID)?  ';';
topLevelSymbol: dataTypeRule | artifactRule;

dataTypeRule: type=ID name=ID ':' typeAssignRule ';';
fieldDeclRule: (ID | '"' ID '"') ':' typeAssignRule ';';
typeAssignRule: ref=ID '(' args+=INTEGER (',' args+=INTEGER)* ')'                        # AssignBuiltInTypeWithArgs
                | hanaType=BUILT_IN_HANA_TYPE                                            # AssignHanaType
                | hanaType=BUILT_IN_HANA_TYPE '(' args+=INTEGER (',' args+=INTEGER)* ')' # AssignHanaTypeWithArgs
                | TYPE_OF? pathSubMembers+=ID ('.'pathSubMembers+=ID)*                   # AssignType
                ;
elementDeclRule: annotationRule* (key=ID)? (name=ID | '"' name=ID '"') ':' typeAssignRule elementDetails* ';';
elementDetails: defaultValue | elementConstraints;
elementConstraints: 'null' | 'not null' | 'NULL' | 'NOT NULL';

association: ascId=ID ':' ascKeyword=ID cardinality? toKeyword=ID associationTarget (managedForeignKeys | unmanagedForeignKey)* ';';
associationTarget: pathSubMembers+=ID ('.' pathSubMembers+=ID)*;
unmanagedForeignKey: ON pathSubMembers+=ID ('.' pathSubMembers+=ID)* '=' source=ID;
managedForeignKeys: '{' foreignKey (',' foreignKey)* '}';
foreignKey: pathSubMembers+=ID ('.' pathSubMembers+=ID)* (AS alias=ID)?;
cardinality:  '[' ASSOCIATION_MIN (max=INTEGER | many='*') ']'   # MinMaxCardinality
              | '[' (max=INTEGER | many='*') ']'                 # MaxCardinality
              |  '[' ']'                                         # NoCardinality
              ;

defaultValue: DEFAULT value=(STRING | INTEGER | DECIMAL | LOCAL_TIME | LOCAL_DATE | UTC_DATE_TIME | UTC_TIMESTAMP | NULL | VARBINARY);

annotationRule: '@' ID ':' annValue                       #AnnObjectRule
              | '@' annId=ID ('.' prop=ID)* ':' annValue     #AnnPropertyRule
              | '@' ID                                    #AnnMarkerRule
              ;

annValue:  arrRule | enumRule | obj | literal=(STRING | BOOLEAN);
enumRule: '#' ID;
arrRule: '[' annValue (',' annValue)* ']';
obj: '{' keyValue (',' keyValue)* '}';
keyValue: ID ':' annValue;

artifactRule: annotationRule* artifactType=ID artifactName=ID '{' (artifactRule | dataTypeRule | fieldDeclRule | elementDeclRule | association)* '}' ';'?;

NAMESPACE: N A M E S P A C E;
AS: 'as';

BUILT_IN_HANA_TYPE: HanaTypePrefix ('VARCHAR' | 'ALPHANUM' | 'SMALLINT' | 'TINYINT' | 'REAL' | 'SMALLDECIMAL' | 'CLOB' | 'BINARY' | 'ST_POINT' | 'ST_GEOMETRY');

USING: U S I N G;
ON: 'on';
NULL: 'null';

DEFAULT: D E F A U L T;
ASSOCIATION_MIN: INTEGER '..';
BOOLEAN: 'true' | 'false';
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
LINE_COMMENT        : '//' .*? '\r'? '\n' -> skip ; // Match "//" stuff '\n'
LINE_COMMENT2        : '/*' .*? '*/' -> skip ; // Match "/* */" stuff

fragment IdCharacters : ([a-z] | [A-Z])(([a-z] | [A-Z])+ | INTEGER | '_')*;
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

fragment HanaTypePrefix: 'hana.';

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