# Generated from Language.g4 by ANTLR 4.7.2
# encoding: utf-8
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys


def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32")
        buf.write("\67\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3")
        buf.write("\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\30\n\3\3\4\3\4")
        buf.write("\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\'\n\4")
        buf.write("\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\61\n\5\3\6\3\6\3")
        buf.write("\7\3\7\3\7\2\2\b\2\4\6\b\n\f\2\3\3\2\21\25\2\66\2\16\3")
        buf.write("\2\2\2\4\27\3\2\2\2\6&\3\2\2\2\b\60\3\2\2\2\n\62\3\2\2")
        buf.write("\2\f\64\3\2\2\2\16\17\5\4\3\2\17\3\3\2\2\2\20\21\7\3\2")
        buf.write("\2\21\22\7\17\2\2\22\30\5\6\4\2\23\24\7\4\2\2\24\30\5")
        buf.write("\b\5\2\25\26\7\3\2\2\26\30\7\5\2\2\27\20\3\2\2\2\27\23")
        buf.write("\3\2\2\2\27\25\3\2\2\2\30\5\3\2\2\2\31\32\7\6\2\2\32\33")
        buf.write("\5\n\6\2\33\34\7\20\2\2\34\'\3\2\2\2\35\36\7\6\2\2\36")
        buf.write("\37\7\20\2\2\37 \7\7\2\2 \'\7\20\2\2!\"\7\b\2\2\"#\5\n")
        buf.write("\6\2#$\7\r\2\2$\'\3\2\2\2%\'\7\t\2\2&\31\3\2\2\2&\35\3")
        buf.write("\2\2\2&!\3\2\2\2&%\3\2\2\2\'\7\3\2\2\2()\7\n\2\2)*\5\f")
        buf.write("\7\2*+\7\17\2\2+\61\3\2\2\2,-\7\13\2\2-.\5\f\7\2./\7\f")
        buf.write("\2\2/\61\3\2\2\2\60(\3\2\2\2\60,\3\2\2\2\61\t\3\2\2\2")
        buf.write("\62\63\t\2\2\2\63\13\3\2\2\2\64\65\7\21\2\2\65\r\3\2\2")
        buf.write("\2\5\27&\60")
        return buf.getvalue()


class LanguageParser ( Parser ):

    grammarFileName = "Language.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "'GET'", "'SET'", "'config'", "'time'", 
                     "'to'", "'value'", "'status'", "'guideline'", "'alerts'", 
                     "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "'='", "'<'", "'>'", "'<='", "'>='", "'-'", 
                     "':'", "'.'", "'&'" ]

    symbolicNames = [ "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "ALERTTYPE", "NUM", "INT", 
                      "STRING", "DATE", "EQ", "LT", "GT", "LTEQ", "GTEQ", 
                      "DASH", "COL", "PT", "AND", "WS" ]

    RULE_s = 0
    RULE_cmd = 1
    RULE_getexpr = 2
    RULE_setexpr = 3
    RULE_compare = 4
    RULE_assign = 5

    ruleNames =  [ "s", "cmd", "getexpr", "setexpr", "compare", "assign" ]

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
    DATE=14
    EQ=15
    LT=16
    GT=17
    LTEQ=18
    GTEQ=19
    DASH=20
    COL=21
    PT=22
    AND=23
    WS=24

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
            self.state = 12
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
        def getexpr(self):
            return self.getTypedRuleContext(LanguageParser.GetexprContext,0)


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

        def setexpr(self):
            return self.getTypedRuleContext(LanguageParser.SetexprContext,0)


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
        try:
            self.state = 21
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,0,self._ctx)
            if la_ == 1:
                localctx = LanguageParser.GetCommandContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 14
                self.match(LanguageParser.T__0)
                self.state = 15
                self.match(LanguageParser.STRING)
                self.state = 16
                self.getexpr()
                pass

            elif la_ == 2:
                localctx = LanguageParser.SetCommandContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 17
                self.match(LanguageParser.T__1)
                self.state = 18
                self.setexpr()
                pass

            elif la_ == 3:
                localctx = LanguageParser.GetCfgCommandContext(self, localctx)
                self.enterOuterAlt(localctx, 3)
                self.state = 19
                self.match(LanguageParser.T__0)
                self.state = 20
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

        def DATE(self):
            return self.getToken(LanguageParser.DATE, 0)
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

        def DATE(self, i:int=None):
            if i is None:
                return self.getTokens(LanguageParser.DATE)
            else:
                return self.getToken(LanguageParser.DATE, i)

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
            self.state = 36
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,1,self._ctx)
            if la_ == 1:
                localctx = LanguageParser.GetTimeExprContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 23
                self.match(LanguageParser.T__3)
                self.state = 24
                localctx.op = self.compare()
                self.state = 25
                self.match(LanguageParser.DATE)
                pass

            elif la_ == 2:
                localctx = LanguageParser.GetTimeIntervalExprContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 27
                self.match(LanguageParser.T__3)
                self.state = 28
                self.match(LanguageParser.DATE)
                self.state = 29
                self.match(LanguageParser.T__4)
                self.state = 30
                self.match(LanguageParser.DATE)
                pass

            elif la_ == 3:
                localctx = LanguageParser.GetValueExprContext(self, localctx)
                self.enterOuterAlt(localctx, 3)
                self.state = 31
                self.match(LanguageParser.T__5)
                self.state = 32
                localctx.op = self.compare()
                self.state = 33
                self.match(LanguageParser.NUM)
                pass

            elif la_ == 4:
                localctx = LanguageParser.GetStatusExprContext(self, localctx)
                self.enterOuterAlt(localctx, 4)
                self.state = 35
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
            self.state = 46
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [LanguageParser.T__7]:
                localctx = LanguageParser.SetGuidelineExprContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 38
                self.match(LanguageParser.T__7)
                self.state = 39
                localctx.op = self.assign()
                self.state = 40
                self.match(LanguageParser.STRING)
                pass
            elif token in [LanguageParser.T__8]:
                localctx = LanguageParser.SetAlertExprContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 42
                self.match(LanguageParser.T__8)
                self.state = 43
                localctx.op = self.assign()
                self.state = 44
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
            self.state = 48
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
            self.state = 50
            self.match(LanguageParser.EQ)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx





