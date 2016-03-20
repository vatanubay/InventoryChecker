package com.example.inventorychecker.activity;

import com.example.inventorychecker.database.CRUD;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
	
	private Button btn_regis;
	private EditText et_user, et_pass;
	private Context context;
	private CRUD db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		context = this;
		et_user = (EditText) findViewById(R.id.editText1);
		et_pass = (EditText) findViewById(R.id.editText2);
		btn_regis = (Button) findViewById(R.id.btn_register_homeadminpage);
		
		btn_regis.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				db = new CRUD(context);
				long insert = db.createUser(et_user.getText().toString().trim(),
											et_pass.getText().toString().trim());
				
				if(insert != -1){
					Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
					Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
