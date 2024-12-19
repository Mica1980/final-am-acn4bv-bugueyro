package com.example.medisoft;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class ClienteActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextNombre, editTextNumeroSocio;
    private Button btnAlta, btnBaja;
    private FirestoreHelper firestoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        // Inicializar vistas
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextNumeroSocio = findViewById(R.id.editTextNumeroSocio);
        btnAlta = findViewById(R.id.btnAlta);
        btnBaja = findViewById(R.id.btnBaja);

        // Inicializar FirestoreHelper
        firestoreHelper = new FirestoreHelper();

        // Botón Alta
        btnAlta.setOnClickListener(v -> {
            String clienteId = UUID.randomUUID().toString();
            String email = editTextEmail.getText().toString().trim();
            String nombre = editTextNombre.getText().toString().trim();
            String numeroSocio = editTextNumeroSocio.getText().toString().trim();

            if (email.isEmpty() || nombre.isEmpty() || numeroSocio.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            firestoreHelper.agregarCliente(clienteId, email, nombre, numeroSocio);
            Toast.makeText(this, "Cliente dado de alta correctamente", Toast.LENGTH_SHORT).show();
        });

        // Botón Baja
        btnBaja.setOnClickListener(v -> {
            String numeroSocio = editTextNumeroSocio.getText().toString().trim();

            if (numeroSocio.isEmpty()) {
                Toast.makeText(this, "Ingresa el número de socio", Toast.LENGTH_SHORT).show();
                return;
            }

            firestoreHelper.darDeBajaCliente(numeroSocio);
            Toast.makeText(this, "Cliente dado de baja correctamente", Toast.LENGTH_SHORT).show();
        });
    }
}
