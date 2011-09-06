package com.intellij.plugins.haxe.lang.parser.parsing;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.HaxeParser;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;
import com.intellij.psi.tree.TokenSet;

public class ImportDeclaration implements HaxeElementTypes {
    private static final TokenSet localImportTokens = TokenSet.create(mIDENT, oDOT);

    public static boolean parse(PsiBuilder builder, HaxeParser parser) {

        PsiBuilder.Marker marker = builder.mark();

        if (!ParserUtils.getToken(builder, kIMPORT)) {
            marker.rollbackTo();
            return false;
        }

        ParserUtils.skipNLS(builder);

        parseImportStatement(builder, parser);

        marker.done(IMPORT_DECLARATION);
        return true;
    }

    private static boolean parseImportStatement(PsiBuilder builder, HaxeParser parser) {
        boolean parsed = false;
        PsiBuilder.Marker importPathMarker = builder.mark();

        String tokenText = builder.getTokenText();
        if (!ParserUtils.getToken(builder, mIDENT)) {
            importPathMarker.rollbackTo();
            builder.error(HaxeBundle.message("import.path.expected"));
        }

        String packagePath = tokenText;

        while (builder.getTokenType() == oDOT) {
            packagePath += builder.getTokenText();
            builder.advanceLexer();
            if (builder.getTokenType() != mIDENT) {
                importPathMarker.rollbackTo();
                builder.error(HaxeBundle.message("import.path.expected"));
                break;
            }
            packagePath += builder.getTokenText();
            builder.advanceLexer();
        }

        if (builder.getTokenType() != oSEMI && builder.getTokenType() != wsNLS) {
            builder.error(HaxeBundle.message("semicolon.or.newline.expected"));
        } else {
            parsed = true;
            builder.advanceLexer();
        }

        if (parsed) {
            parser.setKnownPackage(packagePath);
            importPathMarker.done(IMPORT_PATH);
        }

        return parsed;
    }
}
