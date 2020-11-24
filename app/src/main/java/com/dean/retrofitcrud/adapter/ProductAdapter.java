package com.dean.retrofitcrud.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dean.retrofitcrud.R;
import com.dean.retrofitcrud.activity.ProductActivity;
import com.dean.retrofitcrud.model.PersonItem;
import com.dean.retrofitcrud.model.ResponseProduct;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<PersonItem> {
    private Context context;
    private List<PersonItem> personItem;

    public ProductAdapter(@NonNull Context context, int resource,
                          @NonNull List<PersonItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.personItem = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_item, parent, false);

        TextView tvIdProduct = v.findViewById(R.id.tv_product_id);
        TextView tvNameProduct = v.findViewById(R.id.tv_product_name);
        TextView tvPriceProduct = v.findViewById(R.id.tv_product_price);
        TextView tvDescProduct = v.findViewById(R.id.tv_product_desc);

        tvIdProduct.setText(String.valueOf(personItem.get(position).getId()));
        tvNameProduct.setText(String.valueOf(personItem.get(position).getName()));
        tvPriceProduct.setText(String.valueOf(personItem.get(position).getPrice()));
        tvDescProduct.setText(String.valueOf(personItem.get(position).getDesc()));


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("id", String.valueOf(personItem.get(position).getId()));
                intent.putExtra("name", personItem.get(position).getName());
                intent.putExtra("price", personItem.get(position).getPrice());
                intent.putExtra("desc", personItem.get(position).getDesc());
                context.startActivity(intent);
            }
        });

        return v;
    }
}
