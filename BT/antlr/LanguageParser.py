# Generated from Language.g4 by ANTLR 4.7.2
# encoding: utf-8
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys


def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36")
        buf.write("^\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b")
        buf.write("\t\b\4\t\t\t\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\33\n")
        buf.write("\3\f\3\16\3\36\13\3\3\3\3\3\3\3\3\3\3\3\7\3%\n\3\f\3\16")
        buf.write("\3(\13\3\3\3\3\3\5\3,\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4")
        buf.write("\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4<\n\4\3\5\3\5\3\5\3\5")
        buf.write("\3\5\3\5\3\5\3\5\5\5F\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t")
        buf.write("\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5")
        buf.write("\t\\\n\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\3\3\2\25\31\2^")
        buf.write("\2\22\3\2\2\2\4+\3\2\2\2\6;\3\2\2\2\bE\3\2\2\2\nG\3\2")
        buf.write("\2\2\fI\3\2\2\2\16K\3\2\2\2\20[\3\2\2\2\22\23\5\4\3\2")
        buf.write("\23\3\3\2\2\2\24\25\7\3\2\2\25\26\7\17\2\2\26\34\5\6\4")
        buf.write("\2\27\30\5\16\b\2\30\31\5\6\4\2\31\33\3\2\2\2\32\27\3")
        buf.write("\2\2\2\33\36\3\2\2\2\34\32\3\2\2\2\34\35\3\2\2\2\35,\3")
        buf.write("\2\2\2\36\34\3\2\2\2\37 \7\4\2\2 &\5\b\5\2!\"\5\16\b\2")
        buf.write("\"#\5\b\5\2#%\3\2\2\2$!\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'")
        buf.write("\3\2\2\2\',\3\2\2\2(&\3\2\2\2)*\7\3\2\2*,\7\5\2\2+\24")
        buf.write("\3\2\2\2+\37\3\2\2\2+)\3\2\2\2,\5\3\2\2\2-.\7\6\2\2./")
        buf.write("\5\n\6\2/\60\5\20\t\2\60<\3\2\2\2\61\62\7\6\2\2\62\63")
        buf.write("\5\20\t\2\63\64\7\7\2\2\64\65\5\20\t\2\65<\3\2\2\2\66")
        buf.write("\67\7\b\2\2\678\5\n\6\289\7\r\2\29<\3\2\2\2:<\7\t\2\2")
        buf.write(";-\3\2\2\2;\61\3\2\2\2;\66\3\2\2\2;:\3\2\2\2<\7\3\2\2")
        buf.write("\2=>\7\n\2\2>?\5\f\7\2?@\7\17\2\2@F\3\2\2\2AB\7\13\2\2")
        buf.write("BC\5\f\7\2CD\7\f\2\2DF\3\2\2\2E=\3\2\2\2EA\3\2\2\2F\t")
        buf.write("\3\2\2\2GH\t\2\2\2H\13\3\2\2\2IJ\7\25\2\2J\r\3\2\2\2K")
        buf.write("L\7\35\2\2L\17\3\2\2\2MN\7\20\2\2NO\7\32\2\2OP\7\21\2")
        buf.write("\2PQ\7\32\2\2Q\\\7\22\2\2RS\7\20\2\2ST\7\32\2\2TU\7\21")
        buf.write("\2\2UV\7\32\2\2VW\7\22\2\2WX\7\33\2\2XY\7\23\2\2YZ\7\34")
        buf.write("\2\2Z\\\7\24\2\2[M\3\2\2\2[R\3\2\2\2\\\21\3\2\2\2\b\34")
        buf.write("&+;E[")
        return buf.getvalue()


class LanguageParser ( Parser ):

    grammarFileName = "Language.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "'GET'", "'SET'", "'config'", "'time'", 
                     "'to'", "'value'", "'status'", "'guideline'", "'alerts'", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "'='", "'<'", "'>'", "'<='", "'>='", "'-'", 
                     "':'", "'.'", "'&'" ]

    symbolicNames = [ "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "ALERTTYPE", "NUM", "INT", 
                      "STRING", "DAY", "MONTH", "YEAR", "HOUR", "MINUTE", 
                      "EQ", "LT", "GT", "LTEQ", "GTEQ", "DASH", "COL", "PT", 
                      "AND", "WS" ]

    RULE_s = 0
    RULE_cmd = 1
    RULE_getexpr = 2
    RULE_setexpr = 3
    RULE_compare = 4
    RULE_assign = 5
    RULE_andexpr = 6
    RULE_time = 7

    ruleNames =  [ "s", "cmd", "getexpr", "setexpr", "compare", "assign", 
                   "andexpr", "time" ]

    EOF = Token.EOF
    T__0=1
    T__1=2
    T__2=3
    T__3=4
    T__4=5
    T__5=6
    T__6=7
    T__7=8
    T__8=9
    ALERTTYPE=10
    NUM=11
    INT=12
    STRING=13
    DAY=14
    MONTH=15
    YEAR=16
    HOUR=17
    MINUTE=18
    EQ=19
    LT=20
    GT=21
    LTEQ=22
    GTEQ=23
    DASH=24
    COL=25
    PT=26
    AND=27
    WS=28

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.7.2")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None




    class SContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def cmd(self):
            return self.getTypedRuleContext(LanguageParser.CmdContext,0)


        def getRuleIndex(self):
            return LanguageParser.RULE_s

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterS" ):
                listener.enterS(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitS" ):
                listener.exitS(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitS" ):
                return visitor.visitS(self)
            else:
                return visitor.visitChildren(self)




    def s(self):

        localctx = LanguageParser.SContext(self, self._ctx, self.state)
        self.enterRule(localctx, 0, self.RULE_s)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 16
            self.cmd()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class CmdContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return LanguageParser.RULE_cmd

     
        def copyFrom(self, ctx:ParserRuleContext):
            super().copyFrom(ctx)



    class GetCommandContext(CmdContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a LanguageParser.CmdContext
            super().__init__(parser)
            self.copyFrom(ctx)

        def STRING(self):
            return self.getToken(LanguageParser.STRING, 0)
        def getexpr(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(LanguageParser.GetexprContext)
            else:
                return self.getTypedRuleContext(LanguageParser.GetexprContext,i)

        def andexpr(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(LanguageParser.AndexprContext)
            else:
                return self.getTypedRuleContext(LanguageParser.AndexprContext,i)


        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterGetCommand" ):
                listener.enterGetCommand(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitGetCommand" ):
                listener.exitGetCommand(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitGetCommand" ):
                return visitor.visitGetCommand(self)
            else:
                return visitor.visitChildren(self)


    class SetCommandContext(CmdContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a LanguageParser.CmdContext
            super().__init__(parser)
            self.copyFrom(ctx)

        def setexpr(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(LanguageParser.SetexprContext)
            else:
                return self.getTypedRuleContext(LanguageParser.SetexprContext,i)

        def andexpr(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(LanguageParser.AndexprContext)
            else:
                return self.getTypedRuleContext(LanguageParser.AndexprContext,i)


        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterSetCommand" ):
                listener.enterSetCommand(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitSetCommand" ):
                listener.exitSetCommand(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitSetCommand" ):
                return visitor.visitSetCommand(self)
            else:
                return visitor.visitChildren(self)


    class GetCfgCommandContext(CmdContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a LanguageParser.CmdContext
            super().__init__(parser)
            self.copyFrom(ctx)


        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterGetCfgCommand" ):
                listener.enterGetCfgCommand(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitGetCfgCommand" ):
                listener.exitGetCfgCommand(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitGetCfgCommand" ):
                return visitor.visitGetCfgCommand(self)
            else:
                return visitor.visitChildren(self)



    def cmd(self):

        localctx = LanguageParser.CmdContext(self, self._ctx, self.state)
        self.enterRule(localctx, 2, self.RULE_cmd)
        self._la = 0 # Token type
        try:
            self.state = 41
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,2,self._ctx)
            if la_ == 1:
                localctx = LanguageParser.GetCommandContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 18
                self.match(LanguageParser.T__0)
                self.state = 19
                self.match(LanguageParser.STRING)
                self.state = 20
                self.getexpr()
                self.state = 26
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                while _la==LanguageParser.AND:
                    self.state = 21
                    self.andexpr()
                    self.state = 22
                    self.getexpr()
                    self.state = 28
                    self._errHandler.sync(self)
                    _la = self._input.LA(1)

                pass

            elif la_ == 2:
                localctx = LanguageParser.SetCommandContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 29
                self.match(LanguageParser.T__1)
                self.state = 30
                self.setexpr()
                self.state = 36
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                while _la==LanguageParser.AND:
                    self.state = 31
                    self.andexpr()
                    self.state = 32
                    self.setexpr()
                    self.state = 38
                    self._errHandler.sync(self)
                    _la = self._input.LA(1)

                pass

            elif la_ == 3:
                localctx = LanguageParser.GetCfgCommandContext(self, localctx)
                self.enterOuterAlt(localctx, 3)
                self.state = 39
                self.match(LanguageParser.T__0)
                self.state = 40
                self.match(LanguageParser.T__2)
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class GetexprContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return LanguageParser.RULE_getexpr

     
        def copyFrom(self, ctx:ParserRuleContext):
            super().copyFrom(ctx)



    class GetValueExprContext(GetexprContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a LanguageParser.GetexprContext
            super().__init__(parser)
            self.op = None # CompareContext
            self.copyFrom(ctx)

        def NUM(self):
            return self.getToken(LanguageParser.NUM, 0)
        def compare(self):
            return self.getTypedRuleContext(LanguageParser.CompareContext,0)


        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterGetValueExpr" ):
                listener.enterGetValueExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitGetValueExpr" ):
                listener.exitGetValueExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitGetValueExpr" ):
                return visitor.visitGetValueExpr(self)
            else:
                return visitor.visitChildren(self)


    class GetTimeExprContext(GetexprContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a LanguageParser.GetexprContext
            super().__init__(parser)
            self.op = None # CompareContext
            self.copyFrom(ctx)

        def time(self):
            return self.getTypedRuleContext(LanguageParser.TimeContext,0)

        def compare(self):
            return self.getTypedRuleContext(LanguageParser.CompareContext,0)


        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterGetTimeExpr" ):
                listener.enterGetTimeExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitGetTimeExpr" ):
                listener.exitGetTimeExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitGetTimeExpr" ):
                return visitor.visitGetTimeExpr(self)
            else:
                return visitor.visitChildren(self)


    class GetTimeIntervalExprContext(GetexprContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a LanguageParser.GetexprContext
            super().__init__(parser)
            self.copyFrom(ctx)

        def time(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(LanguageParser.TimeContext)
            else:
                return self.getTypedRuleContext(LanguageParser.TimeContext,i)


        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterGetTimeIntervalExpr" ):
                listener.enterGetTimeIntervalExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitGetTimeIntervalExpr" ):
                listener.exitGetTimeIntervalExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitGetTimeIntervalExpr" ):
                return visitor.visitGetTimeIntervalExpr(self)
            else:
                return visitor.visitChildren(self)


    class GetStatusExprContext(GetexprContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a LanguageParser.GetexprContext
            super().__init__(parser)
            self.copyFrom(ctx)


        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterGetStatusExpr" ):
                listener.enterGetStatusExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitGetStatusExpr" ):
                listener.exitGetStatusExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitGetStatusExpr" ):
                return visitor.visitGetStatusExpr(self)
            else:
                return visitor.visitChildren(self)



    def getexpr(self):

        localctx = LanguageParser.GetexprContext(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_getexpr)
        try:
            self.state = 57
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,3,self._ctx)
            if la_ == 1:
                localctx = LanguageParser.GetTimeExprContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 43
                self.match(LanguageParser.T__3)
                self.state = 44
                localctx.op = self.compare()
                self.state = 45
                self.time()
                pass

            elif la_ == 2:
                localctx = LanguageParser.GetTimeIntervalExprContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 47
                self.match(LanguageParser.T__3)
                self.state = 48
                self.time()
                self.state = 49
                self.match(LanguageParser.T__4)
                self.state = 50
                self.time()
                pass

            elif la_ == 3:
                localctx = LanguageParser.GetValueExprContext(self, localctx)
                self.enterOuterAlt(localctx, 3)
                self.state = 52
                self.match(LanguageParser.T__5)
                self.state = 53
                localctx.op = self.compare()
                self.state = 54
                self.match(LanguageParser.NUM)
                pass

            elif la_ == 4:
                localctx = LanguageParser.GetStatusExprContext(self, localctx)
                self.enterOuterAlt(localctx, 4)
                self.state = 56
                self.match(LanguageParser.T__6)
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class SetexprContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return LanguageParser.RULE_setexpr

     
        def copyFrom(self, ctx:ParserRuleContext):
            super().copyFrom(ctx)



    class SetAlertExprContext(SetexprContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a LanguageParser.SetexprContext
            super().__init__(parser)
            self.op = None # AssignContext
            self.copyFrom(ctx)

        def ALERTTYPE(self):
            return self.getToken(LanguageParser.ALERTTYPE, 0)
        def assign(self):
            return self.getTypedRuleContext(LanguageParser.AssignContext,0)


        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterSetAlertExpr" ):
                listener.enterSetAlertExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitSetAlertExpr" ):
                listener.exitSetAlertExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitSetAlertExpr" ):
                return visitor.visitSetAlertExpr(self)
            else:
                return visitor.visitChildren(self)


    class SetGuidelineExprContext(SetexprContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a LanguageParser.SetexprContext
            super().__init__(parser)
            self.op = None # AssignContext
            self.copyFrom(ctx)

        def STRING(self):
            return self.getToken(LanguageParser.STRING, 0)
        def assign(self):
            return self.getTypedRuleContext(LanguageParser.AssignContext,0)


        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterSetGuidelineExpr" ):
                listener.enterSetGuidelineExpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitSetGuidelineExpr" ):
                listener.exitSetGuidelineExpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitSetGuidelineExpr" ):
                return visitor.visitSetGuidelineExpr(self)
            else:
                return visitor.visitChildren(self)



    def setexpr(self):

        localctx = LanguageParser.SetexprContext(self, self._ctx, self.state)
        self.enterRule(localctx, 6, self.RULE_setexpr)
        try:
            self.state = 67
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [LanguageParser.T__7]:
                localctx = LanguageParser.SetGuidelineExprContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 59
                self.match(LanguageParser.T__7)
                self.state = 60
                localctx.op = self.assign()
                self.state = 61
                self.match(LanguageParser.STRING)
                pass
            elif token in [LanguageParser.T__8]:
                localctx = LanguageParser.SetAlertExprContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 63
                self.match(LanguageParser.T__8)
                self.state = 64
                localctx.op = self.assign()
                self.state = 65
                self.match(LanguageParser.ALERTTYPE)
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class CompareContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def GT(self):
            return self.getToken(LanguageParser.GT, 0)

        def LT(self):
            return self.getToken(LanguageParser.LT, 0)

        def EQ(self):
            return self.getToken(LanguageParser.EQ, 0)

        def LTEQ(self):
            return self.getToken(LanguageParser.LTEQ, 0)

        def GTEQ(self):
            return self.getToken(LanguageParser.GTEQ, 0)

        def getRuleIndex(self):
            return LanguageParser.RULE_compare

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterCompare" ):
                listener.enterCompare(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitCompare" ):
                listener.exitCompare(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitCompare" ):
                return visitor.visitCompare(self)
            else:
                return visitor.visitChildren(self)




    def compare(self):

        localctx = LanguageParser.CompareContext(self, self._ctx, self.state)
        self.enterRule(localctx, 8, self.RULE_compare)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 69
            _la = self._input.LA(1)
            if not((((_la) & ~0x3f) == 0 and ((1 << _la) & ((1 << LanguageParser.EQ) | (1 << LanguageParser.LT) | (1 << LanguageParser.GT) | (1 << LanguageParser.LTEQ) | (1 << LanguageParser.GTEQ))) != 0)):
                self._errHandler.recoverInline(self)
            else:
                self._errHandler.reportMatch(self)
                self.consume()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class AssignContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def EQ(self):
            return self.getToken(LanguageParser.EQ, 0)

        def getRuleIndex(self):
            return LanguageParser.RULE_assign

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterAssign" ):
                listener.enterAssign(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitAssign" ):
                listener.exitAssign(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitAssign" ):
                return visitor.visitAssign(self)
            else:
                return visitor.visitChildren(self)




    def assign(self):

        localctx = LanguageParser.AssignContext(self, self._ctx, self.state)
        self.enterRule(localctx, 10, self.RULE_assign)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 71
            self.match(LanguageParser.EQ)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class AndexprContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def AND(self):
            return self.getToken(LanguageParser.AND, 0)

        def getRuleIndex(self):
            return LanguageParser.RULE_andexpr

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterAndexpr" ):
                listener.enterAndexpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitAndexpr" ):
                listener.exitAndexpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitAndexpr" ):
                return visitor.visitAndexpr(self)
            else:
                return visitor.visitChildren(self)




    def andexpr(self):

        localctx = LanguageParser.AndexprContext(self, self._ctx, self.state)
        self.enterRule(localctx, 12, self.RULE_andexpr)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 73
            self.match(LanguageParser.AND)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class TimeContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return LanguageParser.RULE_time

     
        def copyFrom(self, ctx:ParserRuleContext):
            super().copyFrom(ctx)



    class DateexprContext(TimeContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a LanguageParser.TimeContext
            super().__init__(parser)
            self.copyFrom(ctx)

        def DAY(self):
            return self.getToken(LanguageParser.DAY, 0)
        def DASH(self, i:int=None):
            if i is None:
                return self.getTokens(LanguageParser.DASH)
            else:
                return self.getToken(LanguageParser.DASH, i)
        def MONTH(self):
            return self.getToken(LanguageParser.MONTH, 0)
        def YEAR(self):
            return self.getToken(LanguageParser.YEAR, 0)

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterDateexpr" ):
                listener.enterDateexpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitDateexpr" ):
                listener.exitDateexpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitDateexpr" ):
                return visitor.visitDateexpr(self)
            else:
                return visitor.visitChildren(self)


    class DatetimeexprContext(TimeContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a LanguageParser.TimeContext
            super().__init__(parser)
            self.copyFrom(ctx)

        def DAY(self):
            return self.getToken(LanguageParser.DAY, 0)
        def DASH(self, i:int=None):
            if i is None:
                return self.getTokens(LanguageParser.DASH)
            else:
                return self.getToken(LanguageParser.DASH, i)
        def MONTH(self):
            return self.getToken(LanguageParser.MONTH, 0)
        def YEAR(self):
            return self.getToken(LanguageParser.YEAR, 0)
        def COL(self):
            return self.getToken(LanguageParser.COL, 0)
        def HOUR(self):
            return self.getToken(LanguageParser.HOUR, 0)
        def PT(self):
            return self.getToken(LanguageParser.PT, 0)
        def MINUTE(self):
            return self.getToken(LanguageParser.MINUTE, 0)

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterDatetimeexpr" ):
                listener.enterDatetimeexpr(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitDatetimeexpr" ):
                listener.exitDatetimeexpr(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitDatetimeexpr" ):
                return visitor.visitDatetimeexpr(self)
            else:
                return visitor.visitChildren(self)



    def time(self):

        localctx = LanguageParser.TimeContext(self, self._ctx, self.state)
        self.enterRule(localctx, 14, self.RULE_time)
        try:
            self.state = 89
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,5,self._ctx)
            if la_ == 1:
                localctx = LanguageParser.DateexprContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 75
                self.match(LanguageParser.DAY)
                self.state = 76
                self.match(LanguageParser.DASH)
                self.state = 77
                self.match(LanguageParser.MONTH)
                self.state = 78
                self.match(LanguageParser.DASH)
                self.state = 79
                self.match(LanguageParser.YEAR)
                pass

            elif la_ == 2:
                localctx = LanguageParser.DatetimeexprContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 80
                self.match(LanguageParser.DAY)
                self.state = 81
                self.match(LanguageParser.DASH)
                self.state = 82
                self.match(LanguageParser.MONTH)
                self.state = 83
                self.match(LanguageParser.DASH)
                self.state = 84
                self.match(LanguageParser.YEAR)
                self.state = 85
                self.match(LanguageParser.COL)
                self.state = 86
                self.match(LanguageParser.HOUR)
                self.state = 87
                self.match(LanguageParser.PT)
                self.state = 88
                self.match(LanguageParser.MINUTE)
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx





