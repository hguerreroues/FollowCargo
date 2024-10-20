
package com.followcargo.transporte.dao;

import java.io.Serializable;

public class Ruta implements Serializable {
    
    private int id;
    private int idViaje;
    private String origen;
    private String destino;
    private double distancia;
    private String fechaCreacion;

    public Ruta() {
    }

    public Ruta(int id, int idViaje, String origen, String destino, double distancia) {
        this.id = id;
        this.idViaje = idViaje;
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Ruta{" + "id=" + id + ", idViaje=" + idViaje + ", origen=" + origen + ", destino=" + destino + ", distancia=" + distancia + ", fechaCreacion=" + fechaCreacion + '}';
    }
    
    
}
