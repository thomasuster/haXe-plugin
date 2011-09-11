package com.intellij.plugins.haxe.lang.parser.parsing.declarations;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class TemplateDeclaration implements HaxeElementTypes {
    public static boolean parse(PsiBuilder builder, HaxeParser parser) {
        PsiBuilder.Marker marker = builder.mark();

        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, oLESS)) {
            marker.rollbackTo();
            return false;
        }

        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, mIDENT, HaxeBundle.message("type.expected"))) {
            marker.drop();
            return false;
        }

        ParserUtils.skipNLS(builder);
        if (!ParserUtils.getToken(builder, oGREATER, HaxeBundle.message("template.end.expected"))) {
            marker.drop();
            return false;
        }

        marker.done(TEMPLATE_DECLARATION);

        return true;
    }
}
