package com.example.inventorychecker.module.store;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.inventorychecker.activity.R;
import com.example.inventorychecker.manager.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 6/12/2016.
 */
public class CustomSpinnerNameAdapter extends ArrayAdapter<ProductModel>{

    private List<ProductModel> items;
    private Context context;

    public CustomSpinnerNameAdapter(Context context, int resource, List<ProductModel> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        if(items != null)
            return items.size();
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.view_item_spinner, parent, false);

            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtViewTitle);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtTitle.setText(items.get(position).getName());

        return convertView;
    }

    class ViewHolder{
        private TextView txtTitle;
    }
}
