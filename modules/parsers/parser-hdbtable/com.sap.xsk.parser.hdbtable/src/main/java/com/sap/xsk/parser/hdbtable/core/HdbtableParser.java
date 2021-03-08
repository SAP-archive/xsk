// Generated from com\sap\xsk\parser\hdbtable\core\Hdbtable.g4 by ANTLR 4.3
package com.sap.xsk.parser.hdbtable.core;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbtableParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__26=1, T__25=2, T__24=3, T__23=4, T__22=5, T__21=6, T__20=7, T__19=8, 
		T__18=9, T__17=10, T__16=11, T__15=12, T__14=13, T__13=14, T__12=15, T__11=16, 
		T__10=17, T__9=18, T__8=19, T__7=20, T__6=21, T__5=22, T__4=23, T__3=24, 
		T__2=25, T__1=26, T__0=27, STRING=28, WS=29, TABLE=30, DOT=31, EQ=32, 
		SEMICOLON=33, SQLTYPES=34, BOOLEAN=35, ORDER=36, INDEXTYPE=37, INT=38, 
		TABLETYPE=39, TABLELOGGINGTYPE=40;
	public static final String[] tokenNames = {
		"<INVALID>", "'loggingType'", "'sqlType'", "'name'", "'length'", "'scale'", 
		"'tableType'", "'{'", "'order'", "'}'", "'indexes'", "'nullable'", "'pkcolumns'", 
		"'indexType'", "'indexColumns'", "'description'", "','", "'unique'", "'primaryKey'", 
		"'columns'", "'precision'", "'defaultValue'", "'['", "']'", "'public'", 
		"'comment'", "'temporary'", "'schemaName'", "STRING", "WS", "'table'", 
		"'.'", "'='", "';'", "SQLTYPES", "BOOLEAN", "ORDER", "INDEXTYPE", "INT", 
		"TABLETYPE", "TABLELOGGINGTYPE"
	};
	public static final int
		RULE_hdbtableDefinition = 0, RULE_schemaNameProp = 1, RULE_temporaryProp = 2, 
		RULE_tableTypeProp = 3, RULE_publicProp = 4, RULE_loggingTypeProp = 5, 
		RULE_tableColumnsProp = 6, RULE_tableIndexesProp = 7, RULE_tablePrimaryKeyProp = 8, 
		RULE_descriptionProp = 9, RULE_columnsObject = 10, RULE_indexesObject = 11, 
		RULE_columnAssignName = 12, RULE_columnAssignSQLType = 13, RULE_columnAssignNullable = 14, 
		RULE_columnAssignLength = 15, RULE_columnAssignComment = 16, RULE_columnAssignDefaultValue = 17, 
		RULE_columnAssignPrecision = 18, RULE_columnAssignScale = 19, RULE_indexAssignName = 20, 
		RULE_indexAssignUnique = 21, RULE_indexAssignOrder = 22, RULE_indexAssignIndexColumns = 23, 
		RULE_indexAssignIndexType = 24, RULE_indexColumnsArray = 25;
	public static final String[] ruleNames = {
		"hdbtableDefinition", "schemaNameProp", "temporaryProp", "tableTypeProp", 
		"publicProp", "loggingTypeProp", "tableColumnsProp", "tableIndexesProp", 
		"tablePrimaryKeyProp", "descriptionProp", "columnsObject", "indexesObject", 
		"columnAssignName", "columnAssignSQLType", "columnAssignNullable", "columnAssignLength", 
		"columnAssignComment", "columnAssignDefaultValue", "columnAssignPrecision", 
		"columnAssignScale", "indexAssignName", "indexAssignUnique", "indexAssignOrder", 
		"indexAssignIndexColumns", "indexAssignIndexType", "indexColumnsArray"
	};

	@Override
	public String getGrammarFileName() { return "Hdbtable.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HdbtableParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class HdbtableDefinitionContext extends ParserRuleContext {
		public LoggingTypePropContext loggingTypeProp() {
			return getRuleContext(LoggingTypePropContext.class,0);
		}
		public PublicPropContext publicProp() {
			return getRuleContext(PublicPropContext.class,0);
		}
		public TableIndexesPropContext tableIndexesProp() {
			return getRuleContext(TableIndexesPropContext.class,0);
		}
		public TemporaryPropContext temporaryProp() {
			return getRuleContext(TemporaryPropContext.class,0);
		}
		public DescriptionPropContext descriptionProp() {
			return getRuleContext(DescriptionPropContext.class,0);
		}
		public TableTypePropContext tableTypeProp() {
			return getRuleContext(TableTypePropContext.class,0);
		}
		public TableColumnsPropContext tableColumnsProp() {
			return getRuleContext(TableColumnsPropContext.class,0);
		}
		public SchemaNamePropContext schemaNameProp() {
			return getRuleContext(SchemaNamePropContext.class,0);
		}
		public TablePrimaryKeyPropContext tablePrimaryKeyProp() {
			return getRuleContext(TablePrimaryKeyPropContext.class,0);
		}
		public HdbtableDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hdbtableDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterHdbtableDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitHdbtableDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitHdbtableDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HdbtableDefinitionContext hdbtableDefinition() throws RecognitionException {
		HdbtableDefinitionContext _localctx = new HdbtableDefinitionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_hdbtableDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52); schemaNameProp();
			setState(54);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(53); temporaryProp();
				}
				break;
			}
			setState(57);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(56); tableTypeProp();
				}
				break;
			}
			setState(60);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(59); publicProp();
				}
				break;
			}
			setState(63);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(62); loggingTypeProp();
				}
				break;
			}
			setState(65); tableColumnsProp();
			setState(67);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(66); tableIndexesProp();
				}
				break;
			}
			setState(70);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(69); tablePrimaryKeyProp();
				}
				break;
			}
			setState(73);
			_la = _input.LA(1);
			if (_la==TABLE) {
				{
				setState(72); descriptionProp();
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

	public static class SchemaNamePropContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbtableParser.STRING, 0); }
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public SchemaNamePropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schemaNameProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterSchemaNameProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitSchemaNameProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitSchemaNameProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaNamePropContext schemaNameProp() throws RecognitionException {
		SchemaNamePropContext _localctx = new SchemaNamePropContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_schemaNameProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); match(TABLE);
			setState(76); match(DOT);
			setState(77); match(T__0);
			setState(78); match(EQ);
			setState(79); match(STRING);
			setState(80); match(SEMICOLON);
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

	public static class TemporaryPropContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbtableParser.BOOLEAN, 0); }
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TemporaryPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_temporaryProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTemporaryProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTemporaryProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTemporaryProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemporaryPropContext temporaryProp() throws RecognitionException {
		TemporaryPropContext _localctx = new TemporaryPropContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_temporaryProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); match(TABLE);
			setState(83); match(DOT);
			setState(84); match(T__1);
			setState(85); match(EQ);
			setState(86); match(BOOLEAN);
			setState(87); match(SEMICOLON);
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

	public static class TableTypePropContext extends ParserRuleContext {
		public TerminalNode TABLETYPE() { return getToken(HdbtableParser.TABLETYPE, 0); }
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TableTypePropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableTypeProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTableTypeProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTableTypeProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTableTypeProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableTypePropContext tableTypeProp() throws RecognitionException {
		TableTypePropContext _localctx = new TableTypePropContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tableTypeProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89); match(TABLE);
			setState(90); match(DOT);
			setState(91); match(T__21);
			setState(92); match(EQ);
			setState(93); match(TABLETYPE);
			setState(94); match(SEMICOLON);
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

	public static class PublicPropContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbtableParser.BOOLEAN, 0); }
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public PublicPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_publicProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterPublicProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitPublicProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitPublicProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PublicPropContext publicProp() throws RecognitionException {
		PublicPropContext _localctx = new PublicPropContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_publicProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(TABLE);
			setState(97); match(DOT);
			setState(98); match(T__3);
			setState(99); match(EQ);
			setState(100); match(BOOLEAN);
			setState(101); match(SEMICOLON);
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

	public static class LoggingTypePropContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode TABLELOGGINGTYPE() { return getToken(HdbtableParser.TABLELOGGINGTYPE, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public LoggingTypePropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loggingTypeProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterLoggingTypeProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitLoggingTypeProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitLoggingTypeProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoggingTypePropContext loggingTypeProp() throws RecognitionException {
		LoggingTypePropContext _localctx = new LoggingTypePropContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_loggingTypeProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); match(TABLE);
			setState(104); match(DOT);
			setState(105); match(T__26);
			setState(106); match(EQ);
			setState(107); match(TABLELOGGINGTYPE);
			setState(108); match(SEMICOLON);
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

	public static class TableColumnsPropContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public ColumnsObjectContext columnsObject(int i) {
			return getRuleContext(ColumnsObjectContext.class,i);
		}
		public List<ColumnsObjectContext> columnsObject() {
			return getRuleContexts(ColumnsObjectContext.class);
		}
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TableColumnsPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableColumnsProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTableColumnsProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTableColumnsProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTableColumnsProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableColumnsPropContext tableColumnsProp() throws RecognitionException {
		TableColumnsPropContext _localctx = new TableColumnsPropContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_tableColumnsProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); match(TABLE);
			setState(111); match(DOT);
			setState(112); match(T__8);
			setState(113); match(EQ);
			setState(114); match(T__5);
			setState(123);
			_la = _input.LA(1);
			if (_la==T__20) {
				{
				setState(115); columnsObject();
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(116); match(T__11);
					setState(117); columnsObject();
					}
					}
					setState(122);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(125); match(T__4);
			setState(126); match(SEMICOLON);
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

	public static class TableIndexesPropContext extends ParserRuleContext {
		public List<IndexesObjectContext> indexesObject() {
			return getRuleContexts(IndexesObjectContext.class);
		}
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public IndexesObjectContext indexesObject(int i) {
			return getRuleContext(IndexesObjectContext.class,i);
		}
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TableIndexesPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableIndexesProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTableIndexesProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTableIndexesProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTableIndexesProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableIndexesPropContext tableIndexesProp() throws RecognitionException {
		TableIndexesPropContext _localctx = new TableIndexesPropContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_tableIndexesProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); match(TABLE);
			setState(129); match(DOT);
			setState(130); match(T__17);
			setState(131); match(EQ);
			setState(132); match(T__5);
			setState(133); indexesObject();
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(134); match(T__11);
				setState(135); indexesObject();
				}
				}
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(141); match(T__4);
			setState(142); match(SEMICOLON);
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

	public static class TablePrimaryKeyPropContext extends ParserRuleContext {
		public List<TerminalNode> DOT() { return getTokens(HdbtableParser.DOT); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode STRING(int i) {
			return getToken(HdbtableParser.STRING, i);
		}
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public List<TerminalNode> STRING() { return getTokens(HdbtableParser.STRING); }
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TerminalNode DOT(int i) {
			return getToken(HdbtableParser.DOT, i);
		}
		public TablePrimaryKeyPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tablePrimaryKeyProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTablePrimaryKeyProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTablePrimaryKeyProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTablePrimaryKeyProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TablePrimaryKeyPropContext tablePrimaryKeyProp() throws RecognitionException {
		TablePrimaryKeyPropContext _localctx = new TablePrimaryKeyPropContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_tablePrimaryKeyProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144); match(TABLE);
			setState(145); match(DOT);
			setState(146); match(T__9);
			setState(147); match(DOT);
			setState(148); match(T__15);
			setState(149); match(EQ);
			setState(150); match(T__5);
			setState(159);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(151); match(STRING);
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(152); match(T__11);
					setState(153); match(STRING);
					}
					}
					setState(158);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(161); match(T__4);
			setState(162); match(SEMICOLON);
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

	public static class DescriptionPropContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(HdbtableParser.DOT, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbtableParser.STRING, 0); }
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public DescriptionPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_descriptionProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterDescriptionProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitDescriptionProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitDescriptionProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionPropContext descriptionProp() throws RecognitionException {
		DescriptionPropContext _localctx = new DescriptionPropContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_descriptionProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); match(TABLE);
			setState(165); match(DOT);
			setState(166); match(T__12);
			setState(167); match(EQ);
			setState(168); match(STRING);
			setState(169); match(SEMICOLON);
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

	public static class ColumnsObjectContext extends ParserRuleContext {
		public ColumnAssignLengthContext columnAssignLength() {
			return getRuleContext(ColumnAssignLengthContext.class,0);
		}
		public ColumnAssignDefaultValueContext columnAssignDefaultValue() {
			return getRuleContext(ColumnAssignDefaultValueContext.class,0);
		}
		public ColumnAssignSQLTypeContext columnAssignSQLType() {
			return getRuleContext(ColumnAssignSQLTypeContext.class,0);
		}
		public ColumnAssignPrecisionContext columnAssignPrecision() {
			return getRuleContext(ColumnAssignPrecisionContext.class,0);
		}
		public ColumnAssignNameContext columnAssignName() {
			return getRuleContext(ColumnAssignNameContext.class,0);
		}
		public ColumnAssignScaleContext columnAssignScale() {
			return getRuleContext(ColumnAssignScaleContext.class,0);
		}
		public ColumnAssignNullableContext columnAssignNullable() {
			return getRuleContext(ColumnAssignNullableContext.class,0);
		}
		public ColumnAssignCommentContext columnAssignComment() {
			return getRuleContext(ColumnAssignCommentContext.class,0);
		}
		public ColumnsObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnsObject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnsObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnsObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnsObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnsObjectContext columnsObject() throws RecognitionException {
		ColumnsObjectContext _localctx = new ColumnsObjectContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_columnsObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); match(T__20);
			setState(172); columnAssignName();
			setState(173); columnAssignSQLType();
			setState(175);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(174); columnAssignNullable();
				}
			}

			setState(178);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(177); columnAssignLength();
				}
			}

			setState(181);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(180); columnAssignComment();
				}
			}

			setState(184);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(183); columnAssignDefaultValue();
				}
			}

			setState(187);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(186); columnAssignPrecision();
				}
			}

			setState(190);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(189); columnAssignScale();
				}
			}

			setState(192); match(T__18);
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

	public static class IndexesObjectContext extends ParserRuleContext {
		public IndexAssignOrderContext indexAssignOrder() {
			return getRuleContext(IndexAssignOrderContext.class,0);
		}
		public IndexAssignIndexTypeContext indexAssignIndexType() {
			return getRuleContext(IndexAssignIndexTypeContext.class,0);
		}
		public IndexAssignUniqueContext indexAssignUnique() {
			return getRuleContext(IndexAssignUniqueContext.class,0);
		}
		public IndexAssignIndexColumnsContext indexAssignIndexColumns() {
			return getRuleContext(IndexAssignIndexColumnsContext.class,0);
		}
		public IndexAssignNameContext indexAssignName() {
			return getRuleContext(IndexAssignNameContext.class,0);
		}
		public IndexesObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexesObject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexesObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexesObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexesObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexesObjectContext indexesObject() throws RecognitionException {
		IndexesObjectContext _localctx = new IndexesObjectContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_indexesObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194); match(T__20);
			setState(195); indexAssignName();
			setState(196); indexAssignUnique();
			setState(198);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(197); indexAssignOrder();
				}
			}

			setState(200); indexAssignIndexColumns();
			setState(202);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(201); indexAssignIndexType();
				}
			}

			setState(204); match(T__18);
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

	public static class ColumnAssignNameContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbtableParser.STRING, 0); }
		public ColumnAssignNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignNameContext columnAssignName() throws RecognitionException {
		ColumnAssignNameContext _localctx = new ColumnAssignNameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_columnAssignName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206); match(T__24);
			setState(207); match(EQ);
			setState(208); match(STRING);
			setState(209); match(SEMICOLON);
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

	public static class ColumnAssignSQLTypeContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode SQLTYPES() { return getToken(HdbtableParser.SQLTYPES, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public ColumnAssignSQLTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignSQLType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignSQLType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignSQLType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignSQLType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignSQLTypeContext columnAssignSQLType() throws RecognitionException {
		ColumnAssignSQLTypeContext _localctx = new ColumnAssignSQLTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_columnAssignSQLType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211); match(T__25);
			setState(212); match(EQ);
			setState(213); match(SQLTYPES);
			setState(214); match(SEMICOLON);
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

	public static class ColumnAssignNullableContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbtableParser.BOOLEAN, 0); }
		public ColumnAssignNullableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignNullable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignNullable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignNullable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignNullable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignNullableContext columnAssignNullable() throws RecognitionException {
		ColumnAssignNullableContext _localctx = new ColumnAssignNullableContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_columnAssignNullable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216); match(T__16);
			setState(217); match(EQ);
			setState(218); match(BOOLEAN);
			setState(219); match(SEMICOLON);
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

	public static class ColumnAssignLengthContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbtableParser.INT, 0); }
		public ColumnAssignLengthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignLength; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignLength(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignLength(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignLength(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignLengthContext columnAssignLength() throws RecognitionException {
		ColumnAssignLengthContext _localctx = new ColumnAssignLengthContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_columnAssignLength);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221); match(T__23);
			setState(222); match(EQ);
			setState(223); match(INT);
			setState(224); match(SEMICOLON);
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

	public static class ColumnAssignCommentContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbtableParser.STRING, 0); }
		public ColumnAssignCommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignComment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignComment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignCommentContext columnAssignComment() throws RecognitionException {
		ColumnAssignCommentContext _localctx = new ColumnAssignCommentContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_columnAssignComment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226); match(T__2);
			setState(227); match(EQ);
			setState(228); match(STRING);
			setState(229); match(SEMICOLON);
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

	public static class ColumnAssignDefaultValueContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbtableParser.STRING, 0); }
		public ColumnAssignDefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignDefaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignDefaultValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignDefaultValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignDefaultValueContext columnAssignDefaultValue() throws RecognitionException {
		ColumnAssignDefaultValueContext _localctx = new ColumnAssignDefaultValueContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_columnAssignDefaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231); match(T__6);
			setState(232); match(EQ);
			setState(233); match(STRING);
			setState(234); match(SEMICOLON);
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

	public static class ColumnAssignPrecisionContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbtableParser.INT, 0); }
		public ColumnAssignPrecisionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignPrecision; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignPrecision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignPrecision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignPrecision(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignPrecisionContext columnAssignPrecision() throws RecognitionException {
		ColumnAssignPrecisionContext _localctx = new ColumnAssignPrecisionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_columnAssignPrecision);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236); match(T__7);
			setState(237); match(EQ);
			setState(238); match(INT);
			setState(239); match(SEMICOLON);
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

	public static class ColumnAssignScaleContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbtableParser.INT, 0); }
		public ColumnAssignScaleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnAssignScale; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterColumnAssignScale(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitColumnAssignScale(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitColumnAssignScale(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnAssignScaleContext columnAssignScale() throws RecognitionException {
		ColumnAssignScaleContext _localctx = new ColumnAssignScaleContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_columnAssignScale);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241); match(T__22);
			setState(242); match(EQ);
			setState(243); match(INT);
			setState(244); match(SEMICOLON);
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

	public static class IndexAssignNameContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode STRING() { return getToken(HdbtableParser.STRING, 0); }
		public IndexAssignNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexAssignName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexAssignName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexAssignName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexAssignName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexAssignNameContext indexAssignName() throws RecognitionException {
		IndexAssignNameContext _localctx = new IndexAssignNameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_indexAssignName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246); match(T__24);
			setState(247); match(EQ);
			setState(248); match(STRING);
			setState(249); match(SEMICOLON);
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

	public static class IndexAssignUniqueContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode BOOLEAN() { return getToken(HdbtableParser.BOOLEAN, 0); }
		public IndexAssignUniqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexAssignUnique; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexAssignUnique(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexAssignUnique(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexAssignUnique(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexAssignUniqueContext indexAssignUnique() throws RecognitionException {
		IndexAssignUniqueContext _localctx = new IndexAssignUniqueContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_indexAssignUnique);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251); match(T__10);
			setState(252); match(EQ);
			setState(253); match(BOOLEAN);
			setState(254); match(SEMICOLON);
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

	public static class IndexAssignOrderContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode ORDER() { return getToken(HdbtableParser.ORDER, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public IndexAssignOrderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexAssignOrder; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexAssignOrder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexAssignOrder(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexAssignOrder(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexAssignOrderContext indexAssignOrder() throws RecognitionException {
		IndexAssignOrderContext _localctx = new IndexAssignOrderContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_indexAssignOrder);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256); match(T__19);
			setState(257); match(EQ);
			setState(258); match(ORDER);
			setState(259); match(SEMICOLON);
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

	public static class IndexAssignIndexColumnsContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public IndexColumnsArrayContext indexColumnsArray() {
			return getRuleContext(IndexColumnsArrayContext.class,0);
		}
		public IndexAssignIndexColumnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexAssignIndexColumns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexAssignIndexColumns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexAssignIndexColumns(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexAssignIndexColumns(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexAssignIndexColumnsContext indexAssignIndexColumns() throws RecognitionException {
		IndexAssignIndexColumnsContext _localctx = new IndexAssignIndexColumnsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_indexAssignIndexColumns);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261); match(T__13);
			setState(262); match(EQ);
			setState(263); indexColumnsArray();
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

	public static class IndexAssignIndexTypeContext extends ParserRuleContext {
		public TerminalNode INDEXTYPE() { return getToken(HdbtableParser.INDEXTYPE, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public IndexAssignIndexTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexAssignIndexType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexAssignIndexType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexAssignIndexType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexAssignIndexType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexAssignIndexTypeContext indexAssignIndexType() throws RecognitionException {
		IndexAssignIndexTypeContext _localctx = new IndexAssignIndexTypeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_indexAssignIndexType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265); match(T__14);
			setState(266); match(EQ);
			setState(267); match(INDEXTYPE);
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

	public static class IndexColumnsArrayContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode STRING(int i) {
			return getToken(HdbtableParser.STRING, i);
		}
		public List<TerminalNode> STRING() { return getTokens(HdbtableParser.STRING); }
		public IndexColumnsArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexColumnsArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterIndexColumnsArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitIndexColumnsArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitIndexColumnsArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexColumnsArrayContext indexColumnsArray() throws RecognitionException {
		IndexColumnsArrayContext _localctx = new IndexColumnsArrayContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_indexColumnsArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269); match(T__5);
			setState(278);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(270); match(STRING);
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(271); match(T__11);
					setState(272); match(STRING);
					}
					}
					setState(277);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(280); match(T__4);
			setState(281); match(SEMICOLON);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3*\u011e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\3\2\5\29\n\2\3\2\5\2<\n\2\3\2\5\2?\n\2\3\2\5"+
		"\2B\n\2\3\2\3\2\5\2F\n\2\3\2\5\2I\n\2\3\2\5\2L\n\2\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\7\by\n\b\f\b\16\b|\13\b\5\b~\n\b\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\7\t\u008b\n\t\f\t\16\t\u008e\13\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u009d\n\n\f\n\16\n\u00a0\13\n"+
		"\5\n\u00a2\n\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\5\f\u00b2\n\f\3\f\5\f\u00b5\n\f\3\f\5\f\u00b8\n\f\3\f\5\f\u00bb"+
		"\n\f\3\f\5\f\u00be\n\f\3\f\5\f\u00c1\n\f\3\f\3\f\3\r\3\r\3\r\3\r\5\r\u00c9"+
		"\n\r\3\r\3\r\5\r\u00cd\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\3\33\7\33\u0114\n\33\f\33\16\33\u0117\13\33\5\33\u0119"+
		"\n\33\3\33\3\33\3\33\3\33\2\2\34\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\2\2\u0119\2\66\3\2\2\2\4M\3\2\2\2\6T\3\2\2\2\b[\3\2"+
		"\2\2\nb\3\2\2\2\fi\3\2\2\2\16p\3\2\2\2\20\u0082\3\2\2\2\22\u0092\3\2\2"+
		"\2\24\u00a6\3\2\2\2\26\u00ad\3\2\2\2\30\u00c4\3\2\2\2\32\u00d0\3\2\2\2"+
		"\34\u00d5\3\2\2\2\36\u00da\3\2\2\2 \u00df\3\2\2\2\"\u00e4\3\2\2\2$\u00e9"+
		"\3\2\2\2&\u00ee\3\2\2\2(\u00f3\3\2\2\2*\u00f8\3\2\2\2,\u00fd\3\2\2\2."+
		"\u0102\3\2\2\2\60\u0107\3\2\2\2\62\u010b\3\2\2\2\64\u010f\3\2\2\2\668"+
		"\5\4\3\2\679\5\6\4\28\67\3\2\2\289\3\2\2\29;\3\2\2\2:<\5\b\5\2;:\3\2\2"+
		"\2;<\3\2\2\2<>\3\2\2\2=?\5\n\6\2>=\3\2\2\2>?\3\2\2\2?A\3\2\2\2@B\5\f\7"+
		"\2A@\3\2\2\2AB\3\2\2\2BC\3\2\2\2CE\5\16\b\2DF\5\20\t\2ED\3\2\2\2EF\3\2"+
		"\2\2FH\3\2\2\2GI\5\22\n\2HG\3\2\2\2HI\3\2\2\2IK\3\2\2\2JL\5\24\13\2KJ"+
		"\3\2\2\2KL\3\2\2\2L\3\3\2\2\2MN\7 \2\2NO\7!\2\2OP\7\35\2\2PQ\7\"\2\2Q"+
		"R\7\36\2\2RS\7#\2\2S\5\3\2\2\2TU\7 \2\2UV\7!\2\2VW\7\34\2\2WX\7\"\2\2"+
		"XY\7%\2\2YZ\7#\2\2Z\7\3\2\2\2[\\\7 \2\2\\]\7!\2\2]^\7\b\2\2^_\7\"\2\2"+
		"_`\7)\2\2`a\7#\2\2a\t\3\2\2\2bc\7 \2\2cd\7!\2\2de\7\32\2\2ef\7\"\2\2f"+
		"g\7%\2\2gh\7#\2\2h\13\3\2\2\2ij\7 \2\2jk\7!\2\2kl\7\3\2\2lm\7\"\2\2mn"+
		"\7*\2\2no\7#\2\2o\r\3\2\2\2pq\7 \2\2qr\7!\2\2rs\7\25\2\2st\7\"\2\2t}\7"+
		"\30\2\2uz\5\26\f\2vw\7\22\2\2wy\5\26\f\2xv\3\2\2\2y|\3\2\2\2zx\3\2\2\2"+
		"z{\3\2\2\2{~\3\2\2\2|z\3\2\2\2}u\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080"+
		"\7\31\2\2\u0080\u0081\7#\2\2\u0081\17\3\2\2\2\u0082\u0083\7 \2\2\u0083"+
		"\u0084\7!\2\2\u0084\u0085\7\f\2\2\u0085\u0086\7\"\2\2\u0086\u0087\7\30"+
		"\2\2\u0087\u008c\5\30\r\2\u0088\u0089\7\22\2\2\u0089\u008b\5\30\r\2\u008a"+
		"\u0088\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2"+
		"\2\2\u008d\u008f\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0090\7\31\2\2\u0090"+
		"\u0091\7#\2\2\u0091\21\3\2\2\2\u0092\u0093\7 \2\2\u0093\u0094\7!\2\2\u0094"+
		"\u0095\7\24\2\2\u0095\u0096\7!\2\2\u0096\u0097\7\16\2\2\u0097\u0098\7"+
		"\"\2\2\u0098\u00a1\7\30\2\2\u0099\u009e\7\36\2\2\u009a\u009b\7\22\2\2"+
		"\u009b\u009d\7\36\2\2\u009c\u009a\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c"+
		"\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1"+
		"\u0099\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\7\31"+
		"\2\2\u00a4\u00a5\7#\2\2\u00a5\23\3\2\2\2\u00a6\u00a7\7 \2\2\u00a7\u00a8"+
		"\7!\2\2\u00a8\u00a9\7\21\2\2\u00a9\u00aa\7\"\2\2\u00aa\u00ab\7\36\2\2"+
		"\u00ab\u00ac\7#\2\2\u00ac\25\3\2\2\2\u00ad\u00ae\7\t\2\2\u00ae\u00af\5"+
		"\32\16\2\u00af\u00b1\5\34\17\2\u00b0\u00b2\5\36\20\2\u00b1\u00b0\3\2\2"+
		"\2\u00b1\u00b2\3\2\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00b5\5 \21\2\u00b4\u00b3"+
		"\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b8\5\"\22\2"+
		"\u00b7\u00b6\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00bb"+
		"\5$\23\2\u00ba\u00b9\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bd\3\2\2\2\u00bc"+
		"\u00be\5&\24\2\u00bd\u00bc\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00c0\3\2"+
		"\2\2\u00bf\u00c1\5(\25\2\u00c0\u00bf\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\u00c2\3\2\2\2\u00c2\u00c3\7\13\2\2\u00c3\27\3\2\2\2\u00c4\u00c5\7\t\2"+
		"\2\u00c5\u00c6\5*\26\2\u00c6\u00c8\5,\27\2\u00c7\u00c9\5.\30\2\u00c8\u00c7"+
		"\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\5\60\31\2"+
		"\u00cb\u00cd\5\62\32\2\u00cc\u00cb\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce"+
		"\3\2\2\2\u00ce\u00cf\7\13\2\2\u00cf\31\3\2\2\2\u00d0\u00d1\7\5\2\2\u00d1"+
		"\u00d2\7\"\2\2\u00d2\u00d3\7\36\2\2\u00d3\u00d4\7#\2\2\u00d4\33\3\2\2"+
		"\2\u00d5\u00d6\7\4\2\2\u00d6\u00d7\7\"\2\2\u00d7\u00d8\7$\2\2\u00d8\u00d9"+
		"\7#\2\2\u00d9\35\3\2\2\2\u00da\u00db\7\r\2\2\u00db\u00dc\7\"\2\2\u00dc"+
		"\u00dd\7%\2\2\u00dd\u00de\7#\2\2\u00de\37\3\2\2\2\u00df\u00e0\7\6\2\2"+
		"\u00e0\u00e1\7\"\2\2\u00e1\u00e2\7(\2\2\u00e2\u00e3\7#\2\2\u00e3!\3\2"+
		"\2\2\u00e4\u00e5\7\33\2\2\u00e5\u00e6\7\"\2\2\u00e6\u00e7\7\36\2\2\u00e7"+
		"\u00e8\7#\2\2\u00e8#\3\2\2\2\u00e9\u00ea\7\27\2\2\u00ea\u00eb\7\"\2\2"+
		"\u00eb\u00ec\7\36\2\2\u00ec\u00ed\7#\2\2\u00ed%\3\2\2\2\u00ee\u00ef\7"+
		"\26\2\2\u00ef\u00f0\7\"\2\2\u00f0\u00f1\7(\2\2\u00f1\u00f2\7#\2\2\u00f2"+
		"\'\3\2\2\2\u00f3\u00f4\7\7\2\2\u00f4\u00f5\7\"\2\2\u00f5\u00f6\7(\2\2"+
		"\u00f6\u00f7\7#\2\2\u00f7)\3\2\2\2\u00f8\u00f9\7\5\2\2\u00f9\u00fa\7\""+
		"\2\2\u00fa\u00fb\7\36\2\2\u00fb\u00fc\7#\2\2\u00fc+\3\2\2\2\u00fd\u00fe"+
		"\7\23\2\2\u00fe\u00ff\7\"\2\2\u00ff\u0100\7%\2\2\u0100\u0101\7#\2\2\u0101"+
		"-\3\2\2\2\u0102\u0103\7\n\2\2\u0103\u0104\7\"\2\2\u0104\u0105\7&\2\2\u0105"+
		"\u0106\7#\2\2\u0106/\3\2\2\2\u0107\u0108\7\20\2\2\u0108\u0109\7\"\2\2"+
		"\u0109\u010a\5\64\33\2\u010a\61\3\2\2\2\u010b\u010c\7\17\2\2\u010c\u010d"+
		"\7\"\2\2\u010d\u010e\7\'\2\2\u010e\63\3\2\2\2\u010f\u0118\7\30\2\2\u0110"+
		"\u0115\7\36\2\2\u0111\u0112\7\22\2\2\u0112\u0114\7\36\2\2\u0113\u0111"+
		"\3\2\2\2\u0114\u0117\3\2\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116"+
		"\u0119\3\2\2\2\u0117\u0115\3\2\2\2\u0118\u0110\3\2\2\2\u0118\u0119\3\2"+
		"\2\2\u0119\u011a\3\2\2\2\u011a\u011b\7\31\2\2\u011b\u011c\7#\2\2\u011c"+
		"\65\3\2\2\2\308;>AEHKz}\u008c\u009e\u00a1\u00b1\u00b4\u00b7\u00ba\u00bd"+
		"\u00c0\u00c8\u00cc\u0115\u0118";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}