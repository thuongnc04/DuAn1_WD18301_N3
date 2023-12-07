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

import com.example.duan1_wd18301_n3.Models.Product;
import com.example.duan1_wd18301_n3.R;
import com.example.duan1_wd18301_n3.User.CartActivity;

import java.util.List;

public class ProductUserAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Product> productList;
    public ProductUserAdapter(Context context, int layout, List<Product> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return productList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, viewGroup, false);

            holder = new ViewHolder();
            holder.productImage = view.findViewById(R.id.productImage);
            holder.txtProductName = view.findViewById(R.id.txtProductName);
            holder.txtProductPrice = view.findViewById(R.id.txtProductPrice);
            holder.btnAddToShoppingCart = view.findViewById(R.id.btnAddToShoppingCart);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Product product = (Product) getItem(i);

        byte[] productImage = product.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);

        holder.productImage.setImageBitmap(bitmap);
        holder.txtProductName.setText(product.getName());
        holder.txtProductPrice.setText("" + product.getPrice());
        holder.btnAddToShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean add = CartActivity.addItem(product.getId(), 1);
                if (add) {
                    Toast.makeText(v.getContext(), "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(v.getContext(), "Thêm vào giỏ hàng Không thành công", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private class ViewHolder {
        ImageView productImage;
        TextView txtProductName, txtProductPrice;
        Button btnAddToShoppingCart;
    }
}
