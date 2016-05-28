package com.example.inventorychecker.manager.service;

import com.example.inventorychecker.manager.dao.StatusDao;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by HP on 5/21/2016.
 */
public interface UpdateProductService {

    @POST("product_update.php")
    @FormUrlEncoded
    Call<StatusDao> updateProduct(@Field("pro_id") int proId, @Field("pro_amount") String amount);
}
