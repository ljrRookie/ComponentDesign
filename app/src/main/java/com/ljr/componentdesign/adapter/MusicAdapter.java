package com.ljr.componentdesign.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljr.componentdesign.R;
import com.ljr.componentdesign.bean.MusicBean;

import java.util.List;

public class MusicAdapter extends BaseQuickAdapter<MusicBean, BaseViewHolder> {
    public MusicAdapter(int item_music, List<MusicBean> musicList) {
        super(item_music,musicList);
    }

    @Override
    protected void convert(BaseViewHolder helper, MusicBean musicBean) {

        helper.setText(R.id.tv_content, musicBean.getTitle());
        helper.setText(R.id.tv_name, musicBean.getPerson());
        helper.setText(R.id.tv_size, musicBean.getSize() + "M");
    }
}
