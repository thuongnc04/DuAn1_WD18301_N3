package com.example.duan1_wd18301_n3.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1_wd18301_n3.DAO.CategoryDAO;
import com.example.duan1_wd18301_n3.DAO.ProductDAO;
import com.example.duan1_wd18301_n3.Models.Product;
import com.example.duan1_wd18301_n3.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ProductEditActivity extends AppCompatActivity {

    Product product;
    EditText txtProductName, txtQuantity, txtPrice;
    ImageView imgHinh;
    ProductDAO productDAO;
    CategoryDAO categoryDAO;
    Spinner spinnerCategory;
    int category;
    ImageButton ibnCamera, ibnFolder, ibtnBack;
    Button btnEdit, btnDelete;
    int id;
    int REQUEST_CODE_CAMERA = 454;
    int REQUEST_CODE_FOLDER = 352;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);

        txtProductName = (EditText) findViewById(R.id.txtProductName);
        txtQuantity = (EditText) findViewById(R.id.txtQuantity);
        txtPrice = (EditText) findViewById(R.id.txtPrice);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        ibnCamera = (ImageButton) findViewById(R.id.ibnCamera);
        ibnFolder = (ImageButton) findViewById(R.id.ibnFolder);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
        productDAO = new ProductDAO(this);
        categoryDAO = new CategoryDAO(this);
        loadSpinner();
        getData();

        ibtnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), ProductListActicity.class);
                startActivity(i);
                finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                category = categoryDAO.getCategoryIdByName(spinnerCategory.getSelectedItem().toString().trim());
                productDAO.editProduct(id, category, txtProductName.getText().toString(), Integer.parseInt(txtQuantity.getText().toString()), Integer.parseInt(txtPrice.getText().toString()), convertToArrayByte(imgHinh));
                Intent intent = new Intent(getApplicationContext(), ProductListActicity.class);
                startActivity(intent);
                Toast.makeText(ProductEditActivity.this, "Sửa hàng thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProductEditActivity.this);
                dialog.setTitle("XÓA SẢN PHẨM NÀY?");
                dialog.setMessage("Bạn thật sự muốn xóa sản phẩm này?");
                dialog.setPositiveButton("XÓA", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Intent intent = new Intent(getApplicationContext(), ProductListActicity.class);
                        startActivity(intent);
                        productDAO.deleteProduct(id);

                        Toast.makeText(ProductEditActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Hủy", null).show();
            }
        });

        ibnCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, REQUEST_CODE_CAMERA);
            }
        });
        ibnFolder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent in = new Intent(Intent.ACTION_PICK);
                in.setType("image/*");
                startActivityForResult(in, REQUEST_CODE_FOLDER);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK & data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);

        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK & data != null) {
            Uri uri = data.getData();
            try {
                InputStream ipstream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(ipstream);
                imgHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void loadSpinner() {

        CategoryDAO categorydao = new CategoryDAO(this);
        List<String> category  = categorydao.getAllNameCategoryForCreateProduct();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spinnerCategory.setAdapter(dataAdapter);

        product = (Product) getIntent().getSerializableExtra("Edit");
        int position = dataAdapter.getPosition(String.valueOf(product.getCategoryName()));
        spinnerCategory.setSelection(position);
    }

    public byte[] convertToArrayByte(ImageView img) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public void getData() {
        if (getIntent().getExtras() != null) {
            product = (Product) getIntent().getSerializableExtra("Edit");
            id = product.getId();
            byte[] hinhanh = product.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            imgHinh.setImageBitmap(bitmap);
            txtProductName.setText(product.getName().toString());
            txtQuantity.setText(String.valueOf(product.getQuantity()));
            txtPrice.setText(String.valueOf(product.getPrice()));

        }
    }
}