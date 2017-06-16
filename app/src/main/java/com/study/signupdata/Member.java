package com.study.signupdata;


import tellh.com.stickyheaderview_rv.adapter.DataBean;
import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;

/**
 * Created by $raina on $5/23/2017.
 */

public class Member extends DataBean {

     String id;
     String name;
     String address;
     String email;
     String added_by;
    public boolean shouldSticky;

    public String getAdded_by() {
        return added_by;
    }

    public void setAdded_by(String added_by) {
        this.added_by = added_by;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Member (String name, String address){
        this.name = name;
        this.address = address;
    }

    public Member(){

    }
   public Member(String id, String name, String address) {

       this.id = id;
       this.name = name;
       this.address = address;
   }

    @Override
    public int getItemLayoutId(StickyHeaderViewAdapter stickyHeaderViewAdapter) {
        return R.layout.item_layout;
    }
        public void setShouldSticky(boolean shouldSticky) {
            this.shouldSticky = shouldSticky;
        }

        public boolean shouldSticky() {
            return shouldSticky;
        }
    }
