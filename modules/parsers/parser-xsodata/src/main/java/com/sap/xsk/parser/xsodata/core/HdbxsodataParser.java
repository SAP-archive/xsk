/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
// Generated from com/sap/xsk/parser/xsodata/core/Hdbxsodata.g4 by ANTLR 4.10.1
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
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, SEMICOLON=68, EQ=69, QUATED_STRING=70, COMMA=71, COLON=72, ESC=73, 
		WS=74, LINE_COMMENT=75, COMMENT=76, INT=77;
	public static final int
		RULE_xsodataDefinition = 0, RULE_service = 1, RULE_namespace = 2, RULE_body = 3, 
		RULE_content = 4, RULE_entry = 5, RULE_entity = 6, RULE_object = 7, RULE_catalogobjectschema = 8, 
		RULE_catalogobjectname = 9, RULE_entityset = 10, RULE_entitysetname = 11, 
		RULE_with = 12, RULE_withProp = 13, RULE_withoutProp = 14, RULE_propertylist = 15, 
		RULE_columnname = 16, RULE_keys = 17, RULE_keylist = 18, RULE_keygenerated = 19, 
		RULE_concurrencytoken = 20, RULE_navigates = 21, RULE_navlist = 22, RULE_naventry = 23, 
		RULE_assocname = 24, RULE_navpropname = 25, RULE_fromend = 26, RULE_principal = 27, 
		RULE_dependent = 28, RULE_aggregates = 29, RULE_aggregatestuple = 30, 
		RULE_aggregate = 31, RULE_aggregatefunction = 32, RULE_parameters = 33, 
		RULE_parameterskeyand = 34, RULE_parameterentitysetname = 35, RULE_parametersresultsprop = 36, 
		RULE_modificationBody = 37, RULE_modification = 38, RULE_create = 39, 
		RULE_update = 40, RULE_delete = 41, RULE_modificationspec = 42, RULE_modificationaction = 43, 
		RULE_forbidden = 44, RULE_action = 45, RULE_events = 46, RULE_eventlist = 47, 
		RULE_eventlistElement = 48, RULE_eventtype = 49, RULE_association = 50, 
		RULE_associationdef = 51, RULE_assocrefconstraint = 52, RULE_principalend = 53, 
		RULE_dependentend = 54, RULE_end = 55, RULE_endref = 56, RULE_endtype = 57, 
		RULE_joinpropertieslist = 58, RULE_multiplicity = 59, RULE_multiplicityvalue = 60, 
		RULE_assoctable = 61, RULE_repoobject = 62, RULE_overprincipalend = 63, 
		RULE_overdependentend = 64, RULE_overend = 65, RULE_storage = 66, RULE_nostorage = 67, 
		RULE_storageend = 68, RULE_annotations = 69, RULE_annotationsbody = 70, 
		RULE_annotationconfig = 71, RULE_settings = 72, RULE_settingsbody = 73, 
		RULE_settingselement = 74, RULE_supportnull = 75, RULE_contentcashecontrol = 76, 
		RULE_metadatacashecontrol = 77, RULE_hints = 78, RULE_hintlist = 79, RULE_hintvalue = 80, 
		RULE_nullvalue = 81, RULE_limits = 82, RULE_limitvalue = 83, RULE_maxrecords = 84, 
		RULE_maxexpandedrecords = 85;
	private static String[] makeRuleNames() {
		return new String[] {
			"xsodataDefinition", "service", "namespace", "body", "content", "entry", 
			"entity", "object", "catalogobjectschema", "catalogobjectname", "entityset", 
			"entitysetname", "with", "withProp", "withoutProp", "propertylist", "columnname", 
			"keys", "keylist", "keygenerated", "concurrencytoken", "navigates", "navlist", 
			"naventry", "assocname", "navpropname", "fromend", "principal", "dependent", 
			"aggregates", "aggregatestuple", "aggregate", "aggregatefunction", "parameters", 
			"parameterskeyand", "parameterentitysetname", "parametersresultsprop", 
			"modificationBody", "modification", "create", "update", "delete", "modificationspec", 
			"modificationaction", "forbidden", "action", "events", "eventlist", "eventlistElement", 
			"eventtype", "association", "associationdef", "assocrefconstraint", "principalend", 
			"dependentend", "end", "endref", "endtype", "joinpropertieslist", "multiplicity", 
			"multiplicityvalue", "assoctable", "repoobject", "overprincipalend", 
			"overdependentend", "overend", "storage", "nostorage", "storageend", 
			"annotations", "annotationsbody", "annotationconfig", "settings", "settingsbody", 
			"settingselement", "supportnull", "contentcashecontrol", "metadatacashecontrol", 
			"hints", "hintlist", "hintvalue", "nullvalue", "limits", "limitvalue", 
			"maxrecords", "maxexpandedrecords"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'service'", "'namespace'", "'{'", "'}'", "'entity'", "'.'", "'as'", 
			"'with'", "'without'", "'('", "')'", "'keys'", "'key'", "'generate'", 
			"'local'", "'concurrencytoken'", "'navigates'", "'from'", "'principal'", 
			"'dependent'", "'aggregates'", "'always'", "'of'", "'SUM'", "'AVG'", 
			"'MIN'", "'MAX'", "'parameters'", "'via'", "'and'", "'results'", "'property'", 
			"'create'", "'update'", "'delete'", "'using'", "'forbidden'", "'events'", 
			"'before'", "'after'", "'precommit'", "'postcommit'", "'association'", 
			"'referential'", "'constraint'", "'multiplicity'", "'\"1\"'", "'\"0..1\"'", 
			"'\"1..*\"'", "'\"*\"'", "'over'", "'no'", "'storage'", "'on'", "'annotations'", 
			"'enable'", "'OData4SAP'", "'settings'", "'support'", "'null'", "'content'", 
			"'cache-control'", "'metadata'", "'hints'", "'limits'", "'max_records'", 
			"'max_expanded_records'", "';'", "'='", null, "','", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "SEMICOLON", "EQ", "QUATED_STRING", 
			"COMMA", "COLON", "ESC", "WS", "LINE_COMMENT", "COMMENT", "INT"
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
	public String getGrammarFileName() { return "Hdbxsodata.g4"; }

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
		public ServiceContext service() {
			return getRuleContext(ServiceContext.class,0);
		}
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public SettingsContext settings() {
			return getRuleContext(SettingsContext.class,0);
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
			setState(172);
			service();
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__54) {
				{
				setState(173);
				annotations();
				}
			}

			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__57) {
				{
				setState(176);
				settings();
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
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public NamespaceContext namespace() {
			return getRuleContext(NamespaceContext.class,0);
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
			setState(179);
			match(T__0);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(180);
				namespace();
				}
			}

			setState(183);
			body();
			}
		}
		catch (RecognitionException re) {
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
			setState(185);
			match(T__1);
			setState(186);
			match(QUATED_STRING);
			}
		}
		catch (RecognitionException re) {
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
			setState(188);
			match(T__2);
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4 || _la==T__42 || _la==QUATED_STRING) {
				{
				setState(189);
				content();
				}
			}

			setState(192);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
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
		public EntryContext entry() {
			return getRuleContext(EntryContext.class,0);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
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
			setState(194);
			entry();
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4 || _la==T__42 || _la==QUATED_STRING) {
				{
				setState(195);
				content();
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
			setState(200);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case QUATED_STRING:
				{
				setState(198);
				entity();
				}
				break;
			case T__42:
				{
				setState(199);
				association();
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
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(HdbxsodataParser.SEMICOLON, 0); }
		public EntitysetContext entityset() {
			return getRuleContext(EntitysetContext.class,0);
		}
		public WithContext with() {
			return getRuleContext(WithContext.class,0);
		}
		public KeysContext keys() {
			return getRuleContext(KeysContext.class,0);
		}
		public ConcurrencytokenContext concurrencytoken() {
			return getRuleContext(ConcurrencytokenContext.class,0);
		}
		public NavigatesContext navigates() {
			return getRuleContext(NavigatesContext.class,0);
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
			setState(202);
			object();
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(203);
				entityset();
				}
			}

			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7 || _la==T__8) {
				{
				setState(206);
				with();
				}
			}

			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11 || _la==T__12) {
				{
				setState(209);
				keys();
				}
			}

			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(212);
				concurrencytoken();
				}
			}

			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(215);
				navigates();
				}
			}

			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__20) {
				{
				setState(218);
				aggregates();
				}
			}

			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(221);
				parameters();
				}
			}

			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__32) | (1L << T__33) | (1L << T__34))) != 0)) {
				{
				setState(224);
				modificationBody();
				}
			}

			setState(227);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
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
		public CatalogobjectnameContext catalogobjectname() {
			return getRuleContext(CatalogobjectnameContext.class,0);
		}
		public CatalogobjectschemaContext catalogobjectschema() {
			return getRuleContext(CatalogobjectschemaContext.class,0);
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
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(229);
				match(T__4);
				}
			}

			setState(235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(232);
				catalogobjectschema();
				setState(233);
				match(T__5);
				}
				break;
			}
			setState(237);
			catalogobjectname();
			}
		}
		catch (RecognitionException re) {
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
			setState(239);
			match(QUATED_STRING);
			}
		}
		catch (RecognitionException re) {
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
			setState(241);
			match(QUATED_STRING);
			}
		}
		catch (RecognitionException re) {
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
			setState(243);
			match(T__6);
			setState(244);
			entitysetname();
			}
		}
		catch (RecognitionException re) {
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
			setState(246);
			match(QUATED_STRING);
			}
		}
		catch (RecognitionException re) {
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
		public PropertylistContext propertylist() {
			return getRuleContext(PropertylistContext.class,0);
		}
		public WithPropContext withProp() {
			return getRuleContext(WithPropContext.class,0);
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
			setState(250);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				{
				setState(248);
				withProp();
				}
				break;
			case T__8:
				{
				setState(249);
				withoutProp();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(252);
			propertylist();
			}
		}
		catch (RecognitionException re) {
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
			setState(254);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
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
			setState(256);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
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
		public List<TerminalNode> COMMA() { return getTokens(HdbxsodataParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HdbxsodataParser.COMMA, i);
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
			setState(258);
			match(T__9);
			{
			setState(259);
			columnname();
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(260);
				match(COMMA);
				setState(261);
				columnname();
				}
				}
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(267);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
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
			setState(269);
			match(QUATED_STRING);
			}
		}
		catch (RecognitionException re) {
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
			setState(271);
			_la = _input.LA(1);
			if ( !(_la==T__11 || _la==T__12) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(274);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				{
				setState(272);
				keylist();
				}
				break;
			case T__13:
				{
				setState(273);
				keygenerated();
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
			setState(276);
			propertylist();
			}
		}
		catch (RecognitionException re) {
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
			setState(278);
			match(T__13);
			setState(279);
			match(T__14);
			setState(280);
			columnname();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConcurrencytokenContext extends ParserRuleContext {
		public KeylistContext keylist() {
			return getRuleContext(KeylistContext.class,0);
		}
		public ConcurrencytokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_concurrencytoken; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterConcurrencytoken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitConcurrencytoken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitConcurrencytoken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConcurrencytokenContext concurrencytoken() throws RecognitionException {
		ConcurrencytokenContext _localctx = new ConcurrencytokenContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_concurrencytoken);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(T__15);
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(283);
				keylist();
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
		enterRule(_localctx, 42, RULE_navigates);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(T__16);
			setState(287);
			match(T__9);
			setState(288);
			navlist();
			setState(289);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
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
		public List<TerminalNode> COMMA() { return getTokens(HdbxsodataParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HdbxsodataParser.COMMA, i);
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
		enterRule(_localctx, 44, RULE_navlist);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(291);
			naventry();
			setState(296);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(292);
					match(COMMA);
					setState(293);
					navlist();
					}
					} 
				}
				setState(298);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
		public NavpropnameContext navpropname() {
			return getRuleContext(NavpropnameContext.class,0);
		}
		public FromendContext fromend() {
			return getRuleContext(FromendContext.class,0);
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
		enterRule(_localctx, 46, RULE_naventry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			assocname();
			setState(300);
			match(T__6);
			setState(301);
			navpropname();
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__17) {
				{
				setState(302);
				fromend();
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
		enterRule(_localctx, 48, RULE_assocname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			match(QUATED_STRING);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 50, RULE_navpropname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			match(QUATED_STRING);
			}
		}
		catch (RecognitionException re) {
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
		public PrincipalContext principal() {
			return getRuleContext(PrincipalContext.class,0);
		}
		public DependentContext dependent() {
			return getRuleContext(DependentContext.class,0);
		}
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
		enterRule(_localctx, 52, RULE_fromend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			match(T__17);
			setState(312);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				{
				setState(310);
				principal();
				}
				break;
			case T__19:
				{
				setState(311);
				dependent();
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

	public static class PrincipalContext extends ParserRuleContext {
		public PrincipalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_principal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterPrincipal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitPrincipal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitPrincipal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrincipalContext principal() throws RecognitionException {
		PrincipalContext _localctx = new PrincipalContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_principal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DependentContext extends ParserRuleContext {
		public DependentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dependent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).enterDependent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HdbxsodataListener ) ((HdbxsodataListener)listener).exitDependent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HdbxsodataVisitor ) return ((HdbxsodataVisitor<? extends T>)visitor).visitDependent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DependentContext dependent() throws RecognitionException {
		DependentContext _localctx = new DependentContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_dependent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 58, RULE_aggregates);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			match(T__20);
			setState(319);
			match(T__21);
			setState(321);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(320);
				aggregatestuple();
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
		public List<AggregateContext> aggregate() {
			return getRuleContexts(AggregateContext.class);
		}
		public AggregateContext aggregate(int i) {
			return getRuleContext(AggregateContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HdbxsodataParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HdbxsodataParser.COMMA, i);
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
		enterRule(_localctx, 60, RULE_aggregatestuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			match(T__9);
			{
			setState(324);
			aggregate();
			setState(329);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(325);
				match(COMMA);
				setState(326);
				aggregate();
				}
				}
				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(332);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 62, RULE_aggregate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			aggregatefunction();
			setState(335);
			match(T__22);
			setState(336);
			columnname();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 64, RULE_aggregatefunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
		enterRule(_localctx, 66, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			match(T__27);
			setState(341);
			match(T__28);
			setState(343);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(342);
				parameterskeyand();
				}
			}

			setState(345);
			match(T__4);
			setState(347);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUATED_STRING) {
				{
				setState(346);
				parameterentitysetname();
				}
			}

			setState(350);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__30) {
				{
				setState(349);
				parametersresultsprop();
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
		enterRule(_localctx, 68, RULE_parameterskeyand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			match(T__12);
			setState(353);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 70, RULE_parameterentitysetname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			match(QUATED_STRING);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 72, RULE_parametersresultsprop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			match(T__30);
			setState(358);
			match(T__31);
			setState(359);
			match(QUATED_STRING);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 74, RULE_modificationBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			modification();
			setState(365);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__32) | (1L << T__33) | (1L << T__34))) != 0)) {
				{
				{
				setState(362);
				modification();
				}
				}
				setState(367);
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
		enterRule(_localctx, 76, RULE_modification);
		try {
			setState(371);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__32:
				enterOuterAlt(_localctx, 1);
				{
				setState(368);
				create();
				}
				break;
			case T__33:
				enterOuterAlt(_localctx, 2);
				{
				setState(369);
				update();
				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 3);
				{
				setState(370);
				delete();
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
		enterRule(_localctx, 78, RULE_create);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			match(T__32);
			setState(374);
			modificationspec();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 80, RULE_update);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			match(T__33);
			setState(377);
			modificationspec();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 82, RULE_delete);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			match(T__34);
			setState(380);
			modificationspec();
			}
		}
		catch (RecognitionException re) {
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
		public ForbiddenContext forbidden() {
			return getRuleContext(ForbiddenContext.class,0);
		}
		public ModificationactionContext modificationaction() {
			return getRuleContext(ModificationactionContext.class,0);
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
		enterRule(_localctx, 84, RULE_modificationspec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__35:
				{
				{
				setState(382);
				modificationaction();
				setState(384);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__37) {
					{
					setState(383);
					events();
					}
				}

				}
				}
				break;
			case T__37:
				{
				setState(386);
				events();
				}
				break;
			case T__36:
				{
				setState(387);
				forbidden();
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
		enterRule(_localctx, 86, RULE_modificationaction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(390);
			match(T__35);
			setState(391);
			action();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 88, RULE_forbidden);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			match(T__36);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 90, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
			match(QUATED_STRING);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 92, RULE_events);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397);
			match(T__37);
			setState(398);
			match(T__9);
			setState(399);
			eventlist();
			setState(400);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
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
		public List<EventlistElementContext> eventlistElement() {
			return getRuleContexts(EventlistElementContext.class);
		}
		public EventlistElementContext eventlistElement(int i) {
			return getRuleContext(EventlistElementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HdbxsodataParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HdbxsodataParser.COMMA, i);
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
		enterRule(_localctx, 94, RULE_eventlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(402);
			eventlistElement();
			setState(407);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(403);
				match(COMMA);
				setState(404);
				eventlistElement();
				}
				}
				setState(409);
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
		public EventtypeContext eventtype() {
			return getRuleContext(EventtypeContext.class,0);
		}
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
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
		enterRule(_localctx, 96, RULE_eventlistElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(410);
			eventtype();
			setState(411);
			action();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 98, RULE_eventtype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class AssociationContext extends ParserRuleContext {
		public AssociationdefContext associationdef() {
			return getRuleContext(AssociationdefContext.class,0);
		}
		public PrincipalendContext principalend() {
			return getRuleContext(PrincipalendContext.class,0);
		}
		public DependentendContext dependentend() {
			return getRuleContext(DependentendContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(HdbxsodataParser.SEMICOLON, 0); }
		public AssocrefconstraintContext assocrefconstraint() {
			return getRuleContext(AssocrefconstraintContext.class,0);
		}
		public AssoctableContext assoctable() {
			return getRuleContext(AssoctableContext.class,0);
		}
		public StorageContext storage() {
			return getRuleContext(StorageContext.class,0);
		}
		public ModificationBodyContext modificationBody() {
			return getRuleContext(ModificationBodyContext.class,0);
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
		enterRule(_localctx, 100, RULE_association);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			associationdef();
			setState(417);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(416);
				assocrefconstraint();
				}
			}

			setState(419);
			principalend();
			setState(420);
			dependentend();
			setState(424);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__50:
				{
				setState(421);
				assoctable();
				}
				break;
			case T__51:
			case T__52:
				{
				setState(422);
				storage();
				}
				break;
			case T__32:
			case T__33:
			case T__34:
				{
				setState(423);
				modificationBody();
				}
				break;
			case SEMICOLON:
				break;
			default:
				break;
			}
			setState(426);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 102, RULE_associationdef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(428);
			match(T__42);
			setState(429);
			assocname();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 104, RULE_assocrefconstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			match(T__7);
			setState(432);
			match(T__43);
			setState(433);
			match(T__44);
			}
		}
		catch (RecognitionException re) {
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
		public PrincipalContext principal() {
			return getRuleContext(PrincipalContext.class,0);
		}
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
		enterRule(_localctx, 106, RULE_principalend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			principal();
			setState(436);
			end();
			}
		}
		catch (RecognitionException re) {
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
		public DependentContext dependent() {
			return getRuleContext(DependentContext.class,0);
		}
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
		enterRule(_localctx, 108, RULE_dependentend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			dependent();
			setState(439);
			end();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 110, RULE_end);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			endref();
			setState(442);
			multiplicity();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 112, RULE_endref);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
			endtype();
			setState(446);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(445);
				joinpropertieslist();
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
		enterRule(_localctx, 114, RULE_endtype);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(448);
			entitysetname();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 116, RULE_joinpropertieslist);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(450);
			propertylist();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 118, RULE_multiplicity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(452);
			match(T__45);
			setState(453);
			multiplicityvalue();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 120, RULE_multiplicityvalue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class AssoctableContext extends ParserRuleContext {
		public RepoobjectContext repoobject() {
			return getRuleContext(RepoobjectContext.class,0);
		}
		public OverprincipalendContext overprincipalend() {
			return getRuleContext(OverprincipalendContext.class,0);
		}
		public OverdependentendContext overdependentend() {
			return getRuleContext(OverdependentendContext.class,0);
		}
		public ModificationBodyContext modificationBody() {
			return getRuleContext(ModificationBodyContext.class,0);
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
		enterRule(_localctx, 122, RULE_assoctable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(457);
			match(T__50);
			setState(458);
			repoobject();
			setState(459);
			overprincipalend();
			setState(460);
			overdependentend();
			setState(462);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__32) | (1L << T__33) | (1L << T__34))) != 0)) {
				{
				setState(461);
				modificationBody();
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
		enterRule(_localctx, 124, RULE_repoobject);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(464);
			match(QUATED_STRING);
			}
		}
		catch (RecognitionException re) {
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
		public PrincipalContext principal() {
			return getRuleContext(PrincipalContext.class,0);
		}
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
		enterRule(_localctx, 126, RULE_overprincipalend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			principal();
			setState(467);
			overend();
			}
		}
		catch (RecognitionException re) {
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
		public DependentContext dependent() {
			return getRuleContext(DependentContext.class,0);
		}
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
		enterRule(_localctx, 128, RULE_overdependentend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			dependent();
			setState(470);
			overend();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 130, RULE_overend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(472);
			propertylist();
			}
		}
		catch (RecognitionException re) {
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
		public StorageendContext storageend() {
			return getRuleContext(StorageendContext.class,0);
		}
		public ModificationBodyContext modificationBody() {
			return getRuleContext(ModificationBodyContext.class,0);
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
		enterRule(_localctx, 132, RULE_storage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__51:
				{
				setState(474);
				nostorage();
				}
				break;
			case T__52:
				{
				setState(475);
				storageend();
				setState(477);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__32) | (1L << T__33) | (1L << T__34))) != 0)) {
					{
					setState(476);
					modificationBody();
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
		enterRule(_localctx, 134, RULE_nostorage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(481);
			match(T__51);
			setState(482);
			match(T__52);
			}
		}
		catch (RecognitionException re) {
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
		public PrincipalContext principal() {
			return getRuleContext(PrincipalContext.class,0);
		}
		public DependentContext dependent() {
			return getRuleContext(DependentContext.class,0);
		}
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
		enterRule(_localctx, 136, RULE_storageend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(484);
			match(T__52);
			setState(485);
			match(T__53);
			setState(488);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				{
				setState(486);
				principal();
				}
				break;
			case T__19:
				{
				setState(487);
				dependent();
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
		enterRule(_localctx, 138, RULE_annotations);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(490);
			match(T__54);
			setState(491);
			annotationsbody();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 140, RULE_annotationsbody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(493);
			match(T__2);
			setState(494);
			annotationconfig();
			setState(496);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__55) {
				{
				setState(495);
				annotationconfig();
				}
			}

			setState(498);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 142, RULE_annotationconfig);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			match(T__55);
			setState(501);
			match(T__56);
			setState(502);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 144, RULE_settings);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(504);
			match(T__57);
			setState(505);
			settingsbody();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 146, RULE_settingsbody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(507);
			match(T__2);
			setState(511);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 59)) & ~0x3f) == 0 && ((1L << (_la - 59)) & ((1L << (T__58 - 59)) | (1L << (T__60 - 59)) | (1L << (T__62 - 59)) | (1L << (T__63 - 59)) | (1L << (T__64 - 59)))) != 0)) {
				{
				{
				setState(508);
				settingselement();
				}
				}
				setState(513);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(514);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
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
		public SupportnullContext supportnull() {
			return getRuleContext(SupportnullContext.class,0);
		}
		public ContentcashecontrolContext contentcashecontrol() {
			return getRuleContext(ContentcashecontrolContext.class,0);
		}
		public MetadatacashecontrolContext metadatacashecontrol() {
			return getRuleContext(MetadatacashecontrolContext.class,0);
		}
		public HintsContext hints() {
			return getRuleContext(HintsContext.class,0);
		}
		public LimitsContext limits() {
			return getRuleContext(LimitsContext.class,0);
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
		enterRule(_localctx, 148, RULE_settingselement);
		try {
			setState(521);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__58:
				enterOuterAlt(_localctx, 1);
				{
				setState(516);
				supportnull();
				}
				break;
			case T__60:
				enterOuterAlt(_localctx, 2);
				{
				setState(517);
				contentcashecontrol();
				}
				break;
			case T__62:
				enterOuterAlt(_localctx, 3);
				{
				setState(518);
				metadatacashecontrol();
				}
				break;
			case T__63:
				enterOuterAlt(_localctx, 4);
				{
				setState(519);
				hints();
				}
				break;
			case T__64:
				enterOuterAlt(_localctx, 5);
				{
				setState(520);
				limits();
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
		enterRule(_localctx, 150, RULE_supportnull);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(523);
			match(T__58);
			setState(524);
			match(T__59);
			setState(525);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbxsodataParser.SEMICOLON, 0); }
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
		enterRule(_localctx, 152, RULE_contentcashecontrol);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(527);
			match(T__60);
			setState(528);
			match(T__61);
			setState(529);
			match(QUATED_STRING);
			setState(530);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode QUATED_STRING() { return getToken(HdbxsodataParser.QUATED_STRING, 0); }
		public TerminalNode SEMICOLON() { return getToken(HdbxsodataParser.SEMICOLON, 0); }
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
		enterRule(_localctx, 154, RULE_metadatacashecontrol);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(532);
			match(T__62);
			setState(533);
			match(T__61);
			setState(534);
			match(QUATED_STRING);
			setState(535);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 156, RULE_hints);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(537);
			match(T__63);
			setState(539);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUATED_STRING) {
				{
				setState(538);
				hintlist();
				}
			}

			setState(542);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__59) {
				{
				setState(541);
				nullvalue();
				}
			}

			setState(544);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
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
		public List<TerminalNode> COMMA() { return getTokens(HdbxsodataParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HdbxsodataParser.COMMA, i);
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
		enterRule(_localctx, 158, RULE_hintlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(546);
			hintvalue();
			setState(551);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(547);
				match(COMMA);
				setState(548);
				hintvalue();
				}
				}
				setState(553);
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
		enterRule(_localctx, 160, RULE_hintvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(554);
			match(QUATED_STRING);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 162, RULE_nullvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(556);
			match(T__59);
			}
		}
		catch (RecognitionException re) {
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
		public List<LimitvalueContext> limitvalue() {
			return getRuleContexts(LimitvalueContext.class);
		}
		public LimitvalueContext limitvalue(int i) {
			return getRuleContext(LimitvalueContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(HdbxsodataParser.SEMICOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(HdbxsodataParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HdbxsodataParser.COMMA, i);
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
		enterRule(_localctx, 164, RULE_limits);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(558);
			match(T__64);
			setState(559);
			limitvalue();
			setState(564);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(560);
				match(COMMA);
				setState(561);
				limitvalue();
				}
				}
				setState(566);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(567);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode EQ() { return getToken(HdbxsodataParser.EQ, 0); }
		public TerminalNode INT() { return getToken(HdbxsodataParser.INT, 0); }
		public MaxrecordsContext maxrecords() {
			return getRuleContext(MaxrecordsContext.class,0);
		}
		public MaxexpandedrecordsContext maxexpandedrecords() {
			return getRuleContext(MaxexpandedrecordsContext.class,0);
		}
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
		enterRule(_localctx, 166, RULE_limitvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__65:
				{
				setState(569);
				maxrecords();
				}
				break;
			case T__66:
				{
				setState(570);
				maxexpandedrecords();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(573);
			match(EQ);
			setState(574);
			match(INT);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 168, RULE_maxrecords);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(576);
			match(T__65);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 170, RULE_maxexpandedrecords);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(578);
			match(T__66);
			}
		}
		catch (RecognitionException re) {
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
		"\u0004\u0001M\u0245\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002"+
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002"+
		"A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007E\u0002"+
		"F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007J\u0002"+
		"K\u0007K\u0002L\u0007L\u0002M\u0007M\u0002N\u0007N\u0002O\u0007O\u0002"+
		"P\u0007P\u0002Q\u0007Q\u0002R\u0007R\u0002S\u0007S\u0002T\u0007T\u0002"+
		"U\u0007U\u0001\u0000\u0001\u0000\u0003\u0000\u00af\b\u0000\u0001\u0000"+
		"\u0003\u0000\u00b2\b\u0000\u0001\u0001\u0001\u0001\u0003\u0001\u00b6\b"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0003\u0003\u00bf\b\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0003\u0004\u00c5\b\u0004\u0001\u0005\u0001\u0005\u0003"+
		"\u0005\u00c9\b\u0005\u0001\u0006\u0001\u0006\u0003\u0006\u00cd\b\u0006"+
		"\u0001\u0006\u0003\u0006\u00d0\b\u0006\u0001\u0006\u0003\u0006\u00d3\b"+
		"\u0006\u0001\u0006\u0003\u0006\u00d6\b\u0006\u0001\u0006\u0003\u0006\u00d9"+
		"\b\u0006\u0001\u0006\u0003\u0006\u00dc\b\u0006\u0001\u0006\u0003\u0006"+
		"\u00df\b\u0006\u0001\u0006\u0003\u0006\u00e2\b\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0003\u0007\u00e7\b\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007\u00ec\b\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0003\f\u00fb\b\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f"+
		"\u0107\b\u000f\n\u000f\f\u000f\u010a\t\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0113"+
		"\b\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0003\u0014\u011d\b\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0005\u0016\u0127\b\u0016\n\u0016\f\u0016\u012a\t\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0130\b\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0003\u001a\u0139\b\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u0142\b\u001d\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0005\u001e\u0148\b\u001e\n\u001e"+
		"\f\u001e\u014b\t\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001!\u0003!\u0158"+
		"\b!\u0001!\u0001!\u0003!\u015c\b!\u0001!\u0003!\u015f\b!\u0001\"\u0001"+
		"\"\u0001\"\u0001#\u0001#\u0001$\u0001$\u0001$\u0001$\u0001%\u0001%\u0005"+
		"%\u016c\b%\n%\f%\u016f\t%\u0001&\u0001&\u0001&\u0003&\u0174\b&\u0001\'"+
		"\u0001\'\u0001\'\u0001(\u0001(\u0001(\u0001)\u0001)\u0001)\u0001*\u0001"+
		"*\u0003*\u0181\b*\u0001*\u0001*\u0003*\u0185\b*\u0001+\u0001+\u0001+\u0001"+
		",\u0001,\u0001-\u0001-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001/\u0001"+
		"/\u0001/\u0005/\u0196\b/\n/\f/\u0199\t/\u00010\u00010\u00010\u00011\u0001"+
		"1\u00012\u00012\u00032\u01a2\b2\u00012\u00012\u00012\u00012\u00012\u0003"+
		"2\u01a9\b2\u00012\u00012\u00013\u00013\u00013\u00014\u00014\u00014\u0001"+
		"4\u00015\u00015\u00015\u00016\u00016\u00016\u00017\u00017\u00017\u0001"+
		"8\u00018\u00038\u01bf\b8\u00019\u00019\u0001:\u0001:\u0001;\u0001;\u0001"+
		";\u0001<\u0001<\u0001=\u0001=\u0001=\u0001=\u0001=\u0003=\u01cf\b=\u0001"+
		">\u0001>\u0001?\u0001?\u0001?\u0001@\u0001@\u0001@\u0001A\u0001A\u0001"+
		"B\u0001B\u0001B\u0003B\u01de\bB\u0003B\u01e0\bB\u0001C\u0001C\u0001C\u0001"+
		"D\u0001D\u0001D\u0001D\u0003D\u01e9\bD\u0001E\u0001E\u0001E\u0001F\u0001"+
		"F\u0001F\u0003F\u01f1\bF\u0001F\u0001F\u0001G\u0001G\u0001G\u0001G\u0001"+
		"H\u0001H\u0001H\u0001I\u0001I\u0005I\u01fe\bI\nI\fI\u0201\tI\u0001I\u0001"+
		"I\u0001J\u0001J\u0001J\u0001J\u0001J\u0003J\u020a\bJ\u0001K\u0001K\u0001"+
		"K\u0001K\u0001L\u0001L\u0001L\u0001L\u0001L\u0001M\u0001M\u0001M\u0001"+
		"M\u0001M\u0001N\u0001N\u0003N\u021c\bN\u0001N\u0003N\u021f\bN\u0001N\u0001"+
		"N\u0001O\u0001O\u0001O\u0005O\u0226\bO\nO\fO\u0229\tO\u0001P\u0001P\u0001"+
		"Q\u0001Q\u0001R\u0001R\u0001R\u0001R\u0005R\u0233\bR\nR\fR\u0236\tR\u0001"+
		"R\u0001R\u0001S\u0001S\u0003S\u023c\bS\u0001S\u0001S\u0001S\u0001T\u0001"+
		"T\u0001U\u0001U\u0001U\u0000\u0000V\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDF"+
		"HJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c"+
		"\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4"+
		"\u00a6\u00a8\u00aa\u0000\u0004\u0001\u0000\f\r\u0001\u0000\u0018\u001b"+
		"\u0001\u0000\'*\u0001\u0000/2\u0225\u0000\u00ac\u0001\u0000\u0000\u0000"+
		"\u0002\u00b3\u0001\u0000\u0000\u0000\u0004\u00b9\u0001\u0000\u0000\u0000"+
		"\u0006\u00bc\u0001\u0000\u0000\u0000\b\u00c2\u0001\u0000\u0000\u0000\n"+
		"\u00c8\u0001\u0000\u0000\u0000\f\u00ca\u0001\u0000\u0000\u0000\u000e\u00e6"+
		"\u0001\u0000\u0000\u0000\u0010\u00ef\u0001\u0000\u0000\u0000\u0012\u00f1"+
		"\u0001\u0000\u0000\u0000\u0014\u00f3\u0001\u0000\u0000\u0000\u0016\u00f6"+
		"\u0001\u0000\u0000\u0000\u0018\u00fa\u0001\u0000\u0000\u0000\u001a\u00fe"+
		"\u0001\u0000\u0000\u0000\u001c\u0100\u0001\u0000\u0000\u0000\u001e\u0102"+
		"\u0001\u0000\u0000\u0000 \u010d\u0001\u0000\u0000\u0000\"\u010f\u0001"+
		"\u0000\u0000\u0000$\u0114\u0001\u0000\u0000\u0000&\u0116\u0001\u0000\u0000"+
		"\u0000(\u011a\u0001\u0000\u0000\u0000*\u011e\u0001\u0000\u0000\u0000,"+
		"\u0123\u0001\u0000\u0000\u0000.\u012b\u0001\u0000\u0000\u00000\u0131\u0001"+
		"\u0000\u0000\u00002\u0133\u0001\u0000\u0000\u00004\u0135\u0001\u0000\u0000"+
		"\u00006\u013a\u0001\u0000\u0000\u00008\u013c\u0001\u0000\u0000\u0000:"+
		"\u013e\u0001\u0000\u0000\u0000<\u0143\u0001\u0000\u0000\u0000>\u014e\u0001"+
		"\u0000\u0000\u0000@\u0152\u0001\u0000\u0000\u0000B\u0154\u0001\u0000\u0000"+
		"\u0000D\u0160\u0001\u0000\u0000\u0000F\u0163\u0001\u0000\u0000\u0000H"+
		"\u0165\u0001\u0000\u0000\u0000J\u0169\u0001\u0000\u0000\u0000L\u0173\u0001"+
		"\u0000\u0000\u0000N\u0175\u0001\u0000\u0000\u0000P\u0178\u0001\u0000\u0000"+
		"\u0000R\u017b\u0001\u0000\u0000\u0000T\u0184\u0001\u0000\u0000\u0000V"+
		"\u0186\u0001\u0000\u0000\u0000X\u0189\u0001\u0000\u0000\u0000Z\u018b\u0001"+
		"\u0000\u0000\u0000\\\u018d\u0001\u0000\u0000\u0000^\u0192\u0001\u0000"+
		"\u0000\u0000`\u019a\u0001\u0000\u0000\u0000b\u019d\u0001\u0000\u0000\u0000"+
		"d\u019f\u0001\u0000\u0000\u0000f\u01ac\u0001\u0000\u0000\u0000h\u01af"+
		"\u0001\u0000\u0000\u0000j\u01b3\u0001\u0000\u0000\u0000l\u01b6\u0001\u0000"+
		"\u0000\u0000n\u01b9\u0001\u0000\u0000\u0000p\u01bc\u0001\u0000\u0000\u0000"+
		"r\u01c0\u0001\u0000\u0000\u0000t\u01c2\u0001\u0000\u0000\u0000v\u01c4"+
		"\u0001\u0000\u0000\u0000x\u01c7\u0001\u0000\u0000\u0000z\u01c9\u0001\u0000"+
		"\u0000\u0000|\u01d0\u0001\u0000\u0000\u0000~\u01d2\u0001\u0000\u0000\u0000"+
		"\u0080\u01d5\u0001\u0000\u0000\u0000\u0082\u01d8\u0001\u0000\u0000\u0000"+
		"\u0084\u01df\u0001\u0000\u0000\u0000\u0086\u01e1\u0001\u0000\u0000\u0000"+
		"\u0088\u01e4\u0001\u0000\u0000\u0000\u008a\u01ea\u0001\u0000\u0000\u0000"+
		"\u008c\u01ed\u0001\u0000\u0000\u0000\u008e\u01f4\u0001\u0000\u0000\u0000"+
		"\u0090\u01f8\u0001\u0000\u0000\u0000\u0092\u01fb\u0001\u0000\u0000\u0000"+
		"\u0094\u0209\u0001\u0000\u0000\u0000\u0096\u020b\u0001\u0000\u0000\u0000"+
		"\u0098\u020f\u0001\u0000\u0000\u0000\u009a\u0214\u0001\u0000\u0000\u0000"+
		"\u009c\u0219\u0001\u0000\u0000\u0000\u009e\u0222\u0001\u0000\u0000\u0000"+
		"\u00a0\u022a\u0001\u0000\u0000\u0000\u00a2\u022c\u0001\u0000\u0000\u0000"+
		"\u00a4\u022e\u0001\u0000\u0000\u0000\u00a6\u023b\u0001\u0000\u0000\u0000"+
		"\u00a8\u0240\u0001\u0000\u0000\u0000\u00aa\u0242\u0001\u0000\u0000\u0000"+
		"\u00ac\u00ae\u0003\u0002\u0001\u0000\u00ad\u00af\u0003\u008aE\u0000\u00ae"+
		"\u00ad\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af"+
		"\u00b1\u0001\u0000\u0000\u0000\u00b0\u00b2\u0003\u0090H\u0000\u00b1\u00b0"+
		"\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2\u0001"+
		"\u0001\u0000\u0000\u0000\u00b3\u00b5\u0005\u0001\u0000\u0000\u00b4\u00b6"+
		"\u0003\u0004\u0002\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b5\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b8"+
		"\u0003\u0006\u0003\u0000\u00b8\u0003\u0001\u0000\u0000\u0000\u00b9\u00ba"+
		"\u0005\u0002\u0000\u0000\u00ba\u00bb\u0005F\u0000\u0000\u00bb\u0005\u0001"+
		"\u0000\u0000\u0000\u00bc\u00be\u0005\u0003\u0000\u0000\u00bd\u00bf\u0003"+
		"\b\u0004\u0000\u00be\u00bd\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000"+
		"\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c1\u0005\u0004"+
		"\u0000\u0000\u00c1\u0007\u0001\u0000\u0000\u0000\u00c2\u00c4\u0003\n\u0005"+
		"\u0000\u00c3\u00c5\u0003\b\u0004\u0000\u00c4\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\t\u0001\u0000\u0000\u0000\u00c6"+
		"\u00c9\u0003\f\u0006\u0000\u00c7\u00c9\u0003d2\u0000\u00c8\u00c6\u0001"+
		"\u0000\u0000\u0000\u00c8\u00c7\u0001\u0000\u0000\u0000\u00c9\u000b\u0001"+
		"\u0000\u0000\u0000\u00ca\u00cc\u0003\u000e\u0007\u0000\u00cb\u00cd\u0003"+
		"\u0014\n\u0000\u00cc\u00cb\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cd\u00cf\u0001\u0000\u0000\u0000\u00ce\u00d0\u0003\u0018"+
		"\f\u0000\u00cf\u00ce\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000"+
		"\u0000\u00d0\u00d2\u0001\u0000\u0000\u0000\u00d1\u00d3\u0003\"\u0011\u0000"+
		"\u00d2\u00d1\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000"+
		"\u00d3\u00d5\u0001\u0000\u0000\u0000\u00d4\u00d6\u0003(\u0014\u0000\u00d5"+
		"\u00d4\u0001\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d8\u0001\u0000\u0000\u0000\u00d7\u00d9\u0003*\u0015\u0000\u00d8\u00d7"+
		"\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u00db"+
		"\u0001\u0000\u0000\u0000\u00da\u00dc\u0003:\u001d\u0000\u00db\u00da\u0001"+
		"\u0000\u0000\u0000\u00db\u00dc\u0001\u0000\u0000\u0000\u00dc\u00de\u0001"+
		"\u0000\u0000\u0000\u00dd\u00df\u0003B!\u0000\u00de\u00dd\u0001\u0000\u0000"+
		"\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df\u00e1\u0001\u0000\u0000"+
		"\u0000\u00e0\u00e2\u0003J%\u0000\u00e1\u00e0\u0001\u0000\u0000\u0000\u00e1"+
		"\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e4\u0005D\u0000\u0000\u00e4\r\u0001\u0000\u0000\u0000\u00e5\u00e7"+
		"\u0005\u0005\u0000\u0000\u00e6\u00e5\u0001\u0000\u0000\u0000\u00e6\u00e7"+
		"\u0001\u0000\u0000\u0000\u00e7\u00eb\u0001\u0000\u0000\u0000\u00e8\u00e9"+
		"\u0003\u0010\b\u0000\u00e9\u00ea\u0005\u0006\u0000\u0000\u00ea\u00ec\u0001"+
		"\u0000\u0000\u0000\u00eb\u00e8\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001"+
		"\u0000\u0000\u0000\u00ec\u00ed\u0001\u0000\u0000\u0000\u00ed\u00ee\u0003"+
		"\u0012\t\u0000\u00ee\u000f\u0001\u0000\u0000\u0000\u00ef\u00f0\u0005F"+
		"\u0000\u0000\u00f0\u0011\u0001\u0000\u0000\u0000\u00f1\u00f2\u0005F\u0000"+
		"\u0000\u00f2\u0013\u0001\u0000\u0000\u0000\u00f3\u00f4\u0005\u0007\u0000"+
		"\u0000\u00f4\u00f5\u0003\u0016\u000b\u0000\u00f5\u0015\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f7\u0005F\u0000\u0000\u00f7\u0017\u0001\u0000\u0000\u0000"+
		"\u00f8\u00fb\u0003\u001a\r\u0000\u00f9\u00fb\u0003\u001c\u000e\u0000\u00fa"+
		"\u00f8\u0001\u0000\u0000\u0000\u00fa\u00f9\u0001\u0000\u0000\u0000\u00fb"+
		"\u00fc\u0001\u0000\u0000\u0000\u00fc\u00fd\u0003\u001e\u000f\u0000\u00fd"+
		"\u0019\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005\b\u0000\u0000\u00ff\u001b"+
		"\u0001\u0000\u0000\u0000\u0100\u0101\u0005\t\u0000\u0000\u0101\u001d\u0001"+
		"\u0000\u0000\u0000\u0102\u0103\u0005\n\u0000\u0000\u0103\u0108\u0003 "+
		"\u0010\u0000\u0104\u0105\u0005G\u0000\u0000\u0105\u0107\u0003 \u0010\u0000"+
		"\u0106\u0104\u0001\u0000\u0000\u0000\u0107\u010a\u0001\u0000\u0000\u0000"+
		"\u0108\u0106\u0001\u0000\u0000\u0000\u0108\u0109\u0001\u0000\u0000\u0000"+
		"\u0109\u010b\u0001\u0000\u0000\u0000\u010a\u0108\u0001\u0000\u0000\u0000"+
		"\u010b\u010c\u0005\u000b\u0000\u0000\u010c\u001f\u0001\u0000\u0000\u0000"+
		"\u010d\u010e\u0005F\u0000\u0000\u010e!\u0001\u0000\u0000\u0000\u010f\u0112"+
		"\u0007\u0000\u0000\u0000\u0110\u0113\u0003$\u0012\u0000\u0111\u0113\u0003"+
		"&\u0013\u0000\u0112\u0110\u0001\u0000\u0000\u0000\u0112\u0111\u0001\u0000"+
		"\u0000\u0000\u0113#\u0001\u0000\u0000\u0000\u0114\u0115\u0003\u001e\u000f"+
		"\u0000\u0115%\u0001\u0000\u0000\u0000\u0116\u0117\u0005\u000e\u0000\u0000"+
		"\u0117\u0118\u0005\u000f\u0000\u0000\u0118\u0119\u0003 \u0010\u0000\u0119"+
		"\'\u0001\u0000\u0000\u0000\u011a\u011c\u0005\u0010\u0000\u0000\u011b\u011d"+
		"\u0003$\u0012\u0000\u011c\u011b\u0001\u0000\u0000\u0000\u011c\u011d\u0001"+
		"\u0000\u0000\u0000\u011d)\u0001\u0000\u0000\u0000\u011e\u011f\u0005\u0011"+
		"\u0000\u0000\u011f\u0120\u0005\n\u0000\u0000\u0120\u0121\u0003,\u0016"+
		"\u0000\u0121\u0122\u0005\u000b\u0000\u0000\u0122+\u0001\u0000\u0000\u0000"+
		"\u0123\u0128\u0003.\u0017\u0000\u0124\u0125\u0005G\u0000\u0000\u0125\u0127"+
		"\u0003,\u0016\u0000\u0126\u0124\u0001\u0000\u0000\u0000\u0127\u012a\u0001"+
		"\u0000\u0000\u0000\u0128\u0126\u0001\u0000\u0000\u0000\u0128\u0129\u0001"+
		"\u0000\u0000\u0000\u0129-\u0001\u0000\u0000\u0000\u012a\u0128\u0001\u0000"+
		"\u0000\u0000\u012b\u012c\u00030\u0018\u0000\u012c\u012d\u0005\u0007\u0000"+
		"\u0000\u012d\u012f\u00032\u0019\u0000\u012e\u0130\u00034\u001a\u0000\u012f"+
		"\u012e\u0001\u0000\u0000\u0000\u012f\u0130\u0001\u0000\u0000\u0000\u0130"+
		"/\u0001\u0000\u0000\u0000\u0131\u0132\u0005F\u0000\u0000\u01321\u0001"+
		"\u0000\u0000\u0000\u0133\u0134\u0005F\u0000\u0000\u01343\u0001\u0000\u0000"+
		"\u0000\u0135\u0138\u0005\u0012\u0000\u0000\u0136\u0139\u00036\u001b\u0000"+
		"\u0137\u0139\u00038\u001c\u0000\u0138\u0136\u0001\u0000\u0000\u0000\u0138"+
		"\u0137\u0001\u0000\u0000\u0000\u01395\u0001\u0000\u0000\u0000\u013a\u013b"+
		"\u0005\u0013\u0000\u0000\u013b7\u0001\u0000\u0000\u0000\u013c\u013d\u0005"+
		"\u0014\u0000\u0000\u013d9\u0001\u0000\u0000\u0000\u013e\u013f\u0005\u0015"+
		"\u0000\u0000\u013f\u0141\u0005\u0016\u0000\u0000\u0140\u0142\u0003<\u001e"+
		"\u0000\u0141\u0140\u0001\u0000\u0000\u0000\u0141\u0142\u0001\u0000\u0000"+
		"\u0000\u0142;\u0001\u0000\u0000\u0000\u0143\u0144\u0005\n\u0000\u0000"+
		"\u0144\u0149\u0003>\u001f\u0000\u0145\u0146\u0005G\u0000\u0000\u0146\u0148"+
		"\u0003>\u001f\u0000\u0147\u0145\u0001\u0000\u0000\u0000\u0148\u014b\u0001"+
		"\u0000\u0000\u0000\u0149\u0147\u0001\u0000\u0000\u0000\u0149\u014a\u0001"+
		"\u0000\u0000\u0000\u014a\u014c\u0001\u0000\u0000\u0000\u014b\u0149\u0001"+
		"\u0000\u0000\u0000\u014c\u014d\u0005\u000b\u0000\u0000\u014d=\u0001\u0000"+
		"\u0000\u0000\u014e\u014f\u0003@ \u0000\u014f\u0150\u0005\u0017\u0000\u0000"+
		"\u0150\u0151\u0003 \u0010\u0000\u0151?\u0001\u0000\u0000\u0000\u0152\u0153"+
		"\u0007\u0001\u0000\u0000\u0153A\u0001\u0000\u0000\u0000\u0154\u0155\u0005"+
		"\u001c\u0000\u0000\u0155\u0157\u0005\u001d\u0000\u0000\u0156\u0158\u0003"+
		"D\"\u0000\u0157\u0156\u0001\u0000\u0000\u0000\u0157\u0158\u0001\u0000"+
		"\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000\u0159\u015b\u0005\u0005"+
		"\u0000\u0000\u015a\u015c\u0003F#\u0000\u015b\u015a\u0001\u0000\u0000\u0000"+
		"\u015b\u015c\u0001\u0000\u0000\u0000\u015c\u015e\u0001\u0000\u0000\u0000"+
		"\u015d\u015f\u0003H$\u0000\u015e\u015d\u0001\u0000\u0000\u0000\u015e\u015f"+
		"\u0001\u0000\u0000\u0000\u015fC\u0001\u0000\u0000\u0000\u0160\u0161\u0005"+
		"\r\u0000\u0000\u0161\u0162\u0005\u001e\u0000\u0000\u0162E\u0001\u0000"+
		"\u0000\u0000\u0163\u0164\u0005F\u0000\u0000\u0164G\u0001\u0000\u0000\u0000"+
		"\u0165\u0166\u0005\u001f\u0000\u0000\u0166\u0167\u0005 \u0000\u0000\u0167"+
		"\u0168\u0005F\u0000\u0000\u0168I\u0001\u0000\u0000\u0000\u0169\u016d\u0003"+
		"L&\u0000\u016a\u016c\u0003L&\u0000\u016b\u016a\u0001\u0000\u0000\u0000"+
		"\u016c\u016f\u0001\u0000\u0000\u0000\u016d\u016b\u0001\u0000\u0000\u0000"+
		"\u016d\u016e\u0001\u0000\u0000\u0000\u016eK\u0001\u0000\u0000\u0000\u016f"+
		"\u016d\u0001\u0000\u0000\u0000\u0170\u0174\u0003N\'\u0000\u0171\u0174"+
		"\u0003P(\u0000\u0172\u0174\u0003R)\u0000\u0173\u0170\u0001\u0000\u0000"+
		"\u0000\u0173\u0171\u0001\u0000\u0000\u0000\u0173\u0172\u0001\u0000\u0000"+
		"\u0000\u0174M\u0001\u0000\u0000\u0000\u0175\u0176\u0005!\u0000\u0000\u0176"+
		"\u0177\u0003T*\u0000\u0177O\u0001\u0000\u0000\u0000\u0178\u0179\u0005"+
		"\"\u0000\u0000\u0179\u017a\u0003T*\u0000\u017aQ\u0001\u0000\u0000\u0000"+
		"\u017b\u017c\u0005#\u0000\u0000\u017c\u017d\u0003T*\u0000\u017dS\u0001"+
		"\u0000\u0000\u0000\u017e\u0180\u0003V+\u0000\u017f\u0181\u0003\\.\u0000"+
		"\u0180\u017f\u0001\u0000\u0000\u0000\u0180\u0181\u0001\u0000\u0000\u0000"+
		"\u0181\u0185\u0001\u0000\u0000\u0000\u0182\u0185\u0003\\.\u0000\u0183"+
		"\u0185\u0003X,\u0000\u0184\u017e\u0001\u0000\u0000\u0000\u0184\u0182\u0001"+
		"\u0000\u0000\u0000\u0184\u0183\u0001\u0000\u0000\u0000\u0185U\u0001\u0000"+
		"\u0000\u0000\u0186\u0187\u0005$\u0000\u0000\u0187\u0188\u0003Z-\u0000"+
		"\u0188W\u0001\u0000\u0000\u0000\u0189\u018a\u0005%\u0000\u0000\u018aY"+
		"\u0001\u0000\u0000\u0000\u018b\u018c\u0005F\u0000\u0000\u018c[\u0001\u0000"+
		"\u0000\u0000\u018d\u018e\u0005&\u0000\u0000\u018e\u018f\u0005\n\u0000"+
		"\u0000\u018f\u0190\u0003^/\u0000\u0190\u0191\u0005\u000b\u0000\u0000\u0191"+
		"]\u0001\u0000\u0000\u0000\u0192\u0197\u0003`0\u0000\u0193\u0194\u0005"+
		"G\u0000\u0000\u0194\u0196\u0003`0\u0000\u0195\u0193\u0001\u0000\u0000"+
		"\u0000\u0196\u0199\u0001\u0000\u0000\u0000\u0197\u0195\u0001\u0000\u0000"+
		"\u0000\u0197\u0198\u0001\u0000\u0000\u0000\u0198_\u0001\u0000\u0000\u0000"+
		"\u0199\u0197\u0001\u0000\u0000\u0000\u019a\u019b\u0003b1\u0000\u019b\u019c"+
		"\u0003Z-\u0000\u019ca\u0001\u0000\u0000\u0000\u019d\u019e\u0007\u0002"+
		"\u0000\u0000\u019ec\u0001\u0000\u0000\u0000\u019f\u01a1\u0003f3\u0000"+
		"\u01a0\u01a2\u0003h4\u0000\u01a1\u01a0\u0001\u0000\u0000\u0000\u01a1\u01a2"+
		"\u0001\u0000\u0000\u0000\u01a2\u01a3\u0001\u0000\u0000\u0000\u01a3\u01a4"+
		"\u0003j5\u0000\u01a4\u01a8\u0003l6\u0000\u01a5\u01a9\u0003z=\u0000\u01a6"+
		"\u01a9\u0003\u0084B\u0000\u01a7\u01a9\u0003J%\u0000\u01a8\u01a5\u0001"+
		"\u0000\u0000\u0000\u01a8\u01a6\u0001\u0000\u0000\u0000\u01a8\u01a7\u0001"+
		"\u0000\u0000\u0000\u01a8\u01a9\u0001\u0000\u0000\u0000\u01a9\u01aa\u0001"+
		"\u0000\u0000\u0000\u01aa\u01ab\u0005D\u0000\u0000\u01abe\u0001\u0000\u0000"+
		"\u0000\u01ac\u01ad\u0005+\u0000\u0000\u01ad\u01ae\u00030\u0018\u0000\u01ae"+
		"g\u0001\u0000\u0000\u0000\u01af\u01b0\u0005\b\u0000\u0000\u01b0\u01b1"+
		"\u0005,\u0000\u0000\u01b1\u01b2\u0005-\u0000\u0000\u01b2i\u0001\u0000"+
		"\u0000\u0000\u01b3\u01b4\u00036\u001b\u0000\u01b4\u01b5\u0003n7\u0000"+
		"\u01b5k\u0001\u0000\u0000\u0000\u01b6\u01b7\u00038\u001c\u0000\u01b7\u01b8"+
		"\u0003n7\u0000\u01b8m\u0001\u0000\u0000\u0000\u01b9\u01ba\u0003p8\u0000"+
		"\u01ba\u01bb\u0003v;\u0000\u01bbo\u0001\u0000\u0000\u0000\u01bc\u01be"+
		"\u0003r9\u0000\u01bd\u01bf\u0003t:\u0000\u01be\u01bd\u0001\u0000\u0000"+
		"\u0000\u01be\u01bf\u0001\u0000\u0000\u0000\u01bfq\u0001\u0000\u0000\u0000"+
		"\u01c0\u01c1\u0003\u0016\u000b\u0000\u01c1s\u0001\u0000\u0000\u0000\u01c2"+
		"\u01c3\u0003\u001e\u000f\u0000\u01c3u\u0001\u0000\u0000\u0000\u01c4\u01c5"+
		"\u0005.\u0000\u0000\u01c5\u01c6\u0003x<\u0000\u01c6w\u0001\u0000\u0000"+
		"\u0000\u01c7\u01c8\u0007\u0003\u0000\u0000\u01c8y\u0001\u0000\u0000\u0000"+
		"\u01c9\u01ca\u00053\u0000\u0000\u01ca\u01cb\u0003|>\u0000\u01cb\u01cc"+
		"\u0003~?\u0000\u01cc\u01ce\u0003\u0080@\u0000\u01cd\u01cf\u0003J%\u0000"+
		"\u01ce\u01cd\u0001\u0000\u0000\u0000\u01ce\u01cf\u0001\u0000\u0000\u0000"+
		"\u01cf{\u0001\u0000\u0000\u0000\u01d0\u01d1\u0005F\u0000\u0000\u01d1}"+
		"\u0001\u0000\u0000\u0000\u01d2\u01d3\u00036\u001b\u0000\u01d3\u01d4\u0003"+
		"\u0082A\u0000\u01d4\u007f\u0001\u0000\u0000\u0000\u01d5\u01d6\u00038\u001c"+
		"\u0000\u01d6\u01d7\u0003\u0082A\u0000\u01d7\u0081\u0001\u0000\u0000\u0000"+
		"\u01d8\u01d9\u0003\u001e\u000f\u0000\u01d9\u0083\u0001\u0000\u0000\u0000"+
		"\u01da\u01e0\u0003\u0086C\u0000\u01db\u01dd\u0003\u0088D\u0000\u01dc\u01de"+
		"\u0003J%\u0000\u01dd\u01dc\u0001\u0000\u0000\u0000\u01dd\u01de\u0001\u0000"+
		"\u0000\u0000\u01de\u01e0\u0001\u0000\u0000\u0000\u01df\u01da\u0001\u0000"+
		"\u0000\u0000\u01df\u01db\u0001\u0000\u0000\u0000\u01e0\u0085\u0001\u0000"+
		"\u0000\u0000\u01e1\u01e2\u00054\u0000\u0000\u01e2\u01e3\u00055\u0000\u0000"+
		"\u01e3\u0087\u0001\u0000\u0000\u0000\u01e4\u01e5\u00055\u0000\u0000\u01e5"+
		"\u01e8\u00056\u0000\u0000\u01e6\u01e9\u00036\u001b\u0000\u01e7\u01e9\u0003"+
		"8\u001c\u0000\u01e8\u01e6\u0001\u0000\u0000\u0000\u01e8\u01e7\u0001\u0000"+
		"\u0000\u0000\u01e9\u0089\u0001\u0000\u0000\u0000\u01ea\u01eb\u00057\u0000"+
		"\u0000\u01eb\u01ec\u0003\u008cF\u0000\u01ec\u008b\u0001\u0000\u0000\u0000"+
		"\u01ed\u01ee\u0005\u0003\u0000\u0000\u01ee\u01f0\u0003\u008eG\u0000\u01ef"+
		"\u01f1\u0003\u008eG\u0000\u01f0\u01ef\u0001\u0000\u0000\u0000\u01f0\u01f1"+
		"\u0001\u0000\u0000\u0000\u01f1\u01f2\u0001\u0000\u0000\u0000\u01f2\u01f3"+
		"\u0005\u0004\u0000\u0000\u01f3\u008d\u0001\u0000\u0000\u0000\u01f4\u01f5"+
		"\u00058\u0000\u0000\u01f5\u01f6\u00059\u0000\u0000\u01f6\u01f7\u0005D"+
		"\u0000\u0000\u01f7\u008f\u0001\u0000\u0000\u0000\u01f8\u01f9\u0005:\u0000"+
		"\u0000\u01f9\u01fa\u0003\u0092I\u0000\u01fa\u0091\u0001\u0000\u0000\u0000"+
		"\u01fb\u01ff\u0005\u0003\u0000\u0000\u01fc\u01fe\u0003\u0094J\u0000\u01fd"+
		"\u01fc\u0001\u0000\u0000\u0000\u01fe\u0201\u0001\u0000\u0000\u0000\u01ff"+
		"\u01fd\u0001\u0000\u0000\u0000\u01ff\u0200\u0001\u0000\u0000\u0000\u0200"+
		"\u0202\u0001\u0000\u0000\u0000\u0201\u01ff\u0001\u0000\u0000\u0000\u0202"+
		"\u0203\u0005\u0004\u0000\u0000\u0203\u0093\u0001\u0000\u0000\u0000\u0204"+
		"\u020a\u0003\u0096K\u0000\u0205\u020a\u0003\u0098L\u0000\u0206\u020a\u0003"+
		"\u009aM\u0000\u0207\u020a\u0003\u009cN\u0000\u0208\u020a\u0003\u00a4R"+
		"\u0000\u0209\u0204\u0001\u0000\u0000\u0000\u0209\u0205\u0001\u0000\u0000"+
		"\u0000\u0209\u0206\u0001\u0000\u0000\u0000\u0209\u0207\u0001\u0000\u0000"+
		"\u0000\u0209\u0208\u0001\u0000\u0000\u0000\u020a\u0095\u0001\u0000\u0000"+
		"\u0000\u020b\u020c\u0005;\u0000\u0000\u020c\u020d\u0005<\u0000\u0000\u020d"+
		"\u020e\u0005D\u0000\u0000\u020e\u0097\u0001\u0000\u0000\u0000\u020f\u0210"+
		"\u0005=\u0000\u0000\u0210\u0211\u0005>\u0000\u0000\u0211\u0212\u0005F"+
		"\u0000\u0000\u0212\u0213\u0005D\u0000\u0000\u0213\u0099\u0001\u0000\u0000"+
		"\u0000\u0214\u0215\u0005?\u0000\u0000\u0215\u0216\u0005>\u0000\u0000\u0216"+
		"\u0217\u0005F\u0000\u0000\u0217\u0218\u0005D\u0000\u0000\u0218\u009b\u0001"+
		"\u0000\u0000\u0000\u0219\u021b\u0005@\u0000\u0000\u021a\u021c\u0003\u009e"+
		"O\u0000\u021b\u021a\u0001\u0000\u0000\u0000\u021b\u021c\u0001\u0000\u0000"+
		"\u0000\u021c\u021e\u0001\u0000\u0000\u0000\u021d\u021f\u0003\u00a2Q\u0000"+
		"\u021e\u021d\u0001\u0000\u0000\u0000\u021e\u021f\u0001\u0000\u0000\u0000"+
		"\u021f\u0220\u0001\u0000\u0000\u0000\u0220\u0221\u0005D\u0000\u0000\u0221"+
		"\u009d\u0001\u0000\u0000\u0000\u0222\u0227\u0003\u00a0P\u0000\u0223\u0224"+
		"\u0005G\u0000\u0000\u0224\u0226\u0003\u00a0P\u0000\u0225\u0223\u0001\u0000"+
		"\u0000\u0000\u0226\u0229\u0001\u0000\u0000\u0000\u0227\u0225\u0001\u0000"+
		"\u0000\u0000\u0227\u0228\u0001\u0000\u0000\u0000\u0228\u009f\u0001\u0000"+
		"\u0000\u0000\u0229\u0227\u0001\u0000\u0000\u0000\u022a\u022b\u0005F\u0000"+
		"\u0000\u022b\u00a1\u0001\u0000\u0000\u0000\u022c\u022d\u0005<\u0000\u0000"+
		"\u022d\u00a3\u0001\u0000\u0000\u0000\u022e\u022f\u0005A\u0000\u0000\u022f"+
		"\u0234\u0003\u00a6S\u0000\u0230\u0231\u0005G\u0000\u0000\u0231\u0233\u0003"+
		"\u00a6S\u0000\u0232\u0230\u0001\u0000\u0000\u0000\u0233\u0236\u0001\u0000"+
		"\u0000\u0000\u0234\u0232\u0001\u0000\u0000\u0000\u0234\u0235\u0001\u0000"+
		"\u0000\u0000\u0235\u0237\u0001\u0000\u0000\u0000\u0236\u0234\u0001\u0000"+
		"\u0000\u0000\u0237\u0238\u0005D\u0000\u0000\u0238\u00a5\u0001\u0000\u0000"+
		"\u0000\u0239\u023c\u0003\u00a8T\u0000\u023a\u023c\u0003\u00aaU\u0000\u023b"+
		"\u0239\u0001\u0000\u0000\u0000\u023b\u023a\u0001\u0000\u0000\u0000\u023c"+
		"\u023d\u0001\u0000\u0000\u0000\u023d\u023e\u0005E\u0000\u0000\u023e\u023f"+
		"\u0005M\u0000\u0000\u023f\u00a7\u0001\u0000\u0000\u0000\u0240\u0241\u0005"+
		"B\u0000\u0000\u0241\u00a9\u0001\u0000\u0000\u0000\u0242\u0243\u0005C\u0000"+
		"\u0000\u0243\u00ab\u0001\u0000\u0000\u00000\u00ae\u00b1\u00b5\u00be\u00c4"+
		"\u00c8\u00cc\u00cf\u00d2\u00d5\u00d8\u00db\u00de\u00e1\u00e6\u00eb\u00fa"+
		"\u0108\u0112\u011c\u0128\u012f\u0138\u0141\u0149\u0157\u015b\u015e\u016d"+
		"\u0173\u0180\u0184\u0197\u01a1\u01a8\u01be\u01ce\u01dd\u01df\u01e8\u01f0"+
		"\u01ff\u0209\u021b\u021e\u0227\u0234\u023b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}