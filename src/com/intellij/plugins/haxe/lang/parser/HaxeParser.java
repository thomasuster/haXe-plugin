package com.intellij.plugins.haxe.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.plugins.haxe.lang.parser.parsing.ImportDeclaration;
import com.intellij.plugins.haxe.lang.parser.parsing.PackageDeclaration;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class HaxeParser implements PsiParser {
    private HashSet<String> packageNames = new HashSet<String>();

    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder builder) {
        PsiBuilder.Marker rootMarker = builder.mark();

        ParserUtils.skipNLS(builder);

        if (ParserUtils.lookAhead(builder, HaxeElementTypes.kPACKAGE)) {
            PackageDeclaration.parse(builder);
        }

        ParserUtils.skipNLS(builder);

        while (ParserUtils.lookAhead(builder, HaxeElementTypes.kIMPORT)) {
            ImportDeclaration.parse(builder, this);
            ParserUtils.skipNLS(builder);
        }

        while (!builder.eof()) {
            builder.advanceLexer();
        }

        rootMarker.done(root);

        return builder.getTreeBuilt();
    }

    public void setKnownPackage(String packageName) {
        packageNames.add(packageName);
    }
}
