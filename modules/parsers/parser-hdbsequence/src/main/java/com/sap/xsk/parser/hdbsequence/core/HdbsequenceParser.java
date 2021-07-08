// Generated from com/sap/xsk/parser/hdbsequence/core/Hdbsequence.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbsequence.core;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbsequenceParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__13=1, T__12=2, T__11=3, T__10=4, T__9=5, T__8=6, T__7=7, T__6=8, T__5=9, 
		T__4=10, T__3=11, T__2=12, T__1=13, T__0=14, STRING=15, INT=16, BOOLEAN=17, 
		TRUE=18, FALSE=19, WS=20, LB=21, RB=22, EQ=23, SC=24, SIGNED_INT=25, LINE_COMMENT=26, 
		COMMENT=27;
	public static final String[] tokenNames = {
		"<INVALID>", "'nominvalue'", "'depends_on_table'", "'depends_on'", "'depends_on_view'", 
		"'public'", "'reset_by'", "'schema'", "'start_with'", "'cycles'", "'maxvalue'", 
		"','", "'increment_by'", "'nomaxvalue'", "'minvalue'", "STRING", "INT", 
		"BOOLEAN", "'true'", "'false'", "WS", "'['", "']'", "'='", "';'", "'-'", 
		"LINE_COMMENT", "COMMENT"
	};
	public static final int
		RULE_hdbsequence = 0, RULE_property = 1, RULE_schema = 2, RULE_increment_by = 3, 
		RULE_start_with = 4, RULE_maxvalue = 5, RULE_nomaxvalue = 6, RULE_minvalue = 7, 
		RULE_nominvalue = 8, RULE_cycles = 9, RULE_reset_by = 10, RULE_publicc = 11, 
		RULE_dependsOnProp = 12, RULE_dependsOnTable = 13, RULE_dependsOnView = 14;
	public static final String[] ruleNames = {
		"hdbsequence", "property", "schema", "increment_by", "start_with", "maxvalue", 
		"nomaxvalue", "minvalue", "nominvalue", "cycles", "reset_by", "publicc", 
		"dependsOnProp", "dependsOnTable", "dependsOnView"
	};

	@Override
	public String getGrammarFileName() { return "Hdbsequence.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HdbsequenceParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class HdbsequenceContext extends ParserRuleContext {
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public HdbsequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hdbsequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterHdbsequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitHdbsequence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitHdbsequence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HdbsequenceContext hdbsequence() throws RecognitionException {
		HdbsequenceContext _localctx = new HdbsequenceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_hdbsequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(30); property();
				}
				}
				setState(33); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__12) | (1L << T__11) | (1L << T__10) | (1L << T__9) | (1L << T__8) | (1L << T__7) | (1L << T__6) | (1L << T__5) | (1L << T__4) | (1L << T__2) | (1L << T__1) | (1L << T__0))) != 0) );
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

	public static class PropertyContext extends ParserRuleContext {
		public NominvalueContext nominvalue() {
			return getRuleContext(NominvalueContext.class,0);
		}
		public Start_withContext start_with() {
			return getRuleContext(Start_withContext.class,0);
		}
		public MaxvalueContext maxvalue() {
			return getRuleContext(MaxvalueContext.class,0);
		}
		public NomaxvalueContext nomaxvalue() {
			return getRuleContext(NomaxvalueContext.class,0);
		}
		public PubliccContext publicc() {
			return getRuleContext(PubliccContext.class,0);
		}
		public DependsOnPropContext dependsOnProp() {
			return getRuleContext(DependsOnPropContext.class,0);
		}
		public DependsOnTableContext dependsOnTable() {
			return getRuleContext(DependsOnTableContext.class,0);
		}
		public CyclesContext cycles() {
			return getRuleContext(CyclesContext.class,0);
		}
		public Reset_byContext reset_by() {
			return getRuleContext(Reset_byContext.class,0);
		}
		public DependsOnViewContext dependsOnView() {
			return getRuleContext(DependsOnViewContext.class,0);
		}
		public Increment_byContext increment_by() {
			return getRuleContext(Increment_byContext.class,0);
		}
		public MinvalueContext minvalue() {
			return getRuleContext(MinvalueContext.class,0);
		}
		public SchemaContext schema() {
			return getRuleContext(SchemaContext.class,0);
		}
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_property);
		try {
			setState(48);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(35); schema();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(36); increment_by();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 3);
				{
				setState(37); start_with();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 4);
				{
				setState(38); maxvalue();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 5);
				{
				setState(39); nomaxvalue();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 6);
				{
				setState(40); minvalue();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 7);
				{
				setState(41); nominvalue();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 8);
				{
				setState(42); cycles();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 9);
				{
				setState(43); reset_by();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 10);
				{
				setState(44); publicc();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 11);
				{
				setState(45); dependsOnProp();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 12);
				{
				setState(46); dependsOnTable();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 13);
				{
				setState(47); dependsOnView();
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

	public static class SchemaContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbsequenceParser.STRING, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public SchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterSchema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitSchema(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitSchema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaContext schema() throws RecognitionException {
		SchemaContext _localctx = new SchemaContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_schema);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50); match(T__7);
			setState(51); match(EQ);
			setState(52); match(STRING);
			setState(53); match(SC);
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

	public static class Increment_byContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbsequenceParser.INT, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public Increment_byContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_increment_by; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterIncrement_by(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitIncrement_by(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitIncrement_by(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Increment_byContext increment_by() throws RecognitionException {
		Increment_byContext _localctx = new Increment_byContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_increment_by);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55); match(T__2);
			setState(56); match(EQ);
			setState(57); match(INT);
			setState(58); match(SC);
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

	public static class Start_withContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbsequenceParser.INT, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public Start_withContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_with; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterStart_with(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitStart_with(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitStart_with(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Start_withContext start_with() throws RecognitionException {
		Start_withContext _localctx = new Start_withContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_start_with);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); match(T__6);
			setState(61); match(EQ);
			setState(62); match(INT);
			setState(63); match(SC);
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

	public static class MaxvalueContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbsequenceParser.INT, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public MaxvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_maxvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterMaxvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitMaxvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitMaxvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MaxvalueContext maxvalue() throws RecognitionException {
		MaxvalueContext _localctx = new MaxvalueContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_maxvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65); match(T__4);
			setState(66); match(EQ);
			setState(67); match(INT);
			setState(68); match(SC);
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

	public static class NomaxvalueContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbsequenceParser.BOOLEAN, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public NomaxvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nomaxvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterNomaxvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitNomaxvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitNomaxvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NomaxvalueContext nomaxvalue() throws RecognitionException {
		NomaxvalueContext _localctx = new NomaxvalueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_nomaxvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); match(T__1);
			setState(71); match(EQ);
			setState(72); match(BOOLEAN);
			setState(73); match(SC);
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

	public static class MinvalueContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbsequenceParser.INT, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public MinvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterMinvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitMinvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitMinvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MinvalueContext minvalue() throws RecognitionException {
		MinvalueContext _localctx = new MinvalueContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_minvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); match(T__0);
			setState(76); match(EQ);
			setState(77); match(INT);
			setState(78); match(SC);
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

	public static class NominvalueContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbsequenceParser.BOOLEAN, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public NominvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nominvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterNominvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitNominvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitNominvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NominvalueContext nominvalue() throws RecognitionException {
		NominvalueContext _localctx = new NominvalueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_nominvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); match(T__13);
			setState(81); match(EQ);
			setState(82); match(BOOLEAN);
			setState(83); match(SC);
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

	public static class CyclesContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbsequenceParser.BOOLEAN, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public CyclesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cycles; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterCycles(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitCycles(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitCycles(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CyclesContext cycles() throws RecognitionException {
		CyclesContext _localctx = new CyclesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cycles);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85); match(T__5);
			setState(86); match(EQ);
			setState(87); match(BOOLEAN);
			setState(88); match(SC);
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

	public static class Reset_byContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbsequenceParser.STRING, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public Reset_byContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reset_by; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterReset_by(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitReset_by(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitReset_by(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Reset_byContext reset_by() throws RecognitionException {
		Reset_byContext _localctx = new Reset_byContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_reset_by);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90); match(T__8);
			setState(91); match(EQ);
			setState(92); match(STRING);
			setState(93); match(SC);
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

	public static class PubliccContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbsequenceParser.BOOLEAN, 0); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public PubliccContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_publicc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterPublicc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitPublicc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitPublicc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PubliccContext publicc() throws RecognitionException {
		PubliccContext _localctx = new PubliccContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_publicc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95); match(T__9);
			setState(96); match(EQ);
			setState(97); match(BOOLEAN);
			setState(98); match(SC);
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

	public static class DependsOnPropContext extends ParserRuleContext {
		public TerminalNode STRING(int i) {
			return getToken(HdbsequenceParser.STRING, i);
		}
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbsequenceParser.STRING); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public DependsOnPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dependsOnProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterDependsOnProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitDependsOnProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitDependsOnProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DependsOnPropContext dependsOnProp() throws RecognitionException {
		DependsOnPropContext _localctx = new DependsOnPropContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_dependsOnProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); match(T__11);
			setState(101); match(EQ);
			setState(102); match(LB);
			setState(111);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(103); match(STRING);
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(104); match(T__3);
					setState(105); match(STRING);
					}
					}
					setState(110);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(113); match(RB);
			setState(114); match(SC);
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

	public static class DependsOnTableContext extends ParserRuleContext {
		public TerminalNode STRING(int i) {
			return getToken(HdbsequenceParser.STRING, i);
		}
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbsequenceParser.STRING); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public DependsOnTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dependsOnTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterDependsOnTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitDependsOnTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitDependsOnTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DependsOnTableContext dependsOnTable() throws RecognitionException {
		DependsOnTableContext _localctx = new DependsOnTableContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_dependsOnTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116); match(T__12);
			setState(117); match(EQ);
			setState(118); match(LB);
			setState(127);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(119); match(STRING);
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(120); match(T__3);
					setState(121); match(STRING);
					}
					}
					setState(126);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(129); match(RB);
			setState(130); match(SC);
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

	public static class DependsOnViewContext extends ParserRuleContext {
		public TerminalNode STRING(int i) {
			return getToken(HdbsequenceParser.STRING, i);
		}
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbsequenceParser.STRING); }
		public TerminalNode SC() { return getToken(HdbsequenceParser.SC, 0); }
		public DependsOnViewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dependsOnView; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).enterDependsOnView(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbsequenceListener ) ((HdbsequenceListener)listener).exitDependsOnView(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbsequenceVisitor ) return ((HdbsequenceVisitor<? extends T>)visitor).visitDependsOnView(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DependsOnViewContext dependsOnView() throws RecognitionException {
		DependsOnViewContext _localctx = new DependsOnViewContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_dependsOnView);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132); match(T__10);
			setState(133); match(EQ);
			setState(134); match(LB);
			setState(143);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(135); match(STRING);
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(136); match(T__3);
					setState(137); match(STRING);
					}
					}
					setState(142);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(145); match(RB);
			setState(146); match(SC);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\35\u0097\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\6\2\"\n\2\r\2"+
		"\16\2#\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\63\n\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\7\16m\n\16\f\16\16\16p\13\16\5\16r\n\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\7\17}\n\17\f\17\16\17\u0080\13"+
		"\17\5\17\u0082\n\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\7\20"+
		"\u008d\n\20\f\20\16\20\u0090\13\20\5\20\u0092\n\20\3\20\3\20\3\20\3\20"+
		"\2\2\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\2\u009a\2!\3\2\2\2\4"+
		"\62\3\2\2\2\6\64\3\2\2\2\b9\3\2\2\2\n>\3\2\2\2\fC\3\2\2\2\16H\3\2\2\2"+
		"\20M\3\2\2\2\22R\3\2\2\2\24W\3\2\2\2\26\\\3\2\2\2\30a\3\2\2\2\32f\3\2"+
		"\2\2\34v\3\2\2\2\36\u0086\3\2\2\2 \"\5\4\3\2! \3\2\2\2\"#\3\2\2\2#!\3"+
		"\2\2\2#$\3\2\2\2$\3\3\2\2\2%\63\5\6\4\2&\63\5\b\5\2\'\63\5\n\6\2(\63\5"+
		"\f\7\2)\63\5\16\b\2*\63\5\20\t\2+\63\5\22\n\2,\63\5\24\13\2-\63\5\26\f"+
		"\2.\63\5\30\r\2/\63\5\32\16\2\60\63\5\34\17\2\61\63\5\36\20\2\62%\3\2"+
		"\2\2\62&\3\2\2\2\62\'\3\2\2\2\62(\3\2\2\2\62)\3\2\2\2\62*\3\2\2\2\62+"+
		"\3\2\2\2\62,\3\2\2\2\62-\3\2\2\2\62.\3\2\2\2\62/\3\2\2\2\62\60\3\2\2\2"+
		"\62\61\3\2\2\2\63\5\3\2\2\2\64\65\7\t\2\2\65\66\7\31\2\2\66\67\7\21\2"+
		"\2\678\7\32\2\28\7\3\2\2\29:\7\16\2\2:;\7\31\2\2;<\7\22\2\2<=\7\32\2\2"+
		"=\t\3\2\2\2>?\7\n\2\2?@\7\31\2\2@A\7\22\2\2AB\7\32\2\2B\13\3\2\2\2CD\7"+
		"\f\2\2DE\7\31\2\2EF\7\22\2\2FG\7\32\2\2G\r\3\2\2\2HI\7\17\2\2IJ\7\31\2"+
		"\2JK\7\23\2\2KL\7\32\2\2L\17\3\2\2\2MN\7\20\2\2NO\7\31\2\2OP\7\22\2\2"+
		"PQ\7\32\2\2Q\21\3\2\2\2RS\7\3\2\2ST\7\31\2\2TU\7\23\2\2UV\7\32\2\2V\23"+
		"\3\2\2\2WX\7\13\2\2XY\7\31\2\2YZ\7\23\2\2Z[\7\32\2\2[\25\3\2\2\2\\]\7"+
		"\b\2\2]^\7\31\2\2^_\7\21\2\2_`\7\32\2\2`\27\3\2\2\2ab\7\7\2\2bc\7\31\2"+
		"\2cd\7\23\2\2de\7\32\2\2e\31\3\2\2\2fg\7\5\2\2gh\7\31\2\2hq\7\27\2\2i"+
		"n\7\21\2\2jk\7\r\2\2km\7\21\2\2lj\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2"+
		"\2or\3\2\2\2pn\3\2\2\2qi\3\2\2\2qr\3\2\2\2rs\3\2\2\2st\7\30\2\2tu\7\32"+
		"\2\2u\33\3\2\2\2vw\7\4\2\2wx\7\31\2\2x\u0081\7\27\2\2y~\7\21\2\2z{\7\r"+
		"\2\2{}\7\21\2\2|z\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0082"+
		"\3\2\2\2\u0080~\3\2\2\2\u0081y\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\u0084\7\30\2\2\u0084\u0085\7\32\2\2\u0085\35\3\2\2\2\u0086"+
		"\u0087\7\6\2\2\u0087\u0088\7\31\2\2\u0088\u0091\7\27\2\2\u0089\u008e\7"+
		"\21\2\2\u008a\u008b\7\r\2\2\u008b\u008d\7\21\2\2\u008c\u008a\3\2\2\2\u008d"+
		"\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0092\3\2"+
		"\2\2\u0090\u008e\3\2\2\2\u0091\u0089\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\u0094\7\30\2\2\u0094\u0095\7\32\2\2\u0095\37\3\2"+
		"\2\2\n#\62nq~\u0081\u008e\u0091";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}