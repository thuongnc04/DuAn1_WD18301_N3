package com.example.duan1_wd18301_n3.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan1_wd18301_n3.DAO.CategoryDAO;
import com.example.duan1_wd18301_n3.Models.Category;
import com.example.duan1_wd18301_n3.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    EditText txtNameCategory,txtIDCategory;
    ListView lv;
    Button btnCreate, btnEdit, btnDelete;
    ArrayAdapter<String> arrayAdapter;
    List<String> list = new ArrayList<String>();
    Context content;
    CategoryDAO categoryDAO;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        txtNameCategory = (EditText) findViewById(R.id.txtNameCategory);
        txtIDCategory = (EditText) findViewById(R.id.txtId);
        lv = (ListView) findViewById(R.id.lvCategory);
        btnCreate = findViewById(R.id.add);
        btnEdit = findViewById(R.id.edit);
        btnDelete = findViewById(R.id.delete);
        btnBack = (ImageButton)findViewById(R.id.btnBack);
        //Khởi tạo biến
        content = this;
        //Hiển thị khi chạy chương trình
        display();
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), MenuAdminActivity.class);
                startActivity(i);
                finish();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy ra giá trị của item được chọn
                String selectedItem = (String) parent.getItemAtPosition(position);
                String[] arr = selectedItem.split(" - ");
                txtIDCategory.setText(arr[0]);
                txtNameCategory.setText(arr[1]);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputString = txtNameCategory.getText().toString();
                if(!inputString.isEmpty()){
                    Category category = new Category();
                    category.setNameCategory(txtNameCategory.getText().toString());
                    int kq = categoryDAO.insertCategory(category);
                    if(kq == -1){
                        Toast.makeText(content,"Insert Thất Bại",Toast.LENGTH_LONG).show();
                    }else {
                        String text = null;
                        txtNameCategory.setText(text);
                        txtIDCategory.setText(text);
                        display();
                        Toast.makeText(content,"Insert Thành Công",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(content,"Điền đầy đủ thông tin trước khi tạo",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog =	new AlertDialog.Builder(content);
                dialog.setTitle("XÓA DANH MỤC NÀY?");
                dialog.setMessage("Bạn thật sự muốn xóa danh mục này?");
                dialog.setPositiveButton("XÓA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        String inputString = txtNameCategory.getText().toString();
                        String idString = txtIDCategory.getText().toString();
                        if(!inputString.isEmpty() && !idString.isEmpty()){
                            String _id = txtIDCategory.getText().toString();
                            int kq = categoryDAO.deleteCategory(_id);
                            if(kq == -1){
                                Toast.makeText(content,"Delete Thất Bại",Toast.LENGTH_LONG).show();
                            }else {
                                String text = null;
                                txtNameCategory.setText(text);
                                txtIDCategory.setText(text);
                                display();
                                Toast.makeText(content,"Delete Thành Công",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(content,"Chọn danh mục muốn xóa",Toast.LENGTH_LONG).show();
                        }
                    }
                }).setNegativeButton("Hủy", null).show();

            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputString = txtNameCategory.getText().toString();
                String idString = txtIDCategory.getText().toString();
                if(!inputString.isEmpty() && !idString.isEmpty()){
                    Category category = new Category();
                    category.setNameCategory(txtNameCategory.getText().toString());
                    category.setIdCategory(Integer.parseInt(txtIDCategory.getText().toString()));

                    int kq = categoryDAO.updateCategory(category);
                    if(kq == -1){
                        Toast.makeText(content,"Update Thất Bại",Toast.LENGTH_LONG).show();
                    }else {
                        String text = null;
                        txtIDCategory.setText(text);
                        txtNameCategory.setText(text);
                        display();
                        Toast.makeText(content,"Update Thành Công",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(content,"Chọn danh mục muốn sửa",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    protected void display(){
        list.clear();
        categoryDAO = new CategoryDAO(content);
        list = categoryDAO.getAllCategory();
        arrayAdapter = new ArrayAdapter<>(content, android.R.layout.simple_list_item_1,list);
        lv.setAdapter(arrayAdapter);
    }
}