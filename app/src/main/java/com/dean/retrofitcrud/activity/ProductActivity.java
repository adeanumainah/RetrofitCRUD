package com.dean.retrofitcrud.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dean.retrofitcrud.R;
import com.dean.retrofitcrud.model.PersonItem;
import com.dean.retrofitcrud.remote.APIUtils;
import com.dean.retrofitcrud.remote.ProductServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    ProductServices productService;
    EditText etName, etPrice, etDesc, etId;
    Button btnSave, btnDel;
    TextView tvId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etName = findViewById(R.id.et_name);
        etPrice = findViewById(R.id.et_price);
        etDesc = findViewById(R.id.et_desc);
        btnSave = findViewById(R.id.btn_save);
        btnDel = findViewById(R.id.btn_delete);
        etId = findViewById(R.id.et_id);
        tvId = findViewById(R.id.tv_id);

        productService = APIUtils.getProductServices();

        Bundle extras = getIntent().getExtras();
        String productName = extras.getString("name");
        String productPrice = extras.getString("price");
        String productDesc = extras.getString("desc");
        final String productID = extras.getString("id");

        etId.setText(productID);
        etName.setText(productName);
        etPrice.setText(productPrice);
        etDesc.setText(productDesc);

        if (productID != null && productID.trim().length() > 0){
            etId.setFocusable(false);
        } else {
            tvId.setVisibility(View.INVISIBLE);
            etId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(view -> {

            String name = etName.getText().toString();
            String price = etPrice.getText().toString();
            String desc = etDesc.getText().toString();

            if (productID != null && productID.trim().length() >0){
                    updateProduct(Integer.parseInt(productID), name, price, desc);
            } else {
                addProduct(name, price, desc);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProduct(Integer.parseInt(productID));
                Intent intent = new Intent(ProductActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addProduct(String name, String price, String desc) {
        Call<PersonItem> call = productService.addProduct(name, price, desc);
        call.enqueue(new Callback<PersonItem>() {
            @Override
            public void onResponse(Call<PersonItem> call, Response<PersonItem> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ProductActivity.this, "product added",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProductActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<PersonItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void updateProduct(int id, String name, String price, String desc) {
        Call<PersonItem> call = productService.updateProduct(id, name, price, desc);
        call.enqueue(new Callback<PersonItem>() {
            @Override
            public void onResponse(Call<PersonItem> call, Response<PersonItem> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ProductActivity.this, "Product Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProductActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<PersonItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void deleteProduct(int id) {
        Call<PersonItem> call = productService.deleteProduct(id);
        call.enqueue(new Callback<PersonItem>() {
            @Override
            public void onResponse(Call<PersonItem> call, Response<PersonItem> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ProductActivity.this, "Product deleted",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PersonItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}