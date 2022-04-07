grammar Cds;
cdsFile: namespaceRule
         usingRule*
         topLevelSymbol;

namespaceRule: NAMESPACE members+=identifier ('.' members+=identifier)* ';';
usingRule: USING pack+=identifier ('.' pack+=identifier)* '::' members+=identifier ('.' members+=identifier)* (AS alias=identifier)?  ';';
topLevelSymbol: dataTypeRule* | contextRule? | structuredTypeRule? | entityRule? | viewRule? ;

dataTypeRule: annotationRule* artifactType=TYPE artifactName=identifier ':' typeAssignRule ';';
contextRule: annotationRule* artifactType=CONTEXT artifactName=identifier '{' (contextRule | dataTypeRule | structuredTypeRule | entityRule | viewRule)* '}' ';'?;
structuredTypeRule: annotationRule* artifactType=TYPE artifactName=identifier '{' fieldDeclRule* '}' ';'?;
entityRule: annotationRule* artifactType=ENTITY artifactName=identifier '{' ( elementDeclRule | association)* '}' ';'?;
viewRule: annotationRule* DEFINE? artifactType=VIEW artifactName=identifier AS selectRule*;

fieldDeclRule: (identifier | '"' identifier '"') ':' typeAssignRule ';';
typeAssignRule: ref=identifier '(' args+=INTEGER (',' args+=INTEGER)* ')'                # AssignBuiltInTypeWithArgs
                | hanaType=BUILT_IN_HANA_TYPE                                            # AssignHanaType
                | hanaType=BUILT_IN_HANA_TYPE '(' args+=INTEGER (',' args+=INTEGER)* ')' # AssignHanaTypeWithArgs
                | TYPE_OF? pathSubMembers+=identifier ('.'pathSubMembers+=identifier)*   # AssignType
                ;
elementDeclRule: annotationRule* (key=identifier)? (name=identifier | '"' name=identifier '"') ':' typeAssignRule elementDetails* ';';
elementDetails: defaultValue | elementConstraints;
elementConstraints: constraints;
associationConstraints: constraints;
constraints: 'null' | 'not null' | 'NULL' | 'NOT NULL';

association: (key=identifier)? ascId=identifier ':' ASSOCIATION cardinality? TO associationTarget (managedForeignKeys | unmanagedForeignKey)* associationConstraints? ';';
associationTarget: pathSubMembers+=identifier ('.' pathSubMembers+=identifier)*;
unmanagedForeignKey: ON pathSubMembers+=identifier ('.' pathSubMembers+=identifier)* '=' source=identifier;
managedForeignKeys: '{' foreignKey (',' foreignKey)* '}';
foreignKey: pathSubMembers+=identifier ('.' pathSubMembers+=identifier)* (AS alias=identifier)?;
cardinality:  '[' ASSOCIATION_MIN (max=INTEGER | many='*') ']'   # MinMaxCardinality
              | '[' (max=INTEGER | many='*') ']'                 # MaxCardinality
              |  '[' ']'                                         # NoCardinality
              ;

defaultValue: DEFAULT value=(STRING | INTEGER | DECIMAL | LOCAL_TIME | LOCAL_DATE | UTC_DATE_TIME | UTC_TIMESTAMP | VARBINARY | DATETIME_VALUE_FUNCTION | NULL);

annotationRule: '@' identifier ':' annValue                               #AnnObjectRule
              | '@' annId=identifier ('.' prop=identifier)* ':' annValue  #AnnPropertyRule
              | '@' identifier                                            #AnnMarkerRule
              ;

annValue:  arrRule | enumRule | obj | literal=(STRING | BOOLEAN);
enumRule: '#' identifier;
arrRule: '[' annValue (',' annValue)* ']';
obj: '{' keyValue (',' keyValue)* '}';
keyValue: identifier ':' annValue;

selectRule: isUnion=UNION? SELECT FROM dependsOnTable=identifier ((AS dependingTableAlias=identifier) | dependingTableAlias=identifier)? joinRule* isDistinct=DISTINCT? '{' selectedColumnsRule '}' ';'? (WHERE whereRule ';'?)?;
joinRule: joinType=JOIN_TYPES joinArtifactName=identifier ((AS joinTableAlias=identifier) | joinTableAlias=identifier)? joinFields;
joinFields: .*?;
selectedColumnsRule: .*?;
whereRule: .*?;

identifier: ID | CONTEXT | ENTITY | TYPE | VIEW | ASSOCIATION | TO | JOIN_TYPES;

NAMESPACE: N A M E S P A C E;

AS: A S;
ON: O N;
SELECT: S E L E C T;
FROM: F R O M;
WHERE: W H E R E;
DEFINE: D E F I N E;
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

BUILT_IN_HANA_TYPE: 'hana.' ID;

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