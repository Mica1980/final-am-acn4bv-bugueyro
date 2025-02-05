package com.example.medisoft;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FacturasPagasActivity extends AppCompatActivity implements OnFacturaClickListener {

    private RecyclerView recyclerFacturasPagas;
    private FacturaAdapter adapter;
    private List<Factura> facturasPagasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturas_pagas);

        recyclerFacturasPagas = findViewById(R.id.recycler_facturas_pagas);
        recyclerFacturasPagas.setLayoutManager(new LinearLayoutManager(this));

        // Lista de facturas pagas
        facturasPagasList = new ArrayList<>();
        facturasPagasList.add(new Factura("Pago Enero 2024", "$100.000", "16/01/2024"));
        facturasPagasList.add(new Factura("Pago Febrero 2024", "$120.000", "16/02/2024"));

        // Configurar adaptador (facturas pagas → esFacturaPaga = true)
        adapter = new FacturaAdapter(facturasPagasList, this, true);
        recyclerFacturasPagas.setAdapter(adapter);

        // Botón de retroceso
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    public void onFacturaClick(Factura factura, String action) {
        if ("descargar".equals(action)) {
            Toast.makeText(this, "Descargando factura: " + factura.getNombre(), Toast.LENGTH_SHORT).show();
        }
    }
}
