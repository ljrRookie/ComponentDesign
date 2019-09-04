package com.ljr.order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.ljr.arouter_annotation.ARouter;
import com.ljr.arouter_annotation.Parameter;
import com.ljr.arouter_api.ParameterManager;
import com.ljr.arouter_api.RouterManager;
import com.ljr.common.RecordPathManager;
import com.ljr.common.user.BaseUser;
import com.ljr.common.user.IUser;
import com.ljr.common.utils.Cons;
import com.ljr.skin_library.base.SkinActivity;
import com.ljr.skin_library.utils.PreferencesUtils;

import java.io.File;

@ARouter(path = "/order/Order_MainActivity")
public class Order_MainActivity extends SkinActivity {
    @Parameter
    String name;
    private String skinPath;

    @Parameter(name = "/app/getUserInfo")
    IUser iUser;

    @Override
    protected boolean openChangeSkin() {
        return true;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_main);
        Log.e(Cons.TAG, "order/Order_MainActivity");

       /* if (getIntent() != null) {
       原始传参
          *//*  String content = getIntent().getStringExtra("name");
            Log.e(Cons.TAG, "接收参数值：" + content);*//*
        路由传参
            new Order_MainActivity$$Parameter().loadParameter(this);
            Log.e(Cons.TAG, "接收参数值：" + name);
        }*/
        // 懒加载方式，跳到哪加载哪个类
        ParameterManager.getInstance().loadParameter(this);
        Log.e(Cons.TAG, "接收参数值：" + name);
        initView();
        BaseUser userInfo = iUser.getUserInfo();
        if (userInfo != null) {
            Log.e(Cons.TAG, userInfo.getName() + " / "
                    + userInfo.getAccount() + " / "
                    + userInfo.getPassword());
        }
    }

    private void initView() {
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
        // 1.类加载跳转，可以成功。维护成本较高且容易出现人为失误
        /*try {
            Class targetClass = Class.forName("com.ljr.componentdesign.MainActivity");
            Intent intent = new Intent(this, targetClass);
            intent.putExtra("name", "simon");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        // 1.1类加载跳转，可以成功。统一在Application管理
       /* Class<?> targetClass = RecordPathManager.getTargetClass("app", "MainActivity");

        if (targetClass == null) {
            Log.e(Cons.TAG, "获取跳转targetClass失败！");
            return;
        }

        Intent intent = new Intent(this, targetClass);
        intent.putExtra("name", "simon");
        startActivity(intent);*/
        RouterManager.getInstance()
                .build("/app/MainActivity")
                .withResultString("call", "I'am comeback!")
                .navigation(this);
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

     /*   Class<?> targetClass = RecordPathManager.getTargetClass("personal", "Personal_MainActivity");

        if (targetClass == null) {
            Log.e(Cons.TAG, "获取跳转targetClass失败！");
            return;
        }

        Intent intent = new Intent(this, targetClass);
        intent.putExtra("name", "simon");
        startActivity(intent);*/
        RouterManager.getInstance()
                .build("/personal/Personal_MainActivity")
                .withString("name", "simon")
                .navigation(this);
    }
}
