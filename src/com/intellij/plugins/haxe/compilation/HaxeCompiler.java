package com.intellij.plugins.haxe.compilation;

import com.intellij.compiler.OutputParser;
import com.intellij.compiler.impl.javaCompiler.ExternalCompiler;
import com.intellij.compiler.impl.javaCompiler.ModuleChunk;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompileScope;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.options.Configurable;
import com.intellij.plugins.haxe.HaxeBundle;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HaxeCompiler extends ExternalCompiler {
    private static final Logger LOG = Logger.getInstance("#com.intellij.plugins.haxe.compilation.HaxeCompiler");

    @NotNull
    @Override
    public String getId() {
        return "haXe";
    }

    @NotNull
    public String getDescription() {
        return HaxeBundle.message("haxe.compiler.description");
    }

    @NotNull
    @Override
    public String getPresentableName() {
        return HaxeBundle.message("haxe.compiler.name");
    }

    @Override
    public OutputParser createErrorParser(@NotNull String outputDir, Process process) {
        return null;
    }

    @Override
    public OutputParser createOutputParser(@NotNull String outputDir) {
        return null;
    }

    @Override
    public boolean checkCompiler(CompileScope scope) {
        return true;
    }

    @Override
    public void compileFinished() {
    }

    @NotNull
    @Override
    public Configurable createConfigurable() {
        //TODO
        return null;
    }

    @NotNull
    @Override
    public String[] createStartupCommand(ModuleChunk chunk, CompileContext context, String outputPath) throws IOException, IllegalArgumentException {
        //TODO
        return new String[0];
    }
}
