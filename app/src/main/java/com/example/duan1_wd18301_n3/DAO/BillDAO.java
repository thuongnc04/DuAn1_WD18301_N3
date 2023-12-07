package com.example.duan1_wd18301_n3.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan1_wd18301_n3.Database.DbHelper;
import com.example.duan1_wd18301_n3.Models.Bill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillDAO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QUANLYDONHANG";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    DbHelper dbHelper;
    public BillDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DbHelper(context); // tạo db
    }

    public int insertBill(Bill bill) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put("user_id", bill.getUser_id());
        values.put("total_price", bill.getTotal_price());
        values.put("description", bill.getDescription());
        String currentDateTimeString = sdf.format(new Date(System.currentTimeMillis()));
        values.put("date_created", currentDateTimeString);
        long result = dbHelper.getWritableDatabase().insert("bills", null, values);
        if (result <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public List<Bill> getAllBillByUserId(int user_id) {
        List<Bill> billList = new ArrayList<>();
        String query = "SELECT * FROM bills WHERE user_id = ?";
        Cursor c = dbHelper.getReadableDatabase().rawQuery(query, new String[]{"" + user_id});
        c.moveToFirst();
        while(!c.isAfterLast()) {
            Bill bill = new Bill();
            bill.setId(c.getInt(0));
            bill.setUser_id(c.getInt(1));
            bill.setTotal_price(c.getInt(2));
            bill.setDescription(c.getString(3));
            bill.setDate_created(c.getString(4));

            billList.add(bill);
            c.moveToNext();
        }
        c.close();
        return billList;
    }

    public int getBillIdNew() {
        int bill_id = -1;
        String query = "SELECT id FROM bills ORDER BY id DESC LIMIT 1;";

        Cursor c = dbHelper.getReadableDatabase().rawQuery(query, null);
        c.moveToFirst();
        if (!c.isAfterLast()) {
            bill_id = c.getInt(0);
        }
        bill_id = c.getInt(0);
        c.close();
        return bill_id;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
