package com.ljr.order.debug;

import android.os.Bundle;
import android.util.Log;

import com.ljr.common.utils.Cons;
import com.ljr.order.R;


public class Order_DebugActivity extends Order_DebugBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_debug);

        Log.e(Cons.TAG, "order/debug/Order_DebugActivity");
    }
}
