package com.intellij.plugins.haxe.lang.parser.parsing.statements;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.parsing.expressions.Expressions;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class WhileStatement implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser) {
        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, kWHILE)) {
            return false;
        }

        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, pLPAREN, HaxeBundle.message("left.bracket.expected"))) {
            return false;
        }

        if (!Expressions.parse(builder, parser, true)) {
            return false;
        }

        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, pRPAREN, HaxeBundle.message("right.bracket.expected"))) {
            return false;
        }

        return parser.parseStatement(builder);
    }
}
