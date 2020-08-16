package com.example.aidllibrary.V2;

import android.os.RemoteException;
import android.util.Log;

import com.example.aidllibrary.IHostInterface;
import com.example.aidllibrary.Params;

public class IHostInterfaceV2Impl extends IHostInterfaceV2.Stub {
    private static final String TAG = IHostInterfaceV2Impl.class.getSimpleName();

//    @Override
//    public void call(AIDLBridge bridge) throws RemoteException {
//        if (bridge == null) {
//            Log.i(TAG, "bridge is null");
//            return;
//        }
//        hostCallback = bridge.getHostCallback();
//        Params params = new Params();
//        params.put("Host", "I'm from Host String");
//        setMsgToPlugin(params);
//        Log.i(TAG, bridge.toString());
//    }

    public void setMsgToPlugin(Params params) {
//        if (hostCallback != null) {
//            hostCallback.onLive(params);
//        }
    }

    @Override
    public void setHostCallback(HostCallback hostCallback) throws RemoteException {

    }

    @Override
    public void setPluginCallback(PluginCallback pluginCallback) throws RemoteException {

    }
}
