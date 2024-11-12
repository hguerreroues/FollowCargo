package com.followcargo.transporte.dao;

import java.io.Serializable;

public class Conductor implements Serializable {

    private int id;
    private String nombres;
    private String apellidos;
    private String licencia;
    private String fechaLicencia;
    private String vencimientoLicencia;
    private String telefono;
    private String email;
    private String estado;
    private String ubicacionActual;
    private int idUltimaRuta;

    public Conductor() {
    }

    public Conductor(int id, String nombres, String apellidos, String licencia, String fechaLicencia, String vencimientoLicencia, String telefono, String email, String estado, String ubicacionActual, int idUltimaRuta) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.licencia = licencia;
        this.fechaLicencia = fechaLicencia;
        this.vencimientoLicencia = vencimientoLicencia;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.ubicacionActual = ubicacionActual;
        this.idUltimaRuta = idUltimaRuta;
    }

    public Conductor(String nombres, String apellidos, String licencia, String fechaLicencia, String vencimientoLicencia, String telefono, String email) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.licencia = licencia;
        this.fechaLicencia = fechaLicencia;
        this.vencimientoLicencia = vencimientoLicencia;
        this.telefono = telefono;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getFechaLicencia() {
        return fechaLicencia;
    }

    public void setFechaLicencia(String fechaLicencia) {
        this.fechaLicencia = fechaLicencia;
    }

    public String getVencimientoLicencia() {
        return vencimientoLicencia;
    }

    public void setVencimientoLicencia(String vencimientoLicencia) {
        this.vencimientoLicencia = vencimientoLicencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public int getIdUltimaRuta() {
        return idUltimaRuta;
    }

    public void setIdUltimaRuta(int idUltimaRuta) {
        this.idUltimaRuta = idUltimaRuta;
    }

    @Override
    public String toString() {
        return "Conductor{" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", licencia=" + licencia + ", fechaLicencia=" + fechaLicencia + ", vencimientoLicencia=" + vencimientoLicencia + ", telefono=" + telefono + ", email=" + email + ", estado=" + estado + ", ubicacionActual=" + ubicacionActual + ", idUltimaRuta=" + idUltimaRuta + '}';
    }

    
}
