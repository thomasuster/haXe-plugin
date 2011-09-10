package com.intellij.plugins.haxe.lang.parser.parsing.declarations;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.parsing.expressions.Expressions;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class VarOrConstDeclaration implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser) {
        PsiBuilder.Marker varConstMarker = builder.mark();

        if (!ParserUtils.getToken(builder, kVAR) && !ParserUtils.getToken(builder, kCONST)) {
            varConstMarker.rollbackTo();
            return false;
        }

        if (!TypedVariableDeclaration.parse(builder, parser)) {
            varConstMarker.drop();
            return false;
        }

        varConstMarker.done(VAR_CONST_DECLARATION);

        ParserUtils.skipNLS(builder);
        if (ParserUtils.lookAhead(builder, oASSIGN)) {
            builder.advanceLexer();
            if (!Expressions.parse(builder, parser, false)) {
                return false;
            }
        }
        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, oSEMI, HaxeBundle.message("semicolon.expected"))) {
            return false;
        }

        return true;
    }
}
