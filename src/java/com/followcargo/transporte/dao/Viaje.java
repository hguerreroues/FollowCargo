package com.followcargo.transporte.dao;

import java.io.Serializable;
import java.util.List;

public class Viaje implements Serializable {
    
    private int id;
    private int idVehiculo;
    private int idConductor;
    private List<Producto> listaProductos;
    private double costo;
    private String estado;
    private String fecha;
    private String fechaCreacion;
    
    private Vehiculo vehiculo;
    private Conductor conductor;

    public Viaje() {
    }

    public Viaje(int id, int idVehiculo, int idConductor, double costo, String estado, String fecha) {
        this.id = id;
        this.idVehiculo = idVehiculo;
        this.idConductor = idConductor;
        this.costo = costo;
        this.estado = estado;
        this.fecha = fecha;
    }

    
    public Viaje(int idVehiculo, int idConductor, List<Producto> listaProductos, double costo, String estado, String fecha) {
        this.idVehiculo = idVehiculo;
        this.idConductor = idConductor;
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

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }
    
    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
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

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }
    
    @Override
    public String toString() {
        return "Viaje{" + "id=" + id + ", idVehiculo=" + idVehiculo + ", idConductor=" + idConductor + ", listaProductos=" + listaProductos + ", costo=" + costo + ", estado=" + estado + ", fecha=" + fecha + ", fechaCreacion=" + fechaCreacion + '}';
    }

}
