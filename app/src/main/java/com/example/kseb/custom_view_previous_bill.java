package com.example.kseb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class custom_view_previous_bill extends BaseAdapter {
    String [] bid,amount,date,current_reading,unit_consumed,status;
    private Context context;

    public custom_view_previous_bill(Context applicationContext, String[] bid, String[] amount, String[] date, String[] current_reading, String[] unit_consumed, String[] status) {
        this.context = applicationContext;
        this.bid = bid;
        this.amount = amount;
        this.date = date;
        this.current_reading = current_reading;
        this.unit_consumed = unit_consumed;
        this.status = status;
    }


    @Override
    public int getCount() {
        return amount.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.activity_custom_view_previous_bill, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView24);
//        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageView5);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView26);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView28);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView30);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView32);


        tv1.setTextColor(Color.RED);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);


        tv1.setText(amount[i]);
        tv2.setText(date[i]);
        tv3.setText(current_reading[i]);
        tv4.setText(unit_consumed[i]);
        tv5.setText(status[i]);

//
        return gridView;
    }
}