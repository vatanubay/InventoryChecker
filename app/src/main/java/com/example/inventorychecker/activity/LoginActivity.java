package com.example.inventorychecker.activity;

import java.util.ArrayList;

import com.example.inventorychecker.database.CRUD;
import com.example.inventorychecker.database.InformationLogin;
import com.example.inventorychecker.manager.RestServiceManager;
import com.example.inventorychecker.manager.dao.LoginDao;
import com.example.inventorychecker.manager.service.LoginService;
import com.example.inventorychecker.view.CircularProgressDialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button mLogin, btn_backend;
    private EditText mUsername;
    private EditText mPassword;
    private Context mContext;
    private CircularProgressDialog progressDialog;
  
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
            public void onClick(final View v) {

                progressDialog = new CircularProgressDialog(LoginActivity.this, R.style.CircleProgressDialogStyle);
                progressDialog.show();

                LoginService call = RestServiceManager.create(LoginService.class);
                call.login(mUsername.getText().toString(), mPassword.getText().toString()).enqueue(new Callback<LoginDao>() {
                    @Override
                    public void onResponse(Call<LoginDao> call, Response<LoginDao> response) {
                        if (response.isSuccessful()) {
                            LoginDao loginDao = response.body();
                            if(loginDao != null) {
                                progressDialog.dismiss();
                                if (loginDao.getMsg().equals("1")) {
                                    Intent intent = new Intent(mContext, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Snackbar.make(v, "Username/Password it's incorrect", Snackbar.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            progressDialog.dismiss();
                            Snackbar.make(v, "Failed", Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginDao> call, Throwable t) {
                        progressDialog.dismiss();
                        Snackbar.make(v, "Failed : "+t.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });

                /*
                CRUD crud = new CRUD(getApplicationContext());
				ArrayList<InformationLogin> data = crud.validateLogin(mUsername.getText().toString(), mPassword.getText().toString());
				if(data.size() != 0){
					Intent intent = new Intent(mContext, HomeActivity.class);
	                startActivity(intent);
                    finish();
				}else{
					Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
				}
				*/

            }
        });
        
        btn_backend.setOnClickListener(new OnClickListener() { //กำหนดการกะทำ 
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, HomeAdminActivity.class);
                startActivity(intent);

			}
		});
    }
}     
    
