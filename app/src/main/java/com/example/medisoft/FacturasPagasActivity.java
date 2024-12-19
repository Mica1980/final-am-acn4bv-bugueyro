package com.example.medisoft;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FacturasPagasActivity extends AppCompatActivity implements FacturaAdapter.OnFacturaClickListener {

    private RecyclerView recyclerFacturasPagas;
    private FacturaAdapter adapter;
    private List<Factura> facturasPagasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_facturas);

        // Configurar RecyclerView
        recyclerFacturasPagas = findViewById(R.id.recyclerVerFacturas);
        recyclerFacturasPagas.setLayoutManager(new LinearLayoutManager(this));

        facturasPagasList = new ArrayList<>();
        facturasPagasList.add(new Factura("Pago Enero 2024", "$100.000", "16/01/2024"));
        facturasPagasList.add(new Factura("Pago Febrero 2024", "$120.000", "16/02/2024"));

        adapter = new FacturaAdapter(facturasPagasList, this);
        recyclerFacturasPagas.setAdapter(adapter);

        // Botón de retroceso
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }

    // Métodos de la interfaz OnFacturaClickListener
    @Override
    public void onFacturaDescargarClick(Factura factura) {
        Toast.makeText(this, "Descargando factura: " + factura.getNombre(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFacturaVerClick(Factura factura) {
        Toast.makeText(this, "Viendo factura: " + factura.getNombre(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPagarClick(Factura factura) {
        Toast.makeText(this, "Pagando factura: " + factura.getNombre(), Toast.LENGTH_SHORT).show();
    }
}

