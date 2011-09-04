package com.intellij.plugins.haxe.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.plugins.haxe.runner.ui.HaxeRunConfigurationEditorForm;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

public class HaxeApplicationConfiguration extends ModuleBasedConfiguration<HaxeApplicationModuleBasedConfiguration> {
    private String mainClass;

    public HaxeApplicationConfiguration(String name, Project project, HaxeRunConfigurationType configurationType) {
        super(name, new HaxeApplicationModuleBasedConfiguration(project), configurationType.getConfigurationFactories()[0]);
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    @Override
    public Collection<Module> getValidModules() {
        Module[] modules = ModuleManager.getInstance(getProject()).getModules();
        return Arrays.asList(modules);
    }

    @Override
    protected ModuleBasedConfiguration createInstance() {
        return new HaxeApplicationConfiguration(getName(), getProject(), HaxeRunConfigurationType.getInstance());
    }

    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new HaxeRunConfigurationEditorForm(getProject());
    }

    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment env) throws ExecutionException {
        return new HaxeRunningState(env, getConfigurationModule().getModule());
    }
}
