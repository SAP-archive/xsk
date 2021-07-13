/*
 * Grammer and Lexer for parsing hdbti files. 
 * 
 * When this file is changed you need to regenerate the hdbti.js
 * file:
 * npm install -g jison
 * jison hdbti.jison
 */

%{

    function merge(obj1, obj2) {
        var obj3 = {};
        for (var attrname in obj1) { obj3[attrname] = obj1[attrname]; }
        for (var attrname in obj2) { obj3[attrname] = obj2[attrname]; }
        return obj3;
    }

    function skipQuote(str) {
        return str.substring(1, str.length-1);
    }

%}

/* lexical grammar */
%lex

%x ML_COMMENT
%%

\s+                   /* skip whitespace */
"//".*                /* skip comments */

<INITIAL>"/*"                   this.begin('ML_COMMENT');
<ML_COMMENT>"*/"                this.begin('INITIAL');
<ML_COMMENT>[\w\s\[\]=_\":,;\n\r]+  { }
"["                   return '[';
"]"                   return ']';
"{"                   return '{';
"}"                   return '}';
","                   return ',';
":"                   return ":";
";"                   return ';';
"="                   return '=';
"import"              return 'IMPORT';
"table"               return 'TABLE';
"hdbtable"            return 'TABLE';
"cdstable"            return 'TABLE';
"schema"              return 'SCHEMA';
"file"                return 'FILE';
"header"              return 'HEADER';
"delimField"          return 'DELIMFIELD';
"delimEnclosing"      return 'DELIMENCLOSING';
"distinguishEmptyFromNull"  return 'DISTINGUISHEMPTYFROMNULL';
"useHeaderNames"      return 'USEHEADERNAMES';
"keys"                return 'KEYS';
"true"                return 'TRUE';
"false"               return 'FALSE';
(["'])(?:\\\1|.)*?\1  return 'ID';
<<EOF>>               return 'EOF';

/lex


%start hdbtifile

%%
hdbtifile
    : hdbtilist ";" EOF
    {return $1}
    ;

hdbtilist
    : hdbtistmt
    {$$ = $1}
    | hdbtilist ";" hdbtistmt
    {$$ = $1.concat($3)}
    ;

hdbtistmt
    : IMPORT '=' '[' importlist ']'
    { $$ = $4;}
    ;

importlist
    : importstmt
    {$$ = [$1]}
    | importlist ',' importstmt
    {$$ = $1.concat($3)}
    | 
    {$$ = []}
    ;

importstmt
    : '{' imports '}'
    { $$ = $2;}
    ;

imports
    : import
    | imports import
    { $$ = merge($1, $2);}
    ;

import 
    : TABLE  '=' value ';'
    {$$ = {table: skipQuote($3)}}
    | SCHEMA '=' value ';'
    {$$ = {schema: skipQuote($3)}}
    | FILE '=' value ';'
    {$$ = {file: skipQuote($3)}}
    | HEADER '=' headervalue ';'
    {$$ = {header: $3}}
    | DELIMFIELD '=' value ';'
    {$$ = {delimiter: skipQuote($3)}}
    | DELIMENCLOSING '=' value ';'
    {$$ = {delimEnclosing: skipQuote($3)}}
    | DISTINGUISHEMPTYFROMNULL '=' booleanValue ';'
    {$$ = {distinguishEmptyFromNull: $3}}
    | USEHEADERNAMES '=' booleanValue ';'
    {$$ = {useHeaderNames: $3}}
    | keys ';'
    {$$ = {keys: $1}}
    ;

headervalue
    : TRUE
    {$$ = true}
    | FALSE
    {$$ = false}
    ;

booleanValue
    : TRUE
    {$$ = true}
    | FALSE
    {$$ = false}
    ;

keys: 
    KEYS '=' '[' kvlist ']'
    {$$ = $4}
    ;

kvlist
    : kv
    { $$ = [$1];}
    | kvlist ',' kv
    { $$ = $1.concat($3)}
    ;

kv
    : value ':' value
    { $$ = {}; $$[skipQuote($1)] = skipQuote($3)}
    ;

value
    : ID
    ;
