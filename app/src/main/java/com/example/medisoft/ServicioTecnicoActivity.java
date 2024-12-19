package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServicioTecnicoActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_tecnico);

        // Inicializar Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("servicioTecnico");

        // Referencias a los campos de entrada
        EditText edtNombreCliente = findViewById(R.id.edtNombreCliente);
        EditText edtEmailCliente = findViewById(R.id.edtEmailCliente);
        EditText edtTelefonoCliente = findViewById(R.id.edtTelefonoCliente);
        EditText edtDescripcionProblema = findViewById(R.id.edtDescripcionProblema);

        // Botón para enviar los datos
        Button btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(v -> {
            String nombre = edtNombreCliente.getText().toString();
            String email = edtEmailCliente.getText().toString();
            String telefono = edtTelefonoCliente.getText().toString();
            String descripcion = edtDescripcionProblema.getText().toString();

            if (!nombre.isEmpty() && !email.isEmpty() && !telefono.isEmpty() && !descripcion.isEmpty()) {
                // Crea un nuevo objeto para guardar en Firebase
                String id = databaseReference.push().getKey();
                if (id != null) {
                    ServicioMensaje mensaje = new ServicioMensaje(id, nombre, email, telefono, descripcion);
                    databaseReference.child(id).setValue(mensaje)
                            .addOnSuccessListener(aVoid -> Toast.makeText(this, "Mensaje enviado con éxito", Toast.LENGTH_SHORT).show())
                            .addOnFailureListener(e -> Toast.makeText(this, "Error al enviar mensaje: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                }
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón para cerrar sesión
        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(v -> {
            Intent intent = new Intent(ServicioTecnicoActivity.this, CierreActivity.class);
            startActivity(intent);
            finish(); // Finaliza la actividad actual
        });

        // Botón para volver
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }
}


