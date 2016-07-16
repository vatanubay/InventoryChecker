package com.example.inventorychecker.module.store;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.inventorychecker.Utils.Constant;
import com.example.inventorychecker.activity.R;
import com.example.inventorychecker.manager.model.ProductModel;

import java.util.List;

/**
 * Created by HP on 7/16/2016.
 */
public class CustomViewProductAdapter extends RecyclerView.Adapter<CustomViewProductAdapter.ProductViewHolder> {

    private List<ProductModel> items;
    private Context context;

    public CustomViewProductAdapter(Context context, List<ProductModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if(position < items.size()){
            holder.txtBrand.setText(items.get(position).getBrand());
            holder.txtAmount.setText(items.get(position).getAmount());

            Glide.with(context)
                    .load(Constant.PATH_URL_IMAGE + items.get(position).getImageName())
                    .into(holder.imageProduct);
        }
    }

    @Override
    public int getItemCount() {
        if(items != null)
            return items.size();
        return 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imageProduct;
        TextView txtBrand;
        TextView txtAmount;

        public ProductViewHolder(View itemView) {
            super(itemView);
            imageProduct = (ImageView) itemView.findViewById(R.id.imageProduct);
            txtBrand = (TextView) itemView.findViewById(R.id.txtBrand);
            txtAmount = (TextView) itemView.findViewById(R.id.txtAmount);
        }
    }
}
