package com.example.duan1_wd18301_n3.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan1_wd18301_n3.Database.DbHelper;

public class LoginDAO extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final String DATABASE_NAME = "QUANLYDONHANG";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    DbHelper dbHelper;

    public LoginDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public String checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Kiểm tra tài khoản trong bảng admin
        String[] columns = { "username" };
        String selection = "username" + " = ?" + " AND " + "password" + " = ?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query("admin", columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return "admin";
        }

        // Kiểm tra tài khoản trong bảng customer
        columns = new String[]{ "username" };
        selection = "username" + " = ?" + " AND " + "password" + " = ?";
        selectionArgs = new String[]{ username, password };
        cursor = db.query("customers", columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return "customer";
        }

        cursor.close();
        return "false";
    }

    public int getUserId(String username) {
        String query = "SELECT id FROM customers WHERE username = ?";
        Cursor c = db.rawQuery(query, new String[] {username});
        c.moveToFirst();
        int user_id = -1;
        if (!c.isAfterLast()) {
            user_id = c.getInt(0);
        }
        c.close();
        return user_id;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
