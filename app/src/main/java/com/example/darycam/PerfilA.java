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

public class PerfilA extends AppCompatActivity {

    private TextView txtcona, txtemaila, txtnoma, txtapella, txttela;
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
        setContentView(R.layout.activity_perfil2);
        topbar =findViewById(R.id.topBar);
        setSupportActionBar(topbar);
        requestQueue = Volley.newRequestQueue(this);

        txtcona =findViewById(R.id.txtnumcontrolA);
        txtemaila =findViewById(R.id.txtEmailA);
        txtnoma =findViewById(R.id.txtNombreA);
        txtapella =findViewById(R.id.txtApellidoA);
        txttela =findViewById(R.id.txttelA);

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
            case R.id.Perfil:intent = new Intent(getApplicationContext(),PerfilA.class);
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
        String URL = "http://192.168.100.64/darycam/fetch.php?num_control="+ncontrol;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String num_controlA, nombre, apellidos, password, email, telefono;
                        try {
                            num_controlA = response.getString("num_controlA");
                            nombre = response.getString("nombrea");
                            apellidos = response.getString("apellidos");
                            email = response.getString("email");
                            telefono = response.getString("telefono");

                            txtcona.setText(num_controlA);
                            txtemaila.setText(email);
                            txtnoma.setText(nombre);
                            txtapella.setText(apellidos);
                            txttela.setText(telefono);
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
        if (id==R.id.btnHomea){
            Intent intent =new Intent(getApplicationContext(),Alumno.class);
            intent.putExtra("ncontrol",ncontrol.trim());
            startActivity(intent);

        }
    }
}