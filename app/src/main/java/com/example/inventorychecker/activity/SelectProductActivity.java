package com.example.inventorychecker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class SelectProductActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_product);
		ImageView imageView = (ImageView)findViewById(R.id.imageView1);
	    imageView.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Intent intent = new Intent(getApplicationContext(), ViewProductActivity.class);
	                startActivity(intent);
		            }
	        });
	    
	}
		  
}