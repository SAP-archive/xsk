// Generated from com\sap\xsk\parser\xsodata\core\Hdbxsodata.g4 by ANTLR 4.3
package com.sap.xsk.parser.xsodata.core;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HdbxsodataParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__65=1, T__64=2, T__63=3, T__62=4, T__61=5, T__60=6, T__59=7, T__58=8, 
		T__57=9, T__56=10, T__55=11, T__54=12, T__53=13, T__52=14, T__51=15, T__50=16, 
		T__49=17, T__48=18, T__47=19, T__46=20, T__45=21, T__44=22, T__43=23, 
		T__42=24, T__41=25, T__40=26, T__39=27, T__38=28, T__37=29, T__36=30, 
		T__35=31, T__34=32, T__33=33, T__32=34, T__31=35, T__30=36, T__29=37, 
		T__28=38, T__27=39, T__26=40, T__25=41, T__24=42, T__23=43, T__22=44, 
		T__21=45, T__20=46, T__19=47, T__18=48, T__17=49, T__16=50, T__15=51, 
		T__14=52, T__13=53, T__12=54, T__11=55, T__10=56, T__9=57, T__8=58, T__7=59, 
		T__6=60, T__5=61, T__4=62, T__3=63, T__2=64, T__1=65, T__0=66, SEMICOLON=67, 
		EQ=68, QUATED_STRING=69, COMMA=70, COLON=71, ESC=72, WS=73, LINE_COMMENT=74, 
		COMMENT=75, INT=76;
	public static final String[] tokenNames = {
		"<INVALID>", "'storage'", "'{'", "'results'", "'navigates'", "'entity'", 
		"'delete'", "'no'", "'over'", "'content'", "'null'", "'('", "'events'", 
		"'always'", "'without'", "'after'", "'annotations'", "'via'", "'precommit'", 
		"'multiplicity'", "'SUM'", "'\"1\"'", "'keys'", "'postcommit'", "'parameters'", 
		"'limits'", "'hints'", "'MAX'", "'generate'", "'as'", "'of'", "'forbidden'", 
		"'create'", "'max_records'", "'using'", "'property'", "'aggregates'", 
		"'on'", "'}'", "'local'", "'dependent'", "'from'", "'constraint'", "'\"1..*\"'", 
		"'association'", "'metadata'", "'update'", "'enable'", "'.'", "'OData4SAP'", 
		"'before'", "'settings'", "'AVG'", "'key'", "'service'", "'referential'", 
		"'cache-control'", "'support'", "'MIN'", "'max_expanded_records'", "'with'", 
		"'namespace'", "'\"0..1\"'", "'principal'", "')'", "'and'", "'\"*\"'", 
		"';'", "'='", "QUATED_STRING", "','", "':'", "ESC", "WS", "LINE_COMMENT", 
		"COMMENT", "INT"
	};
	public static final int
		RULE_xsodataDefinition = 0, RULE_service = 1, RULE_namespace = 2, RULE_body = 3, 
		RULE_content = 4, RULE_entry = 5, RULE_entity = 6, RULE_object = 7, RULE_catalogobjectschema = 8, 
		RULE_catalogobjectname = 9, RULE_entityset = 10, RULE_entitysetname = 11, 
		RULE_with = 12, RULE_withProp = 13, RULE_withoutProp = 14, RULE_propertylist = 15, 
		RULE_columnname = 16, RULE_keys = 17, RULE_keylist = 18, RULE_keygenerated = 19, 
		RULE_navigates = 20, RULE_navlist = 21, RULE_naventry = 22, RULE_assocname = 23, 
		RULE_navpropname = 24, RULE_fromend = 25, RULE_aggregates = 26, RULE_aggregatestuple = 27, 
		RULE_aggregate = 28, RULE_aggregatefunction = 29, RULE_parameters = 30, 
		RULE_parameterskeyand = 31, RULE_parameterentitysetname = 32, RULE_parametersresultsprop = 33, 
		RULE_modificationBody = 34, RULE_modification = 35, RULE_create = 36, 
		RULE_update = 37, RULE_delete = 38, RULE_modificationspec = 39, RULE_modificationaction = 40, 
		RULE_forbidden = 41, RULE_action = 42, RULE_events = 43, RULE_eventlist = 44, 
		RULE_eventlistElement = 45, RULE_eventtype = 46, RULE_association = 47, 
		RULE_associationdef = 48, RULE_assocrefconstraint = 49, RULE_principalend = 50, 
		RULE_dependentend = 51, RULE_end = 52, RULE_endref = 53, RULE_endtype = 54, 
		RULE_joinpropertieslist = 55, RULE_multiplicity = 56, RULE_multiplicityvalue = 57, 
		RULE_assoctable = 58, RULE_repoobject = 59, RULE_overprincipalend = 60, 
		RULE_overdependentend = 61, RULE_overend = 62, RULE_storage = 63, RULE_nostorage = 64, 
		RULE_storageend = 65, RULE_annotations = 66, RULE_annotationsbody = 67, 
		RULE_annotationconfig = 68, RULE_settings = 69, RULE_settingsbody = 70, 
		RULE_settingselement = 71, RULE_supportnull = 72, RULE_contentcashecontrol = 73, 
		RULE_metadatacashecontrol = 74, RULE_hints = 75, RULE_hintlist = 76, RULE_hintvalue = 77, 
		RULE_nullvalue = 78, RULE_limits = 79, RULE_limitvalue = 80, RULE_maxrecords = 81, 
		RULE_maxexpandedrecords = 82;
	public static final String[] ruleNames = {
		"xsodataDefinition", "service", "namespace", "body", "content", "entry", 
		"entity", "object", "catalogobjectschema", "catalogobjectname", "entityset", 
		"entitysetname", "with", "withProp", "withoutProp", "propertylist", "columnname", 
		"keys", "keylist", "keygenerated", "navigates", "navlist", "naventry", 
		"assocname", "navpropname", "fromend", "aggregates", "aggregatestuple", 
		"aggregate", "aggregatefunction", "parameters", "parameterskeyand", "parameterentitysetname", 
		"parametersresultsprop", "modificationBody", "modification", "create", 
		"update", "delete", "modificationspec", "modificationaction", "forbidden", 
		"action", "events", "eventlist", "eventlistElement", "eventtype", "association", 
		"associationdef", "assocrefconstraint", "principalend", "dependentend", 
		"end", "endref", "endtype", "joinpropertieslist", "multiplicity", "multiplicityvalue", 
		"assoctable", "repoobject", "overprincipalend", "overdependentend", "overend", 
		"storage", "nostorage", "storageend", "annotations", "annotationsbody", 
		"annotationconfig", "settings", "settingsbody", "settingselement", "supportnull", 
		"contentcashecontrol", "metadatacashecontrol", "hints", "hintlist", "hintvalue", 
		"nullvalue", "limits", "limitvalue", "maxrecords", "maxexpandedrecords"
	};

	@Override
	public String getGrammarFileName() { return "Hdbxsodata.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HdbxsodataParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class XsodataDefinitionContext extends ParserRuleContext {
		public SettingsContext settings() {
			return getRuleContext(SettingsContext.class,0);
		}
		public ServiceContext service() {
			return getRuleContext(ServiceContext.class,0);
		}
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public XsodataDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xsodataDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterXsodataDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitXsodataDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitXsodataDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XsodataDefinitionContext xsodataDefinition() throws RecognitionException {
		XsodataDefinitionContext _localctx = new XsodataDefinitionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_xsodataDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166); service();
			setState(168);
			_la = _input.LA(1);
			if (_la==T__50) {
				{
				setState(167); annotations();
				}
			}

			setState(171);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(170); settings();
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

	public static class ServiceContext extends ParserRuleContext {
		public NamespaceContext namespace() {
			return getRuleContext(NamespaceContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ServiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_service; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterService(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitService(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitService(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceContext service() throws RecognitionException {
		ServiceContext _localctx = new ServiceContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_service);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173); match(T__12);
			setState(175);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(174); namespace();
				}
			}

			setState(177); body();
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

	public static class NamespaceContext extends ParserRuleContext {
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public NamespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterNamespace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitNamespace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitNamespace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceContext namespace() throws RecognitionException {
		NamespaceContext _localctx = new NamespaceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_namespace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179); match(T__5);
			setState(180); match(QUATED_STRING);
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

	public static class BodyContext extends ParserRuleContext {
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182); match(T__64);
			setState(184);
			_la = _input.LA(1);
			if (_la==T__61 || _la==T__22 || _la==QUATED_STRING) {
				{
				setState(183); content();
				}
			}

			setState(186); match(T__28);
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

	public static class ContentContext extends ParserRuleContext {
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public EntryContext entry() {
			return getRuleContext(EntryContext.class,0);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_content);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188); entry();
			setState(190);
			_la = _input.LA(1);
			if (_la==T__61 || _la==T__22 || _la==QUATED_STRING) {
				{
				setState(189); content();
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

	public static class EntryContext extends ParserRuleContext {
		public EntityContext entity() {
			return getRuleContext(EntityContext.class,0);
		}
		public AssociationContext association() {
			return getRuleContext(AssociationContext.class,0);
		}
		public EntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntryContext entry() throws RecognitionException {
		EntryContext _localctx = new EntryContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_entry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			switch (_input.LA(1)) {
			case T__61:
			case QUATED_STRING:
				{
				setState(192); entity();
				}
				break;
			case T__22:
				{
				setState(193); association();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class EntityContext extends ParserRuleContext {
		public KeysContext keys() {
			return getRuleContext(KeysContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(HdbxsodataParser.SEMICOLON, 0); }
		public EntitysetContext entityset() {
			return getRuleContext(EntitysetContext.class,0);
		}
		public AggregatesContext aggregates() {
			return getRuleContext(AggregatesContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public ModificationBodyContext modificationBody() {
			return getRuleContext(ModificationBodyContext.class,0);
		}
		public WithContext with() {
			return getRuleContext(WithContext.class,0);
		}
		public NavigatesContext navigates() {
			return getRuleContext(NavigatesContext.class,0);
		}
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public EntityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterEntity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitEntity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitEntity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntityContext entity() throws RecognitionException {
		EntityContext _localctx = new EntityContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_entity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196); object();
			setState(198);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(197); entityset();
				}
			}

			setState(201);
			_la = _input.LA(1);
			if (_la==T__52 || _la==T__6) {
				{
				setState(200); with();
				}
			}

			setState(204);
			_la = _input.LA(1);
			if (_la==T__44 || _la==T__13) {
				{
				setState(203); keys();
				}
			}

			setState(207);
			_la = _input.LA(1);
			if (_la==T__62) {
				{
				setState(206); navigates();
				}
			}

			setState(210);
			_la = _input.LA(1);
			if (_la==T__30) {
				{
				setState(209); aggregates();
				}
			}

			setState(213);
			_la = _input.LA(1);
			if (_la==T__42) {
				{
				setState(212); parameters();
				}
			}

			setState(216);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__60) | (1L << T__34) | (1L << T__20))) != 0)) {
				{
				setState(215); modificationBody();
				}
			}

			setState(218); match(SEMICOLON);
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

	public static class ObjectContext extends ParserRuleContext {
		public CatalogobjectschemaContext catalogobjectschema() {
			return getRuleContext(CatalogobjectschemaContext.class,0);
		}
		public CatalogobjectnameContext catalogobjectname() {
			return getRuleContext(CatalogobjectnameContext.class,0);
		}
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			_la = _input.LA(1);
			if (_la==T__61) {
				{
				setState(220); match(T__61);
				}
			}

			setState(226);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(223); catalogobjectschema();
				setState(224); match(T__18);
				}
				break;
			}
			setState(228); catalogobjectname();
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

	public static class CatalogobjectschemaContext extends ParserRuleContext {
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public CatalogobjectschemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catalogobjectschema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterCatalogobjectschema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitCatalogobjectschema(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitCatalogobjectschema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatalogobjectschemaContext catalogobjectschema() throws RecognitionException {
		CatalogobjectschemaContext _localctx = new CatalogobjectschemaContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_catalogobjectschema);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230); match(QUATED_STRING);
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

	public static class CatalogobjectnameContext extends ParserRuleContext {
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public CatalogobjectnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catalogobjectname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterCatalogobjectname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitCatalogobjectname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitCatalogobjectname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatalogobjectnameContext catalogobjectname() throws RecognitionException {
		CatalogobjectnameContext _localctx = new CatalogobjectnameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_catalogobjectname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232); match(QUATED_STRING);
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

	public static class EntitysetContext extends ParserRuleContext {
		public EntitysetnameContext entitysetname() {
			return getRuleContext(EntitysetnameContext.class,0);
		}
		public EntitysetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entityset; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterEntityset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitEntityset(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitEntityset(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntitysetContext entityset() throws RecognitionException {
		EntitysetContext _localctx = new EntitysetContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_entityset);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234); match(T__37);
			setState(235); entitysetname();
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

	public static class EntitysetnameContext extends ParserRuleContext {
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public EntitysetnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entitysetname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterEntitysetname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitEntitysetname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitEntitysetname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntitysetnameContext entitysetname() throws RecognitionException {
		EntitysetnameContext _localctx = new EntitysetnameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_entitysetname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237); match(QUATED_STRING);
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

	public static class WithContext extends ParserRuleContext {
		public WithPropContext withProp() {
			return getRuleContext(WithPropContext.class,0);
		}
		public PropertylistContext propertylist() {
			return getRuleContext(PropertylistContext.class,0);
		}
		public WithoutPropContext withoutProp() {
			return getRuleContext(WithoutPropContext.class,0);
		}
		public WithContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_with; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterWith(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitWith(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitWith(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WithContext with() throws RecognitionException {
		WithContext _localctx = new WithContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_with);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			switch (_input.LA(1)) {
			case T__6:
				{
				setState(239); withProp();
				}
				break;
			case T__52:
				{
				setState(240); withoutProp();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(243); propertylist();
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

	public static class WithPropContext extends ParserRuleContext {
		public WithPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterWithProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitWithProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitWithProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WithPropContext withProp() throws RecognitionException {
		WithPropContext _localctx = new WithPropContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_withProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245); match(T__6);
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

	public static class WithoutPropContext extends ParserRuleContext {
		public WithoutPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withoutProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterWithoutProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitWithoutProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitWithoutProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WithoutPropContext withoutProp() throws RecognitionException {
		WithoutPropContext _localctx = new WithoutPropContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_withoutProp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247); match(T__52);
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

	public static class PropertylistContext extends ParserRuleContext {
		public List<ColumnnameContext> columnname() {
			return getRuleContexts(ColumnnameContext.class);
		}
		public ColumnnameContext columnname(int i) {
			return getRuleContext(ColumnnameContext.class,i);
		}
		public PropertylistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertylist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterPropertylist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitPropertylist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitPropertylist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertylistContext propertylist() throws RecognitionException {
		PropertylistContext _localctx = new PropertylistContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_propertylist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249); match(T__55);
			{
			setState(250); columnname();
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(251); match(COMMA);
				setState(252); columnname();
				}
				}
				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(258); match(T__2);
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

	public static class ColumnnameContext extends ParserRuleContext {
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public ColumnnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterColumnname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitColumnname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitColumnname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnnameContext columnname() throws RecognitionException {
		ColumnnameContext _localctx = new ColumnnameContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_columnname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260); match(QUATED_STRING);
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

	public static class KeysContext extends ParserRuleContext {
		public KeylistContext keylist() {
			return getRuleContext(KeylistContext.class,0);
		}
		public KeygeneratedContext keygenerated() {
			return getRuleContext(KeygeneratedContext.class,0);
		}
		public KeysContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keys; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterKeys(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitKeys(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitKeys(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeysContext keys() throws RecognitionException {
		KeysContext _localctx = new KeysContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_keys);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			_la = _input.LA(1);
			if ( !(_la==T__44 || _la==T__13) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(265);
			switch (_input.LA(1)) {
			case T__55:
				{
				setState(263); keylist();
				}
				break;
			case T__38:
				{
				setState(264); keygenerated();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class KeylistContext extends ParserRuleContext {
		public PropertylistContext propertylist() {
			return getRuleContext(PropertylistContext.class,0);
		}
		public KeylistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keylist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterKeylist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitKeylist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitKeylist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeylistContext keylist() throws RecognitionException {
		KeylistContext _localctx = new KeylistContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_keylist);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267); propertylist();
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

	public static class KeygeneratedContext extends ParserRuleContext {
		public ColumnnameContext columnname() {
			return getRuleContext(ColumnnameContext.class,0);
		}
		public KeygeneratedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keygenerated; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterKeygenerated(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitKeygenerated(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitKeygenerated(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeygeneratedContext keygenerated() throws RecognitionException {
		KeygeneratedContext _localctx = new KeygeneratedContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_keygenerated);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269); match(T__38);
			setState(270); match(T__27);
			setState(271); columnname();
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

	public static class NavigatesContext extends ParserRuleContext {
		public NavlistContext navlist() {
			return getRuleContext(NavlistContext.class,0);
		}
		public NavigatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_navigates; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterNavigates(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitNavigates(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitNavigates(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NavigatesContext navigates() throws RecognitionException {
		NavigatesContext _localctx = new NavigatesContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_navigates);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273); match(T__62);
			setState(274); match(T__55);
			setState(275); navlist();
			setState(276); match(T__2);
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

	public static class NavlistContext extends ParserRuleContext {
		public NaventryContext naventry() {
			return getRuleContext(NaventryContext.class,0);
		}
		public List<NavlistContext> navlist() {
			return getRuleContexts(NavlistContext.class);
		}
		public NavlistContext navlist(int i) {
			return getRuleContext(NavlistContext.class,i);
		}
		public NavlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_navlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterNavlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitNavlist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitNavlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NavlistContext navlist() throws RecognitionException {
		NavlistContext _localctx = new NavlistContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_navlist);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(278); naventry();
			setState(283);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(279); match(COMMA);
					setState(280); navlist();
					}
					} 
				}
				setState(285);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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

	public static class NaventryContext extends ParserRuleContext {
		public AssocnameContext assocname() {
			return getRuleContext(AssocnameContext.class,0);
		}
		public FromendContext fromend() {
			return getRuleContext(FromendContext.class,0);
		}
		public NavpropnameContext navpropname() {
			return getRuleContext(NavpropnameContext.class,0);
		}
		public NaventryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_naventry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterNaventry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitNaventry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitNaventry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NaventryContext naventry() throws RecognitionException {
		NaventryContext _localctx = new NaventryContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_naventry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286); assocname();
			setState(287); match(T__37);
			setState(288); navpropname();
			setState(290);
			_la = _input.LA(1);
			if (_la==T__25) {
				{
				setState(289); fromend();
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

	public static class AssocnameContext extends ParserRuleContext {
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public AssocnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assocname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterAssocname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitAssocname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitAssocname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssocnameContext assocname() throws RecognitionException {
		AssocnameContext _localctx = new AssocnameContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_assocname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292); match(QUATED_STRING);
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

	public static class NavpropnameContext extends ParserRuleContext {
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public NavpropnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_navpropname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterNavpropname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitNavpropname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitNavpropname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NavpropnameContext navpropname() throws RecognitionException {
		NavpropnameContext _localctx = new NavpropnameContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_navpropname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294); match(QUATED_STRING);
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

	public static class FromendContext extends ParserRuleContext {
		public FromendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterFromend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitFromend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitFromend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FromendContext fromend() throws RecognitionException {
		FromendContext _localctx = new FromendContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_fromend);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296); match(T__25);
			setState(297);
			_la = _input.LA(1);
			if ( !(_la==T__26 || _la==T__3) ) {
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

	public static class AggregatesContext extends ParserRuleContext {
		public AggregatestupleContext aggregatestuple() {
			return getRuleContext(AggregatestupleContext.class,0);
		}
		public AggregatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregates; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterAggregates(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitAggregates(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitAggregates(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregatesContext aggregates() throws RecognitionException {
		AggregatesContext _localctx = new AggregatesContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_aggregates);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299); match(T__30);
			setState(300); match(T__53);
			setState(302);
			_la = _input.LA(1);
			if (_la==T__55) {
				{
				setState(301); aggregatestuple();
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

	public static class AggregatestupleContext extends ParserRuleContext {
		public AggregateContext aggregate(int i) {
			return getRuleContext(AggregateContext.class,i);
		}
		public List<AggregateContext> aggregate() {
			return getRuleContexts(AggregateContext.class);
		}
		public AggregatestupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregatestuple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterAggregatestuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitAggregatestuple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitAggregatestuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregatestupleContext aggregatestuple() throws RecognitionException {
		AggregatestupleContext _localctx = new AggregatestupleContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_aggregatestuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304); match(T__55);
			{
			setState(305); aggregate();
			setState(310);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(306); match(COMMA);
				setState(307); aggregate();
				}
				}
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(313); match(T__2);
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

	public static class AggregateContext extends ParserRuleContext {
		public AggregatefunctionContext aggregatefunction() {
			return getRuleContext(AggregatefunctionContext.class,0);
		}
		public ColumnnameContext columnname() {
			return getRuleContext(ColumnnameContext.class,0);
		}
		public AggregateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterAggregate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitAggregate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitAggregate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregateContext aggregate() throws RecognitionException {
		AggregateContext _localctx = new AggregateContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_aggregate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315); aggregatefunction();
			setState(316); match(T__36);
			setState(317); columnname();
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

	public static class AggregatefunctionContext extends ParserRuleContext {
		public AggregatefunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregatefunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterAggregatefunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitAggregatefunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitAggregatefunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregatefunctionContext aggregatefunction() throws RecognitionException {
		AggregatefunctionContext _localctx = new AggregatefunctionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_aggregatefunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__46) | (1L << T__39) | (1L << T__14) | (1L << T__8))) != 0)) ) {
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

	public static class ParametersContext extends ParserRuleContext {
		public ParameterskeyandContext parameterskeyand() {
			return getRuleContext(ParameterskeyandContext.class,0);
		}
		public ParameterentitysetnameContext parameterentitysetname() {
			return getRuleContext(ParameterentitysetnameContext.class,0);
		}
		public ParametersresultspropContext parametersresultsprop() {
			return getRuleContext(ParametersresultspropContext.class,0);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321); match(T__42);
			setState(322); match(T__49);
			setState(324);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(323); parameterskeyand();
				}
			}

			setState(326); match(T__61);
			setState(328);
			_la = _input.LA(1);
			if (_la==QUATED_STRING) {
				{
				setState(327); parameterentitysetname();
				}
			}

			setState(331);
			_la = _input.LA(1);
			if (_la==T__63) {
				{
				setState(330); parametersresultsprop();
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

	public static class ParameterskeyandContext extends ParserRuleContext {
		public ParameterskeyandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterskeyand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterParameterskeyand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitParameterskeyand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitParameterskeyand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterskeyandContext parameterskeyand() throws RecognitionException {
		ParameterskeyandContext _localctx = new ParameterskeyandContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_parameterskeyand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333); match(T__13);
			setState(334); match(T__1);
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

	public static class ParameterentitysetnameContext extends ParserRuleContext {
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public ParameterentitysetnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterentitysetname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterParameterentitysetname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitParameterentitysetname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitParameterentitysetname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterentitysetnameContext parameterentitysetname() throws RecognitionException {
		ParameterentitysetnameContext _localctx = new ParameterentitysetnameContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_parameterentitysetname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336); match(QUATED_STRING);
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

	public static class ParametersresultspropContext extends ParserRuleContext {
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public ParametersresultspropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametersresultsprop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterParametersresultsprop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitParametersresultsprop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitParametersresultsprop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametersresultspropContext parametersresultsprop() throws RecognitionException {
		ParametersresultspropContext _localctx = new ParametersresultspropContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_parametersresultsprop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338); match(T__63);
			setState(339); match(T__31);
			setState(340); match(QUATED_STRING);
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

	public static class ModificationBodyContext extends ParserRuleContext {
		public List<ModificationContext> modification() {
			return getRuleContexts(ModificationContext.class);
		}
		public ModificationContext modification(int i) {
			return getRuleContext(ModificationContext.class,i);
		}
		public ModificationBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modificationBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterModificationBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitModificationBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitModificationBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModificationBodyContext modificationBody() throws RecognitionException {
		ModificationBodyContext _localctx = new ModificationBodyContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_modificationBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342); modification();
			setState(346);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__60) | (1L << T__34) | (1L << T__20))) != 0)) {
				{
				{
				setState(343); modification();
				}
				}
				setState(348);
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

	public static class ModificationContext extends ParserRuleContext {
		public CreateContext create() {
			return getRuleContext(CreateContext.class,0);
		}
		public UpdateContext update() {
			return getRuleContext(UpdateContext.class,0);
		}
		public DeleteContext delete() {
			return getRuleContext(DeleteContext.class,0);
		}
		public ModificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterModification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitModification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitModification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModificationContext modification() throws RecognitionException {
		ModificationContext _localctx = new ModificationContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_modification);
		try {
			setState(352);
			switch (_input.LA(1)) {
			case T__34:
				enterOuterAlt(_localctx, 1);
				{
				setState(349); create();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 2);
				{
				setState(350); update();
				}
				break;
			case T__60:
				enterOuterAlt(_localctx, 3);
				{
				setState(351); delete();
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

	public static class CreateContext extends ParserRuleContext {
		public ModificationspecContext modificationspec() {
			return getRuleContext(ModificationspecContext.class,0);
		}
		public CreateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterCreate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitCreate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitCreate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateContext create() throws RecognitionException {
		CreateContext _localctx = new CreateContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_create);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354); match(T__34);
			setState(355); modificationspec();
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

	public static class UpdateContext extends ParserRuleContext {
		public ModificationspecContext modificationspec() {
			return getRuleContext(ModificationspecContext.class,0);
		}
		public UpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterUpdate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitUpdate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitUpdate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateContext update() throws RecognitionException {
		UpdateContext _localctx = new UpdateContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_update);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357); match(T__20);
			setState(358); modificationspec();
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

	public static class DeleteContext extends ParserRuleContext {
		public ModificationspecContext modificationspec() {
			return getRuleContext(ModificationspecContext.class,0);
		}
		public DeleteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterDelete(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitDelete(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitDelete(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteContext delete() throws RecognitionException {
		DeleteContext _localctx = new DeleteContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_delete);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360); match(T__60);
			setState(361); modificationspec();
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

	public static class ModificationspecContext extends ParserRuleContext {
		public EventsContext events() {
			return getRuleContext(EventsContext.class,0);
		}
		public ModificationactionContext modificationaction() {
			return getRuleContext(ModificationactionContext.class,0);
		}
		public ForbiddenContext forbidden() {
			return getRuleContext(ForbiddenContext.class,0);
		}
		public ModificationspecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modificationspec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterModificationspec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitModificationspec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitModificationspec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModificationspecContext modificationspec() throws RecognitionException {
		ModificationspecContext _localctx = new ModificationspecContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_modificationspec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			switch (_input.LA(1)) {
			case T__32:
				{
				{
				setState(363); modificationaction();
				setState(365);
				_la = _input.LA(1);
				if (_la==T__54) {
					{
					setState(364); events();
					}
				}

				}
				}
				break;
			case T__54:
				{
				setState(367); events();
				}
				break;
			case T__35:
				{
				setState(368); forbidden();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ModificationactionContext extends ParserRuleContext {
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public ModificationactionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modificationaction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterModificationaction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitModificationaction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitModificationaction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModificationactionContext modificationaction() throws RecognitionException {
		ModificationactionContext _localctx = new ModificationactionContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_modificationaction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371); match(T__32);
			setState(372); action();
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

	public static class ForbiddenContext extends ParserRuleContext {
		public ForbiddenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forbidden; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterForbidden(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitForbidden(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitForbidden(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForbiddenContext forbidden() throws RecognitionException {
		ForbiddenContext _localctx = new ForbiddenContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_forbidden);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374); match(T__35);
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

	public static class ActionContext extends ParserRuleContext {
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376); match(QUATED_STRING);
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

	public static class EventsContext extends ParserRuleContext {
		public EventlistContext eventlist() {
			return getRuleContext(EventlistContext.class,0);
		}
		public EventsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_events; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterEvents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitEvents(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitEvents(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EventsContext events() throws RecognitionException {
		EventsContext _localctx = new EventsContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_events);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378); match(T__54);
			setState(379); match(T__55);
			setState(380); eventlist();
			setState(381); match(T__2);
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

	public static class EventlistContext extends ParserRuleContext {
		public EventlistElementContext eventlistElement(int i) {
			return getRuleContext(EventlistElementContext.class,i);
		}
		public List<EventlistElementContext> eventlistElement() {
			return getRuleContexts(EventlistElementContext.class);
		}
		public EventlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eventlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterEventlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitEventlist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitEventlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EventlistContext eventlist() throws RecognitionException {
		EventlistContext _localctx = new EventlistContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_eventlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(383); eventlistElement();
			setState(388);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(384); match(COMMA);
				setState(385); eventlistElement();
				}
				}
				setState(390);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class EventlistElementContext extends ParserRuleContext {
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public EventtypeContext eventtype() {
			return getRuleContext(EventtypeContext.class,0);
		}
		public EventlistElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eventlistElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterEventlistElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitEventlistElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitEventlistElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EventlistElementContext eventlistElement() throws RecognitionException {
		EventlistElementContext _localctx = new EventlistElementContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_eventlistElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391); eventtype();
			setState(392); action();
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

	public static class EventtypeContext extends ParserRuleContext {
		public EventtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eventtype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterEventtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitEventtype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitEventtype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EventtypeContext eventtype() throws RecognitionException {
		EventtypeContext _localctx = new EventtypeContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_eventtype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__51) | (1L << T__48) | (1L << T__43) | (1L << T__16))) != 0)) ) {
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
		public AssocrefconstraintContext assocrefconstraint() {
			return getRuleContext(AssocrefconstraintContext.class,0);
		}
		public AssociationdefContext associationdef() {
			return getRuleContext(AssociationdefContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(HdbxsodataParser.SEMICOLON, 0); }
		public AssoctableContext assoctable() {
			return getRuleContext(AssoctableContext.class,0);
		}
		public StorageContext storage() {
			return getRuleContext(StorageContext.class,0);
		}
		public ModificationBodyContext modificationBody() {
			return getRuleContext(ModificationBodyContext.class,0);
		}
		public PrincipalendContext principalend() {
			return getRuleContext(PrincipalendContext.class,0);
		}
		public DependentendContext dependentend() {
			return getRuleContext(DependentendContext.class,0);
		}
		public AssociationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_association; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterAssociation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitAssociation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitAssociation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssociationContext association() throws RecognitionException {
		AssociationContext _localctx = new AssociationContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_association);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396); associationdef();
			setState(398);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(397); assocrefconstraint();
				}
			}

			setState(400); principalend();
			setState(401); dependentend();
			setState(405);
			switch (_input.LA(1)) {
			case T__58:
				{
				setState(402); assoctable();
				}
				break;
			case T__65:
			case T__59:
				{
				setState(403); storage();
				}
				break;
			case T__60:
			case T__34:
			case T__20:
				{
				setState(404); modificationBody();
				}
				break;
			case SEMICOLON:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(407); match(SEMICOLON);
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

	public static class AssociationdefContext extends ParserRuleContext {
		public AssocnameContext assocname() {
			return getRuleContext(AssocnameContext.class,0);
		}
		public AssociationdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_associationdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterAssociationdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitAssociationdef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitAssociationdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssociationdefContext associationdef() throws RecognitionException {
		AssociationdefContext _localctx = new AssociationdefContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_associationdef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(409); match(T__22);
			setState(410); assocname();
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

	public static class AssocrefconstraintContext extends ParserRuleContext {
		public AssocrefconstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assocrefconstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterAssocrefconstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitAssocrefconstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitAssocrefconstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssocrefconstraintContext assocrefconstraint() throws RecognitionException {
		AssocrefconstraintContext _localctx = new AssocrefconstraintContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_assocrefconstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412); match(T__6);
			setState(413); match(T__11);
			setState(414); match(T__24);
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

	public static class PrincipalendContext extends ParserRuleContext {
		public EndContext end() {
			return getRuleContext(EndContext.class,0);
		}
		public PrincipalendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_principalend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterPrincipalend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitPrincipalend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitPrincipalend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrincipalendContext principalend() throws RecognitionException {
		PrincipalendContext _localctx = new PrincipalendContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_principalend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416); match(T__3);
			setState(417); end();
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

	public static class DependentendContext extends ParserRuleContext {
		public EndContext end() {
			return getRuleContext(EndContext.class,0);
		}
		public DependentendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dependentend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterDependentend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitDependentend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitDependentend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DependentendContext dependentend() throws RecognitionException {
		DependentendContext _localctx = new DependentendContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_dependentend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419); match(T__26);
			setState(420); end();
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

	public static class EndContext extends ParserRuleContext {
		public EndrefContext endref() {
			return getRuleContext(EndrefContext.class,0);
		}
		public MultiplicityContext multiplicity() {
			return getRuleContext(MultiplicityContext.class,0);
		}
		public EndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_end; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitEnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitEnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndContext end() throws RecognitionException {
		EndContext _localctx = new EndContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_end);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(422); endref();
			setState(423); multiplicity();
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

	public static class EndrefContext extends ParserRuleContext {
		public EndtypeContext endtype() {
			return getRuleContext(EndtypeContext.class,0);
		}
		public JoinpropertieslistContext joinpropertieslist() {
			return getRuleContext(JoinpropertieslistContext.class,0);
		}
		public EndrefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endref; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterEndref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitEndref(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitEndref(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndrefContext endref() throws RecognitionException {
		EndrefContext _localctx = new EndrefContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_endref);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(425); endtype();
			setState(427);
			_la = _input.LA(1);
			if (_la==T__55) {
				{
				setState(426); joinpropertieslist();
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

	public static class EndtypeContext extends ParserRuleContext {
		public EntitysetnameContext entitysetname() {
			return getRuleContext(EntitysetnameContext.class,0);
		}
		public EndtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endtype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterEndtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitEndtype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitEndtype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndtypeContext endtype() throws RecognitionException {
		EndtypeContext _localctx = new EndtypeContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_endtype);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429); entitysetname();
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

	public static class JoinpropertieslistContext extends ParserRuleContext {
		public PropertylistContext propertylist() {
			return getRuleContext(PropertylistContext.class,0);
		}
		public JoinpropertieslistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinpropertieslist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterJoinpropertieslist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitJoinpropertieslist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitJoinpropertieslist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinpropertieslistContext joinpropertieslist() throws RecognitionException {
		JoinpropertieslistContext _localctx = new JoinpropertieslistContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_joinpropertieslist);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431); propertylist();
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

	public static class MultiplicityContext extends ParserRuleContext {
		public MultiplicityvalueContext multiplicityvalue() {
			return getRuleContext(MultiplicityvalueContext.class,0);
		}
		public MultiplicityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterMultiplicity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitMultiplicity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitMultiplicity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicityContext multiplicity() throws RecognitionException {
		MultiplicityContext _localctx = new MultiplicityContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_multiplicity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433); match(T__47);
			setState(434); multiplicityvalue();
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

	public static class MultiplicityvalueContext extends ParserRuleContext {
		public MultiplicityvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicityvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterMultiplicityvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitMultiplicityvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitMultiplicityvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicityvalueContext multiplicityvalue() throws RecognitionException {
		MultiplicityvalueContext _localctx = new MultiplicityvalueContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_multiplicityvalue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			_la = _input.LA(1);
			if ( !(((((_la - 21)) & ~0x3f) == 0 && ((1L << (_la - 21)) & ((1L << (T__45 - 21)) | (1L << (T__23 - 21)) | (1L << (T__4 - 21)) | (1L << (T__0 - 21)))) != 0)) ) {
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

	public static class AssoctableContext extends ParserRuleContext {
		public OverprincipalendContext overprincipalend() {
			return getRuleContext(OverprincipalendContext.class,0);
		}
		public ModificationBodyContext modificationBody() {
			return getRuleContext(ModificationBodyContext.class,0);
		}
		public RepoobjectContext repoobject() {
			return getRuleContext(RepoobjectContext.class,0);
		}
		public OverdependentendContext overdependentend() {
			return getRuleContext(OverdependentendContext.class,0);
		}
		public AssoctableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assoctable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterAssoctable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitAssoctable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitAssoctable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssoctableContext assoctable() throws RecognitionException {
		AssoctableContext _localctx = new AssoctableContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_assoctable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438); match(T__58);
			setState(439); repoobject();
			setState(440); overprincipalend();
			setState(441); overdependentend();
			setState(443);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__60) | (1L << T__34) | (1L << T__20))) != 0)) {
				{
				setState(442); modificationBody();
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

	public static class RepoobjectContext extends ParserRuleContext {
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public RepoobjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repoobject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterRepoobject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitRepoobject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitRepoobject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepoobjectContext repoobject() throws RecognitionException {
		RepoobjectContext _localctx = new RepoobjectContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_repoobject);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445); match(QUATED_STRING);
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

	public static class OverprincipalendContext extends ParserRuleContext {
		public OverendContext overend() {
			return getRuleContext(OverendContext.class,0);
		}
		public OverprincipalendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_overprincipalend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterOverprincipalend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitOverprincipalend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitOverprincipalend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OverprincipalendContext overprincipalend() throws RecognitionException {
		OverprincipalendContext _localctx = new OverprincipalendContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_overprincipalend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447); match(T__3);
			setState(448); overend();
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

	public static class OverdependentendContext extends ParserRuleContext {
		public OverendContext overend() {
			return getRuleContext(OverendContext.class,0);
		}
		public OverdependentendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_overdependentend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterOverdependentend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitOverdependentend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitOverdependentend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OverdependentendContext overdependentend() throws RecognitionException {
		OverdependentendContext _localctx = new OverdependentendContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_overdependentend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(450); match(T__26);
			setState(451); overend();
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

	public static class OverendContext extends ParserRuleContext {
		public PropertylistContext propertylist() {
			return getRuleContext(PropertylistContext.class,0);
		}
		public OverendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_overend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterOverend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitOverend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitOverend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OverendContext overend() throws RecognitionException {
		OverendContext _localctx = new OverendContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_overend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(453); propertylist();
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

	public static class StorageContext extends ParserRuleContext {
		public NostorageContext nostorage() {
			return getRuleContext(NostorageContext.class,0);
		}
		public ModificationBodyContext modificationBody() {
			return getRuleContext(ModificationBodyContext.class,0);
		}
		public StorageendContext storageend() {
			return getRuleContext(StorageendContext.class,0);
		}
		public StorageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_storage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterStorage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitStorage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitStorage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StorageContext storage() throws RecognitionException {
		StorageContext _localctx = new StorageContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_storage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(460);
			switch (_input.LA(1)) {
			case T__59:
				{
				setState(455); nostorage();
				}
				break;
			case T__65:
				{
				setState(456); storageend();
				setState(458);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__60) | (1L << T__34) | (1L << T__20))) != 0)) {
					{
					setState(457); modificationBody();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class NostorageContext extends ParserRuleContext {
		public NostorageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nostorage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterNostorage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitNostorage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitNostorage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NostorageContext nostorage() throws RecognitionException {
		NostorageContext _localctx = new NostorageContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_nostorage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462); match(T__59);
			setState(463); match(T__65);
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

	public static class StorageendContext extends ParserRuleContext {
		public StorageendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_storageend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterStorageend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitStorageend(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitStorageend(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StorageendContext storageend() throws RecognitionException {
		StorageendContext _localctx = new StorageendContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_storageend);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465); match(T__65);
			setState(466); match(T__29);
			setState(467);
			_la = _input.LA(1);
			if ( !(_la==T__26 || _la==T__3) ) {
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

	public static class AnnotationsContext extends ParserRuleContext {
		public AnnotationsbodyContext annotationsbody() {
			return getRuleContext(AnnotationsbodyContext.class,0);
		}
		public AnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterAnnotations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitAnnotations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitAnnotations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationsContext annotations() throws RecognitionException {
		AnnotationsContext _localctx = new AnnotationsContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_annotations);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469); match(T__50);
			setState(470); annotationsbody();
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

	public static class AnnotationsbodyContext extends ParserRuleContext {
		public List<AnnotationconfigContext> annotationconfig() {
			return getRuleContexts(AnnotationconfigContext.class);
		}
		public AnnotationconfigContext annotationconfig(int i) {
			return getRuleContext(AnnotationconfigContext.class,i);
		}
		public AnnotationsbodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationsbody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterAnnotationsbody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitAnnotationsbody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitAnnotationsbody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationsbodyContext annotationsbody() throws RecognitionException {
		AnnotationsbodyContext _localctx = new AnnotationsbodyContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_annotationsbody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(472); match(T__64);
			setState(473); annotationconfig();
			setState(475);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(474); annotationconfig();
				}
			}

			setState(477); match(T__28);
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

	public static class AnnotationconfigContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbxsodataParser.SEMICOLON, 0); }
		public AnnotationconfigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationconfig; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterAnnotationconfig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitAnnotationconfig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitAnnotationconfig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationconfigContext annotationconfig() throws RecognitionException {
		AnnotationconfigContext _localctx = new AnnotationconfigContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_annotationconfig);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479); match(T__19);
			setState(480); match(T__17);
			setState(481); match(SEMICOLON);
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

	public static class SettingsContext extends ParserRuleContext {
		public SettingsbodyContext settingsbody() {
			return getRuleContext(SettingsbodyContext.class,0);
		}
		public SettingsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_settings; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterSettings(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitSettings(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitSettings(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SettingsContext settings() throws RecognitionException {
		SettingsContext _localctx = new SettingsContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_settings);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483); match(T__15);
			setState(484); settingsbody();
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

	public static class SettingsbodyContext extends ParserRuleContext {
		public List<SettingselementContext> settingselement() {
			return getRuleContexts(SettingselementContext.class);
		}
		public SettingselementContext settingselement(int i) {
			return getRuleContext(SettingselementContext.class,i);
		}
		public SettingsbodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_settingsbody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterSettingsbody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitSettingsbody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitSettingsbody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SettingsbodyContext settingsbody() throws RecognitionException {
		SettingsbodyContext _localctx = new SettingsbodyContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_settingsbody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486); match(T__64);
			setState(490);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__57) | (1L << T__41) | (1L << T__40) | (1L << T__21) | (1L << T__9))) != 0)) {
				{
				{
				setState(487); settingselement();
				}
				}
				setState(492);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(493); match(T__28);
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

	public static class SettingselementContext extends ParserRuleContext {
		public LimitsContext limits() {
			return getRuleContext(LimitsContext.class,0);
		}
		public ContentcashecontrolContext contentcashecontrol() {
			return getRuleContext(ContentcashecontrolContext.class,0);
		}
		public SupportnullContext supportnull() {
			return getRuleContext(SupportnullContext.class,0);
		}
		public HintsContext hints() {
			return getRuleContext(HintsContext.class,0);
		}
		public MetadatacashecontrolContext metadatacashecontrol() {
			return getRuleContext(MetadatacashecontrolContext.class,0);
		}
		public SettingselementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_settingselement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterSettingselement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitSettingselement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitSettingselement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SettingselementContext settingselement() throws RecognitionException {
		SettingselementContext _localctx = new SettingselementContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_settingselement);
		try {
			setState(500);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(495); supportnull();
				}
				break;
			case T__57:
				enterOuterAlt(_localctx, 2);
				{
				setState(496); contentcashecontrol();
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 3);
				{
				setState(497); metadatacashecontrol();
				}
				break;
			case T__40:
				enterOuterAlt(_localctx, 4);
				{
				setState(498); hints();
				}
				break;
			case T__41:
				enterOuterAlt(_localctx, 5);
				{
				setState(499); limits();
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

	public static class SupportnullContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbxsodataParser.SEMICOLON, 0); }
		public SupportnullContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_supportnull; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterSupportnull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitSupportnull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitSupportnull(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SupportnullContext supportnull() throws RecognitionException {
		SupportnullContext _localctx = new SupportnullContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_supportnull);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(502); match(T__9);
			setState(503); match(T__56);
			setState(504); match(SEMICOLON);
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

	public static class ContentcashecontrolContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbxsodataParser.SEMICOLON, 0); }
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public ContentcashecontrolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contentcashecontrol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterContentcashecontrol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitContentcashecontrol(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitContentcashecontrol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentcashecontrolContext contentcashecontrol() throws RecognitionException {
		ContentcashecontrolContext _localctx = new ContentcashecontrolContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_contentcashecontrol);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(506); match(T__57);
			setState(507); match(T__10);
			setState(508); match(QUATED_STRING);
			setState(509); match(SEMICOLON);
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

	public static class MetadatacashecontrolContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbxsodataParser.SEMICOLON, 0); }
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public MetadatacashecontrolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_metadatacashecontrol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterMetadatacashecontrol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitMetadatacashecontrol(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitMetadatacashecontrol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MetadatacashecontrolContext metadatacashecontrol() throws RecognitionException {
		MetadatacashecontrolContext _localctx = new MetadatacashecontrolContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_metadatacashecontrol);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(511); match(T__21);
			setState(512); match(T__10);
			setState(513); match(QUATED_STRING);
			setState(514); match(SEMICOLON);
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

	public static class HintsContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbxsodataParser.SEMICOLON, 0); }
		public HintlistContext hintlist() {
			return getRuleContext(HintlistContext.class,0);
		}
		public NullvalueContext nullvalue() {
			return getRuleContext(NullvalueContext.class,0);
		}
		public HintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterHints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitHints(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitHints(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HintsContext hints() throws RecognitionException {
		HintsContext _localctx = new HintsContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_hints);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(516); match(T__40);
			setState(518);
			_la = _input.LA(1);
			if (_la==QUATED_STRING) {
				{
				setState(517); hintlist();
				}
			}

			setState(521);
			_la = _input.LA(1);
			if (_la==T__56) {
				{
				setState(520); nullvalue();
				}
			}

			setState(523); match(SEMICOLON);
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

	public static class HintlistContext extends ParserRuleContext {
		public List<HintvalueContext> hintvalue() {
			return getRuleContexts(HintvalueContext.class);
		}
		public HintvalueContext hintvalue(int i) {
			return getRuleContext(HintvalueContext.class,i);
		}
		public HintlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hintlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterHintlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitHintlist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitHintlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HintlistContext hintlist() throws RecognitionException {
		HintlistContext _localctx = new HintlistContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_hintlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(525); hintvalue();
			setState(530);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(526); match(COMMA);
				setState(527); hintvalue();
				}
				}
				setState(532);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class HintvalueContext extends ParserRuleContext {
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public HintvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hintvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterHintvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitHintvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitHintvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HintvalueContext hintvalue() throws RecognitionException {
		HintvalueContext _localctx = new HintvalueContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_hintvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(533); match(QUATED_STRING);
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

	public static class NullvalueContext extends ParserRuleContext {
		public NullvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nullvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterNullvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitNullvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitNullvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NullvalueContext nullvalue() throws RecognitionException {
		NullvalueContext _localctx = new NullvalueContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_nullvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(535); match(T__56);
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

	public static class LimitsContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(HdbxsodataParser.SEMICOLON, 0); }
		public LimitvalueContext limitvalue(int i) {
			return getRuleContext(LimitvalueContext.class,i);
		}
		public List<LimitvalueContext> limitvalue() {
			return getRuleContexts(LimitvalueContext.class);
		}
		public LimitsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limits; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterLimits(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitLimits(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitLimits(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitsContext limits() throws RecognitionException {
		LimitsContext _localctx = new LimitsContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_limits);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(537); match(T__41);
			setState(538); limitvalue();
			setState(543);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(539); match(COMMA);
				setState(540); limitvalue();
				}
				}
				setState(545);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(546); match(SEMICOLON);
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

	public static class LimitvalueContext extends ParserRuleContext {
		public MaxrecordsContext maxrecords() {
			return getRuleContext(MaxrecordsContext.class,0);
		}
		public MaxexpandedrecordsContext maxexpandedrecords() {
			return getRuleContext(MaxexpandedrecordsContext.class,0);
		}
		public TerminalNode EQ() { return getToken(HdbxsodataParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbxsodataParser.INT, 0); }
		public LimitvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterLimitvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitLimitvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitLimitvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitvalueContext limitvalue() throws RecognitionException {
		LimitvalueContext _localctx = new LimitvalueContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_limitvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(550);
			switch (_input.LA(1)) {
			case T__33:
				{
				setState(548); maxrecords();
				}
				break;
			case T__7:
				{
				setState(549); maxexpandedrecords();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(552); match(EQ);
			setState(553); match(INT);
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

	public static class MaxrecordsContext extends ParserRuleContext {
		public MaxrecordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_maxrecords; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterMaxrecords(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitMaxrecords(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitMaxrecords(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MaxrecordsContext maxrecords() throws RecognitionException {
		MaxrecordsContext _localctx = new MaxrecordsContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_maxrecords);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(555); match(T__33);
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

	public static class MaxexpandedrecordsContext extends ParserRuleContext {
		public MaxexpandedrecordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_maxexpandedrecords; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterMaxexpandedrecords(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitMaxexpandedrecords(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitMaxexpandedrecords(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MaxexpandedrecordsContext maxexpandedrecords() throws RecognitionException {
		MaxexpandedrecordsContext _localctx = new MaxexpandedrecordsContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_maxexpandedrecords);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(557); match(T__7);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3N\u0232\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\3\2\3\2\5\2\u00ab\n\2\3\2\5\2\u00ae\n\2\3\3\3\3\5\3\u00b2\n\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\5\3\5\5\5\u00bb\n\5\3\5\3\5\3\6\3\6\5\6\u00c1\n\6\3\7\3"+
		"\7\5\7\u00c5\n\7\3\b\3\b\5\b\u00c9\n\b\3\b\5\b\u00cc\n\b\3\b\5\b\u00cf"+
		"\n\b\3\b\5\b\u00d2\n\b\3\b\5\b\u00d5\n\b\3\b\5\b\u00d8\n\b\3\b\5\b\u00db"+
		"\n\b\3\b\3\b\3\t\5\t\u00e0\n\t\3\t\3\t\3\t\5\t\u00e5\n\t\3\t\3\t\3\n\3"+
		"\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\5\16\u00f4\n\16\3\16\3\16\3"+
		"\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\7\21\u0100\n\21\f\21\16\21\u0103"+
		"\13\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23\5\23\u010c\n\23\3\24\3\24\3"+
		"\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\7\27\u011c"+
		"\n\27\f\27\16\27\u011f\13\27\3\30\3\30\3\30\3\30\5\30\u0125\n\30\3\31"+
		"\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\5\34\u0131\n\34\3\35\3\35"+
		"\3\35\3\35\7\35\u0137\n\35\f\35\16\35\u013a\13\35\3\35\3\35\3\36\3\36"+
		"\3\36\3\36\3\37\3\37\3 \3 \3 \5 \u0147\n \3 \3 \5 \u014b\n \3 \5 \u014e"+
		"\n \3!\3!\3!\3\"\3\"\3#\3#\3#\3#\3$\3$\7$\u015b\n$\f$\16$\u015e\13$\3"+
		"%\3%\3%\5%\u0163\n%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\3)\3)\5)\u0170\n)\3"+
		")\3)\5)\u0174\n)\3*\3*\3*\3+\3+\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\7.\u0185"+
		"\n.\f.\16.\u0188\13.\3/\3/\3/\3\60\3\60\3\61\3\61\5\61\u0191\n\61\3\61"+
		"\3\61\3\61\3\61\3\61\5\61\u0198\n\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63"+
		"\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3\66\3\67\3\67\5\67"+
		"\u01ae\n\67\38\38\39\39\3:\3:\3:\3;\3;\3<\3<\3<\3<\3<\5<\u01be\n<\3=\3"+
		"=\3>\3>\3>\3?\3?\3?\3@\3@\3A\3A\3A\5A\u01cd\nA\5A\u01cf\nA\3B\3B\3B\3"+
		"C\3C\3C\3C\3D\3D\3D\3E\3E\3E\5E\u01de\nE\3E\3E\3F\3F\3F\3F\3G\3G\3G\3"+
		"H\3H\7H\u01eb\nH\fH\16H\u01ee\13H\3H\3H\3I\3I\3I\3I\3I\5I\u01f7\nI\3J"+
		"\3J\3J\3J\3K\3K\3K\3K\3K\3L\3L\3L\3L\3L\3M\3M\5M\u0209\nM\3M\5M\u020c"+
		"\nM\3M\3M\3N\3N\3N\7N\u0213\nN\fN\16N\u0216\13N\3O\3O\3P\3P\3Q\3Q\3Q\3"+
		"Q\7Q\u0220\nQ\fQ\16Q\u0223\13Q\3Q\3Q\3R\3R\5R\u0229\nR\3R\3R\3R\3S\3S"+
		"\3T\3T\3T\2\2U\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088"+
		"\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0"+
		"\u00a2\u00a4\u00a6\2\7\4\2\30\30\67\67\4\2**AA\6\2\26\26\35\35\66\66<"+
		"<\6\2\21\21\24\24\31\31\64\64\6\2\27\27--@@DD\u0211\2\u00a8\3\2\2\2\4"+
		"\u00af\3\2\2\2\6\u00b5\3\2\2\2\b\u00b8\3\2\2\2\n\u00be\3\2\2\2\f\u00c4"+
		"\3\2\2\2\16\u00c6\3\2\2\2\20\u00df\3\2\2\2\22\u00e8\3\2\2\2\24\u00ea\3"+
		"\2\2\2\26\u00ec\3\2\2\2\30\u00ef\3\2\2\2\32\u00f3\3\2\2\2\34\u00f7\3\2"+
		"\2\2\36\u00f9\3\2\2\2 \u00fb\3\2\2\2\"\u0106\3\2\2\2$\u0108\3\2\2\2&\u010d"+
		"\3\2\2\2(\u010f\3\2\2\2*\u0113\3\2\2\2,\u0118\3\2\2\2.\u0120\3\2\2\2\60"+
		"\u0126\3\2\2\2\62\u0128\3\2\2\2\64\u012a\3\2\2\2\66\u012d\3\2\2\28\u0132"+
		"\3\2\2\2:\u013d\3\2\2\2<\u0141\3\2\2\2>\u0143\3\2\2\2@\u014f\3\2\2\2B"+
		"\u0152\3\2\2\2D\u0154\3\2\2\2F\u0158\3\2\2\2H\u0162\3\2\2\2J\u0164\3\2"+
		"\2\2L\u0167\3\2\2\2N\u016a\3\2\2\2P\u0173\3\2\2\2R\u0175\3\2\2\2T\u0178"+
		"\3\2\2\2V\u017a\3\2\2\2X\u017c\3\2\2\2Z\u0181\3\2\2\2\\\u0189\3\2\2\2"+
		"^\u018c\3\2\2\2`\u018e\3\2\2\2b\u019b\3\2\2\2d\u019e\3\2\2\2f\u01a2\3"+
		"\2\2\2h\u01a5\3\2\2\2j\u01a8\3\2\2\2l\u01ab\3\2\2\2n\u01af\3\2\2\2p\u01b1"+
		"\3\2\2\2r\u01b3\3\2\2\2t\u01b6\3\2\2\2v\u01b8\3\2\2\2x\u01bf\3\2\2\2z"+
		"\u01c1\3\2\2\2|\u01c4\3\2\2\2~\u01c7\3\2\2\2\u0080\u01ce\3\2\2\2\u0082"+
		"\u01d0\3\2\2\2\u0084\u01d3\3\2\2\2\u0086\u01d7\3\2\2\2\u0088\u01da\3\2"+
		"\2\2\u008a\u01e1\3\2\2\2\u008c\u01e5\3\2\2\2\u008e\u01e8\3\2\2\2\u0090"+
		"\u01f6\3\2\2\2\u0092\u01f8\3\2\2\2\u0094\u01fc\3\2\2\2\u0096\u0201\3\2"+
		"\2\2\u0098\u0206\3\2\2\2\u009a\u020f\3\2\2\2\u009c\u0217\3\2\2\2\u009e"+
		"\u0219\3\2\2\2\u00a0\u021b\3\2\2\2\u00a2\u0228\3\2\2\2\u00a4\u022d\3\2"+
		"\2\2\u00a6\u022f\3\2\2\2\u00a8\u00aa\5\4\3\2\u00a9\u00ab\5\u0086D\2\u00aa"+
		"\u00a9\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ad\3\2\2\2\u00ac\u00ae\5\u008c"+
		"G\2\u00ad\u00ac\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\3\3\2\2\2\u00af\u00b1"+
		"\78\2\2\u00b0\u00b2\5\6\4\2\u00b1\u00b0\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2"+
		"\u00b3\3\2\2\2\u00b3\u00b4\5\b\5\2\u00b4\5\3\2\2\2\u00b5\u00b6\7?\2\2"+
		"\u00b6\u00b7\7G\2\2\u00b7\7\3\2\2\2\u00b8\u00ba\7\4\2\2\u00b9\u00bb\5"+
		"\n\6\2\u00ba\u00b9\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc"+
		"\u00bd\7(\2\2\u00bd\t\3\2\2\2\u00be\u00c0\5\f\7\2\u00bf\u00c1\5\n\6\2"+
		"\u00c0\u00bf\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\13\3\2\2\2\u00c2\u00c5"+
		"\5\16\b\2\u00c3\u00c5\5`\61\2\u00c4\u00c2\3\2\2\2\u00c4\u00c3\3\2\2\2"+
		"\u00c5\r\3\2\2\2\u00c6\u00c8\5\20\t\2\u00c7\u00c9\5\26\f\2\u00c8\u00c7"+
		"\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cb\3\2\2\2\u00ca\u00cc\5\32\16\2"+
		"\u00cb\u00ca\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd\u00cf"+
		"\5$\23\2\u00ce\u00cd\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0"+
		"\u00d2\5*\26\2\u00d1\u00d0\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\3\2"+
		"\2\2\u00d3\u00d5\5\66\34\2\u00d4\u00d3\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"\u00d7\3\2\2\2\u00d6\u00d8\5> \2\u00d7\u00d6\3\2\2\2\u00d7\u00d8\3\2\2"+
		"\2\u00d8\u00da\3\2\2\2\u00d9\u00db\5F$\2\u00da\u00d9\3\2\2\2\u00da\u00db"+
		"\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\7E\2\2\u00dd\17\3\2\2\2\u00de"+
		"\u00e0\7\7\2\2\u00df\u00de\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e4\3\2"+
		"\2\2\u00e1\u00e2\5\22\n\2\u00e2\u00e3\7\62\2\2\u00e3\u00e5\3\2\2\2\u00e4"+
		"\u00e1\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\5\24"+
		"\13\2\u00e7\21\3\2\2\2\u00e8\u00e9\7G\2\2\u00e9\23\3\2\2\2\u00ea\u00eb"+
		"\7G\2\2\u00eb\25\3\2\2\2\u00ec\u00ed\7\37\2\2\u00ed\u00ee\5\30\r\2\u00ee"+
		"\27\3\2\2\2\u00ef\u00f0\7G\2\2\u00f0\31\3\2\2\2\u00f1\u00f4\5\34\17\2"+
		"\u00f2\u00f4\5\36\20\2\u00f3\u00f1\3\2\2\2\u00f3\u00f2\3\2\2\2\u00f4\u00f5"+
		"\3\2\2\2\u00f5\u00f6\5 \21\2\u00f6\33\3\2\2\2\u00f7\u00f8\7>\2\2\u00f8"+
		"\35\3\2\2\2\u00f9\u00fa\7\20\2\2\u00fa\37\3\2\2\2\u00fb\u00fc\7\r\2\2"+
		"\u00fc\u0101\5\"\22\2\u00fd\u00fe\7H\2\2\u00fe\u0100\5\"\22\2\u00ff\u00fd"+
		"\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102"+
		"\u0104\3\2\2\2\u0103\u0101\3\2\2\2\u0104\u0105\7B\2\2\u0105!\3\2\2\2\u0106"+
		"\u0107\7G\2\2\u0107#\3\2\2\2\u0108\u010b\t\2\2\2\u0109\u010c\5&\24\2\u010a"+
		"\u010c\5(\25\2\u010b\u0109\3\2\2\2\u010b\u010a\3\2\2\2\u010c%\3\2\2\2"+
		"\u010d\u010e\5 \21\2\u010e\'\3\2\2\2\u010f\u0110\7\36\2\2\u0110\u0111"+
		"\7)\2\2\u0111\u0112\5\"\22\2\u0112)\3\2\2\2\u0113\u0114\7\6\2\2\u0114"+
		"\u0115\7\r\2\2\u0115\u0116\5,\27\2\u0116\u0117\7B\2\2\u0117+\3\2\2\2\u0118"+
		"\u011d\5.\30\2\u0119\u011a\7H\2\2\u011a\u011c\5,\27\2\u011b\u0119\3\2"+
		"\2\2\u011c\u011f\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e"+
		"-\3\2\2\2\u011f\u011d\3\2\2\2\u0120\u0121\5\60\31\2\u0121\u0122\7\37\2"+
		"\2\u0122\u0124\5\62\32\2\u0123\u0125\5\64\33\2\u0124\u0123\3\2\2\2\u0124"+
		"\u0125\3\2\2\2\u0125/\3\2\2\2\u0126\u0127\7G\2\2\u0127\61\3\2\2\2\u0128"+
		"\u0129\7G\2\2\u0129\63\3\2\2\2\u012a\u012b\7+\2\2\u012b\u012c\t\3\2\2"+
		"\u012c\65\3\2\2\2\u012d\u012e\7&\2\2\u012e\u0130\7\17\2\2\u012f\u0131"+
		"\58\35\2\u0130\u012f\3\2\2\2\u0130\u0131\3\2\2\2\u0131\67\3\2\2\2\u0132"+
		"\u0133\7\r\2\2\u0133\u0138\5:\36\2\u0134\u0135\7H\2\2\u0135\u0137\5:\36"+
		"\2\u0136\u0134\3\2\2\2\u0137\u013a\3\2\2\2\u0138\u0136\3\2\2\2\u0138\u0139"+
		"\3\2\2\2\u0139\u013b\3\2\2\2\u013a\u0138\3\2\2\2\u013b\u013c\7B\2\2\u013c"+
		"9\3\2\2\2\u013d\u013e\5<\37\2\u013e\u013f\7 \2\2\u013f\u0140\5\"\22\2"+
		"\u0140;\3\2\2\2\u0141\u0142\t\4\2\2\u0142=\3\2\2\2\u0143\u0144\7\32\2"+
		"\2\u0144\u0146\7\23\2\2\u0145\u0147\5@!\2\u0146\u0145\3\2\2\2\u0146\u0147"+
		"\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u014a\7\7\2\2\u0149\u014b\5B\"\2\u014a"+
		"\u0149\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014d\3\2\2\2\u014c\u014e\5D"+
		"#\2\u014d\u014c\3\2\2\2\u014d\u014e\3\2\2\2\u014e?\3\2\2\2\u014f\u0150"+
		"\7\67\2\2\u0150\u0151\7C\2\2\u0151A\3\2\2\2\u0152\u0153\7G\2\2\u0153C"+
		"\3\2\2\2\u0154\u0155\7\5\2\2\u0155\u0156\7%\2\2\u0156\u0157\7G\2\2\u0157"+
		"E\3\2\2\2\u0158\u015c\5H%\2\u0159\u015b\5H%\2\u015a\u0159\3\2\2\2\u015b"+
		"\u015e\3\2\2\2\u015c\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015dG\3\2\2\2"+
		"\u015e\u015c\3\2\2\2\u015f\u0163\5J&\2\u0160\u0163\5L\'\2\u0161\u0163"+
		"\5N(\2\u0162\u015f\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0161\3\2\2\2\u0163"+
		"I\3\2\2\2\u0164\u0165\7\"\2\2\u0165\u0166\5P)\2\u0166K\3\2\2\2\u0167\u0168"+
		"\7\60\2\2\u0168\u0169\5P)\2\u0169M\3\2\2\2\u016a\u016b\7\b\2\2\u016b\u016c"+
		"\5P)\2\u016cO\3\2\2\2\u016d\u016f\5R*\2\u016e\u0170\5X-\2\u016f\u016e"+
		"\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0174\3\2\2\2\u0171\u0174\5X-\2\u0172"+
		"\u0174\5T+\2\u0173\u016d\3\2\2\2\u0173\u0171\3\2\2\2\u0173\u0172\3\2\2"+
		"\2\u0174Q\3\2\2\2\u0175\u0176\7$\2\2\u0176\u0177\5V,\2\u0177S\3\2\2\2"+
		"\u0178\u0179\7!\2\2\u0179U\3\2\2\2\u017a\u017b\7G\2\2\u017bW\3\2\2\2\u017c"+
		"\u017d\7\16\2\2\u017d\u017e\7\r\2\2\u017e\u017f\5Z.\2\u017f\u0180\7B\2"+
		"\2\u0180Y\3\2\2\2\u0181\u0186\5\\/\2\u0182\u0183\7H\2\2\u0183\u0185\5"+
		"\\/\2\u0184\u0182\3\2\2\2\u0185\u0188\3\2\2\2\u0186\u0184\3\2\2\2\u0186"+
		"\u0187\3\2\2\2\u0187[\3\2\2\2\u0188\u0186\3\2\2\2\u0189\u018a\5^\60\2"+
		"\u018a\u018b\5V,\2\u018b]\3\2\2\2\u018c\u018d\t\5\2\2\u018d_\3\2\2\2\u018e"+
		"\u0190\5b\62\2\u018f\u0191\5d\63\2\u0190\u018f\3\2\2\2\u0190\u0191\3\2"+
		"\2\2\u0191\u0192\3\2\2\2\u0192\u0193\5f\64\2\u0193\u0197\5h\65\2\u0194"+
		"\u0198\5v<\2\u0195\u0198\5\u0080A\2\u0196\u0198\5F$\2\u0197\u0194\3\2"+
		"\2\2\u0197\u0195\3\2\2\2\u0197\u0196\3\2\2\2\u0197\u0198\3\2\2\2\u0198"+
		"\u0199\3\2\2\2\u0199\u019a\7E\2\2\u019aa\3\2\2\2\u019b\u019c\7.\2\2\u019c"+
		"\u019d\5\60\31\2\u019dc\3\2\2\2\u019e\u019f\7>\2\2\u019f\u01a0\79\2\2"+
		"\u01a0\u01a1\7,\2\2\u01a1e\3\2\2\2\u01a2\u01a3\7A\2\2\u01a3\u01a4\5j\66"+
		"\2\u01a4g\3\2\2\2\u01a5\u01a6\7*\2\2\u01a6\u01a7\5j\66\2\u01a7i\3\2\2"+
		"\2\u01a8\u01a9\5l\67\2\u01a9\u01aa\5r:\2\u01aak\3\2\2\2\u01ab\u01ad\5"+
		"n8\2\u01ac\u01ae\5p9\2\u01ad\u01ac\3\2\2\2\u01ad\u01ae\3\2\2\2\u01aem"+
		"\3\2\2\2\u01af\u01b0\5\30\r\2\u01b0o\3\2\2\2\u01b1\u01b2\5 \21\2\u01b2"+
		"q\3\2\2\2\u01b3\u01b4\7\25\2\2\u01b4\u01b5\5t;\2\u01b5s\3\2\2\2\u01b6"+
		"\u01b7\t\6\2\2\u01b7u\3\2\2\2\u01b8\u01b9\7\n\2\2\u01b9\u01ba\5x=\2\u01ba"+
		"\u01bb\5z>\2\u01bb\u01bd\5|?\2\u01bc\u01be\5F$\2\u01bd\u01bc\3\2\2\2\u01bd"+
		"\u01be\3\2\2\2\u01bew\3\2\2\2\u01bf\u01c0\7G\2\2\u01c0y\3\2\2\2\u01c1"+
		"\u01c2\7A\2\2\u01c2\u01c3\5~@\2\u01c3{\3\2\2\2\u01c4\u01c5\7*\2\2\u01c5"+
		"\u01c6\5~@\2\u01c6}\3\2\2\2\u01c7\u01c8\5 \21\2\u01c8\177\3\2\2\2\u01c9"+
		"\u01cf\5\u0082B\2\u01ca\u01cc\5\u0084C\2\u01cb\u01cd\5F$\2\u01cc\u01cb"+
		"\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd\u01cf\3\2\2\2\u01ce\u01c9\3\2\2\2\u01ce"+
		"\u01ca\3\2\2\2\u01cf\u0081\3\2\2\2\u01d0\u01d1\7\t\2\2\u01d1\u01d2\7\3"+
		"\2\2\u01d2\u0083\3\2\2\2\u01d3\u01d4\7\3\2\2\u01d4\u01d5\7\'\2\2\u01d5"+
		"\u01d6\t\3\2\2\u01d6\u0085\3\2\2\2\u01d7\u01d8\7\22\2\2\u01d8\u01d9\5"+
		"\u0088E\2\u01d9\u0087\3\2\2\2\u01da\u01db\7\4\2\2\u01db\u01dd\5\u008a"+
		"F\2\u01dc\u01de\5\u008aF\2\u01dd\u01dc\3\2\2\2\u01dd\u01de\3\2\2\2\u01de"+
		"\u01df\3\2\2\2\u01df\u01e0\7(\2\2\u01e0\u0089\3\2\2\2\u01e1\u01e2\7\61"+
		"\2\2\u01e2\u01e3\7\63\2\2\u01e3\u01e4\7E\2\2\u01e4\u008b\3\2\2\2\u01e5"+
		"\u01e6\7\65\2\2\u01e6\u01e7\5\u008eH\2\u01e7\u008d\3\2\2\2\u01e8\u01ec"+
		"\7\4\2\2\u01e9\u01eb\5\u0090I\2\u01ea\u01e9\3\2\2\2\u01eb\u01ee\3\2\2"+
		"\2\u01ec\u01ea\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01ef\3\2\2\2\u01ee\u01ec"+
		"\3\2\2\2\u01ef\u01f0\7(\2\2\u01f0\u008f\3\2\2\2\u01f1\u01f7\5\u0092J\2"+
		"\u01f2\u01f7\5\u0094K\2\u01f3\u01f7\5\u0096L\2\u01f4\u01f7\5\u0098M\2"+
		"\u01f5\u01f7\5\u00a0Q\2\u01f6\u01f1\3\2\2\2\u01f6\u01f2\3\2\2\2\u01f6"+
		"\u01f3\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f6\u01f5\3\2\2\2\u01f7\u0091\3\2"+
		"\2\2\u01f8\u01f9\7;\2\2\u01f9\u01fa\7\f\2\2\u01fa\u01fb\7E\2\2\u01fb\u0093"+
		"\3\2\2\2\u01fc\u01fd\7\13\2\2\u01fd\u01fe\7:\2\2\u01fe\u01ff\7G\2\2\u01ff"+
		"\u0200\7E\2\2\u0200\u0095\3\2\2\2\u0201\u0202\7/\2\2\u0202\u0203\7:\2"+
		"\2\u0203\u0204\7G\2\2\u0204\u0205\7E\2\2\u0205\u0097\3\2\2\2\u0206\u0208"+
		"\7\34\2\2\u0207\u0209\5\u009aN\2\u0208\u0207\3\2\2\2\u0208\u0209\3\2\2"+
		"\2\u0209\u020b\3\2\2\2\u020a\u020c\5\u009eP\2\u020b\u020a\3\2\2\2\u020b"+
		"\u020c\3\2\2\2\u020c\u020d\3\2\2\2\u020d\u020e\7E\2\2\u020e\u0099\3\2"+
		"\2\2\u020f\u0214\5\u009cO\2\u0210\u0211\7H\2\2\u0211\u0213\5\u009cO\2"+
		"\u0212\u0210\3\2\2\2\u0213\u0216\3\2\2\2\u0214\u0212\3\2\2\2\u0214\u0215"+
		"\3\2\2\2\u0215\u009b\3\2\2\2\u0216\u0214\3\2\2\2\u0217\u0218\7G\2\2\u0218"+
		"\u009d\3\2\2\2\u0219\u021a\7\f\2\2\u021a\u009f\3\2\2\2\u021b\u021c\7\33"+
		"\2\2\u021c\u0221\5\u00a2R\2\u021d\u021e\7H\2\2\u021e\u0220\5\u00a2R\2"+
		"\u021f\u021d\3\2\2\2\u0220\u0223\3\2\2\2\u0221\u021f\3\2\2\2\u0221\u0222"+
		"\3\2\2\2\u0222\u0224\3\2\2\2\u0223\u0221\3\2\2\2\u0224\u0225\7E\2\2\u0225"+
		"\u00a1\3\2\2\2\u0226\u0229\5\u00a4S\2\u0227\u0229\5\u00a6T\2\u0228\u0226"+
		"\3\2\2\2\u0228\u0227\3\2\2\2\u0229\u022a\3\2\2\2\u022a\u022b\7F\2\2\u022b"+
		"\u022c\7N\2\2\u022c\u00a3\3\2\2\2\u022d\u022e\7#\2\2\u022e\u00a5\3\2\2"+
		"\2\u022f\u0230\7=\2\2\u0230\u00a7\3\2\2\2.\u00aa\u00ad\u00b1\u00ba\u00c0"+
		"\u00c4\u00c8\u00cb\u00ce\u00d1\u00d4\u00d7\u00da\u00df\u00e4\u00f3\u0101"+
		"\u010b\u011d\u0124\u0130\u0138\u0146\u014a\u014d\u015c\u0162\u016f\u0173"+
		"\u0186\u0190\u0197\u01ad\u01bd\u01cc\u01ce\u01dd\u01ec\u01f6\u0208\u020b"+
		"\u0214\u0221\u0228";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}