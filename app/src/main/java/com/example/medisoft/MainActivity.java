package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configura la pantalla completa si es necesario
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Inicia LoginActivity después de un retraso
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        }, 5000); // Especifica el retraso en milisegundos
    }
}
