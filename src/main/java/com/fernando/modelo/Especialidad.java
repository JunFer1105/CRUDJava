package com.fernando.modelo;

public class Especialidad {
    private int id_especialidad;
    private String descripcion;

    public Especialidad(int id_especialidad, String descripcion) {
        this.id_especialidad = id_especialidad;
        this.descripcion = descripcion;
    }

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
