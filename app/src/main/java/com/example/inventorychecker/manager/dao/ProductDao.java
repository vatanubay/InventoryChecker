package com.example.inventorychecker.manager.dao;

import com.example.inventorychecker.manager.model.ProductModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HP on 5/21/2016.
 */
public class ProductDao {

    @SerializedName("product")
    private List<ProductModel> product;

    public List<ProductModel> getProduct() {
        return product;
    }

    public void setProduct(List<ProductModel> product) {
        this.product = product;
    }
}
