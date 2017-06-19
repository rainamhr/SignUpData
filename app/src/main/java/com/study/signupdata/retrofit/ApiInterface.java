package com.study.signupdata.retrofit;

import com.study.signupdata.database.AddResult;
import com.study.signupdata.Member;
import com.study.signupdata.MemberResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by $raina on $5/23/2017.
 */

public interface ApiInterface {

    @GET("test_get")
    Call<MemberResult> getList();

    @FormUrlEncoded
    @POST("test_add")
    Call<Member>insertInfo(@Field("id")String id,
                           @Field("name")String name,
                           @Field("address")String address,
                           @Field("added_by")String added_by);
@FormUrlEncoded
    @POST("test_add")
    Call<AddResult>insertMember(@Field("id")String id,
                                @Field("name")String name,
                                @Field("address")String address,
                                @Field("added_by")String added_by);

}

