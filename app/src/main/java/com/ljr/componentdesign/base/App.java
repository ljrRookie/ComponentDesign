package com.ljr.componentdesign.base;

import android.util.Log;

import com.ljr.common.RecordPathManager;
import com.ljr.common.base.BaseApplication;
import com.ljr.common.utils.Cons;
import com.ljr.componentdesign.BuildConfig;
import com.ljr.componentdesign.MainActivity;
import com.ljr.order.Order_MainActivity;
import com.ljr.personal.Personal_MainActivity;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        // 如果项目有100个Activity，这种加法会不会太那个？
        RecordPathManager.joinGroup("app", "MainActivity", MainActivity .class);
        RecordPathManager.joinGroup("order", "Order_MainActivity", Order_MainActivity .class);
        RecordPathManager.joinGroup("personal", "Personal_MainActivity", Personal_MainActivity .class);
        if (BuildConfig.isRelease) {
            Log.e(Cons.TAG, "当前为：集成化模式，除app可运行，其他子模块都是Android Library");
        } else {
            Log.e(Cons.TAG, "当前为：组件化模式，app/order/personal子模块都可独立运行");
        }
    }


}
