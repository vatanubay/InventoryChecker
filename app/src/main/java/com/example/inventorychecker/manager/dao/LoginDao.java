package com.example.inventorychecker.manager.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 5/1/2016.
 */
public class LoginDao {

    @SerializedName("num")
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
