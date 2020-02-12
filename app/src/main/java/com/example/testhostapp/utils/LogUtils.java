package com.example.testhostapp.utils;

import android.util.Log;

public class LogUtils {

    public static void v(String tag, Object... objects) {
        String msg = getMessage(objects);
        Log.v(tag, msg);
    }

    public static void d(String tag, Object... objects) {
        String msg = getMessage(objects);
        Log.d(tag, msg);
    }

    public static void i(String tag, Object... objects) {
        String msg = getMessage(objects);
        Log.i(tag, msg);
    }

    public static void w(String tag, Object... objects) {
        String msg = getMessage(objects);
        Log.w(tag, msg);
    }

    public static void e(String tag, Object... objects) {
        String msg = getMessage(objects);
        Log.w(tag, msg);
    }

    private static String getMessage(Object... objects) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : objects) {
            stringBuilder.append(o);
        }
        return stringBuilder.toString();
    }
}
