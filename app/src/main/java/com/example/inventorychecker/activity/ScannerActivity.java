package com.example.inventorychecker.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.inventorychecker.Utils.Constant;
import com.example.inventorychecker.database.CRUD;
import com.example.inventorychecker.manager.RestServiceManager;
import com.example.inventorychecker.manager.dao.ScannerProductDao;
import com.example.inventorychecker.manager.service.ScannerProductService;
import com.example.inventorychecker.model.ProductModel;
import com.example.inventorychecker.view.CircularProgressDialog;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    private List<BarcodeFormat> barcodeFormats = new ArrayList<>();
    private ArrayList<ProductModel> product;
//    private CRUD crud;
    private CircularProgressDialog dialog;

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
//        crud = new CRUD(ScannerActivity.this);
//        product = crud.selectAllProduct();
//        if(product == null)
//            product = new ArrayList<>();
        dialog = new CircularProgressDialog(ScannerActivity.this, R.style.CircleProgressDialogStyle);
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
        mScannerView.stopCamera();
        mScannerView.stopCameraPreview();

        dialog.show();

        ScannerProductService service = RestServiceManager.create(ScannerProductService.class);
        service.getProduct(result.getText()).enqueue(new Callback<ScannerProductDao>() {
            @Override
            public void onResponse(Call<ScannerProductDao> call, Response<ScannerProductDao> response) {
                if(response.isSuccessful()){
                    ScannerProductDao dao = response.body();
                    if(dao != null){
                        dialog.dismiss();
                        Intent intent = new Intent(ScannerActivity.this, Management.class);
                        intent.putExtra(Constant.ScannerResultText, dao.getTaxId());
                        intent.putExtra(Constant.ScannerResultKey, 1);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    dialog.dismiss();
                    Snackbar.make(mScannerView, "Failed", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ScannerProductDao> call, Throwable t) {
                dialog.dismiss();
                Snackbar.make(mScannerView, "Failed : " + t.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
