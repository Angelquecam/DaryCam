package com.example.darycam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText user,password;
    Button bttnalumno, bttmaestr;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=findViewById(R.id.user);
        password=findViewById(R.id.password);
        bttnalumno=findViewById(R.id.bttnlogin);
        bttmaestr=findViewById(R.id.bttnloginmas);


        bttnalumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validadusuario("http://192.168.100.64/darycam/validar.php"
                );
            }
        });
        bttmaestr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validadmaestro("http://192.168.100.64/darycam/Validamaestro.php"
                );
            }
        });


    }
    private void validadusuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                    Intent intent = new Intent(getApplicationContext(), Alumno.class);
                    intent.putExtra("ncontrol",user.getText().toString().trim());
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Contraseña, usuario incorrecto o no es alumno", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parametros=new HashMap<String, String>();
                parametros.put("num_control",user.getText().toString());
                parametros.put("password",password.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void validadmaestro(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                    Intent intent = new Intent(getApplicationContext(),Maestro.class);
                    intent.putExtra("ncontrol",user.getText().toString().trim());
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Contraseña, usuario incorrecto o no es maestro", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parametros=new HashMap<String, String>();
                parametros.put("num_control",user.getText().toString());
                parametros.put("password",password.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}