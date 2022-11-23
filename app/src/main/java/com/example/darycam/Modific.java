package com.example.darycam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Modific extends AppCompatActivity {
    private TextView txtncontrol,teemail;
    private EditText epassw,enewpass;
    private Button btmod;
    String ncontrol;
    RequestQueue requestQueue;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestQueue = Volley.newRequestQueue(this);
        Bundle extras = getIntent().getExtras();
        if (extras !=null){
            ncontrol = extras.getString("ncontrol");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modific);




        txtncontrol=findViewById(R.id.txtnumcontrol);
        teemail=findViewById(R.id.txtemail);
        enewpass=findViewById(R.id.ednewpass);
        epassw=findViewById(R.id.edpassw);

        btmod=findViewById(R.id.btnmod);
        readuser();
    }
    private void readuser() {
        String URL = "http://192.168.100.15/darycam/fetch.php?num_control="+ncontrol;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String num_controlA,email;
                        try {
                            num_controlA=response.getString("num_controlA");
                            email=response.getString("email");


                            txtncontrol.setText(num_controlA);
                            teemail.setText(email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public void onClick(View v){
        int id=v.getId();
        if (id==R.id.btnmod){
            updateUser("http://192.168.100.15/darycam/edit.php");
        }

    }
    private void updateUser(String URL) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.isEmpty()){
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("num_control",ncontrol);
                params.put("password",epassw.getText().toString());
                params.put("npassword",enewpass.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}