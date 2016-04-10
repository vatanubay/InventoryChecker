package com.example.inventorychecker.Utils;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 4/10/2016.
 */
public class CopyDatabase {

    private static String DB_NAME = "Inventory_Checker.db";

    public static void init(Context context){
        String DB_PATH = context.getFilesDir().getAbsolutePath()+"/"; //edited to databases
        File file = new File(DB_PATH+DB_NAME);
        if(!file.exists()) {
            Toast.makeText(context, "No", Toast.LENGTH_LONG).show();
            copyDataBase(context, DB_PATH);
        }else
            Toast.makeText(context, "have", Toast.LENGTH_LONG).show();
    }

    private static void copyDataBase(Context context, String path) {
        byte[] buffer = new byte[1024];
        OutputStream myOutput = null;
        int length;
        // Open your local db as the input stream
        InputStream myInput = null;
        try {
            myInput = context.getAssets().open(DB_NAME);
            // transfer bytes from the inputfile to the
            // outputfile
            myOutput = new FileOutputStream(path+"/"+DB_NAME);
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.close();
            myOutput.flush();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
