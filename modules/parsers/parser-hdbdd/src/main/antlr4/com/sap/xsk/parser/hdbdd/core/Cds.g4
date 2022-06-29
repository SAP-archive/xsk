grammar Cds;
import CdsTokens;

cdsFile: namespaceRule
         usingRule*
         topLevelSymbol;

namespaceRule: NAMESPACE members+=identifier ('.' members+=identifier)* SEMICOLUMN;
usingRule: USING pack+=identifier ('.' pack+=identifier)* '::' members+=identifier ('.' members+=identifier)* (AS alias=identifier)?  SEMICOLUMN;
topLevelSymbol: dataTypeRule* | contextRule? | structuredTypeRule? | entityRule? | viewRule? ;

dataTypeRule: annotationRule* artifactType=TYPE artifactName=identifier ':' typeAssignRule SEMICOLUMN;
contextRule: annotationRule* artifactType=CONTEXT artifactName=identifier '{' (contextRule | dataTypeRule | structuredTypeRule | entityRule | viewRule)* '}' SEMICOLUMN?;
structuredTypeRule: annotationRule* artifactType=TYPE artifactName=identifier '{' (fieldDeclRule | association)* '}' SEMICOLUMN?;
entityRule: annotationRule* artifactType=ENTITY artifactName=identifier '{' ( elementDeclRule | association | calculatedAssociation)* '}' SEMICOLUMN?;
viewRule: annotationRule* DEFINE? artifactType=VIEW artifactName=identifier AS selectRule*;

fieldDeclRule: (identifier | '"' identifier '"') ':' typeAssignRule SEMICOLUMN;
typeAssignRule: ref=identifier '(' args+=INTEGER (',' args+=INTEGER)* ')'                 # AssignBuiltInTypeWithArgs
                | HANA '.' hanaType=identifier                                            # AssignHanaType
                | HANA '.' hanaType=identifier '(' args+=INTEGER (',' args+=INTEGER)* ')' # AssignHanaTypeWithArgs
                | TYPE_OF? pathSubMembers+=identifier ('.'pathSubMembers+=identifier)*    # AssignType
                ;
elementDeclRule: annotationRule* (key=identifier)? (name=identifier | '"' name=identifier '"') ':' typeAssignRule elementDetails* SEMICOLUMN;
elementDetails: defaultValue | elementConstraints;
elementConstraints: constraints;
associationConstraints: constraints;
constraints: 'null' | 'not null' | 'NULL' | 'NOT NULL';

association: (key=identifier)? ascId=identifier ':' ASSOCIATION cardinality? TO associationTarget (managedForeignKeys | unmanagedForeignKey)* associationConstraints? SEMICOLUMN;
calculatedAssociation: ascId=identifier ':' typeAssignRule elementDetails* '=' statement ';';
statement: .*?;
associationTarget: pathSubMembers+=identifier ('.' pathSubMembers+=identifier)*;
unmanagedForeignKey: ON pathSubMembers+=identifier ('.' pathSubMembers+=identifier)* '=' source=identifier;
managedForeignKeys: '{' foreignKey (',' foreignKey)* '}';
foreignKey: pathSubMembers+=identifier ('.' pathSubMembers+=identifier)* (AS alias=identifier)?;
cardinality:  '[' ASSOCIATION_MIN (max=INTEGER | many='*') ']'   # MinMaxCardinality
              | '[' (max=INTEGER | many='*') ']'                 # MaxCardinality
              |  '[' ']'                                         # NoCardinality
              ;

defaultValue: DEFAULT value=(STRING | INTEGER | DECIMAL | BOOLEAN | LOCAL_TIME | LOCAL_DATE | UTC_DATE_TIME | UTC_TIMESTAMP | VARBINARY | DATETIME_VALUE_FUNCTION | NULL);

annotationRule: '@' identifier ':' annValue                               #AnnObjectRule
              | '@' annId=identifier ('.' prop=identifier)* ':' annValue  #AnnPropertyRule
              | '@' identifier                                            #AnnMarkerRule
              ;

annValue:  arrRule | enumRule | obj | literal=(STRING | BOOLEAN);
enumRule: '#' identifier;
arrRule: '[' annValue (',' annValue)* ']';
obj: '{' keyValue (',' keyValue)* '}';
keyValue: identifier ':' annValue;

selectRule: isUnion=UNION? SELECT FROM dependsOnTable+=identifier ('.' dependsOnTable+=identifier)* ((AS dependingTableAlias=identifier) | dependingTableAlias=identifier)? joinRule* isDistinct=DISTINCT? '{' selectedColumnsRule '}' SEMICOLUMN? (WHERE whereRule SEMICOLUMN?)?;
joinRule: joinType=JOIN_TYPES joinArtifactName=identifier ((AS joinTableAlias=identifier) | joinTableAlias=identifier)? joinFields;
joinFields: .*?;
selectedColumnsRule: .*?;
whereRule: .*?;

identifier: ID
| STRING
| NAMESPACE
| HANA
| AS
| ON
| SELECT
| FROM
| WHERE
| DEFINE
| UNION
| DISTINCT
| CONTEXT
| ENTITY
| TYPE
| VIEW
| ASSOCIATION
| TO
| JOIN_TYPES
| USING
| DEFAULT;
