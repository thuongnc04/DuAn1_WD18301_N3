package com.example.duan1_wd18301_n3.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.duan1_wd18301_n3.Adapters.BillStatisticAdapter;
import com.example.duan1_wd18301_n3.DAO.BillStatisticDAO;
import com.example.duan1_wd18301_n3.DAO.CustomerDAO;
import com.example.duan1_wd18301_n3.Models.BillStatistic;
import com.example.duan1_wd18301_n3.R;

import java.util.ArrayList;

public class BillStatisticActivity extends AppCompatActivity {

    ArrayList<BillStatistic> arrayBillStatistic = new ArrayList<BillStatistic>();
    BillStatisticAdapter adapter;
    Cursor cursor,cursorBill;
    BillStatisticDAO billStatisticDAO;
    CustomerDAO CustomerDAO;
    Context context;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_statistic);

        lv = (ListView)findViewById(R.id.lvBill);
        billStatisticDAO = new BillStatisticDAO(this);
        CustomerDAO = new CustomerDAO(this);
        display();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor = billStatisticDAO.getDetailBillByID(position + 1);
                AlertDialog.Builder builder = new AlertDialog.Builder(BillStatisticActivity.this); // sử dụng context của Activity hiện tại
                builder.setTitle("Chi tiết hóa đơn");
                if (cursor != null && cursor.getCount() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (cursor.moveToNext()) {
                        String idBill = cursor.getString(cursor.getColumnIndex("id"));
                        cursorBill = billStatisticDAO.getBillByID(cursor.getColumnIndex("bill_id"));
                        int userIdIndex = cursorBill.getColumnIndex("user_id");
                        int userId = cursor.getInt(userIdIndex);
                        String userName = CustomerDAO.getUserNameById(userId);
                        int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                        String productName = cursor.getString(cursor.getColumnIndex("product_name"));
                        float price = cursor.getFloat(cursor.getColumnIndex("price"));
                        stringBuilder.append("ID hóa đơn: " + idBill + "\n"
                                + "Tên sản phẩm: " + productName + "\n"
                                + "Số lượng: " + quantity + "\n"
                                + "Tên người dùng: " + userName + "\n"
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
//        CustomerHandler.fakeBills();

        cursor = billStatisticDAO.getAllBillStatistic();
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