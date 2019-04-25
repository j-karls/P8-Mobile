package dk.aau.iaqlibrary

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream


val lexer = TestLexer(CharStreams.fromString("Hallo John!"))
val parser = TestParser(CommonTokenStream(lexer))

val name = parser.main().name().text