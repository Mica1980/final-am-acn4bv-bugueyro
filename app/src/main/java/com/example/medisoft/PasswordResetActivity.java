package com.example.medisoft;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordResetActivity extends AppCompatActivity {

    private EditText newPasswordEditText, repeatPasswordEditText;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_reset_activity);

        // Inicializar vistas
        EditText newPasswordEditText = findViewById(R.id.newPassword);
        EditText repeatPasswordEditText = findViewById(R.id.repeatPassword);

        createButton = findViewById(R.id.createButton);

        // Configurar botón de crear contraseña
        createButton.setOnClickListener(v -> {
            String newPassword = newPasswordEditText.getText().toString().trim();
            String repeatPassword = repeatPasswordEditText.getText().toString().trim();

            if (validateInputs(newPassword, repeatPassword)) {
                // Lógica para guardar la nueva contraseña (ejemplo)
                Toast.makeText(this, "Contraseña actualizada con éxito", Toast.LENGTH_SHORT).show();
                finish(); // Opcional: Cierra la actividad después de actualizar la contraseña
            }
        });
    }

    // Método para validar las entradas
    private boolean validateInputs(String newPassword, String repeatPassword) {
        if (TextUtils.isEmpty(newPassword)) {
            newPasswordEditText.setError("Ingrese una nueva contraseña");
            return false;
        }
        if (!newPassword.equals(repeatPassword)) {
            repeatPasswordEditText.setError("Las contraseñas no coinciden");
            return false;
        }
        if (newPassword.length() < 6) {
            newPasswordEditText.setError("La contraseña debe tener al menos 6 caracteres");
            return false;
        }
        return true;
    }
}
