package com.example.duan1_wd18301_n3.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_wd18301_n3.Adapters.StatisticAdapter;
import com.example.duan1_wd18301_n3.DAO.StatisticDAO;
import com.example.duan1_wd18301_n3.Models.ProductStatistic;
import com.example.duan1_wd18301_n3.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StatisticActivity extends AppCompatActivity {

    Button buttonStartDate, buttonEndDate;
    Button buttonRevenue;
    TextView textViewStartDate, textViewEndDate;

    ListView lvRevenue;

    TextView textViewTotalBill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        buttonStartDate = findViewById(R.id.buttonDateStart);
        buttonEndDate = findViewById(R.id.buttonEndDate);
        buttonRevenue = findViewById(R.id.buttonRevenue);
        textViewStartDate = findViewById(R.id.textViewStartDate);
        textViewEndDate = findViewById(R.id.textViewEndDate);
        lvRevenue = findViewById(R.id.lvRevenue);

        textViewTotalBill = findViewById(R.id.textViewTotalBill);

        buttonStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(textViewStartDate);
            }
        });

        buttonEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(textViewEndDate);
            }
        });
        buttonRevenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(textViewEndDate.getText().equals("")  || textViewStartDate.getText().equals("")) {

                    Toast.makeText(StatisticActivity.this,"Vui lòng đủ chọn ngày bắt đầu và ngày kết thúc", Toast.LENGTH_SHORT).show();

                } else {
                    String startDate = formatDateString(textViewStartDate.getText().toString() + " 00:00:00");
                    String endDate = formatDateString(textViewEndDate.getText().toString() + " 23:59:59");
                    StatisticDAO statisticDAO = new StatisticDAO(StatisticActivity.this);

                    int totalBill = statisticDAO.getTotalBill(startDate, endDate);
                    List<ProductStatistic> productSales = statisticDAO.getProductStatistic();
                    if (totalBill == 0) {
                        textViewTotalBill.setText("Tổng doanh thu: "+totalBill+" VND");
                        Toast.makeText(StatisticActivity.this,"Không có sản phẩm nào được bán", Toast.LENGTH_SHORT).show();
                        productSales.clear();
                        StatisticAdapter statisticAdapter = new StatisticAdapter(StatisticActivity.this, R.layout.activity_statistic_list, productSales);
                        lvRevenue.setAdapter(statisticAdapter);
                    } else {
                        textViewTotalBill.setText("Tổng doanh thu: "+totalBill+" VND");
                        StatisticAdapter statisticAdapter = new StatisticAdapter(StatisticActivity.this, R.layout.activity_statistic_list,  productSales);
                        lvRevenue.setAdapter(statisticAdapter);
                    }
                }
            }

        });
    }

    private void showDatePickerDialog(final TextView textView) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // Lấy năm hiện tại
        int mMonth = c.get(Calendar.MONTH); // Lấy tháng hiện tại
        int mDay = c.get(Calendar.DAY_OF_MONTH); // Lấy ngày hiện tại
        DatePickerDialog datePickerDialog = new DatePickerDialog(StatisticActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        textView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    private String formatDateString(String dateString) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateString = null;
        try {
            Date date = inputDateFormat.parse(dateString);
            formattedDateString = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDateString;
    }
}