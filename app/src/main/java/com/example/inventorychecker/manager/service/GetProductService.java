package com.example.inventorychecker.manager.service;

import com.example.inventorychecker.manager.dao.ProductDao;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by HP on 5/21/2016.
 */
public interface GetProductService {

    @GET("product.php")
    Call<ProductDao> getProduct();
}
