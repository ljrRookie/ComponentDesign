package com.ljr.common.base;

import android.app.Application;
import android.util.Log;

import com.ljr.common.utils.Cons;
import com.ljr.skin_library.SkinManager;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Cons.TAG, "common/BaseApplication");
        SkinManager.init(this);
    }
}
