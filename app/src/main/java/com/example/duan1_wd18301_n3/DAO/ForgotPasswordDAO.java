package com.example.duan1_wd18301_n3.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan1_wd18301_n3.Database.DbHelper;
import com.example.duan1_wd18301_n3.Email.SendEmail;

public class ForgotPasswordDAO extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final String DATABASE_NAME = "QUANLYDONHANG";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    DbHelper dbHelper;

    public ForgotPasswordDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean checkEmail(String email) {
        // Kiểm tra tài khoản trong bảng customer
        String[] columns = { "email" };
        String selection = "email" + " = ?";
        String[] selectionArgs = { email };
        Cursor cursor = db.query("customers", columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

    // PHẦN LẤY LẠI MẬT KHẨU
//    public void sendEmail(String email, String code) {
//        new SendEmail(email, code).execute();
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
