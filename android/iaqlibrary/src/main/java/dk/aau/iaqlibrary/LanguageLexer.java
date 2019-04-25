// Generated from D:/GitHub/P8-Mobile/android/iaqlibrary/src/main/java/dk/aau/iaqlibrary\Language.g4 by ANTLR 4.7.2
package dk.aau.iaqlibrary;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LanguageLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, ALERTTYPES=9, 
		NUM=10, INT=11, STRING=12, DATE=13, EQ=14, LT=15, GT=16, LTEQ=17, GTEQ=18, 
		DASH=19, COL=20, PT=21, AND=22, TIME=23, WS=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "ALERTTYPES", 
			"NUM", "INT", "STRING", "DATE", "EQ", "LT", "GT", "LTEQ", "GTEQ", "DASH", 
			"COL", "PT", "AND", "TIME", "WS"
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


	public LanguageLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Language.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u00c9\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nt\n\n\3\13\6\13"+
		"w\n\13\r\13\16\13x\3\13\6\13|\n\13\r\13\16\13}\3\13\3\13\6\13\u0082\n"+
		"\13\r\13\16\13\u0083\5\13\u0086\n\13\3\f\3\f\3\r\6\r\u008b\n\r\r\r\16"+
		"\r\u008c\3\r\5\r\u0090\n\r\3\16\6\16\u0093\n\16\r\16\16\16\u0094\3\16"+
		"\3\16\6\16\u0099\n\16\r\16\16\16\u009a\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\5\16\u00a9\n\16\5\16\u00ab\n\16\5\16\u00ad"+
		"\n\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31"+
		"\2\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\3\2\5\3\2\62;\6\2"+
		"\62;C\\aac|\5\2\13\f\17\17\"\"\2\u00d4\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5\67"+
		"\3\2\2\2\7;\3\2\2\2\t@\3\2\2\2\13C\3\2\2\2\rI\3\2\2\2\17P\3\2\2\2\21W"+
		"\3\2\2\2\23s\3\2\2\2\25\u0085\3\2\2\2\27\u0087\3\2\2\2\31\u008f\3\2\2"+
		"\2\33\u0092\3\2\2\2\35\u00ae\3\2\2\2\37\u00b0\3\2\2\2!\u00b2\3\2\2\2#"+
		"\u00b4\3\2\2\2%\u00b7\3\2\2\2\'\u00ba\3\2\2\2)\u00bc\3\2\2\2+\u00be\3"+
		"\2\2\2-\u00c0\3\2\2\2/\u00c2\3\2\2\2\61\u00c5\3\2\2\2\63\64\7I\2\2\64"+
		"\65\7G\2\2\65\66\7V\2\2\66\4\3\2\2\2\678\7U\2\289\7G\2\29:\7V\2\2:\6\3"+
		"\2\2\2;<\7v\2\2<=\7k\2\2=>\7o\2\2>?\7g\2\2?\b\3\2\2\2@A\7v\2\2AB\7q\2"+
		"\2B\n\3\2\2\2CD\7x\2\2DE\7c\2\2EF\7n\2\2FG\7w\2\2GH\7g\2\2H\f\3\2\2\2"+
		"IJ\7c\2\2JK\7n\2\2KL\7g\2\2LM\7t\2\2MN\7v\2\2NO\7u\2\2O\16\3\2\2\2PQ\7"+
		"u\2\2QR\7v\2\2RS\7c\2\2ST\7v\2\2TU\7w\2\2UV\7u\2\2V\20\3\2\2\2WX\7i\2"+
		"\2XY\7w\2\2YZ\7k\2\2Z[\7f\2\2[\\\7g\2\2\\]\7n\2\2]^\7k\2\2^_\7p\2\2_`"+
		"\7g\2\2`\22\3\2\2\2ab\7k\2\2bc\7o\2\2cd\7o\2\2de\7g\2\2ef\7f\2\2fg\7k"+
		"\2\2gh\7c\2\2hi\7v\2\2it\7g\2\2jk\7r\2\2kl\7t\2\2lm\7g\2\2mn\7f\2\2no"+
		"\7k\2\2op\7e\2\2pq\7v\2\2qr\7g\2\2rt\7f\2\2sa\3\2\2\2sj\3\2\2\2t\24\3"+
		"\2\2\2uw\5\27\f\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\u0086\3\2\2"+
		"\2z|\5\27\f\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177"+
		"\u0081\5+\26\2\u0080\u0082\5\27\f\2\u0081\u0080\3\2\2\2\u0082\u0083\3"+
		"\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0086\3\2\2\2\u0085"+
		"v\3\2\2\2\u0085{\3\2\2\2\u0086\26\3\2\2\2\u0087\u0088\t\2\2\2\u0088\30"+
		"\3\2\2\2\u0089\u008b\t\3\2\2\u008a\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u0090\7,"+
		"\2\2\u008f\u008a\3\2\2\2\u008f\u008e\3\2\2\2\u0090\32\3\2\2\2\u0091\u0093"+
		"\5\27\f\2\u0092\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0092\3\2\2\2"+
		"\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\5\'\24\2\u0097\u0099"+
		"\5\27\f\2\u0098\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u0098\3\2\2\2"+
		"\u009a\u009b\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\5\'\24\2\u009d\u009e"+
		"\5\27\f\2\u009e\u009f\5\27\f\2\u009f\u00a0\5\27\f\2\u00a0\u00ac\5\27\f"+
		"\2\u00a1\u00a2\5)\25\2\u00a2\u00aa\5/\30\2\u00a3\u00a4\5+\26\2\u00a4\u00a8"+
		"\5/\30\2\u00a5\u00a6\5+\26\2\u00a6\u00a7\5/\30\2\u00a7\u00a9\3\2\2\2\u00a8"+
		"\u00a5\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\3\2\2\2\u00aa\u00a3\3\2"+
		"\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ad\3\2\2\2\u00ac\u00a1\3\2\2\2\u00ac"+
		"\u00ad\3\2\2\2\u00ad\34\3\2\2\2\u00ae\u00af\7?\2\2\u00af\36\3\2\2\2\u00b0"+
		"\u00b1\7>\2\2\u00b1 \3\2\2\2\u00b2\u00b3\7@\2\2\u00b3\"\3\2\2\2\u00b4"+
		"\u00b5\7>\2\2\u00b5\u00b6\7?\2\2\u00b6$\3\2\2\2\u00b7\u00b8\7@\2\2\u00b8"+
		"\u00b9\7?\2\2\u00b9&\3\2\2\2\u00ba\u00bb\7/\2\2\u00bb(\3\2\2\2\u00bc\u00bd"+
		"\7<\2\2\u00bd*\3\2\2\2\u00be\u00bf\7\60\2\2\u00bf,\3\2\2\2\u00c0\u00c1"+
		"\7(\2\2\u00c1.\3\2\2\2\u00c2\u00c3\5\27\f\2\u00c3\u00c4\5\27\f\2\u00c4"+
		"\60\3\2\2\2\u00c5\u00c6\t\4\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\b\31\2"+
		"\2\u00c8\62\3\2\2\2\17\2sx}\u0083\u0085\u008c\u008f\u0094\u009a\u00a8"+
		"\u00aa\u00ac\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}