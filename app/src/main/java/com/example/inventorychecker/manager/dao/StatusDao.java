package com.example.inventorychecker.manager.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 5/21/2016.
 */
public class StatusDao {

    @SerializedName("status")
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
