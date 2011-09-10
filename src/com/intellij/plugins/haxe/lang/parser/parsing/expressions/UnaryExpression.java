package com.intellij.plugins.haxe.lang.parser.parsing.expressions;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.lexer.HaxeTokenTypeSets;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.parsing.ParametersList;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class UnaryExpression implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser) {
        ParserUtils.skipNLS(builder);
        if (builder.getTokenType() == oNEW) {
            return parseCreation(builder, parser);
        } else if (HaxeTokenTypeSets.LITERALS.contains(builder.getTokenType())) {
            return parseLiteral(builder);
        } else {
            //TODO
        }
        return true;
    }

    public static boolean parseCreation(PsiBuilder builder, HaxeParser parser) {
        PsiBuilder.Marker newMarker = builder.mark();

        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, oNEW)) {
            newMarker.rollbackTo();
            return false;
        }

        ParserUtils.skipNLS(builder);
        ParserUtils.getToken(builder, mIDENT, HaxeBundle.message("class.name.expected"));

        ParserUtils.skipNLS(builder);
        ParametersList.parse(builder, parser);

        newMarker.done(INSTANTIATION);

        return true;
    }

    private static boolean parseLiteral(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer();
        marker.done(LITERAL);
        return true;
    }
}
