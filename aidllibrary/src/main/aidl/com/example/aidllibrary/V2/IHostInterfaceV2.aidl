// IHostInterfaceV2.aidl
package com.example.aidllibrary.V2;

import com.example.aidllibrary.V2.HostCallback;
import com.example.aidllibrary.V2.PluginCallback;

// Declare any non-default types here with import statements

interface IHostInterfaceV2 {
//    void call(inout AIDLBridge bridge);
      void setHostCallback(in HostCallback hostCallback);
      void setPluginCallback(in PluginCallback pluginCallback);
}
