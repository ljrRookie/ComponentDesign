package com.ljr.componentdesign;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ljr.arouter_annotation.ARouter;
import com.ljr.arouter_annotation.Parameter;
import com.ljr.arouter_api.ParameterManager;
import com.ljr.arouter_api.RouterManager;
import com.ljr.common.user.BaseUser;
import com.ljr.common.user.IUser;
import com.ljr.common.utils.Cons;
import com.ljr.componentdesign.adapter.MusicAdapter;
import com.ljr.componentdesign.bean.MusicBean;
import com.ljr.order.Order_MainActivity;
import com.ljr.personal.Personal_MainActivity;
import com.ljr.skin_library.base.SkinActivity;
import com.ljr.skin_library.utils.PreferencesUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// 小项目或者子模块类重复率不高，可以直接写：path = "/MainActivity"
@ARouter(path = "/app/MainActivity")
public class MainActivity extends SkinActivity {
    private String skinPath;
    private RecyclerView mRecyclerView;
    private MusicAdapter mMusicAdapter;
    private static final int Order_MainActivity_REQ = 1000;
    private static final int Personal_MainActivity_REQ = 1001;
   /* @Parameter(name = "/personal/getUserInfo")
    IUser iUser;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (BuildConfig.isRelease) {
            Log.e(Cons.TAG, "当前为：集成化模式，除app可运行，其他子模块都是Android Library");
        } else {
            Log.e(Cons.TAG, "当前为：组件化模式，app/order/personal子模块都可独立运行");
        }
        // 懒加载方式，跳到哪加载哪个类
        ParameterManager.getInstance().loadParameter(this);
      /*  BaseUser userInfo = iUser.getUserInfo();
        if (userInfo != null) {
            Log.e(Cons.TAG, userInfo.getName() + " / "
                    + userInfo.getAccount() + " / "
                    + userInfo.getPassword());
        }*/
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // File.separator含义：拼接 /
        skinPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "debug.skin";

        // 运行时权限申请（6.0+）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (checkSelfPermission(perms[0]) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(perms, 200);
            }
        }
        Random rand = new Random();
        List<MusicBean> musicList = new ArrayList<>();
        musicList.add(new MusicBean("像你这样的人", "毛不易", rand.nextInt(10) + "." + rand.nextInt(10)));
        musicList.add(new MusicBean("演员", "薛之谦", rand.nextInt(10) + "." + rand.nextInt(10)));
        musicList.add(new MusicBean("不要说话", "陈奕迅", rand.nextInt(10) + "." + rand.nextInt(10)));
        musicList.add(new MusicBean("攀登", "邓紫棋", rand.nextInt(10) + "." + rand.nextInt(10)));
        for (int i = 0; i < 20; i++) {
            musicList.add(new MusicBean("像你这样的人", "毛不易", rand.nextInt(10) + "." + rand.nextInt(10)));
            musicList.add(new MusicBean("爱的宣言", "周柏豪", rand.nextInt(10) + "." + rand.nextInt(10)));
            musicList.add(new MusicBean("饿狼传说", "张学友", rand.nextInt(10) + "." + rand.nextInt(10)));
        }
        mMusicAdapter = new MusicAdapter(R.layout.item_music, musicList);
        mMusicAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(mMusicAdapter);
        if (("debug").equals(PreferencesUtils.getString(this, "currentSkin"))) {
            skinDynamic(skinPath, R.color.skin_item_color);
        } else {
            defaultSkin(R.color.colorPrimary);
        }
    }

    // 换肤按钮（api限制：5.0版本）
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void skinDynamic(View view) {
        // 真实项目中：需要先判断当前皮肤，避免重复操作！
        if (!("debug").equals(PreferencesUtils.getString(this, "currentSkin"))) {
            Log.e(Cons.TAG, "-------------start-------------");
            long start = System.currentTimeMillis();

            skinDynamic(skinPath, R.color.skin_item_color);
            PreferencesUtils.putString(this, "currentSkin", "debug");

            long end = System.currentTimeMillis() - start;
            Log.e(Cons.TAG, "换肤耗时（毫秒）：" + end);
            Log.e(Cons.TAG, "-------------end---------------");
        }

    }
    // 默认按钮（api限制：5.0版本）
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void skinDefault(View view) {
        if (!("default").equals(PreferencesUtils.getString(this, "currentSkin"))) {
            Log.e(Cons.TAG, "-------------start-------------");
            long start = System.currentTimeMillis();

            defaultSkin(R.color.colorPrimary);
            PreferencesUtils.putString(this, "currentSkin", "default");

            long end = System.currentTimeMillis() - start;
            Log.e(Cons.TAG, "还原耗时（毫秒）：" + end);
            Log.e(Cons.TAG, "-------------end---------------");
        }

    }
    public void jumpOrder(View view) {
        // 1. 第一种跳转
      /*  Intent intent = new Intent(this, Order_MainActivity.class);
        intent.putExtra("name", "simon");
        startActivity(intent);*/
      // 2. 第二种跳转
      //最终集成化模块，所有子模块app/order/personal通过APT生成的类文件都会打包到apk里面，不用担心找不到
       /* ARouter$$Group$$order group = new ARouter$$Group$$order();
        Map<String, Class<? extends ARouterLoadPath>> map = group.loadGroup();
        //通过order组名获取对应路由路径对象
        Class<? extends ARouterLoadPath> clazz = map.get("order");
        try {
            // 类加载动态加载路由路径对象
            ARouter$$Path$$order path = (ARouter$$Path$$order) clazz.newInstance();
            Map<String, RouterBean> pathMap = path.loadPath();
            // 获取目标对象封装
            RouterBean bean = pathMap.get("/order/Order_MainActivity");

            if (bean != null) {
                Intent intent = new Intent(this, bean.getClazz());
                intent.putExtra("name", "simon");
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
       //3.路由跳转
        RouterManager.getInstance()
                .build("/order/Order_MainActivity")
                .withString("name", "simon")
                .navigation(this,Order_MainActivity_REQ);
    }

    public void jumpPersonal(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("name", "simon");
        bundle.putInt("age", 35);
        bundle.putBoolean("isSuccess", true);
        RouterManager.getInstance()
                .build("/personal/Personal_MainActivity")
                .withBundle(bundle)
                .navigation(this, Personal_MainActivity_REQ);
    }

    @Override
    protected boolean openChangeSkin() {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(Cons.TAG, "requestCode : "+requestCode);
        Log.e(Cons.TAG, "resultCode : "+resultCode);
    }
}
