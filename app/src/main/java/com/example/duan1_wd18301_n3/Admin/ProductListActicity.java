package com.example.duan1_wd18301_n3.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.duan1_wd18301_n3.Adapters.ProductAdapter;
import com.example.duan1_wd18301_n3.DAO.CategoryDAO;
import com.example.duan1_wd18301_n3.DAO.ProductDAO;
import com.example.duan1_wd18301_n3.Models.Product;
import com.example.duan1_wd18301_n3.R;

import java.util.ArrayList;

public class ProductListActicity extends AppCompatActivity {

    Button btnCreateProduct;
    ImageButton btnBackToMenu;
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    ProductAdapter productAdapter;
    Cursor cursor;
    ProductDAO productDAO;
    CategoryDAO categoryDAO;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_acticity);

        lv = (ListView) findViewById(R.id.lv_Product);
        btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);
        btnBackToMenu = (ImageButton) findViewById(R.id.btnBackToMenu);
        productDAO = new ProductDAO(this);
        categoryDAO = new CategoryDAO(this);
        display();

        btnBackToMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), MenuAdminActivity.class);
                startActivity(i);
                finish();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(ProductListActicity.this, ProductEditActivity.class);
                intent.putExtra("Edit", productAdapter.getItem(arg2));
                startActivity(intent);
            }
        });

        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), ProductCreateActivity.class);
                startActivity(i);
            }
        });
    }

    public void display() {
        cursor = productDAO.getAllProduct();
        while (cursor.moveToNext()){
            productArrayList.add(new Product(cursor.getInt(0), categoryDAO.getCategoryNameById(cursor.getInt(1)), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getBlob(5)));
        }
        productAdapter = new ProductAdapter(this, R.layout.item_product, productArrayList);
        lv.setAdapter(productAdapter);
    }
}