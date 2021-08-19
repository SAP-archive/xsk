// Generated from com\sap\xsk\parser\hdbdd\core\Cds.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbdd.core;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CdsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__22=1, T__21=2, T__20=3, T__19=4, T__18=5, T__17=6, T__16=7, T__15=8, 
		T__14=9, T__13=10, T__12=11, T__11=12, T__10=13, T__9=14, T__8=15, T__7=16, 
		T__6=17, T__5=18, T__4=19, T__3=20, T__2=21, T__1=22, T__0=23, DEFAULT=24, 
		KEY=25, ASSOCIATION_MIN=26, ASSOCIATION=27, BOOLEAN=28, ID=29, SEMICOLUMN=30, 
		INTEGER=31, DECIMAL=32, LOCAL_TIME=33, LOCAL_DATE=34, UTC_DATE_TIME=35, 
		UTC_TIMESTAMP=36, STRING=37, TYPE_OF=38, NULL=39, NOT_NULL=40, NOT=41, 
		WS=42, LINE_COMMENT=43;
	public static final String[] tokenNames = {
		"<INVALID>", "'as'", "'using'", "'{'", "'on'", "'::'", "'='", "'entity'", 
		"'}'", "'('", "'*'", "'context'", "','", "'.'", "'to'", "':'", "'['", 
		"']'", "'@'", "'type'", "'namespace'", "'#'", "')'", "'hana'", "'default'", 
		"'key'", "ASSOCIATION_MIN", "'Association'", "BOOLEAN", "ID", "';'", "INTEGER", 
		"DECIMAL", "LOCAL_TIME", "LOCAL_DATE", "UTC_DATE_TIME", "UTC_TIMESTAMP", 
		"STRING", "TYPE_OF", "'null'", "NOT_NULL", "'not'", "WS", "LINE_COMMENT"
	};
	public static final int
		RULE_cdsFile = 0, RULE_namespaceRule = 1, RULE_usingRule = 2, RULE_topLevelSymbol = 3, 
		RULE_contextRule = 4, RULE_entityRule = 5, RULE_structuredDataTypeRule = 6, 
		RULE_dataTypeRule = 7, RULE_fieldDeclRule = 8, RULE_typeAssignRule = 9, 
		RULE_elementDeclRule = 10, RULE_elementConstraints = 11, RULE_association = 12, 
		RULE_associationTarget = 13, RULE_unmanagedForeignKey = 14, RULE_managedForeignKeys = 15, 
		RULE_foreignKey = 16, RULE_cardinality = 17, RULE_defaultValue = 18, RULE_annotationRule = 19, 
		RULE_annValue = 20, RULE_enumRule = 21, RULE_arrRule = 22, RULE_obj = 23, 
		RULE_keyValue = 24;
	public static final String[] ruleNames = {
		"cdsFile", "namespaceRule", "usingRule", "topLevelSymbol", "contextRule", 
		"entityRule", "structuredDataTypeRule", "dataTypeRule", "fieldDeclRule", 
		"typeAssignRule", "elementDeclRule", "elementConstraints", "association", 
		"associationTarget", "unmanagedForeignKey", "managedForeignKeys", "foreignKey", 
		"cardinality", "defaultValue", "annotationRule", "annValue", "enumRule", 
		"arrRule", "obj", "keyValue"
	};

	@Override
	public String getGrammarFileName() { return "Cds.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CdsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CdsFileContext extends ParserRuleContext {
		public UsingRuleContext usingRule(int i) {
			return getRuleContext(UsingRuleContext.class,i);
		}
		public List<UsingRuleContext> usingRule() {
			return getRuleContexts(UsingRuleContext.class);
		}
		public TopLevelSymbolContext topLevelSymbol() {
			return getRuleContext(TopLevelSymbolContext.class,0);
		}
		public NamespaceRuleContext namespaceRule() {
			return getRuleContext(NamespaceRuleContext.class,0);
		}
		public CdsFileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cdsFile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterCdsFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitCdsFile(this);
		}
	}

	public final CdsFileContext cdsFile() throws RecognitionException {
		CdsFileContext _localctx = new CdsFileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_cdsFile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50); namespaceRule();
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(51); usingRule();
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(58);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__12) | (1L << T__5) | (1L << T__4))) != 0)) {
				{
				setState(57); topLevelSymbol();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamespaceRuleContext extends ParserRuleContext {
		public Token ID;
		public List<Token> members = new ArrayList<Token>();
		public List<TerminalNode> ID() { return getTokens(CdsParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CdsParser.ID, i);
		}
		public NamespaceRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterNamespaceRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitNamespaceRule(this);
		}
	}

	public final NamespaceRuleContext namespaceRule() throws RecognitionException {
		NamespaceRuleContext _localctx = new NamespaceRuleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_namespaceRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); match(T__3);
			setState(61); ((NamespaceRuleContext)_localctx).ID = match(ID);
			((NamespaceRuleContext)_localctx).members.add(((NamespaceRuleContext)_localctx).ID);
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(62); match(T__10);
				setState(63); ((NamespaceRuleContext)_localctx).ID = match(ID);
				((NamespaceRuleContext)_localctx).members.add(((NamespaceRuleContext)_localctx).ID);
				}
				}
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(69); match(SEMICOLUMN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UsingRuleContext extends ParserRuleContext {
		public Token ID;
		public List<Token> pack = new ArrayList<Token>();
		public List<Token> members = new ArrayList<Token>();
		public Token alias;
		public List<TerminalNode> ID() { return getTokens(CdsParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CdsParser.ID, i);
		}
		public UsingRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_usingRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterUsingRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitUsingRule(this);
		}
	}

	public final UsingRuleContext usingRule() throws RecognitionException {
		UsingRuleContext _localctx = new UsingRuleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_usingRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71); match(T__21);
			setState(72); ((UsingRuleContext)_localctx).ID = match(ID);
			((UsingRuleContext)_localctx).pack.add(((UsingRuleContext)_localctx).ID);
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(73); match(T__10);
				setState(74); ((UsingRuleContext)_localctx).ID = match(ID);
				((UsingRuleContext)_localctx).pack.add(((UsingRuleContext)_localctx).ID);
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(80); match(T__18);
			setState(81); ((UsingRuleContext)_localctx).ID = match(ID);
			((UsingRuleContext)_localctx).members.add(((UsingRuleContext)_localctx).ID);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(82); match(T__10);
				setState(83); ((UsingRuleContext)_localctx).ID = match(ID);
				((UsingRuleContext)_localctx).members.add(((UsingRuleContext)_localctx).ID);
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(89); match(T__22);
				setState(90); ((UsingRuleContext)_localctx).alias = match(ID);
				}
			}

			setState(93); match(SEMICOLUMN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TopLevelSymbolContext extends ParserRuleContext {
		public EntityRuleContext entityRule() {
			return getRuleContext(EntityRuleContext.class,0);
		}
		public ContextRuleContext contextRule() {
			return getRuleContext(ContextRuleContext.class,0);
		}
		public StructuredDataTypeRuleContext structuredDataTypeRule() {
			return getRuleContext(StructuredDataTypeRuleContext.class,0);
		}
		public DataTypeRuleContext dataTypeRule() {
			return getRuleContext(DataTypeRuleContext.class,0);
		}
		public TopLevelSymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topLevelSymbol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterTopLevelSymbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitTopLevelSymbol(this);
		}
	}

	public final TopLevelSymbolContext topLevelSymbol() throws RecognitionException {
		TopLevelSymbolContext _localctx = new TopLevelSymbolContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_topLevelSymbol);
		try {
			setState(99);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(95); contextRule();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(96); entityRule();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(97); structuredDataTypeRule();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(98); dataTypeRule();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContextRuleContext extends ParserRuleContext {
		public DataTypeRuleContext dataTypeRule(int i) {
			return getRuleContext(DataTypeRuleContext.class,i);
		}
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public StructuredDataTypeRuleContext structuredDataTypeRule(int i) {
			return getRuleContext(StructuredDataTypeRuleContext.class,i);
		}
		public ContextRuleContext contextRule(int i) {
			return getRuleContext(ContextRuleContext.class,i);
		}
		public List<EntityRuleContext> entityRule() {
			return getRuleContexts(EntityRuleContext.class);
		}
		public EntityRuleContext entityRule(int i) {
			return getRuleContext(EntityRuleContext.class,i);
		}
		public List<ContextRuleContext> contextRule() {
			return getRuleContexts(ContextRuleContext.class);
		}
		public List<AnnotationRuleContext> annotationRule() {
			return getRuleContexts(AnnotationRuleContext.class);
		}
		public List<StructuredDataTypeRuleContext> structuredDataTypeRule() {
			return getRuleContexts(StructuredDataTypeRuleContext.class);
		}
		public AnnotationRuleContext annotationRule(int i) {
			return getRuleContext(AnnotationRuleContext.class,i);
		}
		public List<DataTypeRuleContext> dataTypeRule() {
			return getRuleContexts(DataTypeRuleContext.class);
		}
		public ContextRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contextRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterContextRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitContextRule(this);
		}
	}

	public final ContextRuleContext contextRule() throws RecognitionException {
		ContextRuleContext _localctx = new ContextRuleContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_contextRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(101); annotationRule();
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(107); match(T__12);
			setState(108); match(ID);
			setState(109); match(T__20);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__12) | (1L << T__5) | (1L << T__4))) != 0)) {
				{
				setState(114);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(110); contextRule();
					}
					break;

				case 2:
					{
					setState(111); entityRule();
					}
					break;

				case 3:
					{
					setState(112); structuredDataTypeRule();
					}
					break;

				case 4:
					{
					setState(113); dataTypeRule();
					}
					break;
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(119); match(T__15);
			setState(120); match(SEMICOLUMN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EntityRuleContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public List<ElementDeclRuleContext> elementDeclRule() {
			return getRuleContexts(ElementDeclRuleContext.class);
		}
		public List<AnnotationRuleContext> annotationRule() {
			return getRuleContexts(AnnotationRuleContext.class);
		}
		public List<AssociationContext> association() {
			return getRuleContexts(AssociationContext.class);
		}
		public AssociationContext association(int i) {
			return getRuleContext(AssociationContext.class,i);
		}
		public AnnotationRuleContext annotationRule(int i) {
			return getRuleContext(AnnotationRuleContext.class,i);
		}
		public ElementDeclRuleContext elementDeclRule(int i) {
			return getRuleContext(ElementDeclRuleContext.class,i);
		}
		public EntityRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entityRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterEntityRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitEntityRule(this);
		}
	}

	public final EntityRuleContext entityRule() throws RecognitionException {
		EntityRuleContext _localctx = new EntityRuleContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_entityRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(122); annotationRule();
				}
				}
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(128); match(T__16);
			setState(129); match(ID);
			setState(130); match(T__20);
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << KEY) | (1L << ID))) != 0)) {
				{
				setState(133);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(131); association();
					}
					break;

				case 2:
					{
					setState(132); elementDeclRule();
					}
					break;
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(138); match(T__15);
			setState(139); match(SEMICOLUMN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructuredDataTypeRuleContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public FieldDeclRuleContext fieldDeclRule(int i) {
			return getRuleContext(FieldDeclRuleContext.class,i);
		}
		public List<AnnotationRuleContext> annotationRule() {
			return getRuleContexts(AnnotationRuleContext.class);
		}
		public List<FieldDeclRuleContext> fieldDeclRule() {
			return getRuleContexts(FieldDeclRuleContext.class);
		}
		public AnnotationRuleContext annotationRule(int i) {
			return getRuleContext(AnnotationRuleContext.class,i);
		}
		public StructuredDataTypeRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structuredDataTypeRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterStructuredDataTypeRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitStructuredDataTypeRule(this);
		}
	}

	public final StructuredDataTypeRuleContext structuredDataTypeRule() throws RecognitionException {
		StructuredDataTypeRuleContext _localctx = new StructuredDataTypeRuleContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_structuredDataTypeRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(141); annotationRule();
				}
				}
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(147); match(T__4);
			setState(148); match(ID);
			setState(149); match(T__20);
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(150); fieldDeclRule();
				}
				}
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(156); match(T__15);
			setState(157); match(SEMICOLUMN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataTypeRuleContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public TypeAssignRuleContext typeAssignRule() {
			return getRuleContext(TypeAssignRuleContext.class,0);
		}
		public DataTypeRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataTypeRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterDataTypeRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitDataTypeRule(this);
		}
	}

	public final DataTypeRuleContext dataTypeRule() throws RecognitionException {
		DataTypeRuleContext _localctx = new DataTypeRuleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_dataTypeRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159); match(T__4);
			setState(160); match(ID);
			setState(161); match(T__8);
			setState(162); typeAssignRule();
			setState(163); match(SEMICOLUMN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldDeclRuleContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public TypeAssignRuleContext typeAssignRule() {
			return getRuleContext(TypeAssignRuleContext.class,0);
		}
		public FieldDeclRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDeclRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterFieldDeclRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitFieldDeclRule(this);
		}
	}

	public final FieldDeclRuleContext fieldDeclRule() throws RecognitionException {
		FieldDeclRuleContext _localctx = new FieldDeclRuleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_fieldDeclRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165); match(ID);
			setState(166); match(T__8);
			setState(167); typeAssignRule();
			setState(168); match(SEMICOLUMN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeAssignRuleContext extends ParserRuleContext {
		public TypeAssignRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeAssignRule; }
	 
		public TypeAssignRuleContext() { }
		public void copyFrom(TypeAssignRuleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignBuiltInTypeWithArgsContext extends TypeAssignRuleContext {
		public Token ref;
		public Token INTEGER;
		public List<Token> args = new ArrayList<Token>();
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(CdsParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(CdsParser.INTEGER, i);
		}
		public AssignBuiltInTypeWithArgsContext(TypeAssignRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAssignBuiltInTypeWithArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAssignBuiltInTypeWithArgs(this);
		}
	}
	public static class AssignHanaTypeWithArgsContext extends TypeAssignRuleContext {
		public Token ref;
		public Token INTEGER;
		public List<Token> args = new ArrayList<Token>();
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(CdsParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(CdsParser.INTEGER, i);
		}
		public AssignHanaTypeWithArgsContext(TypeAssignRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAssignHanaTypeWithArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAssignHanaTypeWithArgs(this);
		}
	}
	public static class AssignHanaTypeContext extends TypeAssignRuleContext {
		public Token ref;
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public AssignHanaTypeContext(TypeAssignRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAssignHanaType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAssignHanaType(this);
		}
	}
	public static class AssignTypeContext extends TypeAssignRuleContext {
		public Token ID;
		public List<Token> pathSubMembers = new ArrayList<Token>();
		public List<TerminalNode> ID() { return getTokens(CdsParser.ID); }
		public TerminalNode TYPE_OF() { return getToken(CdsParser.TYPE_OF, 0); }
		public TerminalNode ID(int i) {
			return getToken(CdsParser.ID, i);
		}
		public AssignTypeContext(TypeAssignRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAssignType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAssignType(this);
		}
	}

	public final TypeAssignRuleContext typeAssignRule() throws RecognitionException {
		TypeAssignRuleContext _localctx = new TypeAssignRuleContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_typeAssignRule);
		int _la;
		try {
			setState(208);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new AssignBuiltInTypeWithArgsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(170); ((AssignBuiltInTypeWithArgsContext)_localctx).ref = match(ID);
				setState(171); match(T__14);
				setState(172); ((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
				((AssignBuiltInTypeWithArgsContext)_localctx).args.add(((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER);
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(173); match(T__11);
					setState(174); ((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
					((AssignBuiltInTypeWithArgsContext)_localctx).args.add(((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER);
					}
					}
					setState(179);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(180); match(T__1);
				}
				break;

			case 2:
				_localctx = new AssignHanaTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(181); match(T__0);
				setState(182); match(T__10);
				setState(183); ((AssignHanaTypeContext)_localctx).ref = match(ID);
				}
				break;

			case 3:
				_localctx = new AssignHanaTypeWithArgsContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(184); match(T__0);
				setState(185); match(T__10);
				setState(186); ((AssignHanaTypeWithArgsContext)_localctx).ref = match(ID);
				setState(187); match(T__14);
				setState(188); ((AssignHanaTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
				((AssignHanaTypeWithArgsContext)_localctx).args.add(((AssignHanaTypeWithArgsContext)_localctx).INTEGER);
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(189); match(T__11);
					setState(190); ((AssignHanaTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
					((AssignHanaTypeWithArgsContext)_localctx).args.add(((AssignHanaTypeWithArgsContext)_localctx).INTEGER);
					}
					}
					setState(195);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(196); match(T__1);
				}
				break;

			case 4:
				_localctx = new AssignTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(198);
				_la = _input.LA(1);
				if (_la==TYPE_OF) {
					{
					setState(197); match(TYPE_OF);
					}
				}

				setState(200); ((AssignTypeContext)_localctx).ID = match(ID);
				((AssignTypeContext)_localctx).pathSubMembers.add(((AssignTypeContext)_localctx).ID);
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__10) {
					{
					{
					setState(201); match(T__10);
					setState(202); ((AssignTypeContext)_localctx).ID = match(ID);
					((AssignTypeContext)_localctx).pathSubMembers.add(((AssignTypeContext)_localctx).ID);
					}
					}
					setState(207);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementDeclRuleContext extends ParserRuleContext {
		public Token key;
		public ElementConstraintsContext elementConstraints() {
			return getRuleContext(ElementConstraintsContext.class,0);
		}
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public List<AnnotationRuleContext> annotationRule() {
			return getRuleContexts(AnnotationRuleContext.class);
		}
		public TypeAssignRuleContext typeAssignRule() {
			return getRuleContext(TypeAssignRuleContext.class,0);
		}
		public AnnotationRuleContext annotationRule(int i) {
			return getRuleContext(AnnotationRuleContext.class,i);
		}
		public ElementDeclRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementDeclRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterElementDeclRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitElementDeclRule(this);
		}
	}

	public final ElementDeclRuleContext elementDeclRule() throws RecognitionException {
		ElementDeclRuleContext _localctx = new ElementDeclRuleContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_elementDeclRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(210); annotationRule();
				}
				}
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(217);
			_la = _input.LA(1);
			if (_la==KEY) {
				{
				setState(216); ((ElementDeclRuleContext)_localctx).key = match(KEY);
				}
			}

			setState(219); match(ID);
			setState(220); match(T__8);
			setState(221); typeAssignRule();
			setState(223);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(222); defaultValue();
				}
			}

			setState(226);
			_la = _input.LA(1);
			if (_la==NULL || _la==NOT_NULL) {
				{
				setState(225); elementConstraints();
				}
			}

			setState(228); match(SEMICOLUMN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementConstraintsContext extends ParserRuleContext {
		public TerminalNode NULL() { return getToken(CdsParser.NULL, 0); }
		public TerminalNode NOT_NULL() { return getToken(CdsParser.NOT_NULL, 0); }
		public ElementConstraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementConstraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterElementConstraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitElementConstraints(this);
		}
	}

	public final ElementConstraintsContext elementConstraints() throws RecognitionException {
		ElementConstraintsContext _localctx = new ElementConstraintsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_elementConstraints);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			_la = _input.LA(1);
			if ( !(_la==NULL || _la==NOT_NULL) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssociationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public ManagedForeignKeysContext managedForeignKeys(int i) {
			return getRuleContext(ManagedForeignKeysContext.class,i);
		}
		public AssociationTargetContext associationTarget() {
			return getRuleContext(AssociationTargetContext.class,0);
		}
		public CardinalityContext cardinality() {
			return getRuleContext(CardinalityContext.class,0);
		}
		public List<UnmanagedForeignKeyContext> unmanagedForeignKey() {
			return getRuleContexts(UnmanagedForeignKeyContext.class);
		}
		public UnmanagedForeignKeyContext unmanagedForeignKey(int i) {
			return getRuleContext(UnmanagedForeignKeyContext.class,i);
		}
		public List<ManagedForeignKeysContext> managedForeignKeys() {
			return getRuleContexts(ManagedForeignKeysContext.class);
		}
		public AssociationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_association; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAssociation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAssociation(this);
		}
	}

	public final AssociationContext association() throws RecognitionException {
		AssociationContext _localctx = new AssociationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_association);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232); match(ID);
			setState(233); match(T__8);
			setState(234); match(ASSOCIATION);
			setState(236);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(235); cardinality();
				}
			}

			setState(238); match(T__9);
			setState(239); associationTarget();
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__20 || _la==T__19) {
				{
				setState(242);
				switch (_input.LA(1)) {
				case T__20:
					{
					setState(240); managedForeignKeys();
					}
					break;
				case T__19:
					{
					setState(241); unmanagedForeignKey();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(247); match(SEMICOLUMN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssociationTargetContext extends ParserRuleContext {
		public Token ID;
		public List<Token> pathSubMembers = new ArrayList<Token>();
		public List<TerminalNode> ID() { return getTokens(CdsParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CdsParser.ID, i);
		}
		public AssociationTargetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_associationTarget; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAssociationTarget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAssociationTarget(this);
		}
	}

	public final AssociationTargetContext associationTarget() throws RecognitionException {
		AssociationTargetContext _localctx = new AssociationTargetContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_associationTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249); ((AssociationTargetContext)_localctx).ID = match(ID);
			((AssociationTargetContext)_localctx).pathSubMembers.add(((AssociationTargetContext)_localctx).ID);
			setState(254);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(250); match(T__10);
				setState(251); ((AssociationTargetContext)_localctx).ID = match(ID);
				((AssociationTargetContext)_localctx).pathSubMembers.add(((AssociationTargetContext)_localctx).ID);
				}
				}
				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnmanagedForeignKeyContext extends ParserRuleContext {
		public Token ID;
		public List<Token> pathSubMembers = new ArrayList<Token>();
		public Token source;
		public List<TerminalNode> ID() { return getTokens(CdsParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CdsParser.ID, i);
		}
		public UnmanagedForeignKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unmanagedForeignKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterUnmanagedForeignKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitUnmanagedForeignKey(this);
		}
	}

	public final UnmanagedForeignKeyContext unmanagedForeignKey() throws RecognitionException {
		UnmanagedForeignKeyContext _localctx = new UnmanagedForeignKeyContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_unmanagedForeignKey);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257); match(T__19);
			setState(258); ((UnmanagedForeignKeyContext)_localctx).ID = match(ID);
			((UnmanagedForeignKeyContext)_localctx).pathSubMembers.add(((UnmanagedForeignKeyContext)_localctx).ID);
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(259); match(T__10);
				setState(260); ((UnmanagedForeignKeyContext)_localctx).ID = match(ID);
				((UnmanagedForeignKeyContext)_localctx).pathSubMembers.add(((UnmanagedForeignKeyContext)_localctx).ID);
				}
				}
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(266); match(T__17);
			setState(267); ((UnmanagedForeignKeyContext)_localctx).source = match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ManagedForeignKeysContext extends ParserRuleContext {
		public List<ForeignKeyContext> foreignKey() {
			return getRuleContexts(ForeignKeyContext.class);
		}
		public ForeignKeyContext foreignKey(int i) {
			return getRuleContext(ForeignKeyContext.class,i);
		}
		public ManagedForeignKeysContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_managedForeignKeys; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterManagedForeignKeys(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitManagedForeignKeys(this);
		}
	}

	public final ManagedForeignKeysContext managedForeignKeys() throws RecognitionException {
		ManagedForeignKeysContext _localctx = new ManagedForeignKeysContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_managedForeignKeys);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269); match(T__20);
			setState(270); foreignKey();
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(271); match(T__11);
				setState(272); foreignKey();
				}
				}
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(278); match(T__15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForeignKeyContext extends ParserRuleContext {
		public Token ID;
		public List<Token> pathSubMembers = new ArrayList<Token>();
		public List<TerminalNode> ID() { return getTokens(CdsParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CdsParser.ID, i);
		}
		public ForeignKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreignKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterForeignKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitForeignKey(this);
		}
	}

	public final ForeignKeyContext foreignKey() throws RecognitionException {
		ForeignKeyContext _localctx = new ForeignKeyContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_foreignKey);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280); ((ForeignKeyContext)_localctx).ID = match(ID);
			((ForeignKeyContext)_localctx).pathSubMembers.add(((ForeignKeyContext)_localctx).ID);
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(281); match(T__10);
				setState(282); ((ForeignKeyContext)_localctx).ID = match(ID);
				((ForeignKeyContext)_localctx).pathSubMembers.add(((ForeignKeyContext)_localctx).ID);
				}
				}
				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CardinalityContext extends ParserRuleContext {
		public CardinalityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cardinality; }
	 
		public CardinalityContext() { }
		public void copyFrom(CardinalityContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NoCardinalityContext extends CardinalityContext {
		public NoCardinalityContext(CardinalityContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterNoCardinality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitNoCardinality(this);
		}
	}
	public static class MaxCardinalityContext extends CardinalityContext {
		public Token max;
		public Token many;
		public TerminalNode INTEGER() { return getToken(CdsParser.INTEGER, 0); }
		public MaxCardinalityContext(CardinalityContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterMaxCardinality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitMaxCardinality(this);
		}
	}
	public static class MinMaxCardinalityContext extends CardinalityContext {
		public Token max;
		public Token many;
		public TerminalNode INTEGER() { return getToken(CdsParser.INTEGER, 0); }
		public TerminalNode ASSOCIATION_MIN() { return getToken(CdsParser.ASSOCIATION_MIN, 0); }
		public MinMaxCardinalityContext(CardinalityContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterMinMaxCardinality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitMinMaxCardinality(this);
		}
	}

	public final CardinalityContext cardinality() throws RecognitionException {
		CardinalityContext _localctx = new CardinalityContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_cardinality);
		try {
			setState(303);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				_localctx = new MinMaxCardinalityContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(288); match(T__7);
				setState(289); match(ASSOCIATION_MIN);
				setState(292);
				switch (_input.LA(1)) {
				case INTEGER:
					{
					setState(290); ((MinMaxCardinalityContext)_localctx).max = match(INTEGER);
					}
					break;
				case T__13:
					{
					setState(291); ((MinMaxCardinalityContext)_localctx).many = match(T__13);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(294); match(T__6);
				}
				break;

			case 2:
				_localctx = new MaxCardinalityContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(295); match(T__7);
				setState(298);
				switch (_input.LA(1)) {
				case INTEGER:
					{
					setState(296); ((MaxCardinalityContext)_localctx).max = match(INTEGER);
					}
					break;
				case T__13:
					{
					setState(297); ((MaxCardinalityContext)_localctx).many = match(T__13);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(300); match(T__6);
				}
				break;

			case 3:
				_localctx = new NoCardinalityContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(301); match(T__7);
				setState(302); match(T__6);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultValueContext extends ParserRuleContext {
		public Token value;
		public TerminalNode DECIMAL() { return getToken(CdsParser.DECIMAL, 0); }
		public TerminalNode INTEGER() { return getToken(CdsParser.INTEGER, 0); }
		public TerminalNode UTC_TIMESTAMP() { return getToken(CdsParser.UTC_TIMESTAMP, 0); }
		public TerminalNode STRING() { return getToken(CdsParser.STRING, 0); }
		public TerminalNode LOCAL_DATE() { return getToken(CdsParser.LOCAL_DATE, 0); }
		public TerminalNode UTC_DATE_TIME() { return getToken(CdsParser.UTC_DATE_TIME, 0); }
		public TerminalNode LOCAL_TIME() { return getToken(CdsParser.LOCAL_TIME, 0); }
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitDefaultValue(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_defaultValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305); match(DEFAULT);
			setState(306);
			((DefaultValueContext)_localctx).value = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DECIMAL) | (1L << LOCAL_TIME) | (1L << LOCAL_DATE) | (1L << UTC_DATE_TIME) | (1L << UTC_TIMESTAMP) | (1L << STRING))) != 0)) ) {
				((DefaultValueContext)_localctx).value = (Token)_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationRuleContext extends ParserRuleContext {
		public AnnotationRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationRule; }
	 
		public AnnotationRuleContext() { }
		public void copyFrom(AnnotationRuleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AnnPropertyRuleContext extends AnnotationRuleContext {
		public Token annId;
		public Token prop;
		public List<TerminalNode> ID() { return getTokens(CdsParser.ID); }
		public AnnValueContext annValue() {
			return getRuleContext(AnnValueContext.class,0);
		}
		public TerminalNode ID(int i) {
			return getToken(CdsParser.ID, i);
		}
		public AnnPropertyRuleContext(AnnotationRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAnnPropertyRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAnnPropertyRule(this);
		}
	}
	public static class AnnObjectRuleContext extends AnnotationRuleContext {
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public AnnValueContext annValue() {
			return getRuleContext(AnnValueContext.class,0);
		}
		public AnnObjectRuleContext(AnnotationRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAnnObjectRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAnnObjectRule(this);
		}
	}
	public static class AnnMarkerRuleContext extends AnnotationRuleContext {
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public AnnMarkerRuleContext(AnnotationRuleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAnnMarkerRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAnnMarkerRule(this);
		}
	}

	public final AnnotationRuleContext annotationRule() throws RecognitionException {
		AnnotationRuleContext _localctx = new AnnotationRuleContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_annotationRule);
		try {
			setState(320);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				_localctx = new AnnObjectRuleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(308); match(T__5);
				setState(309); match(ID);
				setState(310); match(T__8);
				setState(311); annValue();
				}
				break;

			case 2:
				_localctx = new AnnPropertyRuleContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(312); match(T__5);
				setState(313); ((AnnPropertyRuleContext)_localctx).annId = match(ID);
				setState(314); match(T__10);
				setState(315); ((AnnPropertyRuleContext)_localctx).prop = match(ID);
				setState(316); match(T__8);
				setState(317); annValue();
				}
				break;

			case 3:
				_localctx = new AnnMarkerRuleContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(318); match(T__5);
				setState(319); match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnValueContext extends ParserRuleContext {
		public Token literal;
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public ArrRuleContext arrRule() {
			return getRuleContext(ArrRuleContext.class,0);
		}
		public TerminalNode STRING() { return getToken(CdsParser.STRING, 0); }
		public TerminalNode BOOLEAN() { return getToken(CdsParser.BOOLEAN, 0); }
		public EnumRuleContext enumRule() {
			return getRuleContext(EnumRuleContext.class,0);
		}
		public AnnValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterAnnValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitAnnValue(this);
		}
	}

	public final AnnValueContext annValue() throws RecognitionException {
		AnnValueContext _localctx = new AnnValueContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_annValue);
		int _la;
		try {
			setState(326);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(322); arrRule();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(323); enumRule();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				setState(324); obj();
				}
				break;
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(325);
				((AnnValueContext)_localctx).literal = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==BOOLEAN || _la==STRING) ) {
					((AnnValueContext)_localctx).literal = (Token)_errHandler.recoverInline(this);
				}
				consume();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumRuleContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public EnumRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterEnumRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitEnumRule(this);
		}
	}

	public final EnumRuleContext enumRule() throws RecognitionException {
		EnumRuleContext _localctx = new EnumRuleContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_enumRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328); match(T__2);
			setState(329); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrRuleContext extends ParserRuleContext {
		public List<AnnValueContext> annValue() {
			return getRuleContexts(AnnValueContext.class);
		}
		public AnnValueContext annValue(int i) {
			return getRuleContext(AnnValueContext.class,i);
		}
		public ArrRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterArrRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitArrRule(this);
		}
	}

	public final ArrRuleContext arrRule() throws RecognitionException {
		ArrRuleContext _localctx = new ArrRuleContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_arrRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331); match(T__7);
			setState(332); annValue();
			setState(337);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(333); match(T__11);
				setState(334); annValue();
				}
				}
				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(340); match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjContext extends ParserRuleContext {
		public KeyValueContext keyValue(int i) {
			return getRuleContext(KeyValueContext.class,i);
		}
		public List<KeyValueContext> keyValue() {
			return getRuleContexts(KeyValueContext.class);
		}
		public ObjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obj; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterObj(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitObj(this);
		}
	}

	public final ObjContext obj() throws RecognitionException {
		ObjContext _localctx = new ObjContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_obj);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342); match(T__20);
			setState(343); keyValue();
			setState(348);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(344); match(T__11);
				setState(345); keyValue();
				}
				}
				setState(350);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(351); match(T__15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyValueContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public AnnValueContext annValue() {
			return getRuleContext(AnnValueContext.class,0);
		}
		public KeyValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterKeyValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitKeyValue(this);
		}
	}

	public final KeyValueContext keyValue() throws RecognitionException {
		KeyValueContext _localctx = new KeyValueContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_keyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353); match(ID);
			setState(354); match(T__8);
			setState(355); annValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3-\u0168\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\7\2\67\n\2\f\2\16\2:\13\2\3\2\5\2=\n\2\3\3\3\3\3\3"+
		"\3\3\7\3C\n\3\f\3\16\3F\13\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4N\n\4\f\4\16\4"+
		"Q\13\4\3\4\3\4\3\4\3\4\7\4W\n\4\f\4\16\4Z\13\4\3\4\3\4\5\4^\n\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\5\5f\n\5\3\6\7\6i\n\6\f\6\16\6l\13\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\7\6u\n\6\f\6\16\6x\13\6\3\6\3\6\3\6\3\7\7\7~\n\7\f\7\16"+
		"\7\u0081\13\7\3\7\3\7\3\7\3\7\3\7\7\7\u0088\n\7\f\7\16\7\u008b\13\7\3"+
		"\7\3\7\3\7\3\b\7\b\u0091\n\b\f\b\16\b\u0094\13\b\3\b\3\b\3\b\3\b\7\b\u009a"+
		"\n\b\f\b\16\b\u009d\13\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\7\13\u00b2\n\13\f\13\16\13\u00b5\13"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00c2"+
		"\n\13\f\13\16\13\u00c5\13\13\3\13\3\13\5\13\u00c9\n\13\3\13\3\13\3\13"+
		"\7\13\u00ce\n\13\f\13\16\13\u00d1\13\13\5\13\u00d3\n\13\3\f\7\f\u00d6"+
		"\n\f\f\f\16\f\u00d9\13\f\3\f\5\f\u00dc\n\f\3\f\3\f\3\f\3\f\5\f\u00e2\n"+
		"\f\3\f\5\f\u00e5\n\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\5\16\u00ef\n"+
		"\16\3\16\3\16\3\16\3\16\7\16\u00f5\n\16\f\16\16\16\u00f8\13\16\3\16\3"+
		"\16\3\17\3\17\3\17\7\17\u00ff\n\17\f\17\16\17\u0102\13\17\3\20\3\20\3"+
		"\20\3\20\7\20\u0108\n\20\f\20\16\20\u010b\13\20\3\20\3\20\3\20\3\21\3"+
		"\21\3\21\3\21\7\21\u0114\n\21\f\21\16\21\u0117\13\21\3\21\3\21\3\22\3"+
		"\22\3\22\7\22\u011e\n\22\f\22\16\22\u0121\13\22\3\23\3\23\3\23\3\23\5"+
		"\23\u0127\n\23\3\23\3\23\3\23\3\23\5\23\u012d\n\23\3\23\3\23\3\23\5\23"+
		"\u0132\n\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\5\25\u0143\n\25\3\26\3\26\3\26\3\26\5\26\u0149\n\26\3"+
		"\27\3\27\3\27\3\30\3\30\3\30\3\30\7\30\u0152\n\30\f\30\16\30\u0155\13"+
		"\30\3\30\3\30\3\31\3\31\3\31\3\31\7\31\u015d\n\31\f\31\16\31\u0160\13"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\2\2\33\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\2\5\3\2)*\3\2!\'\4\2\36\36\'\'\u017e\2"+
		"\64\3\2\2\2\4>\3\2\2\2\6I\3\2\2\2\be\3\2\2\2\nj\3\2\2\2\f\177\3\2\2\2"+
		"\16\u0092\3\2\2\2\20\u00a1\3\2\2\2\22\u00a7\3\2\2\2\24\u00d2\3\2\2\2\26"+
		"\u00d7\3\2\2\2\30\u00e8\3\2\2\2\32\u00ea\3\2\2\2\34\u00fb\3\2\2\2\36\u0103"+
		"\3\2\2\2 \u010f\3\2\2\2\"\u011a\3\2\2\2$\u0131\3\2\2\2&\u0133\3\2\2\2"+
		"(\u0142\3\2\2\2*\u0148\3\2\2\2,\u014a\3\2\2\2.\u014d\3\2\2\2\60\u0158"+
		"\3\2\2\2\62\u0163\3\2\2\2\648\5\4\3\2\65\67\5\6\4\2\66\65\3\2\2\2\67:"+
		"\3\2\2\28\66\3\2\2\289\3\2\2\29<\3\2\2\2:8\3\2\2\2;=\5\b\5\2<;\3\2\2\2"+
		"<=\3\2\2\2=\3\3\2\2\2>?\7\26\2\2?D\7\37\2\2@A\7\17\2\2AC\7\37\2\2B@\3"+
		"\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2EG\3\2\2\2FD\3\2\2\2GH\7 \2\2H\5\3"+
		"\2\2\2IJ\7\4\2\2JO\7\37\2\2KL\7\17\2\2LN\7\37\2\2MK\3\2\2\2NQ\3\2\2\2"+
		"OM\3\2\2\2OP\3\2\2\2PR\3\2\2\2QO\3\2\2\2RS\7\7\2\2SX\7\37\2\2TU\7\17\2"+
		"\2UW\7\37\2\2VT\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y]\3\2\2\2ZX\3\2"+
		"\2\2[\\\7\3\2\2\\^\7\37\2\2][\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\7 \2\2`\7"+
		"\3\2\2\2af\5\n\6\2bf\5\f\7\2cf\5\16\b\2df\5\20\t\2ea\3\2\2\2eb\3\2\2\2"+
		"ec\3\2\2\2ed\3\2\2\2f\t\3\2\2\2gi\5(\25\2hg\3\2\2\2il\3\2\2\2jh\3\2\2"+
		"\2jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mn\7\r\2\2no\7\37\2\2ov\7\5\2\2pu\5\n"+
		"\6\2qu\5\f\7\2ru\5\16\b\2su\5\20\t\2tp\3\2\2\2tq\3\2\2\2tr\3\2\2\2ts\3"+
		"\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2wy\3\2\2\2xv\3\2\2\2yz\7\n\2\2z{\7"+
		" \2\2{\13\3\2\2\2|~\5(\25\2}|\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177"+
		"\u0080\3\2\2\2\u0080\u0082\3\2\2\2\u0081\177\3\2\2\2\u0082\u0083\7\t\2"+
		"\2\u0083\u0084\7\37\2\2\u0084\u0089\7\5\2\2\u0085\u0088\5\32\16\2\u0086"+
		"\u0088\5\26\f\2\u0087\u0085\3\2\2\2\u0087\u0086\3\2\2\2\u0088\u008b\3"+
		"\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b"+
		"\u0089\3\2\2\2\u008c\u008d\7\n\2\2\u008d\u008e\7 \2\2\u008e\r\3\2\2\2"+
		"\u008f\u0091\5(\25\2\u0090\u008f\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090"+
		"\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0092\3\2\2\2\u0095"+
		"\u0096\7\25\2\2\u0096\u0097\7\37\2\2\u0097\u009b\7\5\2\2\u0098\u009a\5"+
		"\22\n\2\u0099\u0098\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u009f\7\n"+
		"\2\2\u009f\u00a0\7 \2\2\u00a0\17\3\2\2\2\u00a1\u00a2\7\25\2\2\u00a2\u00a3"+
		"\7\37\2\2\u00a3\u00a4\7\21\2\2\u00a4\u00a5\5\24\13\2\u00a5\u00a6\7 \2"+
		"\2\u00a6\21\3\2\2\2\u00a7\u00a8\7\37\2\2\u00a8\u00a9\7\21\2\2\u00a9\u00aa"+
		"\5\24\13\2\u00aa\u00ab\7 \2\2\u00ab\23\3\2\2\2\u00ac\u00ad\7\37\2\2\u00ad"+
		"\u00ae\7\13\2\2\u00ae\u00b3\7!\2\2\u00af\u00b0\7\16\2\2\u00b0\u00b2\7"+
		"!\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00d3\7\30"+
		"\2\2\u00b7\u00b8\7\31\2\2\u00b8\u00b9\7\17\2\2\u00b9\u00d3\7\37\2\2\u00ba"+
		"\u00bb\7\31\2\2\u00bb\u00bc\7\17\2\2\u00bc\u00bd\7\37\2\2\u00bd\u00be"+
		"\7\13\2\2\u00be\u00c3\7!\2\2\u00bf\u00c0\7\16\2\2\u00c0\u00c2\7!\2\2\u00c1"+
		"\u00bf\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2"+
		"\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00d3\7\30\2\2\u00c7"+
		"\u00c9\7(\2\2\u00c8\u00c7\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\3\2"+
		"\2\2\u00ca\u00cf\7\37\2\2\u00cb\u00cc\7\17\2\2\u00cc\u00ce\7\37\2\2\u00cd"+
		"\u00cb\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2"+
		"\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00ac\3\2\2\2\u00d2"+
		"\u00b7\3\2\2\2\u00d2\u00ba\3\2\2\2\u00d2\u00c8\3\2\2\2\u00d3\25\3\2\2"+
		"\2\u00d4\u00d6\5(\25\2\u00d5\u00d4\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5"+
		"\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da"+
		"\u00dc\7\33\2\2\u00db\u00da\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\3"+
		"\2\2\2\u00dd\u00de\7\37\2\2\u00de\u00df\7\21\2\2\u00df\u00e1\5\24\13\2"+
		"\u00e0\u00e2\5&\24\2\u00e1\u00e0\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e4"+
		"\3\2\2\2\u00e3\u00e5\5\30\r\2\u00e4\u00e3\3\2\2\2\u00e4\u00e5\3\2\2\2"+
		"\u00e5\u00e6\3\2\2\2\u00e6\u00e7\7 \2\2\u00e7\27\3\2\2\2\u00e8\u00e9\t"+
		"\2\2\2\u00e9\31\3\2\2\2\u00ea\u00eb\7\37\2\2\u00eb\u00ec\7\21\2\2\u00ec"+
		"\u00ee\7\35\2\2\u00ed\u00ef\5$\23\2\u00ee\u00ed\3\2\2\2\u00ee\u00ef\3"+
		"\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\7\20\2\2\u00f1\u00f6\5\34\17\2"+
		"\u00f2\u00f5\5 \21\2\u00f3\u00f5\5\36\20\2\u00f4\u00f2\3\2\2\2\u00f4\u00f3"+
		"\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7"+
		"\u00f9\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9\u00fa\7 \2\2\u00fa\33\3\2\2\2"+
		"\u00fb\u0100\7\37\2\2\u00fc\u00fd\7\17\2\2\u00fd\u00ff\7\37\2\2\u00fe"+
		"\u00fc\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2"+
		"\2\2\u0101\35\3\2\2\2\u0102\u0100\3\2\2\2\u0103\u0104\7\6\2\2\u0104\u0109"+
		"\7\37\2\2\u0105\u0106\7\17\2\2\u0106\u0108\7\37\2\2\u0107\u0105\3\2\2"+
		"\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010c"+
		"\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010d\7\b\2\2\u010d\u010e\7\37\2\2"+
		"\u010e\37\3\2\2\2\u010f\u0110\7\5\2\2\u0110\u0115\5\"\22\2\u0111\u0112"+
		"\7\16\2\2\u0112\u0114\5\"\22\2\u0113\u0111\3\2\2\2\u0114\u0117\3\2\2\2"+
		"\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0118\3\2\2\2\u0117\u0115"+
		"\3\2\2\2\u0118\u0119\7\n\2\2\u0119!\3\2\2\2\u011a\u011f\7\37\2\2\u011b"+
		"\u011c\7\17\2\2\u011c\u011e\7\37\2\2\u011d\u011b\3\2\2\2\u011e\u0121\3"+
		"\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120#\3\2\2\2\u0121\u011f"+
		"\3\2\2\2\u0122\u0123\7\22\2\2\u0123\u0126\7\34\2\2\u0124\u0127\7!\2\2"+
		"\u0125\u0127\7\f\2\2\u0126\u0124\3\2\2\2\u0126\u0125\3\2\2\2\u0127\u0128"+
		"\3\2\2\2\u0128\u0132\7\23\2\2\u0129\u012c\7\22\2\2\u012a\u012d\7!\2\2"+
		"\u012b\u012d\7\f\2\2\u012c\u012a\3\2\2\2\u012c\u012b\3\2\2\2\u012d\u012e"+
		"\3\2\2\2\u012e\u0132\7\23\2\2\u012f\u0130\7\22\2\2\u0130\u0132\7\23\2"+
		"\2\u0131\u0122\3\2\2\2\u0131\u0129\3\2\2\2\u0131\u012f\3\2\2\2\u0132%"+
		"\3\2\2\2\u0133\u0134\7\32\2\2\u0134\u0135\t\3\2\2\u0135\'\3\2\2\2\u0136"+
		"\u0137\7\24\2\2\u0137\u0138\7\37\2\2\u0138\u0139\7\21\2\2\u0139\u0143"+
		"\5*\26\2\u013a\u013b\7\24\2\2\u013b\u013c\7\37\2\2\u013c\u013d\7\17\2"+
		"\2\u013d\u013e\7\37\2\2\u013e\u013f\7\21\2\2\u013f\u0143\5*\26\2\u0140"+
		"\u0141\7\24\2\2\u0141\u0143\7\37\2\2\u0142\u0136\3\2\2\2\u0142\u013a\3"+
		"\2\2\2\u0142\u0140\3\2\2\2\u0143)\3\2\2\2\u0144\u0149\5.\30\2\u0145\u0149"+
		"\5,\27\2\u0146\u0149\5\60\31\2\u0147\u0149\t\4\2\2\u0148\u0144\3\2\2\2"+
		"\u0148\u0145\3\2\2\2\u0148\u0146\3\2\2\2\u0148\u0147\3\2\2\2\u0149+\3"+
		"\2\2\2\u014a\u014b\7\27\2\2\u014b\u014c\7\37\2\2\u014c-\3\2\2\2\u014d"+
		"\u014e\7\22\2\2\u014e\u0153\5*\26\2\u014f\u0150\7\16\2\2\u0150\u0152\5"+
		"*\26\2\u0151\u014f\3\2\2\2\u0152\u0155\3\2\2\2\u0153\u0151\3\2\2\2\u0153"+
		"\u0154\3\2\2\2\u0154\u0156\3\2\2\2\u0155\u0153\3\2\2\2\u0156\u0157\7\23"+
		"\2\2\u0157/\3\2\2\2\u0158\u0159\7\5\2\2\u0159\u015e\5\62\32\2\u015a\u015b"+
		"\7\16\2\2\u015b\u015d\5\62\32\2\u015c\u015a\3\2\2\2\u015d\u0160\3\2\2"+
		"\2\u015e\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0161\3\2\2\2\u0160\u015e"+
		"\3\2\2\2\u0161\u0162\7\n\2\2\u0162\61\3\2\2\2\u0163\u0164\7\37\2\2\u0164"+
		"\u0165\7\21\2\2\u0165\u0166\5*\26\2\u0166\63\3\2\2\2(8<DOX]ejtv\177\u0087"+
		"\u0089\u0092\u009b\u00b3\u00c3\u00c8\u00cf\u00d2\u00d7\u00db\u00e1\u00e4"+
		"\u00ee\u00f4\u00f6\u0100\u0109\u0115\u011f\u0126\u012c\u0131\u0142\u0148"+
		"\u0153\u015e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}