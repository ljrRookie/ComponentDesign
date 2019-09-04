package com.ljr.personal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.ljr.arouter_annotation.ARouter;
import com.ljr.arouter_annotation.Parameter;
import com.ljr.arouter_api.ParameterManager;
import com.ljr.arouter_api.RouterManager;
import com.ljr.common.RecordPathManager;
import com.ljr.common.base.BaseActivity;
import com.ljr.common.user.BaseUser;
import com.ljr.common.user.IUser;
import com.ljr.common.utils.Cons;
import com.ljr.skin_library.base.SkinActivity;
import com.ljr.skin_library.utils.PreferencesUtils;

import java.io.File;

@ARouter(path = "/personal/Personal_MainActivity")
public class Personal_MainActivity  extends SkinActivity {
    @Parameter
    String name;

    @Parameter
    int age;
    @Parameter(name = "/app/getUserInfo")
    IUser iUser;

    @Parameter
    boolean isSuccess;

    private String skinPath;

    @Override
    protected boolean openChangeSkin() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity_main);
        Log.e(Cons.TAG, "personal/Personal_MainActivity");
       /* if (getIntent() != null) {
            String content = getIntent().getStringExtra("name");
            Log.e(Cons.TAG, "接收参数值：" + content);
        }*/
        ParameterManager.getInstance().loadParameter(this);
        Log.e(Cons.TAG, "接收参数值：" + toString());
        initView();
        BaseUser userInfo = iUser.getUserInfo();
        if (userInfo != null) {
            Log.e(Cons.TAG, userInfo.getName() + " / "
                    + userInfo.getAccount() + " / "
                    + userInfo.getPassword());
        }
    }

    @Override
    public String toString() {
        return "Personal_MainActivity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isSuccess=" + isSuccess +
                '}';
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
        // 类加载跳转，可以成功。维护成本较高且容易出现人为失误
       /* try {
            Class targetClass = Class.forName("com.ljr.componentdesign.MainActivity");
            Intent intent = new Intent(this, targetClass);
            intent.putExtra("name", "simon");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/


        /*Class<?> targetClass = RecordPathManager.getTargetClass("app", "MainActivity");

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

    public void jumpOrder(View view) {
        // 类加载跳转，可以成功。维护成本较高且容易出现人为失误
       /* try {
            Class targetClass = Class.forName("com.ljr.order.Order_MainActivity");
            Intent intent = new Intent(this, targetClass);
            intent.putExtra("name", "simon");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
   /*     Class<?> targetClass = RecordPathManager.getTargetClass("order", "Order_MainActivity");

        if (targetClass == null) {
            Log.e(Cons.TAG, "获取跳转targetClass失败！");
            return;
        }

        Intent intent = new Intent(this, targetClass);
        intent.putExtra("name", "simon");
        startActivity(intent);*/
        RouterManager.getInstance()
                .build("/order/Order_MainActivity")
                .withString("name", "simon")
                .navigation(this);
    }
}
