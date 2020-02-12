package com.example.testhostapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.usage.ExternalStorageStats;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.testhostapp.model.PluginUseInfo;
import com.example.testhostapp.model.PluginUseManager;
import com.example.testhostapp.utils.LogUtils;
import com.example.testhostapp.utils.ToastUtils;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private static final String PLUGIN_NAME = "plugin.apk";
    private AppCompatEditText editText;
    private Button btnInstall;
    private Button btnOpen;
    private ImageView ivBelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_text);
        btnInstall = findViewById(R.id.btn_install);
        btnOpen = findViewById(R.id.btn_open);
        ivBelle = findViewById(R.id.iv_belle);
        editText.setText(PLUGIN_NAME);
        btnInstall.setOnClickListener(this);
        btnOpen.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_install:
                pluginInstall();
                break;
            case R.id.btn_open:
                pluginStart();
                break;
            default:
                break;
        }
    }

    private void pluginInstall() {
        LogUtils.d(TAG, "pluginInstall");
        initImageView();
        Editable text = editText.getText();
        if (TextUtils.isEmpty(text)) {
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), PLUGIN_NAME);
        String pluginPath = text.toString();
        if (!file.exists()) {
            LogUtils.w(TAG, "file=", file.getPath(), " not found");
            return;
        }
        PluginInfo pluginInfo = RePlugin.install(file.getPath());
        if (pluginInfo != null) {
            RePlugin.preload(pluginInfo);
            ToastUtils.showMessage(this, R.string.app_main_install_success);
        }
    }

    private void pluginStart() {
        LogUtils.d(TAG, "pluginStart");
        PluginUseManager pluginUseManager = new PluginUseManager();
        List<PluginUseInfo> pluginUseInfoList = pluginUseManager.getAllPluginUseInfoFromAssets(this);
        PluginUseInfo pluginUseInfo = pluginUseInfoList.isEmpty() ? null : pluginUseInfoList.get(0);
        if (pluginUseInfo != null) {
            Intent intent = RePlugin.createIntent(pluginUseInfo.getPluginName(), pluginUseInfo.getLauncherActivity());
            RePlugin.startActivity(this, intent);
        } else {
            LogUtils.w(TAG, "pluginUseInfo is null");
        }
    }

    private void initImageView() {
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578142230410&di=17edd80d1b94cf7b94728f37de82df0b&imgtype=0&src=http%3A%2F%2Fa.vpimg3.com%2Fupload%2Fmerchandise%2F353690%2FDUSHI-063804-0021-4.jpg";
        Glide.with(this).load(url).into(ivBelle);
    }
}
