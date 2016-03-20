package com.example.inventorychecker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper{
	
	private final static String DB_NAME = "MyDB"; //สร้างชื่อฐานข้อมูล
	private static int db_version = 1; //เวอร์ชั่นฐานข้อมูล
	
	public static String DB_TABLE_NAME = "TABLE_ONE"; //สร้างชื่อตาราง
	public static String DB_FEILD_ID = "id"; //ชื่อคอลัม
	public static String DB_FEILD_USERNAME = "username";
	public static String DB_FEILD_PASSWORD = "password";

	String SQL_TABLE_ONE = "CREATE TABLE "+DB_TABLE_NAME+"("+DB_FEILD_ID+" integer primary key autoincrement, "+
															DB_FEILD_USERNAME+" text(250), "+
															DB_FEILD_PASSWORD+" text(250))"; //กำหนดค่าที่จะเก็บไว้ในคอลัมต่างๆ
	
	public static String DB_TABLE_PRO = "TABLE_TWO";
	public static String DB_PRO_ID = "id";
	public static String DB_PRO_NAME = "productname";
	public static String DB_PRO_COLOR = "productcolor";
	public static String DB_PRO_TYPE = "producttype";
	public static String DB_PRO_PRICE = "productprice";
		
	String SQL_TABLE_TWO = "CREATE TABLE "+DB_TABLE_NAME+"("+DB_PRO_ID+" integer primary key autoincrement, "+
															DB_PRO_NAME+" text(250), "+
															DB_PRO_COLOR+" text(250), "+
															DB_PRO_TYPE+" text(250), "+
															DB_PRO_PRICE+" integer(50))";
	public MyDatabase(Context context) {
		super(context, DB_NAME, null, db_version); //ระบุ ชื่อ และเวอร์ชั นของฐานข้อมูล
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_TABLE_ONE); //สร้างฐานข้อมูลลงใน SQlite
		//db.execSQL(SQL_TABLE_TWO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_NAME); //ใช้ในการอัพเกรดฐานข้อมูล
		onCreate(db);
	}
	
}
