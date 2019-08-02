package com.ljr.order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.ljr.common.RecordPathManager;
import com.ljr.common.base.BaseActivity;
import com.ljr.common.utils.Cons;
import com.ljr.skin_library.base.SkinActivity;
import com.ljr.skin_library.utils.PreferencesUtils;

import java.io.File;

public class Order_MainActivity extends SkinActivity {

    private String skinPath;

    @Override
    protected boolean openChangeSkin() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_main);
        Log.e(Cons.TAG, "order/Order_MainActivity");
        if (getIntent() != null) {
            String content = getIntent().getStringExtra("name");
            Log.e(Cons.TAG, "接收参数值：" + content);
        }
        // File.separator含义：拼接 /
        skinPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "debug.skin";
        if (("debug").equals(PreferencesUtils.getString(this, "currentSkin"))) {
            skinDynamic(skinPath, R.color.skin_item_color);
        } else {
            defaultSkin(R.color.colorPrimary);
        }
    }

    public void jumpApp(View view) {
        // 类加载跳转，可以成功。维护成本较高且容易出现人为失误
        /*try {
            Class targetClass = Class.forName("com.ljr.componentdesign.MainActivity");
            Intent intent = new Intent(this, targetClass);
            intent.putExtra("name", "simon");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        Class<?> targetClass = RecordPathManager.getTargetClass("app", "MainActivity");

        if (targetClass == null) {
            Log.e(Cons.TAG, "获取跳转targetClass失败！");
            return;
        }

        Intent intent = new Intent(this, targetClass);
        intent.putExtra("name", "simon");
        startActivity(intent);
    }

    public void jumpPersonal(View view) {
        // 类加载跳转，可以成功。维护成本较高且容易出现人为失误
       /* try {
            Class targetClass = Class.forName("com.ljr.personal.Personal_MainActivity");
            Intent intent = new Intent(this, targetClass);
            intent.putExtra("name", "simon");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        Class<?> targetClass = RecordPathManager.getTargetClass("personal", "Personal_MainActivity");

        if (targetClass == null) {
            Log.e(Cons.TAG, "获取跳转targetClass失败！");
            return;
        }

        Intent intent = new Intent(this, targetClass);
        intent.putExtra("name", "simon");
        startActivity(intent);
    }
}
