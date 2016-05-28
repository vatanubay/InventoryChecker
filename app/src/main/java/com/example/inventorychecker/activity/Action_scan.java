package com.example.inventorychecker.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Action_scan extends AppCompatActivity {

    private Button btn_back;
    private Button btn_manu;
    private TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_scan);

        txtTitle = (TextView) findViewById(R.id.txtTitle);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            boolean flagPlus = bundle.getBoolean("flagPlus");
            if(flagPlus)
                txtTitle.setText("Add");
            else
                txtTitle.setText("Minus");

        }

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Action_scan.this,ScannerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_manu = (Button)findViewById(R.id.btn_manu);
        btn_manu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Action_scan.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }



}
