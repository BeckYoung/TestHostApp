// HostCallback.aidl
package com.example.aidllibrary.V2;

import com.example.aidllibrary.Params;

interface HostCallback {
    void onLive(out Params params);
}