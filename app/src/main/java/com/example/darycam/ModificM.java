package com.example.darycam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class ModificM extends AppCompatActivity {
    private TextView txtncontrolm,txtmailm;
    private EditText epasswm,enewpassm;
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
        setContentView(R.layout.activity_modific_m);

        txtncontrolm=findViewById(R.id.txtEmailM);
        txtmailm=findViewById(R.id.txtemailm);
        enewpassm=findViewById(R.id.ednewpassM);
        epasswm=findViewById(R.id.edpasswM);

        readuser();
    }
    private void readuser() {
        String URL = "http://10.132.92.217/darycam/fetchM.php?num_control="+ncontrol;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                response -> {
                    String num_controlM,emailm;
                    try {
                        num_controlM=response.getString("num_controlM");
                        emailm=response.getString("email");

                        txtncontrolm.setText(num_controlM);
                        txtmailm.setText(emailm);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {

                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public void onClick(View v){
        int id=v.getId();
        if (id==R.id.btnmodM){
            updateUser("http://10.132.92.217/darycam/editM.php");
        }
    }
    private void updateUser(String URL) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                response -> {
                    if (!response.isEmpty()){
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                }, error -> {

                }
        ){
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params =new HashMap<>();
                params.put("num_controlm",ncontrol);
                params.put("password",epasswm.getText().toString());
                params.put("npassword",enewpassm.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

