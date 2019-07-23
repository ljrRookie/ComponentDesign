package com.ljr.order;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ljr.common.base.BaseActivity;
import com.ljr.common.utils.Cons;

public class Order_MainActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.order_activity_main);
        Log.e(Cons.TAG, "order/Order_MainActivity");
    }
}
