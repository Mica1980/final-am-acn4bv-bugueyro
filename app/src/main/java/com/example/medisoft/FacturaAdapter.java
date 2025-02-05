package com.example.medisoft;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FacturaAdapter extends RecyclerView.Adapter<FacturaAdapter.FacturaViewHolder> {

    private final List<Factura> facturaList;
    private final OnFacturaClickListener listener;
    private final boolean esFacturaPaga;

    public FacturaAdapter(List<Factura> facturaList, OnFacturaClickListener listener, boolean esFacturaPaga) {
        this.facturaList = facturaList;
        this.listener = listener;
        this.esFacturaPaga = esFacturaPaga;
    }

    @NonNull
    @Override
    public FacturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_factura_paga, parent, false);
        return new FacturaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacturaViewHolder holder, int position) {
        Factura factura = facturaList.get(position);

        if (factura != null) {
            holder.txtCuenta.setText("Cuenta: " + factura.getNombre());
            holder.txtMonto.setText("Monto: " + factura.getMonto());
            holder.txtFecha.setText("Vencimiento: " + factura.getFechaVencimiento());

            // BOTÓN DESCARGAR
            holder.btnDescargar.setOnClickListener(v -> listener.onFacturaClick(factura, "descargar"));

            // BOTÓN PAGAR SOLO SI LA FACTURA NO ESTÁ PAGA
            if (esFacturaPaga) {
                holder.btnPagar.setVisibility(View.GONE);
            } else {
                holder.btnPagar.setVisibility(View.VISIBLE);
                holder.btnPagar.setOnClickListener(v -> listener.onFacturaClick(factura, "pagar"));
            }
        }
    }

    @Override
    public int getItemCount() {
        return facturaList.size();
    }

    public static class FacturaViewHolder extends RecyclerView.ViewHolder {
        TextView txtCuenta, txtMonto, txtFecha;
        ImageView btnDescargar;
        Button btnPagar;

        public FacturaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCuenta = itemView.findViewById(R.id.txtCuenta);
            txtMonto = itemView.findViewById(R.id.txtMonto);
            txtFecha = itemView.findViewById(R.id.txtFecha);
            btnDescargar = itemView.findViewById(R.id.btnDescargar);
            btnPagar = itemView.findViewById(R.id.btnPagar);
        }
    }
}
