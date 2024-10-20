package com.followcargo.transporte.dao;

import java.io.Serializable;

public class Geolocalizacion implements Serializable {
    
    private int id;
    private int idRuta;
    private double latitud;
    private double longitud;
    private String descripcion;
    private String fechaCreacion;

    public Geolocalizacion() {
    }

    public Geolocalizacion(int idRuta, double latitud, double longitud) {
        this.idRuta = idRuta;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Geolocalizacion(int id, int idRuta, double latitud, double longitud, String descripcion) {
        this.id = id;
        this.idRuta = idRuta;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    
    
    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "GeoLocalizacion{" + "id=" + id + ", idRuta=" + idRuta + ", latitud=" + latitud + ", longitud=" + longitud + ", descripcion=" + descripcion + ", fechaCreacion=" + fechaCreacion + '}';
    }

}
