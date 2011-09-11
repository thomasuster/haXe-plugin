package com.intellij.plugins.haxe.lang.parser.parsing.declarations;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class TypeDeclaration implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser) {
        ParserUtils.skipNLS(builder);

        PsiBuilder.Marker typeMarker = builder.mark();
        if (!ParserUtils.getToken(builder, mIDENT)) {
            typeMarker.rollbackTo();
            return false;
        }

        if (ParserUtils.lookAhead(builder, oLESS)) {
            if (!TemplateDeclaration.parse(builder, parser)) {
                typeMarker.drop();
                return false;
            }
        }
        typeMarker.done(TYPE);

        return true;

    }
}
