package com.followcargo.transporte.dao;

import java.io.Serializable;

public class Usuario implements Serializable {
    
    private String id;
    private String nombreUsuario;
    private String contra;
    private String rol;
    private String nombreCompleto;
    private String estado;
    private String fechaCreacion;

    public Usuario() {
    }

    public Usuario(String id, String nombreUsuario, String contra, String rol, String nombreCompleto, String estado) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contra = contra;
        this.rol = rol;
        this.nombreCompleto = nombreCompleto;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombreUsuario=" + nombreUsuario + ", contra=" + contra + ", rol=" + rol + ", nombreCompleto=" + nombreCompleto + ", estado=" + estado + ", fechaCreacion=" + fechaCreacion + '}';
    }

}
