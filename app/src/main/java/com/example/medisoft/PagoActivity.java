package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PagoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);


        // Botón de retroceso
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            // Regresa a la pantalla anterior
            finish();
        });

        // Botón de pago en efectivo
        Button btnEfectivo = findViewById(R.id.btnEfectivo);
        btnEfectivo.setOnClickListener(v -> {
            Intent intent = new Intent(PagoActivity.this, EfectivoActivity.class);
            startActivity(intent);
        });

        // Botón de pago con tarjeta
        Button btnOnlineTarjeta = findViewById(R.id.btnOnlineTarjeta);
        btnOnlineTarjeta.setOnClickListener(v -> {
            Intent intent = new Intent(PagoActivity.this, TarjetaActivity.class);
            startActivity(intent);
        });
    }
}



