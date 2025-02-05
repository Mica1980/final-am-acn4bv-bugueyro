package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FacturaDetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura_detalle);

        // Obtener datos del Intent
        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "Error: No se recibieron datos.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        String facturaNombre = intent.getStringExtra("factura_nombre");
        String facturaMonto = intent.getStringExtra("factura_monto");
        String facturaFecha = intent.getStringExtra("factura_fecha");

        if (facturaNombre == null || facturaMonto == null || facturaFecha == null) {
            Toast.makeText(this, "Error: Datos de factura incompletos.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Configurar TextViews
        TextView txtNombre = findViewById(R.id.txtNombreFactura);
        TextView txtMonto = findViewById(R.id.txtMontoFactura);
        TextView txtFecha = findViewById(R.id.txtFechaFactura);

        txtNombre.setText("Número de Cuenta: " + facturaNombre);
        txtMonto.setText("Monto: " + facturaMonto);
        txtFecha.setText("Fecha de Vencimiento: " + facturaFecha);

        // Botón de retroceso
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }
}

