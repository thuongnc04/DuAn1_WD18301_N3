package com.example.duan1_wd18301_n3.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.duan1_wd18301_n3.Database.DbHelper;

public class RegisterDAO extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final String DATABASE_NAME = "QUANLYDONHANG";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    DbHelper dbHelper;

    public RegisterDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean register(String name, String email, String username, String password, String phone) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Insert into customers (name, email, username, password, phone) values (?, ?, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, name);
        statement.bindString(2, email);
        statement.bindString(3, username);
        statement.bindString(4, password);
        statement.bindString(5, phone);
        long rowId = statement.executeInsert();
        db.close();
        return (rowId != -1); // Trả về true nếu rowId khác -1 (tức là insert thành công), ngược lại trả về false
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
