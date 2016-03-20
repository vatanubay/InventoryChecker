package com.example.inventorychecker.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.inventorychecker.Utils.Constant;

public class Management extends AppCompatActivity {

    private ImageView imgProduct;
    private Button btn_back_scan;
    private String txtResultScanner;
    private int idResultScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        imgProduct = (ImageView) findViewById(R.id.imgProduct);
        btn_back_scan = (Button)findViewById(R.id.btn_back_scan);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            idResultScanner = extra.getInt(Constant.ScannerResultKey);
            txtResultScanner = extra.getString(Constant.ScannerResultText);
        }

        if(idResultScanner == 1){
            imgProduct.setBackgroundResource(R.drawable.gosen_gn_505_nylon_480_bath);
        }



        btn_back_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Management.this,ScannerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
