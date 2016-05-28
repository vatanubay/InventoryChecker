package com.example.inventorychecker.manager.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by HP on 5/21/2016.
 */
@Parcel
public class ProductModel {

    @SerializedName("id")
    private String id;
    @SerializedName("taxId")
    private String taxId;
    @SerializedName("brand")
    private String brand;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("color")
    private String color;
    @SerializedName("price")
    private String price;
    @SerializedName("img_name")
    private String imageName;
    @SerializedName("amount")
    private String amount;

    public ProductModel(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
