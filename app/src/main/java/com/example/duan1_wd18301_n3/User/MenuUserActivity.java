package com.example.duan1_wd18301_n3.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.duan1_wd18301_n3.Activities.MainActivity;
import com.example.duan1_wd18301_n3.Adapters.MenuUserAdapter;
import com.example.duan1_wd18301_n3.DAO.CategoryDAO;
import com.example.duan1_wd18301_n3.Models.Category;
import com.example.duan1_wd18301_n3.R;

import java.util.ArrayList;
import java.util.List;

public class MenuUserActivity extends AppCompatActivity {

    ListView lv;
    Button btnGioHang, btnHoaDon, btnDangXuat;
    List<Category> categoryList;
    MenuUserAdapter menuUserAdapter;
    CategoryDAO categoryDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);

        lv = (ListView) findViewById(R.id.lv_user_category);
        btnGioHang = (Button) findViewById(R.id.btnGioHang);
        btnHoaDon = (Button) findViewById(R.id.btnHoaDon);

        //
        ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list

// imageList.add(new SlideModel("String Url" or R.drawable);
// imageList.add(new SlideModel("String Url" or R.drawable, "title"); You can add title

        imageList.add(new SlideModel(R.drawable.anh1, "Vũ Minh Quân", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.anh2, "Tân Cảnh Phạm",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.anh3, "Hoàng Huy Hùng",ScaleTypes.CENTER_CROP));

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);

        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
        btnDangXuat = (Button) findViewById(R.id.btnDangXuat);
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(b);
                finish();
            }
        });
        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BillStatisticUserActivity.class);
                startActivity(intent);
            }
        });
        categoryList = new ArrayList<>();
        display();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), ProductListUserActivity.class);
                Category category = (Category) menuUserAdapter.getItem(i);
                intent.putExtra("category_id", category.getIdCategory());
                intent.putExtra("category_name", category.getNameCategory());
                startActivity(intent);
            }
        });
    }

    public void display() {
        categoryDAO = new CategoryDAO(this);
        categoryList = categoryDAO.getAllCategoriesWithIdCategory();
        menuUserAdapter = new MenuUserAdapter(this, R.layout.user_item_category, categoryList);
        lv.setAdapter(menuUserAdapter);
    }
}