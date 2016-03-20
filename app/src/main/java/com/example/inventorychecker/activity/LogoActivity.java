package com.example.inventorychecker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class LogoActivity extends AppCompatActivity {
	Handler handler;
	Runnable runnable;
	long delay_time;
	long time = 3000l;
	
	    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.activity_logo);
        handler = new Handler();
        runnable = new Runnable() {
        	@Override
			public void run() {
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class); 
				startActivity(intent); 
				finish(); 
			}
		};
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	delay_time = time;
    	handler.postDelayed(runnable, delay_time);
    	time = System.currentTimeMillis();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	handler.removeCallbacks(runnable);
    	time = delay_time - (System.currentTimeMillis() - time);
    }
}

        


   