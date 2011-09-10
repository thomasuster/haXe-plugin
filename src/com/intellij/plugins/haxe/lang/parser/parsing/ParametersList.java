package com.intellij.plugins.haxe.lang.parser.parsing;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.parsing.expressions.Expressions;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class ParametersList implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser) {

        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, pLPAREN, HaxeBundle.message("left.bracket.expected"))) {
            return false;
        }


        boolean isInChain = false;

        ParserUtils.skipNLS(builder);
        while (builder.getTokenType() != pRPAREN) {
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
        if (!ParserUtils.getToken(builder, pRPAREN, HaxeBundle.message("right.bracket.expected"))) {
            return false;
        }
        return true;
    }
}
