package com.example.inventorychecker.manager.service;

import com.example.inventorychecker.manager.dao.LoginDao;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by HP on 5/1/2016.
 */
public interface LoginService {

    @POST("login.php")
    @FormUrlEncoded
    Call<LoginDao> login(@Field("strUser") String username, @Field("strPass") String password);
}
