package com.example.kseb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class custom_view_bill extends BaseAdapter {

    String [] bid,area,bill_date,due_date,dis_date,c_no,c_name,unit,tariff,bill_amount,current_reading,previous_reading;
    private Context context;
    SharedPreferences sh;


    public custom_view_bill(Context applicationContext, String[] bid, String[] area, String[] bill_date, String[] due_date, String[] dis_date, String[] c_no, String[] c_name, String[] unit, String[] tariff, String[] bill_amount,String[] current_reading,String[] previous_reading) {
        this.context = applicationContext;
        this.bid = bid;
        this.area = area;
        this.bill_date = bill_date;
        this.due_date = due_date;
        this.dis_date = dis_date;
        this.c_no = c_no;
        this.c_name = c_name;

        this.tariff = tariff;
        this.unit = unit;
        this.current_reading = current_reading;
        this.previous_reading = previous_reading;
        this.bill_amount = bill_amount;

    }

    @Override
    public int getCount() {
        return area.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_bill, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView50);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView52);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView54);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView56);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView64);
        TextView tv6 = (TextView) gridView.findViewById(R.id.textView65);
        TextView tv7 = (TextView) gridView.findViewById(R.id.textView66);
        TextView tv8 = (TextView) gridView.findViewById(R.id.textView67);
        TextView tv9 = (TextView) gridView.findViewById(R.id.textView68);
        TextView tv10 = (TextView) gridView.findViewById(R.id.textView69);
        TextView tv11= (TextView) gridView.findViewById(R.id.textView70);
//        TextView tv12 = (TextView) gridView.findViewById(R.id.textView72);

        Button b1 = (Button) gridView.findViewById(R.id.button6);     // online payment
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int)view.getTag();
                sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed =sh.edit();
                ed.putString("bid",bid[pos]);
                ed.putString("bill_amount",bill_amount[pos]);
                ed.commit();
                Intent j =new Intent(context,online_payment.class);
                j.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(j);
            }
        });


        tv1.setTextColor(Color.RED);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);
        tv6.setTextColor(Color.BLACK);
        tv7.setTextColor(Color.BLACK);
        tv8.setTextColor(Color.BLACK);
        tv9.setTextColor(Color.BLACK);
        tv10.setTextColor(Color.BLACK);
        tv11.setTextColor(Color.BLACK);
//        tv12.setTextColor(Color.BLACK);


        tv1.setText(area[i]);
        tv2.setText(bill_date[i]);
        tv3.setText(due_date[i]);
        tv4.setText(dis_date[i]);
        tv5.setText(c_no[i]);
        tv6.setText(c_name[i]);
        tv7.setText(tariff[i]);
        tv8.setText(unit[i]);
        tv9.setText(current_reading[i]);
        tv10.setText(previous_reading[i]);
        tv11.setText(bill_amount[i]);
//        tv12.setText(meter_reader[i]);

//
        return gridView;
    }
}