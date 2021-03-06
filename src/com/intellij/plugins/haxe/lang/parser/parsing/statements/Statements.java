package com.intellij.plugins.haxe.lang.parser.parsing.statements;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.lexer.HaxeTokenTypeSets;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.parsing.declarations.VarOrConstDeclaration;
import com.intellij.plugins.haxe.lang.parser.parsing.expressions.CallExpression;
import com.intellij.plugins.haxe.lang.parser.parsing.expressions.Expressions;
import com.intellij.plugins.haxe.lang.parser.parsing.expressions.UnaryExpression;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class Statements implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser) {
        if (builder.getTokenType() == kVAR || builder.getTokenType() == kCONST) {
            return VarOrConstDeclaration.parse(builder, parser);
        } else if (builder.getTokenType() == pLCURLY) {
            return BlockStatement.parse(builder, parser);
        } else if (builder.getTokenType() == kRETURN) {
            return parseReturnStatement(builder, parser);
        } else if (builder.getTokenType() == kIF) {
            return IfStatement.parse(builder, parser);
        } else if (builder.getTokenType() == kWHILE) {
            return WhileStatement.parse(builder, parser);
        } else if (HaxeTokenTypeSets.INC_DEC_OPERATORS.contains(builder.getTokenType())) {
            return parsePrefixIncDec(builder, parser);
        } else {
            return parseCallStatement(builder, parser);
        }
    }

    private static boolean parseReturnStatement(PsiBuilder builder, HaxeParser parser) {
        ParserUtils.getToken(builder, kRETURN);

        ParserUtils.skipNLS(builder);
        if (ParserUtils.getToken(builder, oSEMI)) {
            return true;
        }

        if (!Expressions.parse(builder, parser, false)) {
            return false;
        }

        ParserUtils.skipNLS(builder);
        return ParserUtils.getToken(builder, oSEMI, HaxeBundle.message("semicolon.expected"));
    }

    private static boolean parseCallStatement(PsiBuilder builder, HaxeParser parser) {
        if (!CallExpression.parse(builder, parser)) {
            return false;
        }

        if (HaxeTokenTypeSets.ASSIGN_OPERATORS.contains(builder.getTokenType())) {
            builder.advanceLexer();

            if (!Expressions.parse(builder, parser, false)) {
                return false;
            }
        }

        ParserUtils.skipNLS(builder);
        return ParserUtils.getToken(builder, oSEMI, HaxeBundle.message("semicolon.expected"));
    }

    private static boolean parsePrefixIncDec(PsiBuilder builder, HaxeParser parser) {
        if (!UnaryExpression.parse(builder, parser)) {
            return false;
        }

        ParserUtils.skipNLS(builder);
        return ParserUtils.getToken(builder, oSEMI, HaxeBundle.message("semicolon.expected"));
    }
}
