package com.example.inventorychecker.activity;

import java.util.ArrayList;

import com.example.inventorychecker.database.CRUD;
import com.example.inventorychecker.database.InformationLogin;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button mLogin, btn_backend;
    private EditText mUsername;
    private EditText mPassword;
    private Context mContext;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login); // กำหนด ContentView ของ dialog

        mContext = this;
        mLogin = (Button) findViewById(R.id.btn_register_homeadminpage);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        btn_backend = (Button) findViewById(R.id.btn_backend_loginpage);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	CRUD crud = new CRUD(getApplicationContext());
				ArrayList<InformationLogin> data = crud.validateLogin(mUsername.getText().toString(), mPassword.getText().toString());
				if(data.size() != 0){
					Intent intent = new Intent(mContext, HomeActivity.class);
	                startActivity(intent);
                    finish();
				}else{
					Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
				}

            }
        });
        
        btn_backend.setOnClickListener(new OnClickListener() { //กำหนดการกะทำ 
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, HomeAdminActivity.class);
                startActivity(intent);
                finish();
			}
		});
    }
}     
    
