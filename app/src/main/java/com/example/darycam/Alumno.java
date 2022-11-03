package com.example.darycam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Alumno extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar topbar;
    String ncontrol;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

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