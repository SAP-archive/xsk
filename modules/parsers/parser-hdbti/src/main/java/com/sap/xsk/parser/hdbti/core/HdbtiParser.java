// Generated from com/sap/xsk/parser/hdbti/core/Hdbti.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbti.core;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbtiParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__16=1, T__15=2, T__14=3, T__13=4, T__12=5, T__11=6, T__10=7, T__9=8, 
		T__8=9, T__7=10, T__6=11, T__5=12, T__4=13, T__3=14, T__2=15, T__1=16, 
		T__0=17, STRING=18, BOOLEAN=19, TRUE=20, FALSE=21, WS=22, RB=23, LB=24, 
		EQ=25, LINE_COMMENT=26, COMMENT=27;
	public static final String[] tokenNames = {
		"<INVALID>", "'file'", "'delimField'", "'useHeaderNames'", "'hdbtable'", 
		"':'", "'{'", "';'", "'}'", "'keys'", "'schema'", "'table'", "'delimEnclosing'", 
		"'header'", "'cdstable'", "','", "'import'", "'distinguishEmptyFromNull'", 
		"STRING", "BOOLEAN", "'true'", "'false'", "WS", "'['", "']'", "'='", "LINE_COMMENT", 
		"COMMENT"
	};
	public static final int
		RULE_importArr = 0, RULE_objConfig = 1, RULE_assignExpression = 2, RULE_assignTable = 3, 
		RULE_assignSchema = 4, RULE_assignFile = 5, RULE_assignHeader = 6, RULE_assignUseHeaderNames = 7, 
		RULE_assignDelimField = 8, RULE_assignDelimEnclosing = 9, RULE_assignDistinguishEmptyFromNull = 10, 
		RULE_assignKeys = 11, RULE_keyArr = 12, RULE_pair = 13, RULE_pairKey = 14, 
		RULE_pairValue = 15, RULE_tableName = 16;
	public static final String[] ruleNames = {
		"importArr", "objConfig", "assignExpression", "assignTable", "assignSchema", 
		"assignFile", "assignHeader", "assignUseHeaderNames", "assignDelimField", 
		"assignDelimEnclosing", "assignDistinguishEmptyFromNull", "assignKeys", 
		"keyArr", "pair", "pairKey", "pairValue", "tableName"
	};

	@Override
	public String getGrammarFileName() { return "Hdbti.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HdbtiParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ImportArrContext extends ParserRuleContext {
		public List<ObjConfigContext> objConfig() {
			return getRuleContexts(ObjConfigContext.class);
		}
		public ObjConfigContext objConfig(int i) {
			return getRuleContext(ObjConfigContext.class,i);
		}
		public ImportArrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importArr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterImportArr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitImportArr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitImportArr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportArrContext importArr() throws RecognitionException {
		ImportArrContext _localctx = new ImportArrContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_importArr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34); match(T__1);
			setState(35); match(EQ);
			setState(36); match(RB);
			setState(45);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(37); objConfig();
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(38); match(T__2);
					setState(39); objConfig();
					}
					}
					setState(44);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(47); match(LB);
			setState(48); match(T__10);
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

	public static class ObjConfigContext extends ParserRuleContext {
		public List<AssignExpressionContext> assignExpression() {
			return getRuleContexts(AssignExpressionContext.class);
		}
		public AssignExpressionContext assignExpression(int i) {
			return getRuleContext(AssignExpressionContext.class,i);
		}
		public ObjConfigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objConfig; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterObjConfig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitObjConfig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitObjConfig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjConfigContext objConfig() throws RecognitionException {
		ObjConfigContext _localctx = new ObjConfigContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_objConfig);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50); match(T__11);
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__15) | (1L << T__14) | (1L << T__13) | (1L << T__8) | (1L << T__7) | (1L << T__6) | (1L << T__5) | (1L << T__4) | (1L << T__3) | (1L << T__0))) != 0)) {
				{
				{
				setState(51); assignExpression();
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(57); match(T__9);
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

	public static class AssignExpressionContext extends ParserRuleContext {
		public AssignDistinguishEmptyFromNullContext assignDistinguishEmptyFromNull() {
			return getRuleContext(AssignDistinguishEmptyFromNullContext.class,0);
		}
		public AssignKeysContext assignKeys() {
			return getRuleContext(AssignKeysContext.class,0);
		}
		public AssignDelimFieldContext assignDelimField() {
			return getRuleContext(AssignDelimFieldContext.class,0);
		}
		public AssignDelimEnclosingContext assignDelimEnclosing() {
			return getRuleContext(AssignDelimEnclosingContext.class,0);
		}
		public AssignSchemaContext assignSchema() {
			return getRuleContext(AssignSchemaContext.class,0);
		}
		public AssignHeaderContext assignHeader() {
			return getRuleContext(AssignHeaderContext.class,0);
		}
		public AssignUseHeaderNamesContext assignUseHeaderNames() {
			return getRuleContext(AssignUseHeaderNamesContext.class,0);
		}
		public AssignFileContext assignFile() {
			return getRuleContext(AssignFileContext.class,0);
		}
		public AssignTableContext assignTable() {
			return getRuleContext(AssignTableContext.class,0);
		}
		public AssignExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterAssignExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitAssignExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitAssignExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignExpressionContext assignExpression() throws RecognitionException {
		AssignExpressionContext _localctx = new AssignExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignExpression);
		try {
			setState(68);
			switch (_input.LA(1)) {
			case T__13:
			case T__6:
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(59); assignTable();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(60); assignSchema();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 3);
				{
				setState(61); assignFile();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 4);
				{
				setState(62); assignHeader();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 5);
				{
				setState(63); assignUseHeaderNames();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 6);
				{
				setState(64); assignDelimField();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 7);
				{
				setState(65); assignDelimEnclosing();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 8);
				{
				setState(66); assignDistinguishEmptyFromNull();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 9);
				{
				setState(67); assignKeys();
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

	public static class AssignTableContext extends ParserRuleContext {
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode STRING() { return getToken(HdbtiParser.STRING, 0); }
		public AssignTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterAssignTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitAssignTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitAssignTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignTableContext assignTable() throws RecognitionException {
		AssignTableContext _localctx = new AssignTableContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assignTable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); tableName();
			setState(71); match(EQ);
			setState(72); match(STRING);
			setState(73); match(T__10);
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

	public static class AssignSchemaContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(HdbtiParser.STRING, 0); }
		public AssignSchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignSchema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterAssignSchema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitAssignSchema(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitAssignSchema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignSchemaContext assignSchema() throws RecognitionException {
		AssignSchemaContext _localctx = new AssignSchemaContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignSchema);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); match(T__7);
			setState(76); match(EQ);
			setState(77); match(STRING);
			setState(78); match(T__10);
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

	public static class AssignFileContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(HdbtiParser.STRING, 0); }
		public AssignFileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignFile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterAssignFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitAssignFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitAssignFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignFileContext assignFile() throws RecognitionException {
		AssignFileContext _localctx = new AssignFileContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignFile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); match(T__16);
			setState(81); match(EQ);
			setState(82); match(STRING);
			setState(83); match(T__10);
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

	public static class AssignHeaderContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(HdbtiParser.BOOLEAN, 0); }
		public AssignHeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignHeader; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterAssignHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitAssignHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitAssignHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignHeaderContext assignHeader() throws RecognitionException {
		AssignHeaderContext _localctx = new AssignHeaderContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignHeader);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85); match(T__4);
			setState(86); match(EQ);
			setState(87); match(BOOLEAN);
			setState(88); match(T__10);
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

	public static class AssignUseHeaderNamesContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(HdbtiParser.BOOLEAN, 0); }
		public AssignUseHeaderNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignUseHeaderNames; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterAssignUseHeaderNames(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitAssignUseHeaderNames(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitAssignUseHeaderNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignUseHeaderNamesContext assignUseHeaderNames() throws RecognitionException {
		AssignUseHeaderNamesContext _localctx = new AssignUseHeaderNamesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignUseHeaderNames);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90); match(T__14);
			setState(91); match(EQ);
			setState(92); match(BOOLEAN);
			setState(93); match(T__10);
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

	public static class AssignDelimFieldContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(HdbtiParser.STRING, 0); }
		public AssignDelimFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignDelimField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterAssignDelimField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitAssignDelimField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitAssignDelimField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignDelimFieldContext assignDelimField() throws RecognitionException {
		AssignDelimFieldContext _localctx = new AssignDelimFieldContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_assignDelimField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95); match(T__15);
			setState(96); match(EQ);
			setState(97); match(STRING);
			setState(98); match(T__10);
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

	public static class AssignDelimEnclosingContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(HdbtiParser.STRING, 0); }
		public AssignDelimEnclosingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignDelimEnclosing; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterAssignDelimEnclosing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitAssignDelimEnclosing(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitAssignDelimEnclosing(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignDelimEnclosingContext assignDelimEnclosing() throws RecognitionException {
		AssignDelimEnclosingContext _localctx = new AssignDelimEnclosingContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_assignDelimEnclosing);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); match(T__5);
			setState(101); match(EQ);
			setState(102); match(STRING);
			setState(103); match(T__10);
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

	public static class AssignDistinguishEmptyFromNullContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(HdbtiParser.BOOLEAN, 0); }
		public AssignDistinguishEmptyFromNullContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignDistinguishEmptyFromNull; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterAssignDistinguishEmptyFromNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitAssignDistinguishEmptyFromNull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitAssignDistinguishEmptyFromNull(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignDistinguishEmptyFromNullContext assignDistinguishEmptyFromNull() throws RecognitionException {
		AssignDistinguishEmptyFromNullContext _localctx = new AssignDistinguishEmptyFromNullContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_assignDistinguishEmptyFromNull);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); match(T__0);
			setState(106); match(EQ);
			setState(107); match(BOOLEAN);
			setState(108); match(T__10);
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

	public static class AssignKeysContext extends ParserRuleContext {
		public KeyArrContext keyArr() {
			return getRuleContext(KeyArrContext.class,0);
		}
		public AssignKeysContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignKeys; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterAssignKeys(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitAssignKeys(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitAssignKeys(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignKeysContext assignKeys() throws RecognitionException {
		AssignKeysContext _localctx = new AssignKeysContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_assignKeys);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); match(T__8);
			setState(111); match(EQ);
			setState(112); keyArr();
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

	public static class KeyArrContext extends ParserRuleContext {
		public PairContext pair(int i) {
			return getRuleContext(PairContext.class,i);
		}
		public List<PairContext> pair() {
			return getRuleContexts(PairContext.class);
		}
		public KeyArrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyArr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterKeyArr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitKeyArr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitKeyArr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyArrContext keyArr() throws RecognitionException {
		KeyArrContext _localctx = new KeyArrContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_keyArr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114); match(RB);
			setState(123);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(115); pair();
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(116); match(T__2);
					setState(117); pair();
					}
					}
					setState(122);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(125); match(LB);
			setState(126); match(T__10);
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

	public static class PairContext extends ParserRuleContext {
		public PairValueContext pairValue() {
			return getRuleContext(PairValueContext.class,0);
		}
		public PairKeyContext pairKey() {
			return getRuleContext(PairKeyContext.class,0);
		}
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); pairKey();
			setState(129); match(T__12);
			setState(130); pairValue();
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

	public static class PairKeyContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(HdbtiParser.STRING, 0); }
		public PairKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterPairKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitPairKey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitPairKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairKeyContext pairKey() throws RecognitionException {
		PairKeyContext _localctx = new PairKeyContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_pairKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132); match(STRING);
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

	public static class PairValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(HdbtiParser.STRING, 0); }
		public PairValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterPairValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitPairValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitPairValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairValueContext pairValue() throws RecognitionException {
		PairValueContext _localctx = new PairValueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_pairValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134); match(STRING);
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

	public static class TableNameContext extends ParserRuleContext {
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).enterTableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtiListener ) ((HdbtiListener)listener).exitTableName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtiVisitor ) return ((HdbtiVisitor<? extends T>)visitor).visitTableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_tableName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__6) | (1L << T__3))) != 0)) ) {
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\35\u008d\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\7\2+\n\2\f\2\16\2.\13\2\5\2\60\n\2\3\2\3\2\3"+
		"\2\3\3\3\3\7\3\67\n\3\f\3\16\3:\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\5\4G\n\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\7\16y\n\16\f\16\16\16|\13\16\5\16~\n\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\2\2\23\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"\2\3\5\2\6\6\r\r\20\20\u0088\2$\3\2\2\2"+
		"\4\64\3\2\2\2\6F\3\2\2\2\bH\3\2\2\2\nM\3\2\2\2\fR\3\2\2\2\16W\3\2\2\2"+
		"\20\\\3\2\2\2\22a\3\2\2\2\24f\3\2\2\2\26k\3\2\2\2\30p\3\2\2\2\32t\3\2"+
		"\2\2\34\u0082\3\2\2\2\36\u0086\3\2\2\2 \u0088\3\2\2\2\"\u008a\3\2\2\2"+
		"$%\7\22\2\2%&\7\33\2\2&/\7\31\2\2\',\5\4\3\2()\7\21\2\2)+\5\4\3\2*(\3"+
		"\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\60\3\2\2\2.,\3\2\2\2/\'\3\2\2\2"+
		"/\60\3\2\2\2\60\61\3\2\2\2\61\62\7\32\2\2\62\63\7\t\2\2\63\3\3\2\2\2\64"+
		"8\7\b\2\2\65\67\5\6\4\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2"+
		"\29;\3\2\2\2:8\3\2\2\2;<\7\n\2\2<\5\3\2\2\2=G\5\b\5\2>G\5\n\6\2?G\5\f"+
		"\7\2@G\5\16\b\2AG\5\20\t\2BG\5\22\n\2CG\5\24\13\2DG\5\26\f\2EG\5\30\r"+
		"\2F=\3\2\2\2F>\3\2\2\2F?\3\2\2\2F@\3\2\2\2FA\3\2\2\2FB\3\2\2\2FC\3\2\2"+
		"\2FD\3\2\2\2FE\3\2\2\2G\7\3\2\2\2HI\5\"\22\2IJ\7\33\2\2JK\7\24\2\2KL\7"+
		"\t\2\2L\t\3\2\2\2MN\7\f\2\2NO\7\33\2\2OP\7\24\2\2PQ\7\t\2\2Q\13\3\2\2"+
		"\2RS\7\3\2\2ST\7\33\2\2TU\7\24\2\2UV\7\t\2\2V\r\3\2\2\2WX\7\17\2\2XY\7"+
		"\33\2\2YZ\7\25\2\2Z[\7\t\2\2[\17\3\2\2\2\\]\7\5\2\2]^\7\33\2\2^_\7\25"+
		"\2\2_`\7\t\2\2`\21\3\2\2\2ab\7\4\2\2bc\7\33\2\2cd\7\24\2\2de\7\t\2\2e"+
		"\23\3\2\2\2fg\7\16\2\2gh\7\33\2\2hi\7\24\2\2ij\7\t\2\2j\25\3\2\2\2kl\7"+
		"\23\2\2lm\7\33\2\2mn\7\25\2\2no\7\t\2\2o\27\3\2\2\2pq\7\13\2\2qr\7\33"+
		"\2\2rs\5\32\16\2s\31\3\2\2\2t}\7\31\2\2uz\5\34\17\2vw\7\21\2\2wy\5\34"+
		"\17\2xv\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{~\3\2\2\2|z\3\2\2\2}u\3"+
		"\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\7\32\2\2\u0080\u0081\7\t\2\2\u0081"+
		"\33\3\2\2\2\u0082\u0083\5\36\20\2\u0083\u0084\7\7\2\2\u0084\u0085\5 \21"+
		"\2\u0085\35\3\2\2\2\u0086\u0087\7\24\2\2\u0087\37\3\2\2\2\u0088\u0089"+
		"\7\24\2\2\u0089!\3\2\2\2\u008a\u008b\t\2\2\2\u008b#\3\2\2\2\b,/8Fz}";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}