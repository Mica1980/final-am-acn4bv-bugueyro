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

    public FacturaAdapter(List<Factura> facturaList, OnFacturaClickListener listener) {
        this.facturaList = facturaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FacturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout "activity_factura.xml"
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_factura, parent, false);
        return new FacturaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacturaViewHolder holder, int position) {
        Factura factura = facturaList.get(position);

        // Configurar vistas
        holder.txtCuenta.setText("Número de cuenta: " + factura.getNombre());
        holder.txtMonto.setText("Monto: " + factura.getMonto());
        holder.txtFecha.setText("Fecha de vencimiento: " + factura.getFechaVencimiento());

        // Configurar listeners
        holder.btnDescargar.setOnClickListener(v -> listener.onFacturaDescargarClick(factura));
        holder.btnVerFactura.setOnClickListener(v -> listener.onFacturaVerClick(factura));
        holder.btnPagar.setOnClickListener(v -> listener.onPagarClick(factura));
    }

    @Override
    public int getItemCount() {
        return facturaList.size();
    }

    public static class FacturaViewHolder extends RecyclerView.ViewHolder {
        TextView txtCuenta, txtMonto, txtFecha;
        ImageView btnDescargar; // Descargar es una imagen
        Button btnVerFactura, btnPagar;

        public FacturaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCuenta = itemView.findViewById(R.id.txtCuenta);
            txtMonto = itemView.findViewById(R.id.txtMonto);
            txtFecha = itemView.findViewById(R.id.txtFecha);
            btnDescargar = itemView.findViewById(R.id.btnDescargar);
            btnVerFactura = itemView.findViewById(R.id.btnVerFacturas);
            btnPagar = itemView.findViewById(R.id.btnPagar);
        }
    }

    public interface OnFacturaClickListener {
        void onFacturaDescargarClick(Factura factura);
        void onFacturaVerClick(Factura factura);
        void onPagarClick(Factura factura);
    }
}

