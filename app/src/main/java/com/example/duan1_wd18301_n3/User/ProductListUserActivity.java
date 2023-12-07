package com.example.duan1_wd18301_n3.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duan1_wd18301_n3.Adapters.ProductUserAdapter;
import com.example.duan1_wd18301_n3.DAO.ProductDAO;
import com.example.duan1_wd18301_n3.Models.Product;
import com.example.duan1_wd18301_n3.R;

import java.util.ArrayList;
import java.util.List;

public class ProductListUserActivity extends AppCompatActivity {

    TextView txtCategoryName;
    int categoryId;
    ListView lv;
    List<Product> productList;
    ProductDAO productDAO;
    ProductUserAdapter productUserAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_user);

        display();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), DetailProductActivity.class);
                Product product = (Product) productUserAdapter.getItem(i);
                intent.putExtra("product_id", product.getId());
                intent.putExtra("category_name", txtCategoryName.getText());
//                Toast.makeText(ProductListUserActivity.this, ""+product.getId(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    private void display() {
        txtCategoryName = (TextView) findViewById(R.id.txtCategoryname);
        lv = (ListView) findViewById(R.id.lv_user_product);
        productList = new ArrayList<>();

        Intent intent = getIntent(); // Lấy Intent hiện tại
        int category_id = intent.getIntExtra("category_id", 1);
        txtCategoryName.setText(intent.getStringExtra("category_name"));

        productDAO = new ProductDAO(this);
        productList = productDAO.getAllProductByCategoryId(category_id);

        productUserAdapter = new ProductUserAdapter(this, R.layout.user_item_product, productList);
        lv.setAdapter(productUserAdapter);
    }
}