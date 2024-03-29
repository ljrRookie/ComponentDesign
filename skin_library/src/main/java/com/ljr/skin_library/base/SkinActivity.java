package com.ljr.skin_library.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljr.skin_library.SkinManager;
import com.ljr.skin_library.core.CustomAppCompatViewInflater;
import com.ljr.skin_library.utils.ActionBarUtils;
import com.ljr.skin_library.utils.NavigationUtils;
import com.ljr.skin_library.utils.StatusBarUtils;

/**
 * 换肤Activity父类
 *
 * 用法：
 * 1、继承此类
 * 2、重写openChangeSkin()方法
 */
public class SkinActivity extends AppCompatActivity {

    private CustomAppCompatViewInflater mViewInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(layoutInflater,this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if(openChangeSkin()){
            if(mViewInflater == null){
                mViewInflater = new CustomAppCompatViewInflater(context);
            }
            mViewInflater.setName(name);
            mViewInflater.setAttrs(attrs);
            return mViewInflater.autoMatch();
        }
        return super.onCreateView(parent, name, context, attrs);

    }
    /**
     * @return 是否开启换肤，增加此开关是为了避免开发者误继承此父类，导致未知bug
     */
    protected boolean openChangeSkin() {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void defaultSkin(int themeColorId) {
        this.skinDynamic(null, themeColorId);
    }
    /**
     * 动态换肤（api限制：5.0版本）
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void skinDynamic(String skinPath, int themeColorId) {
        SkinManager.getInstance().loaderSkinResources(skinPath);

        if (themeColorId != 0) {
            int themeColor = SkinManager.getInstance().getColor(themeColorId);
            StatusBarUtils.forStatusBar(this, themeColor);
            NavigationUtils.forNavigation(this, themeColor);
            ActionBarUtils.forActionBar(this, themeColor);
        }

        applyViews(getWindow().getDecorView());
    }

    /**
     * 控件回调监听，匹配上则给控件执行换肤方法
     */
    protected void applyViews(View view) {
        if (view instanceof ViewsMatch) {
            ViewsMatch viewsMatch = (ViewsMatch) view;
            viewsMatch.skinnableView();
        }

        if (view instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view;
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                applyViews(parent.getChildAt(i));
            }
        }
    }
}
