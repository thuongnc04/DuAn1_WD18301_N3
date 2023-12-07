package com.example.duan1_wd18301_n3.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_wd18301_n3.Activities.MainActivity;
import com.example.duan1_wd18301_n3.DAO.BillDAO;
import com.example.duan1_wd18301_n3.DAO.BillDetailDAO;
import com.example.duan1_wd18301_n3.DAO.ProductDAO;
import com.example.duan1_wd18301_n3.Models.Bill;
import com.example.duan1_wd18301_n3.Models.BillDetail;
import com.example.duan1_wd18301_n3.Models.Cart;
import com.example.duan1_wd18301_n3.Models.Product;
import com.example.duan1_wd18301_n3.R;
import com.example.duan1_wd18301_n3.User.CartActivity;

import java.util.Date;
import java.util.List;

public class CartAdapter extends BaseAdapter {

    BillDAO billDAO;
    BillDetailDAO billDetailDAO;
    ProductDAO productDAO;
    Context context;
    static int quantity_save = 1;
    int layout;
    List<Cart> cartList;
    private List<Cart> itemList;

    public CartAdapter(Context context, int layout, List<Cart> cartList) {
        this.context = context;
        this.layout = layout;
        this.cartList = cartList;
    }
    @Override
    public int getCount() {
        return cartList.size();
    }

    @Override
    public Object getItem(int i) {
        return cartList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return cartList.get(i).getId();
    }

    public void reload(List<Cart> itemList) {
        this.cartList = itemList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;


        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, viewGroup, false);

            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.productImage);
            holder.txtProductName = view.findViewById(R.id.txtProductName);
            holder.txtProductPrice = view.findViewById(R.id.txtProductPrice);
            holder.txtProductQuantityIncrease = view.findViewById(R.id.txtProductQuantityIncrease);
            holder.txtProductQuantity = view.findViewById(R.id.txtProductQuantity);
            holder.txtProductQuantityDecrease = view.findViewById(R.id.txtProductQuantityDecrease);
            holder.btnThanhToan = view.findViewById(R.id.btnThanhToan);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Cart cart = (Cart) getItem(i);
        byte[] cartImage = cart.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(cartImage, 0, cartImage.length);
        holder.imageView.setImageBitmap(bitmap);
        holder.txtProductName.setText(cart.getTenSP());
        holder.txtProductPrice.setText("" + cart.getGiaTien());
        holder.txtProductQuantity.setText("" + cart.getSoLuong());
        quantity_save = Integer.parseInt(holder.txtProductQuantity.getText().toString());

        // click nút giảm
        holder.txtProductQuantityDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(holder.txtProductQuantity.getText().toString()) - 1;
                if (quantity <= 0) {
                    cartList.remove(cart);
                    CartActivity.removeByProductId(cart.getId());
                    Toast.makeText(v.getContext(), "Đã xóa sản phẩm khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
                    quantity = 0;
                    notifyDataSetChanged();
                } else {
                    holder.txtProductQuantity.setText("" + quantity);
                    cartList.get(i).setSoLuong(quantity);
                    CartActivity.setQuantityByProductId(cart.getId(), quantity);
                }
            }
        });

        // click nút cộng
        holder.txtProductQuantityIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(holder.txtProductQuantity.getText().toString()) + 1;
                cartList.get(i).setSoLuong(quantity);
                holder.txtProductQuantity.setText("" + quantity);
                CartActivity.setQuantityByProductId(cart.getId(), quantity);
            }
        });

        // click nút thanh toán
        holder.btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CartActivity.deleteItem(cart.getId())) {
                    Bill bill = new Bill();
                    bill.setUser_id(MainActivity.user_id);
                    int total_price = Integer.parseInt(holder.txtProductQuantity.getText().toString()) * cart.getGiaTien();
                   // bill.setTotal_price((int) (total_price * 1.1));
                    bill.setTotal_price(total_price);
                    bill.setDescription("Đây là phần mô tả ngắn của sản phẩm...");
                    Date now = new Date();
                    bill.setDate_created(now.toString());
                    billDAO = new BillDAO(context);
                    if (billDAO.insertBill(bill) == 1) {
                        // Nếu thêm thành công thì tiến hành thêm chi tiết sản phẩm
                        Toast.makeText(v.getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        BillDetail billDetail = new BillDetail();
                        billDetail.setBill_id(billDAO.getBillIdNew());
                        billDetail.setProduct_name(cart.getTenSP());
                        int quantity = Integer.parseInt(holder.txtProductQuantity.getText().toString());
                        billDetail.setQuantity(quantity);
                        billDetail.setPrice(cart.getGiaTien());
                        billDetailDAO = new BillDetailDAO(v.getContext());
                        billDetailDAO.insertBillDetail(billDetail);
                        productDAO = new ProductDAO(context);
                        productDAO.editQuantity(cart.getId(), quantity);
                    }

                    cartList.remove(cart);
                    CartActivity.removeByProductId(cart.getId());
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(v.getContext(), "Thanh toán không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;

    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtProductName, txtProductPrice, txtProductQuantityIncrease, txtProductQuantity, txtProductQuantityDecrease;
        Button btnThanhToan;
    }
}
