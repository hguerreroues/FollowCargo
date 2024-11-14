package com.followcargo.transporte.dao;

import java.io.Serializable;

public class Vehiculo implements Serializable {
    
    private int id;
    private String tipo;
    private String marca;
    private String modelo;
    private String placa;
    private String fechaCreacion;
    
    private int idConductor;
    private double costoFijoViaje;

    public Vehiculo() {
    }

    public Vehiculo(int id, String tipo, String marca, String modelo, String placa) {
        this.id = id;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }

    public Vehiculo(String tipo, String marca, String modelo, String placa) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public double getCostoFijoViaje() {
        return costoFijoViaje;
    }

    public void setCostoFijoViaje(double costoFijoViaje) {
        this.costoFijoViaje = costoFijoViaje;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", tipo=" + tipo + ", marca=" + marca + ", modelo=" + modelo + ", placa=" + placa + ", fechaCreacion=" + fechaCreacion + ", idConductor=" + idConductor + ", costoFijoViaje=" + costoFijoViaje + '}';
    }
    
}
