package com.intellij.plugins.haxe.lang.parser.parsing;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.lexer.HaxeElementType;
import com.intellij.plugins.haxe.lang.lexer.HaxeTokenTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class PackageDeclaration implements HaxeElementTypes {

    public static boolean parse(PsiBuilder builder) {

        PsiBuilder.Marker packageDeclaration = builder.mark();


        if (ParserUtils.getToken(builder, kPACKAGE, HaxeBundle.message("package.keyword.expected"))) {
            PsiBuilder.Marker marker = builder.mark();

            ParserUtils.skipNLS(builder);

            if (!ParserUtils.getToken(builder, mIDENT, HaxeBundle.message("identifier.expected"))) {
                marker.rollbackTo();
                builder.error(HaxeBundle.message("identifier.expected"));
            } else {
                marker.drop();
            }
        } else {
            ParserUtils.getToken(builder, mIDENT);
        }

        if (builder.getTokenType() != oSEMI && builder.getTokenType() != wsNLS) {
            builder.error(HaxeBundle.message("semicolon.or.newline.expected"));
        }

        ParserUtils.getToken(builder, oSEMI);
        packageDeclaration.done(PACKAGE_DECLARATION);

        return true;
    }
}
