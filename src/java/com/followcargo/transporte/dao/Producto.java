package com.followcargo.transporte.dao;

import java.io.Serializable;

public class Producto implements Serializable {
    
    private int id;
    private String nombre;
    private String descripcion;
    private double peso;
    private int cantidad;
    private String unidadMedida;
    private double precio;
    private Dimensiones dimensiones;
    private String fechaCreacion;

    public Producto() {
    }

    
    //Para lista de productos por viaje
    public Producto(String nombre, String descripcion, double peso, String unidadMedida, Dimensiones dimensiones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.peso = peso;
        this.unidadMedida = unidadMedida;
        this.dimensiones = dimensiones;
    }
    
    

    public Producto(int id, String nombre, String descripcion, double peso, int cantidad, String unidadMedida, double precio, Dimensiones dimensiones, String fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.peso = peso;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.precio = precio;
        this.dimensiones = dimensiones;
        this.fechaCreacion = fechaCreacion;
    }

    public Producto(String nombre, String descripcion, double peso, int cantidad, String unidadMedida, double precio, Dimensiones dimensiones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.peso = peso;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.precio = precio;
        this.dimensiones = dimensiones;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public Dimensiones getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(Dimensiones dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", peso=" + peso + ", cantidad=" + cantidad + ", unidadMedida=" + unidadMedida + ", precio=" + precio + ", dimensiones=" + dimensiones + ", fechaCreacion=" + fechaCreacion + '}';
    }

}
