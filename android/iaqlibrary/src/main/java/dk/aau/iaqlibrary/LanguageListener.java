// Generated from D:/GitHub/P8-Mobile/android/iaqlibrary/src/main/java/dk/aau/iaqlibrary\Language.g4 by ANTLR 4.7.2
package dk.aau.iaqlibrary;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LanguageParser}.
 */
public interface LanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LanguageParser#s}.
	 * @param ctx the parse tree
	 */
	void enterS(LanguageParser.SContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#s}.
	 * @param ctx the parse tree
	 */
	void exitS(LanguageParser.SContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getCommand}
	 * labeled alternative in {@link LanguageParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterGetCommand(LanguageParser.GetCommandContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getCommand}
	 * labeled alternative in {@link LanguageParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitGetCommand(LanguageParser.GetCommandContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setCommand}
	 * labeled alternative in {@link LanguageParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterSetCommand(LanguageParser.SetCommandContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setCommand}
	 * labeled alternative in {@link LanguageParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitSetCommand(LanguageParser.SetCommandContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getTimeExpr}
	 * labeled alternative in {@link LanguageParser#getexpr}.
	 * @param ctx the parse tree
	 */
	void enterGetTimeExpr(LanguageParser.GetTimeExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getTimeExpr}
	 * labeled alternative in {@link LanguageParser#getexpr}.
	 * @param ctx the parse tree
	 */
	void exitGetTimeExpr(LanguageParser.GetTimeExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getTimeIntervalExpr}
	 * labeled alternative in {@link LanguageParser#getexpr}.
	 * @param ctx the parse tree
	 */
	void enterGetTimeIntervalExpr(LanguageParser.GetTimeIntervalExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getTimeIntervalExpr}
	 * labeled alternative in {@link LanguageParser#getexpr}.
	 * @param ctx the parse tree
	 */
	void exitGetTimeIntervalExpr(LanguageParser.GetTimeIntervalExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getValueExpr}
	 * labeled alternative in {@link LanguageParser#getexpr}.
	 * @param ctx the parse tree
	 */
	void enterGetValueExpr(LanguageParser.GetValueExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getValueExpr}
	 * labeled alternative in {@link LanguageParser#getexpr}.
	 * @param ctx the parse tree
	 */
	void exitGetValueExpr(LanguageParser.GetValueExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getAlertExpr}
	 * labeled alternative in {@link LanguageParser#getexpr}.
	 * @param ctx the parse tree
	 */
	void enterGetAlertExpr(LanguageParser.GetAlertExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getAlertExpr}
	 * labeled alternative in {@link LanguageParser#getexpr}.
	 * @param ctx the parse tree
	 */
	void exitGetAlertExpr(LanguageParser.GetAlertExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getStatusExpr}
	 * labeled alternative in {@link LanguageParser#getexpr}.
	 * @param ctx the parse tree
	 */
	void enterGetStatusExpr(LanguageParser.GetStatusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getStatusExpr}
	 * labeled alternative in {@link LanguageParser#getexpr}.
	 * @param ctx the parse tree
	 */
	void exitGetStatusExpr(LanguageParser.GetStatusExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setGuidelineExpr}
	 * labeled alternative in {@link LanguageParser#setexpr}.
	 * @param ctx the parse tree
	 */
	void enterSetGuidelineExpr(LanguageParser.SetGuidelineExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setGuidelineExpr}
	 * labeled alternative in {@link LanguageParser#setexpr}.
	 * @param ctx the parse tree
	 */
	void exitSetGuidelineExpr(LanguageParser.SetGuidelineExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#compare}.
	 * @param ctx the parse tree
	 */
	void enterCompare(LanguageParser.CompareContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#compare}.
	 * @param ctx the parse tree
	 */
	void exitCompare(LanguageParser.CompareContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(LanguageParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(LanguageParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#andexpr}.
	 * @param ctx the parse tree
	 */
	void enterAndexpr(LanguageParser.AndexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#andexpr}.
	 * @param ctx the parse tree
	 */
	void exitAndexpr(LanguageParser.AndexprContext ctx);
}