package com.followcargo.transporte.dao;

import java.io.Serializable;

public class Producto implements Serializable {
    
    private int id;
    private String nombre;
    private String descripcion;
    private double peso;
    private Dimensiones dimensiones;
    private String fechaCreacion;

    public Producto() {
    }

    public Producto(int id, String nombre, String descripcion, double peso, Dimensiones dimensiones) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.peso = peso;
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
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", peso=" + peso + ", dimensiones=" + dimensiones + ", fechaCreacion=" + fechaCreacion + '}';
    }
    
}
