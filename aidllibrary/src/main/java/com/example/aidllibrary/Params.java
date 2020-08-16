package com.example.aidllibrary;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Params implements Parcelable {
    private Map<String, Object> mMap;

    public Params(){
        mMap = new HashMap<>();
    }

    private Params(Parcel in) {
        mMap = new HashMap<>();
        in.readMap(mMap, HashMap.class.getClassLoader());
    }

    public void put(String key, Object value) {
        mMap.put(key, value);
    }

    public Object get(String key) {
        return mMap.get(key);
    }

    public static final Creator<Params> CREATOR = new Creator<Params>() {
        @Override
        public Params createFromParcel(Parcel in) {
            return new Params(in);
        }

        @Override
        public Params[] newArray(int size) {
            return new Params[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(mMap);
    }

    @NonNull
    @Override
    public String toString() {
        return "Params[" + mMap.toString() + "]";
    }
}
