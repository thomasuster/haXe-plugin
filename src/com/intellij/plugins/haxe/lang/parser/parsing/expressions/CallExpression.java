package com.intellij.plugins.haxe.lang.parser.parsing.expressions;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.parsing.ParametersList;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class CallExpression implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser) {
        ParserUtils.skipNLS(builder);

        PsiBuilder.Marker marker = builder.mark();
        if (!ParserUtils.getToken(builder, mIDENT)) {
            marker.drop();
            return false;
        }
        marker.done(VAR_FUNCTION_NAME);

        if (ParserUtils.lookAhead(builder, pLBRACK)) {
            if (!parseIndex(builder, parser)) {
                return false;
            }
        } else if (ParserUtils.lookAhead(builder, pLPAREN)) {
            if (!ParametersList.parse(builder, parser)) {
                return false;
            }
        }

        if (ParserUtils.getToken(builder, oDOT)) {
            return parse(builder, parser);
        }
        return true;
    }

    private static boolean parseIndex(PsiBuilder builder, HaxeParser parser) {
        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, pLBRACK, HaxeBundle.message("left.square.bracket.expected"))) {
            return false;
        }

        ParserUtils.skipNLS(builder);
        if (!Expressions.parse(builder, parser, false)) {
            return false;
        }

        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, pRBRACK, HaxeBundle.message("right.square.bracket.expected"))) {
            return false;
        }
        return true;
    }
}
