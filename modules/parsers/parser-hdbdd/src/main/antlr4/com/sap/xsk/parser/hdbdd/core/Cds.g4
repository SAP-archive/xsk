// ANTLR4 grammar to generate Parser and Lexer for CDS-Language

// To be built the parser by hand, install Java, download the ANTLR4 tool, then
//   antlr4 -no-listener -o ../gen language.g4
// Alternatively, install Java, and use
//   npm run download && npm run gen
//
// To test the parser in the REPL, see file './lib/language/antlrParser.js'.

// This grammar is built according to the following guidelines:
//
//  * Do not express every syntactical restriction by grammar rules, and do
//    not define a grammar which allows every nonsense.  We might specify
//    syntactical restrictions in a certain form inside actions or semantic
//    predicates to have them directly available for IDE code completion.
//
//  * Keep the number of token types small.  Thus, do not define different
//    token types for things which are not distinguished in the parser.
//    Examples: one token type for numbers (have a check if you just want to
//    allow integers at certain places), one token type for non-quoted and
//    quoted identifiers.
//
//  * Keep the number of keywords as small as possible.  Thus, built-ins is a
//    topic for the semantic analysis, not the grammar.  Examples: no keywords
//    for built-in types or built-in SQL functions.  This also avoids noise in
//    the grammar and a huge/slow generated parser.
//  ┌─────────────────────────────────────────────────────────────────────────┐
//    For our adapted ANTLR error strategy concerning (non-reserved) keywords,
//    make sure to define non-reserved keywords between the lexer rule `Number`
//    and `Identifier`.  The latter must be the second last rule, the last is
//    `IllegalToken`.  Do not rename these three rules.  Add each new
//    non-reserved keyword to rule `ident`, but check for ambiguities!
//  └─────────────────────────────────────────────────────────────────────────┘
//
//  * Left-factor the parser grammar if the same initial part covers more than
//    one or two tokens.  ANTLRs adaptive predication allows to write "natural"
//    rules, but slows down parsing, especially if a long lookahead is needed
//    to solve an LLk ambiguity.  Therefore, try to avoid it in rules which are
//    called often.  Unfortunately, we cannot use ANTLR3's grammar and subrule
//    option 'k' (lookahead depth) anymore...  Therefore...
//  ┌─────────────────────────────────────────────────────────────────────────┐
//    Before each alternative with LL1 ambiguities (looking at the next token
//    is not enough for a decision), write a comment starting with `#ATN:`
//    which describes the ambiguity.  Additionally, put a comment `/* #ATN n
//    */` INSIDE an (`@after`) action of a rule if the corresponding function
//    in '../gen/languageParser.js' contains `n` occurrences of
//    `adaptivePredict` calls.  This is checked in 'test/testCompiler.js',
//    which also counts the total number of `adaptivePredict` occurrences.
//  └─────────────────────────────────────────────────────────────────────────┘
//
//  * For fast parsing and lower memory consumption, we use ANTLR4 with SLL
//    prediction-mode only.  That means that ANTLR does not use the actual call
//    stack when deciding which alternative to choose in a rule.  You might
//    need to copy a rule manually to get less ambiguities - this might be a
//    good idea anyway to avoid calls to `adaptivePredict`, see the rules
//    starting with `annotationAssignment_`.
//
//  * Factoring out a sub rule into a named rule influences the error recovery:
//    the parser tries to consume all tokens which are neither in the follow
//    set of loops and named rules.  So be careful.
//
//  * Do not use actions in the lexer.  Examples: de-quote string literals not
//    in the lexer, but in the parser; do not throw errors, but produce error
//    tokens if necessary.
//
//  * Use actions in the parser to produce a Augmented CSN model.  To have it
//    also in the case of syntax errors, produce it by adding sub-nodes to a
//    parent node, not by returning the nodes (the latter is fine for secondary
//    attachments).
//
//  * Action code should be a one-liner (<100 chars); usually, just one action
//    is called per alternative (plus the @after action which sets the AST
//    location).  For more complicated code, define a method in file
//    './genericAntlrParser.js'.
//
//  * Do not write lexer rules for tokens like ';', use ';' directly in the
//    parser rule.  Advantage: better error messages; taste: more or less
//    readable grammar; disadvantage: debugging in generated code.
//
//  * Use all-upper token names for keywords (e.g. CONTEXT), capitalized ones
//    (e.g. Number) for others - EOF is the exception (is ANTLR-builtin).
//    Remember: parser rule names in ANTLR start with a lower-case letter.
//
//  * No useless parentheses in the grammar.  There are just two binary grammar
//    operators: alternative (`|`) and sequence.  It should not be too hard to
//    remember that sequence binds stronger than alternative.
//
//  * Use the following indentation rules:
//     - rule header: indentation 0 + 2* parentheses/braces depth
//     - rule colon (':' separating header & body): 2
//     - rule body: 4 + 2* parentheses/braces depth, -2 for certain chars at
//       beginning of line: '|', ')', ']' or '}'
//     - inside action: as for the action language, e.g. function argument
//       alignment
//     - rule semicolon (';' ending body, before exceptions): 2
//     - rule exceptions (not used): 2 + 2* parentheses/braces depth

// Some practical info:
//
//  * The end location for the match of a rule is just available in the @after
//    action.  Use method `attachLocation` there on the produced AST.
//
//  * Be careful with the rule names: the methods in antlr4.Parser, the methods
//    in `./antlrParser' and the parser rule names share the same namespace.
//    Any shadowing lead to an exception when running 'test/testCompiler.js'.
//
//  * Be careful with names for rule arguments, returns, locals and rule
//    reference labels: the names `parser`, `parent` and `invokingState` cannot
//    be used (these are added by the generator).
//
//  * The ANTLR error "missing attribute access on rule reference c in $c" can
//    be solved with using $ctx.c instead of $c
//
//  * If you want to set a property starting with '$' like $syntax, use
//    obj['$'+'syntax'] as the ANTLR tool would replace $syntax by $ctx.syntax

grammar language;
options {
  language = JavaScript;
  superClass = genericAntlrParser;
}
tokens {
  VIRTUAL,                      // used with setLocalToken()
  OVER,                         // used with setLocalTokenIfBefore()
  HelperToken1,                 // used with setLocalToken(), does not appear in messages
  HelperToken2,                 // used with setLocalToken(), does not appear in messages
  HideAlternatives,             // hide alternative tokens (no token seq!)
  GenericArgFull,               // via token rewriting according to specialFunctions
  DOTbeforeBRACE,               // via token rewrite
  COMPOSITIONofBRACE            // via token rewrite in rule typeAssociationBase
}

// Top-Level -----------------------------------------------------------------

start returns [ source = { kind: 'source' } ] locals [ _sync = 'recover' ]
  :
    usingDeclaration[$source]*
    (
      namespaceDeclaration[$source]
      ( usingDeclaration[$source] | artifactDef[$source] )*
    |
      artifactDef[$source]
      ( usingDeclaration[$source] | artifactDef[$source] )*
    )?
    EOF
  ;

queryEOF returns [ query ]
  :
    q=queryExpression { $query = $q.query; } EOF
  ;

conditionEOF returns [ cond ]
  :
    c=condition { $cond = $c.cond; } EOF
  ;

namespaceDeclaration[ source ] locals[ decl = {} ]
@after { $source.namespace = this.attachLocation($decl); }
  :
    NAMESPACE simplePath[ $decl, 'Namespace' ] ';'
  ;

usingDeclaration[ source ] locals[ decl ]
@after { if ($decl) this.attachLocation($decl); }
  :
    USING
    (
      FROM str=String
       {
         if (!$source.dependencies) $source.dependencies = [];
         $source.dependencies.push( this.quotedLiteral( $str, 'string' ) );
      }
    |
      path=externalPath
      { $decl = this.addItem( $source, 'usings', 'using', [], { extern: $path.extern } ); }
      ( AS name=ident['Using'] { $decl.name = $name.id; }
      | { this.classifyImplicitName( 'Using' ); }
      )
      ( FROM str=String
        {
          if (!$source.dependencies) $source.dependencies = [];
          $source.dependencies.push( $decl.fileDep = this.quotedLiteral( $str, 'string' ) );
        }
      )?
    |
      { $decl = this.addItem( $source, 'usings', 'using', [] ); }
      // We could just create "independent" USING declaration, but if we want
      // to have some check in the future whether the external artifacts are
      // really in the FROM source...
      '{'
      innerUsing[ $decl ]
      ( ',' { if (this.isStraightBefore("}")) break; } // allow ',' before '}'
        innerUsing[ $decl ] )*
      '}'
      ( FROM str=String
        {
          if (!$source.dependencies) $source.dependencies = [];
          $source.dependencies.push( $decl.fileDep = this.quotedLiteral( $str, 'string' ) );
        }
      )?
    )
    ';'
  ;

innerUsing[ using ] locals[ decl ]
@after { if ($decl) this.attachLocation($decl); }
  :
    path=externalPath
    { $decl = this.addItem( $using, 'usings', 'using', null, { extern: $path.extern } ); }
    ( AS name=ident['Using'] { $decl.name = $name.id; }
    | { this.classifyImplicitName( 'Using' ); }
    )
  ;

externalPath returns [ extern = {} ]
  :
    simplePath[ $extern, 'global' ]
  ;

// We have two versions of the annotation assignment rule, because we do not
// want to let the ambiguity in select items (solution: "either" possibility)
// creep into all annotation assignments:
//   view V(p) as select from E {    // either: anno value "ref p", select item -x
//     @anno :p - x as x;            // or: anno value true, select item :p-x
//   }

annotationAssignment_1[ annos ] locals[ assignment = { name: {} } ]
@after { $annos.push( this.attachLocation($assignment) ); }
  :
    annotationPath[ $assignment.name, 'anno' ]
    annotationPathVariant[ $assignment.name ]?
    (
      ':' { this.meltKeywordToIdentifier(true); } // allow path as anno value start with reserved
      val=annoValue { $assignment.value = $val.val; }
    )?
  ;

annotationAssignment_paren[ annos ]
  :
    '('
    // allow completely useless `@()` with a warning, do not offer it for completion
    {
      this.meltKeywordToIdentifier();
      if (this.isStraightBefore(')')) {
        this.warning( 'syntax-anno-useless',
                      this.tokenLocation( this._input.LT(-2), this.getCurrentToken() ),
                      { code: '@()' },
                      'Ignored useless $(CODE)' );
        this.matchWildcard();   // we know it is the ')' - we do not reach the final match
        return $ctx;
      }
    }
    annotationAssignment_1[ $annos ]
    ( ','
      {
        this.meltKeywordToIdentifier();
        if (this.isStraightBefore(')')) break; // allow ',' before ')'
      }
      annotationAssignment_1[ $annos ]
    )*
    ')'
  ;

annotationAssignment_fix[ annos ] locals[ assignment ]
// value outside @(...)
@after {
  if ($assignment) {
    $annos.push( this.attachLocation($assignment) );
    this.docComment( $annos );
  }
} :
    '@'
    (
      annotationAssignment_paren[ annos ]
    |
      { $assignment = { name: {} }; }
      annotationPath[ $assignment.name, 'anno' ]
      annotationPathVariant[ $assignment.name ]?
      {
        var t = this.getCurrentToken();
        if (t.text === ':')
          this.warning( 'syntax-anno-short', $assignment.name.location,
                        { code: '@(...)' },
                        'Better use $(CODE) for annotation assignments here' );
      }
    )
  ;

annotationAssignment_ll1[ annos ] locals[ assignment ]
@after {
  if ($assignment) {
    $annos.push( this.attachLocation($assignment) );
    this.docComment( $annos );
  }
} :
    '@'
    (
      annotationAssignment_paren[ annos ]
    |
      { $assignment = { name: {} }; }
      annotationPath[ $assignment.name, 'anno' ]
      annotationPathVariant[ $assignment.name ]?
      (
        ':' { this.meltKeywordToIdentifier(true); } // allow path as anno value start with reserved
        val=annoValue { $assignment.value = $val.val; }
      )?
    )
  ;

// Has previously used ATN, now via local token rewrite
annotationAssignment_atn[ annos ] locals[ assignment ]
@after {
  if ($assignment) {
    $annos.push( this.attachLocation($assignment) );
    this.docComment( $annos );
  }
} :
    '@'
    (
      annotationAssignment_paren[ annos ]
    |
      { $assignment = { name: {} }; }
      annotationPath[ $assignment.name, 'anno' ]
      // '#' is in the follow set of this rule, as it is used in rule "selectItemDef"
      // before an "expression" which can start with a '#' for an enum value
      // -> used to introduce variant name if and only if in same line as previous token
      { this.setLocalToken( '#', 'HelperToken1', null, true ); }
      (
        HelperToken1 { this.meltKeywordToIdentifier(); }
        variant=ident['variant'] { $assignment.name.variant = $variant.id; }
      )?
      // ':' is in the follow set of this rule, as it is used in rule "selectItemDef"
      // before an "expression" which can start with a ':' for a parameter reference
      // -> used to introduce assignment value if and only if in same line as previous token
      { this.setLocalToken( ':', 'HelperToken2', null, true ); }
      ( HelperToken2                        // ':'
        { this.meltKeywordToIdentifier(true); } // allow path as anno value start with reserved
        (
          val=annoValueBase { $assignment.value = $val.val; }
        |
          { $assignment.value = {}; }              // TODO: think about expression value representation
          at='@'? annotationPath[ $assignment.value, 'ref', $at ]
          { this.setLocalToken( '#', 'HelperToken1', null, true ); } // see above
          (
            HelperToken1 { this.meltKeywordToIdentifier(); }
            variant=ident['variant'] { $assignment.value.variant = $variant.id; }
          )?
        )
      )?
    )
  ;

// Main artifact definitions -------------------------------------------------

requiredSemi
  : ';'
  | { return $ctx; }            // do not actually parse the closing brace
    '}'
  ;

optionalSemi
  : { this.noAssignmentInSameLine(); } // issue warning for } @Anno \n? NextDef
    ';'?
  ;

artifactDef[ outer, defOnly = false ] locals[ annos = [] ] // cannot use `parent` as parameter name!
@after{ /* #ATN 1 */ }
  :
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    (
      DEFINE?
      ( contextDef[ $outer, this.startLocation(), $annos, defOnly ]
      | entityDef[ $outer, this.startLocation(), $annos ]
      | typeDef[ $outer, this.startLocation(), $annos ]
      | aspectDef[ $outer, this.startLocation(), $annos ]
      | annotationDef[ $outer, this.startLocation(), $annos ]
      | viewDef[ $outer, this.startLocation(), $annos ]
      | eventDef[ $outer, this.startLocation(), $annos ]
      | actionFunctionMainDef[ $outer, this.startLocation(), $annos ]
      )
    |
      extend=EXTEND
      { if (defOnly) // this is a syntax restriction which is ensured in CSN in
                     // another way
          this.error( 'syntax-extend-context', $extend,
                      { code: 'EXTEND artifact', kind: defOnly },
                      'No $(CODE) within $(KIND) extensions' ); }
      // #ATN: EXTEND elem, while CONTEXT, ENTITY etc are not reserved
      ( extendContext[ $outer, this.startLocation(), $annos ]
      | extendEntity[ $outer, this.startLocation(), $annos ]
      | extendProjection[ $outer, this.startLocation(), $annos ]
      | extendType[ $outer, this.startLocation(), $annos ]
      | extendAspect[ $outer, this.startLocation(), $annos ]
        // TODO: what about extendAction
      | extendArtifact[ $outer, this.startLocation(), $annos ]
      )
    |
      annotate=ANNOTATE
      { if (defOnly) // this is a syntax restriction which is ensured in CSN in
                     // another way
          this.error( 'syntax-extend-context', $annotate,
                      { code: 'ANNOTATE artifact', kind: defOnly },
                      'No $(CODE) within $(KIND) extensions' );
        this.meltKeywordToIdentifier();
      }
      annotateArtifact[ $outer, this.startLocation(), $annos ] // not kind-specific
    )
  ;

contextDef[ outer, loc, annos, defOnly = false ] locals[ art, name = {} ]
@after { this.attachLocation($art); }
  :
    ( CONTEXT | service=SERVICE ) simplePath[ $name, $service ? 'Service' : 'Context' ]
    { $art = this.addDef( $outer, 'artifacts', $service ? 'service' : 'context', $name, $annos,
                          {}, $loc );
      this.docComment( $annos ); }
    annotationAssignment_fix[ $annos ]*
    (
      '{' artifactDef[ $art, defOnly, true ]* '}'
      optionalSemi
    |
      requiredSemi
    )
  ;

extendContext[ outer, loc, annos ] locals[ art, name = {} ]
@after { this.attachLocation($art); }
  :
    ( CONTEXT | service=SERVICE ) simplePath[ $name, $service ? 'Service' : 'Context' ] // not 'Extend' here
    { $art = this.addItem( $outer, 'extensions', 'extend', $annos,
                           { name: $name, expectedKind: $service ? 'service' : 'context' },
                           $loc );  }
    ( WITH { this.noSemicolonHere(); } )?
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    (
      '{'
      artifactDef[ $art, $service ? 'SERVICE' : 'CONTEXT', true ]*
      '}'
      optionalSemi
    |
      requiredSemi
    )
  ;

entityDef[ outer, loc, annos ] locals[ art, name = {} ]
@after { this.attachLocation($art); }
  :
    ENTITY simplePath[ $name, 'Entity' ]
    { $art = this.addDef( $outer, 'artifacts', 'entity', $name, $annos, {}, $loc );
      this.docComment( $annos ); }
    annotationAssignment_fix[ $annos ]*
    entityParameters[ $art ]?
    (
      ( ':'
        includeRef[ $art ]
        ( ',' { if (this.isStraightBefore('{')) break; } // allow ',' before '{' // }}
          includeRef[ $art ]
        )*
      )?
      '{'
      { $art.elements = Object.create(null); } // better for include and annotate
      elementDef[ $art ]*
      '}'
      // TODO: action definitions in a specific section?
      ( ACTIONS '{' actionFunctionDef[ $art ]* '}' )?
      optionalSemi
    |
      AS
      ( qe=queryExpression
        { $art.query = $qe.query; $art['$'+'syntax'] = 'entity' }
        ( ACTIONS '{' actionFunctionDef[ $art ]* '}' optionalSemi
        | requiredSemi
        )
      | qp=projectionSpec
        { $art.query = $qp.query; $art['$'+'syntax'] = 'projection'; }
        projectionClauses[ $qp.query ]
        ( ACTIONS '{' actionFunctionDef[ $art ]* '}' )?
        optionalSemi            // TODO: not fully correct without columns or excluding
      )
    )
  ;

projectionSpec returns[ query ] locals[ src ]
@after { this.attachLocation($query); }
  :
    proj=PROJECTION ON               // FIXME: First draft only, details unclear/unspecified
    // now a simplified `tableTerm`:
    {
      $src = { path: [], scope: 0 };
      $query = { op: this.tokenLocation( $proj, undefined, 'SELECT' ), from: $src, location: this.startLocation() };
    }
    fromPath[ $src, 'artref']
    ( ':'
      { $src.scope = $src.path.length; }
      fromPath[ $src, 'ref']
    )?
    ( AS aliasName=ident['FromAlias'] { $src.name = $aliasName.id } )?
    // ANTLR errors are better if we use ( A )? instead of ( A | ):
    { if (!$src.name) this.classifyImplicitName( $src.scope ? 'FromAlias' : 'Without' ); }
    bracedSelectItemListDef[ $query ]?
    excludingClause[ $query ]?
  ;

projectionClauses[ query ]
@after { this.attachLocation($query); }
  :
    ( WHERE cond=condition { $query.where = $cond.cond; } )?
    (
      GROUP BY
      e1=expression { $query.groupBy = [ $e1.expr ]; }
      ( ',' en=expression { $query.groupBy.push( $en.expr ); } )*
    )?
    ( HAVING having=condition { $query.having = $having.cond; } )?
    ( ob=orderByClause[ $query ] { $query = $ob.query; } ) ?
    ( lc=limitClause[ $query ]   { $query = $lc.query; } ) ?
  ;

excludingClause[ query ]
  :
    // syntax is less than ideal - EXCLUDING is only useful for `*` - with
    // this syntax, people wonder what happens with explicit select items
    EXCLUDING '{'
    projectionExclusion[ $query ]
    ( ',' { if (this.isStraightBefore("}")) break; } // allow ',' before '}'
      projectionExclusion[ $query ]
    )*
    '}'
  ;

projectionExclusion[ outer ] locals[ art ]
@after { this.attachLocation($art); }
  :
    name=ident['ref']
    { $art = this.addDef( $outer, 'excludingDict', '', $name.id ); }
  ;

extendEntity[ outer, loc, annos ] locals[ art, name = {} ]
@after { /* #ATN 1 */ this.attachLocation($art); }
  :
    ENTITY simplePath[ $name, 'Extend' ]
    { $art = this.addItem( $outer, 'extensions', 'extend', $annos,
                           { name: $name, expectedKind: 'entity' }, $loc );  }
    (
      WITH { this.noSemicolonHere(); this.docComment( $annos ); }
      annotationAssignment_ll1[ $annos ]*
      // ATN: the ref can start with ACTIONS
      (
        includeRef[ $art ]
        requiredSemi
      |
        extendForEntity[ $art ]
      )
    |
      { this.docComment( $annos ); }
      annotationAssignment_ll1[ $annos ]*
      extendForEntity[ $art ]
    )
  ;

extendForEntity[ art ]
  :
    '{'
    elementDefOrExtend[ $art ]*
    '}'
    ( ACTIONS '{' actionFunctionDef[ $art ]* '}' )?
    optionalSemi
  |
    ACTIONS '{' actionFunctionDef[ $art ]* '}'
    optionalSemi
  |
    requiredSemi
  ;

extendProjection[ outer, loc, annos ] locals[ art, name = {} ]
@after { this.attachLocation($art); }
  :
    expected=PROJECTION simplePath[ $name, 'Extend' ]
    {
      $art = this.addItem( $outer, 'extensions', 'extend', $annos,
                           { name: $name, expectedKind: 'entity' }, // or 'projection'?
                           $loc );  }
    ( WITH { this.noSemicolonHere(); } )?
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    (
      '{' { $art.columns = []; }
      (
        selectItemDef[ $art.columns ]
        ( ',' { if (this.isStraightBefore("}")) break; } // allow ',' before '}'
          selectItemDef[ $art.columns ]
        )*
      )?
      '}'
      ( ACTIONS '{' actionFunctionDef[ $art ]* '}' )?
      optionalSemi
    |
      ACTIONS '{' actionFunctionDef[ $art ]* '}'
      optionalSemi
    |
      requiredSemi
    )
  ;

// TODO: no action extension?
actionFunctionDef[ outer ] locals[ art, annos = [] ]
@after { this.attachLocation($art); }
  :
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    (
      ACTION name=ident['BoundAction']
      { $art = this.addDef( $outer, 'actions', 'action', $name.id, $annos );
        this.docComment( $annos ); }
      annotationAssignment_fix[ $annos ]*
      parameterListDef[ $art ]
      ( returnTypeSpec[ $art, $annos ] | requiredSemi )
    |
      FUNCTION name=ident['BoundAction']
      { $art = this.addDef( $outer, 'actions', 'function', $name.id, $annos );
        this.docComment( $annos ); }
      annotationAssignment_fix[ $annos ]*
      parameterListDef[ $art ]
      returnTypeSpec[ $art, $annos ]
    )
  ;

actionFunctionMainDef[ outer, loc, annos ] locals[ art, name = {} ]
@after { this.attachLocation($art); }
  :
    ACTION simplePath[ $name, 'Action' ]
    { $art = this.addDef( $outer, 'artifacts', 'action', $name, $annos, {}, $loc );
      this.docComment( $annos ); }
    annotationAssignment_fix[ $annos ]*
    parameterListDef[ $art ]
    ( returnTypeSpec[ $art, $annos ] | requiredSemi )
  |
    FUNCTION simplePath[ $name, 'Action' ]
    { $art = this.addDef( $outer, 'artifacts', 'function', $name, $annos, {}, $loc );
      this.docComment( $annos ); }
    annotationAssignment_fix[ $annos ]*
    parameterListDef[ $art ]
    returnTypeSpec[ $art, $annos ]
  ;

eventDef[ outer, loc, annos ] locals[ art, name = {} ]
@after { /* #ATN 1 */ this.attachLocation($art); }
  :
    EVENT simplePath[ $name, 'Event' ]
    { $art = this.addDef( $outer, 'artifacts', 'event', $name, $annos, {}, $loc );
      this.docComment( $annos ); }
    annotationAssignment_fix[ $annos ]*
    (
      typeStruct[ $art ] optionalSemi
    |
      ':'
      // #ATN: includeRef can be / start with PROJECTION
      (
        { $art.type = {}; }
        simplePath[ $art.type, 'artref' ]
        (
          { $art.includes = [ $art.type ]; delete $art.type; }
          ( ',' { if (this.isStraightBefore('{')) break; } // allow ',' before '{' // }}
            includeRef[ $art ]
          )*
          typeStruct[ $art ] optionalSemi
        |
          { this.docComment( $annos ); }
          annotationAssignment_ll1[ $annos ]*
          requiredSemi
        )
      |
        typeStruct[ $art ] optionalSemi
      |
        qp=projectionSpec
        { $art.query = $qp.query; $art['$'+'syntax'] = 'projection'; }
        optionalSemi // TODO: not fully correct without columns or excluding

      )
    )
  ;

aspectDef[ outer, loc, annos ] locals[ art, name = {} ]
@after { this.attachLocation($art); }
  :
    ( ASPECT | ( abs=ABSTRACT | HideAlternatives ) ent=ENTITY )
    simplePath[ $name, 'Type' ]
    { $art = this.addDef( $outer, 'artifacts', 'aspect', $name, $annos, {}, $loc );
      // backends do not like ['$'+'syntax']: ($ent ? 'entity' : 'aspect')
      if ($ent)
        this.warning( 'syntax-deprecated-abstract', this.tokenLocation( $abs, $ent ), {},
                      'Abstract entity definitions are deprecated; use aspect definitions instead' );
      this.docComment( $annos ); }
    annotationAssignment_fix[ $annos ]*
    ( ':'
      (
        includeRef[ $art ]
        ( ',' { if (this.isStraightBefore('{')) break; } // allow ',' before '{' // }}
          includeRef[ $art ]
        )*
      )?
    )?
    '{'
    { $art.elements = Object.create(null); } // better for include and annotate
    ( elementDef[ $art ]* )
    '}'
    // TODO: action definitions in a specific section?
    ( ACTIONS '{' actionFunctionDef[ $art ]* '}' )?
    optionalSemi
  ;

typeDef[ outer, loc, annos ] locals[ art, name = {} ]
@after { this.attachLocation($art); }
  :
    TYPE simplePath[ $name, 'Type' ]
    { $art = this.addDef( $outer, 'artifacts', 'type', $name, $annos, {}, $loc );
      this.docComment( $annos ); }
    annotationAssignment_fix[ $annos ]*
    typeSpecSemi[ $art, $annos ]
  ;

extendType[ outer, loc, annos ] locals[ art, name = {} ]
@after { this.attachLocation($art); }
  :
    // aspects are types, i.e. kind is 'type' for aspects
    TYPE simplePath[ $name, 'Extend' ]
    { $art = this.addItem( $outer, 'extensions', 'extend', $annos,
                           { name: $name, expectedKind: 'type' },
                           $loc );  }
    extendWithOptElements[ $art, $annos ]
  ;

extendAspect[ outer, loc, annos ] locals[ art, name = {} ]
@after { this.attachLocation($art); }
  :
    // aspects are types, i.e. kind is 'type' for aspects
    ASPECT simplePath[ $name, 'Extend' ]
    { $art = this.addItem( $outer, 'extensions', 'extend', $annos,
                           { name: $name, expectedKind: 'aspect' },
                           $loc );  }
    extendWithOptElements[ $art, $annos ]
  ;

annotationDef[ outer, loc, annos ] locals[ art, name = {} ]
@after { this.attachLocation($art); }
  :
    annotation=ANNOTATION simplePath[ $name, 'AnnoDef' ]
    { if ($outer.kind !== 'source') { // this is a syntax restriction to avoid confusion
        this.error( 'syntax-no-inner-vocabulary', $annotation, {},
                    'Annotation definitions can\'t be defined inside contexts or services' );
        $art = {}; }
      else
        $art = this.addDef( $outer, 'vocabularies', 'annotation', $name, $annos, {}, $loc );
      this.docComment( $annos ); }
    annotationAssignment_fix[ $annos ]*
    typeSpecSemi[ $art, $annos ] // also 'includes'...
  ;

extendArtifact[ outer, loc, annos ] locals[ art, name = {} ]
@after { this.attachLocation($art); }
  :
    simplePath[ $name, 'Extend' ]
    { $art = this.addItem( $outer, 'extensions', 'extend', $annos,
                           { name: $name }, $loc );  }
    extendWithOptElements[ $art, $annos ]
  ;

extendWithOptElements[ art, annos ]
  :
    WITH { this.noSemicolonHere(); this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    (
      includeRef[ $art ]
      requiredSemi
    |
      '{'
      elementDefOrExtend[ $art ]*
      '}'
      optionalSemi
    |
      requiredSemi
    )
  |
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    (
      '{'
      elementDefOrExtend[ $art ]*
      '}'
      optionalSemi
    |
      requiredSemi
    )
  ;

annotateArtifact[ outer, loc, annos ] locals[ art, name = {} ]
@after { this.attachLocation($art); }
  :
    simplePath[ $name, 'Annotate' ]
    { $art = this.addItem( $outer, 'extensions', 'annotate', $annos, { name: $name }, $loc ); }
    ( WITH { this.noSemicolonHere(); } )?
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    (
      '{'
      annotateElement[ $art ]*
      '}'
      (
        ACTIONS
        '{'
        annotateAction[ $art ]*
        '}'
      )?
      optionalSemi
    |
      ACTIONS
      '{'
      annotateAction[ $art ]*
      '}'
      optionalSemi
    |
      '('
      annotateParam[ $art ]
      ( ',' { if (this.isStraightBefore(')')) break; } // allow ',' before ')'
        annotateParam[ $art ]
      )*
      ')'
      (
        RETURNS '{' { $art['$'+'syntax'] = 'returns'; }
        annotateElement[ $art ]*
        '}'
        optionalSemi
      |
        requiredSemi
      )
    |
      RETURNS '{' { $art['$'+'syntax'] = 'returns'; }
      annotateElement[ $art ]*
      '}'
      optionalSemi

    |
      requiredSemi
    )
  ;

annotateElement[ outer ] locals[ art, annos = [] ]
@after{ this.attachLocation($art); }
  :
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    name=ident['Element']
    { $art = this.addDef( $outer, 'elements', 'annotate', $name.id, $annos );
      this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    (
      '{'
      annotateElement[ $art ]*
      '}'
      optionalSemi
    |
      requiredSemi
    )
  ;

annotateAction [ outer ] locals [ art, annos = [] ]
@after{ this.attachLocation($art); }
  :
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    name=ident['BoundAction']
    { $art = this.addDef( $outer, 'actions', 'annotate', $name.id, $annos );
      this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    (
      '('
      annotateParam[ $art ]
      ( ',' { if (this.isStraightBefore(')')) break; } // allow ',' before ')'
        annotateParam[ $art ]
      )*
      ')'
    )?
    (
      RETURNS '{'
      annotateElement[ $art ]*
      '}'
      optionalSemi
    |
      requiredSemi
    )
  ;

annotateParam [ outer ] locals [ art, annos = [] ]
@after{ this.attachLocation($art); }
  :
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    param=ident['Param']
    { $art = this.addDef( $outer, 'params', 'annotate', $param.id, $annos );
      this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
  ;

// Element definition and its helpers ----------------------------------------

enumSymbolDef[ outer ] locals[ art, annos = [] ]
@after { this.attachLocation($art); }
  :
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    name=ident['Enum']
    { $art = this.addDef( $outer, 'enum', 'enum', $name.id, $annos );
      this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    ( '='
      { this.excludeExpected( ['Boolean', 'QuotedLiteral', "'#'", 'NULL'] ); }
      (
        val=literalValue
        { $art.value = $val.val; }
      |
        ( plus='+' | min='-' ) num=Number
        { $art.value = this.numberLiteral( $num, $plus||$min ); }
      )
      { this.docComment( $annos ); }
      annotationAssignment_ll1[ $annos ]*
    )?
    requiredSemi
  ;

elementDefOrExtend[ outer ] locals[ annos = [] ]
@after { /* #ATN 1 */ if ($ctx.art) this.attachLocation($art.art); }
// tool complains if I test for ($art)
  :
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    // #ATN: element name for definition can be EXTEND
    (
      EXTEND
      extendElement[ $outer, this.startLocation(), $annos ]
    |
      art = elementDefInner[ $outer, this.startLocation(), $annos, true ]
    )
  ;

elementDef[ outer ] locals[ annos = [] ]
@after { this.attachLocation($art.art); }
  :
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    art = elementDefInner[ $outer, this.startLocation(), $annos, false ]
  ;

// Actually, this is a subset if elementDefInner...
// TODO: the corresponding restrictions must also be checked in the core
// compiler, as the mixin element could come via CSN
mixinElementDef[ outer ] locals[ art ]
@after { /* #ATN 2 */ this.attachLocation($art); }
  :
    name=ident['Mixin']
    { $art = this.addDef( $outer, 'mixin', 'mixin', $name.id ); }
    (
      ':'
      // #ATN: referenced type name can be ASSOCIATION or COMPOSITION
      (
        typeAssociationBase[ $art, false ]
        // #ATN: path could start with MANY or ONE - make sure a token follows in same rule!
        ( typeToMany[ $art ] | typeToOne[ $art ] | simplePath[ $art.target, 'artref' ] )
        typeAssociationCont[ $art ]?
      |
        typeRefOptArgs[ $art ]
        ( as='=' expression
          { this.notSupportedYet( 'Calculated fields are not supported yet', $as ); }
        )?
      )
    |
      as='=' expression
      { this.notSupportedYet( 'Calculated fields are not supported yet', $as ); }
    )
    requiredSemi
  ;

misplacedAnnotations[ annos, messageId ]
  :
    annotationAssignment_ll1[ $annos ]+
    { if ($messageId)           // issue specified in central registry
        this.message( messageId, this.tokenLocation( $ctx.start, this.getCurrentToken() ) );
    }
  ;

elementDefInner[ outer, loc, annos, allowEq ] returns[ art ]
@after{ /* #ATN 5 */ }
  :
    // TODO: it would be excellent to remove ELEMENT...
    // or have a special ident rule without the ELEMENT
    // Reason: it would be good for error recover to start a major block without LL1 ambiguity
    // VIRTUAL is keyword, except if before the following tokens texts:
    { this.setLocalToken( 'VIRTUAL', 'VIRTUAL', /^[:{@=}]$/ ); }
    virtual=VIRTUAL? key=KEY?
    // #ATN: element name can be MASKED or ELEMENT (2x)
    masked=MASKED?    // TODO: order?
    ELEMENT?
    name=ident['Element']
    { $art = this.addDef( $outer, 'elements', 'element', $name.id, $annos,
                          { virtual: $virtual, key: $key, masked: $masked },
                          $loc );
      this.docComment( $annos ); }
    annotationAssignment_fix[ $annos ]*
    // TODO: we can think of making the typeSpec optional and do checks instead:
    // type optional with '=', type required otherwise
    (
      typeStruct[ $art ]
      ( nullability[ $art ]
        requiredSemi
      | optionalSemi            // NOT and NULL are reserved...
      )
    |
      ':'
      // #ATN: referenced type name can be ASSOCIATION or ARRAY or TYPE or LOCALIZED
      (
        typeStruct[ $art ]
        nullability[ $art ]?
        misplacedAnnotations[ $annos, 'syntax-anno-after-struct' ]?
        requiredSemi
      |
        typeAssociationBase[ $art, true ]
        // #ATN: path could start with MANY or ONE - make sure a token follows in same rule!
        (
          typeStruct[ $art.target, true ] optionalSemi
        |
          one=ONE
          { this.setMaxCardinality( $art, $one, this.numberLiteral( $one, null, '1' ) ); }
          typeCompoStruct[ $art.target ] optionalSemi
        |
          many=MANY
          { this.setMaxCardinality( $art, $many, { literal: 'string', val: '*' } ); }
          typeCompoStruct[ $art.target ] optionalSemi
        |
          // we do not support `Composition of many { e }` - ambiguity ad-hoc target versus foreign keys!
          typeToMany[ $art ] typeAssociationElementCont[ $art, $annos ]
        |
          typeToOne[ $art ] typeAssociationElementCont[ $art, $annos ]
        |
          simplePath[ $art.target, 'artref' ] typeAssociationElementCont[ $art, $annos ]
        )
      |
        (
          array=ARRAY of=OF 
          { $art.items = { location: this.tokenLocation( $array, $of ) }; }
        | many=MANY
          { $art.items = { location: this.tokenLocation( $many ) };}
        )
        // #ATN: typeRefOptArgs can start with TYPE
        ( typeStruct[ $art.items ]
          nullability[ $art.items ]?
          misplacedAnnotations[ $annos, 'syntax-anno-after-struct' ]?
        | typeTypeOf[ $art.items ]
          nullability[ $art.items ]?
          { this.docComment( $annos ); }
          annotationAssignment_ll1[ $annos ]*
        | typeRefOptArgs[ $art.items ]
          nullability[ $art.items ]?
          { this.docComment( $annos ); }
          annotationAssignment_ll1[ $annos ]*
          (
              ENUM '{'
              { $art.items.enum = Object.create(null); }
              enumSymbolDef[ $art.items ]*
            '}'
            misplacedAnnotations[ $annos, 'syntax-anno-after-enum' ]?
          )?
        )
        requiredSemi                     // also req after struct/enum
      |
        typeTypeOf[ $art ] elementProperties[ $art ]?
        { this.docComment( $annos ); }
        annotationAssignment_ll1[ $annos ]*
        requiredSemi                     // also req after foreign key spec
      |
        l=LOCALIZED { $art.localized = this.tokenLocation( $l, undefined, true ); }
        typeRefOptArgs[ $art ]
        { this.docComment( $annos ); }
        annotationAssignment_ll1[ $annos ]*
        ( elementProperties[ $art ]
          { this.docComment( $annos ); }
          annotationAssignment_ll1[ $annos ]*
        )?
        requiredSemi
      |
        typeRefOptArgs[ $art ]
        { this.docComment( $annos ); }
        annotationAssignment_ll1[ $annos ]*
        ( ENUM '{'
            { $art.enum = Object.create(null); }
            enumSymbolDef[ $art ]*
          '}'
          elementProperties[ $art ]?
          misplacedAnnotations[ $annos, 'syntax-anno-after-enum' ]?
        | elementProperties[ $art ]
          { this.docComment( $annos ); }
          annotationAssignment_ll1[ $annos ]*
        )?
        requiredSemi                     // also req after enum spec
      )
    |
      // this is also called for enum symbols (in EXTEND)
      eq='=' e=expression       // never introduce AS as syntax variant of '='
      {
        if (!$allowEq || $e.expr && !$e.expr.literal )
          this.notSupportedYet( 'Calculated fields are not supported yet', $eq );
        else if ($e.expr)
          $art.value = $e.expr;
      }
      { this.docComment( $annos ); }
      annotationAssignment_ll1[ $annos ]* // for enum symbol def via EXTEND
      requiredSemi
    )
  ;

extendElement[ outer, loc, annos ] locals[ art ]
@after{ /* #ATN 1 */ this.attachLocation($art); }
  :
    // #ATN: element name can be ELEMENT
    expected=ELEMENT? name=ident['Element']
    { $art = this.addDef( $outer, 'elements', 'extend', $name.id, $annos,
                          { expectedKind: $expected && 'element' },
                          $loc ); }
    extendWithOptElements[ $art, $annos ]
  ;

bracedSelectItemListDef[ query ]
  :
    '{'
    { if (!$query.columns) $query.columns = []; }  // set it early to avoid "wildcard" errors
    (
      selectItemDef[ $query.columns ]
      ( ',' { if (this.isStraightBefore("}")) break; } // allow ',' before '}'
        selectItemDef[ $query.columns ]
      )*
    )?
    '}'
  ;

selectItemDef[ outer ] locals[ annos = [] ]
@after{ if ($ctx.art) this.attachLocation($art.art); }
  :
    star='*'
    { $outer.push( this.tokenLocation( $star, undefined, '*' ) ); }
  |
    { this.docComment( $annos ); }
    annotationAssignment_atn[ $annos ]*
    // VIRTUAL is keyword, except if before the following tokens texts:
    { this.setLocalToken( 'VIRTUAL', 'VIRTUAL', /^([,.:\[@]|as)$/i ) ; } // not '{'
    virtual=VIRTUAL?
    key=KEY?
    art=selectItemDefBody[ $outer, $annos ]
    {
      if ($virtual) $art.art.virtual = this.tokenLocation( $virtual, undefined, true );
      if ($key) $art.art.key = this.tokenLocation( $key, undefined, true );
    }
  ;

selectItemDefBody[ outer, annos ] returns[ art = {} ]
@after{ /* #ATN 1 */ this.attachLocation($art); }
  :
    (
      e=expression
      // we cannot use 'condition' instead, as long as we allow aliases without
      // AS (using rule 'ident' instead of 'identNoKeyword') -> ambiguities
      {
        $art = this.addItem( $outer, null, null, $annos, { value: $e.expr } );
      }
      ( AS n1=ident['Item'] { $art.name = $n1.id }
      | n2=ident['Item'] { $art.name = this.fragileAlias( $n2.id, true ); }
      | { if (this.getCurrentToken().text !== '.') this.classifyImplicitName( 'Item', $e.expr ); }
      )
      { if ($art.value && !$art.value.path) this.excludeExpected( ["'.'", "'{'"] ); }
      (
        { if ($art.value && !$art.value.path) this.reportExpandInline( 'expand' ); }
        selectItemInlineList[ $art, 'expand' ]
        excludingClause[ $art ]?
        // TODO: we might alternatively allow AS here
      |
        // TODO: complain if AS has been used - or in definer?
        { if ($art.value && !$art.value.path) this.reportExpandInline( 'inline' ); }
        DOTbeforeBRACE          // ...orASTERISK
        (
          selectItemInlineList[ $art, 'inline' ]
          excludingClause[ $art ]?
        |
          star='*'
          { $art.inline = [ this.tokenLocation( $star, undefined, '*' ) ]; }
        )
      )?
    |
      { $art = this.addItem( $outer, null, null, $annos ); }
      selectItemInlineList[ $art, 'expand' ]
      excludingClause[ $art ]?
      AS n1=ident['Item'] { $art.name = $n1.id }
    )
    { this.docComment( $annos ); }
    annotationAssignment_fix[ $annos ]*
    ( ':'
      // #ATN: typeRefOptArgs can start with TYPE, REDIRECTED
      ( re=REDIRECTED to=TO
        { $art.target = {}; }
        simplePath[ $art.target, 'artref' ]
        (
          typeAssociationCont[ $art ]
        |
          { this.docComment( $annos ); }
          annotationAssignment_ll1[ $annos ]*
        )
      | typeTypeOf[ $art ]
        { this.docComment( $annos ); }
        annotationAssignment_ll1[ $annos ]*
      | typeRefOptArgs[ $art ]         // TODO: annos here?
        { this.docComment( $annos ); }
        annotationAssignment_ll1[ $annos ]*
      )
    )?
  ;

selectItemInlineList[ art, clause ]
  :
    '{'
    { $art[$clause] = []; }
    (
      selectItemInlineDef[ $art[$clause] ]
      ( ',' { if (this.isStraightBefore("}")) break; } // allow ',' before '}'
        selectItemInlineDef[ $art[$clause] ]
      )*
    )?
    '}'
  ;

selectItemInlineDef[ outer ] locals[ annos = [] ]
@after{ if ($ctx.art) this.attachLocation($art.art); }
  :
    star='*'
    { $outer.push( this.tokenLocation( $star, undefined, '*' ) ); }
  |
    { this.docComment( $annos ); }
    annotationAssignment_atn[ $annos ]*
    art=selectItemDefBody[ $outer, $annos ]
  ;

parameterListDef[ art ]
  :
    '('
    // also empty param list (we might do some hacking later to allow reserved words)
    // see annotationAssignment_paren
    (
      parameterDef[ $art ]
      ( ',' { if (this.isStraightBefore(')')) break; } // allow ',' before ')'
        parameterDef[ $art ]
      )*
    )?
    ')'
  ;

parameterDef[ outer ] locals[ art, annos = [] ]
@after { this.attachLocation($art); }
  :
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    name=ident['Param']
    { $art = this.addDef( $outer, 'params', 'param', $name.id, $annos );
      this.docComment( $annos ); }
    annotationAssignment_fix[ $annos ]*
    typeSpec[ $art ]
    ( DEFAULT expr=expression { $art.default = $expr.expr; } )?
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
  ;

entityParameters[ art ]
  :
    '('
    // also empty param list (we might do some hacking later to allow reserved words)
    // see annotationAssignment_paren
    (
      entityParameterDef[ $art ]
      ( ',' { if (this.isStraightBefore(')')) break; } // allow ',' before ')'
        entityParameterDef[ $art ]
      )*
    )?
    ')'
  ;

entityParameterDef[ outer ] locals[ art, annos = [] ]
@after { this.attachLocation($art); }
  :
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    name=ident['Param']
    { $art = this.addDef( $outer, 'params', 'param', $name.id, $annos );
      this.docComment( $annos ); }
    annotationAssignment_fix[ $annos ]*
    typeSpec[ $art ]
    ( DEFAULT expr=expression { $art.default = $expr.expr; } )?
  ;

nullability[ art ]
  :
    not=NOT n1=NULL
    { $art.notNull = this.tokenLocation($not,$n1,true); }
  |
    n2=NULL
    { $art.notNull = this.tokenLocation($n2,undefined,false); }
  ;

elementProperties[ elem ]
  :
    nullability[$elem]
    (
      DEFAULT expr=expression
      { $elem.default = $expr.expr; }
    )?
  |
    (
      DEFAULT expr=expression
      { $elem.default = $expr.expr; }
    )
    nullability[$elem]?
  |
    eq='='
    { this.notSupportedYet( 'Calculated fields are not supported yet', $eq ); }
    expression
  ;

// View definitions ----------------------------------------------------------

viewDef[ outer, loc, annos ] locals[ art, name = {} ]
@after { this.attachLocation($art); }
  :
    v=VIEW simplePath[ $name, 'Entity' ]
    { $art = this.addDef( $outer, 'artifacts', 'entity', $name, $annos, { ['$'+'syntax']: 'view' }, $loc );
      this.docComment( $annos ); }
    annotationAssignment_fix[ $annos ]*
    (
      entityParameters[ $art ]
    |
      ( HideAlternatives | WITH ) PARAMETERS
      entityParameterDef[ $art ]
      ( ',' entityParameterDef[ $art ] )* // no optional final ',' here
    )?
    AS qe=queryExpression { $art.query = $qe.query; } // beta-mode test now in definer
    // TODO check ANTLR: bad msg with 'view V as'<eof> but 'view V as FOO' is fine
    requiredSemi
  ;

// Type references -----------------------------------------------------------

includeRef[ art ] locals[ incl = {} ]
  :
    simplePath[ $incl, 'artref' ]
    { if ($art.includes) $art.includes.push($incl); else $art.includes = [$incl]; }
  ;

typeSpec[ art ]                 // for params
@after{ /* #ATN 1 */ }
  :
    typeStruct[ $art ]
  |
    ':'
    // #ATN: typeSimple can start with ARRAY or TYPE
    ( typeStruct[ $art ]
      nullability[ $art ]?
    | typeArray[ $art ] // nullability is set in typeArray
    | typeTypeOf[ $art ]
      nullability[ $art ]?
      // TODO: no LOCALIZED ?
    | typeRefOptArgs[ $art ]
      nullability[ $art ]?
      ( ENUM '{'
          { $art.enum = Object.create(null); }
          enumSymbolDef[ $art ]*
        '}'
      )?
    )
  ;

returnTypeSpec[ art, annos ]
@after{ /* #ATN 1 */ }
  :
    ret=RETURNS { $art.returns = { location: this.tokenLocation( $ret ), kind: 'param' }; }
    // #ATN: typeSimple can start with ARRAY or TYPE
    ( typeStruct[ $art.returns ] 
      nullability[ $art.returns ]?
    | typeArray[ $art.returns ] // nullability is set in typeArray
    | typeTypeOf[ $art.returns ]
      nullability[ $art.returns ]?
      // TODO: no LOCALIZED ?
    | typeRefOptArgs[ $art.returns ]
      nullability[ $art.returns ]?
      ( ENUM '{'
          { $art.returns.enum = Object.create(null); }
          enumSymbolDef[ $art.returns ]*
        '}'
      | misplacedAnnotations[ $annos, 'syntax-anno-after-params' ]
      )?
    )

    requiredSemi // currently for all - might change if we get rid of the misplaced annos
  ;


typeSpecSemi[ art, annos ]      // with 'includes', for type and annotation defs
@after{ /* #ATN 3 */ }
  :
    typeStruct[ $art ]
    optionalSemi
  |
    ':'
    // #ATN: typeRefOptArgs can start with ARRAY or MANY or ASSOCIATION or TYPE or LOCALIZED
    // Nevertheless, MANY '{' is handled by local token rewrite:
    { this.setLocalToken( 'MANY', 'HelperToken1', /^[^\{]/ ); }
    (
      typeStruct[ $art ]
      optionalSemi
    |
      typeAssociationBase[ $art, false ]
      // #ATN: path could start with MANY or ONE - make sure a token follows in same rule!
      ( typeToMany[ $art ] | typeToOne[ $art ] | simplePath[ $art.target, 'artref' ] )
      typeAssociationCont[ $art ]?
      requiredSemi                       // and if its the ';'...
    |
      many=HelperToken1         // rewritten MANY before '{'
      { $art.items = { location: this.tokenLocation( $many ) };}
      typeStruct[ $art.items ]
      nullability[ $art.items ]?
      optionalSemi
    |
      (
        array=ARRAY of=OF
        { $art.items = { location: this.tokenLocation( $array, $of ) }; }
      | many=MANY
        { $art.items = { location: this.tokenLocation( $many ) };}
      )
      // #ATN: typeRefOptArgs can start with TYPE
      ( typeStruct[ $art.items ] 
        nullability[ $art.items ]?
        optionalSemi
      | typeTypeOf[ $art.items ]
        nullability[ $art.items ]?
        { this.docComment( $annos ); }
        annotationAssignment_ll1[ $annos ]*
        requiredSemi
      | typeRefOptArgs[ $art.items ]
        nullability[ $art.items ]?
        { this.docComment( $annos ); }
        annotationAssignment_ll1[ $annos ]*
        ( ENUM '{'
            { $art.items.enum = Object.create(null); }
            enumSymbolDef[ $art.items ]*
          '}'
          optionalSemi 
        | requiredSemi
        )
      )
    |
      typeTypeOf[ $art ]
      { this.docComment( $annos ); }
      annotationAssignment_ll1[ $annos ]* requiredSemi
    |
      l=LOCALIZED { $art.localized = this.tokenLocation( $l, undefined, true ); }
      typeRefOptArgs[ $art ]
      { this.docComment( $annos ); }
      annotationAssignment_ll1[ $annos ]*
      requiredSemi
    |
      // alt lookahead includes MANY '{'
      { $art.type = {}; }
      simplePath[ $art.type, 'artref' ]
      (
        '(' // with type args, e.g. `type T : String(100) enum { ... }`
        head=Number
        { $art['$'+'typeArgs'] = [ this.numberLiteral( $head ) ]; }
        ( ',' { if (this.isStraightBefore(')')) break; } // allow ',' before ')'
          (
            v=VARIABLE
            { $art['$'+'typeArgs'].push(
              { literal: 'string', val: 'variable', location: this.tokenLocation($v) } );
            }
          |
            f=FLOATING
            { $art['$'+'typeArgs'].push(
              { literal: 'string', val: 'floating', location: this.tokenLocation($f) } );
            }
          |
            tail=Number
            { $art['$'+'typeArgs'].push( this.numberLiteral( $tail ) ); }
          )
        )*
        ')'
        { this.docComment( $annos ); }
        annotationAssignment_ll1[ $annos ]*
        ( ENUM '{'
            { $art.enum = Object.create(null); }
            enumSymbolDef[ $art ]*
          '}'
          optionalSemi
        | requiredSemi
        )
      |
        ':' // with element, e.g. `type T : E:elem enum { ... }`
        { $art.type.scope = $art.type.path.length; }
        simplePath[ $art.type, 'ref']
        { this.docComment( $annos ); }
        annotationAssignment_ll1[ $annos ]*
        ( ENUM '{'
            { $art.enum = Object.create(null); }
            enumSymbolDef[ $art ]*
          '}'
          optionalSemi
        | requiredSemi
        )
      |
        { this.docComment( $annos ); }
        annotationAssignment_ll1[ $annos ]*
        ( ENUM '{'
            { $art.enum = Object.create(null); }
            enumSymbolDef[ $art ]*
          '}'
          optionalSemi
        | requiredSemi
        )
      |
        // TODO: complain if used in anno def?
        { $art.includes = [ $art.type ]; delete $art.type; }
        ( ',' { if (this.isStraightBefore('{')) break; } // allow ',' before '{' // }}
          includeRef[ $art ]
        )*
        typeStruct[ $art ]
        optionalSemi
      )
    )
  ;

typeStruct[ art, attachLoc = false ]
@after { if ($attachLoc) this.attachLocation($art); }
  :
    { $art.elements = Object.create(null); } // we allow empty structures
    '{' elementDef[ $art ]* '}'
  ;

typeCompoStruct[ art ]
@after { this.attachLocation($art); }
  :
    { $art.elements = Object.create(null); } // we allow empty structures
    COMPOSITIONofBRACE elementDef[ $art ]* '}'
  ;

typeArray[ art ]
@after { /* #ATN 1 */ }
  :
    (
      array=ARRAY of=OF
      { $art.items = { location: this.tokenLocation( $array, $of ) }; }
    | many=MANY
      { $art.items = { location: this.tokenLocation( $many ) };}
    )
    // #ATN: typeRefOptArgs can start with TYPE
    ( typeStruct[ $art.items ]
      nullability[ $art.items ]?
    | typeTypeOf[ $art.items ]
      nullability[ $art.items ]?
    | typeRefOptArgs[ $art.items ]
      nullability[ $art.items ]?
      ( ENUM '{'
          { $art.items.enum = Object.create(null); }
          enumSymbolDef[ $art.items ]*
        '}'
      )?
    )
  ;

typeAssociationBase[ art, handleTypeCompo ] // including Composition
  :
    (
      assoc=ASSOCIATION cardinality[$art]? TO
      {{
        let location = this.tokenLocation($assoc);
        $art.type = { path: [{ id: 'cds.Association', location }], scope: 'global', location };
        this.handleComposition( $art.cardinality, false );
      }}
    |
      compo=COMPOSITION cardinality[$art]? OF
      {{
        let location = this.tokenLocation($compo);
        $art.type = { path: [{ id: 'cds.Composition', location }], scope: 'global', location };
        this.handleComposition( $art.cardinality, handleTypeCompo );
      }}
    )
    { $art.target = {}; }
  ;

typeAssociationCont[ art ]
  :
    (
      '{'
      { this.addDef( $art, 'foreignKeys' ); }
      (
        foreignKey[ $art ]
        ( ',' { if (this.isStraightBefore("}")) break; } // allow ',' before '}'
          foreignKey[ $art ]
        )*
      )?
      '}'
    |
      ON cond=condition
      { $art.on=$cond.cond; }
    )
  ;

typeAssociationElementCont[ art, annos ] // including Composition
// optional NULL / NOT NULL for managed association only
  :
    (
      '{'
      { this.addDef( $art, 'foreignKeys' ); }
      (
        foreignKey[ $art ]
        ( ',' { if (this.isStraightBefore("}")) break; } // allow ',' before '}'
          foreignKey[ $art ]
        )*
      )?
      '}'
      nullability[ $art ]?
    |
      ON cond=condition
      { $art.on=$cond.cond; }
    |
      nullability[ $art ]
    )?
    { this.docComment( $annos ); }
    annotationAssignment_ll1[ $annos ]*
    requiredSemi                     // also req after foreign key spec
  ;

typeToOne[ art ]
  :
    one=ONE
    { this.setMaxCardinality( $art, $one, this.numberLiteral( $one, null, '1' ) ); }
    simplePath[ $art.target, 'artref' ]
  ;

typeToMany[ art ]
  :
    many=MANY
    { this.setMaxCardinality( $art, $many, { literal: 'string', val: '*' } ); }
    simplePath[ $art.target, 'artref' ]
  ;

cardinality[ art ] locals[ card = {} ]
@after { /* #ATN 2 */ $art.cardinality = this.attachLocation($card); }
  :
    lbrack='['
    { $card.targetMax = { literal: 'string', val: '*',
                          location: this.tokenLocation($lbrack) }; }
    (
      // #ATN: simple lookahead behind Number
      (
        srcMax=Number  ','
        { $card.sourceMax = this.numberLiteral( $srcMax ); }
      |
        srcMaxStar='*' ','
        { $card.sourceMax = { literal: 'string', val: '*',
                              location: this.tokenLocation($srcMaxStar) }; }
      )?
      // #ATN: simple lookahead behind Number
      (
        trgMin=Number '..'
        { $card.targetMin = this.numberLiteral( $trgMin ); }
      )?
      (
        trgMax=Number
        { $card.targetMax = this.numberLiteral( $trgMax ); }
      |
        trgMaxStar='*'
        { $card.targetMax = { literal: 'string', val: '*',
                              location: this.tokenLocation($trgMaxStar) }; }
      )
    )?
    ']'
  ;

foreignKey[ outer ] locals[ art = {}, elem = {} ]
@after { this.attachLocation($art); }
  :
    simplePath[ $elem, 'ref' ]
    ( AS name=ident['Key'] )?
    { $art = this.addDef( $outer, 'foreignKeys', 'key', ($ctx.name) ? $name.id : $elem.path,
                          undefined, { targetElement: $elem } ); }
  ;

typeTypeOf[ art ] locals[ _sync = 'nop' ]
@after { this.attachLocation($art.type); }
  :
    TYPE OF
    { $art.type = { scope: 'typeOf' }; }
    simplePath[ $art.type, 'ref' ]
    ( ':'
      // If we have too much time, we could set the category of the simple path
      // before to 'artref'
      { $art.type.scope = $art.type.path.length; }
      simplePath[ $art.type, 'ref']
    )?
  ;

typeRefOptArgs[ art ]
@init { $art.type = {}; }
  :
    simplePath[ $art.type, 'artref' ]
    (
      '('
      head=Number
      { $art['$'+'typeArgs'] = [ this.numberLiteral( $head ) ]; }
      ( ',' { if (this.isStraightBefore(')')) break; } // allow ',' before ')'
        (
          v=VARIABLE
          { $art['$'+'typeArgs'].push(
            { literal: 'string', val: 'variable', location: this.tokenLocation($v) } );
          }
        |
          f=FLOATING
          { $art['$'+'typeArgs'].push(
            { literal: 'string', val: 'floating', location: this.tokenLocation($f) } );
          }
        |
          tail=Number
          { $art['$'+'typeArgs'].push( this.numberLiteral( $tail ) ); }
        )
      )*
      ')'
    |
      ':'
      { $art.type.scope = $art.type.path.length; }
      simplePath[ $art.type, 'ref']
    )?
  ;

// Queries -------------------------------------------------------------------

queryExpression returns[ query ] // QLSubqueryComplex, SubqueryComplex
@after{ this.attachLocation($query); }
  :
    qt1=queryTerm { $query = $qt1.query; }
    (
      ( op=UNION  q=( DISTINCT | ALL )?
      | op=EXCEPT q=DISTINCT?
      | op=MINUS  q=DISTINCT?
      )
      qt=queryTerm
      { $query = this.leftAssocBinaryOp( $query, $op, $q, $qt.query );; $ctx.q = null; }
    )*
    ( ob=orderByClause[ $query ] { $query = $ob.query; } ) ?
    ( lc=limitClause[ $query ]   { $query = $lc.query; } ) ?
  ;

orderByClause[ inQuery ] returns [ query ]
  :
    ORDER BY { $query = this.unaryOpForParens( $inQuery, '$'+'query' ); }
    ob1=orderBySpec { $query.orderBy = [ $ob1.ob ]; }
    ( ',' obn=orderBySpec  { $query.orderBy.push( $obn.ob ); } )*
  ;

overOrderByClause returns [ expr ]
  :
    o=ORDER b=BY { $expr = { op: this.tokenLocation($o, $b, 'orderBy' ) , args: [] }}
    ob1=orderBySpec { $expr.args.push( $ob1.ob ); }
    ( ',' obn=orderBySpec { $expr.args.push( $obn.ob ); } )*
  ;

partitionByClause returns [ expr ]
  :
    p=PARTITION b=BY { $expr = { op: this.tokenLocation($p, $b, 'partitionBy' ) , args: [] }}
    e1=expression { $expr.args.push( $e1.expr ); }
    ( ',' en=expression { $expr.args.push( $en.expr ); } )*
  ;

windowFrameClause returns [ wf ]
  :
    r=ROWS { $wf = { op: this.tokenLocation($r, null, 'rows' ) , args: [] }}
    wfe=windowFrameExtentSpec { $wf.args.push( $wfe.wfe ); }
  ;

windowFrameExtentSpec returns[ wfe ]
  :
    { $wfe = {} }
    windowFrameStartSpec [ $wfe ]
  |
    b=BETWEEN
    { $wfe = { op: this.tokenLocation( $b, null, 'frameBetween' ), args: [] } }
    wfb1=windowFrameBoundSpec { $wfe.args.push( $wfb1.wfb ); }
    AND
    wfb2=windowFrameBoundSpec { $wfe.args.push( $wfb2.wfb ); }
  ;

windowFrameBoundSpec returns [ wfb ]
@after{ /* #ATN 1 */ }
  :
    // #ATN:  Not ll1 because `UNBOUNDED` could also be part of the windowFrameStartSpec
    //        `UNBOUNDED` would then be immediately followed by `PRECEDING`
    u=UNBOUNDED f=FOLLOWING
    { $wfb = { op: this.tokenLocation($u, $f, 'unboundedFollowing' ), args: []} }
  |
    // #ATN:  Not ll1 because `Number` could also be part of the windowFrameStartSpec
    //        `Number` would then be immediately followed by `PRECEDING`
    n=Number f=FOLLOWING
    { $wfb = { op: this.tokenLocation($n, $f, 'following' ), args: [ this.numberLiteral( $n ) ]} }
  |
    { $wfb = {} }
    windowFrameStartSpec [ $wfb ]
  ;

windowFrameStartSpec [ wf ]
  :
    u=UNBOUNDED p=PRECEDING
    {
      $wf.op = this.tokenLocation($u, $p, 'unboundedPreceding' );
      $wf.args = [];
    }
  |
    n=Number p=PRECEDING
    {
      $wf.op = this.tokenLocation($p, null, 'preceding' );
      $wf.args = [ this.numberLiteral( $n ) ];
    }
  | 
    c=CURRENT r=ROW
    {
      $wf.op = this.tokenLocation($c, $r, 'currentRow' );
      $wf.args = [];
    }
  ;

overClause returns [ over ]
@after { this.attachLocation($over); }
  :
    o=OVER { $over = { op: this.tokenLocation( $o, null, 'over' ) , args: []  } }
    '('
      ( pb=partitionByClause { $over.args.push( $pb.expr ); } )?
      ( ob=overOrderByClause { $over.args.push( $ob.expr ); } )?
      ( wf=windowFrameClause { $over.args.push( $wf.wf ); } )?
    ')'
  ;

limitClause[ inQuery ] returns [ query ]
  :
    limkw=LIMIT { $query = this.unaryOpForParens( $inQuery, '$'+'query' ); }
    ( lim=Number   { $query.limit = { rows: this.numberLiteral( $lim, '' ) }; }
    | limnull=NULL { $query.limit = { rows: {
        literal: 'null', val: null, location: this.tokenLocation($limnull) } }; }
    )
    ( OFFSET off=Number { $query.limit.offset = this.numberLiteral( $off ); } )? // unsigned integer
  ;

orderBySpec returns[ ob ]
  :
    e=expression { $ob = $e.expr; }
    ( asc=ASC   { $ob.sort = this.tokenLocation( $asc, undefined, 'asc' ); }
    | desc=DESC { $ob.sort = this.tokenLocation( $desc, undefined, 'desc' ); }
    )?
    ( nb=NULLS ne=( FIRST | LAST )
      { $ob.nulls = this.tokenLocation( $nb, $ne, $ne.text.toLowerCase() ); }
    )?
  ;

queryTerm returns[ query ]
@after{ this.attachLocation($query); }
  :
    qt1=queryPrimary { $query = $qt1.query; }
    (
      intersect=INTERSECT quantifier=DISTINCT?
      qt=queryPrimary
      { $query = this.leftAssocBinaryOp( $query, $intersect, $quantifier, $qt.query );
        $ctx.quantifier = null; }   // reset for loop
    )*
  ;

queryPrimary returns[ query = {} ]
@after { this.attachLocation($query); }
  :
    open='(' qe=queryExpression close=')'
    { $query = this.surroundByParens( $qe.query, $open, $close ); }
  |
    select=SELECT
    { $query = { op: this.tokenLocation( $select, undefined, 'SELECT' ), location: this.startLocation() }; }
    (
      FROM querySource[ $query ]
      (
        mixin=MIXIN '{'
        mixinElementDef[ $query ]*
        '}' INTO
      )?
      ( ad=( ALL | DISTINCT )     // TODO: or directly after SELECT ?
      { $query.quantifier = this.tokenLocation( $ad, undefined, $ad.text.toLowerCase() ); }
      )?
      bracedSelectItemListDef[ $query ]?
      excludingClause[ $query ]?
    |
      ( ad=( ALL | DISTINCT )     // TODO: or directly after SELECT ?
      { $query.quantifier = this.tokenLocation( $ad, undefined, $ad.text.toLowerCase() ); }
      )?
      { $query.columns = []; }  // set it early to avoid "wildcard" errors
      selectItemDef[ $query.columns ]
      ( ',' { if (this.isStraightBefore("}")) break; } // allow ',' before '}'
        selectItemDef[ $query.columns ]
      )*
      FROM querySource[ $query ]
    )
    ( WHERE cond=condition { $query.where = $cond.cond; } )?
    (
      GROUP BY
      e1=expression { $query.groupBy = [ $e1.expr ]; }
      ( ',' en=expression { $query.groupBy.push( $en.expr ); } )*
    )?
    ( HAVING having=condition { $query.having = $having.cond; } )?
  ;

querySource[ query ]
@after { this.attachLocation($query.from); }
  :
    t1=tableExpression { $query.from = $t1.table; }
    (
      { const location = this.tokenLocation( this.getCurrentToken() );
        $query.from = { op: { val: 'join', location },
                        join: { val: 'cross', location },
                        args: [$t1.table] }; }
      ( ',' tn=tableExpression { if ($tn.table) $query.from.args.push( $tn.table ); } )+
    )?
  ;

tableExpression returns[ table ] // TableOrJoin
@after { this.attachLocation($table); }
  :
    qt=tableTerm { $table = $qt.table; }
    (
      join=joinOp[ $table ] { $table = $join.table; }
      te=tableExpression
      { if (!$table) { $table = {}; } else if ($te.table) $table.args.push( $te.table ); }
      ON cond=condition { $table.on = $cond.cond; }
    |
      crj=CROSS jn=JOIN tt=tableTerm
      { $table = this.leftAssocBinaryOp( $table, $jn, $crj, $tt.table, 'join' ); }
    )*
  ;

joinOp[ left ] returns[ table ] locals [ join ]
  :
    ( op=JOIN { $join = 'inner'; }
    | t1=INNER c=joinCardinality? op=JOIN { $join = 'inner' }
    | t1=LEFT t2=OUTER? c=joinCardinality? op=JOIN { $join = 'left' }
    | t1=RIGHT t2=OUTER? c=joinCardinality? op=JOIN { $join = 'right' }
    | t1=FULL t2=OUTER? c=joinCardinality? op=JOIN { $join = 'full' }
    )
    { $table = { op: this.tokenLocation( $op, undefined, 'join' ),
                 join: this.tokenLocation( $t1 || $op, $t2, $join ),
                 args: ($left ? [$left] : []),
                 location: $left && $left.location };
      if ($ctx.c) $table.cardinality = $c.joinCard; }
  ;

joinCardinality returns [ joinCard ]
@init { $joinCard = {}; }
@after { this.attachLocation($joinCard); }
  :
    (
      srcExact=EXACT?
      srcMaxOne=ONE
      { if($srcExact)
          $joinCard.sourceMin = { literal: 'number', val: 1,
                                  location: this.tokenLocation($srcExact) };
        $joinCard.sourceMax = { literal: 'number', val: 1,
                                  location: this.tokenLocation($srcMaxOne) }; }
    |
      srcMaxMany=MANY
      { $joinCard.sourceMax = { literal: 'string', val: '*',
                                   location: this.tokenLocation($srcMaxMany) }; }
    )
    TO
    (
      tgtExact=EXACT? tgtMaxOne=ONE
      { if($tgtExact)
          $joinCard.targetMin = { literal: 'number', val: 1,
                                  location: this.tokenLocation($tgtExact) };
        $joinCard.targetMax = { literal: 'number', val: 1,
                              location: this.tokenLocation($tgtMaxOne) }; }
    |
      tgtMaxMany=MANY
      { $joinCard.targetMax = { literal: 'string', val: '*',
                                location: this.tokenLocation($tgtMaxMany) }; }
    )
  ;

tableTerm returns [ table ]
@after{ /* #ATN 1 */ this.attachLocation($table); }
  :
    { $table = { path: [], scope: 0 }; }
    fromPath[ $table, 'artref']
    ( ':'
      { $table.scope = $table.path.length; }
      fromPath[ $table, 'ref']
    )?
    ( AS n1=ident['FromAlias'] { $table.name = $n1.id }
    | n2=identNoKeyword['FromAlias'] { $table.name = this.fragileAlias( $n2.id ); }
      // if we would use rule `ident`, we would either had to make all JOIN
      // kinds reserved or introduce ATN
    )?
    // ANTLR errors are better if we use ( A | B )? instead of ( A | B | ):
    { if (!$table.name) this.classifyImplicitName( $table.scope ? 'FromAlias' : 'Without' ); }
  |
    open='('
    // #ATN: The following alternative is not LL1, because both can start with
    // left-paren, but queryExpression has SELECT after initial left-parens
    (
      qe=queryExpression close=')'
      { $table = this.surroundByParens( $qe.query, $open, $close, true ); }
      ( AS a1=ident['FromAlias'] { $table.name = $a1.id } // for defining table aliass
      | a2=identNoKeyword['FromAlias'] { $table.name = this.fragileAlias( $a2.id, true ); }
        // not using ident` to have a similar behavior to above
      )
    |
      te=tableExpression close=')'
      { $table = this.surroundByParens( $te.table, $open, $close ); }
    )
  ;

fromPath[ qp, idkind ]
@after{ this.attachLocation($qp); }
  :
    id=ident[$idkind] { this.pushIdent( $qp.path, $id.id ); }
    ( fromArguments[ $id.id ] cardinalityAndFilter[ $id.id ]?
    | cardinalityAndFilter[ $id.id ]
    )?
    (
      '.' id=ident[$idkind] { this.pushIdent( $qp.path, $id.id ); }
      ( fromArguments[ $id.id ] cardinalityAndFilter[ $id.id ]?
      | cardinalityAndFilter[ $id.id ]
      )?
    )*
  ;

// Conditions and expressions ------------------------------------------------

// With "separate" `condition` and `expression` rules, we have long LL
// ambiguities (not so with LALR used in Bison) with initial left parentheses:
//   ( ( ( a.b.c + d.e.f
//       )     // now we know: 3rd left-paren for expression
//       =     // now we know: 1st and 2nd left-paren for condition
//       3 ) ) )
//
// To avoid expensive parsing, we "combine" both rules, i.e. inside '('…')' of
// rule `expressionTerm`, we recursively refer to `condition`, not
// `expression`.  With that, the existence of relations/predicates in rule
// `conditionTerm` must be optional.  Correct conditions and expressions must
// be then ensured by code (either in actions of the grammar or in a check
// phase - to be discussed).
//
// ANTLR4s left-recursion feature cannot be used as we will have rule
// arguments.

condition returns [ cond ] locals [ args = [], orl = [] ]
@after{
  $cond = ($args.length === 1)
    ? this.attachLocation( $args[0] )
    : this.attachLocation({ op: $orl[0], args: $args });
}
  :
    c1=conditionAnd { $args.push($c1.cond); }
    ( or=OR c2=conditionAnd { $args.push($c2.cond); $orl.push(this.tokenLocation( $or, undefined, 'or' ))} )*
  ;

conditionAnd returns [ cond ] locals [ args = [], andl = [] ]
@after{
  $cond = ($args.length === 1)
    ? $args[0]
    : this.attachLocation({ op: $andl[0], args: $args });
}
  :
    c1=conditionTerm { $args.push($c1.cond); }
    ( and=AND c2=conditionTerm { $args.push($c2.cond); $andl.push(this.tokenLocation( $and, undefined, 'and' )) } )*
  ;

conditionTerm returns [ cond ]
@after{
  if ($cond) { this.attachLocation($cond); } else { $cond = $expr.expr; }
}
  :
    nt=NOT ct=conditionTerm
    { $cond = { op: this.tokenLocation( $nt, undefined, 'not' ), args: [ $ct.cond ] }; }
  |
    ex=EXISTS
    (
      open='(' qe=queryExpression close=')'
      { $cond = { op: this.tokenLocation( $ex, undefined, 'exists' ),
                  args: [ this.surroundByParens( $qe.query, $open, $close, true ) ] }; }
    |
      qm=( HideAlternatives | '?' )
      { $cond = { op: this.tokenLocation( $ex, undefined, 'exists' ), args: [
          { param: this.tokenLocation( $qm, undefined, '?' ), scope: 'param' }
        ] };
        this.csnParseOnly( 'Dynamic parameter "?" is not supported', $qm );
      }
    |
      ep=valuePath[ 'ref' ]
      { $ep.qp['$'+'expected'] = 'exists';
        $cond = { op: this.tokenLocation( $ex, undefined, 'exists' ), args: [ $ep.qp ] };
      }
    )
  |
    expr=expression             // see @after
    (
      rel=( '=' | '<>' | '>'  | '>=' | '<' | '<=' | '!=' )
      { $cond = { op: this.tokenLocation( $rel, undefined, $rel.text), args: [ $expr.expr ] }; }
      ( asa=( ANY | SOME | ALL )
        { $cond.quantifier = this.tokenLocation($asa, undefined, $asa.text.toLowerCase()); }
      )?
      e2=expression { $cond.args.push($e2.expr); }
    |
      IS ( inn=NOT NULL | innu=NULL )
      { $cond = { op: $inn ? this.tokenLocation( $inn, undefined, 'isNotNull' ) : this.tokenLocation( $innu, undefined, 'isNull' ), args: [ $expr.expr ] }; }
    |
      { $cond = { args: [ $expr.expr ] }; }
      NOT predicate[ $cond, true ]
    |
      { $cond = { args: [ $expr.expr ] }; }
      predicate[ $cond, false ]
    )?                          // optional: for conditions in parentheses
  ;

predicate[ cond, negated ]
// As an alternative, we could have a `negated` properties for the operations
// `isNull`(!), `in`, `between` and `like` (or produce the same AST as for
//    NOT (a BETWEEN b AND c)
  :
    ino=IN e1=expression        // including ExpressionList
    { $cond.op = this.tokenLocation( $ino, undefined, (negated) ? 'notIn' : 'in'); $cond.args.push( $e1.expr ); }
  |
    bw=BETWEEN e2=expression
    { $cond.op = this.tokenLocation( $bw, undefined, (negated) ? 'notBetween' : 'between' ); $cond.args.push( $e2.expr ); }
    AND e3=expression { $cond.args.push( $e3.expr ); }
  |
    lk=LIKE e4=expression
    { $cond.op = this.tokenLocation( $lk, undefined, (negated) ? 'notLike' : 'like' ); $cond.args.push( $e4.expr ); }
    ( ESCAPE e5=expression { $cond.args.push( $e5.expr ); } )?
  ;

expression returns [ expr ]
@after{ if ($expr) { this.attachLocation($expr); } else { $expr = this.attachLocation({});} }
  :
    e1=expressionSum { $expr = $e1.expr; }
    (
      or='||' e2=expressionSum
      {
        $expr = {
          op: this.tokenLocation( $or, undefined, '||' ), args: [$expr, $e2.expr],
          location: this.combinedLocation( $expr, $e2.expr ) };
      }
    )*
  ;

expressionSum returns [ expr ]
@after{ if ($expr) this.attachLocation($expr); }
  :
    e1=expressionFactor { $expr = $e1.expr; }
    (
      op=( '+' | '-' ) e2=expressionFactor
      {
        $expr = {
          op: this.tokenLocation($op, undefined, $op.text), args: [$expr, $e2.expr],
          location: this.combinedLocation( $expr, $e2.expr ) };
      }
    )*
  ;

expressionFactor returns [ expr ]
@after{ if ($expr) this.attachLocation($expr); }
  :
    e1=expressionTerm { $expr = $e1.expr; }
    (
      op=( '*' | '/' ) e2=expressionTerm
      {
        $expr = {
          op: this.tokenLocation($op, undefined, $op.text), args: [$expr, $e2.expr],
          location: this.combinedLocation( $expr, $e2.expr ) };
      }
    )*
  ;

expressionTerm returns [ expr ] locals [ op, args = [] ]
@after{ /* #ATN 1 */ this.attachLocation($expr); }
  :
    unary=( '+' | '-' ) e1=expressionTerm // prefix op or part of the number
    { $expr = this.signedExpression( $unary, $e1.expr ); }
  |
    (
      val=literalValue
      { $expr = $val.val; }
    |
      sf=specialFunction
      { $expr = $sf.ret; }
    |
      ca=CASE
      { $expr = { op : this.tokenLocation( $ca, undefined, 'case' ), args: [] }; }
      (
        e2=expression { $expr.args.push($e2.expr); }
        ( ow=WHEN ew=expression THEN e3=expression
          { $expr.args.push( this.createPrefixOp( $ow, [ $ew.expr, $e3.expr ] ) ); }
        )+
      |
        ( ow=WHEN c=condition THEN e3=expression
          { $expr.args.push( this.createPrefixOp( $ow, [ $c.cond, $e3.expr ] ) ); }
        )+
      )
      ( el=ELSE e4=expression
        { $expr.args.push( this.createPrefixOp( $el, [ $e4.expr ] ) ); }
      )?
      END
    |
      ne=NEW nqp=valuePath[ 'ref', null] // token rewrite for NEW
      // please note: there will be no compiler-supported code completion after NEW
      { $expr = { op: this.tokenLocation( $ne, undefined, 'new' ), args: [] };
        this.notSupportedYet( $ne ); }
    |
      vp=valuePath[ 'ref', null ] { $expr = this.valuePathAst( $vp.qp ); }
      { this.setLocalTokenIfBefore( 'OVER', 'OVER', /^\($/i ); }
      (
        over=overClause { $expr.suffix = [ $over.over ] }
      )?
    |
      ':'
      ( vp=valuePath[ 'paramref', this.startLocation() ]
        { $expr = $vp.qp;; $expr.scope = 'param'; }
      | pp=Number
        { $expr = { param: this.numberLiteral( $pp ), scope: 'param' };
          this.csnParseOnly( 'Positional parameter ":' + $pp.text + '" is not supported', $pp );
        }
      )
    |
      qm=( HideAlternatives | '?' )
      { $expr = { param: this.tokenLocation( $qm, undefined, '?' ), scope: 'param' };
        this.csnParseOnly( 'Dynamic parameter "?" is not supported', $qm );
      }
    |
      open='('
      // #ATN: The following alternative is not LL1, because both can start with
      // left-paren, but queryExpression has SELECT after initial left-parens
      (
        qe=queryExpression close=')'
        { $expr = this.surroundByParens( $qe.query, $open, $close, true ); }
      |
        c1=condition { $expr = [ $c1.cond ]; }
        ( ',' { if ($expr.length > 1 && this.isStraightBefore(')')) break; } // allow ',' before ')'
          cn=expression { if ($cn.expr) $expr.push($cn.expr); }
        )*
        close=')'
        {
          if ($expr.length > 1)
            $expr = { op: this.tokenLocation( $open, undefined, ',' ), args: $expr };
          else if ($expr[0]) // can be `null` if condition failed to parse
            $expr = this.surroundByParens( $expr[0], $open, $close );
        }
      )
    )
  ;

specialFunction returns [ ret = { } ] locals[ art = {} ]
@after{ /* #ATN 1 */ }
  :
    fun=TRIM open='('
    { $ret = this.functionAst( $fun, $open ); }
    // #ATN: we do not want to reserve these three optional keywords
    (
      t=( LEADING | TRAILING | BOTH ) { $ret.args[0].args.push( $t.text ); }
      ( e=expression { $ret.args[0].args.push( $e.expr ); } )?
      t=FROM e=expression { $ret.args[0].args.push( $t.text, $e.expr ); }
    |
      e=expression
      (
        { $ret.args[0].args.push( $e.expr ); }
        t=FROM e=expression
        { $ret.args[0].args.push( $t.text, $e.expr ); }
      |
        { $ret.args[0] = $e.expr; }
      )
    )
    ')'
  |
    fun=EXTRACT open='('
    { $ret = this.functionAst( $fun, $open ); }
    t=( YEAR | MONTH | DAY | HOUR | MINUTE | SECOND )
    f=FROM e=expression
    { $ret.args[0].args.push( $t.text, $f.text, $e.expr ); }
    ')'
  |
    ca=CAST open='('
    {
      $ret = {
        op: this.tokenLocation( $ca, undefined, 'cast' ),
        args: [ ],
        location: this.tokenLocation( $ca )
      };
    }
    e=expression AS typeRefOptArgs[ $ret ]
    {
      $ret.args.push( $e.expr );
    }
    ')'
    // TODO: ROUND - see also resolver.js
  ;

// query path includes aggregation:
// ( COUNT | MIN | MAX | SUM | AVG | STDDEV | VAR )
// '(' ( '*' | expression | ALL expression | DISTINCT expression_list ) ')'

valuePath[ category, location = null ] returns[ qp = { path: [] } ] locals[ _sync = 'nop' ]
@init { $qp.location = location || this.startLocation(); }
@after{ this.attachLocation($qp); }
  :
    id=ident[ $category ]
    { this.pushIdent( $qp.path, $id.id ); }
    ( pathArguments[ $id.id, $id.id ] cardinalityAndFilter[ $id.id ]?
    | cardinalityAndFilter[ $id.id ]
    )?
    (
      '.' id=ident['ref']       // yes 'ref', not $category
      { this.pushIdent( $qp.path, $id.id ); }
      ( pathArguments[ $id.id ] cardinalityAndFilter[ $id.id ]?
      | cardinalityAndFilter[ $id.id ]
      )?
    )*
  ;

fromArguments[ pathStep ]
  :
    paren='('
    namedExpression[ $pathStep ]
    ( ',' { if (this.isStraightBefore(')')) break; } // allow ',' before ')'
      namedExpression[ $pathStep ]
    )*
    ')'
  ;

pathArguments[ pathStep, considerSpecial ]
@after{ /* #ATN 1 */ }
  :
    paren='('
    { this.prepareGenericKeywords( $considerSpecial ); }
    // ATN, LL2: Identifier can start both named arguments and the positional.
    // Make sure that we do not introduce A:B paths in expressions!
    (
      namedExpression[ $pathStep ]
      ( ',' { if (this.isStraightBefore(')')) break; } // allow ',' before ')'
        namedExpression[ $pathStep ]
      )*
    |
      { $pathStep.args = Object.create(null); }  // TODO: XSN func path cleanup
      arrowedExpression[ $pathStep ]
      ( ',' { if (this.isStraightBefore(')')) break; } // allow ',' before ')'
        arrowedExpression[ $pathStep ]
      )*
    |
      { $pathStep.args = []; }
      funcExpression[ $pathStep, $considerSpecial ]
      ( ',' { if (this.isStraightBefore(')')) break; } // allow ',' before ')'
        funcExpression[ $pathStep, $considerSpecial ]
      )*
    |
      a=ALL { $pathStep.quantifier = this.tokenLocation( $a, undefined, 'all' ); }
      e1=expression { $pathStep.args = [ $e1.expr ]; }
    |
      d=DISTINCT { $pathStep.quantifier = this.tokenLocation( $d, undefined, 'distinct' ); }
      e1=expression { $pathStep.args = [ $e1.expr ]; }
      ( ',' e2=expression { $pathStep.args.push( $e2.expr ); } )*
    |
      star='*'
      { $pathStep.args = [ { location: this.tokenLocation($star), val: '*', literal: 'token' } ]; }
    |
      { $pathStep.args = []; }
    )
    ')'
  ;

namedExpression[ pathStep ]
  :
    name=ident['paramname'] ':' elem=expression
    { if ($pathStep && $name.id) {
        this.addDef( $pathStep, 'args', 0, $name.id, true,
                     ($ctx.elem) ? $elem.expr : { location: $name.id.location } );
        $pathStep['$'+'syntax'] = ':';
      }
    }
  ;

arrowedExpression[ pathStep ]
  :
    name=ident['paramname'] a='=>' elem=expression
    { if ($name.id) this.addDef( $pathStep, 'args', 0, $name.id, true,
                                 ($ctx.elem) ? $elem.expr : { location: $name.id.location } ); }
  ;

funcExpression[ pathStep, considerSpecial ]
@init { this.prepareGenericKeywords( $considerSpecial ); }
  :
    only=GenericArgFull
    { $pathStep.args.push( { location: this.tokenLocation($only), val: $only.text, literal: 'token' } ); }
  |
    expr=expression
    { $pathStep.args.push( $expr.expr ); }
  ;

cardinalityAndFilter[ pathStep ] locals [ _sync = 'nop' ]
  :
    '['
    optionalCardinality[ pathStep ]?
    //{ $LeaveLoop = false; }
    optionalWhereForFilter
    co=condition { if ($pathStep) $pathStep.where = $co.cond; }
    ']'
  ;

optionalCardinality[ pathStep ]
@after { if ($pathStep && $pathStep.cardinality) this.attachLocation($pathStep.cardinality); }
  :
    // Make sure to test second token to allow expressions starting with Number
    // without introducing WHERE - that would be @options{k=2}.  The code
    // completion just produces `:`after having inserted a Number - TODO.
    { if (this._input.LT(2).text !== ':') return $ctx; }
    ( trgMax=Number ':'
      { if ($pathStep) $pathStep.cardinality = { targetMax: this.numberLiteral( $trgMax ), location: this.startLocation() }; }
    )
  ;

optionalWhereForFilter
  :
    // For ANTLR, WHERE is required, but we allow the generated parser skipping
    // the call of match(WHERE) except for the future (optional) clauses GROUP,
    // ORDER, LIMIT.  This hack requires that sync() at each state in the
    // calling rule does not throw an error if the current token does not match
    // one of the expected ones.
    {
      var text = this.getCurrentToken().text.toUpperCase();
      if (!['WHERE','GROUP','ORDER','LIMIT'].includes( text )) return;
      // TODO: should we somehow add those keywords to $(EXPECTED)?
    }
    WHERE
  ;

// Simple paths and values ---------------------------------------------------

annoValueBase returns[ val ] locals [ hasEllipsis=0 ]
@after { this.attachLocation($val); }
  :
    { $val = { literal: 'struct', location: this.startLocation() }; }
    '{'
    { this.meltKeywordToIdentifier(); }
    namedValue[ $val ]
    (
      ',' {
        this.meltKeywordToIdentifier();
        if (this.isStraightBefore("}")) break; // allow ',' before ')'
      }
      namedValue[ $val ]
    )*
    '}'
  |
    { $val = { literal: 'array', location: this.startLocation(), val: [] }; }
    '['
    (
      (
          head=arrayValue  { $val.val.push( $head.val ); }
        |
          e='...'
          { 
            $val.val.push( { literal: 'token', val: '...', location: this.tokenLocation($e) } );
            $hasEllipsis++;
          }
      )
      (
        ',' { if (this.isStraightBefore(']')) break; } // allow ',' before ']'
        (
            tail=arrayValue { $val.val.push( $tail.val ); }
          |
            e='...'
            { 
              $val.val.push( { literal: 'token', val: '...', location: this.tokenLocation($e) } );
              if(++$hasEllipsis > 1)
                this.error( 'syntax-unexpected-ellipsis', $e, { code: '...' },
                            'Expected no more than one $(CODE)' );
            }
        )
      )*
    )?
    ']'
  |
    v1=literalValue { $val = $v1.val; }
  |
    ( plus='+' | min='-' ) num=Number
    { $val = this.numberLiteral( $num, $plus||$min ); }
  ;

annoValue returns[ val ]
  :
    base=annoValueBase { $val = $base.val }
  |
    { $val = {}; }              // TODO: think about expression value representation
    at='@'? annotationPath[ $val, 'ref', $at ]
    annotationPathVariant[ $val ]?
  ;

namedValue[ struct ] locals[ namedVal = { name: {} } ]
  :
    at='@'? annotationPath[ $namedVal.name, 'name', $at ]
    (
      '#' { this.meltKeywordToIdentifier(); }
      variant=ident['variant'] { $namedVal.name.variant = $variant.id; }
    )?
    (
      ':' { this.meltKeywordToIdentifier(true); } // allow path as anno value start with reserved
      elem=annoValue
    )?
    { this.addItem( $struct, '_struct', null, true, // TODO: re-check _struct
                    ($ctx.elem) ? Object.assign($namedVal, $elem.val) : $namedVal ); }
  ;

arrayValue returns[ val ]
@after { this.attachLocation($val); }
  :
    { $val = { literal: 'struct', location: this.startLocation() }; }
    '{'
    { this.meltKeywordToIdentifier(); }
    namedValueInArray[ $val ]
    ( ','
      {
        this.meltKeywordToIdentifier();
        if (this.isStraightBefore("}")) break; // allow ',' before '}'
      }
      namedValueInArray[ $val ]
    )*
    '}'
  |
    { $val = { literal: 'array', location: this.startLocation(), val: [] }; }
    '['
    ( head=arrayValue { $val.val.push( $head.val ); }
      ( ',' { if (this.isStraightBefore(']')) break; } // allow ',' before ']'
        tail=arrayValue { $val.val.push( $tail.val ); }
      )*
    )?
    ']'
  |
    v1=literalValue { $val = $v1.val; }
  |
    ( plus='+' | min='-' ) num=Number
    { $val = this.numberLiteral( $num, $plus||$min ); }
  |
    { $val = {}; }              // TODO: think about expression value representation
    at='@'? annotationPath[ $val, 'ref', $at ]
    (
      '#' { this.meltKeywordToIdentifier(); }
      variant=ident['variant'] { $val.variant = $variant.id; }
    )?
  ;

namedValueInArray[ struct ] locals[ name = {} ]
  :
    at='@'? annotationPath[ $name, 'name', $at ]
    ( ':' elem=arrayValue )?
    { this.addDef( $struct, 'struct', null, $name, true,
                   ($ctx.elem) ? $elem.val : { location: $name.location } ); }
  ;

literalValue returns[ val ] locals[ tok ]
@init{ $tok = this.getCurrentToken(); }
@after { this.attachLocation($val); }
  :
    '#' name=ident['enumref']
    { $val = { literal: 'enum', sym: $name.id } }
  |
    NULL
    { $val = { literal: 'null', val: null }; }
  |
    Boolean
    { $val = { literal: 'boolean', val: $tok.text.toLowerCase() != 'false' }; }
  |
    Number
    { $val = this.numberLiteral( $tok, '' ); } // allow float and large number
  |
    String
    { $val = this.quotedLiteral( $tok, 'string' ); }
  |
    QuotedLiteral               // x'12', date'...', time'...', timestamp'...'
    { $val = this.quotedLiteral( $tok ); }
  ;

simplePath[ art, category ] locals[ _sync = 'nop' ]
@after { this.attachLocation($art); }
// Due to error recovery, rule `ident` can return with value `null`.  Set the
// path as broken in this case.
  :
    head=ident[ $category ]
    { if (!$art.path) $art.path = []; this.pushIdent( $art.path, $head.id );
      if ($category === 'artref') $art.scope = 0;
    }
    (
      '.' tail=ident[ $category ] { this.pushIdent( $art.path, $tail.id ); }
    )*
  ;

annotationPath[ art, category, headat = null ] locals[ _sync = 'nop' ]
@after { this.attachLocation($art); }
// Due to error recovery, rule `ident` can return with value `null`.  Set the
// path as broken in this case.
  :
    head=ident[ $category ]
    { $art.path = []; this.pushIdent( $art.path, $head.id, $headat );
      if ($category === 'artref') $art.scope = 0;
    }
    (
      '.' at='@'? tail=ident[ $category ]
      { this.pushIdent( $art.path, $tail.id, $at );
        // Otherwise, $at may continue to be set after one `.@anno` segment.
        $ctx.at = null;
      }
    )*
  ;

annotationPathVariant[ art ]
@after { this.attachLocation($art); }
  :
    // TODO: warning for space before and after '#'
    '#' { this.meltKeywordToIdentifier(); }
    variant=ident['variant'] { $art.variant = $variant.id; }
  ;

// Identifier and non-reserved keywords --------------------------------------

identNoKeyword[ category ] returns[ id ]    // for aliases without AS
@after{ $id = this.identAst( $stop, $category ); }
  :
    Identifier
  ;

// The `ident` rule matches `Identifier` and all non-reserved keywords.  List
// all non-reserved keywords directly, do not use an indirection via a rule
// like `nonReservedKeywords`.
ident[ category ] returns[ id ]
@after{ $id = this.identAst( $stop, $category ); }
  :
    Identifier
  | ABSTRACT
  | ACTION
  | ACTIONS
  | AND
  | ANNOTATE
  | ANNOTATION
  | ARRAY
  | ASC
  | ASPECT
  | ASSOCIATION
  | BETWEEN
  | BOTH
  | COMPOSITION
  | CONTEXT
  | CROSS
  | CURRENT
  | DAY
  | DEFAULT
  | DEFINE
  | DESC
  | ELEMENT
  | ELSE
  | END
  | ENTITY
  | ENUM
  | ESCAPE
  | EVENT
  | EXACT
  | EXCEPT
  | EXCLUDING
  | EXTEND
  | FIRST
  | FLOATING
  | FOLLOWING
  | FULL
  | FUNCTION
  | GROUP
  | HAVING
  | HOUR
  | INNER
  | INTERSECT
  | INTO
  | IS
  | JOIN
  | LAST
  | LEADING
  | LEFT
  | LIKE
  | LIMIT
  | LOCALIZED
  | MANY
  | MASKED
  | MINUS
  | MINUTE
  | MIXIN
  | MONTH
  | NAMESPACE
  | NULLS
  | OFFSET
  | ONE
  | OR
  | ORDER
  | OUTER
  | PARAMETERS
  | PARTITION
  | PRECEDING
  | PROJECTION
  | REDIRECTED
  | RETURNS
  | RIGHT
  | ROW
  | ROWS
  | SECOND
  | SERVICE
  | THEN
  | TRAILING
  | UNION
  | TO
  | TYPE
  | USING
  | UNBOUNDED
  | VARIABLE
  | VIEW
  | YEAR
  ;

//----------------------------------------------------------------------------

WhiteSpace                      // like \s in JavaScript RegExp
  :                             // LineTerminator | [\t\f\v\u00A0\uFEFF] | Zs
    [\r\n\u2028\u2029 \t\f\u000B\u00A0\u1680\u180e\u2000-\u200A\u202F\u205F\u3000\uFEFF]+
    -> skip ;

DocComment : '/**' .*? '*/' -> channel(HIDDEN);

Comment : '/*' .*? '*/' -> channel(HIDDEN);

LineComment : '//' ~[\r\n\u2028\u2029]* -> channel(HIDDEN);

// Values --------------------------------------------------------------------

String
  :
    ( '\'' ~[\u0027\n\r\u2028\u2029]* '\'' )+ // \u0027 = '\''
  ;

QuotedLiteral
  :
    ( [xX] | [dD][aA][tT][eE] | [tT][iI][mM][eE] ( [sS][tT][aA][mM][pP] )? )
    ( '\'' ~[\u0027\n\r\u2028\u2029]* '\'' )+ // \u0027 = '\''
  ;

UnterminatedLiteral
  :
    ( [xX] | [dD][aA][tT][eE] | [tT][iI][mM][eE] ( [sS][tT][aA][mM][pP] )? )?
    '\'' ~[\u0027\n\r\u2028\u2029]* // \u0027 = '\''
  ;

UnterminatedDelimitedIdentifier
  :
    '"' ~[\u0022\n\r\u2028\u2029]* ( '""' ~[\u0022\n\r\u2028\u2029]* )* // \u0022 = '"'
  | '![' ~[\u005d\n\r\u2028\u2029]*  ( ']]' ~[\u005d\n\r\u2028\u2029]* )* // \u005d = ']'
// \u005d = ']'
  ;

Boolean                         // TMP?
  : [tT][rR][uU][eE] | [fF][aA][lL][sS][eE]
  ;

// Reserved keywords (are case-insensitive): ---------------------------------

ALL : [aA][lL][lL] ;
ANY : [aA][nN][yY] ;
AS : [aA][sS] ;
BY : [bB][yY] ;
CASE : [cC][aA][sS][eE] ;
CAST : [cC][aA][sS][tT] ;
DISTINCT : [dD][iI][sS][tT][iI][nN][cC][tT] ;
EXISTS : [eE][xX][iI][sS][tT][sS] ;
EXTRACT : [eE][xX][tT][rR][aA][cC][tT] ;
// FALSE: see Boolean
FROM : [fF][rR][oO][mM] ;
IN : [iI][nN] ;
KEY : [kK][eE][yY] ;
NEW : [nN][eE][wW] ;            // token rewrite for NEW -> not reserved (also not in SQL)
NOT : [nN][oO][tT] ;
NULL : [nN][uU][lL][lL] ;
OF : [oO][fF] ;
ON : [oO][nN] ;
SELECT : [sS][eE][lL][eE][cC][tT] ;
SOME : [sS][oO][mM][eE] ;
WHEN : [wW][hH][eE][nN] ;
TRIM : [tT][rR][iI][mM] ;
// TRUE: see Boolean
WHERE : [wW][hH][eE][rR][eE] ;
WITH : [wW][iI][tT][hH] ;

// Fixed Token which is defined DIRECTLY BEFORE the unreserved keywords ------

Number                          // DO NOT RENAME OR MOVE THIS RULE !!!
  : [0-9]+                      // no initial sign
    ( '.' [0-9]+ )?
    ( [eE] ('+'|'-')? [0-9]+ )?
  ;

// Unreserved keywords (are case-insensitive): -------------------------------

ABSTRACT : [aA][bB][sS][tT][rR][aA][cC][tT] ;
ACTION : [aA][cC][tT][iI][oO][nN] ;
ACTIONS : [aA][cC][tT][iI][oO][nN][sS] ;
AND : [aA][nN][dD] ;
ANNOTATE : [aA][nN][nN][oO][tT][aA][tT][eE] ;
ANNOTATION : [aA][nN][nN][oO][tT][aA][tT][iI][oO][nN] ;
ARRAY : [aA][rR][rR][aA][yY] ;
ASC : [aA][sS][cC] ;
ASPECT : [aA][sS][pP][eE][cC][tT] ;
ASSOCIATION : [aA][sS][sS][oO][cC][iI][aA][tT][iI][oO][nN] ;
BETWEEN : [bB][eE][tT][wW][eE][eE][nN] ;
BOTH : [bB][oO][tT][hH] ;
COMPOSITION : [cC][oO][mM][pP][oO][sS][iI][tT][iI][oO][nN] ;
CONTEXT : [cC][oO][nN][tT][eE][xX][tT] ;
CROSS : [cC][rR][oO][sS][sS] ;
CURRENT : [cC][uU][rR][rR][eE][nN][tT] ;
DAY : [dD][aA][yY] ;
DEFAULT : [dD][eE][fF][aA][uU][lL][tT] ;
DEFINE : [dD][eE][fF][iI][nN][eE] ;
DESC : [dD][eE][sS][cC] ;
ELEMENT : [eE][lL][eE][mM][eE][nN][tT] ;
ELSE : [eE][lL][sS][eE] ;
END : [eE][nN][dD] ;
ENTITY : [eE][nN][tT][iI][tT][yY] ;
ENUM : [eE][nN][uU][mM] ;
EVENT : [eE][vV][eE][nN][tT] ;
ESCAPE : [eE][sS][cC][aA][pP][eE] ;
EXACT : [eE][xX][aA][cC][tT] ;
EXCEPT : [eE][xX][cC][eE][pP][tT] ;
EXCLUDING : [eE][xX][cC][lL][uU][dD][iI][nN][gG] ;
EXTEND : [eE][xX][tT][eE][nN][dD] ;
FIRST : [fF][iI][rR][sS][tT] ;
FLOATING : [fF][lL][oO][aA][tT][iI][nN][gG] ;
FOLLOWING : [fF][oO][lL][lL][oO][wW][iI][nN][gG] ;
FULL : [fF][uU][lL][lL] ;
FUNCTION : [fF][uU][nN][cC][tT][iI][oO][nN] ;
GROUP : [gG][rR][oO][uU][pP] ;
HAVING : [hH][aA][vV][iI][nN][gG] ;
HOUR : [hH][oO][uU][rR] ;
INNER : [iI][nN][nN][eE][rR] ;
INTERSECT : [iI][nN][tT][eE][rR][sS][eE][cC][tT] ;
INTO : [iI][nN][tT][oO] ;
IS : [iI][sS] ;
JOIN : [jJ][oO][iI][nN] ;
LAST : [lL][aA][sS][tT] ;
LEADING : [lL][eE][aA][dD][iI][nN][gG] ;
LEFT : [lL][eE][fF][tT] ;
LIKE : [lL][iI][kK][eE] ;
LIMIT : [lL][iI][mM][iI][tT] ;
LOCALIZED: [lL][oO][cC][aA][lL][iI][zZ][eE][dD];
MANY : [mM][aA][nN][yY] ;
MASKED : [mM][aA][sS][kK][eE][dD] ;
MINUS : [mM][iI][nN][uU][sS] ;
MINUTE : [mM][iI][nN][uU][tT][eE] ;
MIXIN : [mM][iI][xX][iI][nN] ;
MONTH : [mM][oO][nN][tT][hH] ;
NAMESPACE : [nN][aA][mM][eE][sS][pP][aA][cC][eE] ;
NULLS : [nN][uU][lL][lL][sS] ;
OFFSET : [oO][fF][fF][sS][eE][tT] ;
ONE : [oO][nN][eE] ;
OR : [oO][rR] ;
ORDER : [oO][rR][dD][eE][rR] ;
OUTER : [oO][uU][tT][eE][rR] ;
// OVER : [oO][vV][eE][rR] ;
PARAMETERS : [pP][aA][rR][aA][mM][eE][tT][eE][rR][sS] ;
PARTITION: [pP][aA][rR][tT][iI][tT][iI][oO][nN] ;
PRECEDING: [pP][rR][eE][cC][eE][dD][iI][nN][gG] ;
PROJECTION : [pP][rR][oO][jJ][eE][cC][tT][iI][oO][nN] ;
REDIRECTED : [rR][eE][dD][iI][rR][eE][cC][tT][eE][dD] ;
RETURNS : [rR][eE][tT][uU][rR][nN][sS] ;
RIGHT : [rR][iI][gG][hH][tT] ;
ROW : [rR][oO][wW] ;
ROWS : [rR][oO][wW][sS] ;
SECOND : [sS][eE][cC][oO][nN][dD] ;
SERVICE : [sS][eE][rR][vV][iI][cC][eE] ;
THEN : [tT][hH][eE][nN] ;
TRAILING : [tT][rR][aA][iI][lL][iI][nN][gG] ;
TO : [tT][oO] ;                 // or make reserved? (is in SQL-92)
TYPE : [tT][yY][pP][eE] ;
UNION : [uU][nN][iI][oO][nN] ;
UNBOUNDED : [uU][nN][bB][oO][uU][nN][dD][eE][dD] ;
USING : [uU][sS][iI][nN][gG] ;
VARIABLE : [vV][aA][rR][iI][aA][bB][lL][eE] ;
VIEW : [vV][iI][eE][wW] ;
// VIRTUAL: [vV][iI][rR][tT][uU][aA][lL] ; see tokens {}
YEAR : [yY][eE][aA][rR] ;

// Identifiers, must BE LAST, DIRECTLY AFTER the unreserved keywords ---------

Identifier                      // DO NOT RENAME OR MOVE THIS RULE !!!
  : [$_a-zA-Z][$_a-zA-Z0-9]*    // i.e. including $param
  | ( '"' ~[\u0022\n\r\u2028\u2029]* '"' )+ // \u0022 = '"'
  | '![' ~[\u005d\n\r\u2028\u2029]* ']' ( ']' ~[\u005d\n\r\u2028\u2029]* ']' )* // \u005d = ']'
  ;

IllegalToken : . ;

// Local Variables:
// c-basic-offset: 2
// End: