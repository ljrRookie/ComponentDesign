package com.ljr.order.debug;

import android.app.Application;
import android.util.Log;

import com.ljr.common.utils.Cons;


public class Order_DebugApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Cons.TAG, "order/debug/Order_DebugApplication");
    }
}
