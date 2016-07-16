package com.example.inventorychecker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.inventorychecker.manager.RestServiceManager;
import com.example.inventorychecker.manager.dao.ProductDao;
import com.example.inventorychecker.manager.model.ProductModel;
import com.example.inventorychecker.manager.service.GetProductService;
import com.example.inventorychecker.module.store.CustomSpinnerBrandAdapter;
import com.example.inventorychecker.module.store.CustomSpinnerNameAdapter;
import com.example.inventorychecker.module.store.CustomViewProductAdapter;
import com.example.inventorychecker.view.CircularProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreManagemant extends AppCompatActivity{

    private Spinner sprinnerBrandproduct;
    private Spinner spinnerNameproduct;
    private RecyclerView recyclerView;

    private CustomSpinnerBrandAdapter adapterBrand;
    private CustomSpinnerNameAdapter adapterName;
    private CustomViewProductAdapter adapterProduct;
    private List<ProductModel> productBrand = new ArrayList<ProductModel>();
    private List<ProductModel> productName = new ArrayList<ProductModel>();
    private List<ProductModel> productItems = new ArrayList<ProductModel>();
    private Map<String, List<ProductModel>> productBrandMap = new HashMap<String, List<ProductModel>>();
    private Map<String, List<ProductModel>> productNameMap = new HashMap<String, List<ProductModel>>();

    private CircularProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_managemant);

        initView();
        init();
    }

    private void init() {
        progressDialog = new CircularProgressDialog(StoreManagemant.this, R.style.CircleProgressDialogStyle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapterProduct = new CustomViewProductAdapter(getApplicationContext(), productItems);
        recyclerView.setAdapter(adapterProduct);
        GetProductService service = RestServiceManager.create(GetProductService.class);
        progressDialog.show();
        service.getProduct().enqueue(new Callback<ProductDao>() {
            @Override
            public void onResponse(Call<ProductDao> call, Response<ProductDao> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    ProductDao dao = response.body();
                    if (dao != null) {
                        productBrandMap = getProductBrandMap(dao.getProduct());
                        productNameMap = getProductNameMap(dao.getProduct());
                        productBrand.addAll(dao.getProduct());
                        productName.addAll(dao.getProduct());
                        adapterBrand = new CustomSpinnerBrandAdapter(StoreManagemant.this, 0, productBrand);

                        sprinnerBrandproduct.setAdapter(adapterBrand);

                    }
                } else {
                    // failed
                    Toast.makeText(StoreManagemant.this, "Fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProductDao> call, Throwable t) {
                Toast.makeText(StoreManagemant.this, "onFailure", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

        sprinnerBrandproduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProductModel product = (ProductModel) parent.getItemAtPosition(position);
                adapterName = new CustomSpinnerNameAdapter(StoreManagemant.this, 0, productBrandMap.get(product.getBrand()));
                spinnerNameproduct.setAdapter(adapterName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerNameproduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProductModel product = (ProductModel) parent.getItemAtPosition(position);
                List<ProductModel> items = productNameMap.get(product.getName());
                if (items != null || !items.isEmpty()) {
                    productItems.clear();
                    productItems.addAll(items);
                    adapterProduct.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        sprinnerBrandproduct = (Spinner) findViewById(R.id.spinnerBrandproduct);
        spinnerNameproduct = (Spinner) findViewById(R.id.spinnerNameproduct);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private Map<String, List<ProductModel>> getProductBrandMap(List<ProductModel> productBrand){
        Map<String, List<ProductModel>> productBrandMap = new HashMap<String, List<ProductModel>>();
        List<ProductModel> productBrandList;
        if(productBrand != null){
            for(ProductModel product : productBrand){
                if(productBrandMap.containsKey(product.getBrand())){
                    productBrandList = productBrandMap.get(product.getBrand());
                    productBrandList.add(product);
                }else{
                    productBrandList = new ArrayList<ProductModel>();
                    productBrandList.add(product);
                    productBrandMap.put(product.getBrand(), productBrandList);
                }
            }
        }
        return productBrandMap;
    }

    private Map<String, List<ProductModel>> getProductNameMap(List<ProductModel> product){
        Map<String, List<ProductModel>> productNameMap = new HashMap<String, List<ProductModel>>();
        List<ProductModel> productList;
        if(product != null){
            for(ProductModel p : product){
                if(productNameMap.containsKey(p.getName())){
                    productList = productNameMap.get(p.getName());
                    productList.add(p);
                }else{
                    productList = new ArrayList<ProductModel>();
                    productList.add(p);
                    productNameMap.put(p.getName(), productList);
                }
            }
        }
        return productNameMap;
    }
}
