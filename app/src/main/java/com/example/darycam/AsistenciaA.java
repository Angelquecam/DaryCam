package com.example.darycam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AsistenciaA extends AppCompatActivity {

    TextView nommateriatxt;
    TextView horariotxt;
    Button btntomasis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);

        ListElement element = (ListElement) getIntent().getSerializableExtra("ListElement");
        nommateriatxt = findViewById(R.id.nommateria);
        horariotxt = findViewById(R.id.horario);

        nommateriatxt.setText(element.getClase());
        horariotxt.setText(element.getHora());
    }
}