# Generated from Language.g4 by ANTLR 4.7.2
# encoding: utf-8
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys


def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32")
        buf.write("R\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b")
        buf.write("\t\b\3\2\3\2\3\2\3\2\7\2\25\n\2\f\2\16\2\30\13\2\3\3\3")
        buf.write("\3\3\3\3\3\3\3\3\3\7\3 \n\3\f\3\16\3#\13\3\3\3\3\3\3\3")
        buf.write("\3\3\3\3\7\3*\n\3\f\3\16\3-\13\3\3\3\3\3\5\3\61\n\3\3")
        buf.write("\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4")
        buf.write("@\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5J\n\5\3\6\3\6")
        buf.write("\3\7\3\7\3\b\3\b\3\b\2\2\t\2\4\6\b\n\f\16\2\3\3\2\21\25")
        buf.write("\2S\2\20\3\2\2\2\4\60\3\2\2\2\6?\3\2\2\2\bI\3\2\2\2\n")
        buf.write("K\3\2\2\2\fM\3\2\2\2\16O\3\2\2\2\20\26\5\4\3\2\21\22\5")
        buf.write("\16\b\2\22\23\5\4\3\2\23\25\3\2\2\2\24\21\3\2\2\2\25\30")
        buf.write("\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2\27\3\3\2\2\2\30\26")
        buf.write("\3\2\2\2\31\32\7\3\2\2\32\33\7\17\2\2\33!\5\6\4\2\34\35")
        buf.write("\5\16\b\2\35\36\5\6\4\2\36 \3\2\2\2\37\34\3\2\2\2 #\3")
        buf.write("\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\61\3\2\2\2#!\3\2\2\2$")
        buf.write("%\7\4\2\2%+\5\b\5\2&\'\5\16\b\2\'(\5\b\5\2(*\3\2\2\2)")
        buf.write("&\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,\61\3\2\2\2-+")
        buf.write("\3\2\2\2./\7\3\2\2/\61\7\5\2\2\60\31\3\2\2\2\60$\3\2\2")
        buf.write("\2\60.\3\2\2\2\61\5\3\2\2\2\62\63\7\6\2\2\63\64\5\n\6")
        buf.write("\2\64\65\7\20\2\2\65@\3\2\2\2\66\67\7\6\2\2\678\7\20\2")
        buf.write("\289\7\7\2\29@\7\20\2\2:;\7\b\2\2;<\5\n\6\2<=\7\r\2\2")
        buf.write("=@\3\2\2\2>@\7\t\2\2?\62\3\2\2\2?\66\3\2\2\2?:\3\2\2\2")
        buf.write("?>\3\2\2\2@\7\3\2\2\2AB\7\n\2\2BC\5\f\7\2CD\7\17\2\2D")
        buf.write("J\3\2\2\2EF\7\13\2\2FG\5\f\7\2GH\7\f\2\2HJ\3\2\2\2IA\3")
        buf.write("\2\2\2IE\3\2\2\2J\t\3\2\2\2KL\t\2\2\2L\13\3\2\2\2MN\7")
        buf.write("\21\2\2N\r\3\2\2\2OP\7\31\2\2P\17\3\2\2\2\b\26!+\60?I")
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
    RULE_andexpr = 6

    ruleNames =  [ "s", "cmd", "getexpr", "setexpr", "compare", "assign", 
                   "andexpr" ]

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

        def cmd(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(LanguageParser.CmdContext)
            else:
                return self.getTypedRuleContext(LanguageParser.CmdContext,i)


        def andexpr(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(LanguageParser.AndexprContext)
            else:
                return self.getTypedRuleContext(LanguageParser.AndexprContext,i)


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
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 14
            self.cmd()
            self.state = 20
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==LanguageParser.AND:
                self.state = 15
                self.andexpr()
                self.state = 16
                self.cmd()
                self.state = 22
                self._errHandler.sync(self)
                _la = self._input.LA(1)

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
        try:
            self.state = 46
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,3,self._ctx)
            if la_ == 1:
                localctx = LanguageParser.GetCommandContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 23
                self.match(LanguageParser.T__0)
                self.state = 24
                self.match(LanguageParser.STRING)
                self.state = 25
                self.getexpr()
                self.state = 31
                self._errHandler.sync(self)
                _alt = self._interp.adaptivePredict(self._input,1,self._ctx)
                while _alt!=2 and _alt!=ATN.INVALID_ALT_NUMBER:
                    if _alt==1:
                        self.state = 26
                        self.andexpr()
                        self.state = 27
                        self.getexpr() 
                    self.state = 33
                    self._errHandler.sync(self)
                    _alt = self._interp.adaptivePredict(self._input,1,self._ctx)

                pass

            elif la_ == 2:
                localctx = LanguageParser.SetCommandContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 34
                self.match(LanguageParser.T__1)
                self.state = 35
                self.setexpr()
                self.state = 41
                self._errHandler.sync(self)
                _alt = self._interp.adaptivePredict(self._input,2,self._ctx)
                while _alt!=2 and _alt!=ATN.INVALID_ALT_NUMBER:
                    if _alt==1:
                        self.state = 36
                        self.andexpr()
                        self.state = 37
                        self.setexpr() 
                    self.state = 43
                    self._errHandler.sync(self)
                    _alt = self._interp.adaptivePredict(self._input,2,self._ctx)

                pass

            elif la_ == 3:
                localctx = LanguageParser.GetCfgCommandContext(self, localctx)
                self.enterOuterAlt(localctx, 3)
                self.state = 44
                self.match(LanguageParser.T__0)
                self.state = 45
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
            self.state = 61
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,4,self._ctx)
            if la_ == 1:
                localctx = LanguageParser.GetTimeExprContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 48
                self.match(LanguageParser.T__3)
                self.state = 49
                localctx.op = self.compare()
                self.state = 50
                self.match(LanguageParser.DATE)
                pass

            elif la_ == 2:
                localctx = LanguageParser.GetTimeIntervalExprContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 52
                self.match(LanguageParser.T__3)
                self.state = 53
                self.match(LanguageParser.DATE)
                self.state = 54
                self.match(LanguageParser.T__4)
                self.state = 55
                self.match(LanguageParser.DATE)
                pass

            elif la_ == 3:
                localctx = LanguageParser.GetValueExprContext(self, localctx)
                self.enterOuterAlt(localctx, 3)
                self.state = 56
                self.match(LanguageParser.T__5)
                self.state = 57
                localctx.op = self.compare()
                self.state = 58
                self.match(LanguageParser.NUM)
                pass

            elif la_ == 4:
                localctx = LanguageParser.GetStatusExprContext(self, localctx)
                self.enterOuterAlt(localctx, 4)
                self.state = 60
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
            self.state = 71
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [LanguageParser.T__7]:
                localctx = LanguageParser.SetGuidelineExprContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 63
                self.match(LanguageParser.T__7)
                self.state = 64
                localctx.op = self.assign()
                self.state = 65
                self.match(LanguageParser.STRING)
                pass
            elif token in [LanguageParser.T__8]:
                localctx = LanguageParser.SetAlertExprContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 67
                self.match(LanguageParser.T__8)
                self.state = 68
                localctx.op = self.assign()
                self.state = 69
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
            self.state = 73
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
            self.state = 75
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
            self.state = 77
            self.match(LanguageParser.AND)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx





