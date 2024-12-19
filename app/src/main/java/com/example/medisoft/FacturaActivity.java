package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FacturaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        // Botón de retroceso
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Botón Descargar (como ImageView)
        ImageView btnDescargar = findViewById(R.id.btnDescargar);
        btnDescargar.setOnClickListener(v -> {
            // Mostrar mensaje de descarga
            Toast.makeText(this, "Descargando factura...", Toast.LENGTH_SHORT).show();
        });

        // Botón Ver Facturas
        Button btnVerFacturas = findViewById(R.id.btnVerFacturas);
        btnVerFacturas.setOnClickListener(v -> {
            // Navegar a VerFacturasActivity (corregido)
            Intent intent = new Intent(FacturaActivity.this, VerFacturasActivity.class);
            startActivity(intent);
        });

        // Botón Ver Pagos
        Button btnVerPagos = findViewById(R.id.btnVerPagos);
        btnVerPagos.setOnClickListener(v -> {
            // Navegar a FacturasPagasActivity (corregido)
            Intent intent = new Intent(FacturaActivity.this, FacturasPagasActivity.class);
            startActivity(intent);
        });

        // Botón Pagar
        Button btnPagar = findViewById(R.id.btnPagar);
        btnPagar.setOnClickListener(v -> {
            // Navegar a PagoActivity
            Intent intent = new Intent(FacturaActivity.this, PagoActivity.class);
            startActivity(intent);
        });
    }
}









