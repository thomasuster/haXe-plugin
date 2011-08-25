package com.intellij.plugins.haxe.lang.lexer;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HaxeElementType extends IElementType {

    public HaxeElementType(@NotNull @NonNls String debugName, @Nullable Language language) {
        super(debugName, language);
    }

}
