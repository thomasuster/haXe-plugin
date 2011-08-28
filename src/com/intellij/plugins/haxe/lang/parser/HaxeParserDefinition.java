package com.intellij.plugins.haxe.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.plugins.haxe.lang.lexer.HaxeLexer;
import com.intellij.plugins.haxe.lang.lexer.HaxeTokenTypeSets;
import com.intellij.plugins.haxe.lang.psi.HaxeFile;
import com.intellij.plugins.haxe.lang.psi.HaxeFileElementType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class HaxeParserDefinition implements ParserDefinition {
    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new HaxeLexer();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new HaxeParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return new HaxeFileElementType();
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return HaxeTokenTypeSets.WHITESPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return HaxeTokenTypeSets.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return HaxeTokenTypeSets.STRINGS;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return HaxePsiCreator.createElement(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new HaxeFile(viewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }
}
