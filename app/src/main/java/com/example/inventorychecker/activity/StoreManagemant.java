package com.example.inventorychecker.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.example.inventorychecker.manager.RestServiceManager;
import com.example.inventorychecker.manager.dao.ProductDao;
import com.example.inventorychecker.manager.model.ProductModel;
import com.example.inventorychecker.manager.service.GetProductService;
import com.example.inventorychecker.module.CustomSpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreManagemant extends AppCompatActivity  {

    private Spinner sprinnerProduct;
    private ImageView imgProduct;
    private SearchView searchView;

    private CustomSpinnerAdapter adapter;
    private List<ProductModel> product = new ArrayList<ProductModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_managemant);

        initView();
        init();

        onSearch();
    }

    private void init() {

        GetProductService service = RestServiceManager.create(GetProductService.class);
        service.getProduct().enqueue(new Callback<ProductDao>() {
            @Override
            public void onResponse(Call<ProductDao> call, Response<ProductDao> response) {
                if (response.isSuccessful()) {
                    ProductDao dao = response.body();
                    if (dao != null) {
                        product = dao.getProduct();

                        adapter = new CustomSpinnerAdapter(StoreManagemant.this, 0, product);

                        sprinnerProduct.setAdapter(adapter);
                    }
                } else {
                    // failed
                }
            }

            @Override
            public void onFailure(Call<ProductDao> call, Throwable t) {

            }
        });

        sprinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Glide.with(getApplicationContext())
                        .load("http://forteamproject.esy.es/meen/inventory/images/RSL Srilver.jpg")
                        .into(imgProduct);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void onSearch(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                final List<ProductModel> filteredModelList = filter(product, query);
                adapter.setFilter(filteredModelList);
                return true;

            }

        });
    }

    private List<ProductModel> filter(List<ProductModel> models, String query) {
        query = query.toLowerCase();

        final List<ProductModel> filteredModelList = new ArrayList<>();
        for (ProductModel model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    private void initView() {
        sprinnerProduct = (Spinner) findViewById(R.id.spinerProduct);
        imgProduct = (ImageView) findViewById(R.id.imgProduct);
        searchView = (SearchView) findViewById(R.id.searchView);
    }
}
