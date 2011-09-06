package com.intellij.plugins.haxe.lang.parser.util;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.lang.lexer.HaxeTokenTypeSets;
import com.intellij.plugins.haxe.lang.lexer.HaxeTokenTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.psi.tree.IElementType;

public class ParserUtils {

    public static void skipNLS(PsiBuilder builder) {
        while (builder.getTokenType() == HaxeTokenTypes.wsNLS) {
            builder.advanceLexer();
        }
    }

    public static void skipComments(PsiBuilder builder) {
        while (HaxeTokenTypeSets.COMMENTS.contains(builder.getTokenType())) {
            builder.advanceLexer();
        }
    }

    public static boolean getToken(PsiBuilder builder, IElementType elem, String errorMsg) {
        if (elem.equals(builder.getTokenType())) {
            builder.advanceLexer();
            return true;
        }
        if (errorMsg != null)
            builder.error(errorMsg);
        return false;
    }

    public static boolean getToken(PsiBuilder builder, IElementType elem) {
        if (elem.equals(builder.getTokenType())) {
            builder.advanceLexer();
            return true;
        }
        return false;
    }

    public static boolean lookAhead(PsiBuilder builder, IElementType... elems) {
        if (!elems[0].equals(builder.getTokenType())) return false;

        if (elems.length == 1) return true;

        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer();
        int i = 1;
        while (!builder.eof() && i < elems.length && (builder.getTokenType() == HaxeElementTypes.wsNLS || elems[i].equals(builder.getTokenType()))) {

            if (builder.getTokenType() != HaxeElementTypes.wsNLS) {
                i++;
            }

            builder.advanceLexer();
        }
        marker.rollbackTo();
        return i == elems.length;
    }
}
