package com.example.medisoft;

public class Factura {
    private String nombre;
    private String monto;
    private String fechaVencimiento;

    public Factura(String nombre, String monto, String fechaVencimiento) {
        this.nombre = nombre;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMonto() {
        return monto;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }
}

