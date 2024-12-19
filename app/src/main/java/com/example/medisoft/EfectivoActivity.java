package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class EfectivoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efectivo);

        // Botón de retroceso
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish()); // Regresa a la pantalla anterior

        // Opciones de pago
        LinearLayout optionGalicia = findViewById(R.id.optionGalicia);
        LinearLayout optionRapiPago = findViewById(R.id.optionRapiPago);
        LinearLayout optionPagoFacil = findViewById(R.id.optionPagoFacil);

        // Acción al seleccionar "Galicia"
        optionGalicia.setOnClickListener(v -> {
            Intent intent = new Intent(EfectivoActivity.this, TransferenciaActivity.class);
            startActivity(intent); // Lleva a TransferenciaActivity
        });

        // Acción al seleccionar "RapiPago"
        optionRapiPago.setOnClickListener(v -> {
            Intent intent = new Intent(EfectivoActivity.this, RapipagoActivity.class);
            startActivity(intent); // Lleva a RapipagoActivity
        });

        // Acción al seleccionar "PagoFácil"
        optionPagoFacil.setOnClickListener(v -> {
            Intent intent = new Intent(EfectivoActivity.this, PagoFacilActivity.class);
            startActivity(intent); // Lleva a PagoFacilActivity
        });
    }
}


