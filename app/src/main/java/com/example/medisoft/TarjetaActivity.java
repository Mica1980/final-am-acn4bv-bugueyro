package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TarjetaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjeta);

        // Referencia a los elementos de la interfaz
        ImageView btnBack = findViewById(R.id.btnBack);
        EditText edtCuenta = findViewById(R.id.edtCuenta);
        EditText edtSaldo = findViewById(R.id.edtSaldo);
        EditText edtImportePagar = findViewById(R.id.edtImportePagar);
        CheckBox chkPagoParcial = findViewById(R.id.chkPagoParcial);
        Spinner spinnerTarjeta = findViewById(R.id.spinnerTarjeta);
        EditText edtNumeroTarjeta = findViewById(R.id.edtNumeroTarjeta);
        EditText edtNombreTarjeta = findViewById(R.id.edtNombreTarjeta);
        EditText edtVencimientoTarjeta = findViewById(R.id.edtVencimientoTarjeta);
        EditText edtCodigoSeguridad = findViewById(R.id.edtCodigoSeguridad);
        Button btnCancelar = findViewById(R.id.btnCancelar);
        Button btnPagar = findViewById(R.id.btnPagar);

        // Acción del botón de retroceder
        btnBack.setOnClickListener(v -> finish());

        // Acción del CheckBox para pago parcial
        chkPagoParcial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                edtImportePagar.setEnabled(true);
                edtImportePagar.requestFocus();
            } else {
                edtImportePagar.setEnabled(false);
                edtImportePagar.setText(edtSaldo.getText().toString());
            }
        });

        // Acción del botón Cancelar
        btnCancelar.setOnClickListener(v -> finish());

        // Acción del botón Pagar
        btnPagar.setOnClickListener(v -> {
            // Validar los campos antes de proceder
            if (validarCampos(edtNumeroTarjeta, edtNombreTarjeta, edtVencimientoTarjeta, edtCodigoSeguridad)) {
                Toast.makeText(this, "Redirigiendo a Confirmar Pago", Toast.LENGTH_SHORT).show();

                // Redirige a la pantalla de Confirmar Pago
                Intent intent = new Intent(TarjetaActivity.this, ConfirmarPagoActivity.class);
                intent.putExtra("metodoPago", spinnerTarjeta.getSelectedItem().toString());
                startActivity(intent);
                finish();
            }
        });
    }

    // Método para validar los campos
    private boolean validarCampos(EditText numeroTarjeta, EditText nombreTarjeta, EditText vencimientoTarjeta, EditText codigoSeguridad) {
        if (numeroTarjeta.getText().toString().isEmpty() || numeroTarjeta.getText().toString().length() != 16) {
            numeroTarjeta.setError("El número de la tarjeta debe tener exactamente 16 dígitos");
            numeroTarjeta.requestFocus();
            return false;
        }

        if (nombreTarjeta.getText().toString().isEmpty()) {
            nombreTarjeta.setError("Ingrese el nombre como aparece en la tarjeta");
            nombreTarjeta.requestFocus();
            return false;
        }

        if (vencimientoTarjeta.getText().toString().isEmpty() || !vencimientoTarjeta.getText().toString().matches("\\d{2}/\\d{2}")) {
            vencimientoTarjeta.setError("Ingrese una fecha válida en formato MM/AA");
            vencimientoTarjeta.requestFocus();
            return false;
        }

        if (codigoSeguridad.getText().toString().isEmpty() || codigoSeguridad.getText().toString().length() != 3) {
            codigoSeguridad.setError("Ingrese un código de seguridad válido (3 dígitos)");
            codigoSeguridad.requestFocus();
            return false;
        }

        return true;
    }
}
