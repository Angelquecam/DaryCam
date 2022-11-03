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

public class Maestro extends AppCompatActivity {
    private androidx.appcompat.widget.Toolbar topbar;
    String ncontrol;

    List<ListElement> elements;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maestro);
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


        ListAdapter listAdapter = new ListAdapter(elements,this);
        RecyclerView recyclerView= findViewById(R.id.recycleviewm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);



    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        switch (item.getItemId()){
            case R.id.modcontra:Intent intent = new Intent(getApplicationContext(),ModificM.class);
                intent.putExtra("ncontrol",ncontrol.trim());
                startActivity(intent);
                return true;
            case R.id.Perfil:intent = new Intent(getApplicationContext(),Perfil.class);
                intent.putExtra("ncontrol",ncontrol.trim());
                startActivity(intent);
                return  true;
            case R.id.logout:intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
