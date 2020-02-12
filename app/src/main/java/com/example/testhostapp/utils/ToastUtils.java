package com.example.testhostapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

public class ToastUtils {
    private static final String TAG = "ToastUtils";
    public static void showMessage(Context context, int resId) {
        try {
            Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
        } catch (Resources.NotFoundException e) {
            LogUtils.e(TAG, "resource not found");
        }
    }
}
