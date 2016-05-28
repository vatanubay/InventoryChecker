package com.example.inventorychecker.manager.util;

import com.example.inventorychecker.Utils.Constant;

/**
 * Created by HP on 5/1/2016.
 */
public class BaseConfig {

    public static String BaseUrlProduction =  "http://forteamproject.esy.es/meen/inventory/";
    public static String BaseUrlLocal = "http://192.168.1.176/inventory/";

    public static String profixUrl(){
        if(Constant.isProduction)
            return BaseUrlProduction;
        else
            return BaseUrlLocal;
    }

//    public static String BaseUrl = "http://127.0.0.1/inventory/";
    public static String BaseUrl = "http://192.168.1.176/inventory/";
}
