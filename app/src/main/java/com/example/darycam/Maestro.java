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
    List<ListElement> elementsm;
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
        elementsm = new ArrayList<>();
        elementsm.add(new ListElement("#788878","Base de Datos","7Am","Actual"));
        elementsm.add(new ListElement("#348858","Leng de interfaz","9Am","Esperando"));
        elementsm.add(new ListElement("#588459","Automatas","5Pm","Esperando"));


        ListAdapter listAdapterm = new ListAdapter(elementsm, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                moveToGenerateqr(item);
            }
        });


        RecyclerView recyclerView= findViewById(R.id.recycleviewm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapterm);



    }

    public void moveToGenerateqr(ListElement item){
        Intent intent = new Intent(this, infoM.class);
        intent.putExtra("ListElement", item);
        startActivity(intent);
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
