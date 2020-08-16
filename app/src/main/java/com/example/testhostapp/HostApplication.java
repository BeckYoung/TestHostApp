package com.example.testhostapp;

import android.content.Context;
import android.content.Intent;

import com.example.testhostapp.utils.LogUtils;
import com.qihoo360.replugin.RePluginApplication;
import com.qihoo360.replugin.RePluginCallbacks;
import com.qihoo360.replugin.RePluginConfig;
import com.qihoo360.replugin.RePluginEventCallbacks;
import com.qihoo360.replugin.model.PluginInfo;

import java.util.ArrayList;
import java.util.List;


public class HostApplication extends RePluginApplication {
    public static final String TAG = "HostApplication";
    public HostEventCallbacks hostEventCallbacks;

    @Override
    public void onCreate() {
        super.onCreate();
        HostApplicationHelper.getInstance().init(this);
    }

    /**
     * RePlugin允许提供各种“自定义”的行为，让您“无需修改源代码”，即可实现相应的功能
     */
    @Override
    protected RePluginConfig createConfig() {
        RePluginConfig c = new RePluginConfig();

        // 允许“插件使用宿主类”。默认为“关闭”
        c.setUseHostClassIfNotFound(true);

        // RePlugin默认会对安装的外置插件进行签名校验，这里先关掉，避免调试时出现签名错误
        c.setVerifySign(false);

        hostEventCallbacks = new HostEventCallbacks(this);
        // 针对“安装失败”等情况来做进一步的事件处理
        c.setEventCallbacks(hostEventCallbacks);

        // 若宿主为Release，则此处应加上您认为"合法"的插件的签名，例如，可以写上"宿主"自己的。
        // RePlugin.addCertSignature("AAAAAAAAA");

        // 在Art上，优化第一次loadDex的速度
        c.setOptimizeArtLoadDex(true);
        return c;
    }

    @Override
    protected RePluginCallbacks createCallbacks() {
        return new HostCallbacks(this);
    }

    /**
     * 宿主针对RePlugin的自定义行为
     */
    private class HostCallbacks extends RePluginCallbacks {

        private static final String TAG = "HostCallbacks";

        private HostCallbacks(Context context) {
            super(context);
        }

        @Override
        public boolean onPluginNotExistsForActivity(Context context, String plugin, Intent intent, int process) {
            // 当插件"没有安装"时触发此逻辑，可打开您的"下载对话框"并开始下载。
            // 其中"intent"需传递到"对话框"内，这样可在下载完成后，打开这个插件的Activity
            if (BuildConfig.DEBUG) {
                LogUtils.d(TAG, "onPluginNotExistsForActivity: plugin=", plugin, "; intent=", intent, "; process", process);
            }
//            if (RePlugin.isPluginInstalled(plugin)) {
//                LogUtils.w(TAG, "plugin is installed, but activity not found");
//                return true;
//            }
//            final String PLUGIN_PATH = File.separator + "sdcard" + File.separator + "plugin.apk";
//            if (!new File(PLUGIN_PATH).exists()) {
//                LogUtils.w(TAG, "file not exist");
//                return false;
//            }
//            PluginInfo pluginInfo = RePlugin.install(PLUGIN_PATH);
//            if (pluginInfo != null) {
//                RePlugin.preload(pluginInfo);
//            }
            return super.onPluginNotExistsForActivity(context, plugin, intent, process);
        }
    }

    public class HostEventCallbacks extends RePluginEventCallbacks {
        private List<PluginEventCallback> callbacks = new ArrayList<>();
        public HostEventCallbacks(Context context) {
            super(context);
        }

        @Override
        public void onInstallPluginSucceed(PluginInfo info) {
            super.onInstallPluginSucceed(info);
            Intent intent = new Intent("com.plugin.INSTALL_PLUGIN_SUCCESS");
            onCallback(intent);
        }

        @Override

        public void onInstallPluginFailed(String path, InstallResult code) {
            // 当插件安装失败时触发此逻辑。您可以在此处做“打点统计”，也可以针对安装失败情况做“特殊处理”
            // 大部分可以通过RePlugin.install的返回值来判断是否成功
            if (BuildConfig.DEBUG) {
                LogUtils.d(TAG, "onInstallPluginFailed: Failed! path=", path, "; r=", code);
            }
            super.onInstallPluginFailed(path, code);
            Intent intent = new Intent("com.plugin.INSTALL_PLUGIN_FAILED");
            onCallback(intent);
        }

        @Override
        public void onStartActivityCompleted(String plugin, String activity, boolean result) {
            // 当打开Activity成功时触发此逻辑，可在这里做一些APM、打点统计等相关工作
            super.onStartActivityCompleted(plugin, activity, result);
        }

        private void onCallback(Intent intent) {
            for(PluginEventCallback eventCallback : callbacks) {
                if (eventCallback == null) {
                    continue;
                }
                eventCallback.onEvent(intent);
            }
        }

        public void setCallback(PluginEventCallback callback) {
            this.callbacks.add(callback);
        }

        public void removeCallback(PluginEventCallback callback) {
            this.callbacks.remove(callback);
        }
    }
}
