package com.example.medisoft;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class VerFacturasActivity extends AppCompatActivity implements OnFacturaClickListener {

    private RecyclerView recyclerView;
    private FacturaAdapter adapter;
    private List<Factura> facturaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_facturas);

        recyclerView = findViewById(R.id.recyclerVerFacturas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Cargar datos de ejemplo (facturas pendientes)
        facturaList = new ArrayList<>();
        facturaList.add(new Factura("123456", "$100.000", "16/01/2024"));
        facturaList.add(new Factura("789012", "$120.000", "01/02/2024"));

        // Configurar adaptador (facturas pendientes → esFacturaPaga = true para que NO muestre el botón de pagar)
        adapter = new FacturaAdapter(facturaList, this, true);
        recyclerView.setAdapter(adapter);

        // Botón de retroceso
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    public void onFacturaClick(Factura factura, String action) {
        switch (action) {
            case "descargar":
                Toast.makeText(this, "Descargando factura: " + factura.getNombre(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}





