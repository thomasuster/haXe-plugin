package com.intellij.plugins.haxe.config.sdk;

import com.intellij.openapi.projectRoots.*;
import com.intellij.util.SystemProperties;
import org.jdom.Element;

public class HaxeSdkType extends SdkType {
    public HaxeSdkType() {
        super("haXe SDK");
    }

    public static HaxeSdkType getInstance() {
        return SdkType.findInstance(HaxeSdkType.class);
    }

    @Override
    public String getPresentableName() {
        return "haXe SDK";
    }

    @Override
    public String suggestSdkName(String currentSdkName, String sdkHome) {
        return "haXe SDK";
    }

    @Override
    public String suggestHomePath() {
        return SystemProperties.getUserHome();
    }

    @Override
    public boolean isValidSdkHome(String path) {
        //TODO test sdk
        return true;
    }

    @Override
    public AdditionalDataConfigurable createAdditionalDataConfigurable(SdkModel sdkModel, SdkModificator sdkModificator) {
        return null;
    }

    @Override
    public void saveAdditionalData(SdkAdditionalData additionalData, Element additional) {
    }
}
