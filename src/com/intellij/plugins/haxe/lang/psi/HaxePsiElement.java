package com.intellij.plugins.haxe.lang.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class HaxePsiElement extends ASTWrapperPsiElement {
    public HaxePsiElement(@NotNull ASTNode node) {
        super(node);
    }

    public IElementType getTokenType() {
        return getNode().getElementType();
    }

    public String toString() {
        return getTokenType().toString();
    }
}
