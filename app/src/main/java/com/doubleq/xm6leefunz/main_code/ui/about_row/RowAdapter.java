package com.doubleq.xm6leefunz.main_code.ui.about_row;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.doubleq.xm6leefunz.R;

import java.util.List;

public class RowAdapter extends BaseQuickAdapter<DataRow, BaseViewHolder> {

    public RowAdapter(int layoutResId, @Nullable List<DataRow> data,int rowDrawable, String rowName) {
        super(R.layout.item_row, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, DataRow item) {

    }
}
