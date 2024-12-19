package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmarPagoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pago);

        // Vincula las vistas del XML con el código
        TextView txtMetodoPago = findViewById(R.id.txtMetodoPago);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);
        Button btnCancelar = findViewById(R.id.btnCancelar);
        ImageView btnBack = findViewById(R.id.btnBack);

        // Obtén el método de pago desde el Intent
        String metodoPago = getIntent().getStringExtra("metodoPago");

        // Validación del método de pago
        if (metodoPago != null && !metodoPago.isEmpty()) {
            txtMetodoPago.setText("Has seleccionado: " + metodoPago);
        } else {
            txtMetodoPago.setText("No se ha seleccionado un método de pago.");
            Toast.makeText(this, "Error: No se proporcionó el método de pago", Toast.LENGTH_SHORT).show();
        }

        // Acción para el botón Confirmar
        btnConfirmar.setOnClickListener(v -> {
            // Simula el resultado del pago
            boolean pagoExitoso = realizarPagoSimulado();

            // Muestra mensajes según el resultado
            if (pagoExitoso) {
                mostrarMensajeExito("Pago exitoso", "Tu pago fue procesado con éxito. Gracias.");
            } else {
                mostrarMensajeError("Error en el pago", "No se pudo realizar el pago. Por favor, intenta nuevamente.");
            }
        });

        // Acción para el botón Cancelar
        btnCancelar.setOnClickListener(v -> {
            Intent intent = new Intent(ConfirmarPagoActivity.this, ServicioActivity.class);
            startActivity(intent);
            finish();
        });

        // Acción para el botón de retroceso
        btnBack.setOnClickListener(v -> finish());
    }

    private boolean realizarPagoSimulado() {
        return Math.random() < 0.8;
    }

    private void mostrarMensajeExito(String titulo, String mensaje) {
        new AlertDialog.Builder(this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    dialog.dismiss();

                    // Redirige a CierreActivity tras un pago exitoso
                    Intent intent = new Intent(ConfirmarPagoActivity.this, CierreActivity.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    }

    private void mostrarMensajeError(String titulo, String mensaje) {
        new AlertDialog.Builder(this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("Reintentar", (dialog, which) -> {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    Intent intent = new Intent(ConfirmarPagoActivity.this, ServicioActivity.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    }
}


