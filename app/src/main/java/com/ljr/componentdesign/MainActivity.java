package com.ljr.componentdesign;

import android.Manifest;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ljr.common.utils.Cons;
import com.ljr.componentdesign.adapter.MusicAdapter;
import com.ljr.componentdesign.bean.MusicBean;
import com.ljr.order.Order_MainActivity;
import com.ljr.personal.Personal_MainActivity;
import com.ljr.skin_library.base.SkinActivity;
import com.ljr.skin_library.utils.PreferencesUtils;

import java.io.File;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends SkinActivity {
    private String skinPath;
    private RecyclerView mRecyclerView;
    private MusicAdapter mMusicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            mMusicAdapter.notifyDataSetChanged();
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
            mMusicAdapter.notifyDataSetChanged();
        }

    }
    public void jumpOrder(View view) {
        Intent intent = new Intent(this, Order_MainActivity.class);
        intent.putExtra("name", "simon");
        startActivity(intent);
    }

    public void jumpPersonal(View view) {
        Intent intent = new Intent(this, Personal_MainActivity.class);
        intent.putExtra("name", "simon");
        startActivity(intent);
    }

    @Override
    protected boolean openChangeSkin() {
        return true;
    }


}
