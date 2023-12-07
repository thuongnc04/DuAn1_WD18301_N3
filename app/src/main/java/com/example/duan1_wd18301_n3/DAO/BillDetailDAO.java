package com.example.duan1_wd18301_n3.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan1_wd18301_n3.Database.DbHelper;
import com.example.duan1_wd18301_n3.Models.BillDetail;

public class BillDetailDAO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QUANLYDONHANG";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    DbHelper dbHelper;

    public BillDetailDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DbHelper(context); // tạo db
    }

    public int insertBillDetail(BillDetail billDetail) {
        ContentValues values = new ContentValues();
        values.put("bill_id", billDetail.getBill_id());
        values.put("product_name", billDetail.getProduct_name());
        values.put("quantity", billDetail.getQuantity());
        values.put("price", billDetail.getPrice());

        long result = dbHelper.getWritableDatabase().insert("bill_detail", null, values);
        if (result <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public BillDetail getBillDetailByBillId(int bill_id) {
        BillDetail billDetail = new BillDetail();
        String query = "SELECT * FROM bill_detail WHERE bill_id = ?";

        Cursor c = dbHelper.getReadableDatabase().rawQuery(query, new String[]{"" + bill_id});
        c.moveToFirst();

        billDetail.setId(c.getInt(0));
        billDetail.setBill_id(c.getInt(1));
        billDetail.setProduct_name(c.getString(2));
        billDetail.setQuantity(c.getInt(3));
        billDetail.setPrice(c.getInt(4));

        c.close();
        return billDetail;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
