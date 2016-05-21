package com.example.inventorychecker.manager.service;

import com.example.inventorychecker.manager.dao.ScannerProductDao;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by HP on 5/5/2016.
 */
public interface ScannerProductService {

    @POST("scanner_product.php")
    @FormUrlEncoded
    Call<ScannerProductDao> getProduct(@Field("strTaxId") String taxId);
}
