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
		T__15=1, T__14=2, T__13=3, T__12=4, T__11=5, T__10=6, T__9=7, T__8=8, 
		T__7=9, T__6=10, T__5=11, T__4=12, T__3=13, T__2=14, T__1=15, T__0=16, 
		KEY=17, NAMESPACE=18, AS=19, ENTITY=20, TYPE=21, HANA=22, CONTEXT=23, 
		USING=24, ASSOCIATION=25, TO=26, ON=27, NULL=28, DEFAULT=29, ASSOCIATION_MIN=30, 
		BOOLEAN=31, ID=32, SEMICOLUMN=33, INTEGER=34, DECIMAL=35, LOCAL_TIME=36, 
		LOCAL_DATE=37, UTC_DATE_TIME=38, UTC_TIMESTAMP=39, STRING=40, VARBINARY=41, 
		TYPE_OF=42, WS=43, LINE_COMMENT=44, LINE_COMMENT2=45, A=46, B=47, C=48, 
		D=49, E=50, F=51, G=52, H=53, I=54, J=55, K=56, L=57, M=58, N=59, O=60, 
		P=61, Q=62, R=63, S=64, T=65, U=66, V=67, W=68, X=69, Y=70, Z=71;
	public static final String[] tokenNames = {
		"<INVALID>", "':'", "'['", "'{'", "'::'", "']'", "'='", "'}'", "'@'", 
		"'\"'", "'#'", "'('", "')'", "'*'", "','", "'not null'", "'.'", "KEY", 
		"NAMESPACE", "'as'", "'entity'", "'type'", "'hana'", "CONTEXT", "USING", 
		"ASSOCIATION", "'to'", "'on'", "'null'", "DEFAULT", "ASSOCIATION_MIN", 
		"BOOLEAN", "ID", "';'", "INTEGER", "DECIMAL", "LOCAL_TIME", "LOCAL_DATE", 
		"UTC_DATE_TIME", "UTC_TIMESTAMP", "STRING", "VARBINARY", "TYPE_OF", "WS", 
		"LINE_COMMENT", "LINE_COMMENT2", "A", "B", "C", "D", "E", "F", "G", "H", 
		"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", 
		"W", "X", "Y", "Z"
	};
	public static final int
		RULE_cdsFile = 0, RULE_namespaceRule = 1, RULE_usingRule = 2, RULE_topLevelSymbol = 3, 
		RULE_contextRule = 4, RULE_entityRule = 5, RULE_structuredDataTypeRule = 6, 
		RULE_dataTypeRule = 7, RULE_fieldDeclRule = 8, RULE_typeAssignRule = 9, 
		RULE_elementDeclRule = 10, RULE_elementDetails = 11, RULE_elementConstraints = 12, 
		RULE_association = 13, RULE_associationTarget = 14, RULE_unmanagedForeignKey = 15, 
		RULE_managedForeignKeys = 16, RULE_foreignKey = 17, RULE_cardinality = 18, 
		RULE_defaultValue = 19, RULE_annotationRule = 20, RULE_annValue = 21, 
		RULE_enumRule = 22, RULE_arrRule = 23, RULE_obj = 24, RULE_keyValue = 25;
	public static final String[] ruleNames = {
		"cdsFile", "namespaceRule", "usingRule", "topLevelSymbol", "contextRule", 
		"entityRule", "structuredDataTypeRule", "dataTypeRule", "fieldDeclRule", 
		"typeAssignRule", "elementDeclRule", "elementDetails", "elementConstraints", 
		"association", "associationTarget", "unmanagedForeignKey", "managedForeignKeys", 
		"foreignKey", "cardinality", "defaultValue", "annotationRule", "annValue", 
		"enumRule", "arrRule", "obj", "keyValue"
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
			setState(52); namespaceRule();
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==USING) {
				{
				{
				setState(53); usingRule();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << ENTITY) | (1L << TYPE) | (1L << CONTEXT))) != 0)) {
				{
				setState(59); topLevelSymbol();
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
		public TerminalNode NAMESPACE() { return getToken(CdsParser.NAMESPACE, 0); }
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
			setState(62); match(NAMESPACE);
			setState(63); ((NamespaceRuleContext)_localctx).ID = match(ID);
			((NamespaceRuleContext)_localctx).members.add(((NamespaceRuleContext)_localctx).ID);
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(64); match(T__0);
				setState(65); ((NamespaceRuleContext)_localctx).ID = match(ID);
				((NamespaceRuleContext)_localctx).members.add(((NamespaceRuleContext)_localctx).ID);
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71); match(SEMICOLUMN);
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
		public TerminalNode USING() { return getToken(CdsParser.USING, 0); }
		public TerminalNode AS() { return getToken(CdsParser.AS, 0); }
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
			setState(73); match(USING);
			setState(74); ((UsingRuleContext)_localctx).ID = match(ID);
			((UsingRuleContext)_localctx).pack.add(((UsingRuleContext)_localctx).ID);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(75); match(T__0);
				setState(76); ((UsingRuleContext)_localctx).ID = match(ID);
				((UsingRuleContext)_localctx).pack.add(((UsingRuleContext)_localctx).ID);
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(82); match(T__12);
			setState(83); ((UsingRuleContext)_localctx).ID = match(ID);
			((UsingRuleContext)_localctx).members.add(((UsingRuleContext)_localctx).ID);
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(84); match(T__0);
				setState(85); ((UsingRuleContext)_localctx).ID = match(ID);
				((UsingRuleContext)_localctx).members.add(((UsingRuleContext)_localctx).ID);
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(93);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(91); match(AS);
				setState(92); ((UsingRuleContext)_localctx).alias = match(ID);
				}
			}

			setState(95); match(SEMICOLUMN);
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
			setState(101);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(97); contextRule();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(98); entityRule();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(99); structuredDataTypeRule();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(100); dataTypeRule();
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
		public TerminalNode CONTEXT() { return getToken(CdsParser.CONTEXT, 0); }
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
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(103); annotationRule();
				}
				}
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(109); match(CONTEXT);
			setState(110); match(ID);
			setState(111); match(T__13);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << ENTITY) | (1L << TYPE) | (1L << CONTEXT))) != 0)) {
				{
				setState(116);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(112); contextRule();
					}
					break;

				case 2:
					{
					setState(113); entityRule();
					}
					break;

				case 3:
					{
					setState(114); structuredDataTypeRule();
					}
					break;

				case 4:
					{
					setState(115); dataTypeRule();
					}
					break;
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(121); match(T__9);
			setState(122); match(SEMICOLUMN);
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
		public TerminalNode ENTITY() { return getToken(CdsParser.ENTITY, 0); }
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
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(124); annotationRule();
				}
				}
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(130); match(ENTITY);
			setState(131); match(ID);
			setState(132); match(T__13);
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << KEY) | (1L << ID))) != 0)) {
				{
				setState(135);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(133); association();
					}
					break;

				case 2:
					{
					setState(134); elementDeclRule();
					}
					break;
				}
				}
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(140); match(T__9);
			setState(142);
			_la = _input.LA(1);
			if (_la==SEMICOLUMN) {
				{
				setState(141); match(SEMICOLUMN);
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

	public static class StructuredDataTypeRuleContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public FieldDeclRuleContext fieldDeclRule(int i) {
			return getRuleContext(FieldDeclRuleContext.class,i);
		}
		public TerminalNode TYPE() { return getToken(CdsParser.TYPE, 0); }
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
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(144); annotationRule();
				}
				}
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(150); match(TYPE);
			setState(151); match(ID);
			setState(152); match(T__13);
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7 || _la==ID) {
				{
				{
				setState(153); fieldDeclRule();
				}
				}
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(159); match(T__9);
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

	public static class DataTypeRuleContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(CdsParser.TYPE, 0); }
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
			setState(162); match(TYPE);
			setState(163); match(ID);
			setState(164); match(T__15);
			setState(165); typeAssignRule();
			setState(166); match(SEMICOLUMN);
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
			setState(172);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(168); match(ID);
				}
				break;
			case T__7:
				{
				setState(169); match(T__7);
				setState(170); match(ID);
				setState(171); match(T__7);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(174); match(T__15);
			setState(175); typeAssignRule();
			setState(176); match(SEMICOLUMN);
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
		public TerminalNode HANA() { return getToken(CdsParser.HANA, 0); }
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
		public TerminalNode HANA() { return getToken(CdsParser.HANA, 0); }
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
			setState(216);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				_localctx = new AssignBuiltInTypeWithArgsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(178); ((AssignBuiltInTypeWithArgsContext)_localctx).ref = match(ID);
				setState(179); match(T__5);
				setState(180); ((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
				((AssignBuiltInTypeWithArgsContext)_localctx).args.add(((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER);
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(181); match(T__2);
					setState(182); ((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
					((AssignBuiltInTypeWithArgsContext)_localctx).args.add(((AssignBuiltInTypeWithArgsContext)_localctx).INTEGER);
					}
					}
					setState(187);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(188); match(T__4);
				}
				break;

			case 2:
				_localctx = new AssignHanaTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(189); match(HANA);
				setState(190); match(T__0);
				setState(191); ((AssignHanaTypeContext)_localctx).ref = match(ID);
				}
				break;

			case 3:
				_localctx = new AssignHanaTypeWithArgsContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(192); match(HANA);
				setState(193); match(T__0);
				setState(194); ((AssignHanaTypeWithArgsContext)_localctx).ref = match(ID);
				setState(195); match(T__5);
				setState(196); ((AssignHanaTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
				((AssignHanaTypeWithArgsContext)_localctx).args.add(((AssignHanaTypeWithArgsContext)_localctx).INTEGER);
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(197); match(T__2);
					setState(198); ((AssignHanaTypeWithArgsContext)_localctx).INTEGER = match(INTEGER);
					((AssignHanaTypeWithArgsContext)_localctx).args.add(((AssignHanaTypeWithArgsContext)_localctx).INTEGER);
					}
					}
					setState(203);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(204); match(T__4);
				}
				break;

			case 4:
				_localctx = new AssignTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(206);
				_la = _input.LA(1);
				if (_la==TYPE_OF) {
					{
					setState(205); match(TYPE_OF);
					}
				}

				setState(208); ((AssignTypeContext)_localctx).ID = match(ID);
				((AssignTypeContext)_localctx).pathSubMembers.add(((AssignTypeContext)_localctx).ID);
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(209); match(T__0);
					setState(210); ((AssignTypeContext)_localctx).ID = match(ID);
					((AssignTypeContext)_localctx).pathSubMembers.add(((AssignTypeContext)_localctx).ID);
					}
					}
					setState(215);
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
		public TerminalNode KEY() { return getToken(CdsParser.KEY, 0); }
		public TerminalNode ID() { return getToken(CdsParser.ID, 0); }
		public List<ElementDetailsContext> elementDetails() {
			return getRuleContexts(ElementDetailsContext.class);
		}
		public List<AnnotationRuleContext> annotationRule() {
			return getRuleContexts(AnnotationRuleContext.class);
		}
		public ElementDetailsContext elementDetails(int i) {
			return getRuleContext(ElementDetailsContext.class,i);
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
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(218); annotationRule();
				}
				}
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(225);
			_la = _input.LA(1);
			if (_la==KEY) {
				{
				setState(224); ((ElementDeclRuleContext)_localctx).key = match(KEY);
				}
			}

			setState(227); match(ID);
			setState(228); match(T__15);
			setState(229); typeAssignRule();
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << NULL) | (1L << DEFAULT))) != 0)) {
				{
				{
				setState(230); elementDetails();
				}
				}
				setState(235);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(236); match(SEMICOLUMN);
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

	public static class ElementDetailsContext extends ParserRuleContext {
		public ElementConstraintsContext elementConstraints() {
			return getRuleContext(ElementConstraintsContext.class,0);
		}
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public ElementDetailsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementDetails; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).enterElementDetails(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CdsListener ) ((CdsListener)listener).exitElementDetails(this);
		}
	}

	public final ElementDetailsContext elementDetails() throws RecognitionException {
		ElementDetailsContext _localctx = new ElementDetailsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_elementDetails);
		try {
			setState(240);
			switch (_input.LA(1)) {
			case DEFAULT:
				enterOuterAlt(_localctx, 1);
				{
				setState(238); defaultValue();
				}
				break;
			case T__1:
			case NULL:
				enterOuterAlt(_localctx, 2);
				{
				setState(239); elementConstraints();
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

	public static class ElementConstraintsContext extends ParserRuleContext {
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
		enterRule(_localctx, 24, RULE_elementConstraints);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			_la = _input.LA(1);
			if ( !(_la==T__1 || _la==NULL) ) {
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
		public TerminalNode ASSOCIATION() { return getToken(CdsParser.ASSOCIATION, 0); }
		public TerminalNode TO() { return getToken(CdsParser.TO, 0); }
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
		enterRule(_localctx, 26, RULE_association);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244); match(ID);
			setState(245); match(T__15);
			setState(246); match(ASSOCIATION);
			setState(248);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(247); cardinality();
				}
			}

			setState(250); match(TO);
			setState(251); associationTarget();
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13 || _la==ON) {
				{
				setState(254);
				switch (_input.LA(1)) {
				case T__13:
					{
					setState(252); managedForeignKeys();
					}
					break;
				case ON:
					{
					setState(253); unmanagedForeignKey();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(259); match(SEMICOLUMN);
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
		enterRule(_localctx, 28, RULE_associationTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261); ((AssociationTargetContext)_localctx).ID = match(ID);
			((AssociationTargetContext)_localctx).pathSubMembers.add(((AssociationTargetContext)_localctx).ID);
			setState(266);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(262); match(T__0);
				setState(263); ((AssociationTargetContext)_localctx).ID = match(ID);
				((AssociationTargetContext)_localctx).pathSubMembers.add(((AssociationTargetContext)_localctx).ID);
				}
				}
				setState(268);
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
		public TerminalNode ON() { return getToken(CdsParser.ON, 0); }
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
		enterRule(_localctx, 30, RULE_unmanagedForeignKey);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269); match(ON);
			setState(270); ((UnmanagedForeignKeyContext)_localctx).ID = match(ID);
			((UnmanagedForeignKeyContext)_localctx).pathSubMembers.add(((UnmanagedForeignKeyContext)_localctx).ID);
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(271); match(T__0);
				setState(272); ((UnmanagedForeignKeyContext)_localctx).ID = match(ID);
				((UnmanagedForeignKeyContext)_localctx).pathSubMembers.add(((UnmanagedForeignKeyContext)_localctx).ID);
				}
				}
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(278); match(T__10);
			setState(279); ((UnmanagedForeignKeyContext)_localctx).source = match(ID);
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
		enterRule(_localctx, 32, RULE_managedForeignKeys);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281); match(T__13);
			setState(282); foreignKey();
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(283); match(T__2);
				setState(284); foreignKey();
				}
				}
				setState(289);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(290); match(T__9);
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
		enterRule(_localctx, 34, RULE_foreignKey);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292); ((ForeignKeyContext)_localctx).ID = match(ID);
			((ForeignKeyContext)_localctx).pathSubMembers.add(((ForeignKeyContext)_localctx).ID);
			setState(297);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(293); match(T__0);
				setState(294); ((ForeignKeyContext)_localctx).ID = match(ID);
				((ForeignKeyContext)_localctx).pathSubMembers.add(((ForeignKeyContext)_localctx).ID);
				}
				}
				setState(299);
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
		enterRule(_localctx, 36, RULE_cardinality);
		try {
			setState(315);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				_localctx = new MinMaxCardinalityContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(300); match(T__14);
				setState(301); match(ASSOCIATION_MIN);
				setState(304);
				switch (_input.LA(1)) {
				case INTEGER:
					{
					setState(302); ((MinMaxCardinalityContext)_localctx).max = match(INTEGER);
					}
					break;
				case T__3:
					{
					setState(303); ((MinMaxCardinalityContext)_localctx).many = match(T__3);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(306); match(T__11);
				}
				break;

			case 2:
				_localctx = new MaxCardinalityContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(307); match(T__14);
				setState(310);
				switch (_input.LA(1)) {
				case INTEGER:
					{
					setState(308); ((MaxCardinalityContext)_localctx).max = match(INTEGER);
					}
					break;
				case T__3:
					{
					setState(309); ((MaxCardinalityContext)_localctx).many = match(T__3);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(312); match(T__11);
				}
				break;

			case 3:
				_localctx = new NoCardinalityContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(313); match(T__14);
				setState(314); match(T__11);
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
		public TerminalNode NULL() { return getToken(CdsParser.NULL, 0); }
		public TerminalNode UTC_TIMESTAMP() { return getToken(CdsParser.UTC_TIMESTAMP, 0); }
		public TerminalNode VARBINARY() { return getToken(CdsParser.VARBINARY, 0); }
		public TerminalNode STRING() { return getToken(CdsParser.STRING, 0); }
		public TerminalNode DEFAULT() { return getToken(CdsParser.DEFAULT, 0); }
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
		enterRule(_localctx, 38, RULE_defaultValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317); match(DEFAULT);
			setState(318);
			((DefaultValueContext)_localctx).value = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NULL) | (1L << INTEGER) | (1L << DECIMAL) | (1L << LOCAL_TIME) | (1L << LOCAL_DATE) | (1L << UTC_DATE_TIME) | (1L << UTC_TIMESTAMP) | (1L << STRING) | (1L << VARBINARY))) != 0)) ) {
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
		enterRule(_localctx, 40, RULE_annotationRule);
		try {
			setState(332);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				_localctx = new AnnObjectRuleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(320); match(T__8);
				setState(321); match(ID);
				setState(322); match(T__15);
				setState(323); annValue();
				}
				break;

			case 2:
				_localctx = new AnnPropertyRuleContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(324); match(T__8);
				setState(325); ((AnnPropertyRuleContext)_localctx).annId = match(ID);
				setState(326); match(T__0);
				setState(327); ((AnnPropertyRuleContext)_localctx).prop = match(ID);
				setState(328); match(T__15);
				setState(329); annValue();
				}
				break;

			case 3:
				_localctx = new AnnMarkerRuleContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(330); match(T__8);
				setState(331); match(ID);
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
		enterRule(_localctx, 42, RULE_annValue);
		int _la;
		try {
			setState(338);
			switch (_input.LA(1)) {
			case T__14:
				enterOuterAlt(_localctx, 1);
				{
				setState(334); arrRule();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(335); enumRule();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 3);
				{
				setState(336); obj();
				}
				break;
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(337);
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
		enterRule(_localctx, 44, RULE_enumRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340); match(T__6);
			setState(341); match(ID);
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
		enterRule(_localctx, 46, RULE_arrRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343); match(T__14);
			setState(344); annValue();
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(345); match(T__2);
				setState(346); annValue();
				}
				}
				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(352); match(T__11);
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
		enterRule(_localctx, 48, RULE_obj);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354); match(T__13);
			setState(355); keyValue();
			setState(360);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(356); match(T__2);
				setState(357); keyValue();
				}
				}
				setState(362);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(363); match(T__9);
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
		enterRule(_localctx, 50, RULE_keyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365); match(ID);
			setState(366); match(T__15);
			setState(367); annValue();
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3I\u0174\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\3\2\7\29\n\2\f\2\16\2<\13\2\3\2\5\2?\n\2\3\3"+
		"\3\3\3\3\3\3\7\3E\n\3\f\3\16\3H\13\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4P\n\4"+
		"\f\4\16\4S\13\4\3\4\3\4\3\4\3\4\7\4Y\n\4\f\4\16\4\\\13\4\3\4\3\4\5\4`"+
		"\n\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5h\n\5\3\6\7\6k\n\6\f\6\16\6n\13\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\7\6w\n\6\f\6\16\6z\13\6\3\6\3\6\3\6\3\7\7\7\u0080"+
		"\n\7\f\7\16\7\u0083\13\7\3\7\3\7\3\7\3\7\3\7\7\7\u008a\n\7\f\7\16\7\u008d"+
		"\13\7\3\7\3\7\5\7\u0091\n\7\3\b\7\b\u0094\n\b\f\b\16\b\u0097\13\b\3\b"+
		"\3\b\3\b\3\b\7\b\u009d\n\b\f\b\16\b\u00a0\13\b\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\5\n\u00af\n\n\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\7\13\u00ba\n\13\f\13\16\13\u00bd\13\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00ca\n\13\f\13\16\13\u00cd"+
		"\13\13\3\13\3\13\5\13\u00d1\n\13\3\13\3\13\3\13\7\13\u00d6\n\13\f\13\16"+
		"\13\u00d9\13\13\5\13\u00db\n\13\3\f\7\f\u00de\n\f\f\f\16\f\u00e1\13\f"+
		"\3\f\5\f\u00e4\n\f\3\f\3\f\3\f\3\f\7\f\u00ea\n\f\f\f\16\f\u00ed\13\f\3"+
		"\f\3\f\3\r\3\r\5\r\u00f3\n\r\3\16\3\16\3\17\3\17\3\17\3\17\5\17\u00fb"+
		"\n\17\3\17\3\17\3\17\3\17\7\17\u0101\n\17\f\17\16\17\u0104\13\17\3\17"+
		"\3\17\3\20\3\20\3\20\7\20\u010b\n\20\f\20\16\20\u010e\13\20\3\21\3\21"+
		"\3\21\3\21\7\21\u0114\n\21\f\21\16\21\u0117\13\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\7\22\u0120\n\22\f\22\16\22\u0123\13\22\3\22\3\22\3\23"+
		"\3\23\3\23\7\23\u012a\n\23\f\23\16\23\u012d\13\23\3\24\3\24\3\24\3\24"+
		"\5\24\u0133\n\24\3\24\3\24\3\24\3\24\5\24\u0139\n\24\3\24\3\24\3\24\5"+
		"\24\u013e\n\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\5\26\u014f\n\26\3\27\3\27\3\27\3\27\5\27\u0155\n"+
		"\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\7\31\u015e\n\31\f\31\16\31\u0161"+
		"\13\31\3\31\3\31\3\32\3\32\3\32\3\32\7\32\u0169\n\32\f\32\16\32\u016c"+
		"\13\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\2\2\34\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\2\5\4\2\21\21\36\36\4\2\36\36$+\4"+
		"\2!!**\u018b\2\66\3\2\2\2\4@\3\2\2\2\6K\3\2\2\2\bg\3\2\2\2\nl\3\2\2\2"+
		"\f\u0081\3\2\2\2\16\u0095\3\2\2\2\20\u00a4\3\2\2\2\22\u00ae\3\2\2\2\24"+
		"\u00da\3\2\2\2\26\u00df\3\2\2\2\30\u00f2\3\2\2\2\32\u00f4\3\2\2\2\34\u00f6"+
		"\3\2\2\2\36\u0107\3\2\2\2 \u010f\3\2\2\2\"\u011b\3\2\2\2$\u0126\3\2\2"+
		"\2&\u013d\3\2\2\2(\u013f\3\2\2\2*\u014e\3\2\2\2,\u0154\3\2\2\2.\u0156"+
		"\3\2\2\2\60\u0159\3\2\2\2\62\u0164\3\2\2\2\64\u016f\3\2\2\2\66:\5\4\3"+
		"\2\679\5\6\4\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;>\3\2\2\2<:\3"+
		"\2\2\2=?\5\b\5\2>=\3\2\2\2>?\3\2\2\2?\3\3\2\2\2@A\7\24\2\2AF\7\"\2\2B"+
		"C\7\22\2\2CE\7\"\2\2DB\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2"+
		"HF\3\2\2\2IJ\7#\2\2J\5\3\2\2\2KL\7\32\2\2LQ\7\"\2\2MN\7\22\2\2NP\7\"\2"+
		"\2OM\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2RT\3\2\2\2SQ\3\2\2\2TU\7\6\2"+
		"\2UZ\7\"\2\2VW\7\22\2\2WY\7\"\2\2XV\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2"+
		"\2\2[_\3\2\2\2\\Z\3\2\2\2]^\7\25\2\2^`\7\"\2\2_]\3\2\2\2_`\3\2\2\2`a\3"+
		"\2\2\2ab\7#\2\2b\7\3\2\2\2ch\5\n\6\2dh\5\f\7\2eh\5\16\b\2fh\5\20\t\2g"+
		"c\3\2\2\2gd\3\2\2\2ge\3\2\2\2gf\3\2\2\2h\t\3\2\2\2ik\5*\26\2ji\3\2\2\2"+
		"kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2\2op\7\31\2\2pq\7\"\2"+
		"\2qx\7\5\2\2rw\5\n\6\2sw\5\f\7\2tw\5\16\b\2uw\5\20\t\2vr\3\2\2\2vs\3\2"+
		"\2\2vt\3\2\2\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y{\3\2\2\2zx\3\2"+
		"\2\2{|\7\t\2\2|}\7#\2\2}\13\3\2\2\2~\u0080\5*\26\2\177~\3\2\2\2\u0080"+
		"\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0084\3\2\2"+
		"\2\u0083\u0081\3\2\2\2\u0084\u0085\7\26\2\2\u0085\u0086\7\"\2\2\u0086"+
		"\u008b\7\5\2\2\u0087\u008a\5\34\17\2\u0088\u008a\5\26\f\2\u0089\u0087"+
		"\3\2\2\2\u0089\u0088\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u008e\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u0090\7\t"+
		"\2\2\u008f\u0091\7#\2\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\r\3\2\2\2\u0092\u0094\5*\26\2\u0093\u0092\3\2\2\2\u0094\u0097\3\2\2\2"+
		"\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0095"+
		"\3\2\2\2\u0098\u0099\7\27\2\2\u0099\u009a\7\"\2\2\u009a\u009e\7\5\2\2"+
		"\u009b\u009d\5\22\n\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c"+
		"\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1"+
		"\u00a2\7\t\2\2\u00a2\u00a3\7#\2\2\u00a3\17\3\2\2\2\u00a4\u00a5\7\27\2"+
		"\2\u00a5\u00a6\7\"\2\2\u00a6\u00a7\7\3\2\2\u00a7\u00a8\5\24\13\2\u00a8"+
		"\u00a9\7#\2\2\u00a9\21\3\2\2\2\u00aa\u00af\7\"\2\2\u00ab\u00ac\7\13\2"+
		"\2\u00ac\u00ad\7\"\2\2\u00ad\u00af\7\13\2\2\u00ae\u00aa\3\2\2\2\u00ae"+
		"\u00ab\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\7\3\2\2\u00b1\u00b2\5\24"+
		"\13\2\u00b2\u00b3\7#\2\2\u00b3\23\3\2\2\2\u00b4\u00b5\7\"\2\2\u00b5\u00b6"+
		"\7\r\2\2\u00b6\u00bb\7$\2\2\u00b7\u00b8\7\20\2\2\u00b8\u00ba\7$\2\2\u00b9"+
		"\u00b7\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2"+
		"\2\2\u00bc\u00be\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\u00db\7\16\2\2\u00bf"+
		"\u00c0\7\30\2\2\u00c0\u00c1\7\22\2\2\u00c1\u00db\7\"\2\2\u00c2\u00c3\7"+
		"\30\2\2\u00c3\u00c4\7\22\2\2\u00c4\u00c5\7\"\2\2\u00c5\u00c6\7\r\2\2\u00c6"+
		"\u00cb\7$\2\2\u00c7\u00c8\7\20\2\2\u00c8\u00ca\7$\2\2\u00c9\u00c7\3\2"+
		"\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc"+
		"\u00ce\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00db\7\16\2\2\u00cf\u00d1\7"+
		",\2\2\u00d0\u00cf\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2"+
		"\u00d7\7\"\2\2\u00d3\u00d4\7\22\2\2\u00d4\u00d6\7\"\2\2\u00d5\u00d3\3"+
		"\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8"+
		"\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00b4\3\2\2\2\u00da\u00bf\3\2"+
		"\2\2\u00da\u00c2\3\2\2\2\u00da\u00d0\3\2\2\2\u00db\25\3\2\2\2\u00dc\u00de"+
		"\5*\26\2\u00dd\u00dc\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df"+
		"\u00e0\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e4\7\23"+
		"\2\2\u00e3\u00e2\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\u00e6\7\"\2\2\u00e6\u00e7\7\3\2\2\u00e7\u00eb\5\24\13\2\u00e8\u00ea\5"+
		"\30\r\2\u00e9\u00e8\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb"+
		"\u00ec\3\2\2\2\u00ec\u00ee\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00ef\7#"+
		"\2\2\u00ef\27\3\2\2\2\u00f0\u00f3\5(\25\2\u00f1\u00f3\5\32\16\2\u00f2"+
		"\u00f0\3\2\2\2\u00f2\u00f1\3\2\2\2\u00f3\31\3\2\2\2\u00f4\u00f5\t\2\2"+
		"\2\u00f5\33\3\2\2\2\u00f6\u00f7\7\"\2\2\u00f7\u00f8\7\3\2\2\u00f8\u00fa"+
		"\7\33\2\2\u00f9\u00fb\5&\24\2\u00fa\u00f9\3\2\2\2\u00fa\u00fb\3\2\2\2"+
		"\u00fb\u00fc\3\2\2\2\u00fc\u00fd\7\34\2\2\u00fd\u0102\5\36\20\2\u00fe"+
		"\u0101\5\"\22\2\u00ff\u0101\5 \21\2\u0100\u00fe\3\2\2\2\u0100\u00ff\3"+
		"\2\2\2\u0101\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103"+
		"\u0105\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u0106\7#\2\2\u0106\35\3\2\2\2"+
		"\u0107\u010c\7\"\2\2\u0108\u0109\7\22\2\2\u0109\u010b\7\"\2\2\u010a\u0108"+
		"\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\37\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0110\7\35\2\2\u0110\u0115\7\"\2"+
		"\2\u0111\u0112\7\22\2\2\u0112\u0114\7\"\2\2\u0113\u0111\3\2\2\2\u0114"+
		"\u0117\3\2\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0118\3\2"+
		"\2\2\u0117\u0115\3\2\2\2\u0118\u0119\7\b\2\2\u0119\u011a\7\"\2\2\u011a"+
		"!\3\2\2\2\u011b\u011c\7\5\2\2\u011c\u0121\5$\23\2\u011d\u011e\7\20\2\2"+
		"\u011e\u0120\5$\23\2\u011f\u011d\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f"+
		"\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0124\3\2\2\2\u0123\u0121\3\2\2\2\u0124"+
		"\u0125\7\t\2\2\u0125#\3\2\2\2\u0126\u012b\7\"\2\2\u0127\u0128\7\22\2\2"+
		"\u0128\u012a\7\"\2\2\u0129\u0127\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129"+
		"\3\2\2\2\u012b\u012c\3\2\2\2\u012c%\3\2\2\2\u012d\u012b\3\2\2\2\u012e"+
		"\u012f\7\4\2\2\u012f\u0132\7 \2\2\u0130\u0133\7$\2\2\u0131\u0133\7\17"+
		"\2\2\u0132\u0130\3\2\2\2\u0132\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134"+
		"\u013e\7\7\2\2\u0135\u0138\7\4\2\2\u0136\u0139\7$\2\2\u0137\u0139\7\17"+
		"\2\2\u0138\u0136\3\2\2\2\u0138\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a"+
		"\u013e\7\7\2\2\u013b\u013c\7\4\2\2\u013c\u013e\7\7\2\2\u013d\u012e\3\2"+
		"\2\2\u013d\u0135\3\2\2\2\u013d\u013b\3\2\2\2\u013e\'\3\2\2\2\u013f\u0140"+
		"\7\37\2\2\u0140\u0141\t\3\2\2\u0141)\3\2\2\2\u0142\u0143\7\n\2\2\u0143"+
		"\u0144\7\"\2\2\u0144\u0145\7\3\2\2\u0145\u014f\5,\27\2\u0146\u0147\7\n"+
		"\2\2\u0147\u0148\7\"\2\2\u0148\u0149\7\22\2\2\u0149\u014a\7\"\2\2\u014a"+
		"\u014b\7\3\2\2\u014b\u014f\5,\27\2\u014c\u014d\7\n\2\2\u014d\u014f\7\""+
		"\2\2\u014e\u0142\3\2\2\2\u014e\u0146\3\2\2\2\u014e\u014c\3\2\2\2\u014f"+
		"+\3\2\2\2\u0150\u0155\5\60\31\2\u0151\u0155\5.\30\2\u0152\u0155\5\62\32"+
		"\2\u0153\u0155\t\4\2\2\u0154\u0150\3\2\2\2\u0154\u0151\3\2\2\2\u0154\u0152"+
		"\3\2\2\2\u0154\u0153\3\2\2\2\u0155-\3\2\2\2\u0156\u0157\7\f\2\2\u0157"+
		"\u0158\7\"\2\2\u0158/\3\2\2\2\u0159\u015a\7\4\2\2\u015a\u015f\5,\27\2"+
		"\u015b\u015c\7\20\2\2\u015c\u015e\5,\27\2\u015d\u015b\3\2\2\2\u015e\u0161"+
		"\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0162\3\2\2\2\u0161"+
		"\u015f\3\2\2\2\u0162\u0163\7\7\2\2\u0163\61\3\2\2\2\u0164\u0165\7\5\2"+
		"\2\u0165\u016a\5\64\33\2\u0166\u0167\7\20\2\2\u0167\u0169\5\64\33\2\u0168"+
		"\u0166\3\2\2\2\u0169\u016c\3\2\2\2\u016a\u0168\3\2\2\2\u016a\u016b\3\2"+
		"\2\2\u016b\u016d\3\2\2\2\u016c\u016a\3\2\2\2\u016d\u016e\7\t\2\2\u016e"+
		"\63\3\2\2\2\u016f\u0170\7\"\2\2\u0170\u0171\7\3\2\2\u0171\u0172\5,\27"+
		"\2\u0172\65\3\2\2\2*:>FQZ_glvx\u0081\u0089\u008b\u0090\u0095\u009e\u00ae"+
		"\u00bb\u00cb\u00d0\u00d7\u00da\u00df\u00e3\u00eb\u00f2\u00fa\u0100\u0102"+
		"\u010c\u0115\u0121\u012b\u0132\u0138\u013d\u014e\u0154\u015f\u016a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}