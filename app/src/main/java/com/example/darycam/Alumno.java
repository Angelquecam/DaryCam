package com.example.darycam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Alumno extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar topbar;
    String ncontrol;
    List<ListElement> elements;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);
        finit();
        Bundle extras = getIntent().getExtras();
        if (extras !=null){
            ncontrol = extras.getString("ncontrol");
        }
        topbar =findViewById(R.id.topBar);
        setSupportActionBar(topbar);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void redclas() {
        String URL = "http://192.168.100.15/darycam/Trye.php?num_control="+ncontrol;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object1 = array.getJSONObject(i);
                        String id_clase = object1.getString("ID_clase");

                        String URL1 = "http://192.168.100.15/darycam/Trye2.php?num_controla=" + ncontrol + "&ID_clase=" + id_clase;
                        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response1) {
                                try {
                                    elements = new ArrayList<>();
                                    JSONArray arrays = new JSONArray(response1);
                                    for (int j = 0; j < arrays.length(); j++) {
                                        JSONObject object = arrays.getJSONObject(j);
                                        String nom = object.getString("nombre");
                                        String dia = object.getString("Dia");
                                        String hini = object.getString("hora_inicio");
                                        elements.add(new ListElement("#788878",nom,hini,dia));
                                    }
                                    ListAdapter listAdapter = new ListAdapter(elements, Alumno.this, new ListAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(ListElement item) {
                                            moveToAsistencia(item);
                                        }
                                    });
                                    RecyclerView recyclerView= findViewById(R.id.recycleviewa);
                                    recyclerView.setHasFixedSize(true);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(Alumno.this));
                                    recyclerView.setAdapter(listAdapter);
                                } catch (JSONException f) {
                                    f.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                    }
                    ;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);

    }

    public void finit(){
        elements = new ArrayList<>();

        elements.add(new ListElement("#788878","adm base de datos","7","actual"));

        ListAdapter listAdapter = new ListAdapter(elements, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                moveToAsistencia(item);
            }
        });
        RecyclerView recyclerView= findViewById(R.id.recycleviewa);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
    public void moveToAsistencia (ListElement item){
        Intent intent = new Intent(this, AsistenciaA.class);
        intent.putExtra("ListElement", item);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        switch (item.getItemId()){
            case R.id.modcontra:Intent intent = new Intent(getApplicationContext(),Modific.class);
                intent.putExtra("ncontrol",ncontrol.trim());
                startActivity(intent);
                return true;
            case R.id.Perfil:intent = new Intent(getApplicationContext(),PerfilA.class);
                intent.putExtra("ncontrol",ncontrol.trim());
                startActivity(intent);
                return true;
            case R.id.logout:
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}