package com.example.duan1_wd18301_n3.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan1_wd18301_n3.Database.DbHelper;
import com.example.duan1_wd18301_n3.Models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QUANLYDONHANG";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    DbHelper dbHelper;

    public CategoryDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DbHelper(context); // tạo db
    }

    public int insertCategory(Category category) {
        ContentValues values = new ContentValues(); //tạo đối tượng chứa dữ liệu
        //đưa dữ liệu vào đối tượng chứa
        values.put("name", category.getNameCategory());
        //Thực thi insert
        long kq = dbHelper.getWritableDatabase().insert("categories", null, values);
        if (kq <= 0) {
            return -1;
        }
        return 1;
    }

    public List<String> getAllCategory() {
        List<String> ls = new ArrayList<>(); //tạo danh sách rỗng
        //Tạo con trỏ đọc dl trong bảng
        Cursor c = dbHelper.getReadableDatabase().query("categories", null, null, null, null, null, null);
        c.moveToFirst(); //di chuyển con trỏ về bản ghi đầu tiên
        //đọc
        while (c.isAfterLast() == false) { // lặp đến bản ghi cuối
            Category category = new Category(); // tạo đối tượng
            category.setIdCategory(c.getInt(0)); //đọc mã category
            category.setNameCategory(c.getString(1)); //đọc tên category
            //Chuyển thành chuỗi
            String chuoi = category.getIdCategory() + " - " + category.getNameCategory();
            //đưa chuỗi vào list
            ls.add(chuoi);
            c.moveToNext();
        }
        c.close(); //đóng trỏ
        return ls;
    }

    public List<String> getAllNameCategoryForCreateProduct() {
        List<String> listCategory = new ArrayList<String>();
        String selectQuery = "SELECT * FROM categories;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                listCategory.add(cursor.getString(1));

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return listCategory;
    }

    public Integer getCategoryIdByName(String categoryName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Integer categoryId = null;

        String[] columns = {"id"};
        String selection = "name" + "=?";
        String[] selectionArgs = {categoryName};
        Cursor cursor = db.query("categories", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            categoryId = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return categoryId;
    }

    public String getCategoryNameById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT name FROM categories WHERE id = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        String categoryName = null;
        if (cursor.moveToFirst()) {
            categoryName = cursor.getString(0);
        }
        cursor.close();
        return categoryName;
    }

    public int deleteCategory(String _id) {
        int kq = dbHelper.getWritableDatabase().delete("categories", "id=?",
                new String[] {_id});
        if (kq <= 0) {
            return -1;
        }
        return 1;
    }

    public int updateCategory(Category category) {
        ContentValues values = new ContentValues(); //tạo đối tượng chứa dữ liệu
        //đưa dữ liệu vào đối tượng chứa
        values.put("name", category.getNameCategory());
        //Thực thi insert
        long kq = dbHelper.getWritableDatabase().update("categories",values,"id=?",
                new String[]{Integer.toString(category.getIdCategory())});
        if (kq <= 0) {
            return -1;
        }
        return 1;
    }

    public List<Category> getAllCategoriesWithIdCategory() {
        List<Category> ls = new ArrayList<>();
        Cursor c = dbHelper.getReadableDatabase().query("categories", null, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Category category = new Category();
            category.setIdCategory(c.getInt(0));
            category.setNameCategory(c.getString(1));

            ls.add(category);
            c.moveToNext();
        }
        c.close();
        return ls;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
