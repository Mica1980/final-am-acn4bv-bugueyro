package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        firebaseAuth = FirebaseAuth.getInstance();

        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);
        TextView forgotPassword = findViewById(R.id.forgotPassword);

        emailEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        // Listener para el botón de inicio de sesión
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateInputs(email, password)) {
                loginUser(email, password);
            }
        });

        // Listener para el botón de registrarse
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Listener para el texto de "Olvidé mi contraseña"
        forgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, UserConfigActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Valida los campos de entrada para el inicio de sesión.
     * @param email Correo electrónico ingresado.
     * @param password Contraseña ingresada.
     * @return true si los campos son válidos, false de lo contrario.
     */
    private boolean validateInputs(String email, String password) {
        if (email.isEmpty()) {
            emailEditText.setError("Ingrese un correo electrónico");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Ingrese un correo válido");
            return false;
        }
        if (password.isEmpty()) {
            passwordEditText.setError("Ingrese una contraseña");
            return false;
        }
        return true;
    }

    /**
     * Inicia sesión con Firebase.
     * @param email Correo electrónico.
     * @param password Contraseña.
     */
    private void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();

                        // Eliminar la validación de verificación de correo
                        if (user != null) {
                            Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, ServicioActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Credenciales inválidas: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}


