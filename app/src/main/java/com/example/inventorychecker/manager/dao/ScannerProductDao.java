package com.example.inventorychecker.manager.dao;

import com.example.inventorychecker.manager.model.ProductModel;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by HP on 5/5/2016.
 */
@Parcel
public class ScannerProductDao {

    @SerializedName("product")
    private List<ProductModel> product;

    public ScannerProductDao(){

    }

    public List<ProductModel> getProduct() {
        return product;
    }

    public void setProduct(List<ProductModel> product) {
        this.product = product;
    }
}
