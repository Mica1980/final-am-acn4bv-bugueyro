package com.example.medisoft;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FacturaDetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura_detalle);

        // Obtener datos del Intent
        String facturaNombre = getIntent().getStringExtra("factura_nombre");
        String facturaMonto = getIntent().getStringExtra("factura_monto");
        String facturaFecha = getIntent().getStringExtra("factura_fecha");

        // Configurar TextViews
        TextView txtNombre = findViewById(R.id.txtNombreFactura);
        TextView txtMonto = findViewById(R.id.txtMontoFactura);
        TextView txtFecha = findViewById(R.id.txtFechaFactura);

        txtNombre.setText("Número de Cuenta: " + facturaNombre);
        txtMonto.setText("Monto: " + facturaMonto);
        txtFecha.setText("Fecha de Vencimiento: " + facturaFecha);

        // Configurar botón de retroceso
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }
}

