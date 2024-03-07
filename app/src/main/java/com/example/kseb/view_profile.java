package com.example.kseb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class view_profile extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    SharedPreferences sh;
    String url,lid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        t1 = findViewById(R.id.textView4);
        t2 = findViewById(R.id.textView6);
        t3 = findViewById(R.id.textView22);
        t4 = findViewById(R.id.textView8);
        t5 = findViewById(R.id.textView10);
        t6 = findViewById(R.id.textView12);
        t7 = findViewById(R.id.textView14);
        t8 = findViewById(R.id.textView16);
        t9 = findViewById(R.id.textView18);
        t10 = findViewById(R.id.textView20);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ipaddress","");
        url = sh.getString("url","")+"android_view_profile";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                JSONObject jj = jsonObj.getJSONObject("data");
//                                t1.setText(jsonObj.getString("name"));
                                t1.setText(jsonObj.getString("consumer_no"));
                                t2.setText(jsonObj.getString("connection_type"));
                                t3.setText(jsonObj.getString("area"));
                                t4.setText(jsonObj.getString("name"));
                                t5.setText(jsonObj.getString("gender"));
                                t6.setText(jsonObj.getString("place"));
                                t7.setText(jsonObj.getString("post"));
                                t8.setText(jsonObj.getString("pin"));
                                t9.setText(jsonObj.getString("email"));
                                t10.setText(jsonObj.getString("contact"));


//                                String image = jj.getString("photo");
//                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                                String ip = sh.getString("ip", "");
//                                String url = "http://" + ip + ":4000" + image;
//                                Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(pho);//circle

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
