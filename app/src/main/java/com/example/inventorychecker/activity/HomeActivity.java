package com.example.inventorychecker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

	public static final int REQUEST_QR_SCAN = 0;
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ImageView imageView = (ImageView)findViewById(R.id.imageView2); //กำหนดlistenerให้กับ imageview
	    imageView.setOnClickListener(new OnClickListener() { //กำหนดการกะทำของ  imageview 
	            @Override
	            public void onClick(View v) {
	                Intent intent = new Intent(getApplicationContext(), StoreManagemant.class);
	                startActivity(intent);
		            }
	        });
	    ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
	    imageView1.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
					Intent intent = new Intent(getApplicationContext()
							, ScannerActivity.class);
					startActivityForResult(intent, REQUEST_QR_SCAN);

		            }
	        });
	}
		  
}