package com.PrestaServicio.model;

public class UsuarioModel {

    private Integer id_usuario;

    private String usuario;

    private String contrasena;

    private String nombre;

    private String FechaHoraCreacion;

    public Integer getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFechaHoraCreacion() {
        return FechaHoraCreacion;
    }
    public void setFechaHoraCreacion(String fechaHoraCreacion) {
        FechaHoraCreacion = fechaHoraCreacion;
    }

}
