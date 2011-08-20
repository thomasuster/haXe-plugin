package com.intellij.plugins.haxe.compilation;

import com.intellij.openapi.compiler.ClassInstrumentingCompiler;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompileScope;
import com.intellij.openapi.compiler.ValidityState;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.plugins.haxe.HaxeFileType;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.IOException;

public class HaxeCompiler implements ClassInstrumentingCompiler {
    private static final Logger LOG = Logger.getInstance("#com.intellij.plugins.haxe.compilation.HaxeCompiler");

    @NotNull
    public String getDescription() {
        return "haXe Compiler";
    }

    public boolean validateConfiguration(CompileScope scope) {
        return true;
    }

    public ValidityState createValidityState(DataInput in) throws IOException {
        return null;
    }

    @NotNull
    public ProcessingItem[] getProcessingItems(CompileContext context) {
        final VirtualFile files[] = context.getProjectCompileScope().getFiles(HaxeFileType.HAXE_FILE_TYPE, true);
        ProcessingItem[] items = new ProcessingItem[files.length];
        for (int i = 0; i < items.length; ++i) {
            items[i] = new HaxeProcessingItem(files[i]);
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("[compiling] processing %1$ files", items.length));
        }
        return items;
    }

    public ProcessingItem[] process(CompileContext context, ProcessingItem[] items) {
        //TODO
        return ProcessingItem.EMPTY_ARRAY;
    }
}
