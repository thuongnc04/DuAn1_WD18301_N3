package com.example.duan1_wd18301_n3.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.duan1_wd18301_n3.DAO.CustomerDAO;
import com.example.duan1_wd18301_n3.Models.Customer;
import com.example.duan1_wd18301_n3.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class CustomerAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Customer> customersList;

    CustomerDAO customerDAO;

    public CustomerAdapter(Context context, int layout, List<Customer> customersList) {
        this.context = context;
        this.layout = layout;
        this.customersList = customersList;
        customerDAO = new CustomerDAO(context);
    }

    @Override
    public int getCount() {
        return customersList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView textViewCustomerName =(TextView) view.findViewById(R.id.textViewCustomerName);
        Customer customer = customersList.get(i);
        textViewCustomerName.setText(customer.getName());

        Button buttonCustomerInfo = (Button) view.findViewById(R.id.buttonCustomerInfo);
        buttonCustomerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer customer = customersList.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông tin khách hàng");
                builder.setMessage(
                        "Họ và tên: " + customer.getName() + "\n"
                                + "Email: " + customer.getEmail() + "\n"
                                + "Tên đăng nhập: " + customer.getUsername() + "\n"
                                + "Địa chỉ: " + customer.getAddress() + "\n"
                                + "Số điện thoại: " + customer.getPhone());
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Button buttonCustomerBill = (Button) view.findViewById(R.id.buttonCustomerBill);
        buttonCustomerBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy khách hàng được chọn
                Customer customer = customersList.get(i);

                // Lấy danh sách hóa đơn của khách hàng
                Cursor cursor = customerDAO.getBillByCustomerID(customer.getId());

                // Hiển thị danh sách hóa đơn trong dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Hóa đơn của khách hàng");
                if (cursor != null && cursor.getCount() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (cursor.moveToNext()) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        double totalPrice = cursor.getDouble(cursor.getColumnIndex("total_price"));
                        String description = cursor.getString(cursor.getColumnIndex("description"));
                        String dateCreated = cursor.getString(cursor.getColumnIndex("date_created"));
                        stringBuilder.append("ID: " + id + "\n"
                                + "Tổng tiền: " + totalPrice + "\n"
                                + "Mô tả: " + description + "\n"
                                + "Ngày tạo: " + dateCreated + "\n\n");
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

        return view;
    }
}
