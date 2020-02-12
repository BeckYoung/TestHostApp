package com.example.testhostapp.model;

import android.content.Context;

import com.example.testhostapp.utils.FastJsonUtils;
import com.example.testhostapp.utils.LogUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class PluginUseManager {
    private static final String TAG = "PluginUseManager";
    public List<PluginUseInfo> getAllPluginUseInfoFromAssets(Context context) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open("plugin_info.json");
            byte[] buffers = new byte[4096];
            int len = -1;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(buffers)) != -1) {
                String str = new String(buffers, 0, len);
                sb.append(str);
            }
            String data = sb.toString();
            return FastJsonUtils.parseArray(data, PluginUseInfo.class);
        } catch (IOException e) {
            LogUtils.w(TAG, "IOException");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LogUtils.w(TAG, "close IOException");
            }
        }
        return Collections.emptyList();
    }
}
