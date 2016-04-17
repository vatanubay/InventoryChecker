package com.example.inventorychecker.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.inventorychecker.Utils.Constant;
import com.example.inventorychecker.database.CRUD;
import com.example.inventorychecker.model.ProductModel;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    private List<BarcodeFormat> barcodeFormats = new ArrayList<>();
    private ArrayList<ProductModel> product;
    private CRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        barcodeFormats.add(BarcodeFormat.QR_CODE);
        mScannerView.setFormats(barcodeFormats);
        setContentView(mScannerView);
        init();
    }

    private void init(){
        crud = new CRUD(ScannerActivity.this);
        product = crud.selectAllProduct();
        if(product == null)
            product = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result result) {
        // Do something with the result here
        Log.v("CAMERA", result.getText()); // Prints scan results
        Log.v("CAMERA", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        Toast.makeText(getApplicationContext(), " "+result.getText(), Toast.LENGTH_LONG).show();

        // If you would like to resume scanning, call this method below:
//        mScannerView.resumeCameraPreview(this);
        // query database

        boolean validProduct = false;
        String msg = "";
        if(product.size() != 0){
            for(ProductModel productModel : product){
                if(result.getText().equals(String.valueOf(productModel.getId()))){
                    validProduct = true;
                    break;
                }
            }

            if(validProduct){
                Intent intent = new Intent(ScannerActivity.this, Management.class);
                intent.putExtra(Constant.ScannerResultText, result.getText());
                intent.putExtra(Constant.ScannerResultKey, 1);
                startActivity(intent);
                finish();
            }else
                msg = "data not match";
        }else
        msg = "No data";

        mScannerView.stopCamera();
        mScannerView.stopCameraPreview();
        if(!validProduct){
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
