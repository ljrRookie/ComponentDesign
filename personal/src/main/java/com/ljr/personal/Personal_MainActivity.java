package com.ljr.personal;

import android.os.Bundle;
import android.util.Log;

import com.ljr.common.base.BaseActivity;
import com.ljr.common.utils.Cons;


public class Personal_MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity_main);
        Log.e(Cons.TAG, "personal/Personal_MainActivity");
    }
}
