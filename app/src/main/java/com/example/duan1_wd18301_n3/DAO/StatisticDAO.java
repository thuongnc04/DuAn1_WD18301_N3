package com.example.duan1_wd18301_n3.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan1_wd18301_n3.Database.DbHelper;
import com.example.duan1_wd18301_n3.Models.ProductStatistic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatisticDAO extends SQLiteOpenHelper {
    // Tên cơ sở dữ liệu
    private static final String DATABASE_NAME = "QUANLYDONHANG";
    // Phiên bản cơ sở dữ liệu
    private static final int DATABASE_VERSION = 1;
    SQLiteDatabase db;
    private Context context;
    DbHelper dbHelper;

    // Tên bảng

    private static final String TABLE_BILLS = "bills";
    private static final String TABLE_BILL_DETAIL = "bill_detail";


    // Các trường trong bảng Bills
    private static final String COLUMN_BILL_ID = "id";
    private static final String COLUMN_BILL_USER_ID = "user_id";
    private static final String COLUMN_BILL_TOTAL_PRICE = "total_price";
    private static final String COLUMN_BILL_DESCRIPTION = "description";
    private static final String COLUMN_BILL_DATE_CREATED = "date_created";

    // Các trường trong bảng Bill Detail
    private static final String COLUMN_BILL_DETAIL_ID = "id";
    private static final String COLUMN_BILL_DETAIL_BILL_ID = "bill_id";
    private static final String COLUMN_BILL_DETAIL_PRODUCT_NAME = "product_name";
    private static final String COLUMN_BILL_DETAIL_QUANTITY = "quantity";
    private static final String COLUMN_BILL_DETAIL_PRICE = "price";

    public StatisticDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int getTotalBill(String start_date, String end_date) {
        int total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM(" + COLUMN_BILL_TOTAL_PRICE + ") FROM " + TABLE_BILLS + " WHERE " + COLUMN_BILL_DATE_CREATED + " BETWEEN ? AND ? ";
        Cursor cursor = db.rawQuery(query, new String[]{start_date, end_date});
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return total;
    }
    public void fakeBills() {
        SQLiteDatabase db = getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 1; i <= 10; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_BILL_USER_ID, i);
            values.put(COLUMN_BILL_TOTAL_PRICE, i * 105000);
            values.put(COLUMN_BILL_DESCRIPTION, "Bill #" + i);
            String currentDateTimeString = sdf.format(new Date(System.currentTimeMillis()));
            values.put(COLUMN_BILL_DATE_CREATED, currentDateTimeString);
            db.insert(TABLE_BILLS, null, values);
        }
        db.close();
    }

    public List<ProductStatistic> getProductStatistic() {
        List<ProductStatistic> productSales = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_BILL_DETAIL_PRODUCT_NAME + ", SUM(" + COLUMN_BILL_DETAIL_QUANTITY + "), SUM(" + COLUMN_BILL_DETAIL_PRICE + " * " + COLUMN_BILL_DETAIL_QUANTITY + ") " +
                "FROM " + TABLE_BILL_DETAIL +
                " GROUP BY " + COLUMN_BILL_DETAIL_PRODUCT_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String productName = cursor.getString(0);
                int quantity = cursor.getInt(1);
                double totalSales = cursor.getDouble(2);

                ProductStatistic productStatistic = new ProductStatistic(productName, quantity, totalSales);
                productSales.add(productStatistic);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productSales;
    }
    public void fakeBillDetail() {
        SQLiteDatabase db = getWritableDatabase();
        // Tạo 10 bản ghi mẫu cho bảng Bill Detail
        for (int i = 1; i <= 20; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_BILL_DETAIL_BILL_ID, i);
            values.put(COLUMN_BILL_DETAIL_PRODUCT_NAME, "Sản phẩm " + i);
            values.put(COLUMN_BILL_DETAIL_QUANTITY, i );
            values.put(COLUMN_BILL_DETAIL_PRICE, i );
            db.insert(TABLE_BILL_DETAIL, null, values);
        }

        db.close();
    }
    public void deleteBillDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BILL_DETAIL, null, null);
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
