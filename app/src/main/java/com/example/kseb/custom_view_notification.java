package com.example.kseb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_view_notification extends BaseAdapter {
    String [] nid,notification,date;
    private Context context;


    public custom_view_notification(Context applicationContext, String[] nid, String[] notification, String[] date) {
        this.context = applicationContext;
        this.nid = nid;
        this.notification = notification;
        this.date = date;
    }

    @Override
    public int getCount() {
        return notification.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_notification, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView44);
//        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageView5);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView46);


        tv1.setTextColor(Color.RED);//color setting
        tv2.setTextColor(Color.BLACK);


        tv1.setText(notification[i]);
        tv2.setText(date[i]);

//
        return gridView;
    }
}