package com.study.signupdata.stickyHeader;/*
package com.study.signupdata.stickyHeader;

import com.study.signupdata.R;

import tellh.com.stickyheaderview_rv.adapter.DataBean;
import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;

*/

import com.study.signupdata.R;

import tellh.com.stickyheaderview_rv.adapter.DataBean;
import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;

/**
 * Created by tlh on 2017/1/22 :)
 */


public class ItemHeader extends DataBean {
    private String prefixid, prefixname, prefixddress;

    public String getPrefixid() {
        return prefixid;
    }
    public String getPrefixname() {
        return prefixname;
    }
    public String getPrefixddress() {
        return prefixddress;
    }

    public ItemHeader(String prefixid, String prefixname, String prefixddress) {
        this.prefixid = prefixid;
        this.prefixname = prefixname;
        this.prefixddress = prefixddress;
    }

    @Override
    public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
        return R.layout.header;
    }

    @Override
    public boolean shouldSticky() {
        return true;
    }
}
