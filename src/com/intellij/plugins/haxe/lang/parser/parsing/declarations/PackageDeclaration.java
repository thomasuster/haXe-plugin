package com.intellij.plugins.haxe.lang.parser.parsing.declarations;

import com.intellij.lang.PsiBuilder;
import com.intellij.plugins.haxe.lang.parser.HaxeElementTypes;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;

public class PackageDeclaration implements HaxeElementTypes {

    public static boolean parse(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();

        if (!ParserUtils.getToken(builder, kPACKAGE)) {
            marker.rollbackTo();
            return false;
        }

        ParserUtils.skipNLS(builder);
        String packagePath = PackagePathDeclaration.parse(builder);

        marker.done(PACKAGE_DECLARATION);

        return packagePath != null;
    }
}
