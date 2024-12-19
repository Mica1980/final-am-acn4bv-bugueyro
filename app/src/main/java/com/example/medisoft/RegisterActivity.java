package com.example.medisoft;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton, closeButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // Inicializar vistas
        emailEditText = findViewById(R.id.newUsername);
        passwordEditText = findViewById(R.id.repeatUsername);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
        registerButton = findViewById(R.id.createButton);
        closeButton = findViewById(R.id.closeButton);
        TextView forgotPassword = findViewById(R.id.forgotPassword);

        // Inicializar FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        // Configurar botón de registro
        registerButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (validateInputs(email, password, confirmPassword)) {
                if (isNetworkAvailable()) {
                    registerUser(email, password);
                } else {
                    Toast.makeText(this, "Sin conexión a internet. Verifica tu red.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configurar botón de cerrar
        closeButton.setOnClickListener(v -> finish());

        // Listener para "Olvidé mi contraseña"
        forgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, UserConfigActivity.class);
            startActivity(intent);
        });
    }

    private boolean validateInputs(String email, String password, String confirmPassword) {
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Ingrese un correo electrónico");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Ingrese una contraseña");
            return false;
        }
        if (!isValidPassword(password)) {
            passwordEditText.setError("La contraseña debe tener al menos 10 caracteres, incluir una mayúscula y un número");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Las contraseñas no coinciden");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{10,}$";
        return password.matches(passwordPattern);
    }

    private void registerUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            user.sendEmailVerification()
                                    .addOnCompleteListener(emailTask -> {
                                        if (emailTask.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this,
                                                    "Registro exitoso. Verifica tu correo electrónico para activar tu cuenta.",
                                                    Toast.LENGTH_LONG).show();
                                            redirectToLogin();
                                        } else {
                                            Log.e("RegisterActivity", "Error al enviar correo: ", emailTask.getException());
                                            Toast.makeText(RegisterActivity.this,
                                                    "Error al enviar correo de verificación. Inténtalo de nuevo.",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }
                    } else {
                        Log.e("RegisterActivity", "Error en registro: ", task.getException());
                        Toast.makeText(RegisterActivity.this,
                                "Error: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void redirectToLogin() {
        firebaseAuth.signOut();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Cancelar registro")
                .setMessage("¿Estás seguro de que quieres salir del registro?")
                .setPositiveButton("Sí", (dialog, which) -> super.onBackPressed())
                .setNegativeButton("No", null)
                .show();
    }
}
