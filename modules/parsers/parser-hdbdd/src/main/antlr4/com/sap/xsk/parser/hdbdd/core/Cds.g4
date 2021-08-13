grammar Cds;
cdsFile: namespaceRule
         usingRule*
         contextRule?;

namespaceRule: 'namespace' members+=ID ('.' members+=ID)* ';';
usingRule: 'using'   pack+=ID ('.' pack+=ID)* '::' members+=ID ('.' members+=ID)* ('as' alias=ID)?  ';';

contextRule: annotationRule* 'context' ID '{' (contextRule | entityRule | structuredDataTypeRule | dataTypeRule)* '}' ';';
entityRule: annotationRule* 'entity'   ID '{' (association | elementDeclRule)* '}' ';';
structuredDataTypeRule: annotationRule* 'type' ID '{' fieldDeclRule* '}' ';';
dataTypeRule: 'type' ID ':' typeAssignRule ';';
fieldDeclRule: ID ':' typeAssignRule ';';
typeAssignRule: ref=ID '(' args+=INTEGER (',' args+=INTEGER)* ')'           # AssignBuiltInTypeWithArgs
                | TYPE_OF? pathSubMembers+=ID ('.'pathSubMembers+=ID)*      # AssignType
                ;
elementDeclRule: annotationRule* (key='key')? ID ':' typeAssignRule defaultValue? elementConstraints? ';';
elementConstraints: NULL | NOT_NULL;

association: ID ':' 'Association' cardinality?  'to' associationTarget (managedForeignKeys | unmanagedForeignKey)* ';';
associationTarget: pathSubMembers+=ID ('.' pathSubMembers+=ID)*  ;
unmanagedForeignKey: 'on' pathSubMembers+=ID ('.' pathSubMembers+=ID)* '=' source=ID;
managedForeignKeys: '{' foreignKey (',' foreignKey)* '}';
foreignKey: pathSubMembers+=ID ('.' pathSubMembers+=ID)*;
cardinality:  '[' ASSOCIATION_MIN (max=INTEGER | many='*') ']'   # MinMaxCardinality
              | '[' (max=INTEGER | many='*') ']'                 # MaxCardinality
              |  '[' ']'                                         # NoCardinality
              ;

defaultValue: 'default' value=(STRING | BOOLEAN);

annotationRule: '@' ID ':' annValue                       #AnnObjectRule
              | '@' annId=ID '.' prop=ID ':' annValue     #AnnPropertyRule
              | '@' ID                                    #AnnMarkerRule
              ;

annValue:  arrRule | enumRule | obj | literal=(STRING | BOOLEAN);
enumRule: '#' ID;
arrRule: '[' annValue (',' annValue)* ']';
obj: '{' keyValue (',' keyValue)* '}';
keyValue: ID ':' annValue;


DEFAULT: 'default';
KEY: 'key';
ASSOCIATION_MIN: INTEGER '..' ;
ASSOCIATION: 'Association';
BOOLEAN: 'true' | 'false';
ID: ([a-z] | [A-Z])(([a-z] | [A-Z])+ | INTEGER)*;


SEMICOLUMN: ';';
INTEGER: SignedInteger;
DECIMAL: DecimalFloatingPointLiteral;
LOCAL_TIME: LocalTime;
LOCAL_DATE: LocalDate;
STRING: '\'' (~["\\\r\n] | EscapeSequence)*? '\'' { setText(getText().substring(1, getText().length() - 1)); };
TYPE_OF: 'type' WS 'of';


NULL: 'null';
NOT_NULL: 'not' WS NULL;
NOT: 'not';


WS : [ \\\t\r\n]+ -> skip;

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

fragment Date: Digit[4] '-' Digit[2] '-' Digit[2];
fragment Time: Digit[2] ':' Digit[2] (':' Digit[2])?;
fragment TimeWithPrecision: Digit[2] ':' Digit[2] ':' Digit[2] ('.' Digit[1-7])?;
