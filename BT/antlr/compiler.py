import sys
from antlr4 import *
from LanguageLexer import LanguageLexer
from LanguageParser import LanguageParser
from antlr4.tree.Trees import Trees
from antlr4.error.ErrorListener import ErrorListener
from LanguageVisitor import LanguageVisitor
 
def Compile(input):
	lexer = LanguageLexer(InputStream(input))
	stream = CommonTokenStream(lexer)
	parser = LanguageParser(stream)
	parser._listeners = [_syntaxError()]
	tree = None
	try:
		tree = parser.s()
	except Exception as e:
		print(e)

	visitor = LanguageVisitor()
	query = visitor.visitS(tree)
	#print('Input:	' + str(input_stream))
	#print('Tree:	' + Trees.toStringTree(tree, None, parser))
	#print('Result:	' + query)

class _syntaxError(ErrorListener):
	def __init__(self):
		super(_syntaxError, self).__init__()

	def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
		raise Exception("Syntax error: " + "l:" + str(line) + " col:" + str(column) + "\n" + msg)