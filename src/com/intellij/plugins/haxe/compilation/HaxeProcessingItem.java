package com.intellij.plugins.haxe.compilation;

import com.intellij.openapi.compiler.FileProcessingCompiler;
import com.intellij.openapi.compiler.ValidityState;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

class HaxeProcessingItem implements FileProcessingCompiler.ProcessingItem {
    private VirtualFile virtualFile;

    public HaxeProcessingItem(VirtualFile file) {
        virtualFile = file;
    }

    @NotNull
    public VirtualFile getFile() {
        return virtualFile;
    }

    public ValidityState getValidityState() {
        return null;
    }
}
