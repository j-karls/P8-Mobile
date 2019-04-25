// Generated from D:/GitHub/P8-Mobile/android/iaqlibrary/src/main/java/dk/aau/iaqlibrary\Language.g4 by ANTLR 4.7.2
package dk.aau.iaqlibrary;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LanguageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, ALERTTYPES=9, 
		NUM=10, INT=11, STRING=12, DATE=13, EQ=14, LT=15, GT=16, LTEQ=17, GTEQ=18, 
		DASH=19, COL=20, PT=21, AND=22, TIME=23, WS=24;
	public static final int
		RULE_s = 0, RULE_cmd = 1, RULE_getexpr = 2, RULE_setexpr = 3, RULE_compare = 4, 
		RULE_assign = 5, RULE_andexpr = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"s", "cmd", "getexpr", "setexpr", "compare", "assign", "andexpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'GET'", "'SET'", "'time'", "'to'", "'value'", "'alerts'", "'status'", 
			"'guideline'", null, null, null, null, null, "'='", "'<'", "'>'", "'<='", 
			"'>='", "'-'", "':'", "'.'", "'&'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "ALERTTYPES", "NUM", 
			"INT", "STRING", "DATE", "EQ", "LT", "GT", "LTEQ", "GTEQ", "DASH", "COL", 
			"PT", "AND", "TIME", "WS"
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
	public String getGrammarFileName() { return "Language.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LanguageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public List<AndexprContext> andexpr() {
			return getRuleContexts(AndexprContext.class);
		}
		public AndexprContext andexpr(int i) {
			return getRuleContext(AndexprContext.class,i);
		}
		public SContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).enterS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).exitS(this);
		}
	}

	public final SContext s() throws RecognitionException {
		SContext _localctx = new SContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_s);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			cmd();
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(15);
				andexpr();
				setState(16);
				cmd();
				}
				}
				setState(22);
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

	public static class CmdContext extends ParserRuleContext {
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
	 
		public CmdContext() { }
		public void copyFrom(CmdContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class GetCommandContext extends CmdContext {
		public TerminalNode STRING() { return getToken(LanguageParser.STRING, 0); }
		public List<GetexprContext> getexpr() {
			return getRuleContexts(GetexprContext.class);
		}
		public GetexprContext getexpr(int i) {
			return getRuleContext(GetexprContext.class,i);
		}
		public List<AndexprContext> andexpr() {
			return getRuleContexts(AndexprContext.class);
		}
		public AndexprContext andexpr(int i) {
			return getRuleContext(AndexprContext.class,i);
		}
		public GetCommandContext(CmdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).enterGetCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).exitGetCommand(this);
		}
	}
	public static class SetCommandContext extends CmdContext {
		public List<SetexprContext> setexpr() {
			return getRuleContexts(SetexprContext.class);
		}
		public SetexprContext setexpr(int i) {
			return getRuleContext(SetexprContext.class,i);
		}
		public List<AndexprContext> andexpr() {
			return getRuleContexts(AndexprContext.class);
		}
		public AndexprContext andexpr(int i) {
			return getRuleContext(AndexprContext.class,i);
		}
		public SetCommandContext(CmdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).enterSetCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).exitSetCommand(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_cmd);
		try {
			int _alt;
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new GetCommandContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(23);
				match(T__0);
				setState(24);
				match(STRING);
				setState(25);
				getexpr();
				setState(31);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(26);
						andexpr();
						setState(27);
						getexpr();
						}
						} 
					}
					setState(33);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				}
				break;
			case T__1:
				_localctx = new SetCommandContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				match(T__1);
				setState(35);
				setexpr();
				setState(41);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(36);
						andexpr();
						setState(37);
						setexpr();
						}
						} 
					}
					setState(43);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				}
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

	public static class GetexprContext extends ParserRuleContext {
		public GetexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_getexpr; }
	 
		public GetexprContext() { }
		public void copyFrom(GetexprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class GetValueExprContext extends GetexprContext {
		public CompareContext op;
		public TerminalNode NUM() { return getToken(LanguageParser.NUM, 0); }
		public CompareContext compare() {
			return getRuleContext(CompareContext.class,0);
		}
		public GetValueExprContext(GetexprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).enterGetValueExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).exitGetValueExpr(this);
		}
	}
	public static class GetAlertExprContext extends GetexprContext {
		public AssignContext op;
		public TerminalNode ALERTTYPES() { return getToken(LanguageParser.ALERTTYPES, 0); }
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public GetAlertExprContext(GetexprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).enterGetAlertExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).exitGetAlertExpr(this);
		}
	}
	public static class GetTimeExprContext extends GetexprContext {
		public CompareContext op;
		public TerminalNode DATE() { return getToken(LanguageParser.DATE, 0); }
		public CompareContext compare() {
			return getRuleContext(CompareContext.class,0);
		}
		public GetTimeExprContext(GetexprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).enterGetTimeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).exitGetTimeExpr(this);
		}
	}
	public static class GetTimeIntervalExprContext extends GetexprContext {
		public List<TerminalNode> DATE() { return getTokens(LanguageParser.DATE); }
		public TerminalNode DATE(int i) {
			return getToken(LanguageParser.DATE, i);
		}
		public GetTimeIntervalExprContext(GetexprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).enterGetTimeIntervalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).exitGetTimeIntervalExpr(this);
		}
	}
	public static class GetStatusExprContext extends GetexprContext {
		public GetStatusExprContext(GetexprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).enterGetStatusExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).exitGetStatusExpr(this);
		}
	}

	public final GetexprContext getexpr() throws RecognitionException {
		GetexprContext _localctx = new GetexprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_getexpr);
		try {
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new GetTimeExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				match(T__2);
				setState(47);
				((GetTimeExprContext)_localctx).op = compare();
				setState(48);
				match(DATE);
				}
				break;
			case 2:
				_localctx = new GetTimeIntervalExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				match(T__2);
				setState(51);
				match(DATE);
				setState(52);
				match(T__3);
				setState(53);
				match(DATE);
				}
				break;
			case 3:
				_localctx = new GetValueExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(54);
				match(T__4);
				setState(55);
				((GetValueExprContext)_localctx).op = compare();
				setState(56);
				match(NUM);
				}
				break;
			case 4:
				_localctx = new GetAlertExprContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				match(T__5);
				setState(59);
				((GetAlertExprContext)_localctx).op = assign();
				setState(60);
				match(ALERTTYPES);
				}
				break;
			case 5:
				_localctx = new GetStatusExprContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(62);
				match(T__6);
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

	public static class SetexprContext extends ParserRuleContext {
		public SetexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setexpr; }
	 
		public SetexprContext() { }
		public void copyFrom(SetexprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SetGuidelineExprContext extends SetexprContext {
		public AssignContext op;
		public TerminalNode STRING() { return getToken(LanguageParser.STRING, 0); }
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public SetGuidelineExprContext(SetexprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).enterSetGuidelineExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).exitSetGuidelineExpr(this);
		}
	}

	public final SetexprContext setexpr() throws RecognitionException {
		SetexprContext _localctx = new SetexprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_setexpr);
		try {
			_localctx = new SetGuidelineExprContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(T__7);
			setState(66);
			((SetGuidelineExprContext)_localctx).op = assign();
			setState(67);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompareContext extends ParserRuleContext {
		public TerminalNode GT() { return getToken(LanguageParser.GT, 0); }
		public TerminalNode LT() { return getToken(LanguageParser.LT, 0); }
		public TerminalNode EQ() { return getToken(LanguageParser.EQ, 0); }
		public TerminalNode LTEQ() { return getToken(LanguageParser.LTEQ, 0); }
		public TerminalNode GTEQ() { return getToken(LanguageParser.GTEQ, 0); }
		public CompareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).enterCompare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).exitCompare(this);
		}
	}

	public final CompareContext compare() throws RecognitionException {
		CompareContext _localctx = new CompareContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_compare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << LT) | (1L << GT) | (1L << LTEQ) | (1L << GTEQ))) != 0)) ) {
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

	public static class AssignContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(LanguageParser.EQ, 0); }
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).exitAssign(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(EQ);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndexprContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(LanguageParser.AND, 0); }
		public AndexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).enterAndexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LanguageListener ) ((LanguageListener)listener).exitAndexpr(this);
		}
	}

	public final AndexprContext andexpr() throws RecognitionException {
		AndexprContext _localctx = new AndexprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_andexpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(AND);
			}
		}
		catch (RecognitionException re) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32N\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\7\2\25\n\2"+
		"\f\2\16\2\30\13\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3 \n\3\f\3\16\3#\13\3\3\3"+
		"\3\3\3\3\3\3\3\3\7\3*\n\3\f\3\16\3-\13\3\5\3/\n\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4B\n\4\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\2\2\t\2\4\6\b\n\f\16\2\3\3\2\20\24\2"+
		"N\2\20\3\2\2\2\4.\3\2\2\2\6A\3\2\2\2\bC\3\2\2\2\nG\3\2\2\2\fI\3\2\2\2"+
		"\16K\3\2\2\2\20\26\5\4\3\2\21\22\5\16\b\2\22\23\5\4\3\2\23\25\3\2\2\2"+
		"\24\21\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2\27\3\3\2\2\2"+
		"\30\26\3\2\2\2\31\32\7\3\2\2\32\33\7\16\2\2\33!\5\6\4\2\34\35\5\16\b\2"+
		"\35\36\5\6\4\2\36 \3\2\2\2\37\34\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2"+
		"\2\2\"/\3\2\2\2#!\3\2\2\2$%\7\4\2\2%+\5\b\5\2&\'\5\16\b\2\'(\5\b\5\2("+
		"*\3\2\2\2)&\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,/\3\2\2\2-+\3\2\2\2"+
		".\31\3\2\2\2.$\3\2\2\2/\5\3\2\2\2\60\61\7\5\2\2\61\62\5\n\6\2\62\63\7"+
		"\17\2\2\63B\3\2\2\2\64\65\7\5\2\2\65\66\7\17\2\2\66\67\7\6\2\2\67B\7\17"+
		"\2\289\7\7\2\29:\5\n\6\2:;\7\f\2\2;B\3\2\2\2<=\7\b\2\2=>\5\f\7\2>?\7\13"+
		"\2\2?B\3\2\2\2@B\7\t\2\2A\60\3\2\2\2A\64\3\2\2\2A8\3\2\2\2A<\3\2\2\2A"+
		"@\3\2\2\2B\7\3\2\2\2CD\7\n\2\2DE\5\f\7\2EF\7\16\2\2F\t\3\2\2\2GH\t\2\2"+
		"\2H\13\3\2\2\2IJ\7\20\2\2J\r\3\2\2\2KL\7\30\2\2L\17\3\2\2\2\7\26!+.A";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}