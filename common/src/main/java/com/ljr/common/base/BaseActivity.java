package com.ljr.common.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ljr.common.utils.Cons;

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate( @Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.e(Cons.TAG, "common/BaseActivity");
    }
}
