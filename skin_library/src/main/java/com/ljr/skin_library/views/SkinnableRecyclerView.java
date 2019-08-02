package com.ljr.skin_library.views;

import android.content.Context;

import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.ljr.skin_library.R;
import com.ljr.skin_library.base.ViewsMatch;
import com.ljr.skin_library.model.AttrsBean;

public class SkinnableRecyclerView extends RecyclerView implements ViewsMatch {

    private AttrsBean attrsBean;

    public SkinnableRecyclerView(Context context) {
        this(context, null);
    }

    public SkinnableRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinnableRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        attrsBean = new AttrsBean();

        // 根据自定义属性，匹配控件属性的类型集合，如：background
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.SkinnableRecyclerView,
                defStyleAttr, 0);
        // 存储到临时JavaBean对象
        attrsBean.saveViewResource(typedArray, R.styleable.SkinnableRecyclerView);
        // 这一句回收非常重要！obtainStyledAttributes()有语法提示！！
        typedArray.recycle();
    }

    @Override
    public void skinnableView() {

    }
}
