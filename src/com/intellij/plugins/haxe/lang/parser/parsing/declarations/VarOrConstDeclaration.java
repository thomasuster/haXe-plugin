package com.intellij.plugins.haxe.lang.parser.parsing.declarations;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class VarOrConstDeclaration implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser) {
        PsiBuilder.Marker varConstMarker = builder.mark();

        if (!ParserUtils.getToken(builder, kVAR) && !ParserUtils.getToken(builder, kCONST)) {
            varConstMarker.rollbackTo();
            return false;
        }

        ParserUtils.skipNLS(builder);

        PsiBuilder.Marker nameMarker = builder.mark();
        if (!ParserUtils.getToken(builder, mIDENT, HaxeBundle.message("identifier.expected"))) {
            varConstMarker.rollbackTo();
            return false;
        }
        nameMarker.done(VAR_CONST_NAME);

        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, oCOLON, HaxeBundle.message("type.expected"))) {
            varConstMarker.rollbackTo();
            return false;
        }

        ParserUtils.skipNLS(builder);

        PsiBuilder.Marker typeMarker = builder.mark();
        if (!ParserUtils.getToken(builder, mIDENT, HaxeBundle.message("type.expected"))) {
            varConstMarker.rollbackTo();
            return false;
        }
        typeMarker.done(TYPE);

        varConstMarker.done(VAR_CONST_DECLARATION);

        ParserUtils.skipNLS(builder);
        if (ParserUtils.getToken(builder, oSEMI)) {
            return true;
        }

        return true;
    }
}
