package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_config_activity);

        EditText newUsername = findViewById(R.id.newUsername);
        EditText repeatUsername = findViewById(R.id.repeatUsername);
        EditText confirmPassword = findViewById(R.id.confirmPassword);
        Button createButton = findViewById(R.id.createButton);

        createButton.setOnClickListener(v -> {
            String username = newUsername.getText().toString();
            String repeatedUsername = repeatUsername.getText().toString();
            String password = confirmPassword.getText().toString();

            if (username.isEmpty() || repeatedUsername.isEmpty() || password.isEmpty()) {
                Toast.makeText(UserConfigActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!username.equals(repeatedUsername)) {
                Toast.makeText(UserConfigActivity.this, "Los nombres de usuario no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simulación de creación del usuario (aquí puedes agregar tu lógica real)
            boolean userCreated = createUser(username, password);

            if (userCreated) {
                Toast.makeText(UserConfigActivity.this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show();

                // Redirigir a la pantalla de inicio de sesión
                Intent intent = new Intent(UserConfigActivity.this, LoginActivity.class);
                startActivity(intent);

                // Finaliza la actividad actual para que no pueda volver con el botón de retroceso
                finish();
            } else {
                Toast.makeText(UserConfigActivity.this, "Error al crear el usuario. Intenta nuevamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Simulación de la creación del usuario (puedes reemplazarlo con lógica real)
    private boolean createUser(String username, String password) {
        // Aquí podrías guardar el usuario en una base de datos, Firebase, etc.
        return true; // Simula éxito
    }
}
