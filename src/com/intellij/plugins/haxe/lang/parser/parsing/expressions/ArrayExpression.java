package com.intellij.plugins.haxe.lang.parser.parsing.expressions;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class ArrayExpression implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser) {

        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, pLBRACK, HaxeBundle.message("left.square.bracket.expected"))) {
            return false;
        }


        boolean isInChain = false;

        ParserUtils.skipNLS(builder);
        while (builder.getTokenType() != pRBRACK) {
            if (isInChain && !ParserUtils.getToken(builder, oCOMMA, HaxeBundle.message("comma.expected"))) {
                return false;
            }

            ParserUtils.skipNLS(builder);
            if (!Expressions.parse(builder, parser, true)) {
                return false;
            }

            isInChain = true;
            ParserUtils.skipNLS(builder);
        }

        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, pRBRACK, HaxeBundle.message("right.square.bracket.expected"))) {
            return false;
        }
        return true;
    }
}
