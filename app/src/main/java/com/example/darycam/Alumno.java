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
    public void finit(){
        elements = new ArrayList<>();
        elements.add(new ListElement("#788878","Base de Datos","7Am","Actual"));
        elements.add(new ListElement("#348858","Leng de interfaz","9Am","Esperando"));
        elements.add(new ListElement("#588459","Automatas","5Pm","Esperando"));


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