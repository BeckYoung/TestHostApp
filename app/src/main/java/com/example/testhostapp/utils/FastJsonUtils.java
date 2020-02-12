package com.example.testhostapp.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import java.util.Collections;
import java.util.List;

public class FastJsonUtils {
    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (TextUtils.isEmpty(text)) {
            return Collections.emptyList();
        }
        List<T> list = JSON.parseArray(text, clazz);
        if (list == null) {
            return Collections.emptyList();
        } else {
            return list;
        }
    }
}
