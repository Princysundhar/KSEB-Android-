package com.example.kseb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateHandle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class view_bill extends AppCompatActivity {
    String [] bid,area,bill_date,due_date,dis_date,c_no,c_name,unit,tariff,bill_amount,current_reading,previous_reading;
    ListView li;
    SharedPreferences sh;
    String url;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bill);
        li = findViewById(R.id.listview);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ipaddress","");
        url = sh.getString("url","")+"android_view_bill";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js = jsonObj.getJSONArray("data");//from python
                                bid = new String[js.length()];
                                area = new String[js.length()];
                                bill_date = new String[js.length()];
                                due_date = new String[js.length()];
                                dis_date = new String[js.length()];
                                c_no = new String[js.length()];
                                c_name = new String[js.length()];
                                unit = new String[js.length()];
                                tariff = new String[js.length()];
                                bill_amount = new String[js.length()];
                                current_reading = new String[js.length()];
                                previous_reading = new String[js.length()];



                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    bid[i] = u.getString("bid"); // dbcolumn name in double quotes
                                    area[i] = u.getString("area");
                                    bill_date[i] = u.getString("bill_date");
                                    due_date[i] = u.getString("due_date");
                                    dis_date[i] = u.getString("dis_date");
                                    c_no[i] = u.getString("c_no");
                                    c_name[i] = u.getString("c_name");
                                    unit[i] = u.getString("unit");
                                    tariff[i] = u.getString("tariff");
                                    bill_amount[i] = u.getString("bill_amount");
                                    current_reading[i] = u.getString("current_reading");
                                    previous_reading[i] = u.getString("previous_reading");



                                }
                                li.setAdapter(new custom_view_bill(getApplicationContext(),bid,area,bill_date,due_date,dis_date,c_no,c_name,unit,tariff,bill_amount,current_reading,previous_reading));//custom_view_service.xml and li is the listview object


                            } else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {

            //                value Passing android to python
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();
                params.put("lid", sh.getString("lid",""));//passing to python
//                params.put("branch_id", sh.getString("branch_id",""));//passing to python
                return params;
            }
        };


        int MY_SOCKET_TIMEOUT_MS = 100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);



    }

}