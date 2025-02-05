package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FacturaActivity extends AppCompatActivity implements OnFacturaClickListener {
    private RecyclerView recyclerView;
    private FacturaAdapter adapter;
    private List<Factura> facturaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        // BOTÓN DE RETROCESO (FUNCIONANDO)
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // BOTÓN PARA VER FACTURAS
        Button btnVerFacturas = findViewById(R.id.btnVerFacturas);
        btnVerFacturas.setOnClickListener(v -> {
            Intent intent = new Intent(FacturaActivity.this, VerFacturasActivity.class);
            startActivity(intent);
        });

        // BOTÓN PARA VER PAGOS
        Button btnVerPagos = findViewById(R.id.btnVerPagos);
        btnVerPagos.setOnClickListener(v -> {
            Intent intent = new Intent(FacturaActivity.this, FacturasPagasActivity.class);
            startActivity(intent);
        });

        // CONFIGURAR RECYCLERVIEW
        recyclerView = findViewById(R.id.recyclerFacturas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // CARGAR FACTURAS DE EJEMPLO
        facturaList = new ArrayList<>();
        facturaList.add(new Factura("123456", "$100.000", "16/01/2024"));
        facturaList.add(new Factura("789012", "$120.000", "01/02/2024"));

        // CONFIGURAR ADAPTADOR (FACTURAS PENDIENTES → esFacturaPaga = false)
        adapter = new FacturaAdapter(facturaList, this, false);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFacturaClick(Factura factura, String action) {
        switch (action) {
            case "descargar":
                Toast.makeText(this, "Descargando factura: " + factura.getNombre(), Toast.LENGTH_SHORT).show();
                break;
            case "pagar":
                Intent intentPagar = new Intent(this, PagoActivity.class);
                startActivity(intentPagar);
                break;
        }
    }
}
