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
		T__21=1, T__20=2, T__19=3, T__18=4, T__17=5, T__16=6, T__15=7, T__14=8, 
		T__13=9, T__12=10, T__11=11, T__10=12, T__9=13, T__8=14, T__7=15, T__6=16, 
		T__5=17, T__4=18, T__3=19, T__2=20, T__1=21, T__0=22, DEFAULT=23, KEY=24, 
		ASSOCIATION_MIN=25, ASSOCIATION=26, BOOLEAN=27, ID=28, SEMICOLUMN=29, 
		INTEGER=30, DECIMAL=31, LOCAL_TIME=32, LOCAL_DATE=33, STRING=34, TYPE_OF=35, 
		NULL=36, NOT_NULL=37, NOT=38, WS=39;
	public static final String[] tokenNames = {
		"<INVALID>", "'as'", "'using'", "'{'", "'on'", "'::'", "'='", "'entity'", 
		"'}'", "'('", "'*'", "'context'", "','", "'.'", "'to'", "':'", "'['", 
		"']'", "'@'", "'type'", "'namespace'", "'#'", "')'", "'default'", "'key'", 
		"ASSOCIATION_MIN", "'Association'", "BOOLEAN", "ID", "';'", "INTEGER", 
		"DECIMAL", "LOCAL_TIME", "LOCAL_DATE", "STRING", "TYPE_OF", "'null'", 
		"NOT_NULL", "'not'", "WS"
	};
	public static final int
		RULE_cdsFile = 0, RULE_namespaceRule = 1, RULE_usingRule = 2, RULE_contextRule = 3, 
		RULE_entityRule = 4, RULE_structuredDataTypeRule = 5, RULE_dataTypeRule = 6, 
		RULE_fieldDeclRule = 7, RULE_typeAssignRule = 8, RULE_elementDeclRule = 9, 
		RULE_elementConstraints = 10, RULE_association = 11, RULE_associationTarget = 12, 
		RULE_unmanagedForeignKey = 13, RULE_managedForeignKeys = 14, RULE_foreignKey = 15, 
		RULE_cardinality = 16, RULE_defaultValue = 17, RULE_annotationRule = 18, 
		RULE_annValue = 19, RULE_enumRule = 20, RULE_arrRule = 21, RULE_obj = 22, 
		RULE_keyValue = 23;
	public static final String[] ruleNames = {
		"cdsFile", "namespaceRule", "usingRule", "contextRule", "entityRule", 
		"structuredDataTypeRule", "dataTypeRule", "fieldDeclRule", "typeAssignRule", 
		"elementDeclRule", "elementConstraints", "association", "associationTarget", 
		"unmanagedForeignKey", "managedForeignKeys", "foreignKey", "cardinality", 
		"defaultValue", "annotationRule", "annValue", "enumRule", "arrRule", "obj", 
		"keyValue"
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
		public ContextRuleContext contextRule() {
			return getRuleContext(ContextRuleContext.class,0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitCdsFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CdsFileContext cdsFile() throws RecognitionException {
		CdsFileContext _localctx = new CdsFileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_cdsFile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); namespaceRule();
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__20) {
				{
				{
				setState(49); usingRule();
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			_la = _input.LA(1);
			if (_la==T__11 || _la==T__4) {
				{
				setState(55); contextRule();
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitNamespaceRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceRuleContext namespaceRule() throws RecognitionException {
		NamespaceRuleContext _localctx = new NamespaceRuleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_namespaceRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58); match(T__2);
			setState(59); ((NamespaceRuleContext)_localctx).ID = match(ID);
			((NamespaceRuleContext)_localctx).members.add(((NamespaceRuleContext)_localctx).ID);
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(60); match(T__9);
				setState(61); ((NamespaceRuleContext)_localctx).ID = match(ID);
				((NamespaceRuleContext)_localctx).members.add(((NamespaceRuleContext)_localctx).ID);
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67); match(SEMICOLUMN);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitUsingRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UsingRuleContext usingRule() throws RecognitionException {
		UsingRuleContext _localctx = new UsingRuleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_usingRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); match(T__20);
			setState(70); ((UsingRuleContext)_localctx).ID = match(ID);
			((UsingRuleContext)_localctx).pack.add(((UsingRuleContext)_localctx).ID);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(71); match(T__9);
				setState(72); ((UsingRuleContext)_localctx).ID = match(ID);
				((UsingRuleContext)_localctx).pack.add(((UsingRuleContext)_localctx).ID);
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(78); match(T__17);
			setState(79); ((UsingRuleContext)_localctx).ID = match(ID);
			((UsingRuleContext)_localctx).members.add(((UsingRuleContext)_localctx).ID);
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(80); match(T__9);
				setState(81); ((UsingRuleContext)_localctx).ID = match(ID);
				((UsingRuleContext)_localctx).members.add(((UsingRuleContext)_localctx).ID);
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(87); match(T__21);
				setState(88); ((UsingRuleContext)_localctx).alias = match(ID);
				}
			}

			setState(91); match(SEMICOLUMN);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitContextRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContextRuleContext contextRule() throws RecognitionException {
		ContextRuleContext _localctx = new ContextRuleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_contextRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(93); annotationRule();
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99); match(T__11);
			setState(100); match(ID);
			setState(101); match(T__19);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__11) | (1L << T__4) | (1L << T__3))) != 0)) {
				{
				setState(106);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(102); contextRule();
					}
					break;

				case 2:
					{
					setState(103); entityRule();
					}
					break;

				case 3:
					{
					setState(104); structuredDataTypeRule();
					}
					break;

				case 4:
					{
					setState(105); dataTypeRule();
					}
					break;
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111); match(T__14);
			setState(112); match(SEMICOLUMN);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitEntityRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntityRuleContext entityRule() throws RecognitionException {
		EntityRuleContext _localctx = new EntityRuleContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_entityRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(114); annotationRule();
				}
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(120); match(T__15);
			setState(121); match(ID);
			setState(122); match(T__19);
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << KEY) | (1L << ID))) != 0)) {
				{
				setState(125);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(123); association();
					}
					break;

				case 2:
					{
					setState(124); elementDeclRule();
					}
					break;
				}
				}
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(130); match(T__14);
			setState(131); match(SEMICOLUMN);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitStructuredDataTypeRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructuredDataTypeRuleContext structuredDataTypeRule() throws RecognitionException {
		StructuredDataTypeRuleContext _localctx = new StructuredDataTypeRuleContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_structuredDataTypeRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(133); annotationRule();
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(139); match(T__3);
			setState(140); match(ID);
			setState(141); match(T__19);
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(142); fieldDeclRule();
				}
				}
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(148); match(T__14);
			setState(149); match(SEMICOLUMN);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitDataTypeRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataTypeRuleContext dataTypeRule() throws RecognitionException {
		DataTypeRuleContext _localctx = new DataTypeRuleContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dataTypeRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151); match(T__3);
			setState(152); match(ID);
			setState(153); match(T__7);
			setState(154); typeAssignRule();
			setState(155); match(SEMICOLUMN);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitFieldDeclRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldDeclRuleContext fieldDeclRule() throws RecognitionException {
		FieldDeclRuleContext _localctx = new FieldDeclRuleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_fieldDeclRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); match(ID);
			setState(158); match(T__7);
			setState(159); typeAssignRule();
			setState(160); match(SEMICOLUMN);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitAssignBuiltInTypeWithArgs(this);
			else return visitor.visitChildren(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitAssignType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeAssignRuleContext typeAssignRule() throws RecognitionException {
		TypeAssignRuleContext _localctx = new TypeAssignRuleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typeAssignRule);
		int _la;
		try {
			setState(184);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new AssignBuiltInTypeWithArgsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(162); ((AssignBuiltInTypeWithArgsContext)_localctx).ref = match(ID);
				setState(163); match(T__13);
				setState(164); ((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
				((AssignBuiltInTypeWithArgsContext)_localctx).args.add(((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER);
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__10) {
					{
					{
					setState(165); match(T__10);
					setState(166); ((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
					((AssignBuiltInTypeWithArgsContext)_localctx).args.add(((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER);
					}
					}
					setState(171);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(172); match(T__0);
				}
				break;

			case 2:
				_localctx = new AssignTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(174);
				_la = _input.LA(1);
				if (_la==TYPE_OF) {
					{
					setState(173); match(TYPE_OF);
					}
				}

				setState(176); ((AssignTypeContext)_localctx).ID = match(ID);
				((AssignTypeContext)_localctx).pathSubMembers.add(((AssignTypeContext)_localctx).ID);
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(177); match(T__9);
					setState(178); ((AssignTypeContext)_localctx).ID = match(ID);
					((AssignTypeContext)_localctx).pathSubMembers.add(((AssignTypeContext)_localctx).ID);
					}
					}
					setState(183);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitElementDeclRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementDeclRuleContext elementDeclRule() throws RecognitionException {
		ElementDeclRuleContext _localctx = new ElementDeclRuleContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_elementDeclRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(186); annotationRule();
				}
				}
				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(193);
			_la = _input.LA(1);
			if (_la==KEY) {
				{
				setState(192); ((ElementDeclRuleContext)_localctx).key = match(KEY);
				}
			}

			setState(195); match(ID);
			setState(196); match(T__7);
			setState(197); typeAssignRule();
			setState(199);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(198); defaultValue();
				}
			}

			setState(202);
			_la = _input.LA(1);
			if (_la==NULL || _la==NOT_NULL) {
				{
				setState(201); elementConstraints();
				}
			}

			setState(204); match(SEMICOLUMN);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitElementConstraints(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementConstraintsContext elementConstraints() throws RecognitionException {
		ElementConstraintsContext _localctx = new ElementConstraintsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_elementConstraints);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitAssociation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssociationContext association() throws RecognitionException {
		AssociationContext _localctx = new AssociationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_association);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208); match(ID);
			setState(209); match(T__7);
			setState(210); match(ASSOCIATION);
			setState(212);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(211); cardinality();
				}
			}

			setState(214); match(T__8);
			setState(215); associationTarget();
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__19 || _la==T__18) {
				{
				setState(218);
				switch (_input.LA(1)) {
				case T__19:
					{
					setState(216); managedForeignKeys();
					}
					break;
				case T__18:
					{
					setState(217); unmanagedForeignKey();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(223); match(SEMICOLUMN);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitAssociationTarget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssociationTargetContext associationTarget() throws RecognitionException {
		AssociationTargetContext _localctx = new AssociationTargetContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_associationTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225); ((AssociationTargetContext)_localctx).ID = match(ID);
			((AssociationTargetContext)_localctx).pathSubMembers.add(((AssociationTargetContext)_localctx).ID);
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(226); match(T__9);
				setState(227); ((AssociationTargetContext)_localctx).ID = match(ID);
				((AssociationTargetContext)_localctx).pathSubMembers.add(((AssociationTargetContext)_localctx).ID);
				}
				}
				setState(232);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitUnmanagedForeignKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnmanagedForeignKeyContext unmanagedForeignKey() throws RecognitionException {
		UnmanagedForeignKeyContext _localctx = new UnmanagedForeignKeyContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_unmanagedForeignKey);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233); match(T__18);
			setState(234); ((UnmanagedForeignKeyContext)_localctx).ID = match(ID);
			((UnmanagedForeignKeyContext)_localctx).pathSubMembers.add(((UnmanagedForeignKeyContext)_localctx).ID);
			setState(239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(235); match(T__9);
				setState(236); ((UnmanagedForeignKeyContext)_localctx).ID = match(ID);
				((UnmanagedForeignKeyContext)_localctx).pathSubMembers.add(((UnmanagedForeignKeyContext)_localctx).ID);
				}
				}
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(242); match(T__16);
			setState(243); ((UnmanagedForeignKeyContext)_localctx).source = match(ID);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitManagedForeignKeys(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ManagedForeignKeysContext managedForeignKeys() throws RecognitionException {
		ManagedForeignKeysContext _localctx = new ManagedForeignKeysContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_managedForeignKeys);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245); match(T__19);
			setState(246); foreignKey();
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(247); match(T__10);
				setState(248); foreignKey();
				}
				}
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(254); match(T__14);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitForeignKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForeignKeyContext foreignKey() throws RecognitionException {
		ForeignKeyContext _localctx = new ForeignKeyContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_foreignKey);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256); ((ForeignKeyContext)_localctx).ID = match(ID);
			((ForeignKeyContext)_localctx).pathSubMembers.add(((ForeignKeyContext)_localctx).ID);
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(257); match(T__9);
				setState(258); ((ForeignKeyContext)_localctx).ID = match(ID);
				((ForeignKeyContext)_localctx).pathSubMembers.add(((ForeignKeyContext)_localctx).ID);
				}
				}
				setState(263);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitNoCardinality(this);
			else return visitor.visitChildren(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitMaxCardinality(this);
			else return visitor.visitChildren(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitMinMaxCardinality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CardinalityContext cardinality() throws RecognitionException {
		CardinalityContext _localctx = new CardinalityContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_cardinality);
		try {
			setState(279);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				_localctx = new MinMaxCardinalityContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(264); match(T__6);
				setState(265); match(ASSOCIATION_MIN);
				setState(268);
				switch (_input.LA(1)) {
				case INTEGER:
					{
					setState(266); ((MinMaxCardinalityContext)_localctx).max = match(INTEGER);
					}
					break;
				case T__12:
					{
					setState(267); ((MinMaxCardinalityContext)_localctx).many = match(T__12);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(270); match(T__5);
				}
				break;

			case 2:
				_localctx = new MaxCardinalityContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(271); match(T__6);
				setState(274);
				switch (_input.LA(1)) {
				case INTEGER:
					{
					setState(272); ((MaxCardinalityContext)_localctx).max = match(INTEGER);
					}
					break;
				case T__12:
					{
					setState(273); ((MaxCardinalityContext)_localctx).many = match(T__12);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(276); match(T__5);
				}
				break;

			case 3:
				_localctx = new NoCardinalityContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(277); match(T__6);
				setState(278); match(T__5);
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
		public TerminalNode STRING() { return getToken(CdsParser.STRING, 0); }
		public TerminalNode BOOLEAN() { return getToken(CdsParser.BOOLEAN, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitDefaultValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_defaultValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281); match(DEFAULT);
			setState(282);
			((DefaultValueContext)_localctx).value = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==BOOLEAN || _la==STRING) ) {
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitAnnPropertyRule(this);
			else return visitor.visitChildren(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitAnnObjectRule(this);
			else return visitor.visitChildren(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitAnnMarkerRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationRuleContext annotationRule() throws RecognitionException {
		AnnotationRuleContext _localctx = new AnnotationRuleContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_annotationRule);
		try {
			setState(296);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				_localctx = new AnnObjectRuleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(284); match(T__4);
				setState(285); match(ID);
				setState(286); match(T__7);
				setState(287); annValue();
				}
				break;

			case 2:
				_localctx = new AnnPropertyRuleContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(288); match(T__4);
				setState(289); ((AnnPropertyRuleContext)_localctx).annId = match(ID);
				setState(290); match(T__9);
				setState(291); ((AnnPropertyRuleContext)_localctx).prop = match(ID);
				setState(292); match(T__7);
				setState(293); annValue();
				}
				break;

			case 3:
				_localctx = new AnnMarkerRuleContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(294); match(T__4);
				setState(295); match(ID);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitAnnValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnValueContext annValue() throws RecognitionException {
		AnnValueContext _localctx = new AnnValueContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_annValue);
		int _la;
		try {
			setState(302);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(298); arrRule();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(299); enumRule();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 3);
				{
				setState(300); obj();
				}
				break;
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(301);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitEnumRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumRuleContext enumRule() throws RecognitionException {
		EnumRuleContext _localctx = new EnumRuleContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_enumRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304); match(T__1);
			setState(305); match(ID);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitArrRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrRuleContext arrRule() throws RecognitionException {
		ArrRuleContext _localctx = new ArrRuleContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_arrRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307); match(T__6);
			setState(308); annValue();
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(309); match(T__10);
				setState(310); annValue();
				}
				}
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(316); match(T__5);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitObj(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjContext obj() throws RecognitionException {
		ObjContext _localctx = new ObjContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_obj);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318); match(T__19);
			setState(319); keyValue();
			setState(324);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(320); match(T__10);
				setState(321); keyValue();
				}
				}
				setState(326);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(327); match(T__14);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CdsVisitor ) return ((CdsVisitor<? extends T>)visitor).visitKeyValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyValueContext keyValue() throws RecognitionException {
		KeyValueContext _localctx = new KeyValueContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_keyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329); match(ID);
			setState(330); match(T__7);
			setState(331); annValue();
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3)\u0150\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\3\2\7\2\65\n\2\f\2\16\28\13\2\3\2\5\2;\n\2\3\3\3\3\3\3\3\3\7\3A\n"+
		"\3\f\3\16\3D\13\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4L\n\4\f\4\16\4O\13\4\3\4"+
		"\3\4\3\4\3\4\7\4U\n\4\f\4\16\4X\13\4\3\4\3\4\5\4\\\n\4\3\4\3\4\3\5\7\5"+
		"a\n\5\f\5\16\5d\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5m\n\5\f\5\16\5p\13"+
		"\5\3\5\3\5\3\5\3\6\7\6v\n\6\f\6\16\6y\13\6\3\6\3\6\3\6\3\6\3\6\7\6\u0080"+
		"\n\6\f\6\16\6\u0083\13\6\3\6\3\6\3\6\3\7\7\7\u0089\n\7\f\7\16\7\u008c"+
		"\13\7\3\7\3\7\3\7\3\7\7\7\u0092\n\7\f\7\16\7\u0095\13\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\7\n\u00aa"+
		"\n\n\f\n\16\n\u00ad\13\n\3\n\3\n\5\n\u00b1\n\n\3\n\3\n\3\n\7\n\u00b6\n"+
		"\n\f\n\16\n\u00b9\13\n\5\n\u00bb\n\n\3\13\7\13\u00be\n\13\f\13\16\13\u00c1"+
		"\13\13\3\13\5\13\u00c4\n\13\3\13\3\13\3\13\3\13\5\13\u00ca\n\13\3\13\5"+
		"\13\u00cd\n\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\5\r\u00d7\n\r\3\r\3\r"+
		"\3\r\3\r\7\r\u00dd\n\r\f\r\16\r\u00e0\13\r\3\r\3\r\3\16\3\16\3\16\7\16"+
		"\u00e7\n\16\f\16\16\16\u00ea\13\16\3\17\3\17\3\17\3\17\7\17\u00f0\n\17"+
		"\f\17\16\17\u00f3\13\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u00fc"+
		"\n\20\f\20\16\20\u00ff\13\20\3\20\3\20\3\21\3\21\3\21\7\21\u0106\n\21"+
		"\f\21\16\21\u0109\13\21\3\22\3\22\3\22\3\22\5\22\u010f\n\22\3\22\3\22"+
		"\3\22\3\22\5\22\u0115\n\22\3\22\3\22\3\22\5\22\u011a\n\22\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u012b"+
		"\n\24\3\25\3\25\3\25\3\25\5\25\u0131\n\25\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\7\27\u013a\n\27\f\27\16\27\u013d\13\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\7\30\u0145\n\30\f\30\16\30\u0148\13\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\2\2\32\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\2"+
		"\4\3\2&\'\4\2\35\35$$\u0161\2\62\3\2\2\2\4<\3\2\2\2\6G\3\2\2\2\bb\3\2"+
		"\2\2\nw\3\2\2\2\f\u008a\3\2\2\2\16\u0099\3\2\2\2\20\u009f\3\2\2\2\22\u00ba"+
		"\3\2\2\2\24\u00bf\3\2\2\2\26\u00d0\3\2\2\2\30\u00d2\3\2\2\2\32\u00e3\3"+
		"\2\2\2\34\u00eb\3\2\2\2\36\u00f7\3\2\2\2 \u0102\3\2\2\2\"\u0119\3\2\2"+
		"\2$\u011b\3\2\2\2&\u012a\3\2\2\2(\u0130\3\2\2\2*\u0132\3\2\2\2,\u0135"+
		"\3\2\2\2.\u0140\3\2\2\2\60\u014b\3\2\2\2\62\66\5\4\3\2\63\65\5\6\4\2\64"+
		"\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67:\3\2\2\28\66\3"+
		"\2\2\29;\5\b\5\2:9\3\2\2\2:;\3\2\2\2;\3\3\2\2\2<=\7\26\2\2=B\7\36\2\2"+
		">?\7\17\2\2?A\7\36\2\2@>\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2CE\3\2\2"+
		"\2DB\3\2\2\2EF\7\37\2\2F\5\3\2\2\2GH\7\4\2\2HM\7\36\2\2IJ\7\17\2\2JL\7"+
		"\36\2\2KI\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2NP\3\2\2\2OM\3\2\2\2PQ"+
		"\7\7\2\2QV\7\36\2\2RS\7\17\2\2SU\7\36\2\2TR\3\2\2\2UX\3\2\2\2VT\3\2\2"+
		"\2VW\3\2\2\2W[\3\2\2\2XV\3\2\2\2YZ\7\3\2\2Z\\\7\36\2\2[Y\3\2\2\2[\\\3"+
		"\2\2\2\\]\3\2\2\2]^\7\37\2\2^\7\3\2\2\2_a\5&\24\2`_\3\2\2\2ad\3\2\2\2"+
		"b`\3\2\2\2bc\3\2\2\2ce\3\2\2\2db\3\2\2\2ef\7\r\2\2fg\7\36\2\2gn\7\5\2"+
		"\2hm\5\b\5\2im\5\n\6\2jm\5\f\7\2km\5\16\b\2lh\3\2\2\2li\3\2\2\2lj\3\2"+
		"\2\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2pn\3\2\2\2qr\7\n"+
		"\2\2rs\7\37\2\2s\t\3\2\2\2tv\5&\24\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3"+
		"\2\2\2xz\3\2\2\2yw\3\2\2\2z{\7\t\2\2{|\7\36\2\2|\u0081\7\5\2\2}\u0080"+
		"\5\30\r\2~\u0080\5\24\13\2\177}\3\2\2\2\177~\3\2\2\2\u0080\u0083\3\2\2"+
		"\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083\u0081"+
		"\3\2\2\2\u0084\u0085\7\n\2\2\u0085\u0086\7\37\2\2\u0086\13\3\2\2\2\u0087"+
		"\u0089\5&\24\2\u0088\u0087\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2"+
		"\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d"+
		"\u008e\7\25\2\2\u008e\u008f\7\36\2\2\u008f\u0093\7\5\2\2\u0090\u0092\5"+
		"\20\t\2\u0091\u0090\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0097\7\n"+
		"\2\2\u0097\u0098\7\37\2\2\u0098\r\3\2\2\2\u0099\u009a\7\25\2\2\u009a\u009b"+
		"\7\36\2\2\u009b\u009c\7\21\2\2\u009c\u009d\5\22\n\2\u009d\u009e\7\37\2"+
		"\2\u009e\17\3\2\2\2\u009f\u00a0\7\36\2\2\u00a0\u00a1\7\21\2\2\u00a1\u00a2"+
		"\5\22\n\2\u00a2\u00a3\7\37\2\2\u00a3\21\3\2\2\2\u00a4\u00a5\7\36\2\2\u00a5"+
		"\u00a6\7\13\2\2\u00a6\u00ab\7 \2\2\u00a7\u00a8\7\16\2\2\u00a8\u00aa\7"+
		" \2\2\u00a9\u00a7\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab"+
		"\u00ac\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00bb\7\30"+
		"\2\2\u00af\u00b1\7%\2\2\u00b0\u00af\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\u00b2\3\2\2\2\u00b2\u00b7\7\36\2\2\u00b3\u00b4\7\17\2\2\u00b4\u00b6\7"+
		"\36\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7"+
		"\u00b8\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00a4\3\2"+
		"\2\2\u00ba\u00b0\3\2\2\2\u00bb\23\3\2\2\2\u00bc\u00be\5&\24\2\u00bd\u00bc"+
		"\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0"+
		"\u00c3\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2\u00c4\7\32\2\2\u00c3\u00c2\3"+
		"\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\7\36\2\2\u00c6"+
		"\u00c7\7\21\2\2\u00c7\u00c9\5\22\n\2\u00c8\u00ca\5$\23\2\u00c9\u00c8\3"+
		"\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00cd\5\26\f\2\u00cc"+
		"\u00cb\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\7\37"+
		"\2\2\u00cf\25\3\2\2\2\u00d0\u00d1\t\2\2\2\u00d1\27\3\2\2\2\u00d2\u00d3"+
		"\7\36\2\2\u00d3\u00d4\7\21\2\2\u00d4\u00d6\7\34\2\2\u00d5\u00d7\5\"\22"+
		"\2\u00d6\u00d5\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9"+
		"\7\20\2\2\u00d9\u00de\5\32\16\2\u00da\u00dd\5\36\20\2\u00db\u00dd\5\34"+
		"\17\2\u00dc\u00da\3\2\2\2\u00dc\u00db\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de"+
		"\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e1\3\2\2\2\u00e0\u00de\3\2"+
		"\2\2\u00e1\u00e2\7\37\2\2\u00e2\31\3\2\2\2\u00e3\u00e8\7\36\2\2\u00e4"+
		"\u00e5\7\17\2\2\u00e5\u00e7\7\36\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00ea\3"+
		"\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\33\3\2\2\2\u00ea"+
		"\u00e8\3\2\2\2\u00eb\u00ec\7\6\2\2\u00ec\u00f1\7\36\2\2\u00ed\u00ee\7"+
		"\17\2\2\u00ee\u00f0\7\36\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1"+
		"\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f1\3\2"+
		"\2\2\u00f4\u00f5\7\b\2\2\u00f5\u00f6\7\36\2\2\u00f6\35\3\2\2\2\u00f7\u00f8"+
		"\7\5\2\2\u00f8\u00fd\5 \21\2\u00f9\u00fa\7\16\2\2\u00fa\u00fc\5 \21\2"+
		"\u00fb\u00f9\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe"+
		"\3\2\2\2\u00fe\u0100\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100\u0101\7\n\2\2\u0101"+
		"\37\3\2\2\2\u0102\u0107\7\36\2\2\u0103\u0104\7\17\2\2\u0104\u0106\7\36"+
		"\2\2\u0105\u0103\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108!\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u010b\7\22\2\2"+
		"\u010b\u010e\7\33\2\2\u010c\u010f\7 \2\2\u010d\u010f\7\f\2\2\u010e\u010c"+
		"\3\2\2\2\u010e\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u011a\7\23\2\2"+
		"\u0111\u0114\7\22\2\2\u0112\u0115\7 \2\2\u0113\u0115\7\f\2\2\u0114\u0112"+
		"\3\2\2\2\u0114\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u011a\7\23\2\2"+
		"\u0117\u0118\7\22\2\2\u0118\u011a\7\23\2\2\u0119\u010a\3\2\2\2\u0119\u0111"+
		"\3\2\2\2\u0119\u0117\3\2\2\2\u011a#\3\2\2\2\u011b\u011c\7\31\2\2\u011c"+
		"\u011d\t\3\2\2\u011d%\3\2\2\2\u011e\u011f\7\24\2\2\u011f\u0120\7\36\2"+
		"\2\u0120\u0121\7\21\2\2\u0121\u012b\5(\25\2\u0122\u0123\7\24\2\2\u0123"+
		"\u0124\7\36\2\2\u0124\u0125\7\17\2\2\u0125\u0126\7\36\2\2\u0126\u0127"+
		"\7\21\2\2\u0127\u012b\5(\25\2\u0128\u0129\7\24\2\2\u0129\u012b\7\36\2"+
		"\2\u012a\u011e\3\2\2\2\u012a\u0122\3\2\2\2\u012a\u0128\3\2\2\2\u012b\'"+
		"\3\2\2\2\u012c\u0131\5,\27\2\u012d\u0131\5*\26\2\u012e\u0131\5.\30\2\u012f"+
		"\u0131\t\3\2\2\u0130\u012c\3\2\2\2\u0130\u012d\3\2\2\2\u0130\u012e\3\2"+
		"\2\2\u0130\u012f\3\2\2\2\u0131)\3\2\2\2\u0132\u0133\7\27\2\2\u0133\u0134"+
		"\7\36\2\2\u0134+\3\2\2\2\u0135\u0136\7\22\2\2\u0136\u013b\5(\25\2\u0137"+
		"\u0138\7\16\2\2\u0138\u013a\5(\25\2\u0139\u0137\3\2\2\2\u013a\u013d\3"+
		"\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013e\3\2\2\2\u013d"+
		"\u013b\3\2\2\2\u013e\u013f\7\23\2\2\u013f-\3\2\2\2\u0140\u0141\7\5\2\2"+
		"\u0141\u0146\5\60\31\2\u0142\u0143\7\16\2\2\u0143\u0145\5\60\31\2\u0144"+
		"\u0142\3\2\2\2\u0145\u0148\3\2\2\2\u0146\u0144\3\2\2\2\u0146\u0147\3\2"+
		"\2\2\u0147\u0149\3\2\2\2\u0148\u0146\3\2\2\2\u0149\u014a\7\n\2\2\u014a"+
		"/\3\2\2\2\u014b\u014c\7\36\2\2\u014c\u014d\7\21\2\2\u014d\u014e\5(\25"+
		"\2\u014e\61\3\2\2\2&\66:BMV[blnw\177\u0081\u008a\u0093\u00ab\u00b0\u00b7"+
		"\u00ba\u00bf\u00c3\u00c9\u00cc\u00d6\u00dc\u00de\u00e8\u00f1\u00fd\u0107"+
		"\u010e\u0114\u0119\u012a\u0130\u013b\u0146";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}