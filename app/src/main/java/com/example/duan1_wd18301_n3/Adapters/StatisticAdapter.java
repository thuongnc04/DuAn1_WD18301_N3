package com.example.duan1_wd18301_n3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duan1_wd18301_n3.Models.ProductStatistic;
import com.example.duan1_wd18301_n3.R;

import java.util.List;

public class StatisticAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ProductStatistic> productSales;

    public StatisticAdapter(Context context, int layout, List<ProductStatistic> productSales) {
        this.context = context;
        this.layout = layout;
        this.productSales = productSales;
    }

    @Override
    public int getCount() {
        return productSales.size();
    }

    @Override
    public Object getItem(int position) {
        return productSales.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView textViewProductName = (TextView) view.findViewById(R.id.textViewProductName);
        TextView textViewProductQuantity = (TextView) view.findViewById(R.id.textViewProductQuantity);
        TextView textViewProductTotalSales = (TextView) view.findViewById(R.id.textViewProductTotalSales);

        ProductStatistic productStatistic = productSales.get(position);
        textViewProductName.setText(productStatistic.getProductName());
        textViewProductQuantity.setText("" + Integer.toString(productStatistic.getQuantity()));
        textViewProductTotalSales.setText("" + Double.toString(productStatistic.getTotalSales()) + " VND");

        return view;
    }
}
