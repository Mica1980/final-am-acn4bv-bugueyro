package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

public class MainActivity extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configura la pantalla completa
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);

        // Inicia LoginActivity después de un retraso
        handler.postDelayed(() -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }, 3000); // Retraso de 3 segundos
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Elimina cualquier callback pendiente
        handler.removeCallbacksAndMessages(null);
    }
}

