// Generated from com/sap/xsk/parser/hdbsequence/core/Hdbsequence.g4 by ANTLR 4.9.3
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
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, STRING=13, INT=14, BOOLEAN=15, TRUE=16, FALSE=17, 
		WS=18, LB=19, RB=20, EQ=21, SC=22, SIGNED_INT=23, LINE_COMMENT=24, COMMENT=25;
	public static final int
		RULE_hdbsequence = 0, RULE_property = 1, RULE_schema = 2, RULE_increment_by = 3, 
		RULE_start_with = 4, RULE_maxvalue = 5, RULE_nomaxvalue = 6, RULE_minvalue = 7, 
		RULE_nominvalue = 8, RULE_cycles = 9, RULE_reset_by = 10, RULE_publicc = 11, 
		RULE_dependsOnTable = 12, RULE_dependsOnView = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"hdbsequence", "property", "schema", "increment_by", "start_with", "maxvalue", 
			"nomaxvalue", "minvalue", "nominvalue", "cycles", "reset_by", "publicc", 
			"dependsOnTable", "dependsOnView"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'schema'", "'increment_by'", "'start_with'", "'maxvalue'", "'nomaxvalue'", 
			"'minvalue'", "'nominvalue'", "'cycles'", "'reset_by'", "'public'", "'depends_on_table'", 
			"'depends_on_view'", null, null, null, "'true'", "'false'", null, "'['", 
			"']'", "'='", "';'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "STRING", "INT", "BOOLEAN", "TRUE", "FALSE", "WS", "LB", "RB", 
			"EQ", "SC", "SIGNED_INT", "LINE_COMMENT", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Hdbsequence.g4"; }

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
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
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
			setState(29); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(28);
				property();
				}
				}
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0) );
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
		public SchemaContext schema() {
			return getRuleContext(SchemaContext.class,0);
		}
		public Increment_byContext increment_by() {
			return getRuleContext(Increment_byContext.class,0);
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
		public MinvalueContext minvalue() {
			return getRuleContext(MinvalueContext.class,0);
		}
		public NominvalueContext nominvalue() {
			return getRuleContext(NominvalueContext.class,0);
		}
		public CyclesContext cycles() {
			return getRuleContext(CyclesContext.class,0);
		}
		public Reset_byContext reset_by() {
			return getRuleContext(Reset_byContext.class,0);
		}
		public PubliccContext publicc() {
			return getRuleContext(PubliccContext.class,0);
		}
		public DependsOnTableContext dependsOnTable() {
			return getRuleContext(DependsOnTableContext.class,0);
		}
		public DependsOnViewContext dependsOnView() {
			return getRuleContext(DependsOnViewContext.class,0);
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
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(33);
				schema();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				increment_by();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(35);
				start_with();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(36);
				maxvalue();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 5);
				{
				setState(37);
				nomaxvalue();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 6);
				{
				setState(38);
				minvalue();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 7);
				{
				setState(39);
				nominvalue();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 8);
				{
				setState(40);
				cycles();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 9);
				{
				setState(41);
				reset_by();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 10);
				{
				setState(42);
				publicc();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 11);
				{
				setState(43);
				dependsOnTable();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 12);
				{
				setState(44);
				dependsOnView();
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
			setState(47);
			match(T__0);
			setState(48);
			match(EQ);
			setState(49);
			match(STRING);
			setState(50);
			match(SC);
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
			setState(52);
			match(T__1);
			setState(53);
			match(EQ);
			setState(54);
			match(INT);
			setState(55);
			match(SC);
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
			setState(57);
			match(T__2);
			setState(58);
			match(EQ);
			setState(59);
			match(INT);
			setState(60);
			match(SC);
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
			setState(62);
			match(T__3);
			setState(63);
			match(EQ);
			setState(64);
			match(INT);
			setState(65);
			match(SC);
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
			setState(67);
			match(T__4);
			setState(68);
			match(EQ);
			setState(69);
			match(BOOLEAN);
			setState(70);
			match(SC);
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
			setState(72);
			match(T__5);
			setState(73);
			match(EQ);
			setState(74);
			match(INT);
			setState(75);
			match(SC);
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
			setState(77);
			match(T__6);
			setState(78);
			match(EQ);
			setState(79);
			match(BOOLEAN);
			setState(80);
			match(SC);
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
			setState(82);
			match(T__7);
			setState(83);
			match(EQ);
			setState(84);
			match(BOOLEAN);
			setState(85);
			match(SC);
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
			setState(87);
			match(T__8);
			setState(88);
			match(EQ);
			setState(89);
			match(STRING);
			setState(90);
			match(SC);
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
			setState(92);
			match(T__9);
			setState(93);
			match(EQ);
			setState(94);
			match(BOOLEAN);
			setState(95);
			match(SC);
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
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbsequenceParser.STRING, 0); }
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
		enterRule(_localctx, 24, RULE_dependsOnTable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(T__10);
			setState(98);
			match(EQ);
			setState(99);
			match(STRING);
			setState(100);
			match(SC);
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
		public TerminalNode EQ() { return getToken(HdbsequenceParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbsequenceParser.STRING, 0); }
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
		enterRule(_localctx, 26, RULE_dependsOnView);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(T__11);
			setState(103);
			match(EQ);
			setState(104);
			match(STRING);
			setState(105);
			match(SC);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33n\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\6\2 \n\2\r\2\16\2!\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\60\n\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\2\2\2k\2\37\3\2\2\2\4/\3\2\2\2\6\61\3\2\2\2\b\66\3\2\2\2\n;\3\2\2\2\f"+
		"@\3\2\2\2\16E\3\2\2\2\20J\3\2\2\2\22O\3\2\2\2\24T\3\2\2\2\26Y\3\2\2\2"+
		"\30^\3\2\2\2\32c\3\2\2\2\34h\3\2\2\2\36 \5\4\3\2\37\36\3\2\2\2 !\3\2\2"+
		"\2!\37\3\2\2\2!\"\3\2\2\2\"\3\3\2\2\2#\60\5\6\4\2$\60\5\b\5\2%\60\5\n"+
		"\6\2&\60\5\f\7\2\'\60\5\16\b\2(\60\5\20\t\2)\60\5\22\n\2*\60\5\24\13\2"+
		"+\60\5\26\f\2,\60\5\30\r\2-\60\5\32\16\2.\60\5\34\17\2/#\3\2\2\2/$\3\2"+
		"\2\2/%\3\2\2\2/&\3\2\2\2/\'\3\2\2\2/(\3\2\2\2/)\3\2\2\2/*\3\2\2\2/+\3"+
		"\2\2\2/,\3\2\2\2/-\3\2\2\2/.\3\2\2\2\60\5\3\2\2\2\61\62\7\3\2\2\62\63"+
		"\7\27\2\2\63\64\7\17\2\2\64\65\7\30\2\2\65\7\3\2\2\2\66\67\7\4\2\2\67"+
		"8\7\27\2\289\7\20\2\29:\7\30\2\2:\t\3\2\2\2;<\7\5\2\2<=\7\27\2\2=>\7\20"+
		"\2\2>?\7\30\2\2?\13\3\2\2\2@A\7\6\2\2AB\7\27\2\2BC\7\20\2\2CD\7\30\2\2"+
		"D\r\3\2\2\2EF\7\7\2\2FG\7\27\2\2GH\7\21\2\2HI\7\30\2\2I\17\3\2\2\2JK\7"+
		"\b\2\2KL\7\27\2\2LM\7\20\2\2MN\7\30\2\2N\21\3\2\2\2OP\7\t\2\2PQ\7\27\2"+
		"\2QR\7\21\2\2RS\7\30\2\2S\23\3\2\2\2TU\7\n\2\2UV\7\27\2\2VW\7\21\2\2W"+
		"X\7\30\2\2X\25\3\2\2\2YZ\7\13\2\2Z[\7\27\2\2[\\\7\17\2\2\\]\7\30\2\2]"+
		"\27\3\2\2\2^_\7\f\2\2_`\7\27\2\2`a\7\21\2\2ab\7\30\2\2b\31\3\2\2\2cd\7"+
		"\r\2\2de\7\27\2\2ef\7\17\2\2fg\7\30\2\2g\33\3\2\2\2hi\7\16\2\2ij\7\27"+
		"\2\2jk\7\17\2\2kl\7\30\2\2l\35\3\2\2\2\4!/";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}