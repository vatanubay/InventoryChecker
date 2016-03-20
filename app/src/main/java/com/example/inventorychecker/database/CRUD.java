package com.example.inventorychecker.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CRUD {
	
	MyDatabase connect_db;
	SQLiteDatabase db;
	
	public CRUD(Context context) {
		connect_db = new MyDatabase(context);
		db = connect_db.getWritableDatabase();
	}
	
	public long createUser(String user, String pass){
		ContentValues values = new ContentValues();
		values.put("username", user);
		values.put("password", pass);
		
		return db.insert(connect_db.DB_TABLE_NAME, null, values);
	}
	
	public ArrayList<InformationLogin> validateLogin(String username, String password){
		ArrayList<InformationLogin> data = new ArrayList<InformationLogin>();
		
		String sql = "SELECT * FROM " + MyDatabase.DB_TABLE_NAME+
						" WHERE " + MyDatabase.DB_FEILD_USERNAME + " = ?" +
						" AND " + MyDatabase.DB_FEILD_PASSWORD + " = ?";
		Cursor cursor = db.rawQuery(sql, new String[] {username, password});
		cursor.moveToFirst();
		if(cursor.getCount() != 0){
			do {
				InformationLogin information = new InformationLogin();
				information.setId(cursor.getInt(cursor.getColumnIndex(MyDatabase.DB_FEILD_ID)));
				information.setUser(cursor.getString(cursor.getColumnIndex(MyDatabase.DB_FEILD_USERNAME)));
				information.setPassword(cursor.getString(cursor.getColumnIndex(MyDatabase.DB_FEILD_PASSWORD)));
				
				data.add(information);
				
			} while (cursor.moveToNext());
		}
		
		return data;
		
	}
}

