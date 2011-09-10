package com.intellij.plugins.haxe.lang.parser.parsing.declarations;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class TypedVariableDeclaration implements HaxeElementTypes {
    public static boolean parseList(PsiBuilder builder, HaxeParser parser) {
        boolean inChain = false;
        while ((!inChain && ParserUtils.lookAhead(builder, mIDENT))
                || (inChain && ParserUtils.lookAhead(builder, oCOMMA))) {
            if (inChain) {
                ParserUtils.skipNLS(builder);
                ParserUtils.getToken(builder, oCOMMA, HaxeBundle.message("comma.expected"));
            }
            if (!parse(builder, parser)) {
                return false;
            }
            inChain = true;
        }
        return true;
    }

    public static boolean parse(PsiBuilder builder, HaxeParser parser) {
        ParserUtils.skipNLS(builder);

        PsiBuilder.Marker nameMarker = builder.mark();
        if (!ParserUtils.getToken(builder, mIDENT, HaxeBundle.message("identifier.expected"))) {
            nameMarker.rollbackTo();
            return false;
        }

        nameMarker.done(VAR_FUNCTION_NAME);

        return parseTypeAssign(builder, parser);
    }

    public static boolean parseTypeAssign(PsiBuilder builder, HaxeParser parser) {
        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, oCOLON, HaxeBundle.message("type.expected"))) {
            return false;
        }

        ParserUtils.skipNLS(builder);

        PsiBuilder.Marker typeMarker = builder.mark();
        if (!ParserUtils.getToken(builder, mIDENT, HaxeBundle.message("type.expected"))) {
            typeMarker.rollbackTo();
            return false;
        }
        typeMarker.done(TYPE);

        return true;
    }
}
