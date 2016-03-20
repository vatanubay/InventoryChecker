package com.example.inventorychecker.activity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ProductlistActivity extends AppCompatActivity {

	private Spinner Brand;
	private String[] txt_GR = {"GOSEN","RSL"};
	private Button button1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productlist);

		Brand = (Spinner) findViewById(R.id.spinner1);
		
		
		ArrayAdapter<String> adapter_brand = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_spinner_dropdown_item, txt_GR);

		Brand.setAdapter(adapter_brand);
		
	button1 = (Button) findViewById(R.id.button1);
	button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ProductlistActivity.this, SelectProductActivity.class);
                startActivity(intent);
			}	
    });
}
}
