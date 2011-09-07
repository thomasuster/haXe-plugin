package com.intellij.plugins.haxe.lang.parser.parsing;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class PackagePathDeclaration implements HaxeElementTypes {
    public static String parse(PsiBuilder builder) {
        boolean parsed = false;
        PsiBuilder.Marker importPathMarker = builder.mark();

        String tokenText = builder.getTokenText();
        if (!ParserUtils.getToken(builder, mIDENT)) {
            importPathMarker.rollbackTo();
            builder.error(HaxeBundle.message("import.path.expected"));
            return null;
        }

        String packagePath = tokenText;

        while (builder.getTokenType() == oDOT) {
            packagePath += builder.getTokenText();
            builder.advanceLexer();
            if (builder.getTokenType() != mIDENT) {
                builder.error(HaxeBundle.message("import.path.expected"));
                break;
            }
            packagePath += builder.getTokenText();
            builder.advanceLexer();
        }

        if (builder.getTokenType() != oSEMI) {
            builder.error(HaxeBundle.message("semicolon.or.newline.expected"));
        } else {
            parsed = true;
            builder.advanceLexer();
        }

        importPathMarker.done(PACKAGE_PATH);

        if (parsed) {
            return packagePath;
        }

        return null;
    }
}
