package com.example.medisoft;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ServicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio);

        // Referencias a los botones e íconos
        Button btnFactura = findViewById(R.id.btnFactura);
        Button btnServicioTecnico = findViewById(R.id.btnServicioTecnico);
        ImageView btnLlamar = findViewById(R.id.btnLlamar);
        ImageView btnBack = findViewById(R.id.btnBack); // Botón de retroceder

        // Acción del botón "Factura"
        btnFactura.setOnClickListener(v -> {
            Intent intent = new Intent(ServicioActivity.this, FacturaActivity.class);
            startActivity(intent);
        });

        // Acción del botón "Servicio Técnico"
        btnServicioTecnico.setOnClickListener(v -> {
            Intent intent = new Intent(ServicioActivity.this, ServicioTecnicoActivity.class);
            startActivity(intent);
        });

        // Acción del botón de WhatsApp
        btnLlamar.setOnClickListener(v -> {
            String phoneNumber = "5491123456789"; // Número de teléfono ficticio
            String whatsappUrl = "https://wa.me/" + phoneNumber;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(whatsappUrl));
            startActivity(intent);
        });

        // Acción del botón de retroceder
        btnBack.setOnClickListener(v -> finish());
    }
}
