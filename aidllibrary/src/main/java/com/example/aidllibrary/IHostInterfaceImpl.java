package com.example.aidllibrary;

import android.os.RemoteException;
import android.util.Log;

public class IHostInterfaceImpl extends IHostInterface.Stub {
    private static final String TAG = IHostInterfaceImpl.class.getSimpleName();
    @Override
    public void call(Params param) throws RemoteException {
        Log.i(TAG, param.toString());
    }
}
