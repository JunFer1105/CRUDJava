/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fernando.modelo;

import java.sql.Date;

/**
 *
 * @author Juan Fernando Arango <junfer.1105@gmail.com>
 */
public class Usuario {
    private int id_usuario;
    private String nombre_usuario;
    private String email;
    private String password;
    private boolean activo;
    private Date fec_insercion;

    //No se almacena la contraseña
    public Usuario(int id_usuario, String nombre_usuario, String email, boolean activo, Date fec_insercion) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.email = email;
        this.activo = activo;
        this.fec_insercion = fec_insercion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFec_insercion() {
        return fec_insercion;
    }

    public void setFec_insercion(Date fec_insercion) {
        this.fec_insercion = fec_insercion;
    }
    
}
