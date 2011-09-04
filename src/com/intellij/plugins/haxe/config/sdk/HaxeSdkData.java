package com.intellij.plugins.haxe.config.sdk;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.projectRoots.SdkAdditionalData;
import com.intellij.openapi.projectRoots.SdkModel;
import com.intellij.util.xmlb.XmlSerializerUtil;

public class HaxeSdkData implements SdkAdditionalData, PersistentStateComponent<HaxeSdkData> {
    private String homePath = "";
    private String version = "";

    public HaxeSdkData() {
    }

    public HaxeSdkData(String homePath, String version) {
        this.homePath = homePath;
        this.version = version;
    }

    public String getHomePath() {
        return homePath;
    }

    public String getVersion() {
        return version;
    }

    @SuppressWarnings({"CloneDoesntCallSuperClone"})
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void checkValid(SdkModel sdkModel) throws ConfigurationException {

    }

    public HaxeSdkData getState() {
        return this;
    }

    public void loadState(HaxeSdkData state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}