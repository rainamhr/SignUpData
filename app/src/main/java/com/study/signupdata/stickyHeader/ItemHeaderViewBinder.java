package com.study.signupdata.stickyHeader;

import android.view.View;
import android.widget.TextView;

import com.study.signupdata.R;

import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;
import tellh.com.stickyheaderview_rv.adapter.ViewBinder;

/**
 * Created by tlh on 2017/1/22 :)
 */

public class ItemHeaderViewBinder extends ViewBinder<ItemHeader, ItemHeaderViewBinder.ViewHolder> {

    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }
    @Override
    public void bindView(StickyHeaderViewAdapter adapter, ViewHolder holder, int position, ItemHeader entity) {
        holder.headerid.setText(entity.getPrefixid());
        holder.headername.setText(entity.getPrefixname());
        holder.headeraddress.setText(entity.getPrefixddress());
    }
    @Override
    public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
        return R.layout.header;
    }
    static class ViewHolder extends ViewBinder.ViewHolder {
        TextView headerid, headername, headeraddress;
        public ViewHolder(View rootView) {
            super(rootView);
            this.headerid = (TextView) rootView.findViewById(R.id.tv_prefix);
            this.headername = (TextView) rootView.findViewById(R.id.tv_prefix1);
            this.headeraddress = (TextView) rootView.findViewById(R.id.tv_prefix2);

        }
    }
}
