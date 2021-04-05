// Generated from com\sap\xsk\parser\hdbti\core\Hdbti.g4 by ANTLR 4.3
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
		T__14=1, T__13=2, T__12=3, T__11=4, T__10=5, T__9=6, T__8=7, T__7=8, T__6=9, 
		T__5=10, T__4=11, T__3=12, T__2=13, T__1=14, T__0=15, STRING=16, BOOLEAN=17, 
		TRUE=18, FALSE=19, WS=20, RB=21, LB=22, EQ=23;
	public static final String[] tokenNames = {
		"<INVALID>", "'file'", "'delimField'", "'useHeaderNames'", "':'", "'{'", 
		"';'", "'}'", "'keys'", "'schema'", "'table'", "'delimEnclosing'", "'header'", 
		"','", "'import'", "'distinguishEmptyFromNull'", "STRING", "BOOLEAN", 
		"'true'", "'false'", "WS", "'['", "']'", "'='"
	};
	public static final int
		RULE_importArr = 0, RULE_objConfig = 1, RULE_assignTable = 2, RULE_assignSchema = 3, 
		RULE_assignFile = 4, RULE_assignHeader = 5, RULE_assignUseHeaderNames = 6, 
		RULE_assignDelimField = 7, RULE_assignDelimEnclosing = 8, RULE_assignDistinguishEmptyFromNull = 9, 
		RULE_assignKeys = 10, RULE_keyArr = 11, RULE_pair = 12, RULE_pairKey = 13, 
		RULE_pairValue = 14;
	public static final String[] ruleNames = {
		"importArr", "objConfig", "assignTable", "assignSchema", "assignFile", 
		"assignHeader", "assignUseHeaderNames", "assignDelimField", "assignDelimEnclosing", 
		"assignDistinguishEmptyFromNull", "assignKeys", "keyArr", "pair", "pairKey", 
		"pairValue"
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
			setState(30); match(T__1);
			setState(31); match(EQ);
			setState(32); match(RB);
			setState(41);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(33); objConfig();
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(34); match(T__2);
					setState(35); objConfig();
					}
					}
					setState(40);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(43); match(LB);
			setState(44); match(T__9);
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
			setState(46); match(T__10);
			setState(47); assignTable();
			setState(49);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(48); assignSchema();
				}
			}

			setState(51); assignFile();
			setState(53);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(52); assignHeader();
				}
			}

			setState(56);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(55); assignUseHeaderNames();
				}
			}

			setState(59);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(58); assignDelimField();
				}
			}

			setState(62);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(61); assignDelimEnclosing();
				}
			}

			setState(65);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(64); assignDistinguishEmptyFromNull();
				}
			}

			setState(68);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(67); assignKeys();
				}
			}

			setState(70); match(T__8);
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
		enterRule(_localctx, 4, RULE_assignTable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72); match(T__5);
			setState(73); match(EQ);
			setState(74); match(STRING);
			setState(75); match(T__9);
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
		enterRule(_localctx, 6, RULE_assignSchema);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77); match(T__6);
			setState(78); match(EQ);
			setState(79); match(STRING);
			setState(80); match(T__9);
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
		enterRule(_localctx, 8, RULE_assignFile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); match(T__14);
			setState(83); match(EQ);
			setState(84); match(STRING);
			setState(85); match(T__9);
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
		enterRule(_localctx, 10, RULE_assignHeader);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87); match(T__3);
			setState(88); match(EQ);
			setState(89); match(BOOLEAN);
			setState(90); match(T__9);
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
		enterRule(_localctx, 12, RULE_assignUseHeaderNames);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); match(T__12);
			setState(93); match(EQ);
			setState(94); match(BOOLEAN);
			setState(95); match(T__9);
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
		enterRule(_localctx, 14, RULE_assignDelimField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97); match(T__13);
			setState(98); match(EQ);
			setState(99); match(STRING);
			setState(100); match(T__9);
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
		enterRule(_localctx, 16, RULE_assignDelimEnclosing);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); match(T__4);
			setState(103); match(EQ);
			setState(104); match(STRING);
			setState(105); match(T__9);
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
		enterRule(_localctx, 18, RULE_assignDistinguishEmptyFromNull);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107); match(T__0);
			setState(108); match(EQ);
			setState(109); match(BOOLEAN);
			setState(110); match(T__9);
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
		enterRule(_localctx, 20, RULE_assignKeys);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); match(T__7);
			setState(113); match(EQ);
			setState(114); keyArr();
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
		enterRule(_localctx, 22, RULE_keyArr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116); match(RB);
			setState(125);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(117); pair();
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(118); match(T__2);
					setState(119); pair();
					}
					}
					setState(124);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(127); match(LB);
			setState(128); match(T__9);
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
		enterRule(_localctx, 24, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); pairKey();
			setState(131); match(T__11);
			setState(132); pairValue();
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
		enterRule(_localctx, 26, RULE_pairKey);
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
		enterRule(_localctx, 28, RULE_pairValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136); match(STRING);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\31\u008d\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\7\2\'\n\2\f\2\16\2*\13\2\5\2,\n\2\3\2\3\2\3\2\3\3\3\3\3\3\5\3\64"+
		"\n\3\3\3\3\3\5\38\n\3\3\3\5\3;\n\3\3\3\5\3>\n\3\3\3\5\3A\n\3\3\3\5\3D"+
		"\n\3\3\3\5\3G\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\r\7\r{\n\r\f\r\16\r~\13\r\5\r\u0080\n\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\2\2\21\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36\2\2\u0088\2 \3\2\2\2\4\60\3\2\2\2\6J\3\2\2\2\bO\3\2\2"+
		"\2\nT\3\2\2\2\fY\3\2\2\2\16^\3\2\2\2\20c\3\2\2\2\22h\3\2\2\2\24m\3\2\2"+
		"\2\26r\3\2\2\2\30v\3\2\2\2\32\u0084\3\2\2\2\34\u0088\3\2\2\2\36\u008a"+
		"\3\2\2\2 !\7\20\2\2!\"\7\31\2\2\"+\7\27\2\2#(\5\4\3\2$%\7\17\2\2%\'\5"+
		"\4\3\2&$\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2),\3\2\2\2*(\3\2\2\2+#"+
		"\3\2\2\2+,\3\2\2\2,-\3\2\2\2-.\7\30\2\2./\7\b\2\2/\3\3\2\2\2\60\61\7\7"+
		"\2\2\61\63\5\6\4\2\62\64\5\b\5\2\63\62\3\2\2\2\63\64\3\2\2\2\64\65\3\2"+
		"\2\2\65\67\5\n\6\2\668\5\f\7\2\67\66\3\2\2\2\678\3\2\2\28:\3\2\2\29;\5"+
		"\16\b\2:9\3\2\2\2:;\3\2\2\2;=\3\2\2\2<>\5\20\t\2=<\3\2\2\2=>\3\2\2\2>"+
		"@\3\2\2\2?A\5\22\n\2@?\3\2\2\2@A\3\2\2\2AC\3\2\2\2BD\5\24\13\2CB\3\2\2"+
		"\2CD\3\2\2\2DF\3\2\2\2EG\5\26\f\2FE\3\2\2\2FG\3\2\2\2GH\3\2\2\2HI\7\t"+
		"\2\2I\5\3\2\2\2JK\7\f\2\2KL\7\31\2\2LM\7\22\2\2MN\7\b\2\2N\7\3\2\2\2O"+
		"P\7\13\2\2PQ\7\31\2\2QR\7\22\2\2RS\7\b\2\2S\t\3\2\2\2TU\7\3\2\2UV\7\31"+
		"\2\2VW\7\22\2\2WX\7\b\2\2X\13\3\2\2\2YZ\7\16\2\2Z[\7\31\2\2[\\\7\23\2"+
		"\2\\]\7\b\2\2]\r\3\2\2\2^_\7\5\2\2_`\7\31\2\2`a\7\23\2\2ab\7\b\2\2b\17"+
		"\3\2\2\2cd\7\4\2\2de\7\31\2\2ef\7\22\2\2fg\7\b\2\2g\21\3\2\2\2hi\7\r\2"+
		"\2ij\7\31\2\2jk\7\22\2\2kl\7\b\2\2l\23\3\2\2\2mn\7\21\2\2no\7\31\2\2o"+
		"p\7\23\2\2pq\7\b\2\2q\25\3\2\2\2rs\7\n\2\2st\7\31\2\2tu\5\30\r\2u\27\3"+
		"\2\2\2v\177\7\27\2\2w|\5\32\16\2xy\7\17\2\2y{\5\32\16\2zx\3\2\2\2{~\3"+
		"\2\2\2|z\3\2\2\2|}\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2\177w\3\2\2\2\177\u0080"+
		"\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\7\30\2\2\u0082\u0083\7\b\2\2"+
		"\u0083\31\3\2\2\2\u0084\u0085\5\34\17\2\u0085\u0086\7\6\2\2\u0086\u0087"+
		"\5\36\20\2\u0087\33\3\2\2\2\u0088\u0089\7\22\2\2\u0089\35\3\2\2\2\u008a"+
		"\u008b\7\22\2\2\u008b\37\3\2\2\2\r(+\63\67:=@CF|\177";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}