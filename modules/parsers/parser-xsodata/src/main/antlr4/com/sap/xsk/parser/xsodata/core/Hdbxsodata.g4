grammar Hdbxsodata;

xsodataDefinition: service annotations? settings?;

service            : 'service' namespace? body;
namespace           : 'namespace' QUATED_STRING;
body                : '{' content? '}';
content             : entry content?;
entry               : ( entity | association) ;

entity              : object entityset? with? keys? concurrencytoken? navigates? aggregates? parameters? modificationBody? SEMICOLON;//the order is fixed
object              : 'entity'?  (catalogobjectschema '.')? catalogobjectname; //can't deferenciate repoobject here
catalogobjectschema : QUATED_STRING;
catalogobjectname   : QUATED_STRING;
entityset           : 'as' entitysetname;
entitysetname       : QUATED_STRING;
with                : ( withProp | withoutProp ) propertylist;
withProp            : 'with';
withoutProp         : 'without';
propertylist        : '(' (columnname (',' columnname)*) ')';
columnname          : QUATED_STRING;
keys                : ('keys'| 'key') ( keylist | keygenerated );
keylist             : propertylist;
keygenerated        : 'generate' 'local' columnname;
concurrencytoken    : 'concurrencytoken' keylist?;

navigates           : 'navigates' '(' navlist ')';
navlist             : (naventry (',' navlist)*);
naventry            : assocname 'as' navpropname fromend?;
assocname           : QUATED_STRING;
navpropname         : QUATED_STRING;
fromend             : 'from'  (principal | dependent);
principal           : 'principal';
dependent           : 'dependent';
aggregates          : 'aggregates' 'always' aggregatestuple?;
aggregatestuple     : '(' (aggregate (',' aggregate)*) ')';
aggregate           : aggregatefunction 'of' columnname;
aggregatefunction   : ( 'SUM' | 'AVG' | 'MIN' | 'MAX' );

parameters          :'parameters' 'via' parameterskeyand? 'entity' parameterentitysetname? parametersresultsprop?;
parameterskeyand    :'key' 'and';
parameterentitysetname :QUATED_STRING;
parametersresultsprop  :'results' 'property' QUATED_STRING;

modificationBody     : modification modification* ;
modification         : create | update | delete;//the order is random
create               : 'create' modificationspec;
update               : 'update' modificationspec;
delete               : 'delete' modificationspec;
modificationspec	 : ( (modificationaction events?) | events | forbidden );
modificationaction	 : 'using' action;
forbidden            : 'forbidden';
action               : QUATED_STRING;
events               : 'events' '(' eventlist ')';
eventlist            : (eventlistElement (',' eventlistElement)*);
eventlistElement     :  eventtype action;
eventtype            : ( 'before' | 'after' | 'precommit' | 'postcommit' );

association          : associationdef assocrefconstraint? principalend dependentend ( assoctable | storage | modificationBody )? SEMICOLON;
associationdef       : 'association' assocname;
assocrefconstraint   : 'with' 'referential' 'constraint';
principalend         : principal end;
dependentend         : dependent end;
end                  : endref multiplicity;
endref               : endtype joinpropertieslist?;
endtype              : entitysetname;
joinpropertieslist	 : propertylist;
multiplicity         : 'multiplicity'  multiplicityvalue ;
multiplicityvalue    : ( '"1"' | '"0..1"' | '"1..*"' | '"*"' );
assoctable           : 'over' repoobject overprincipalend overdependentend modificationBody?;
repoobject           : QUATED_STRING;
overprincipalend     : principal overend;
overdependentend     : dependent overend;
overend              : propertylist;
storage              : ( nostorage | storageend modificationBody? );
nostorage            : 'no' 'storage';
storageend           : 'storage' 'on' ( principal | dependent );

annotations          : 'annotations' annotationsbody;
annotationsbody      : '{' annotationconfig annotationconfig? '}';
annotationconfig     : 'enable' 'OData4SAP' SEMICOLON;

settings             : 'settings' settingsbody;
settingsbody         :'{' settingselement* '}';
settingselement      : supportnull | contentcashecontrol | metadatacashecontrol | hints | limits;//order is random
supportnull          : 'support' 'null' SEMICOLON;
contentcashecontrol  : 'content' 'cache-control' QUATED_STRING SEMICOLON;
metadatacashecontrol : 'metadata' 'cache-control' QUATED_STRING SEMICOLON;
hints                : 'hints' hintlist? nullvalue? SEMICOLON;
hintlist             : ( hintvalue (',' hintvalue)* );
hintvalue            : QUATED_STRING;
nullvalue            : 'null';
limits               : 'limits' limitvalue (',' limitvalue)* SEMICOLON;
limitvalue           : (maxrecords | maxexpandedrecords) EQ INT;
maxrecords           :'max_records';
maxexpandedrecords   :'max_expanded_records';

SEMICOLON           : ';' ;
EQ                  : '=' ;
QUATED_STRING       : '"' (ESC | ~[{}])*? '"' ;
COMMA               : ',' ;
COLON               : ':';
ESC                 : '\\"' | '\\\\';   // 2-char sequences \" and \\
WS                  : [ \t\r\n\u000C]+ -> skip;   // toss out whitespace
LINE_COMMENT        : '//' .*? '\r'? '\n' -> skip ; // Match "//" stuff '\n'
COMMENT             : '/*' .*? '*/' -> skip ; // Match "/*" stuff "*/"
INT                 : [0-9]+;

//MULTIPLISITY_STRING  : '"' (~[\r\n"] | '""')* '"';