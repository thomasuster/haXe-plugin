package com.intellij.plugins.haxe.compilation;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.RunManager;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompileScope;
import com.intellij.openapi.compiler.CompilerMessageCategory;
import com.intellij.openapi.compiler.TranslatingCompiler;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.plugins.haxe.HaxeBundle;
import com.intellij.plugins.haxe.HaxeFileType;
import com.intellij.plugins.haxe.config.sdk.HaxeSdkData;
import com.intellij.plugins.haxe.config.sdk.HaxeSdkType;
import com.intellij.plugins.haxe.config.sdk.HaxeSdkUtil;
import com.intellij.plugins.haxe.runner.HaxeApplicationConfiguration;
import com.intellij.plugins.haxe.runner.HaxeRunConfigurationType;
import com.intellij.util.Chunk;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HaxeCompiler implements TranslatingCompiler {
    private static final Logger LOG = Logger.getInstance("#com.intellij.plugins.haxe.compilation.HaxeCompiler");

    @NotNull
    public String getDescription() {
        return HaxeBundle.message("haxe.compiler.description");
    }

    public boolean validateConfiguration(CompileScope scope) {
        return true;
    }

    public boolean isCompilableFile(VirtualFile file, CompileContext context) {
        return true;
    }

    public void compile(CompileContext context, Chunk<Module> moduleChunk, VirtualFile[] files, OutputSink sink) {
        final Sdk jdk = getJdk(moduleChunk);
        final HaxeSdkType sdkType = (HaxeSdkType) jdk.getSdkType();
        final HaxeSdkData sdkData = sdkType.getSdkData();
        final String homePath = sdkData.getHomePath();

        GeneralCommandLine commandLine = new GeneralCommandLine();

        commandLine.setExePath(HaxeSdkUtil.getCompilerPathByFolderPath(homePath));

        HaxeApplicationConfiguration applicationConfiguration = getApplicationConfiguration(context.getProject());

        commandLine.addParameter("-main");
        commandLine.addParameter(getMainClassNameByPath(applicationConfiguration.getMainClass()));

        ProcessOutput output = null;
        try {
            output = new CapturingProcessHandler(
                    commandLine.createProcess(),
                    Charset.defaultCharset(),
                    commandLine.getCommandLineString()).runProcess();
        } catch (ExecutionException e) {
            context.addMessage(CompilerMessageCategory.ERROR, "process throw exception: " + e.getMessage(), null, -1, -1);
            return;
        }

        if (output.getExitCode() != 0) {
            context.addMessage(CompilerMessageCategory.WARNING, "process exited with code: " + output.getExitCode(), null, -1, -1);
        }
    }

    /**
     * @return the jdk. Assumes that the jdk is the same for all modules
     */
    public Sdk getJdk(Chunk<Module> moduleChunk) {
        final Module module = moduleChunk.getNodes().iterator().next();
        return ModuleRootManager.getInstance(module).getSdk();
    }

    private HaxeApplicationConfiguration getApplicationConfiguration(Project project) {
        RunManager runManager = RunManager.getInstance(project);
        RunConfiguration[] configurations = runManager.getConfigurations(HaxeRunConfigurationType.getInstance());
        if (configurations.length > 0) {
            return (HaxeApplicationConfiguration) configurations[0];
        }
        return null;
    }

    private String getMainClassNameByPath(String path) {
        Pattern pattern = Pattern.compile("\\.*(\\w+)\\." + HaxeFileType.DEFAULT_EXTENSION);
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
