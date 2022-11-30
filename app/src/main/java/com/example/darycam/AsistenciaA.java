package com.example.darycam;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

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


        btntomasis=findViewById(R.id.bttomaasis);
        btntomasis.setOnClickListener(v->
                {
                    scanCode();
                }

                );

    }


    private void scanCode(){

        ScanOptions options = new ScanOptions();
        options.setPrompt("Subir volumen para el flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(Camera.class);
        barLaucher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result->{
       if(result.getContents() !=null){
           AlertDialog.Builder builder = new AlertDialog.Builder(AsistenciaA.this);
           builder.setTitle("Resultado");
           builder.setMessage(result.getContents());
           builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   dialogInterface.dismiss();
               }
           }).show();
       }
    });

}