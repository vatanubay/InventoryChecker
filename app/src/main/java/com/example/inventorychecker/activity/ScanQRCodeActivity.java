package com.example.inventorychecker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class ScanQRCodeActivity extends AppCompatActivity {
    public static final int REQUEST_QR_SCAN = 0;
    TextView textView1;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
        
        textView1 = (TextView)findViewById(R.id.textView1);
        
        Button button1 = (Button)findViewById(R.id.btn_register_homeadminpage);
        button1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext()
                        , ScannerActivity.class);
                startActivityForResult(intent, REQUEST_QR_SCAN);
            }
        });
    }
    
    public void onActivityResult(int requestCode, int resultCode
            , Intent intent) {
        if (requestCode == REQUEST_QR_SCAN && resultCode == RESULT_OK) {
            String content = intent.getStringExtra("CONTENT");
            String format = intent.getStringExtra("FORMAT");
            String type = intent.getStringExtra("TYPE");
            textView1.setText(content + "\n" + format + "\n" + type);
        } else if(requestCode == REQUEST_QR_SCAN 
                && resultCode == RESULT_CANCELED) {
            textView1.setText("");
        }
    }
}