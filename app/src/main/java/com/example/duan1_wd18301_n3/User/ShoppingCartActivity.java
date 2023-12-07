package com.example.duan1_wd18301_n3.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duan1_wd18301_n3.Activities.MainActivity;
import com.example.duan1_wd18301_n3.Adapters.CartAdapter;
import com.example.duan1_wd18301_n3.DAO.BillDAO;
import com.example.duan1_wd18301_n3.DAO.BillDetailDAO;
import com.example.duan1_wd18301_n3.DAO.ProductDAO;
import com.example.duan1_wd18301_n3.Models.Bill;
import com.example.duan1_wd18301_n3.Models.BillDetail;
import com.example.duan1_wd18301_n3.Models.Cart;
import com.example.duan1_wd18301_n3.Models.Product;
import com.example.duan1_wd18301_n3.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    ListView lv;
    ProductDAO productDAO;
    BillDAO billDAO;
    BillDetailDAO billDetailDAO;


    List<Cart> cartList;
    Button buttonthanhtoan, btnUserMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        lv = (ListView) findViewById(R.id.listviewgiohang);
        buttonthanhtoan = (Button) findViewById(R.id.buttonthanhtoan);
        btnUserMenu = (Button) findViewById(R.id.btnUserMenu);
        btnUserMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MenuUserActivity.class);
                startActivity(i);
                finish();
            }
        });
        cartList = new ArrayList<>();

        if (CartActivity.listItem != null) {
            ProductDAO productDAO = new ProductDAO(this);
            for (int i = 0; i < CartActivity.listItem.size(); i++) {
                Product product = productDAO.findById(CartActivity.listItem.get(i).getProduct_id());
                Cart cart = new Cart();
                cart.setId(product.getId());
                cart.setHinhAnh(product.getImage());
                cart.setTenSP(product.getName());
                cart.setGiaTien(product.getPrice());
                cart.setSoLuong(CartActivity.listItem.get(i).getQuantity());
                cartList.add(cart);
            }
        }
        CartAdapter cartAdapter = new CartAdapter(this, R.layout.item_user_cart_product, cartList);
        lv.setAdapter(cartAdapter);

        // Thanh toán toàn bộ sản phẩm
        buttonthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CartActivity.listItem == null || CartActivity.listItem.size() == 0) {

                    Toast.makeText(ShoppingCartActivity.this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();

                } else {

                    List<Product> productList = new ArrayList<>();
                    List<Integer> quantityList = new ArrayList<>();

                    productDAO = new ProductDAO(ShoppingCartActivity.this);
                    billDAO = new BillDAO(ShoppingCartActivity.this);
                    billDetailDAO = new BillDetailDAO(ShoppingCartActivity.this);

                    int total_price = 0;
                    for (int i = 0; i < CartActivity.listItem.size(); i++) {
                        Product product = productDAO.findById(CartActivity.listItem.get(i).getProduct_id());
                        productList.add(product);

                        int quantity = CartActivity.listItem.get(i).getQuantity();
                        total_price += product.getPrice() * quantity;

                        quantityList.add(quantity);
                    }

                    Bill bill = new Bill();
                    bill.setUser_id(MainActivity.user_id);
                    //bill.setTotal_price((int) (total_price * 1.1));
                    bill.setTotal_price(total_price);
                    bill.setDescription("Thanh toán cả giỏ hàng");

                    Date now = new Date();
                    bill.setDate_created(now.toString());


                    if (billDAO.insertBill(bill) == 1) {
                        int bill_id = billDAO.getBillIdNew();

                        // Nếu thêm thành công thì tiến hành thêm chi tiết sản phẩm
                        Toast.makeText(ShoppingCartActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < productList.size(); i++) {
                            BillDetail billDetail = new BillDetail();
                            billDetail.setBill_id(bill_id);
                            billDetail.setProduct_name(productList.get(i).getName());
                            int quantity = quantityList.get(i);
                            billDetail.setQuantity(quantity);
                            billDetail.setPrice(productList.get(i).getPrice());
                            billDetailDAO.insertBillDetail(billDetail);

                            productDAO.editQuantity(productList.get(i).getId(), quantity);
                        }
                        CartActivity.listItem.clear();
                        cartList.clear();
                        cartAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ShoppingCartActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}