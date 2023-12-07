package com.example.duan1_wd18301_n3.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.duan1_wd18301_n3.Database.DbHelper;
import com.example.duan1_wd18301_n3.Models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final String DATABASE_NAME = "QUANLYDONHANG";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    DbHelper dbHelper;

    public ProductDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public Cursor getAllProduct() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM products;", null);
        return cursor;
    }

    public void createProduct(int category_id, String name, int quantity,
                              int price, byte[] image) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Insert into products (category_id, name, quantity, price, image) values (?, ?, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindLong(1, category_id);
        statement.bindString(2, name);
        statement.bindLong(3, quantity);
        statement.bindLong(4, price);
        statement.bindBlob(5, image);
        statement.executeInsert();
        db.close();
    }

    public void editProduct(int id, int category_id, String name, int quantity,
                            int price, byte[] image) {

        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE products SET category_id = ? , name = ? , quantity = ?, price = ?, image = ? Where id = ? ";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindLong(1, category_id);
        statement.bindString(2, name);
        statement.bindLong(3, quantity);
        statement.bindLong(4, price);
        statement.bindBlob(5, image);
        statement.bindLong(6, id);
        statement.execute();
        db.close();
    }

    public void editQuantity(int product_id, int quantity) {
        String query = "UPDATE products SET quantity = quantity - ? WHERE id = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.clearBindings();
        statement.bindLong(1, quantity);
        statement.bindLong(2, product_id);
        statement.execute();
    }

    public void deleteProduct(int i) {
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete("products", "id" + " = " + i, null);
    }

    public Product findById(int id) {
        String query = "SELECT * FROM products WHERE id = ?";
        Cursor c = dbHelper.getReadableDatabase().rawQuery(query, new String[] {"" + id});
        c.moveToFirst();

        Product product = new Product();
        product.setId(id);
        product.setName(c.getString(2));
        product.setQuantity(c.getInt(3));
        product.setPrice(c.getInt(4));
        product.setImage(c.getBlob(5));

        return product;
    }

    public List<Product> getAllProductByCategoryId(int category_id) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category_id = ?";
        Cursor c = dbHelper.getReadableDatabase().rawQuery(query, new String[] {"" + category_id});
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Product product = new Product();
            product.setId(c.getInt(0));
            product.setName(c.getString(2));
            product.setQuantity(c.getInt(3));
            product.setPrice((int) c.getFloat(4));
            product.setImage(c.getBlob(5));

            productList.add(product);
            c.moveToNext();
        }
        c.close();
        return productList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
