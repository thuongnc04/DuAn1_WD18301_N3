package com.example.duan1_wd18301_n3.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.duan1_wd18301_n3.Activities.MainActivity;
import com.example.duan1_wd18301_n3.R;

public class MenuAdminActivity extends AppCompatActivity {
    CardView cvCategory;
    CardView cvProduct;
    CardView cvUser;
    CardView cvBill;
    CardView cvStatistic;
    ImageButton btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        cvCategory = (CardView) findViewById(R.id.card_category);
        cvProduct = (CardView) findViewById(R.id.card_product);
        cvUser = (CardView) findViewById(R.id.card_customer);
        cvBill = (CardView) findViewById(R.id.card_bill);
        cvStatistic = (CardView) findViewById(R.id.card_chart);
        btnLogout = (ImageButton) findViewById(R.id.btnLogout);

        cvCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(i);
            }
        });

        cvProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), ProductListActicity.class);
                startActivity(i);
            }
        });
        cvBill.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), BillStatisticActivity.class);
                startActivity(i);
            }
        });
        cvStatistic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), StatisticActivity.class);
                startActivity(i);
            }
        });
        cvUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent b = new Intent(getApplicationContext(), CustomerListActivity.class);
                startActivity(b);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent b = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(b);
                finish();
            }
        });
    }
}