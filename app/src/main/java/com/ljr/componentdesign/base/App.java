package com.ljr.componentdesign.base;

import com.ljr.common.RecordPathManager;
import com.ljr.common.base.BaseApplication;
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
    }


}
