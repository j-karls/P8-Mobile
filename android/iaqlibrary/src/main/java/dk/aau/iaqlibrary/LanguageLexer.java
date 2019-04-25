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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, ALERTYPES=9, 
		NUM=10, INT=11, STRING=12, DATE=13, EQ=14, LT=15, GT=16, LTEQ=17, GTEQ=18, 
		DASH=19, COL=20, PT=21, AND=22, WS=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "ALERTYPES", 
			"NUM", "INT", "STRING", "DATE", "EQ", "LT", "GT", "LTEQ", "GTEQ", "DASH", 
			"COL", "PT", "AND", "WS"
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
			null, null, null, null, null, null, null, null, null, "ALERTYPES", "NUM", 
			"INT", "STRING", "DATE", "EQ", "LT", "GT", "LTEQ", "GTEQ", "DASH", "COL", 
			"PT", "AND", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u00be\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nr\n\n\3\13\6\13u\n\13\r"+
		"\13\16\13v\3\13\6\13z\n\13\r\13\16\13{\3\13\3\13\6\13\u0080\n\13\r\13"+
		"\16\13\u0081\5\13\u0084\n\13\3\f\3\f\3\r\6\r\u0089\n\r\r\r\16\r\u008a"+
		"\3\r\5\r\u008e\n\r\3\16\6\16\u0091\n\16\r\16\16\16\u0092\3\16\3\16\6\16"+
		"\u0097\n\16\r\16\16\16\u0098\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\5\16\u00a5\n\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\2\2\31\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\3\2\5\3\2\62;\6\2"+
		"\62;C\\aac|\5\2\13\f\17\17\"\"\2\u00c7\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2\5\65\3\2\2\2\79\3"+
		"\2\2\2\t>\3\2\2\2\13A\3\2\2\2\rG\3\2\2\2\17N\3\2\2\2\21U\3\2\2\2\23q\3"+
		"\2\2\2\25\u0083\3\2\2\2\27\u0085\3\2\2\2\31\u008d\3\2\2\2\33\u0090\3\2"+
		"\2\2\35\u00a6\3\2\2\2\37\u00a8\3\2\2\2!\u00aa\3\2\2\2#\u00ac\3\2\2\2%"+
		"\u00af\3\2\2\2\'\u00b2\3\2\2\2)\u00b4\3\2\2\2+\u00b6\3\2\2\2-\u00b8\3"+
		"\2\2\2/\u00ba\3\2\2\2\61\62\7I\2\2\62\63\7G\2\2\63\64\7V\2\2\64\4\3\2"+
		"\2\2\65\66\7U\2\2\66\67\7G\2\2\678\7V\2\28\6\3\2\2\29:\7v\2\2:;\7k\2\2"+
		";<\7o\2\2<=\7g\2\2=\b\3\2\2\2>?\7v\2\2?@\7q\2\2@\n\3\2\2\2AB\7x\2\2BC"+
		"\7c\2\2CD\7n\2\2DE\7w\2\2EF\7g\2\2F\f\3\2\2\2GH\7c\2\2HI\7n\2\2IJ\7g\2"+
		"\2JK\7t\2\2KL\7v\2\2LM\7u\2\2M\16\3\2\2\2NO\7u\2\2OP\7v\2\2PQ\7c\2\2Q"+
		"R\7v\2\2RS\7w\2\2ST\7u\2\2T\20\3\2\2\2UV\7i\2\2VW\7w\2\2WX\7k\2\2XY\7"+
		"f\2\2YZ\7g\2\2Z[\7n\2\2[\\\7k\2\2\\]\7p\2\2]^\7g\2\2^\22\3\2\2\2_`\7k"+
		"\2\2`a\7o\2\2ab\7o\2\2bc\7g\2\2cd\7f\2\2de\7k\2\2ef\7c\2\2fg\7v\2\2gr"+
		"\7g\2\2hi\7r\2\2ij\7t\2\2jk\7g\2\2kl\7f\2\2lm\7k\2\2mn\7e\2\2no\7v\2\2"+
		"op\7g\2\2pr\7f\2\2q_\3\2\2\2qh\3\2\2\2r\24\3\2\2\2su\5\27\f\2ts\3\2\2"+
		"\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\u0084\3\2\2\2xz\5\27\f\2yx\3\2\2\2z"+
		"{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|}\3\2\2\2}\177\5+\26\2~\u0080\5\27\f\2\177"+
		"~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\u0084\3\2\2\2\u0083t\3\2\2\2\u0083y\3\2\2\2\u0084\26\3\2\2\2\u0085\u0086"+
		"\t\2\2\2\u0086\30\3\2\2\2\u0087\u0089\t\3\2\2\u0088\u0087\3\2\2\2\u0089"+
		"\u008a\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008e\3\2"+
		"\2\2\u008c\u008e\7,\2\2\u008d\u0088\3\2\2\2\u008d\u008c\3\2\2\2\u008e"+
		"\32\3\2\2\2\u008f\u0091\5\27\f\2\u0090\u008f\3\2\2\2\u0091\u0092\3\2\2"+
		"\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0096"+
		"\5\'\24\2\u0095\u0097\5\27\f\2\u0096\u0095\3\2\2\2\u0097\u0098\3\2\2\2"+
		"\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b"+
		"\5\'\24\2\u009b\u009c\5\27\f\2\u009c\u009d\5\27\f\2\u009d\u009e\5\27\f"+
		"\2\u009e\u00a4\5\27\f\2\u009f\u00a0\5\'\24\2\u00a0\u00a1\5\27\f\2\u00a1"+
		"\u00a2\5+\26\2\u00a2\u00a3\5\27\f\2\u00a3\u00a5\3\2\2\2\u00a4\u009f\3"+
		"\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\34\3\2\2\2\u00a6\u00a7\7?\2\2\u00a7\36"+
		"\3\2\2\2\u00a8\u00a9\7>\2\2\u00a9 \3\2\2\2\u00aa\u00ab\7@\2\2\u00ab\""+
		"\3\2\2\2\u00ac\u00ad\7>\2\2\u00ad\u00ae\7?\2\2\u00ae$\3\2\2\2\u00af\u00b0"+
		"\7@\2\2\u00b0\u00b1\7?\2\2\u00b1&\3\2\2\2\u00b2\u00b3\7/\2\2\u00b3(\3"+
		"\2\2\2\u00b4\u00b5\7<\2\2\u00b5*\3\2\2\2\u00b6\u00b7\7\60\2\2\u00b7,\3"+
		"\2\2\2\u00b8\u00b9\7(\2\2\u00b9.\3\2\2\2\u00ba\u00bb\t\4\2\2\u00bb\u00bc"+
		"\3\2\2\2\u00bc\u00bd\b\30\2\2\u00bd\60\3\2\2\2\r\2qv{\u0081\u0083\u008a"+
		"\u008d\u0092\u0098\u00a4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}