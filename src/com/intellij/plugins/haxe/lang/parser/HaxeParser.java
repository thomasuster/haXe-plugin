package com.intellij.plugins.haxe.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.lang.parser.parsing.declarations.*;
import com.intellij.plugins.haxe.lang.parser.util.ParserUtils;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class HaxeParser implements PsiParser {
    private HashSet<String> packageNames = new HashSet<String>();

    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder builder) {
        PsiBuilder.Marker rootMarker = builder.mark();

        ParserUtils.skipNLS(builder);

        PackageDeclaration.parse(builder);
        ParserUtils.skipNLS(builder);

        while (ImportDeclaration.parse(builder, this)) {
            ParserUtils.skipNLS(builder);
        }

        ClassDeclaration.parse(builder, this);

        while (!builder.eof()) {
            builder.advanceLexer();
        }

        rootMarker.done(root);

        return builder.getTreeBuilt();
    }

    public void setKnownPackage(String packageName) {
        packageNames.add(packageName);
    }

    public void parseVarOrConst(PsiBuilder builder) {
        VarOrConstDeclaration.parse(builder, this);
    }

    public void parseFunction(PsiBuilder builder, boolean classMember) {
        FunctionDeclaration.parse(builder, this, classMember);
    }

    public void parseBlock(PsiBuilder builder) {
        //TODO
        ParserUtils.skipNLS(builder);
        ParserUtils.getToken(builder, HaxeElementTypes.pLCURLY, HaxeBundle.message("declaration.expected"));

        ParserUtils.skipNLS(builder);
        ParserUtils.getToken(builder, HaxeElementTypes.pRCURLY, HaxeBundle.message("declaration.expected"));
    }
}
