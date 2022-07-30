/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fernando.pruebabigview.dao;

/**
 *
 * @author Juan Fernando Arango <junfer.1105@gmail.com>
 */
public class FactoryDAO {
    
    public static EspecialidadDAO getEspecialidadDAO(){
        return new EspecialidadDAO();
    }
    
    public static MedicoDAO getMedicoDAO(){
        return new MedicoDAO();
    }
    
    public static UsuarioDAO getUsuarioDAO(){
        return new UsuarioDAO();
    }
}
