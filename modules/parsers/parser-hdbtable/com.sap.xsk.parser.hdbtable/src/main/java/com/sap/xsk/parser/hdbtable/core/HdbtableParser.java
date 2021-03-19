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
		TABLETYPE=39, TABLELOGGINGTYPE=40, LINE_COMMENT=41, COMMENT=42;
	public static final String[] tokenNames = {
		"<INVALID>", "'loggingType'", "'sqlType'", "'name'", "'length'", "'scale'", 
		"'tableType'", "'{'", "'order'", "'}'", "'indexes'", "'nullable'", "'pkcolumns'", 
		"'indexType'", "'indexColumns'", "'description'", "','", "'unique'", "'primaryKey'", 
		"'columns'", "'precision'", "'defaultValue'", "'['", "']'", "'public'", 
		"'comment'", "'temporary'", "'schemaName'", "STRING", "WS", "'table'", 
		"'.'", "'='", "';'", "SQLTYPES", "BOOLEAN", "ORDER", "INDEXTYPE", "INT", 
		"TABLETYPE", "TABLELOGGINGTYPE", "LINE_COMMENT", "COMMENT"
	};
	public static final int
		RULE_hdbtableDefinition = 0, RULE_schemaNameProp = 1, RULE_temporaryProp = 2, 
		RULE_tableTypeProp = 3, RULE_publicProp = 4, RULE_loggingTypeProp = 5, 
		RULE_tableColumnsProp = 6, RULE_tableIndexesProp = 7, RULE_tablePrimaryKeyProp = 8, 
		RULE_tablePrimaryKeyColumnsProp = 9, RULE_tablePrimaryKeyIndexTypeProp = 10, 
		RULE_descriptionProp = 11, RULE_columnsObject = 12, RULE_indexesObject = 13, 
		RULE_columnAssignName = 14, RULE_columnAssignSQLType = 15, RULE_columnAssignNullable = 16, 
		RULE_columnAssignLength = 17, RULE_columnAssignComment = 18, RULE_columnAssignDefaultValue = 19, 
		RULE_columnAssignPrecision = 20, RULE_columnAssignScale = 21, RULE_indexAssignName = 22, 
		RULE_indexAssignUnique = 23, RULE_indexAssignOrder = 24, RULE_indexAssignIndexColumns = 25, 
		RULE_indexAssignIndexType = 26, RULE_indexColumnsArray = 27;
	public static final String[] ruleNames = {
		"hdbtableDefinition", "schemaNameProp", "temporaryProp", "tableTypeProp", 
		"publicProp", "loggingTypeProp", "tableColumnsProp", "tableIndexesProp", 
		"tablePrimaryKeyProp", "tablePrimaryKeyColumnsProp", "tablePrimaryKeyIndexTypeProp", 
		"descriptionProp", "columnsObject", "indexesObject", "columnAssignName", 
		"columnAssignSQLType", "columnAssignNullable", "columnAssignLength", "columnAssignComment", 
		"columnAssignDefaultValue", "columnAssignPrecision", "columnAssignScale", 
		"indexAssignName", "indexAssignUnique", "indexAssignOrder", "indexAssignIndexColumns", 
		"indexAssignIndexType", "indexColumnsArray"
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
		public TablePrimaryKeyIndexTypePropContext tablePrimaryKeyIndexTypeProp() {
			return getRuleContext(TablePrimaryKeyIndexTypePropContext.class,0);
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
			setState(56); schemaNameProp();
			setState(58);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(57); temporaryProp();
				}
				break;
			}
			setState(61);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(60); tableTypeProp();
				}
				break;
			}
			setState(64);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(63); publicProp();
				}
				break;
			}
			setState(67);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(66); loggingTypeProp();
				}
				break;
			}
			setState(69); tableColumnsProp();
			setState(71);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(70); tableIndexesProp();
				}
				break;
			}
			setState(74);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(73); tablePrimaryKeyProp();
				}
				break;
			}
			setState(77);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(76); tablePrimaryKeyIndexTypeProp();
				}
				break;
			}
			setState(80);
			_la = _input.LA(1);
			if (_la==TABLE) {
				{
				setState(79); descriptionProp();
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
			setState(82); match(TABLE);
			setState(83); match(DOT);
			setState(84); match(T__0);
			setState(85); match(EQ);
			setState(86); match(STRING);
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
			setState(89); match(TABLE);
			setState(90); match(DOT);
			setState(91); match(T__1);
			setState(92); match(EQ);
			setState(93); match(BOOLEAN);
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
			setState(96); match(TABLE);
			setState(97); match(DOT);
			setState(98); match(T__21);
			setState(99); match(EQ);
			setState(100); match(TABLETYPE);
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
			setState(103); match(TABLE);
			setState(104); match(DOT);
			setState(105); match(T__3);
			setState(106); match(EQ);
			setState(107); match(BOOLEAN);
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
			setState(110); match(TABLE);
			setState(111); match(DOT);
			setState(112); match(T__26);
			setState(113); match(EQ);
			setState(114); match(TABLELOGGINGTYPE);
			setState(115); match(SEMICOLON);
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
			setState(117); match(TABLE);
			setState(118); match(DOT);
			setState(119); match(T__8);
			setState(120); match(EQ);
			setState(121); match(T__5);
			setState(130);
			_la = _input.LA(1);
			if (_la==T__20) {
				{
				setState(122); columnsObject();
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(123); match(T__11);
					setState(124); columnsObject();
					}
					}
					setState(129);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(132); match(T__4);
			setState(133); match(SEMICOLON);
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
			setState(135); match(TABLE);
			setState(136); match(DOT);
			setState(137); match(T__17);
			setState(138); match(EQ);
			setState(139); match(T__5);
			setState(140); indexesObject();
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(141); match(T__11);
				setState(142); indexesObject();
				}
				}
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(148); match(T__4);
			setState(149); match(SEMICOLON);
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
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TablePrimaryKeyColumnsPropContext tablePrimaryKeyColumnsProp() {
			return getRuleContext(TablePrimaryKeyColumnsPropContext.class,0);
		}
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151); match(TABLE);
			setState(152); match(DOT);
			setState(153); match(T__9);
			setState(154); match(DOT);
			setState(155); match(T__15);
			setState(156); match(EQ);
			setState(157); tablePrimaryKeyColumnsProp();
			setState(158); match(SEMICOLON);
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

	public static class TablePrimaryKeyColumnsPropContext extends ParserRuleContext {
		public TerminalNode STRING(int i) {
			return getToken(HdbtableParser.STRING, i);
		}
		public List<TerminalNode> STRING() { return getTokens(HdbtableParser.STRING); }
		public TablePrimaryKeyColumnsPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tablePrimaryKeyColumnsProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTablePrimaryKeyColumnsProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTablePrimaryKeyColumnsProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTablePrimaryKeyColumnsProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TablePrimaryKeyColumnsPropContext tablePrimaryKeyColumnsProp() throws RecognitionException {
		TablePrimaryKeyColumnsPropContext _localctx = new TablePrimaryKeyColumnsPropContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tablePrimaryKeyColumnsProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); match(T__5);
			setState(161); match(STRING);
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(162); match(T__11);
				setState(163); match(STRING);
				}
				}
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(169); match(T__4);
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

	public static class TablePrimaryKeyIndexTypePropContext extends ParserRuleContext {
		public List<TerminalNode> DOT() { return getTokens(HdbtableParser.DOT); }
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
		public TerminalNode INDEXTYPE() { return getToken(HdbtableParser.INDEXTYPE, 0); }
		public TerminalNode EQ() { return getToken(HdbtableParser.EQ, 0); }
		public TerminalNode TABLE() { return getToken(HdbtableParser.TABLE, 0); }
		public TerminalNode DOT(int i) {
			return getToken(HdbtableParser.DOT, i);
		}
		public TablePrimaryKeyIndexTypePropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tablePrimaryKeyIndexTypeProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).enterTablePrimaryKeyIndexTypeProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbtableListener ) ((HdbtableListener)listener).exitTablePrimaryKeyIndexTypeProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbtableVisitor ) return ((HdbtableVisitor<? extends T>)visitor).visitTablePrimaryKeyIndexTypeProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TablePrimaryKeyIndexTypePropContext tablePrimaryKeyIndexTypeProp() throws RecognitionException {
		TablePrimaryKeyIndexTypePropContext _localctx = new TablePrimaryKeyIndexTypePropContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_tablePrimaryKeyIndexTypeProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); match(TABLE);
			setState(172); match(DOT);
			setState(173); match(T__9);
			setState(174); match(DOT);
			setState(175); match(T__14);
			setState(176); match(EQ);
			setState(177); match(INDEXTYPE);
			setState(178); match(SEMICOLON);
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
		enterRule(_localctx, 22, RULE_descriptionProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180); match(TABLE);
			setState(181); match(DOT);
			setState(182); match(T__12);
			setState(183); match(EQ);
			setState(184); match(STRING);
			setState(185); match(SEMICOLON);
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
		enterRule(_localctx, 24, RULE_columnsObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187); match(T__20);
			setState(188); columnAssignName();
			setState(189); columnAssignSQLType();
			setState(191);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(190); columnAssignLength();
				}
			}

			setState(194);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(193); columnAssignNullable();
				}
			}

			setState(197);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(196); columnAssignComment();
				}
			}

			setState(200);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(199); columnAssignDefaultValue();
				}
			}

			setState(203);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(202); columnAssignPrecision();
				}
			}

			setState(206);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(205); columnAssignScale();
				}
			}

			setState(208); match(T__18);
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
		enterRule(_localctx, 26, RULE_indexesObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210); match(T__20);
			setState(211); indexAssignName();
			setState(212); indexAssignUnique();
			setState(214);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(213); indexAssignOrder();
				}
			}

			setState(216); indexAssignIndexColumns();
			setState(218);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(217); indexAssignIndexType();
				}
			}

			setState(220); match(T__18);
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
		enterRule(_localctx, 28, RULE_columnAssignName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222); match(T__24);
			setState(223); match(EQ);
			setState(224); match(STRING);
			setState(225); match(SEMICOLON);
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
		enterRule(_localctx, 30, RULE_columnAssignSQLType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227); match(T__25);
			setState(228); match(EQ);
			setState(229); match(SQLTYPES);
			setState(230); match(SEMICOLON);
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
		enterRule(_localctx, 32, RULE_columnAssignNullable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232); match(T__16);
			setState(233); match(EQ);
			setState(234); match(BOOLEAN);
			setState(235); match(SEMICOLON);
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
		enterRule(_localctx, 34, RULE_columnAssignLength);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237); match(T__23);
			setState(238); match(EQ);
			setState(239); match(INT);
			setState(240); match(SEMICOLON);
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
		enterRule(_localctx, 36, RULE_columnAssignComment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242); match(T__2);
			setState(243); match(EQ);
			setState(244); match(STRING);
			setState(245); match(SEMICOLON);
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
		enterRule(_localctx, 38, RULE_columnAssignDefaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247); match(T__6);
			setState(248); match(EQ);
			setState(249); match(STRING);
			setState(250); match(SEMICOLON);
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
		enterRule(_localctx, 40, RULE_columnAssignPrecision);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252); match(T__7);
			setState(253); match(EQ);
			setState(254); match(INT);
			setState(255); match(SEMICOLON);
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
		enterRule(_localctx, 42, RULE_columnAssignScale);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257); match(T__22);
			setState(258); match(EQ);
			setState(259); match(INT);
			setState(260); match(SEMICOLON);
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
		enterRule(_localctx, 44, RULE_indexAssignName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262); match(T__24);
			setState(263); match(EQ);
			setState(264); match(STRING);
			setState(265); match(SEMICOLON);
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
		enterRule(_localctx, 46, RULE_indexAssignUnique);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267); match(T__10);
			setState(268); match(EQ);
			setState(269); match(BOOLEAN);
			setState(270); match(SEMICOLON);
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
		enterRule(_localctx, 48, RULE_indexAssignOrder);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272); match(T__19);
			setState(273); match(EQ);
			setState(274); match(ORDER);
			setState(275); match(SEMICOLON);
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
		public TerminalNode SEMICOLON() { return getToken(HdbtableParser.SEMICOLON, 0); }
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
		enterRule(_localctx, 50, RULE_indexAssignIndexColumns);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277); match(T__13);
			setState(278); match(EQ);
			setState(279); indexColumnsArray();
			setState(280); match(SEMICOLON);
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
		enterRule(_localctx, 52, RULE_indexAssignIndexType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282); match(T__14);
			setState(283); match(EQ);
			setState(284); match(INDEXTYPE);
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
		enterRule(_localctx, 54, RULE_indexColumnsArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286); match(T__5);
			setState(295);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(287); match(STRING);
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(288); match(T__11);
					setState(289); match(STRING);
					}
					}
					setState(294);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(297); match(T__4);
			setState(298); match(SEMICOLON);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3,\u012f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\5\2=\n\2\3\2\5\2@\n\2"+
		"\3\2\5\2C\n\2\3\2\5\2F\n\2\3\2\3\2\5\2J\n\2\3\2\5\2M\n\2\3\2\5\2P\n\2"+
		"\3\2\5\2S\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0080\n\b\f\b\16\b"+
		"\u0083\13\b\5\b\u0085\n\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\7\t\u0092\n\t\f\t\16\t\u0095\13\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13\u00a7\n\13\f\13\16\13\u00aa\13\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\5\16\u00c2\n\16\3\16\5\16\u00c5\n\16\3\16\5\16"+
		"\u00c8\n\16\3\16\5\16\u00cb\n\16\3\16\5\16\u00ce\n\16\3\16\5\16\u00d1"+
		"\n\16\3\16\3\16\3\17\3\17\3\17\3\17\5\17\u00d9\n\17\3\17\3\17\5\17\u00dd"+
		"\n\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35"+
		"\3\35\7\35\u0125\n\35\f\35\16\35\u0128\13\35\5\35\u012a\n\35\3\35\3\35"+
		"\3\35\3\35\2\2\36\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668\2\2\u0128\2:\3\2\2\2\4T\3\2\2\2\6[\3\2\2\2\bb\3\2\2\2\ni\3\2\2"+
		"\2\fp\3\2\2\2\16w\3\2\2\2\20\u0089\3\2\2\2\22\u0099\3\2\2\2\24\u00a2\3"+
		"\2\2\2\26\u00ad\3\2\2\2\30\u00b6\3\2\2\2\32\u00bd\3\2\2\2\34\u00d4\3\2"+
		"\2\2\36\u00e0\3\2\2\2 \u00e5\3\2\2\2\"\u00ea\3\2\2\2$\u00ef\3\2\2\2&\u00f4"+
		"\3\2\2\2(\u00f9\3\2\2\2*\u00fe\3\2\2\2,\u0103\3\2\2\2.\u0108\3\2\2\2\60"+
		"\u010d\3\2\2\2\62\u0112\3\2\2\2\64\u0117\3\2\2\2\66\u011c\3\2\2\28\u0120"+
		"\3\2\2\2:<\5\4\3\2;=\5\6\4\2<;\3\2\2\2<=\3\2\2\2=?\3\2\2\2>@\5\b\5\2?"+
		">\3\2\2\2?@\3\2\2\2@B\3\2\2\2AC\5\n\6\2BA\3\2\2\2BC\3\2\2\2CE\3\2\2\2"+
		"DF\5\f\7\2ED\3\2\2\2EF\3\2\2\2FG\3\2\2\2GI\5\16\b\2HJ\5\20\t\2IH\3\2\2"+
		"\2IJ\3\2\2\2JL\3\2\2\2KM\5\22\n\2LK\3\2\2\2LM\3\2\2\2MO\3\2\2\2NP\5\26"+
		"\f\2ON\3\2\2\2OP\3\2\2\2PR\3\2\2\2QS\5\30\r\2RQ\3\2\2\2RS\3\2\2\2S\3\3"+
		"\2\2\2TU\7 \2\2UV\7!\2\2VW\7\35\2\2WX\7\"\2\2XY\7\36\2\2YZ\7#\2\2Z\5\3"+
		"\2\2\2[\\\7 \2\2\\]\7!\2\2]^\7\34\2\2^_\7\"\2\2_`\7%\2\2`a\7#\2\2a\7\3"+
		"\2\2\2bc\7 \2\2cd\7!\2\2de\7\b\2\2ef\7\"\2\2fg\7)\2\2gh\7#\2\2h\t\3\2"+
		"\2\2ij\7 \2\2jk\7!\2\2kl\7\32\2\2lm\7\"\2\2mn\7%\2\2no\7#\2\2o\13\3\2"+
		"\2\2pq\7 \2\2qr\7!\2\2rs\7\3\2\2st\7\"\2\2tu\7*\2\2uv\7#\2\2v\r\3\2\2"+
		"\2wx\7 \2\2xy\7!\2\2yz\7\25\2\2z{\7\"\2\2{\u0084\7\30\2\2|\u0081\5\32"+
		"\16\2}~\7\22\2\2~\u0080\5\32\16\2\177}\3\2\2\2\u0080\u0083\3\2\2\2\u0081"+
		"\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2"+
		"\2\u0084|\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087"+
		"\7\31\2\2\u0087\u0088\7#\2\2\u0088\17\3\2\2\2\u0089\u008a\7 \2\2\u008a"+
		"\u008b\7!\2\2\u008b\u008c\7\f\2\2\u008c\u008d\7\"\2\2\u008d\u008e\7\30"+
		"\2\2\u008e\u0093\5\34\17\2\u008f\u0090\7\22\2\2\u0090\u0092\5\34\17\2"+
		"\u0091\u008f\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094"+
		"\3\2\2\2\u0094\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0097\7\31\2\2"+
		"\u0097\u0098\7#\2\2\u0098\21\3\2\2\2\u0099\u009a\7 \2\2\u009a\u009b\7"+
		"!\2\2\u009b\u009c\7\24\2\2\u009c\u009d\7!\2\2\u009d\u009e\7\16\2\2\u009e"+
		"\u009f\7\"\2\2\u009f\u00a0\5\24\13\2\u00a0\u00a1\7#\2\2\u00a1\23\3\2\2"+
		"\2\u00a2\u00a3\7\30\2\2\u00a3\u00a8\7\36\2\2\u00a4\u00a5\7\22\2\2\u00a5"+
		"\u00a7\7\36\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3"+
		"\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab"+
		"\u00ac\7\31\2\2\u00ac\25\3\2\2\2\u00ad\u00ae\7 \2\2\u00ae\u00af\7!\2\2"+
		"\u00af\u00b0\7\24\2\2\u00b0\u00b1\7!\2\2\u00b1\u00b2\7\17\2\2\u00b2\u00b3"+
		"\7\"\2\2\u00b3\u00b4\7\'\2\2\u00b4\u00b5\7#\2\2\u00b5\27\3\2\2\2\u00b6"+
		"\u00b7\7 \2\2\u00b7\u00b8\7!\2\2\u00b8\u00b9\7\21\2\2\u00b9\u00ba\7\""+
		"\2\2\u00ba\u00bb\7\36\2\2\u00bb\u00bc\7#\2\2\u00bc\31\3\2\2\2\u00bd\u00be"+
		"\7\t\2\2\u00be\u00bf\5\36\20\2\u00bf\u00c1\5 \21\2\u00c0\u00c2\5$\23\2"+
		"\u00c1\u00c0\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c5"+
		"\5\"\22\2\u00c4\u00c3\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2"+
		"\u00c6\u00c8\5&\24\2\u00c7\u00c6\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00ca"+
		"\3\2\2\2\u00c9\u00cb\5(\25\2\u00ca\u00c9\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb"+
		"\u00cd\3\2\2\2\u00cc\u00ce\5*\26\2\u00cd\u00cc\3\2\2\2\u00cd\u00ce\3\2"+
		"\2\2\u00ce\u00d0\3\2\2\2\u00cf\u00d1\5,\27\2\u00d0\u00cf\3\2\2\2\u00d0"+
		"\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\7\13\2\2\u00d3\33\3\2\2"+
		"\2\u00d4\u00d5\7\t\2\2\u00d5\u00d6\5.\30\2\u00d6\u00d8\5\60\31\2\u00d7"+
		"\u00d9\5\62\32\2\u00d8\u00d7\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da\3"+
		"\2\2\2\u00da\u00dc\5\64\33\2\u00db\u00dd\5\66\34\2\u00dc\u00db\3\2\2\2"+
		"\u00dc\u00dd\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\7\13\2\2\u00df\35"+
		"\3\2\2\2\u00e0\u00e1\7\5\2\2\u00e1\u00e2\7\"\2\2\u00e2\u00e3\7\36\2\2"+
		"\u00e3\u00e4\7#\2\2\u00e4\37\3\2\2\2\u00e5\u00e6\7\4\2\2\u00e6\u00e7\7"+
		"\"\2\2\u00e7\u00e8\7$\2\2\u00e8\u00e9\7#\2\2\u00e9!\3\2\2\2\u00ea\u00eb"+
		"\7\r\2\2\u00eb\u00ec\7\"\2\2\u00ec\u00ed\7%\2\2\u00ed\u00ee\7#\2\2\u00ee"+
		"#\3\2\2\2\u00ef\u00f0\7\6\2\2\u00f0\u00f1\7\"\2\2\u00f1\u00f2\7(\2\2\u00f2"+
		"\u00f3\7#\2\2\u00f3%\3\2\2\2\u00f4\u00f5\7\33\2\2\u00f5\u00f6\7\"\2\2"+
		"\u00f6\u00f7\7\36\2\2\u00f7\u00f8\7#\2\2\u00f8\'\3\2\2\2\u00f9\u00fa\7"+
		"\27\2\2\u00fa\u00fb\7\"\2\2\u00fb\u00fc\7\36\2\2\u00fc\u00fd\7#\2\2\u00fd"+
		")\3\2\2\2\u00fe\u00ff\7\26\2\2\u00ff\u0100\7\"\2\2\u0100\u0101\7(\2\2"+
		"\u0101\u0102\7#\2\2\u0102+\3\2\2\2\u0103\u0104\7\7\2\2\u0104\u0105\7\""+
		"\2\2\u0105\u0106\7(\2\2\u0106\u0107\7#\2\2\u0107-\3\2\2\2\u0108\u0109"+
		"\7\5\2\2\u0109\u010a\7\"\2\2\u010a\u010b\7\36\2\2\u010b\u010c\7#\2\2\u010c"+
		"/\3\2\2\2\u010d\u010e\7\23\2\2\u010e\u010f\7\"\2\2\u010f\u0110\7%\2\2"+
		"\u0110\u0111\7#\2\2\u0111\61\3\2\2\2\u0112\u0113\7\n\2\2\u0113\u0114\7"+
		"\"\2\2\u0114\u0115\7&\2\2\u0115\u0116\7#\2\2\u0116\63\3\2\2\2\u0117\u0118"+
		"\7\20\2\2\u0118\u0119\7\"\2\2\u0119\u011a\58\35\2\u011a\u011b\7#\2\2\u011b"+
		"\65\3\2\2\2\u011c\u011d\7\17\2\2\u011d\u011e\7\"\2\2\u011e\u011f\7\'\2"+
		"\2\u011f\67\3\2\2\2\u0120\u0129\7\30\2\2\u0121\u0126\7\36\2\2\u0122\u0123"+
		"\7\22\2\2\u0123\u0125\7\36\2\2\u0124\u0122\3\2\2\2\u0125\u0128\3\2\2\2"+
		"\u0126\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126"+
		"\3\2\2\2\u0129\u0121\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012b\3\2\2\2\u012b"+
		"\u012c\7\31\2\2\u012c\u012d\7#\2\2\u012d9\3\2\2\2\30<?BEILOR\u0081\u0084"+
		"\u0093\u00a8\u00c1\u00c4\u00c7\u00ca\u00cd\u00d0\u00d8\u00dc\u0126\u0129";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}