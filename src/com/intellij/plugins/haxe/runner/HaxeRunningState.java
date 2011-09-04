package com.intellij.plugins.haxe.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.plugins.haxe.config.sdk.HaxeSdkData;
import com.intellij.plugins.haxe.config.sdk.HaxeSdkUtil;
import com.intellij.plugins.haxe.util.CompilationUtil;

public class HaxeRunningState extends CommandLineState {
    private static final String ID = "haXe Console";
    private static final String TITLE = "haXe Console Output";

    private Module module;

    public HaxeRunningState(ExecutionEnvironment env, Module module) {
        super(env);
        this.module = module;
    }

    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        final Sdk sdk = ModuleRootManager.getInstance(module).getSdk();
        final HaxeSdkData sdkData = HaxeSdkUtil.testHaxeSdk(sdk.getHomePath());

        GeneralCommandLine commandLine = new GeneralCommandLine();

        commandLine.setExePath(HaxeSdkUtil.getVMPathByFolderPath(sdkData.getHomePath()));
        commandLine.addParameter(CompilationUtil.getNekoBinPathForModule(module));

        return new OSProcessHandler(commandLine.createProcess(), commandLine.getCommandLineString());
    }
}
