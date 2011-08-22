package com.intellij.plugins.haxe.components;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.projectRoots.impl.ProjectJdkImpl;
import com.intellij.openapi.projectRoots.impl.SdkConfigurationUtil;
import com.intellij.plugins.haxe.config.sdk.HaxeSdkData;
import com.intellij.plugins.haxe.config.sdk.HaxeSdkType;
import com.intellij.plugins.haxe.config.sdk.HaxeSdkUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class HaxeBundledSdkDetector implements ApplicationComponent {

    private static final Logger LOG = Logger.getInstance("#com.intellij.plugins.haxe.components.HaxeBundledSdkDetector");

    @Override
    public void initComponent() {
        final ProjectJdkTable jdkTable = ProjectJdkTable.getInstance();

        List<Sdk> haxeSdks = jdkTable.getSdksOfType(HaxeSdkType.getInstance());

        String homePath = PathManager.getHomePath() + "/bundled/haxe-sdk";

        File bundledHaxeSdkHomePath = new File(homePath);
        if ( ! bundledHaxeSdkHomePath.exists() || ! bundledHaxeSdkHomePath.isDirectory() ) {
            return;
        }

        LOG.debug("Bundled haXe SDK path exists: " + homePath);

        for (Sdk sdk : haxeSdks) {
            if ( homePath.startsWith(sdk.getHomePath()) ) {
                LOG.debug("Bundled haXe SDK at registered already with name: " + sdk.getName());
                return;
            }
        }

        HaxeSdkData sdkData = HaxeSdkUtil.testHaxeSdk(homePath);
        if ( sdkData == null ) {
            return;
        }

        LOG.info("We have a bundled haXe sdk (at " + homePath + ") that is not in the jdk table. Attempting to add");
        try {
            final ProjectJdkImpl bundledHaxeSdk;
            final HaxeSdkType haxeSdkType = HaxeSdkType.getInstance();

            haxeSdkType.setSdkData(sdkData);
            String newSdkName = SdkConfigurationUtil.createUniqueSdkName(haxeSdkType, sdkData.getHomePath(), Arrays.asList(jdkTable.getAllJdks()));
            bundledHaxeSdk = new ProjectJdkImpl(newSdkName, haxeSdkType);
            bundledHaxeSdk.setHomePath(homePath);
            ApplicationManager.getApplication().runWriteAction(new Runnable() {
                @Override
                public void run() {
                    final SdkModificator sdkModificator = bundledHaxeSdk.getSdkModificator();
                    haxeSdkType.setupSdkPaths(bundledHaxeSdk);
                    jdkTable.addJdk(bundledHaxeSdk);
                }
            });

        } catch (Exception e) {
            LOG.error("Exception while adding the bundled sdk", e);
        }
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "HaxeBundledSdkDetector";
    }
}