/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fernando.pruebabigview.dao;

import com.fernando.modelo.Especialidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Fernando Arango <junfer.1105@gmail.com>
 */
public class EspecialidadDAO {
    private PreparedStatement pst;
    private Connection con;
    private String sentencia_sql;
    private ResultSet rs;
    private int resultado;
    private Especialidad especialidad;
    
    
    public EspecialidadDAO(){
        con = Conexion.getConexion();
    }
    
    public Especialidad getEspecialidad(int id_especialidad){
        Especialidad especialidad = null;
        try {
            sentencia_sql = "SELECT ID_ESPECIALIDAD, DESCRIPCION FROM ESPECIALIDAD "
                    + " WHERE ID_ESPECIALIDAD = ? ";
            pst = con.prepareStatement(sentencia_sql);
            pst.setInt(1, id_especialidad);
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                String descripcion = rs.getString("DESCRIPCION");
                especialidad = new Especialidad(id_especialidad, descripcion);
            }
            return especialidad;
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return especialidad;
        }  
    }
    
    public List<Especialidad> getEspecialidad(){
        List especialidades = new ArrayList();
        try {
            sentencia_sql = "SELECT ID_ESPECIALIDAD, DESCRIPCION FROM ESPECIALIDAD ";
            pst = con.prepareStatement(sentencia_sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                int id_especialidad = rs.getInt("ID_ESPECIALIDAD");
                String descripcion = rs.getString("DESCRIPCION");
                especialidad = new Especialidad(id_especialidad, descripcion);
                especialidades.add(especialidad);
            }
            return especialidades;
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return especialidades;
        }
    }
    
    public boolean insertEspecialidad(Especialidad especialidad){
        try {
            sentencia_sql = "INSERT INTO ESPECIALIDAD (`ID_ESPECIALIDAD`, `DESCRIPCION`)  VALUES (?,?)";
            pst = con.prepareStatement(sentencia_sql);
            pst.setInt(1, especialidad.getId_especialidad());
            pst.setString(2, especialidad.getDescripcion());
            resultado = pst.executeUpdate();
            if (resultado==1){//Registro insertado correctamente
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }    
}
