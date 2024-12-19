package com.example.medisoft;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FirestoreHelper {

    private static final String TAG = "FirestoreHelper";
    private FirebaseFirestore db;

    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
    }

    // Obtener fecha actual
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }

    // 1. Altas de Clientes
    public void agregarCliente(String clienteId, String email, String nombre, String numeroSocio) {
        Map<String, Object> cliente = new HashMap<>();
        cliente.put("email", email);
        cliente.put("nombre", nombre);
        cliente.put("numeroSocio", numeroSocio);
        cliente.put("estado", "activo");
        cliente.put("fechaAlta", getCurrentDate());

        db.collection("clientes").document(clienteId)
                .set(cliente)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Cliente agregado exitosamente"))
                .addOnFailureListener(e -> Log.e(TAG, "Error al agregar cliente", e));
    }

    // 2. Bajas de Clientes
    public void darDeBajaCliente(String clienteId) {
        Map<String, Object> update = new HashMap<>();
        update.put("estado", "baja");
        update.put("fechaBaja", getCurrentDate());

        db.collection("clientes").document(clienteId)
                .update(update)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Cliente dado de baja correctamente"))
                .addOnFailureListener(e -> Log.e(TAG, "Error al dar de baja al cliente", e));
    }

    // 3. Altas de Facturas
    public void agregarFactura(String userId, String facturaId, String monto, String fechaVencimiento, boolean pagada, String formaPago) {
        Map<String, Object> factura = new HashMap<>();
        factura.put("monto", monto);
        factura.put("fechaVencimiento", fechaVencimiento);
        factura.put("pagada", pagada);
        factura.put("formaPago", formaPago);

        db.collection("users").document(userId)
                .collection("facturas").document(facturaId)
                .set(factura)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Factura agregada exitosamente"))
                .addOnFailureListener(e -> Log.e(TAG, "Error al agregar factura", e));
    }

    // 4. Bajas de Facturas
    public void eliminarFactura(String userId, String facturaId) {
        db.collection("users").document(userId)
                .collection("facturas").document(facturaId)
                .delete()
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Factura eliminada correctamente"))
                .addOnFailureListener(e -> Log.e(TAG, "Error al eliminar factura", e));
    }

    // 5. Altas de Servicio Técnico
    public void agregarServicioTecnico(String userId, String servicioId, String descripcion, String fechaSolicitud, String estado) {
        Map<String, Object> servicio = new HashMap<>();
        servicio.put("descripcion", descripcion);
        servicio.put("fechaSolicitud", fechaSolicitud);
        servicio.put("estado", estado); // "Pendiente", "Resuelto", etc.

        db.collection("users").document(userId)
                .collection("serviciosTecnicos").document(servicioId)
                .set(servicio)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Servicio técnico agregado correctamente"))
                .addOnFailureListener(e -> Log.e(TAG, "Error al agregar servicio técnico", e));
    }

    // 6. Bajas de Servicio Técnico
    public void eliminarServicioTecnico(String userId, String servicioId) {
        db.collection("users").document(userId)
                .collection("serviciosTecnicos").document(servicioId)
                .delete()
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Servicio técnico eliminado correctamente"))
                .addOnFailureListener(e -> Log.e(TAG, "Error al eliminar servicio técnico", e));
    }
}
