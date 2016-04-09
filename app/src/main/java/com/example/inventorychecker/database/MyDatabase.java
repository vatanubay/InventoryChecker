package com.example.inventorychecker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    private final static String DB_NAME = "Inventory_Checker"; //สร้างชื่อฐานข้อมูล
    private static int db_version = 1; //เวอร์ชั่นฐานข้อมูล


    // ใช้กับการ login
    public static String DB_TABLE_USER = "User"; //สร้างชื่อตาราง
    public static String DB_User_ID = "User_id"; //ชื่อคอลัม
    public static String DB_User_NAME = "User_name";
    public static String DB_User_SURE = "User_sure";
    public static String DB_User_TEL = "User_tel";
    public static String DB_User_USERNAME = "User_username";
    public static String DB_User_PASSWORD = "User_password";

    String SQL_TABLE_User = "CREATE TABLE " + DB_TABLE_USER + "(" + DB_User_ID + " integer primary key autoincrement, " +
            DB_User_NAME + " text(30), " +
            DB_User_SURE + " text(30), " +
            DB_User_TEL + " text(30), " +
            DB_User_USERNAME + " text(250), " +
            DB_User_PASSWORD + " text(250))"; //กำหนดค่าที่จะเก็บไว้ในคอลัมต่างๆ

    //    ------------------------------------------------------------------------------------------------------------------------- //

    private static String DB_TABLE_ADMIN = "Admin";
    public static String DB_Admin_ID = "User_id";
    public static String DB_Admin_NAME = "User_name";
    public static String DB_Admin_SURE = "User_sure";
    public static String DB_Admin_TEL = "User_tel";
    public static String DB_Admin_USERNAME = "User_username";
    public static String DB_Admin_PASSWORD = "User_password";

    String SQL_TABLE_ADMIN = "CREATE TABLE " + DB_TABLE_ADMIN + "(" + DB_Admin_ID + " integer primary key autoincrement, " +
            DB_Admin_NAME + " text(30), " +
            DB_Admin_SURE + " text(30), " +
            DB_Admin_TEL + " text(30), " +
            DB_Admin_USERNAME + " text(250), " +
            DB_Admin_PASSWORD + " text(250))";

    //    ------------------------------------------------------------------------------------------------------------------------- //

    private static String DB_TABLE_EXPORT_FILE = "Export file";
    public static String DB_Ex_ID = "Ep_id";
    public static String DB_Ex_NAME = "Ep_name";
    public static String DB_Ex_DATE = "Ep_date";

    String SQL_TABLE_EXPORT_FILE = "CREATE TABLE " + DB_TABLE_EXPORT_FILE + "(" + DB_Ex_ID + " integer primary key autoincrement, " +
            DB_Ex_NAME + " text(30), " +
            DB_Ex_DATE + " text(250))";

    //    ------------------------------------------------------------------------------------------------------------------------- //

    private static String DB_TABLE_REPORT = "Report";
    public static String DB_Re_ID = "Re_id";
    public static String DB_Re_NAME = "Re_name";
    public static String DB_Re_DATE = "Re_date";

    String SQL_TABLE_REPORT = "CREATE TABLE " + DB_TABLE_REPORT + "(" + DB_Re_ID + " integer primary key autoincrement, " +
            DB_Re_NAME + " text(30), " +
            DB_Re_DATE + " text(250))";

    //    ------------------------------------------------------------------------------------------------------------------------- //

    private static String DB_TABLE_QR_CODE = "QR code";
    public static String DB_Qr_code_ID = "Qr_id";

    String SQL_TABLE_QR_CODE = "CREATE TABLE " + DB_TABLE_QR_CODE + "(" + DB_Qr_code_ID + " integer primary key autoincrement, ";

    //    ------------------------------------------------------------------------------------------------------------------------- //

    public static String DB_TABLE_PRODUCT = "Product";
    public static String DB_PRO_ID = "Pro_id";
    public static String DB_PRO_NAME = "Pro_name";
    public static String DB_PRO_COLOR = "Pro_color";
    public static String DB_PRO_TYPE = "Pro_type";
    public static String DB_PRO_PRICE = "Pro_price";

    String SQL_TABLE_PRODUCT = "CREATE TABLE " + DB_TABLE_PRODUCT + "(" + DB_PRO_ID + " integer primary key autoincrement, " +
            DB_PRO_NAME + " text(250), " +
            DB_PRO_COLOR + " text(250), " +
            DB_PRO_TYPE + " text(250), " +
            DB_PRO_PRICE + " integer(50))";

    //    ------------------------------------------------------------------------------------------------------------------------- //

    public static String DB_TABLE_HISTORY = "History";
    public static String DB_HIS_PRO_ID = "Pro_id";
    public static String DB_HIS_PRO_NAME = "Pro_name";
    public static String DB_HIS_PRO_COLOR = "Pro_color";
    public static String DB_HIS_PRO_TYPE = "Pro_type";
    public static String DB_HIS_PRO_PRICE = "Pro_price";
    public static String DB_HIS_USER_NAME = "User_name";
    public static String DB_HIS_DATE = "Date";

    String SQL_TABLE_HISTORY = "CREATE TABLE " + DB_TABLE_HISTORY + "(" + DB_HIS_PRO_ID + " integer primary key autoincrement, " +
            DB_HIS_PRO_NAME + " text(250), " +
            DB_HIS_PRO_COLOR + " text(250), " +
            DB_HIS_PRO_TYPE + " text(250), " +
            DB_HIS_PRO_PRICE + " integer(50)," +
            DB_HIS_USER_NAME + " text(250), " +
            DB_HIS_DATE + " date(15))" ;

    //    ------------------------------------------------------------------------------------------------------------------------- //
    public MyDatabase(Context context) {
        super(context, DB_NAME, null, db_version); //ระบุ ชื่อ และเวอร์ชั นของฐานข้อมูล
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABLE_User); //สร้างฐานข้อมูลลงใน SQlite
        db.execSQL(SQL_TABLE_PRODUCT);
        db.execSQL(SQL_TABLE_ADMIN);
        db.execSQL(SQL_TABLE_EXPORT_FILE);
        db.execSQL(SQL_TABLE_REPORT);
        db.execSQL(SQL_TABLE_QR_CODE);
        db.execSQL(SQL_TABLE_HISTORY);
        //db.execSQL(SQL_TABLE_...);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_USER); //ใช้ในการอัพเกรดฐานข้อมูล
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_EXPORT_FILE);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_QR_CODE);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_REPORT);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_HISTORY);
        onCreate(db);
    }

}
