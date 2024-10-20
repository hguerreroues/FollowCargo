package com.followcargo.transporte.dao;

import java.io.Serializable;
import java.util.List;

public class Viaje implements Serializable {
    
    private int id;
    private int idVehiculo;
    private int idRuta;
    private List<Producto> listaProductos;
    private List<Geolocalizacion> listaGeoLocalizacion;
    private double costo;
    private String estado;
    private String fecha;
    private String fechaCreacion;

    public Viaje() {
    }

    public Viaje(int id, int idVehiculo, int idRuta, double costo, String estado, String fecha) {
        this.id = id;
        this.idVehiculo = idVehiculo;
        this.idRuta = idRuta;
        this.costo = costo;
        this.estado = estado;
        this.fecha = fecha;
    }

    
    public Viaje(int idVehiculo, int idRuta, List<Producto> listaProductos, double costo, String estado, String fecha) {
        this.idVehiculo = idVehiculo;
        this.idRuta = idRuta;
        this.listaProductos = listaProductos;
        this.costo = costo;
        this.estado = estado;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Geolocalizacion> getListaGeoLocalizacion() {
        return listaGeoLocalizacion;
    }

    public void setListaGeoLocalizacion(List<Geolocalizacion> listaGeoLocalizacion) {
        this.listaGeoLocalizacion = listaGeoLocalizacion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Viaje{" + "id=" + id + ", idVehiculo=" + idVehiculo + ", idRuta=" + idRuta + ", listaProductos=" + listaProductos + ", listaGeoLocalizacion=" + listaGeoLocalizacion + ", costo=" + costo + ", estado=" + estado + ", fecha=" + fecha + ", fechaCreacion=" + fechaCreacion + '}';
    }
    
    
}
