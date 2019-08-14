package com.ljr.componentdesign.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljr.componentdesign.R;
import com.ljr.componentdesign.bean.MusicBean;

import java.util.List;

public class MusicAdapter extends BaseQuickAdapter<MusicBean, MusicAdapter.SkinViewHolder> {
    public MusicAdapter(int item_music, List<MusicBean> musicList) {
        super(item_music,musicList);
    }

    @Override
    protected void convert(SkinViewHolder helper, MusicBean musicBean) {
        View convertView = helper.getConvertView();

        helper.setText(R.id.tv_content, musicBean.getTitle());
        helper.setText(R.id.tv_name, musicBean.getPerson());
        helper.setText(R.id.tv_size, musicBean.getSize() + "M");
    }


    public static class SkinViewHolder extends BaseViewHolder {
        private TextView textView;

        public SkinViewHolder(@NonNull View itemView) {
            super(itemView);
          //  textView = itemView.findViewById(R.id.tv_item);
          //  ChangeSkinHelper.setSkin(textView);
        }

        public void setData(String item) {
           // ChangeSkinHelper.applyViews(itemView);
            textView.setText(item);
        }
    }
}
