package com.intellij.plugins.haxe.lang.parser.parsing;

import com.intellij.lang.PsiBuilder;
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
        String packagePath = PackagePathDeclaration.parse(builder);

        if (packagePath != null) {
            parser.setKnownPackage(packagePath);
        }

        marker.done(IMPORT_DECLARATION);
        return packagePath != null;
    }
}
