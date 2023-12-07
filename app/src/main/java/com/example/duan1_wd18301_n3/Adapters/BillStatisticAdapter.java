package com.example.duan1_wd18301_n3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.duan1_wd18301_n3.Models.BillStatistic;
import com.example.duan1_wd18301_n3.R;

import java.util.List;

public class BillStatisticAdapter extends ArrayAdapter<BillStatistic> {
    public BillStatisticAdapter(@NonNull Context context, int resource, List<BillStatistic> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;
        if (view==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_list_bill, null);
        }
        BillStatistic billStatistic = getItem(position);
        if(billStatistic!=null){
            TextView txtID= (TextView)view.findViewById(R.id.idBill);
            TextView txtDateTime= (TextView)view.findViewById(R.id.dateTime);
            TextView txtTotal= (TextView)view.findViewById(R.id.totalBill);
            TextView txtUserName= (TextView)view.findViewById(R.id.userName);
            txtID.setText(String.valueOf(billStatistic.getIdBill()));
            txtDateTime.setText(billStatistic.getDatetime());
            txtTotal.setText(String.valueOf(billStatistic.getTotalBill()) + " VND");
            txtUserName.setText(String.valueOf(billStatistic.getUserName()));
        }

        return view;
    }
}
