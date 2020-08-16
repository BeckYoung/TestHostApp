// PluginCallback.aidl
package com.example.aidllibrary.V2;

import com.example.aidllibrary.Params;

interface PluginCallback {
    void onLive(in Params params);
}