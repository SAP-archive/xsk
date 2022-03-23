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

defaultValue: DEFAULT value=(STRING | INTEGER | DECIMAL | LOCAL_TIME | LOCAL_DATE | UTC_DATE_TIME | UTC_TIMESTAMP | VARBINARY | DATETIME_VALUE_FUNCTION | NULL);

annotationRule: '@' ID ':' annValue                       #AnnObjectRule
              | '@' annId=ID ('.' prop=ID)* ':' annValue  #AnnPropertyRule
              | '@' ID                                    #AnnMarkerRule
              ;

annValue:  arrRule | enumRule | obj | literal=(STRING | BOOLEAN);
enumRule: '#' ID;
arrRule: '[' annValue (',' annValue)* ']';
obj: '{' keyValue (',' keyValue)* '}';
keyValue: ID ':' annValue;

artifactRule: annotationRule* artifactType=ID artifactName=ID '{' (artifactRule | viewRule | dataTypeRule | fieldDeclRule | elementDeclRule | association)* '}' ';'?;

viewRule: DEFINE? artifactType=VIEW artifactName=ID AS selectRule*;
selectRule: isUnion=UNION? SELECT FROM dependsOnTable=ID ((AS dependingTableAlias=ID) | dependingTableAlias=ID)? joinRule* isDistinct=DISTINCT? '{' selectedColumnsRule '}' ';'? (WHERE whereRule ';'?)?;
joinRule: joinType=JOIN_TYPES joinArtifactName=ID ((AS joinTableAlias=ID) | joinTableAlias=ID)? joinFields;
joinFields: .*?;
selectedColumnsRule: .*?;
whereRule: .*?;

NAMESPACE: N A M E S P A C E;

AS: A S;
ON: O N;
SELECT: S E L E C T;
FROM: F R O M;
WHERE: W H E R E;
DEFINE: D E F I N E;
VIEW: V I E W;
UNION: U N I O N;
DISTINCT: D I S T I N C T;

JOIN_TYPES: (INNER_JOIN) | (LEFT_JOIN) | (LEFT_OUTER_JOIN) | (RIGHT_OUTER_JOIN) | (FULL_OUTER_JOIN) | (REFERNTIAL_JOIN) | (TEXT_JOIN) | (J O I N);

INNER_JOIN: (I N N E R ' ' J O I N);
LEFT_JOIN: (L E F T ' ' J O I N);
LEFT_OUTER_JOIN: (L E F T ' ' O U T E R ' ' J O I N);
RIGHT_OUTER_JOIN: (R I G H T ' ' O U T E R ' ' J O I N);
FULL_OUTER_JOIN: (F U L L ' ' O U T E R ' ' J O I N);
REFERNTIAL_JOIN: (R E F E R E N T I A L ' ' J O I N);
TEXT_JOIN: (T E X T ' ' J O I N);

BUILT_IN_HANA_TYPE: HanaTypePrefix ('VARCHAR' | 'ALPHANUM' | 'SMALLINT' | 'TINYINT' | 'REAL' | 'SMALLDECIMAL' | 'CLOB' | 'BINARY' | 'ST_POINT' | 'ST_GEOMETRY');
DATETIME_VALUE_FUNCTION: ( 'CURRENT_DATE' | 'CURRENT_TIME' | 'CURRENT_TIMESTAMP' | 'CURRENT_UTCDATE' | 'CURRENT_UTCTIME' | 'CURRENT_UTCTIMESTAMP' );

USING: U S I N G;
NULL: 'null';

CONCATENATION: '||';
NOT_EQUAL_TO: '<>';

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

LINE_COMMENT1        : '//' .*? '\r'? '\n' -> skip ; // Match "//" stuff '\n'
LINE_COMMENT2        : '/*' .*? '*/' -> skip ; // Match "/* */" stuff
LINE_COMMENT3        : '--' ~[\r\n]*  -> skip ; // Match "--" stuff

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