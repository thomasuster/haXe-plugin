package com.intellij.plugins.haxe.compilation;

import com.intellij.execution.RunManager;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompileScope;
import com.intellij.openapi.compiler.TranslatingCompiler;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.config.sdk.HaxeSdkData;
import com.intellij.plugins.haxe.config.sdk.HaxeSdkType;
import com.intellij.plugins.haxe.config.sdk.HaxeSdkUtil;
import com.intellij.plugins.haxe.runner.HaxeRunConfigurationType;
import com.intellij.util.Chunk;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class HaxeCompiler implements TranslatingCompiler {
    private static final Logger LOG = Logger.getInstance("#com.intellij.plugins.haxe.compilation.HaxeCompiler");

    @NotNull
    public String getDescription() {
        return HaxeBundle.message("haxe.compiler.description");
    }

    @Override
    public boolean validateConfiguration(CompileScope scope) {
        return true;
    }

    @Override
    public boolean isCompilableFile(VirtualFile file, CompileContext context) {
        return true;
    }

    @Override
    public void compile(CompileContext context, Chunk<Module> moduleChunk, VirtualFile[] files, OutputSink sink) {
        final Sdk jdk = getJdk(moduleChunk);
        final HaxeSdkType sdkType = (HaxeSdkType) jdk.getSdkType();
        final HaxeSdkData sdkData = sdkType.getSdkData();
        final String homePath = sdkData.getHomePath();

        LinkedList<String> commandLine = new LinkedList<String>();

        commandLine.add(HaxeSdkUtil.getCompilerPathByFolderPath(homePath));
    }

    /**
     * @return the jdk. Assumes that the jdk is the same for all modules
     */
    public Sdk getJdk(Chunk<Module> moduleChunk) {
        final Module module = moduleChunk.getNodes().iterator().next();
        return ModuleRootManager.getInstance(module).getSdk();
    }
}
