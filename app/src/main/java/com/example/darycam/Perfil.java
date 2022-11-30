package com.example.darycam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Perfil extends AppCompatActivity {
    private TextView txtconm, txtemailm,txtnomm,txtapellm,txttelm;
    String ncontrol;
    
    RequestQueue requestQueue;


    private androidx.appcompat.widget.Toolbar topbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras !=null){
            ncontrol = extras.getString("ncontrol");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        topbar =findViewById(R.id.topBar);
        setSupportActionBar(topbar);
        requestQueue = Volley.newRequestQueue(this);

        txtconm=findViewById(R.id.txtnumcontrolM);
        txtemailm=findViewById(R.id.txtEmailM);
        txtnomm=findViewById(R.id.txtNombreM);
        txtapellm=findViewById(R.id.txtApellidoM);
        txttelm=findViewById(R.id.txttelM);

        readuser();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.modcontra:
                Intent intent = new Intent(getApplicationContext(),Modific.class);
                startActivity(intent);
                return true;
            case R.id.Perfil:intent = new Intent(getApplicationContext(),Perfil.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void readuser() {
        String URL = "http://10.132.92.217/darycam/fetchM.php?num_control="+ncontrol;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String num_controlM, nombre, apellidos, password, email, telefono;
                        try {
                            num_controlM = response.getString("num_controlM");
                            nombre = response.getString("nombrem");
                            apellidos = response.getString("apellidos");
                            email = response.getString("email");
                            telefono = response.getString("telefono");

                            txtconm.setText(num_controlM);
                            txtemailm.setText(email);
                            txtnomm.setText(nombre);
                            txtapellm.setText(apellidos);
                            txttelm.setText(telefono);
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
        if (id==R.id.btnHOme){
            Intent intent =new Intent(getApplicationContext(),Maestro.class);
            intent.putExtra("ncontrol",ncontrol.trim());
            startActivity(intent);

        }
    }
}