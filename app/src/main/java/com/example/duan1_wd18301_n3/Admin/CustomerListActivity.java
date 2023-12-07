package com.example.duan1_wd18301_n3.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.duan1_wd18301_n3.Adapters.CustomerAdapter;
import com.example.duan1_wd18301_n3.DAO.CustomerDAO;
import com.example.duan1_wd18301_n3.Models.Customer;
import com.example.duan1_wd18301_n3.R;

import java.util.ArrayList;

public class CustomerListActivity extends AppCompatActivity {

    Cursor cursor;
    ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
    CustomerAdapter customerAdapter;
    ListView lv;
    CustomerDAO customerDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        customerDAO = new CustomerDAO(this);

        lv = (ListView) findViewById(R.id.lv_Customer);
        display();
    }

    public void display(){
        cursor = customerDAO.getAllCustomer();

        if(customerAdapter ==null){
            while (cursor.moveToNext()){
                customerArrayList.add(new Customer(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
            }
            customerAdapter = new CustomerAdapter(this, R.layout.activity_customer_management, customerArrayList);
            lv.setAdapter(customerAdapter);
        }else{
            customerAdapter.notifyDataSetChanged();
        }

    }
}