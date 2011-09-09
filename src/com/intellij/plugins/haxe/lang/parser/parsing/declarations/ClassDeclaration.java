package com.intellij.plugins.haxe.lang.parser.parsing.declarations;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.parsing.Modifiers;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;
import com.intellij.psi.tree.TokenSet;

public class ClassDeclaration implements HaxeElementTypes {

    public static boolean parse(PsiBuilder builder, HaxeParser parser) {
        PsiBuilder.Marker marker = builder.mark();

        if (!ParserUtils.getToken(builder, kCLASS)) {
            marker.rollbackTo();
            return false;
        }

        ParserUtils.skipNLS(builder);
        ParserUtils.getToken(builder, mIDENT, HaxeBundle.message("class.name.expected"));

        boolean olreadyExtends = false;
        boolean isInChain = false;

        ParserUtils.skipNLS(builder);
        while (TokenSet.create(kEXTENDS, kIMPLEMENTS, oCOMMA).contains(builder.getTokenType())) {
            if (isInChain) {
                ParserUtils.skipNLS(builder);
                ParserUtils.getToken(builder, oCOMMA, HaxeBundle.message("comma.expected"));
            }
            if (builder.getTokenType() == kEXTENDS) {
                if (olreadyExtends) {
                    builder.error(HaxeBundle.message("already.extends"));
                }

                builder.advanceLexer();

                ParserUtils.skipNLS(builder);
                ParserUtils.getToken(builder, mIDENT, HaxeBundle.message("class.name.expected"));
            } else if (builder.getTokenType() == kIMPLEMENTS) {
                builder.advanceLexer();

                ParserUtils.skipNLS(builder);
                ParserUtils.getToken(builder, mIDENT, HaxeBundle.message("interface.name.expected"));
            } else {
                builder.error(HaxeBundle.message("declaration.error"));
            }
            isInChain = true;
        }

        ParserUtils.skipNLS(builder);
        ParserUtils.getToken(builder, pLCURLY, HaxeBundle.message("declaration.expected"));

        while (builder.getTokenType() != pRCURLY) {
            PsiBuilder.Marker declarationMarker = builder.mark();

            ParserUtils.skipNLS(builder);
            Modifiers.parse(builder, parser);

            if (builder.getTokenType() == kVAR || builder.getTokenType() == kCONST) {
                parser.parseVarOrConst(builder);
                declarationMarker.drop();
            } else {
                //TODO function declaration
                declarationMarker.rollbackTo();
                break;
            }
        }

        ParserUtils.skipNLS(builder);
        ParserUtils.getToken(builder, pRCURLY, HaxeBundle.message("declaration.end.expected"));

        marker.done(CLASS_DECLARATION);

        return true;
    }
}
