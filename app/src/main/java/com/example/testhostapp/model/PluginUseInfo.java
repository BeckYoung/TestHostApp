package com.example.testhostapp.model;

public class PluginUseInfo {
    private String pluginName;
    private String launcherActivity;
    private String launcherParams;
    private String pluginService;

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getLauncherActivity() {
        return launcherActivity;
    }

    public void setLauncherActivity(String launcherActivity) {
        this.launcherActivity = launcherActivity;
    }

    public String getLauncherParams() {
        return launcherParams;
    }

    public void setLauncherParams(String launcherParams) {
        this.launcherParams = launcherParams;
    }

    public String getPluginService() {
        return pluginService;
    }

    public void setPluginService(String pluginService) {
        this.pluginService = pluginService;
    }
}
