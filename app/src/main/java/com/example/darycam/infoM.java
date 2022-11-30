package com.example.darycam;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class infoM extends AppCompatActivity {

    Button btngenerate;
    ImageView ivqr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_m);


        btngenerate = findViewById(R.id.bttqr);
        ivqr=findViewById(R.id.codeQR);

        btngenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap("http://192.168.100.15/darycam/Trye2.php?num_controla=20012301&ID_clase=1",
                            BarcodeFormat.QR_CODE
                            ,750
                            ,750);
                    ivqr.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

    }











}