package com.example.inventorychecker.Utils;

import android.content.Context;

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

    public static void init(Context context) throws IOException {
        String DB_PATH = "/data/data/"+context.getPackageName()+"/databases/"; //edited to databases
        File file = context.getDatabasePath(DB_NAME);
        if(!file.exists()){
            File path = new File(DB_PATH);
            path.mkdirs();
            file.createNewFile();
            copyDataBase(context, file);
        }
//        {
//            Toast.makeText(context, "No", Toast.LENGTH_LONG).show();
//            new MyDatabase(context);
//            copyDataBase(context, DB_PATH);
//        }else
//            Toast.makeText(context, "have", Toast.LENGTH_LONG).show();
    }

    private static void copyDataBase(Context context, File file) {
        byte[] buffer = new byte[1024];
        OutputStream myOutput = null;
        int length;
        // Open your local db as the input stream
        InputStream myInput = null;
        try {
            myInput = context.getAssets().open(DB_NAME);
            // transfer bytes from the inputfile to the
            // outputfile
            myOutput = new FileOutputStream(file);
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
