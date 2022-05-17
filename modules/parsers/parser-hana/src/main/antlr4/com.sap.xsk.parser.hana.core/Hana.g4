grammar Hana;

swallow_to_semi
    : ~( ';' )+
    ;

compilation_unit
    : unit_statement* EOF
    ;

sql_script
    : (unit_statement /*| sql_plus_command*/)* EOF
    ;

unit_statement
    : set_schema
    | create_func_body
    | create_procedure_body
    | create_sequence
    | drop_procedure
    | data_manipulation_language_statements
    ;

set_schema : SET SCHEMA schema_name ';' ;

drop_procedure : DROP PROCEDURE proc_name ';' ;

/*
CREATE PROCEDURE <proc_name> [(<parameter_clause>)] [LANGUAGE <lang>] [SQL SECURITY <mode>] [DEFAULT SCHEMA <default_schema_name>] [READS SQL DATA [WITH RESULT VIEW <view_name>]] AS
  BEGIN [SEQUENTIAL EXECUTION]
    <procedure_body>
  END
*/
create_procedure_body
    : (CREATE)? PROCEDURE proc_name
	  (parameter_clause)?
	  (LANGUAGE lang)?
	  (SQL SECURITY security_mode)?
	  (DEFAULT SCHEMA default_schema_name)?
	  (READS SQL DATA (WITH RESULT VIEW view_name)?)?
	  AS
	  BEGIN (SEQUENTIAL EXECUTION)?
		  procedure_body
	  END (';')?
//	  (body | call_spec | EXTERNAL) ';'
	;

create_func_body
    : FUNCTION proc_name
	  (parameter_clause)?
      RETURNS return_type
	  (LANGUAGE lang)?
	  (SQL SECURITY security_mode)?
	  (DEFAULT SCHEMA default_schema_name)?
	  (READS SQL DATA (WITH RESULT VIEW view_name)?)?
	  AS
	  BEGIN (SEQUENTIAL EXECUTION)?
		  procedure_body
	  END ';'
//	  (body | call_spec | EXTERNAL) ';'
	;

param_name : id_expression ;

//<return_type> ::= <return_parameter_list> | <return_table_type>

return_type
    : param_list
    | return_table_type
    ;

return_table_type
    : TABLE column_list_definition
    ;

param_type
	: sql_type
	| table_type
	| table_type_definition
	;

sql_type
	: DATE
	| TIME
	| TIMESTAMP
	| SECONDDATE
	| TINYINT
	| SMALLINT
	| INTEGER
	| INT
	| BIGINT
	| DECIMAL ('(' numeric (',' numeric)? ')')?
	| SMALLDECIMAL
	| REAL
	| DOUBLE
	| VARCHAR ('(' numeric ')')?
	| NVARCHAR ('(' numeric ')')?
	| ALPHANUM ('(' numeric ')')?
	| VARBINARY ('(' numeric ')')?
	| BLOB
	| CLOB
	| NCLOB
	;

table_type : id_expression ;

table_type_definition : TABLE column_list_definition ;

column_list_definition : '(' column_elem (',' column_elem)* ')' ;

column_elem : column_name data_type ;

column_name : expression ; // id_expression ;

data_type
	: sql_type
	| SHORTTEXT ('(' numeric ')')?
	| TEXT
	;

parameter
	: ( IN | OUT | INOUT)? param_name param_type default_value_part?
	;

parameter_clause : '(' parameter? (',' parameter)* ')' ;

lang : SQLSCRIPT | 'R' ;

security_mode : DEFINER | INVOKER ;

default_schema_name : id_expression ;

view_name : id_expression ;




proc_decl_list : proc_decl (proc_decl)* ;

proc_decl : DECLARE proc_decl_op ';' ;

proc_decl_op
	: proc_variable
	| proc_table_variable
	| proc_cursor
	| proc_condition
//	| proc_handler
	;

proc_variable : variable_name_list (CONSTANT)? (sql_type | array_datatype) (NOT NULL)? proc_default? ;

proc_table_variable : variable_name_list ( table_type_definition | table_type ) ;

variable_name_list : variable_name (',' variable_name)? ;

//<column_list_elements> ::= (<column_definition>[{,<column_definition>}...])

array_datatype : sql_type ARRAY ( '=' array_constructor ) ;

array_constructor : ARRAY '(' expression (',' expression)* ')' ;

proc_default : (DEFAULT | ':=' ) expression ;

proc_cursor : CURSOR cursor_name ( proc_cursor_param_list )? FOR /*<subquery>*/ ; //TODO subquery

proc_cursor_param_list : proc_cursor_param (',' proc_cursor_param)* ;

variable_name : id_expression ;

cursor_name : id_expression ;

proc_cursor_param :  param_name data_type ;

proc_condition
	: variable_name CONDITION
	| variable_name CONDITION FOR sql_error_code
	;

sql_error_code
	: numeric
	| SQL_ERROR_CODE numeric
	;

/* ------------------------------------------------------------------------ */
proc_handler_list : proc_handler+ ;

proc_handler : DECLARE EXIT HANDLER FOR proc_condition_value_list proc_stmt ;

proc_condition_value_list : proc_condition_value (',' proc_condition_value)* ;

proc_condition_value
	: SQLEXCEPTION
	| SQLWARNING
    | sql_error_code
    | variable_name
    ;

/* ------------------------------------------------------------------------ */
proc_stmt_list : proc_stmt+ ;

func_stmt_list : proc_stmt+ ;

proc_stmt
	: proc_block
	| proc_assign
	| proc_single_assign
	| proc_sql
	| proc_call
	| proc_if
	| proc_signal
	| proc_resignal
/*	| <proc_multi_assign>
	| <proc_loop>
	| <proc_while>
	| <proc_for>
	| <proc_foreach>
	| <proc_exit>
	| <proc_continue>
	| <proc_open>
	| <proc_fetch>
	| <proc_close>
	| <proc_exec>
	| <proc_return>
*/	;

proc_sql
	: subquery ';'
	| insert_stmt ';'
	| update_stmt ';'
/*	| <select_into_stmt>
	| <insert_stmt>
	| <delete_stmt>
	| <update_stmt>
	| <replace_stmt>
	| <call_stmt>
	| <create_table>
	| <drop_table>*/
	;

update_stmt
    : UPDATE general_table_ref update_set_clause from_clause? where_clause? static_returning_clause? error_logging_clause?
    ;

insert_stmt
    : INSERT (single_table_insert | multi_table_insert)
    ;

proc_signal : SIGNAL signal_value set_signal_info? ';' ;

proc_resignal : RESIGNAL signal_value? set_signal_info? ';' ;

signal_value
	: signal_name
	| sql_error_code
	;

signal_name : id_expression ;

set_signal_info : SET MESSAGE_TEXT '=' '\'' message_string '\'' ;

message_string : expression;

proc_if
	: IF condition THEN (SEQUENTIAL EXECUTION)*
	  proc_decl_list?
	  proc_handler_list?
	  proc_stmt_list?
	  proc_elsif_list?
	  proc_else?
	  END IF ';'
	;

proc_elsif_list
	: ELSEIF condition THEN (SEQUENTIAL EXECUTION)*
	  proc_decl_list?
	  proc_handler_list?
	  proc_stmt_list?
	;

proc_else
	: ELSE (SEQUENTIAL EXECUTION)*
	  proc_decl_list?
	  proc_handler_list?
	  proc_stmt_list?
	;

proc_block
	: BEGIN proc_block_option?
	  proc_decl_list?
	  proc_handler_list?
	  proc_stmt_list?
	  END ';'
	;

proc_block_option
	: (SEQUENTIAL EXECUTION) (AUTONOMOUS TRANSACTION)?
	| (AUTONOMOUS TRANSACTION) (SEQUENTIAL EXECUTION)?
	;

proc_assign
	: variable_name ':=' ( expression /*| <array_function>*/ ) ';'
	| variable_name  '[' expression ']' '=' expression  ';'
	;


//select_statement
//    : subquery_factoring_clause? subquery (for_update_clause | order_by_clause)*
//    ;

proc_single_assign
	: variable_name '=' select_statement ';' //subquery ';'
//	|  <variable_name> = <proc_ce_call> ';'
//	|  <variable_name> = <proc_apply_filter> ';'
	|  variable_name '=' unnest_function ';'
	;

//proc_multi_assign : (<var_name_list>) = <function_expression>

unnest_function : UNNEST '(' variable_name_list ')' (WITH ORDINALITY)? as_col_names? ';' ;

table_name : (schema_name '.')? id_expression ;

as_col_names : AS table_name '(' column_name_list ')' ;

column_name_list : column_name (',' column_name)* ;

proc_call : CALL proc_name '(' param_list ')' (WITH OVERVIEW)* ';' ;

param_list : proc_param (',' proc_param)*;

proc_param
	: id_expression
	| bind_variable
	| named_param
//	| BINDVAR
	;

named_param
	: id_expression '=>' id_expression
	| id_expression '=>' bind_variable
	;
/* ------------------------------------------------------------------------ */

procedure_body
	: proc_decl_list?
	  proc_handler_list?
	  proc_stmt_list
	;

procedure_body_
	: seq_of_statements //exception_clause?
	;

func_body
	: func_stmt_list
	;

/* ------------------------------------------------------------------------ */


parameter_name : id_expression ;

for_each_row
    : FOR EACH ROW
    ;

alter_attribute_definition
    : (ADD | MODIFY | DROP) ATTRIBUTE (attribute_definition | '(' attribute_definition (',' attribute_definition)* ')')
    ;

attribute_definition
    : attribute_name type_spec?
    ;

alter_collection_clauses
    : MODIFY (LIMIT expression | ELEMENT TYPE type_spec)
    ;

dependent_handling_clause
    : INVALIDATE
    | CASCADE (CONVERT TO SUBSTITUTABLE | NOT? INCLUDING TABLE DATA)? dependent_exceptions_part?
    ;

dependent_exceptions_part
    : FORCE? EXCEPTIONS INTO tableview_name
    ;

type_definition
    : type_name (OID CHAR_STRING)? object_type_def?
    ;

object_type_def
    : invoker_rights_clause? (object_as_part | object_under_part) sqlj_object_type?
      ('(' object_member_spec (',' object_member_spec)* ')')? modifier_clause*
    ;

object_as_part
    : (IS | AS) (OBJECT | varray_type_def | nested_table_type_def)
    ;

object_under_part
    : UNDER type_spec
    ;

nested_table_type_def
    : TABLE OF type_spec (NOT NULL)?
    ;

sqlj_object_type
    : EXTERNAL NAME expression LANGUAGE JAVA USING (SQLDATA|CUSTOMDATUM|ORADATA)
    ;

type_body
    : BODY type_name (IS | AS) type_body_elements (',' type_body_elements)* END
    ;

type_body_elements
    : map_order_func_declaration
    | subprog_decl_in_type
    ;

map_order_func_declaration
    : (MAP | ORDER) MEMBER func_decl_in_type
    ;

subprog_decl_in_type
    : (MEMBER | STATIC) (proc_decl_in_type | func_decl_in_type | constructor_declaration)
    ;

proc_decl_in_type
    : PROCEDURE proc_name '(' type_elements_parameter (',' type_elements_parameter)* ')'
      (IS | AS) (call_spec | DECLARE? declare_spec* body ';')
    ;

func_decl_in_type
    : FUNCTION function_name ('(' type_elements_parameter (',' type_elements_parameter)* ')')?
      RETURN type_spec (IS | AS) (call_spec | DECLARE? declare_spec* body ';')
    ;

constructor_declaration
    : FINAL? INSTANTIABLE? CONSTRUCTOR FUNCTION type_spec
      ('(' (SELF IN OUT type_spec ',') type_elements_parameter (',' type_elements_parameter)*  ')')?
      RETURN SELF AS RESULT (IS | AS) (call_spec | DECLARE? declare_spec* body ';')
    ;

// $>

// $<Common Type Clauses

modifier_clause
    : NOT? (INSTANTIABLE | FINAL | OVERRIDING)
    ;

object_member_spec
    : id type_spec sqlj_object_type_attr?
    | element_spec
    ;

sqlj_object_type_attr
    : EXTERNAL NAME expression
    ;

element_spec
    : modifier_clause? element_spec_options+ (',' pragma_clause)?
    ;

element_spec_options
    : subprogram_spec
    | constructor_spec
    | map_order_function_spec
    ;

subprogram_spec
    : (MEMBER|STATIC) (type_procedure_spec|type_function_spec)
    ;

type_procedure_spec
    : PROCEDURE proc_name '(' type_elements_parameter (',' type_elements_parameter)* ')' ((IS | AS) call_spec)?
    ;

type_function_spec
    : FUNCTION function_name ('(' type_elements_parameter (',' type_elements_parameter)* ')')?
      RETURN (type_spec | SELF AS RESULT) ((IS | AS) call_spec | EXTERNAL VARIABLE? NAME expression)?
    ;

constructor_spec
    : FINAL? INSTANTIABLE? CONSTRUCTOR FUNCTION type_spec ('(' (SELF IN OUT type_spec ',') type_elements_parameter (',' type_elements_parameter)*  ')')?  RETURN SELF AS RESULT ((IS | AS) call_spec)?
    ;

map_order_function_spec
    : (MAP | ORDER) MEMBER type_function_spec
    ;

pragma_clause
    : PRAGMA RESTRICT_REFERENCES '(' pragma_elements (',' pragma_elements)* ')'
    ;

pragma_elements
    : id
    | DEFAULT
    ;

type_elements_parameter
    : parameter_name type_spec
    ;

create_sequence
    : CREATE SEQUENCE sequence_name (sequence_start_clause | sequence_spec)* ';'
    ;

// $<Common Sequence

sequence_spec
    : INCREMENT BY UNSIGNED_INTEGER
    | MAXVALUE UNSIGNED_INTEGER
    | NOMAXVALUE
    | MINVALUE UNSIGNED_INTEGER
    | NOMINVALUE
    | CYCLE
    | NOCYCLE
    | CACHE UNSIGNED_INTEGER
    | NOCACHE
    | ORDER
    | NOORDER
    ;

sequence_start_clause
    : START WITH UNSIGNED_INTEGER
    ;

// $<Common DDL Clauses

invoker_rights_clause
    : AUTHID (CURRENT_USER|DEFINER)
    ;

compiler_parameters_clause
    : id '=' expression
    ;

call_spec
    : LANGUAGE (java_spec | c_spec)
    ;

// $<Call Spec - Specific Clauses

java_spec
    : JAVA NAME CHAR_STRING
    ;

c_spec
    : C_LETTER (NAME CHAR_STRING)? LIBRARY id c_agent_in_clause? (WITH CONTEXT)? c_parameters_clause?
    ;

c_agent_in_clause
    : AGENT IN '(' expression (',' expression)* ')'
    ;

c_parameters_clause
    : PARAMETERS '(' (expression (',' expression)* | '.' '.' '.') ')'
    ;

// $>


default_value_part
    : (ASSIGN_OP | DEFAULT) expression
    ;

// $<PL/SQL Elements Declarations

declare_spec
    : variable_declaration
    | subtype_declaration
    | cursor_declaration
    | exception_declaration
    | pragma_declaration
    | record_declaration
    | table_declaration
    | create_procedure_body
    ;

//incorporates constant_declaration
variable_declaration
    : variable_name CONSTANT? type_spec (NOT NULL)? default_value_part? ';'
    ;

subtype_declaration
    : SUBTYPE type_name IS type_spec (RANGE expression '..' expression)? (NOT NULL)? ';'
    ;

//cursor_declaration incorportates curscursor_body and cursor_spec
cursor_declaration
    : CURSOR cursor_name ('(' parameter_spec (',' parameter_spec)* ')' )? (RETURN type_spec)? (IS select_statement)? ';'
    ;

parameter_spec
    : parameter_name (IN? type_spec)? default_value_part?
    ;

exception_declaration
    : exception_name EXCEPTION ';'
    ;

pragma_declaration
    : PRAGMA (SERIALLY_REUSABLE
    | AUTONOMOUS_TRANSACTION
    | EXCEPTION_INIT '(' exception_name ',' numeric_negative ')'
    | INLINE '(' id1=id ',' expression ')'
    | RESTRICT_REFERENCES '(' (id | DEFAULT) (',' id)+ ')') ';'
    ;

record_declaration
    : record_type_dec
    | record_var_dec
    ;

// $<Record Declaration - Specific Clauses

//incorporates ref_cursor_type_definition
record_type_dec
    : TYPE type_name IS (RECORD '(' field_spec (',' field_spec)* ')' | REF CURSOR (RETURN type_spec)?) ';'
    ;

field_spec
    : column_name type_spec? (NOT NULL)? default_value_part?
    ;

record_var_dec
    : record_name type_name (PERCENT_ROWTYPE | PERCENT_TYPE) ';'
    ;

// $>

table_declaration
    : (table_type_dec | table_var_dec) ';'
    ;

table_type_dec
    : TYPE type_name IS (TABLE OF type_spec table_indexed_by_part? (NOT NULL)? | varray_type_def)
    ;

table_indexed_by_part
    : (idx1=INDEXED | idx2=INDEX) BY type_spec
    ;

varray_type_def
    : (VARRAY | VARYING ARRAY) '(' expression ')' OF type_spec (NOT NULL)?
    ;

table_var_dec
    : table_var_name type_spec
    ;

// $>

// $<PL/SQL Statements

seq_of_statements
    : (statement (';' | EOF) | label_declaration)+
    ;

label_declaration
    : ltp1= '<' '<' label_name '>' '>'
    ;

statement
    : CREATE swallow_to_semi
    | ALTER swallow_to_semi
    | GRANT swallow_to_semi
    | TRUNCATE swallow_to_semi
    | body
    | block
    | assignment_statement
    | continue_statement
    | exit_statement
    | goto_statement
    | if_statement
    | loop_statement
    | forall_statement
    | null_statement
    | raise_statement
    | return_statement
    | case_statement/*[true]*/
    | sql_statement
    | function_call
    ;

assignment_statement
    : (general_element | bind_variable) ASSIGN_OP expression
    ;

continue_statement
    : CONTINUE label_name? (WHEN condition)?
    ;

exit_statement
    : EXIT label_name? (WHEN condition)?
    ;

goto_statement
    : GOTO label_name
    ;

if_statement
    : IF condition THEN seq_of_statements elsif_part* else_part? END IF
    ;

elsif_part
    : ELSIF condition THEN seq_of_statements
    ;

else_part
    : ELSE seq_of_statements
    ;

loop_statement
    : label_name? (WHILE condition | FOR cursor_loop_param)? LOOP seq_of_statements END LOOP label_name?
    ;

// $<Loop - Specific Clause

cursor_loop_param
    : index_name IN REVERSE? lower_bound '..' upper_bound
    | record_name IN ( cursor_name expression_list? | '(' select_statement ')')
    ;
// $>

forall_statement
    : FORALL index_name IN bounds_clause sql_statement (SAVE EXCEPTIONS)?
    ;

bounds_clause
    : lower_bound '..' upper_bound
    | INDICES OF collection_name between_bound?
    | VALUES OF index_name
    ;

between_bound
    : BETWEEN lower_bound AND upper_bound
    ;

lower_bound
    : concatenation
    ;

upper_bound
    : concatenation
    ;

null_statement
    : NULL
    ;

raise_statement
    : RAISE exception_name?
    ;

return_statement
    : RETURN cn1=condition?
    | RETURN select_statement
    ;

function_call
    : CALL? routine_name function_argument?
    ;

body
    : BEGIN seq_of_statements exception_clause? END label_name?
    ;

// $<Body - Specific Clause

exception_clause
    : EXCEPTION exception_handler+
    ;

exception_handler
    : WHEN exception_name (OR exception_name)* THEN seq_of_statements
    ;

// $>

trigger_block
    : (DECLARE? declare_spec+)? body
    ;

block
    : DECLARE? declare_spec+ body
    ;

// $>

// $<SQL PL/SQL Statements

sql_statement
    : execute_immediate
    | data_manipulation_language_statements
    | cursor_manipulation_statements
    | transaction_control_statements
    ;

execute_immediate
    : EXECUTE IMMEDIATE expression (into_clause using_clause? | using_clause dynamic_returning_clause? | dynamic_returning_clause)?
    ;

// $<Execute Immediate - Specific Clause
dynamic_returning_clause
    : (RETURNING | RETURN) into_clause
    ;
// $>


// $<DML SQL PL/SQL Statements

data_manipulation_language_statements
    : merge_statement
    | lock_table_statement
    | declare_statement
    | exception_statement
    | select_statement
    | update_statement
    | delete_statement
    | insert_statement
    | explain_statement
    ;

// $>

// $<Cursor Manipulation SQL PL/SQL Statements

cursor_manipulation_statements
    : close_statement
    | open_statement
    | fetch_statement
    | open_for_statement
    ;

close_statement
    : CLOSE cursor_name
    ;

open_statement
    : OPEN cursor_name expression_list?
    ;

fetch_statement
    : FETCH cursor_name (it1=INTO variable_name (',' variable_name )* | BULK COLLECT INTO variable_name (',' variable_name )*)
    ;

open_for_statement
    : OPEN variable_name FOR (select_statement | expression) using_clause?
    ;

// $>

// $<Transaction Control SQL PL/SQL Statements

transaction_control_statements
    : set_transaction_command
    | set_constraint_command
    | commit_statement
    | rollback_statement
    | savepoint_statement
    ;

set_transaction_command
    : SET TRANSACTION
      (READ (ONLY | WRITE) | ISOLATION LEVEL (SERIALIZABLE | READ COMMITTED) | USE ROLLBACK SEGMENT rollback_segment_name)?
      (NAME quoted_string)?
    ;

set_constraint_command
    : SET (CONSTRAINT | CONSTRAINTS) (ALL | constraint_name (',' constraint_name)*) (IMMEDIATE | DEFERRED)
    ;

commit_statement
    : COMMIT WORK?
      (COMMENT expression | FORCE (CORRUPT_XID expression| CORRUPT_XID_ALL | expression (',' expression)?))?
      write_clause?
    ;

write_clause
    : WRITE (WAIT|NOWAIT)? (IMMEDIATE|BATCH)?
    ;

rollback_statement
    : ROLLBACK WORK? (TO SAVEPOINT? savepoint_name | FORCE quoted_string)?
    ;

savepoint_statement
    : SAVEPOINT savepoint_name
    ;

// Dml

/* TODO
//SHOULD BE OVERRIDEN!
compilation_unit
    :  seq_of_statements* EOF
    ;

//SHOULD BE OVERRIDEN!
seq_of_statements
    : select_statement
    | update_statement
    | delete_statement
    | insert_statement
    | lock_table_statement
    | merge_statement
    | explain_statement
//    | case_statement[true]
    ;
*/

explain_statement
    : EXPLAIN PLAN (SET STATEMENT_ID '=' quoted_string)? (INTO tableview_name)?
      FOR (select_statement | update_statement | delete_statement | insert_statement | merge_statement)
    ;

select_statement
    : subquery_factoring_clause? subquery (for_update_clause | order_by_clause)*
    ;

// $<Select - Specific Clauses
subquery_factoring_clause
    : WITH factoring_element (',' factoring_element)*
    ;

factoring_element
    : query_name ('(' column_name (',' column_name)* ')')? AS '(' subquery order_by_clause? ')'
      search_clause? cycle_clause?
    ;

search_clause
    : SEARCH (DEPTH | BREADTH) FIRST BY column_name ASC? DESC? (NULLS FIRST)? (NULLS LAST)?
      (',' column_name ASC? DESC? (NULLS FIRST)? (NULLS LAST)?)* SET column_name
    ;

cycle_clause
    : CYCLE column_name (',' column_name)* SET column_name TO expression DEFAULT expression
    ;

subquery
    : subquery_basic_elements subquery_operation_part*
    ;

subquery_operation_part
    : (UNION ALL? | INTERSECT | MINUS) subquery_basic_elements
    ;

subquery_basic_elements
    : query_block
    | '(' subquery ')'
    ;

query_block
    : SELECT (DISTINCT | UNIQUE | ALL)? ('*' | selected_element (',' selected_element)*)
      into_clause? from_clause where_clause? hierarchical_query_clause? group_by_clause? model_clause?
    ;

selected_element
    : select_list_elements column_alias?
    ;

from_clause
    : FROM table_ref_list
    ;

select_list_elements
    : tableview_name '.' '*'
    | expression
    | expression_
    ;

table_ref_list
    : table_ref (',' table_ref)*
    ;

// NOTE to PIVOT clause
// according the SQL reference this should not be possible
// according to he reality it is. Here we probably apply pivot/unpivot onto whole join clause
// eventhough it is not enclosed in parenthesis. See pivot examples 09,10,11
table_ref
	: table_ref_aux join_clause* (pivot_clause | unpivot_clause)?
//	| table_declare
    ;

//table_declare : BINDVAR ; //bind_variable ; //(':'id_expression) ;

table_ref_aux
    : (dml_table_expression_clause (pivot_clause | unpivot_clause)?
    | '(' table_ref subquery_operation_part* ')' (pivot_clause | unpivot_clause)?
    | ONLY '(' dml_table_expression_clause ')'
    | dml_table_expression_clause (pivot_clause | unpivot_clause)?)
    flashback_query_clause* (/*{isTableAlias()}?*/ AS? table_alias)?
    | BINDVAR (AS? table_alias)?
    ;

join_clause
    : query_partition_clause? (CROSS | NATURAL)? (INNER | outer_join_type)?
      JOIN table_ref_aux query_partition_clause? (join_on_part | join_using_part)*
    ;

join_on_part
    : ON condition
    ;

join_using_part
    : USING '(' column_name (',' column_name)* ')'
    ;

outer_join_type
    : (FULL | LEFT | RIGHT) OUTER?
    ;

query_partition_clause
    : PARTITION BY ('(' subquery ')' | expression_list | expression (',' expression)*)
    ;

flashback_query_clause
    : VERSIONS BETWEEN (SCN | TIMESTAMP) expression
    | AS OF (SCN | TIMESTAMP | SNAPSHOT) expression
    ;

pivot_clause
    : PIVOT XML? '(' pivot_element (',' pivot_element)* pivot_for_clause pivot_in_clause ')'
    ;

pivot_element
    : aggregate_function_name '(' expression ')' column_alias?
    ;

pivot_for_clause
    : FOR (column_name | '(' column_name (',' column_name)* ')')
    ;

pivot_in_clause
    : IN '(' (subquery | ANY (',' ANY)* | pivot_in_clause_element (',' pivot_in_clause_element)*) ')'
    ;

pivot_in_clause_element
    : pivot_in_clause_elements column_alias?
    ;

pivot_in_clause_elements
    : expression
    | expression_list
    ;

unpivot_clause
    : UNPIVOT ((INCLUDE | EXCLUDE) NULLS)?
    '(' (column_name | '(' column_name (',' column_name)* ')') pivot_for_clause unpivot_in_clause ')'
    ;

unpivot_in_clause
    : IN '(' unpivot_in_elements (',' unpivot_in_elements)* ')'
    ;

unpivot_in_elements
    : (column_name | '(' column_name (',' column_name)* ')')
      (AS (constant | '(' constant (',' constant)* ')'))?
    ;

hierarchical_query_clause
    : CONNECT BY NOCYCLE? condition start_part?
    | start_part CONNECT BY NOCYCLE? condition
    ;

start_part
    : START WITH condition
    ;

group_by_clause
    : GROUP BY group_by_elements (',' group_by_elements)* having_clause?
    | having_clause (GROUP BY group_by_elements (',' group_by_elements)*)?
    ;

group_by_elements
    : grouping_sets_clause
    | rollup_cube_clause
    | expression
    ;

rollup_cube_clause
    : (ROLLUP|CUBE) '(' grouping_sets_elements (',' grouping_sets_elements)* ')'
    ;

grouping_sets_clause
    : GROUPING SETS '(' grouping_sets_elements (',' grouping_sets_elements)* ')'
    ;

grouping_sets_elements
    : rollup_cube_clause
    | expression_list
    | expression
    ;

having_clause
    : HAVING condition
    ;

model_clause
    : MODEL cell_reference_options* return_rows_clause? reference_model* main_model
    ;

cell_reference_options
    : (IGNORE | KEEP) NAV
    | UNIQUE (DIMENSION | SINGLE REFERENCE)
    ;

return_rows_clause
    : RETURN (UPDATED | ALL) ROWS
    ;

reference_model
    : REFERENCE reference_model_name ON '(' subquery ')' model_column_clauses cell_reference_options*
    ;

main_model
    : (MAIN main_model_name)? model_column_clauses cell_reference_options* model_rules_clause
    ;

model_column_clauses
    : model_column_partition_part? DIMENSION BY model_column_list MEASURES model_column_list
    ;

model_column_partition_part
    : PARTITION BY model_column_list
    ;

model_column_list
    : '(' model_column (',' model_column)*  ')'
    ;

model_column
    : expression table_alias?
    ;

model_rules_clause
    : model_rules_part? '(' model_rules_element (',' model_rules_element)* ')'
    ;

model_rules_part
    : RULES (UPDATE | UPSERT ALL?)? ((AUTOMATIC | SEQUENTIAL) ORDER)? model_iterate_clause?
    ;

model_rules_element
    : (UPDATE | UPSERT ALL?)? cell_assignment order_by_clause? '=' expression
    ;

cell_assignment
    : model_expression
    ;

model_iterate_clause
    : ITERATE '(' expression ')' until_part?
    ;

until_part
    : UNTIL '(' condition ')'
    ;

order_by_clause
    : ORDER SIBLINGS? BY order_by_elements (',' order_by_elements)*
    ;

order_by_elements
    : expression (ASC | DESC)? (NULLS (FIRST | LAST))?
    ;

for_update_clause
    : FOR UPDATE for_update_of_part? for_update_options?
    ;

for_update_of_part
    : OF column_name (',' column_name)*
    ;

for_update_options
    : SKIP_ LOCKED
    | NOWAIT
    | WAIT expression
    ;

// $>

update_statement
    : UPDATE general_table_ref update_set_clause where_clause? static_returning_clause? error_logging_clause?
    ;

// $<Update - Specific Clauses
update_set_clause
    : SET
      (column_based_update_set_clause (',' column_based_update_set_clause)* | VALUE '(' id ')' '=' expression)
    ;

column_based_update_set_clause
    : column_name '=' expression
    | '(' column_name (',' column_name)* ')' '=' subquery
    ;

// $>

delete_statement
    : DELETE FROM? general_table_ref where_clause? static_returning_clause? error_logging_clause?
    ;

insert_statement
    : INSERT (single_table_insert | multi_table_insert)
    ;

declare_statement
	: DECLARE id type_spec? default_value_part?
	;

exception_statement
	: DECLARE EXIT HANDLER FOR proc_condition_value data_manipulation_language_statements
	;

proc_condition_value_
	: SQLEXCEPTION
//	| SQL_ERROR_CODE error_code
	| id
	;

/*
DECLARE EXIT HANDLER FOR <proc_condition_value> {,<proc_condition_value>}...]
<proc_stmt>
<proc_condition_value> ::= SQLEXCEPTION
   | SQL_ERROR_CODE <error_code>
   | <condition_name>
*/


// $<Insert - Specific Clauses

single_table_insert
    : insert_into_clause (values_clause static_returning_clause? | select_statement) error_logging_clause?
    ;

multi_table_insert
    : (ALL multi_table_element+ | conditional_insert_clause) select_statement
    ;

multi_table_element
    : insert_into_clause values_clause? error_logging_clause?
    ;

conditional_insert_clause
    : (ALL | FIRST)? conditional_insert_when_part+ conditional_insert_else_part?
    ;

conditional_insert_when_part
    : WHEN condition THEN multi_table_element+
    ;

conditional_insert_else_part
    : ELSE multi_table_element+
    ;

insert_into_clause
    : INTO general_table_ref ('(' column_name (',' column_name)* ')')?
    ;

values_clause
    : VALUES expression_list
    ;

// $>
merge_statement
    : MERGE INTO tableview_name table_alias? USING selected_tableview ON '(' condition ')'
      (merge_update_clause merge_insert_clause? | merge_insert_clause merge_update_clause?)?
      error_logging_clause?
    ;

// $<Merge - Specific Clauses

merge_update_clause
    : WHEN MATCHED THEN UPDATE SET merge_element (',' merge_element)* where_clause? merge_update_delete_part?
    ;

merge_element
    : column_name '=' expression
    ;

merge_update_delete_part
    : DELETE where_clause
    ;

merge_insert_clause
    : WHEN NOT MATCHED THEN INSERT ('(' column_name (',' column_name)* ')')? VALUES expression_list where_clause?
    ;

selected_tableview
    : (tableview_name | '(' select_statement ')') table_alias?
    ;

// $>

lock_table_statement
    : LOCK TABLE lock_table_element (',' lock_table_element)* IN lock_mode MODE wait_nowait_part?
    ;

wait_nowait_part
    : WAIT expression
    | NOWAIT
    ;

// $<Lock - Specific Clauses

lock_table_element
    : tableview_name partition_extension_clause?
    ;

lock_mode
    : ROW SHARE
    | ROW EXCLUSIVE
    | SHARE UPDATE?
    | SHARE ROW EXCLUSIVE
    | EXCLUSIVE
    ;

// $<Common DDL Clauses

general_table_ref
    : (dml_table_expression_clause | ONLY '(' dml_table_expression_clause ')') table_alias?
    ;

static_returning_clause
    : (RETURNING | RETURN) expression (',' expression)* into_clause
    ;

error_logging_clause
    : LOG ERRORS error_logging_into_part? expression_wrapper? error_logging_reject_part?
    ;

error_logging_into_part
    : INTO tableview_name
    ;

error_logging_reject_part
    : REJECT LIMIT (UNLIMITED | expression_wrapper)
    ;

dml_table_expression_clause
    : table_collection_expression
    | '(' select_statement subquery_restriction_clause? ')'
    | tableview_name sample_clause?
    ;

table_collection_expression
    : (TABLE | THE) ('(' subquery ')' | '(' expression ')' ('(' '+' ')')?)
    ;

subquery_restriction_clause
    : WITH (READ ONLY | CHECK OPTION (CONSTRAINT constraint_name)?)
    ;

sample_clause
    : SAMPLE BLOCK? '(' expression (',' expression)? ')' seed_part?
    ;

seed_part
    : SEED '(' expression ')'
    ;

// $>

// $<Expression & Condition
cursor_expression
    : CURSOR '(' subquery ')'
    ;

expression_list
    : '(' expression? (',' expression)* ')'
    ;

condition
    : expression
    ;

condition_wrapper
//	: expression
	: condition_
    ;


condition_
	: condition_ OR condition_
	| condition_ AND condition_
	| NOT condition_
	| '(' condition_ ')'
	| predicate
	;

predicate
	: comparison_predicate
	| range_predicate
	| in_predicate
	| exist_predicate
	| like_predicate
	| null_predicate
	;

comparison_predicate
	: expression_ relational_operator ( ANY | SOME | ALL )? (expression__list | subquery)
	;

relational_operator
	: '='
	| '!='
	| '<>'
	| '>'
	| '<'
	| '>='
	| '<='
	;

range_predicate : expression_ NOT? BETWEEN expression_ AND expression_ ;

in_predicate : expression_ NOT? IN ( expression__list | subquery ) ;

exist_predicate : NOT? EXISTS '(' subquery ')' ;

like_predicate : expression_ NOT? LIKE expression_ (ESCAPE expression_)? ;

null_predicate : expression_ IS NOT? NULL ;

expression__list : expression_ (',' expression_)* ;

expression_
	: case_expression_
	| function_expression_
	| aggregate_expression_
	| '(' expression_ ')'
	| '(' subquery ')'
	| '-' expression_
	| expression_ operator expression_
	| variable_name
	| constant
	| (correlation_name'.')? column_name
	;

correlation_name : id_expression ;

operator
	: '+'
	| '-'
	| '*'
	| '/'
	| '||'
	| '='
	| '!='
	| '<'
	| '>'
	| '<='
	| '>='
	| '<>'
	| AND
	| OR
	;

case_expression_
	: simple_case_expression_
	| search_case_expression_
	;

simple_case_expression_
	: CASE expression_
	  WHEN expression_ THEN expression_
	 (WHEN expression_ THEN expression_)*
	 (ELSE expression_)?
	  END
	;

search_case_expression_
	: CASE
	  WHEN condition_ THEN expression_
	 (WHEN condition_ THEN expression_)*
	 (ELSE expression_)?
	  END
	;

function_expression_ : function_name '(' expression_  ( ',' expression_ )* ')' ;

aggregate_expression_
	: COUNT '(' '*' ')'
	| COUNT '(' DISTINCT expression__list ')'
	| agg_name '(' ( ALL | DISTINCT )? expression_ ')'
	| STRING_AGG '(' expression_ (',' delimiter)? aggregate_order_by_clause? ')'
	;

agg_name
	: CORR
	| CORR_SPEARMAN
	| COUNT
	| MIN
	| MEDIAN
	| MAX
	| SUM
	| AVG
	| STDDEV
	| VAR
	| STDDEV_POP
	| VAR_POP
	| STDDEV_SAMP
	| VAR_SAMP
	;

delimiter
    : id_expression
    | quoted_string ; //string_constant ;

aggregate_order_by_clause
	: ORDER BY expression_ ( ASC | DESC )? ( NULLS FIRST | NULLS LAST )? ;


///////////////////////////////////////////////////////////////////////////////////////////////////////

expression
    : cursor_expression
    | logical_and_expression ( OR logical_and_expression )*
    ;

expression_wrapper
    : expression
    ;

logical_and_expression
    : negated_expression ( AND negated_expression )*
    ;

negated_expression
    : NOT negated_expression
    | equality_expression
    ;

equality_expression
    : multiset_expression (IS NOT?
      (NULL | NAN | PRESENT | INFINITE | A_LETTER SET | EMPTY | OF TYPE? '(' ONLY? type_spec (',' type_spec)* ')'))*
    ;


multiset_expression
    : relational_expression (multiset_type OF? concatenation)?
    ;

multiset_type
    : MEMBER
    | SUBMULTISET
    ;

relational_expression : compound_expression (relational_expression_operator compound_expression)*
    ;

relational_expression_operator
	: '='
	| not_equal_op
	| '<'
	| '>'
	| less_than_or_equals_op
	| greater_than_or_equals_op
	;

compound_expression
    : concatenation
      (NOT? (IN in_elements | BETWEEN between_elements | like_type concatenation like_escape_part?))?
    ;

like_type
    : LIKE
    | LIKEC
    | LIKE2
    | LIKE4
    ;

like_escape_part
    : ESCAPE concatenation
    ;

in_elements
    : '(' subquery ')'
    | '(' concatenation_wrapper (',' concatenation_wrapper)* ')'
    | constant
    | bind_variable
    | general_element
    ;

between_elements
    : concatenation AND concatenation
    ;

concatenation
    : additive_expression (concatenation_op additive_expression)*
    ;

concatenation_wrapper
    : concatenation
    ;

additive_expression
    : multiply_expression (('+' | '-') multiply_expression)*
    ;

multiply_expression
    : datetime_expression (('*' | '/') datetime_expression)*
    ;

datetime_expression
    : model_expression
      (AT (LOCAL | TIME ZONE concatenation_wrapper) | interval_expression)?
    ;

interval_expression
    : DAY ('(' concatenation_wrapper ')')? TO SECOND ('(' concatenation_wrapper ')')?
    | YEAR ('(' concatenation_wrapper ')')? TO MONTH
    ;

model_expression
    : unary_expression ('[' model_expression_element ']')?
    ;

model_expression_element
    : (ANY | condition_wrapper) (',' (ANY | condition_wrapper))*
    | single_column_for_loop (',' single_column_for_loop)*
    | multi_column_for_loop
    ;

single_column_for_loop
    : FOR column_name
      (IN expression_list | for_like_part? FROM ex1=expression TO ex2=expression for_increment_decrement_type ex3=expression)
    ;

for_like_part
    : LIKE expression
    ;

for_increment_decrement_type
    : INCREMENT
    | DECREMENT
    ;

multi_column_for_loop
    : FOR
      '(' column_name (',' column_name)* ')' IN '(' (subquery | '(' expression_list (',' expression_list)* ')') ')'
    ;

unary_expression
    : '-' unary_expression
    | '+' unary_expression
    | PRIOR unary_expression
    | CONNECT_BY_ROOT unary_expression
    | /*TODO {input.LT(1).getText().equalsIgnoreCase("new") && !input.LT(2).getText().equals(".")}?*/ NEW unary_expression
    |  DISTINCT unary_expression
    |  ALL unary_expression
    |  /*TODO{(input.LA(1) == SQL92_RESERVED_CASE || input.LA(2) == SQL92_RESERVED_CASE)}?*/ case_statement/*[false]*/
    |  quantified_expression
    |  standard_function
    |  atom
    ;

case_statement /*TODO [boolean isStatementParameter]
TODO scope    {
    boolean isStatement;
}
@init    {$case_statement::isStatement = $isStatementParameter;}*/
    : searched_case_statement
    | simple_case_statement
    ;

// $<CASE - Specific Clauses

simple_case_statement
    : label_name? ck1=CASE atom simple_case_when_part+  case_else_part? END CASE? label_name?
    ;

simple_case_when_part
    : WHEN expression_wrapper THEN (/*TODO{$case_statement::isStatement}?*/ seq_of_statements | expression_wrapper)
    ;

searched_case_statement
    : label_name? ck1=CASE searched_case_when_part+ case_else_part? END CASE? label_name?
    ;

searched_case_when_part
    : WHEN condition_wrapper THEN (/*TODO{$case_statement::isStatement}?*/ seq_of_statements | expression_wrapper)
    ;

case_else_part
    : ELSE (/*{$case_statement::isStatement}?*/ seq_of_statements | expression_wrapper)
    ;
// $>

atom
    : table_element outer_join_sign
    | bind_variable
    | constant
    | general_element
    | '(' (subquery ')' subquery_operation_part* | expression_or_vector ')')
    ;

expression_or_vector
    : expression (vector_expr)?
    ;

vector_expr
    : ',' expression (',' expression)*
    ;

quantified_expression
    : (SOME | EXISTS | ALL | ANY) ('(' subquery ')' | '(' expression_wrapper ')')
    ;

standard_function
    : over_clause_keyword function_argument_analytic over_clause?
    | /*TODO stantard_function_enabling_using*/ regular_id function_argument_modeling using_clause?
    | COUNT '(' ( '*' | (DISTINCT | UNIQUE | ALL)? concatenation_wrapper) ')' over_clause?
    | (CAST | XMLCAST) '(' (MULTISET '(' subquery ')' | concatenation_wrapper) AS type_spec ')'
    | CHR '(' concatenation_wrapper USING NCHAR_CS ')'
    | COLLECT '(' (DISTINCT | UNIQUE)? concatenation_wrapper collect_order_by_part? ')'
    | within_or_over_clause_keyword function_argument within_or_over_part+
    | DECOMPOSE '(' concatenation_wrapper (CANONICAL | COMPATIBILITY)? ')'
    | EXTRACT '(' regular_id FROM concatenation_wrapper ')'
    | (FIRST_VALUE | LAST_VALUE) function_argument_analytic respect_or_ignore_nulls? over_clause
    | standard_prediction_function_keyword
      '(' expression_wrapper (',' expression_wrapper)* cost_matrix_clause? using_clause? ')'
    | TRANSLATE '(' expression_wrapper (USING (CHAR_CS | NCHAR_CS))? (',' expression_wrapper)* ')'
    | TREAT '(' expression_wrapper AS REF? type_spec ')'
    | TRIM '(' ((LEADING | TRAILING | BOTH)? quoted_string? FROM)? concatenation_wrapper ')'
    | XMLAGG '(' expression_wrapper order_by_clause? ')' ('.' general_element_part)?
    | (XMLCOLATTVAL|XMLFOREST)
      '(' xml_multiuse_expression_element (',' xml_multiuse_expression_element)* ')' ('.' general_element_part)?
    | XMLELEMENT
      '(' (ENTITYESCAPING | NOENTITYESCAPING)? (NAME | EVALNAME)? expression_wrapper
       (/*TODO{input.LT(2).getText().equalsIgnoreCase("xmlattributes")}?*/ ',' xml_attributes_clause)?
       (',' expression_wrapper column_alias?)* ')' ('.' general_element_part)?
    | XMLEXISTS '(' expression_wrapper xml_passing_clause? ')'
    | XMLPARSE '(' (DOCUMENT | CONTENT) concatenation_wrapper WELLFORMED? ')' ('.' general_element_part)?
    | XMLPI
      '(' (NAME id | EVALNAME concatenation_wrapper) (',' concatenation_wrapper)? ')' ('.' general_element_part)?
    | XMLQUERY
      '(' concatenation_wrapper xml_passing_clause? RETURNING CONTENT (NULL ON EMPTY)? ')' ('.' general_element_part)?
    | XMLROOT
      '(' concatenation_wrapper (',' xmlroot_param_version_part)? (',' xmlroot_param_standalone_part)? ')' ('.' general_element_part)?
    | XMLSERIALIZE
      '(' (DOCUMENT | CONTENT) concatenation_wrapper (AS type_spec)?
      xmlserialize_param_enconding_part? xmlserialize_param_version_part? xmlserialize_param_ident_part? ((HIDE | SHOW) DEFAULTS)? ')'
      ('.' general_element_part)?
    | XMLTABLE
      '(' xml_namespaces_clause? concatenation_wrapper xml_passing_clause? (COLUMNS xml_table_column (',' xml_table_column))? ')' ('.' general_element_part)?
    ;

over_clause_keyword
    : AVG
    | CORR
    | LAG
    | LEAD
    | MAX
    | MEDIAN
    | MIN
    | NTILE
    | RATIO_TO_REPORT
    | ROW_NUMBER
    | SUM
    | VARIANCE
    | REGR_
    | STDDEV
    | VAR_
    | COVAR_
    ;

/*TODO
stantard_function_enabling_using
    : {enablesUsingClause(input.LT(1).getText())}? REGULAR_ID
    ;
*/

within_or_over_clause_keyword
    : CUME_DIST
    | DENSE_RANK
    | LISTAGG
    | PERCENT_RANK
    | PERCENTILE_CONT
    | PERCENTILE_DISC
    | RANK
    ;

standard_prediction_function_keyword
    : PREDICTION
    | PREDICTION_BOUNDS
    | PREDICTION_COST
    | PREDICTION_DETAILS
    | PREDICTION_PROBABILITY
    | PREDICTION_SET
    ;

over_clause
    : OVER '(' query_partition_clause? (order_by_clause windowing_clause?)? ')'
    ;

windowing_clause
    : windowing_type
      (BETWEEN windowing_elements AND windowing_elements | windowing_elements)
    ;

windowing_type
    : ROWS
    | RANGE
    ;

windowing_elements
    : UNBOUNDED PRECEDING
    | CURRENT ROW
    | concatenation_wrapper (PRECEDING|FOLLOWING)
    ;

using_clause
    : USING ('*' | using_element (',' using_element)*)
    ;

using_element
    : (IN OUT? | OUT)? select_list_elements column_alias?
    ;

collect_order_by_part
    : ORDER BY concatenation_wrapper
    ;

within_or_over_part
    : WITHIN GROUP '(' order_by_clause ')'
    | over_clause
    ;

cost_matrix_clause
    : COST (MODEL AUTO? | '(' cost_class_name (',' cost_class_name)* ')' VALUES expression_list)
    ;

xml_passing_clause
    : PASSING (BY VALUE)? expression_wrapper column_alias? (',' expression_wrapper column_alias?)
    ;

xml_attributes_clause
    : XMLATTRIBUTES
     '(' (ENTITYESCAPING | NOENTITYESCAPING)? (SCHEMACHECK | NOSCHEMACHECK)?
     xml_multiuse_expression_element (',' xml_multiuse_expression_element)* ')'
    ;

xml_namespaces_clause
    : XMLNAMESPACES
      '(' (concatenation_wrapper column_alias)? (',' concatenation_wrapper column_alias)*
      xml_general_default_part? ')'
    ;

xml_table_column
    : xml_column_name
      (FOR ORDINALITY | type_spec (PATH concatenation_wrapper)? xml_general_default_part?)
    ;

xml_general_default_part
    : DEFAULT concatenation_wrapper
    ;

xml_multiuse_expression_element
    : expression (AS (id_expression | EVALNAME concatenation))?
    ;

xmlroot_param_version_part
    : VERSION (NO VALUE | expression_wrapper)
    ;

xmlroot_param_standalone_part
    : STANDALONE (YES | NO VALUE?)
    ;

xmlserialize_param_enconding_part
    : ENCODING concatenation_wrapper
    ;

xmlserialize_param_version_part
    : VERSION concatenation_wrapper
    ;

xmlserialize_param_ident_part
    : NO INDENT
    | INDENT (SIZE '=' concatenation_wrapper)?
    ;

// SqlPlus

sql_plus_command
    : ('/' | whenever_command | exit_command | prompt_command | set_command) ';'?
    ;

whenever_command
    : WHENEVER (SQLERROR | OSERROR)
      (EXIT (SUCCESS | FAILURE | WARNING) (COMMIT | ROLLBACK) | CONTINUE (COMMIT|ROLLBACK|NONE))
    ;

set_command
    : SET regular_id (CHAR_STRING | ON | OFF | /*EXACT_NUM_LIT*/numeric | regular_id) // TODO
    ;

exit_command
    : EXIT
    ;

prompt_command
    : PROMPT
    ;

// Common

partition_extension_clause
    : (SUBPARTITION | PARTITION) FOR? expression_list
    ;

column_alias
    : AS? (id | alias_quoted_string)
    | AS
    ;

table_alias
    : (id | alias_quoted_string)
    ;

alias_quoted_string
    : quoted_string
    ;

where_clause
    : WHERE (current_of_clause | condition_wrapper)
    ;

current_of_clause
    : CURRENT OF cursor_name
    ;

into_clause
    : INTO variable_name (',' variable_name)*
    | BULK COLLECT INTO variable_name (',' variable_name)*
    ;

// $>

// $<Common PL/SQL Named Elements

xml_column_name
    : id
    | quoted_string
    ;

cost_class_name
    : id
    ;

attribute_name
    : id
    ;

savepoint_name
    : id
    ;

rollback_segment_name
    : id
    ;

table_var_name
    : id
    ;

schema_name
    : id_expression
    ;

routine_name
    : id ('.' id_expression)* ('@' link_name)?
    ;

package_name
    : id
    ;

implementation_type_name
    : id ('.' id_expression)?
    ;

reference_model_name
    : id
    ;

main_model_name
    : id
    ;

aggregate_function_name
    : id ('.' id_expression)*
    ;

query_name
    : id
    ;

constraint_name
    : id ('.' id_expression)* ('@' link_name)?
    ;

label_name
    : id_expression
    ;

type_name
    : id_expression ('.' id_expression)*
    ;

sequence_name
    : id_expression ('.' id_expression)*
    ;

exception_name
    : id ('.' id_expression)*
    ;

function_name
    : id ('.' id_expression)?
    ;

proc_name
//	: id_expression ('.' id_expression)?
    : (schema_name '.')? id_expression
//	: id ('.' id_expression)?
    ;

trigger_name
    : id ('.' id_expression)?
    ;

variable_name_old
    : (INTRODUCER char_set_name)? id_expression ('.' id_expression)?
    | bind_variable
    ;

index_name
    : id
    ;

cursor_name_old
    : id
    | bind_variable
    ;

record_name
    : id
    | bind_variable
    ;

collection_name
    : id ('.' id_expression)?
    ;

link_name
    : id
    ;

column_name_old
    : id ('.' id_expression)*
    ;

tableview_name
    : id ('.' id_expression)?
      ('@' link_name | /*TODO{!(input.LA(2) == SQL92_RESERVED_BY)}?*/ partition_extension_clause)?
    ;

char_set_name
    : id_expression ('.' id_expression)*
    ;

// $>

// $<Common PL/SQL Specs

// NOTE: In reality this applies to aggregate functions only
keep_clause
    : KEEP '(' DENSE_RANK (FIRST | LAST) order_by_clause ')' over_clause?
    ;

function_argument
    : '(' argument? (',' argument )* ')' keep_clause?
    ;

function_argument_analytic
    : '(' (argument respect_or_ignore_nulls?)? (',' argument respect_or_ignore_nulls?)* ')' keep_clause?
    ;

function_argument_modeling
    : '(' column_name (',' (numeric | NULL) (',' (numeric | NULL))?)?
      USING (tableview_name '.' '*' | '*' | expression column_alias? (',' expression column_alias?)*)
      ')' keep_clause?
    ;

respect_or_ignore_nulls
    : (RESPECT | IGNORE) NULLS
    ;

argument
    : (id '=' '>')? expression_wrapper
    ;

type_spec
    : datatype
    | REF? type_name (PERCENT_ROWTYPE | PERCENT_TYPE)?
    ;

datatype
    : native_datatype_element precision_part? (WITH LOCAL? TIME ZONE)?
    | INTERVAL (YEAR | DAY) ('(' expression_wrapper ')')? TO (MONTH | SECOND) ('(' expression_wrapper ')')?
    ;

precision_part
    : '(' numeric (',' numeric)? (CHAR | BYTE)? ')'
    ;

native_datatype_element
    : BINARY_INTEGER
    | PLS_INTEGER
    | NATURAL
    | BINARY_FLOAT
    | BINARY_DOUBLE
    | BIGINT
    | NATURALN
    | POSITIVE
    | POSITIVEN
    | SECONDDATE
    | SIGNTYPE
    | SIMPLE_INTEGER
    | NVARCHAR
    | DEC
    | INTEGER
    | INT
    | NUMERIC
    | SMALLINT
    | NUMBER
    | DECIMAL
    | DOUBLE PRECISION?
    | FLOAT
    | REAL
    | NCHAR
    | LONG RAW?
    | CHAR
    | CHARACTER
    | VARCHAR2
    | VARCHAR
    | STRING
    | RAW
    | BOOLEAN
    | DATE
    | ROWID
    | UROWID
    | YEAR
    | MONTH
    | DAY
    | HOUR
    | MINUTE
    | SECOND
    | TIMEZONE_HOUR
    | TIMEZONE_MINUTE
    | TIMEZONE_REGION
    | TIMEZONE_ABBR
    | TIMESTAMP
    | TIMESTAMP_UNCONSTRAINED
    | TIMESTAMP_TZ_UNCONSTRAINED
    | TIMESTAMP_LTZ_UNCONSTRAINED
    | YMINTERVAL_UNCONSTRAINED
    | DSINTERVAL_UNCONSTRAINED
    | BFILE
    | BLOB
    | CLOB
    | NCLOB
    | MLSLABEL
    ;

bind_variable
    : (BINDVAR | ':' UNSIGNED_INTEGER)
      (INDICATOR? (BINDVAR | ':' UNSIGNED_INTEGER))?
      ('.' general_element_part)*
    | bind_sql_error_code
    | const_sql_error_code
    | bind_sql_error_message
    | const_sql_error_message
    ;

bind_sql_error_code : '::' SQL_ERROR_CODE ;

const_sql_error_code : SQL_ERROR_CODE ;

bind_sql_error_message : '::' SQL_ERROR_MESSAGE ;

const_sql_error_message : SQL_ERROR_MESSAGE ;

general_element
    : general_element_part ('.' general_element_part)*
    ;

general_element_part
    : (INTRODUCER char_set_name)? id_expression
      ('.' id_expression)* function_argument?
    ;

table_element
    : (INTRODUCER char_set_name)? id_expression ('.' id_expression)*
    ;

// $>

// $<Lexer Mappings

constant
    : TIMESTAMP (quoted_string | bind_variable) (AT TIME ZONE quoted_string)?
    | INTERVAL (quoted_string | bind_variable | general_element_part)
      (DAY | HOUR | MINUTE | SECOND)
      ('(' (UNSIGNED_INTEGER | bind_variable) (',' (UNSIGNED_INTEGER | bind_variable) )? ')')?
      (TO ( DAY | HOUR | MINUTE | SECOND ('(' (UNSIGNED_INTEGER | bind_variable) ')')?))?
    | numeric
    | DATE quoted_string
    | quoted_string
    | NULL
    | TRUE
    | FALSE
    | DBTIMEZONE
    | SESSIONTIMEZONE
    | MINVALUE
    | MAXVALUE
    | DEFAULT
    ;

numeric
    : UNSIGNED_INTEGER
    | APPROXIMATE_NUM_LIT
    ;

numeric_negative
    : MINUS_SIGN numeric
    ;

quoted_string
    : CHAR_STRING
    //| CHAR_STRING_PERL
    | NATIONAL_CHAR_STRING_LIT
    ;

id
    : (INTRODUCER char_set_name)? id_expression
    ;

id_expression
    : regular_id
    | DELIMITED_ID
    ;

not_equal_op
    : NOT_EQUAL_OP
    | '<' '>'
    | '!' '='
    | '^' '='
    ;

greater_than_or_equals_op
    : '>='
    | '>' '='
    ;

less_than_or_equals_op
    : '<='
    | '<' '='
    ;

concatenation_op
    : '||'
    | '|' '|'
    ;

outer_join_sign
    : '(' '+' ')'
    ;

regular_id
    : REGULAR_ID
    | A_LETTER
    | ADD
    | AFTER
    | AGENT
    | AGGREGATE
    //| ALL
    //| ALTER
    | ANALYZE
    //| AND
    //| ANY
    | ARRAY
    // | AS
    //| ASC
    | ASSOCIATE
    | AT
    | ATTRIBUTE
    | AUDIT
    | AUTHID
    | AUTO
    | AUTOMATIC
    | AUTONOMOUS_TRANSACTION
    | BATCH
    | BEFORE
    //| BEGIN
    // | BETWEEN
    | BFILE
    | BINARY_DOUBLE
    | BINARY_FLOAT
    | BINARY_INTEGER
    | BLOB
    | BLOCK
    | BODY
    | BOOLEAN
    | BOTH
    // | BREADTH
    | BULK
    // | BY
    | BYTE
    | C_LETTER
    // | CACHE
    | CALL
    | CANONICAL
    | CASCADE
    //| CASE
    | CAST
    | CHAR
    | CHAR_CS
    | CHARACTER
    //| CHECK
    | CHR
    | CLOB
    | CLOSE
    | CLUSTER
    | COLLECT
    | COLUMNS
    | COMMENT
    | COMMIT
    | COMMITTED
    | COMPATIBILITY
    | COMPILE
    | COMPOUND
    //| CONNECT
    //| CONNECT_BY_ROOT
    | CONSTANT
    | CONSTRAINT
    | CONSTRAINTS
    | CONSTRUCTOR
    | CONTENT
    | CONTEXT
    | CONTINUE
    | CONVERT
    | CORRUPT_XID
    | CORRUPT_XID_ALL
    | COST
    | COUNT
    //| CREATE
    | CROSS
    | CUBE
    //| CURRENT
    | CURRENT_USER
    | CURSOR
    | CUSTOMDATUM
    | CYCLE
    | DATA
    | DATABASE
    //| DATE
    | DAY
    | DB_ROLE_CHANGE
    | DBTIMEZONE
    | DDL
    | DEBUG
    | DEC
    | DECIMAL
    //| DECLARE
    | DECOMPOSE
    | DECREMENT
    //| DEFAULT
    | DEFAULTS
    | DEFERRED
    | DEFINER
    // | DELETE
    // | DEPTH
    //| DESC
    | DETERMINISTIC
    | DIMENSION
    | DISABLE
    | DISASSOCIATE
    //| DISTINCT
    | DOCUMENT
    | DOUBLE
    //| DROP
    | DSINTERVAL_UNCONSTRAINED
    | EACH
    | ELEMENT
    //| ELSE
    //| ELSIF
    | EMPTY
    | ENABLE
    | ENCODING
    //| END
    | ENTITYESCAPING
    | ERRORS
    | ESCAPE
    | EVALNAME
    //| EXCEPTION
    | EXCEPTION_INIT
    | EXCEPTIONS
    | EXCLUDE
    //| EXCLUSIVE
    | EXECUTE
    //| EXISTS
    | EXIT
    | EXPLAIN
    | EXTERNAL
    | EXTRACT
    | FAILURE
    //| FALSE
    //| FETCH
    | FINAL
    | FIRST
    | FIRST_VALUE
    | FLOAT
    | FOLLOWING
    | FOLLOWS
    //| FOR
    | FORALL
    | FORCE
    // | FROM
    | FULL
    | FUNCTION
    //| GOTO
    //| GRANT
    //| GROUP
    | GROUPING
    | HASH
    //| HAVING
    | HIDE
    | HOUR
    //| IF
    | IGNORE
    | IMMEDIATE
    // | IN
    | INCLUDE
    | INCLUDING
    | INCREMENT
    | INDENT
    //| INDEX
    | INDEXED
    | INDICATOR
    | INDICES
    | INFINITE
    | INLINE
    | INNER
    | INOUT
    //| INSERT
    | INSTANTIABLE
    | INSTEAD
    | INT
    | INTEGER
    //| INTERSECT
    | INTERVAL
    // | INTO
    | INVALIDATE
    //| IS
    | ISOLATION
    | ITERATE
    | JAVA
    | JOIN
    | KEEP
    | LANGUAGE
    | LAST
    | LAST_VALUE
    | LEADING
    | LEFT
    | LEVEL
    | LIBRARY
    // | LIKE
    | LIKE2
    | LIKE4
    | LIKEC
    | LIMIT
    | LOCAL
    //| LOCK
    | LOCKED
    | LOG
    | LOGOFF
    | LOGON
    | LONG
    | LOOP
    | MAIN
    | MAP
    | MATCHED
    | MAXVALUE
    | MEASURES
    | MEMBER
    | MERGE
    //| MINUS
    | MINUTE
    | MINVALUE
    | MLSLABEL
    //| MODE
    | MODEL
    | MODIFY
    | MONTH
    | MULTISET
    | NAME
    | NAN
    | NATURAL
    | NATURALN
    | NAV
    | NCHAR
    | NCHAR_CS
    | NCLOB
    | NESTED
    | NEW
    | NO
    | NOAUDIT
    // | NOCACHE
    | NOCOPY
    | NOCYCLE
    | NOENTITYESCAPING
    //| NOMAXVALUE
    //| NOMINVALUE
    | NONE
    // | NOORDER
    | NOSCHEMACHECK
    //| NOT
    //| NOWAIT
    // | NULL
    | NULLS
    | NUMBER
    | NUMERIC
    | NVARCHAR
    | OBJECT
    //| OF
    | OFF
    | OID
    | OLD
    //| ON
    | ONLY
    | OPEN
    //| OPTION
    //| OR
    | ORADATA
    //| ORDER
    | ORDINALITY
    | OSERROR
    | OUT
    | OUTER
    | OVER
    | OVERRIDING
    | PACKAGE
    | PARALLEL_ENABLE
    | PARAMETERS
    | PARENT
    | PARTITION
    | PASSING
    | PATH
    //| PERCENT_ROWTYPE
    //| PERCENT_TYPE
    | PIPELINED
    //| PIVOT
    | PLAN
    | PLS_INTEGER
    | POSITIVE
    | POSITIVEN
    | PRAGMA
    | PRECEDING
    | PRECISION
    | PRESENT
    //| PRIOR
    //| PROCEDURE
    | RAISE
    | RANGE
    | RAW
    | READ
    | REAL
    | RECORD
    | REF
    | REFERENCE
    | REFERENCING
    | REJECT
    | RELIES_ON
    | RENAME
    | REPLACE
    | RESPECT
    | RESTRICT_REFERENCES
    | RESULT
    | RESULT_CACHE
    | RETURN
    | RETURNS
    | RETURNING
    | REUSE
    | REVERSE
    //| REVOKE
    | RIGHT
    | ROLLBACK
    | ROLLUP
    | ROW
    | ROWID
    | ROWS
    | RULES
    | SAMPLE
    | SAVE
    | SAVEPOINT
    | SCHEMA
    | SCHEMACHECK
    | SCN
    // | SEARCH
    | SECOND
    | SEED
    | SEGMENT
    // | SELECT
    | SELF
    // | SEQUENCE
    | SEQUENTIAL
    | SERIALIZABLE
    | SERIALLY_REUSABLE
    | SERVERERROR
    | SESSIONTIMEZONE
    | SET
    | SETS
    | SETTINGS
    //| SHARE
    | SHOW
    | SHUTDOWN
    | SIBLINGS
    | SIGNTYPE
    | SIMPLE_INTEGER
    | SINGLE
    //| SIZE
    | SKIP_
    | SMALLINT
    | SNAPSHOT
    | SOME
    | SPECIFICATION
    | SQLDATA
    | SQLERROR
    | STANDALONE
    //| START
    | STARTUP
    | STATEMENT
    | STATEMENT_ID
    | STATIC
    | STATISTICS
    | STRING
    | SUBMULTISET
    | SUBPARTITION
    | SUBSTITUTABLE
    | SUBTYPE
    | SUCCESS
    | SUSPEND
    //| TABLE
    //| THE
    //| THEN
    | TIME
    | TIMESTAMP
    | TIMESTAMP_LTZ_UNCONSTRAINED
    | TIMESTAMP_TZ_UNCONSTRAINED
    | TIMESTAMP_UNCONSTRAINED
    | TIMEZONE_ABBR
    | TIMEZONE_HOUR
    | TIMEZONE_MINUTE
    | TIMEZONE_REGION
    //| TO
    | TRAILING
    | TRANSACTION
    | TRANSLATE
    | TREAT
    | TRIGGER
    | TRIM
    //| TRUE
    | TRUNCATE
    | TYPE
    | UNBOUNDED
    | UNDER
    //| UNION
    //| UNIQUE
    | UNLIMITED
    //| UNPIVOT
    | UNTIL
    //| UPDATE
    | UPDATED
    | UPSERT
    | UROWID
    | USE
    //| USING
    | VALIDATE
    | VALUE
    //| VALUES
    | VARCHAR
    | VARCHAR2
    | VARIABLE
    | VARRAY
    | VARYING
    | VERSION
    | VERSIONS
    | WAIT
    | WARNING
    | WELLFORMED
    // | WHEN
    | WHENEVER
    // | WHERE
    | WHILE
    //| WITH
    | WITHIN
    | WORK
    | WRITE
    | XML
    | XMLAGG
    | XMLATTRIBUTES
    | XMLCAST
    | XMLCOLATTVAL
    | XMLELEMENT
    | XMLEXISTS
    | XMLFOREST
    | XMLNAMESPACES
    | XMLPARSE
    | XMLPI
    | XMLQUERY
    | XMLROOT
    | XMLSERIALIZE
    | XMLTABLE
    | YEAR
    | YES
    | YMINTERVAL_UNCONSTRAINED
    | ZONE
    | PREDICTION
    | PREDICTION_BOUNDS
    | PREDICTION_COST
    | PREDICTION_DETAILS
    | PREDICTION_PROBABILITY
    | PREDICTION_SET
    | CUME_DIST
    | DENSE_RANK
    | LISTAGG
    | PERCENT_RANK
    | PERCENTILE_CONT
    | PERCENTILE_DISC
    | RANK
    | AVG
    | CORR
    | LAG
    | LEAD
    | MAX
    | MEDIAN
    | MIN
    | NTILE
    | RATIO_TO_REPORT
    | ROW_NUMBER
    | SUM
    | VARIANCE
    | REGR_
    | STDDEV
    | VAR_
    | COVAR_
    ;

A_LETTER:                     A;
ADD:                          A D D;
AFTER:                        A F T E R;
AGENT:                        A G E N T;
AGGREGATE:                    A G G R E G A T E;
ALL:                          A L L;
ALTER:                        A L T E R;
ALPHANUM:                     A L P H A N U M;
ANALYZE:                      A N A L Y Z E;
AND:                          A N D;
ANY:                          A N Y;
ARRAY:                        A R R A Y;
AS:                           A S;
ASC:                          A S C;
ASSOCIATE:                    A S S O C I A T E;
AT:                           A T;
ATTRIBUTE:                    A T T R I B U T E;
AUDIT:                        A U D I T;
AUTHID:                       A U T H I D;
AUTO:                         A U T O;
AUTOMATIC:                    A U T O M A T I C;
AUTONOMOUS_TRANSACTION:       A U T O N O M O U S '_' T R A N S A C T I O N;
BATCH:                        B A T C H;
BEFORE:                       B E F O R E;
BEGIN:                        B E G I N;
BETWEEN:                      B E T W E E N;
BFILE:                        B F I L E;
BINARY_DOUBLE:                B I N A R Y '_' D O U B L E;
BINARY_FLOAT:                 B I N A R Y '_' F L O A T;
BINARY_INTEGER:               B I N A R Y '_' I N T E G E R;
BIGINT:                       B I G I N T;
BLOB:                         B L O B;
BLOCK:                        B L O C K;
BODY:                         B O D Y;
BOOLEAN:                      B O O L E A N;
BOTH:                         B O T H;
BREADTH:                      B R E A D T H;
BULK:                         B U L K;
BY:                           B Y;
BYTE:                         B Y T E;
C_LETTER:                     C;
CACHE:                        C A C H E;
CALL:                         C A L L;
CANONICAL:                    C A N O N I C A L;
CASCADE:                      C A S C A D E;
CASE:                         C A S E;
CAST:                         C A S T;
CHAR:                         C H A R;
CHAR_CS:                      C H A R '_' C S;
CHARACTER:                    C H A R A C T E R;
CHECK:                        C H E C K;
CHR:                          C H R;
CLOB:                         C L O B;
CLOSE:                        C L O S E;
CLUSTER:                      C L U S T E R;
COLLECT:                      C O L L E C T;
COLUMNS:                      C O L U M N S;
COMMENT:                      C O M M E N T;
COMMIT:                       C O M M I T;
COMMITTED:                    C O M M I T T E D;
COMPATIBILITY:                C O M P A T I B I L I T Y;
COMPILE:                      C O M P I L E;
COMPOUND:                     C O M P O U N D;
CONNECT:                      C O N N E C T;
CONNECT_BY_ROOT:              C O N N E C T '_' B Y '_' R O O T;
CONSTANT:                     C O N S T A N T;
CONSTRAINT:                   C O N S T R A I N T;
CONSTRAINTS:                  C O N S T R A I N T S;
CONSTRUCTOR:                  C O N S T R U C T O R;
CONTENT:                      C O N T E N T;
CONTEXT:                      C O N T E X T;
CONTINUE:                     C O N T I N U E;
CONVERT:                      C O N V E R T;
CORRUPT_XID:                  C O R R U P T '_' X I D;
CORRUPT_XID_ALL:              C O R R U P T '_' X I D '_' A L L;
COST:                         C O S T;
COUNT:                        C O U N T;
CREATE:                       C R E A T E;
CROSS:                        C R O S S;
CUBE:                         C U B E;
CURRENT:                      C U R R E N T;
CURRENT_USER:                 C U R R E N T '_' U S E R;
CURSOR:                       C U R S O R;
CUSTOMDATUM:                  C U S T O M D A T U M;
CYCLE:                        C Y C L E;
DATA:                         D A T A;
DATABASE:                     D A T A B A S E;
DATE:                         D A T E;
DAY:                          D A Y;
DB_ROLE_CHANGE:               D B '_' R O L E '_' C H A N G E;
DBTIMEZONE:                   D B T I M E Z O N E;
DDL:                          D D L;
DEBUG:                        D E B U G;
DEC:                          D E C;
DECIMAL:                      D E C I M A L;
DECLARE:                      D E C L A R E;
DECOMPOSE:                    D E C O M P O S E;
DECREMENT:                    D E C R E M E N T;
DEFAULT:                      D E F A U L T;
DEFAULTS:                     D E F A U L T S;
DEFERRED:                     D E F E R R E D;
DEFINER:                      D E F I N E R;
DELETE:                       D E L E T E;
DEPTH:                        D E P T H;
DESC:                         D E S C;
DETERMINISTIC:                D E T E R M I N I S T I C;
DIMENSION:                    D I M E N S I O N;
DISABLE:                      D I S A B L E;
DISASSOCIATE:                 D I S A S S O C I A T E;
DISTINCT:                     D I S T I N C T;
DOCUMENT:                     D O C U M E N T;
DOUBLE:                       D O U B L E;
DROP:                         D R O P;
DSINTERVAL_UNCONSTRAINED:     D S I N T E R V A L '_' U N C O N S T R A I N E D;
EACH:                         E A C H;
ELEMENT:                      E L E M E N T;
ELSE:                         E L S E;
ELSIF:                        E L S I F;
EMPTY:                        E M P T Y;
ENABLE:                       E N A B L E;
ENCODING:                     E N C O D I N G;
END:                          E N D;
ENTITYESCAPING:               E N T I T Y E S C A P I N G;
ERRORS:                       E R R O R S;
ESCAPE:                       E S C A P E;
EVALNAME:                     E V A L N A M E;
EXCEPTION:                    E X C E P T I O N;
EXCEPTION_INIT:               E X C E P T I O N '_' I N I T;
EXCEPTIONS:                   E X C E P T I O N S;
EXCLUDE:                      E X C L U D E;
EXCLUSIVE:                    E X C L U S I V E;
EXECUTE:                      E X E C U T E;
EXISTS:                       E X I S T S;
EXIT:                         E X I T;
EXPLAIN:                      E X P L A I N;
EXTERNAL:                     E X T E R N A L;
EXTRACT:                      E X T R A C T;
FAILURE:                      F A I L U R E;
FALSE:                        F A L S E;
FETCH:                        F E T C H;
FINAL:                        F I N A L;
FIRST:                        F I R S T;
FIRST_VALUE:                  F I R S T '_' V A L U E;
FLOAT:                        F L O A T;
FOLLOWING:                    F O L L O W I N G;
FOLLOWS:                      F O L L O W S;
FOR:                          F O R;
FORALL:                       F O R A L L;
FORCE:                        F O R C E;
FROM:                         F R O M;
FULL:                         F U L L;
FUNCTION:                     F U N C T I O N;
GOTO:                         G O T O;
GRANT:                        G R A N T;
GROUP:                        G R O U P;
GROUPING:                     G R O U P I N G;
HASH:                         H A S H;
HAVING:                       H A V I N G;
HANDLER:                      H A N D L E R;
HIDE:                         H I D E;
HOUR:                         H O U R;
IF:                           I F;
IGNORE:                       I G N O R E;
IMMEDIATE:                    I M M E D I A T E;
IN:                           I N;
INCLUDE:                      I N C L U D E;
INCLUDING:                    I N C L U D I N G;
INCREMENT:                    I N C R E M E N T;
INDENT:                       I N D E N T;
INDEX:                        I N D E X;
INDEXED:                      I N D E X E D;
INDICATOR:                    I N D I C A T O R;
INDICES:                      I N D I C E S;
INFINITE:                     I N F I N I T E;
INLINE:                       I N L I N E;
INNER:                        I N N E R;
INOUT:                        I N O U T;
INSERT:                       I N S E R T;
INSTANTIABLE:                 I N S T A N T I A B L E;
INSTEAD:                      I N S T E A D;
INT:                          I N T;
INTEGER:                      I N T E G E R;
INTERSECT:                    I N T E R S E C T;
INTERVAL:                     I N T E R V A L;
INTO:                         I N T O;
INVALIDATE:                   I N V A L I D A T E;
INVOKER:                      I N V O K E R;
IS:                           I S;
ISOLATION:                    I S O L A T I O N;
ITERATE:                      I T E R A T E;
JAVA:                         J A V A;
JOIN:                         J O I N;
KEEP:                         K E E P;
LANGUAGE:                     L A N G U A G E;
LAST:                         L A S T;
LAST_VALUE:                   L A S T '_' V A L U E;
LEADING:                      L E A D I N G;
LEFT:                         L E F T;
LEVEL:                        L E V E L;
LIBRARY:                      L I B R A R Y;
LIKE:                         L I K E;
LIKE2:                        L I K E '2';
LIKE4:                        L I K E '4';
LIKEC:                        L I K E C;
LIMIT:                        L I M I T;
LOCAL:                        L O C A L;
LOCK:                         L O C K;
LOCKED:                       L O C K E D;
LOG:                          L O G;
LOGOFF:                       L O G O F F;
LOGON:                        L O G O N;
LONG:                         L O N G;
LOOP:                         L O O P;
MAIN:                         M A I N;
MAP:                          M A P;
MATCHED:                      M A T C H E D;
MAXVALUE:                     M A X V A L U E;
MEASURES:                     M E A S U R E S;
MEMBER:                       M E M B E R;
MERGE:                        M E R G E;
MINUS:                        M I N U S;
MINUTE:                       M I N U T E;
MINVALUE:                     M I N V A L U E;
MLSLABEL:                     M L S L A B E L;
MODE:                         M O D E;
MODEL:                        M O D E L;
MODIFY:                       M O D I F Y;
MONTH:                        M O N T H;
MULTISET:                     M U L T I S E T;
NAME:                         N A M E;
NAN:                          N A N;
NATURAL:                      N A T U R A L;
NATURALN:                     N A T U R A L N;
NAV:                          N A V;
NCHAR:                        N C H A R;
NCHAR_CS:                     N C H A R '_' C S;
NCLOB:                        N C L O B;
NESTED:                       N E S T E D;
NEW:                          N E W;
NO:                           N O;
NOAUDIT:                      N O A U D I T;
NOCACHE:                      N O C A C H E;
NOCOPY:                       N O C O P Y;
NOCYCLE:                      N O C Y C L E;
NOENTITYESCAPING:             N O E N T I T Y E S C A P I N G;
NOMAXVALUE:                   N O M A X V A L U E;
NOMINVALUE:                   N O M I N V A L U E;
NONE:                         N O N E;
NOORDER:                      N O O R D E R;
NOSCHEMACHECK:                N O S C H E M A C H E C K;
NOT:                          N O T;
NOWAIT:                       N O W A I T;
NULL:                         N U L L;
NULLS:                        N U L L S;
NUMBER:                       N U M B E R;
NUMERIC:                      N U M E R I C;
NVARCHAR:                     N V A R C H A R;
OBJECT:                       O B J E C T;
OF:                           O F;
OFF:                          O F F;
OID:                          O I D;
OLD:                          O L D;
ON:                           O N;
ONLY:                         O N L Y;
OPEN:                         O P E N;
OPTION:                       O P T I O N;
OR:                           O R;
ORADATA:                      O R A D A T A;
ORDER:                        O R D E R;
ORDINALITY:                   O R D I N A L I T Y;
OSERROR:                      O S E R R O R;
OUT:                          O U T;
OUTER:                        O U T E R;
OVER:                         O V E R;
OVERRIDING:                   O V E R R I D I N G;
PACKAGE:                      P A C K A G E;
PARALLEL_ENABLE:              P A R A L L E L '_' E N A B L E;
PARAMETERS:                   P A R A M E T E R S;
PARENT:                       P A R E N T;
PARTITION:                    P A R T I T I O N;
PASSING:                      P A S S I N G;
PATH:                         P A T H;
PERCENT_ROWTYPE:              '%' R O W T Y P E;
PERCENT_TYPE:                 '%' T Y P E;
PIPELINED:                    P I P E L I N E D;
PIVOT:                        P I V O T;
PLAN:                         P L A N;
PLS_INTEGER:                  P L S '_' I N T E G E R;
POSITIVE:                     P O S I T I V E;
POSITIVEN:                    P O S I T I V E N;
PRAGMA:                       P R A G M A;
PRECEDING:                    P R E C E D I N G;
PRECISION:                    P R E C I S I O N;
PRESENT:                      P R E S E N T;
PRIOR:                        P R I O R;
PROCEDURE:                    P R O C E D U R E;
RAISE:                        R A I S E;
RANGE:                        R A N G E;
RAW:                          R A W;
READ:                         R E A D;
READS:                         R E A D S;
REAL:                         R E A L;
RECORD:                       R E C O R D;
REF:                          R E F;
REFERENCE:                    R E F E R E N C E;
REFERENCING:                  R E F E R E N C I N G;
REJECT:                       R E J E C T;
RELIES_ON:                    R E L I E S '_' O N;
RENAME:                       R E N A M E;
REPLACE:                      R E P L A C E;
RESPECT:                      R E S P E C T;
RESTRICT_REFERENCES:          R E S T R I C T '_' R E F E R E N C E S;
RESULT:                       R E S U L T;
RESULT_CACHE:                 R E S U L T '_' C A C H E;
RETURN:                       R E T U R N;
RETURNS:                      R E T U R N S;
RETURNING:                    R E T U R N I N G;
REUSE:                        R E U S E;
REVERSE:                      R E V E R S E;
REVOKE:                       R E V O K E;
RIGHT:                        R I G H T;
ROLLBACK:                     R O L L B A C K;
ROLLUP:                       R O L L U P;
ROW:                          R O W;
ROWID:                        R O W I D;
ROWS:                         R O W S;
RULES:                        R U L E S;
SAMPLE:                       S A M P L E;
SAVE:                         S A V E;
SAVEPOINT:                    S A V E P O I N T;
SCHEMA:                       S C H E M A;
SCHEMACHECK:                  S C H E M A C H E C K;
SCN:                          S C N;
SEARCH:                       S E A R C H;
SECOND:                       S E C O N D;
SECONDDATE:                   S E C O N D D A T E;
SECURITY:                     S E C U R I T Y;
SEED:                         S E E D;
SEGMENT:                      S E G M E N T;
SELECT:                       S E L E C T;
SELF:                         S E L F;
SEQUENCE:                     S E Q U E N C E;
SEQUENTIAL:                   S E Q U E N T I A L;
SERIALIZABLE:                 S E R I A L I Z A B L E;
SERIALLY_REUSABLE:            S E R I A L L Y '_' R E U S A B L E;
SERVERERROR:                  S E R V E R E R R O R;
SESSIONTIMEZONE:              S E S S I O N T I M E Z O N E;
SET:                          S E T;
SETS:                         S E T S;
SETTINGS:                     S E T T I N G S;
SHARE:                        S H A R E;
SHOW:                         S H O W;
SHUTDOWN:                     S H U T D O W N;
SIBLINGS:                     S I B L I N G S;
SIGNTYPE:                     S I G N T Y P E;
SIMPLE_INTEGER:               S I M P L E '_' I N T E G E R;
SINGLE:                       S I N G L E;
SIZE:                         S I Z E;
SKIP_:                        S K I P;
SMALLINT:                     S M A L L I N T;
SMALLDECIMAL:                 S M A L L D E C I M A L;
SNAPSHOT:                     S N A P S H O T;
SOME:                         S O M E;
SPECIFICATION:                S P E C I F I C A T I O N;
SQL:                          S Q L;
SQLDATA:                      S Q L D A T A;
SQLERROR:                     S Q L E R R O R;
SQLEXCEPTION:                 S Q L E X C E P T I O N;
SQLSCRIPT:                    S Q L S C R I P T;
STANDALONE:                   S T A N D A L O N E;
START:                        S T A R T;
STARTUP:                      S T A R T U P;
STATEMENT:                    S T A T E M E N T;
STATEMENT_ID:                 S T A T E M E N T '_' I D;
STATIC:                       S T A T I C;
STATISTICS:                   S T A T I S T I C S;
STRING:                       S T R I N G;
SUBMULTISET:                  S U B M U L T I S E T;
SUBPARTITION:                 S U B P A R T I T I O N;
SUBSTITUTABLE:                S U B S T I T U T A B L E;
SUBTYPE:                      S U B T Y P E;
SUCCESS:                      S U C C E S S;
SUSPEND:                      S U S P E N D;
TABLE:                        T A B L E;
THE:                          T H E;
THEN:                         T H E N;
TIME:                         T I M E;
TIMESTAMP:                    T I M E S T A M P;
TIMESTAMP_LTZ_UNCONSTRAINED:  T I M E S T A M P '_' L T Z '_' U N C O N S T R A I N E D;
TIMESTAMP_TZ_UNCONSTRAINED:   T I M E S T A M P '_' T Z '_' U N C O N S T R A I N E D;
TIMESTAMP_UNCONSTRAINED:      T I M E S T A M P '_' U N C O N S T R A I N E D;
TIMEZONE_ABBR:                T I M E Z O N E '_' A B B R;
TIMEZONE_HOUR:                T I M E Z O N E '_' H O U R;
TIMEZONE_MINUTE:              T I M E Z O N E '_' M I N U T E;
TIMEZONE_REGION:              T I M E Z O N E '_' R E G I O N;
TINYINT:                      T I N Y I N T;
TO:                           T O;
TRAILING:                     T R A I L I N G;
TRANSACTION:                  T R A N S A C T I O N;
TRANSLATE:                    T R A N S L A T E;
TREAT:                        T R E A T;
TRIGGER:                      T R I G G E R;
TRIM:                         T R I M;
TRUE:                         T R U E;
TRUNCATE:                     T R U N C A T E;
TYPE:                         T Y P E;
UNBOUNDED:                    U N B O U N D E D;
UNDER:                        U N D E R;
UNION:                        U N I O N;
UNIQUE:                       U N I Q U E;
UNLIMITED:                    U N L I M I T E D;
UNPIVOT:                      U N P I V O T;
UNTIL:                        U N T I L;
UPDATE:                       U P D A T E;
UPDATED:                      U P D A T E D;
UPSERT:                       U P S E R T;
UROWID:                       U R O W I D;
USE:                          U S E;
USING:                        U S I N G;
VALIDATE:                     V A L I D A T E;
VALUE:                        V A L U E;
VALUES:                       V A L U E S;
VARCHAR:                      V A R C H A R;
VARCHAR2:                     V A R C H A R '2';
VARIABLE:                     V A R I A B L E;
VARRAY:                       V A R R A Y;
VARYING:                      V A R Y I N G;
VERSION:                      V E R S I O N;
VERSIONS:                     V E R S I O N S;
VIEW:                         V I E W;
WAIT:                         W A I T;
WARNING:                      W A R N I N G;
WELLFORMED:                   W E L L F O R M E D;
WHEN:                         W H E N;
WHENEVER:                     W H E N E V E R;
WHERE:                        W H E R E;
WHILE:                        W H I L E;
WITH:                         W I T H;
WITHIN:                       W I T H I N;
WORK:                         W O R K;
WRITE:                        W R I T E;
XML:                          X M L;
XMLAGG:                       X M L A G G;
XMLATTRIBUTES:                X M L A T T R I B U T E S;
XMLCAST:                      X M L C A S T;
XMLCOLATTVAL:                 X M L C O L A T T V A L;
XMLELEMENT:                   X M L E L E M E N T;
XMLEXISTS:                    X M L E X I S T S;
XMLFOREST:                    X M L F O R E S T;
XMLNAMESPACES:                X M L N A M E S P A C E S;
XMLPARSE:                     X M L P A R S E;
XMLPI:                        X M L P I;
XMLQUERY:                     X M L Q U E R Y;
XMLROOT:                      X M L R O O T;
XMLSERIALIZE:                 X M L S E R I A L I Z E;
XMLTABLE:                     X M L T A B L E;
YEAR:                         Y E A R;
YES:                          Y E S;
YMINTERVAL_UNCONSTRAINED:     Y M I N T E R V A L '_' U N C O N S T R A I N E D;
ZONE:                         Z O N E;

// SAP HANA
AUTONOMOUS:                   A U T O N O M O U S;
CONDITION:                    C O N D I T I O N;
ELSEIF:                       E L S E I F;
EXECUTION:                    E X E C U T I O N;
OVERVIEW:                     O V E R V I E W;
RESIGNAL:                     R E S I G N A L;
MESSAGE_TEXT:                 M E S S A G E '_' T E X T;
SHORTTEXT:                    S H O R T T E X T;
SIGNAL:                       S I G N A L;
SQL_ERROR_CODE:               S Q L '_' E R R O R '_' C O D E;
SQL_ERROR_MESSAGE:            S Q L '_' E R R O R '_' M E S S A G E;
SQLWARNING:                   S Q L W A R N I N G;
TEXT:                         T E X T;
UNNEST:                       U N N E S T;
VARBINARY:                    V A R B  I N A R Y;
STRING_AGG:                   S T R I N G '_' A G G;
CORR_SPEARMAN:                C O R R '_' S P E A R M A N;
VAR:                          V A R;
STDDEV_POP:                   S T D D E V '_' P O P;
VAR_POP:                      V A R '_' P O P;
STDDEV_SAMP:                  S T D D E V '_' S A M P;
VAR_SAMP:                     V A R '_' S A M P;

PREDICTION:                   P R E D I C T I O N;
PREDICTION_BOUNDS:            P R E D I C T I O N '_' B O U N D S;
PREDICTION_COST:              P R E D I C T I O N '_' C O S T;
PREDICTION_DETAILS:           P R E D I C T I O N '_' D E T A I L S;
PREDICTION_PROBABILITY:       P R E D I C T I O N '_' P R O B A B I L I T Y;
PREDICTION_SET:               P R E D I C T I O N '_' S E T;

CUME_DIST:                    C U M E '_' D I S T;
DENSE_RANK:                   D E N S E '_' R A N K;
LISTAGG:                      L I S T A G G;
PERCENT_RANK:                 P E R C E N T '_' R A N K;
PERCENTILE_CONT:              P E R C E N T I L E '_' C O N T;
PERCENTILE_DISC:              P E R C E N T I L E '_' D I S C;
RANK:                         R A N K;

AVG:                          A V G;
CORR:                         C O R R;
LAG:                          L A G;
LEAD:                         L E A D;
MAX:                          M A X;
MEDIAN:                       M E D I A N;
MIN:                          M I N;
NTILE:                        N T I L E;
RATIO_TO_REPORT:              R A T I O '_' T O '_' R  E P O R T;
ROW_NUMBER:                   R O W '_' N U M B E R;
SUM:                          S U M;
VARIANCE:                     V A R I A N C E;
REGR_:                        R E G R '_';
STDDEV:                       S T D D E V;
VAR_:                         V A R '_';
COVAR_:                       C O V A R '_';

fragment A: [aA];
fragment B: [bB];
fragment C: [cC];
fragment D: [dD];
fragment E: [eE];
fragment F: [fF];
fragment G: [gG];
fragment H: [hH];
fragment I: [iI];
fragment J: [jJ];
fragment K: [kK];
fragment L: [lL];
fragment M: [mM];
fragment N: [nN];
fragment O: [oO];
fragment P: [pP];
fragment Q: [qQ];
fragment R: [rR];
fragment S: [sS];
fragment T: [tT];
fragment U: [uU];
fragment V: [vV];
fragment W: [wW];
fragment X: [xX];
fragment Y: [yY];
fragment Z: [zZ];

/*FOR_NOTATION
    :    UNSIGNED_INTEGER
        {state.type = UNSIGNED_INTEGER; emit(); advanceInput();}
        '..'
        {state.type = DOUBLE_PERIOD; emit(); advanceInput();}
        UNSIGNED_INTEGER
        {state.type = UNSIGNED_INTEGER; emit(); advanceInput(); $channel=HIDDEN;}
    ;
*/

//{ Rule #358 <NATIONAL_CHAR_STRING_LIT> - subtoken typecast in <REGULAR_ID>, it also incorporates <character_representation>
//  Lowercase 'n' is a usual addition to the standard
NATIONAL_CHAR_STRING_LIT
    : N '\'' (~('\'' | '\r' | '\n' ) | '\'' '\'' | NEWLINE)* '\''
    ;
//}

//{ Rule #040 <BIT_STRING_LIT> - subtoken typecast in <REGULAR_ID>
//  Lowercase 'b' is a usual addition to the standard
BIT_STRING_LIT
    : B ('\'' ('0' | '1')* '\'' /*SEPARATOR?*/ )+
    ;
//}


//{ Rule #284 <HEX_STRING_LIT> - subtoken typecast in <REGULAR_ID>
//  Lowercase 'x' is a usual addition to the standard
HEX_STRING_LIT
    : X ('\'' ('a'..'f' | 'A'..'F' | '0'..'9')* '\'' /*SEPARATOR?*/ )+
    ;
//}

DOUBLE_PERIOD
    : '.' '.'
    ;

PERIOD
    : '.'
    ;

//{ Rule #238 <EXACT_NUM_LIT>
//  This rule is a bit tricky - it resolves the ambiguity with <PERIOD>
//  It als44o incorporates <mantisa> and <exponent> for the <APPROXIMATE_NUM_LIT>
//  Rule #501 <signed_integer> was incorporated directly in the token <APPROXIMATE_NUM_LIT>
//  See also the rule #617 <unsigned_num_lit>
/*
    : (
            UNSIGNED_INTEGER
            ( '.' UNSIGNED_INTEGER
            | {$type = UNSIGNED_INTEGER;}
            ) ( E ('+' | '-')? UNSIGNED_INTEGER {$type = APPROXIMATE_NUM_LIT;} )?
    | '.' UNSIGNED_INTEGER ( E ('+' | '-')? UNSIGNED_INTEGER {$type = APPROXIMATE_NUM_LIT;} )?
    )
    (D | F)?
    ;*/
//}

UNSIGNED_INTEGER: UNSIGNED_INTEGER_FRAGMENT;

APPROXIMATE_NUM_LIT: FLOAT_FRAGMENT (('e'|'E') ('+'|'-')? (FLOAT_FRAGMENT | UNSIGNED_INTEGER_FRAGMENT))? (D | F)?;


//{ Rule #--- <CHAR_STRING> is a base for Rule #065 <char_string_lit> , it incorporates <character_representation>
//  and a superfluous subtoken typecasting of the "QUOTE"
CHAR_STRING
    : '\'' (~('\'' | '\r' | '\n') | '\'' '\'' | NEWLINE)* '\''
    ;
//}

// Perl-style quoted string, see Oracle SQL reference, chapter String Literals
CHAR_STRING_PERL    : Q ( QS_ANGLE | QS_BRACE | QS_BRACK | QS_PAREN /*| QS_OTHER*/) -> type(CHAR_STRING);
fragment QUOTE      : '\'' ;
fragment QS_ANGLE   : QUOTE '<' .*? '>' QUOTE ;
fragment QS_BRACE   : QUOTE '{' .*? '}' QUOTE ;
fragment QS_BRACK   : QUOTE '[' .*? ']' QUOTE ;
fragment QS_PAREN   : QUOTE '(' .*? ')' QUOTE ;

fragment QS_OTHER_CH: ~('<' | '{' | '[' | '(' | ' ' | '\t' | '\n' | '\r');
/*fragment QS_OTHER:
    QUOTE QS_OTHER_CH { delimeter = _input.La(-1); }
    (. { _input.La(-1) != delimeter }? )* QS_OTHER_CH QUOTE;*/
/*fragment QS_OTHER
//    For C target we have to preserve case sensitivity.
//		@declarations {
//    		ANTLR3_UINT32 (*oldLA)(struct ANTLR3_INT_STREAM_struct *, ANTLR3_INT32);
//		}
//		@init {
//			oldLA = INPUT->istream->_LA;
//            INPUT->setUcaseLA(INPUT, ANTLR3_FALSE);
//		}
		:
		QUOTE delimiter=QS_OTHER_CH
// JAVA Syntax
    ( { input.LT(1) != $delimiter.text.charAt(0) || ( input.LT(1) == $delimiter.text.charAt(0) && input.LT(2) != '\'') }? => . )*
    ( { input.LT(1) == $delimiter.text.charAt(0) && input.LT(2) == '\'' }? => . ) QUOTE
		;*/

//{ Rule #163 <DELIMITED_ID>
DELIMITED_ID
    : '"' (~('"' | '\r' | '\n') | '"' '"')+ '"'
    ;
//}

//{ Rule #546 <SQL_SPECIAL_CHAR> was split into single rules
PERCENT
    : '%'
    ;

AMPERSAND
    : '&'
    ;

LEFT_PAREN
    : '('
    ;

RIGHT_PAREN
    : ')'
    ;

DOUBLE_ASTERISK
    : '**'
    ;

ASTERISK
    : '*'
    ;

PLUS_SIGN
    : '+'
    ;

MINUS_SIGN
    : '-'
    ;

COMMA
    : ','
    ;

SOLIDUS
    : '/'
    ;

AT_SIGN
    : '@'
    ;

ASSIGN_OP
    : ':='
    ;

// See OCI reference for more information about this
BINDVAR
    : ':' SIMPLE_LETTER  (SIMPLE_LETTER | '0' .. '9' | '_')*
    | ':' DELIMITED_ID  // not used in SQL but spotted in v$sqltext when using cursor_sharing
    | ':' UNSIGNED_INTEGER
    | QUESTION_MARK // not in SQL, not in Oracle, not in OCI, use this for JDBC
    ;

COLON
    : ':'
    ;

SEMICOLON
    : ';'
    ;

LESS_THAN_OR_EQUALS_OP
    : '<='
    ;

LESS_THAN_OP
    : '<'
    ;

GREATER_THAN_OR_EQUALS_OP
    : '>='
    ;

NOT_EQUAL_OP
    : '!='
    | '<>'
    | '^='
    | '~='
    ;

CARRET_OPERATOR_PART
    : '^'
    ;

TILDE_OPERATOR_PART
    : '~'
    ;

EXCLAMATION_OPERATOR_PART
    : '!'
    ;

GREATER_THAN_OP
    : '>'
    ;

fragment
QUESTION_MARK
    : '?'
    ;

// protected UNDERSCORE : '_' SEPARATOR ; // subtoken typecast within <INTRODUCER>
CONCATENATION_OP
    : '||'
    ;

VERTICAL_BAR
    : '|'
    ;

EQUALS_OP
    : '='
    ;

//{ Rule #532 <SQL_EMBDD_LANGUAGE_CHAR> was split into single rules:
LEFT_BRACKET
    : '['
    ;

RIGHT_BRACKET
    : ']'
    ;

//}

//{ Rule #319 <INTRODUCER>
INTRODUCER
    : '_' //(SEPARATOR {$type = UNDERSCORE;})?
    ;

//{ Rule #479 <SEPARATOR>
//  It was originally a protected rule set to be filtered out but the <COMMENT> and <'-'> clashed.
/*SEPARATOR
    : '-' -> type('-')
    | COMMENT -> channel(HIDDEN)
    | (SPACE | NEWLINE)+ -> channel(HIDDEN)
    ;*/
//}

SPACES
    : [ \t\r\n]+ -> skip
    ;

//{ Rule #504 <SIMPLE_LETTER> - simple_latin _letter was generalised into SIMPLE_LETTER
//  Unicode is yet to be implemented - see NSF0
fragment
SIMPLE_LETTER
    : 'a'..'z'
    | 'A'..'Z'
    ;
//}

//  Rule #176 <DIGIT> was incorporated by <UNSIGNED_INTEGER>
//{ Rule #615 <UNSIGNED_INTEGER> - subtoken typecast in <EXACT_NUM_LIT>
fragment
UNSIGNED_INTEGER_FRAGMENT
    : ('0'..'9')+
    ;
//}

fragment
FLOAT_FRAGMENT
    : UNSIGNED_INTEGER* '.'? UNSIGNED_INTEGER+
    ;

//{ Rule #097 <COMMENT>
SINGLE_LINE_COMMENT: '--' ( ~('\r' | '\n') )* (NEWLINE|EOF) -> channel(HIDDEN);
MULTI_LINE_COMMENT: '/*' .*? '*/' -> channel(HIDDEN);

// SQL*Plus prompt
// TODO should be grammar rule, but tricky to implement
PROMPT
	: 'prompt' SPACE ( ~('\r' | '\n') )* (NEWLINE|EOF)
	;

//{ Rule #360 <NEWLINE>
fragment
NEWLINE: '\r'? '\n';

fragment
SPACE: [ \t];

REGULAR_ID
    : (SIMPLE_LETTER) (SIMPLE_LETTER | '$' | '_' | '#' | '0'..'9')*
    ;
