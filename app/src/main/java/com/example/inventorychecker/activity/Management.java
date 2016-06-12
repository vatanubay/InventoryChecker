package com.example.inventorychecker.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.inventorychecker.Utils.Constant;
import com.example.inventorychecker.manager.RestServiceManager;
import com.example.inventorychecker.manager.dao.ScannerProductDao;
import com.example.inventorychecker.manager.dao.StatusDao;
import com.example.inventorychecker.manager.service.UpdateProductService;
import com.example.inventorychecker.manager.util.BaseConfig;
import com.example.inventorychecker.view.CircularProgressDialog;

import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Management extends AppCompatActivity implements View.OnClickListener, Callback<StatusDao>{

    private ImageView imgProduct;
    private TextView contentProduct;
    private EditText edtNumber;
    private Button btn_back_scan;
    private Button btn_add;
    private Button btn_subtract;
    private ScannerProductDao product;
    private CircularProgressDialog dialog;
    private boolean flagPlus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        initView();
        dialog = new CircularProgressDialog(Management.this, R.style.CircleProgressDialogStyle);
        Bundle extra = getIntent().getExtras();
        if (extra != null)
            product = Parcels.unwrap(extra.getParcelable(Constant.ScannerResultText));

        if(product != null){
            Glide.with(getApplicationContext())
                    .load(BaseConfig.profixUrl()+"images/RSL_Srilver.jpg") //ถ้ามีของมูลในฐานข้อมูลค่ิย get
                    .into(imgProduct);
            contentProduct.setText("ID : "+product.getProduct().get(0).getId() + "\n" +
                                    "Brand : "+product.getProduct().get(0).getBrand() + "\n" +
                                    "Name : "+product.getProduct().get(0).getName() + "\n" +
                                    "Color : "+product.getProduct().get(0).getColor() + "\n" +
                                    "Type : "+product.getProduct().get(0).getType() + "\n" +
                                    "Price : "+product.getProduct().get(0).getPrice() + "\n" +
                                    "Amount : "+product.getProduct().get(0).getAmount());

            if(product.getProduct().get(0).getAmount().equals("0"))
                btn_subtract.setEnabled(false);
        }

        btn_add.setOnClickListener(this);
        btn_subtract.setOnClickListener(this);
        btn_back_scan.setOnClickListener(this);
    }

    private void initView() {
        imgProduct = (ImageView) findViewById(R.id.imgProduct);
        contentProduct = (TextView) findViewById(R.id.contentProduct);
        btn_back_scan = (Button) findViewById(R.id.btn_back_scan);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_subtract = (Button) findViewById(R.id.btn_subtract);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private int calculator(String cal) throws NumberFormatException{
        int oNum = Integer.parseInt(product.getProduct().get(0).getAmount().toString());
        int number = Integer.parseInt(edtNumber.getText().toString());
        int sum = 0;
        switch (cal){
            case "+":
                sum = oNum + number;
                break;
            case "-":
                sum = oNum - number;
                break;
        }
        return sum;
    }

    private void process(String cal){
        try{
            int sum = calculator(cal);

            if(cal.equals("+")){
                flagPlus = true;
                dialog.show();
                UpdateProductService service = RestServiceManager.create(UpdateProductService.class);
                service.updateProduct(Integer.parseInt(product.getProduct().get(0).getId()), sum+"").enqueue(this);
            }else if(cal.equals("-")){
                flagPlus = false;
                if(sum < 0){
                    Toast.makeText(Management.this, "item not enough", Toast.LENGTH_LONG).show();
                }else{
                    dialog.show();
                    UpdateProductService service = RestServiceManager.create(UpdateProductService.class);
                    service.updateProduct(Integer.parseInt(product.getProduct().get(0).getId()), sum + "").enqueue(this);
                }
            }

        }catch (NumberFormatException e){
            Toast.makeText(Management.this, "Calculate Failed : "+e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("Management", "Calculate Failed : "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                process("+");
                break;
            case R.id.btn_subtract:
                process("-");
                break;
            case R.id.btn_back_scan:
                Intent intent = new Intent(Management.this, ScannerActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onResponse(Call<StatusDao> call, Response<StatusDao> response) {
        dialog.dismiss();
        if(response.isSuccessful()){
            if(response.body().getStatus() == 0){
                // success
                Intent intent = new Intent(Management.this, Action_scan.class);
                intent.putExtra("flagPlus", flagPlus);
                startActivity(intent);
                finish();
            }else{
                // fail go to ?????
                Toast.makeText(Management.this, "Update Fail", Toast.LENGTH_LONG).show();
                Log.i("Management", "Update Fail");
                onBackPressed();
            }
        }else{
            Toast.makeText(Management.this, "Failed", Toast.LENGTH_LONG).show();
            Log.i("Management", "Failed");
            onBackPressed();
        }
    }

    @Override
    public void onFailure(Call<StatusDao> call, Throwable t) {
        dialog.dismiss();
        Toast.makeText(Management.this, "Failed : " + t.getMessage(), Toast.LENGTH_LONG).show();
        Log.i("Management", "Failed : " + t.getMessage());
        onBackPressed();
    }
}
