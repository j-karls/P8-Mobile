# Generated from Language.g4 by ANTLR 4.7.2
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .LanguageParser import LanguageParser
else:
    from LanguageParser import LanguageParser

# This class defines a complete generic visitor for a parse tree produced by LanguageParser.


class LanguageVisitor(ParseTreeVisitor):

    def __init__(self):
        self.__query = ''
        self.__bindings = [('CO', 'shortterm'),
                ('CO2', 'shortterm'),
                ('Temp', 'shortterm'),
                ('Hum', 'shortterm')]

    def append(self, str):
        self.__query += str

    # Visit a parse tree produced by LanguageParser#s.
    def visitS(self, ctx:LanguageParser.SContext):
        self.visitChildren(ctx)
        return self.__query


    # Visit a parse tree produced by LanguageParser#getCommand.
    def visitGetCommand(self, ctx:LanguageParser.GetCommandContext):
	#str(ctx.STRING())
        for x in self.__bindings:
                _type, _table = x
                if(_type.upper() == str(ctx.STRING()).upper()):
                        self.append('SELECT * FROM {} WHERE type = "{}" AND'.format(_table, _type))
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LanguageParser#setCommand.
    def visitSetCommand(self, ctx:LanguageParser.SetCommandContext):
        return self.visitChildren(ctx)

    # Visit a parse tree produced by LanguageParser#getCfgCommand.
    def visitGetCfgCommand(self, ctx:LanguageParser.GetCfgCommandContext):
        # client asks to see its configuration
        return self.visitChildren(ctx)

    # Visit a parse tree produced by LanguageParser#getTimeExpr.
    def visitGetTimeExpr(self, ctx:LanguageParser.GetTimeExprContext):
        self.append(' time ')
        self.visitChildren(ctx)
        self.append(' ' + str(ctx.DATE()) + ' ')

    # Visit a parse tree produced by LanguageParser#getTimeIntervalExpr.
    def visitGetTimeIntervalExpr(self, ctx:LanguageParser.GetTimeIntervalExprContext):
        self.append(' time >= ')
        self.append(' ' + str(ctx.DATE(0)) + ' ')
        self.append(' AND ')
        self.append(' time <= ')
        self.append(' ' + str(ctx.DATE(1)) + ' ')

    # Visit a parse tree produced by LanguageParser#getValueExpr.
    def visitGetValueExpr(self, ctx:LanguageParser.GetValueExprContext):
        self.append(' value ')
        self.visitChildren(ctx)
        self.append(' '+ str(ctx.NUM()) + ' ')


    # Visit a parse tree produced by LanguageParser#setAlertExpr.
    def visitSetAlertExpr(self, ctx:LanguageParser.SetAlertExprContext):
        # enable alerts
        return self.visitChildren(ctx)

    # Visit a parse tree produced by LanguageParser#getStatusExpr.
    def visitGetStatusExpr(self, ctx:LanguageParser.GetStatusExprContext):
        # respond with status data
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LanguageParser#setGuidelineExpr.
    def visitSetGuidelineExpr(self, ctx:LanguageParser.SetGuidelineExprContext):
        # set guideline
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LanguageParser#compare.
    def visitCompare(self, ctx:LanguageParser.CompareContext):
        self.append(' ' + ctx.getText() + ' ')
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LanguageParser#assign.
    def visitAssign(self, ctx:LanguageParser.AssignContext):
        return self.visitChildren(ctx)

    # Visit a parse tree produced by LanguageParser#and.
    def visitAndexpr(self, ctx:LanguageParser.AndexprContext):
        self.append(' AND ')
        return self.visitChildren(ctx)


del LanguageParser
