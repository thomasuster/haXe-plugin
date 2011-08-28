package com.intellij.plugins.haxe.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.plugins.haxe.lang.psi.HaxePsiElement;
import com.intellij.psi.PsiElement;

public class HaxePsiCreator {
    public static PsiElement createElement(ASTNode node) {
        return new HaxePsiElement(node);
    }
}
