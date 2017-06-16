package com.study.signupdata.stickyHeader;/*
package com.study.signupdata.stickyHeader;

import android.view.View;
import android.widget.TextView;

import com.study.signupdata.Member;
import com.study.signupdata.R;

import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;
import tellh.com.stickyheaderview_rv.adapter.ViewBinder;

//import com.squareup.picasso.Picasso;

*/

import android.view.View;
import android.widget.TextView;

import com.study.signupdata.Member;
import com.study.signupdata.R;

import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;
import tellh.com.stickyheaderview_rv.adapter.ViewBinder;

/**
 * Created by tlh on 2017/1/22 :)
 */


public class MemberItemViewBinder extends ViewBinder<Member, MemberItemViewBinder.ViewHolder> {
    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void bindView(StickyHeaderViewAdapter adapter, ViewHolder holder, int position, Member entity) {
        holder.tvId.setText(String.valueOf(entity.getId()));
        //holder.tvName.setText(entity.getLogin());

    }

    @Override
    public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
        return R.layout.item_layout;
    }

    static class ViewHolder extends ViewBinder.ViewHolder {
        public TextView tvId;
       public TextView tvname;
        public TextView tvaddress;
        public ViewHolder(View rootView) {
            super(rootView);
            this.tvId = (TextView) rootView.findViewById(R.id.number);
            this.tvname = (TextView) rootView.findViewById(R.id.tx_name);
            this.tvaddress = (TextView) rootView.findViewById(R.id.tx_address);
        }

    }
}

