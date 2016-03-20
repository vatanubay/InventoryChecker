package com.example.inventorychecker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeAdminActivity extends AppCompatActivity {
	
	Button btn_register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_admin);
		
		btn_register = (Button) findViewById(R.id.btn_register_homeadminpage); //กำหนดlistenerให้กับปุ่ม button
		
		btn_register.setOnClickListener(new OnClickListener() { //การกำหนดออบเจคปัจจุบัน ของการคลิกปุ่ม 
			
			@Override
			public void onClick(View v) { //เมธอด onclick
				Intent intent = new Intent(HomeAdminActivity.this, RegisterActivity.class);
                startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home_admin, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
