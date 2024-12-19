package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CierreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cierre);

        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        Button btnSalir = findViewById(R.id.btnSalir);

        // Acción del botón Cerrar Sesión
        btnCerrarSesion.setOnClickListener(v -> {
            // Aquí puedes implementar lógica adicional si es necesario
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CierreActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Acción del botón Salir
        btnSalir.setOnClickListener(v -> {
            Toast.makeText(this, "Saliendo de la aplicación", Toast.LENGTH_SHORT).show();
            finishAffinity(); // Cierra todas las actividades
            System.exit(0);   // Finaliza la aplicación
        });
    }
}
