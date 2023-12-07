package com.example.duan1_wd18301_n3.User;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.duan1_wd18301_n3.Activities.MainActivity;
import com.example.duan1_wd18301_n3.Adapters.BillStatisticAdapter;
import com.example.duan1_wd18301_n3.DAO.BillDAO;
import com.example.duan1_wd18301_n3.DAO.BillStatisticDAO;
import com.example.duan1_wd18301_n3.DAO.CustomerDAO;
import com.example.duan1_wd18301_n3.Models.Bill;
import com.example.duan1_wd18301_n3.Models.BillStatistic;
import com.example.duan1_wd18301_n3.R;

import java.util.ArrayList;
import java.util.List;

public class BillStatisticUserActivity extends AppCompatActivity {

    ArrayList<BillStatistic> arrayBillStatistic = new ArrayList<BillStatistic>();
    BillStatisticAdapter adapter;
    Cursor cursor,cursorBill;
    BillStatisticDAO billStatisticDAO;
    CustomerDAO CustomerDAO;
    Context context;
    ListView lv;
    BillDAO billDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_statistic_user);

        lv = (ListView)findViewById(R.id.lvBill);
        billStatisticDAO = new BillStatisticDAO(this);
        CustomerDAO = new CustomerDAO(this);
        display();
        billDAO = new BillDAO(this);
        List<Bill> billIdList = billDAO.getAllBillByUserId(MainActivity.user_id);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @SuppressLint("Range")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor = billStatisticDAO.getDetailBillByID(billIdList.get(position).getId());
                AlertDialog.Builder builder = new AlertDialog.Builder(BillStatisticUserActivity.this); // sử dụng context của Activity hiện tại
                builder.setTitle("Chi tiết hóa đơn");
                if (cursor != null && cursor.getCount() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (cursor.moveToNext()) {
                        String idBill = cursor.getString(cursor.getColumnIndex("id"));
                        cursorBill = billStatisticDAO.getBillByID(cursor.getColumnIndex("bill_id"));
                        int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                        String productName = cursor.getString(cursor.getColumnIndex("product_name"));
                        float price = cursor.getFloat(cursor.getColumnIndex("price"));
                        stringBuilder.append("ID hóa đơn: " + idBill + "\n"
                                + "Tên sản phẩm: " + productName + "\n"
                                + "Số lượng: " + quantity + "\n"
                                + "Giá: " + price + "VND" + "\n\n");
                    }
                    builder.setMessage(stringBuilder.toString());
                } else {
                    builder.setMessage("Không tìm thấy hóa đơn nào.");
                }

                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void display(){
        CustomerDAO = new CustomerDAO(this);
//        CustomerHandler.fakeCustomerRecords();
//       CustomerHandler.fakeBills();

        cursor = billStatisticDAO.getBillByUserID(MainActivity.user_id);
        if (adapter==null){
            while (cursor.moveToNext()){
                arrayBillStatistic.add(new BillStatistic(cursor.getInt(0),CustomerDAO.getUserNameById(cursor.getInt(1)),cursor.getFloat(2), cursor.getString(3), cursor.getString(4)));
            }
            adapter = new BillStatisticAdapter(this, R.layout.item_list_bill, arrayBillStatistic);
            lv.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

    }
}