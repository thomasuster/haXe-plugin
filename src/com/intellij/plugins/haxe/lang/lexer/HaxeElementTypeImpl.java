package com.intellij.plugins.haxe.lang.lexer;

import com.intellij.plugins.haxe.HaxeFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class HaxeElementTypeImpl extends HaxeElementType {

    private String debugName;

    public HaxeElementTypeImpl(@NotNull @NonNls String debugName) {
        super(debugName, HaxeFileType.HAXE_FILE_TYPE.getLanguage());
        this.debugName = debugName;
    }

    @Override
    public String toString() {
        return debugName;
    }
}
