package com.example.inventorychecker.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inventorychecker.manager.model.ProductModel;

public class CRUD {

    MyDatabase connect_db;
    SQLiteDatabase db;

    public CRUD(Context context) {
        connect_db = new MyDatabase(context);
        db = connect_db.getWritableDatabase();
    }

    public long createUser(String user, String pass) {
        ContentValues values = new ContentValues();
        values.put("User_username", user);
        values.put("User_password", pass);

        return db.insert(connect_db.DB_TABLE_USER, null, values);
    }

    public ArrayList<InformationLogin> validateLogin(String username, String password) {
        ArrayList<InformationLogin> data = new ArrayList<InformationLogin>();

        String sql = "SELECT * FROM " + MyDatabase.DB_TABLE_USER +
                " WHERE " + MyDatabase.DB_User_USERNAME + " = ?" +
                " AND " + MyDatabase.DB_User_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});
        cursor.moveToFirst();
        if (cursor.getCount() != 0) {
            do {
                InformationLogin information = new InformationLogin();
                information.setId(cursor.getInt(cursor.getColumnIndex(MyDatabase.DB_User_ID)));
                information.setUser(cursor.getString(cursor.getColumnIndex(MyDatabase.DB_User_USERNAME)));
                information.setPassword(cursor.getString(cursor.getColumnIndex(MyDatabase.DB_User_PASSWORD)));

                data.add(information);

            } while (cursor.moveToNext());
        }

        return data;
    }
/*
    public ArrayList<ProductModel> selectAllProduct() {
        ArrayList<ProductModel> data = new ArrayList<>();

        String sql = "SELECT * FROM " + MyDatabase.DB_TABLE_PRODUCT;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getCount() != 0) {
            do {
                ProductModel productModel = new ProductModel();
                productModel.setId(cursor.getInt(cursor.getColumnIndex(MyDatabase.DB_PRO_ID)));
                productModel.setName(cursor.getString(cursor.getColumnIndex(MyDatabase.DB_PRO_NAME)));
                productModel.setType(cursor.getString(cursor.getColumnIndex(MyDatabase.DB_PRO_TYPE)));
                productModel.setColor(cursor.getString(cursor.getColumnIndex(MyDatabase.DB_PRO_COLOR)));
                productModel.setPrice(cursor.getInt(cursor.getColumnIndex(MyDatabase.DB_PRO_PRICE)));

                data.add(productModel);

            } while (cursor.moveToNext());
        }

        return data;
    }
*/
    public long addProduct(int amount, int id){
        return 0;
    }
}

