package com.study.signupdata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by $raina on $5/23/2017.
 */

public class MemberResult {
    public String status;

    @SerializedName("data")
    public List<Member> data;

    public MemberResult(String status , List<Member> data) {
        this.status = status;
        this.data = data;
    }
}
