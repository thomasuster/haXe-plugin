package com.intellij.plugins.haxe.lang.parser.parsing.expressions;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.lexer.HaxeTokenTypeSets;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class BinaryExpression implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser, boolean inCondition) {
        ParserUtils.skipNLS(builder);

        PsiBuilder.Marker marker = builder.mark();
        if (!parse(builder, parser, inCondition, 0)) {
            marker.drop();
            return false;
        }

        marker.done(EXPRESSION);
        return true;
    }

    private static boolean parse(PsiBuilder builder, HaxeParser parser, boolean inCondition, int bracketCount) {
        ParserUtils.skipNLS(builder);
        while (ParserUtils.getToken(builder, pLPAREN)) {
            ++bracketCount;
            ParserUtils.skipNLS(builder);
        }

        if (!UnaryExpression.parse(builder, parser)) {
            return false;
        }

        ParserUtils.skipNLS(builder);
        while (ParserUtils.lookAhead(builder, pRPAREN)) {
            if (inCondition && bracketCount == 0) {
                //expression completed
                return true;
            }
            builder.advanceLexer();
            --bracketCount;
            ParserUtils.skipNLS(builder);
        }

        if (bracketCount < 0) {
            builder.error(HaxeBundle.message("no.open.bracket"));
            return false;
        }

        return parseCont(builder, parser, inCondition, bracketCount);
    }

    private static boolean parseCont(PsiBuilder builder, HaxeParser parser, boolean inCondition, int bracketCount) {
        ParserUtils.skipNLS(builder);
        if (!HaxeTokenTypeSets.BINARY_OPERATORS.contains(builder.getTokenType())) {
            if (bracketCount == 0) {
                //expression completed
                return true;
            }
            builder.error(HaxeBundle.message("binary.operator.expected"));
            return false;
        }

        builder.advanceLexer();
        return parse(builder, parser, inCondition, bracketCount);

    }
}
