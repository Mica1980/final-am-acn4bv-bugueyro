package com.example.medisoft;

public class Factura2 {
    private String numeroCuenta;
    private String monto;
    private String fechaVencimiento;

    public Factura2(String numeroCuenta, String monto, String fechaVencimiento) {
        this.numeroCuenta = numeroCuenta;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
    }

    // Getters y setters
    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getMonto() { return monto; }
    public void setMonto(String monto) { this.monto = monto; }

    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
}

