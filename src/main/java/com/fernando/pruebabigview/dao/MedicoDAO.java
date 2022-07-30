/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fernando.pruebabigview.dao;

import com.fernando.modelo.Medico;
import java.sql.Connection;
import java.sql.Date;
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
public class MedicoDAO {
    private PreparedStatement pst;
    private Connection con;
    private String sentencia_sql;
    private ResultSet rs;
    private int resultado;
    private Medico medico;
    
    
    public MedicoDAO(){
        con = Conexion.getConexion();
    }
    
    public Medico getMedico(int id_medico){
        Medico medico = null;
        try {
            sentencia_sql = "SELECT ID_MEDICO, NOMBRE, FECHA_NACIMIENTO, DIRECCION, TELEFONO, ID_ESPECIALIDAD, ACTIVO FROM `medico` "
                    + " WHERE ID_MEDICO = ? ";
            pst = con.prepareStatement(sentencia_sql);
            pst.setInt(1, id_medico);
            rs = pst.executeQuery();
            
            while(rs.next()){
                String nombre = rs.getString("NOMBRE");
                String direccion = rs.getString("DIRECCION");
                Date fec_nacimiento = rs.getDate("FECHA_NACIMIENTO");
                String telefono = rs.getString("TELEFONO");
                Boolean activo = rs.getBoolean("ACTIVO");
                int id_especialidad = rs.getInt("ID_ESPECIALIDAD");
                
                medico = new Medico(id_medico, nombre, direccion, telefono, fec_nacimiento, activo, id_especialidad);
            }
            return medico;
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return medico;
        }  
    }
    
    public List<Medico> getMedico(){
        List medicos = new ArrayList();
        try {
            sentencia_sql = "SELECT ID_MEDICO, NOMBRE, FECHA_NACIMIENTO, DIRECCION, TELEFONO, ID_ESPECIALIDAD, ACTIVO FROM `medico` ";
            pst = con.prepareStatement(sentencia_sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                int id_medico = rs.getInt("ID_MEDICO");
                String nombre = rs.getString("NOMBRE");
                String direccion = rs.getString("DIRECCION");
                Date fec_nacimiento = rs.getDate("FECHA_NACIMIENTO");
                String telefono = rs.getString("TELEFONO");
                Boolean activo = rs.getBoolean("ACTIVO");
                int id_especialidad = rs.getInt("ID_ESPECIALIDAD");
                
                medico = new Medico(id_medico, nombre, direccion, telefono, fec_nacimiento, activo, id_especialidad);
                medicos.add(medico);
            }
            return medicos;
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return medicos;
        }
    }
    
    public List<Medico> getMedicoPorEspecialidad(int id_especialidad){
        List medicos = new ArrayList();
        try {
            sentencia_sql = "SELECT ID_MEDICO, NOMBRE, FECHA_NACIMIENTO, DIRECCION, TELEFONO, ID_ESPECIALIDAD, ACTIVO FROM `medico` "
                    + "WHERE ID_ESPECIALIDAD = ?";
            pst = con.prepareStatement(sentencia_sql);
            pst.setInt(1, id_especialidad);
            rs = pst.executeQuery();
            
            while(rs.next()){
                int id_medico = rs.getInt("ID_MEDICO");
                String nombre = rs.getString("NOMBRE");
                String direccion = rs.getString("DIRECCION");
                Date fec_nacimiento = rs.getDate("FECHA_NACIMIENTO");
                String telefono = rs.getString("TELEFONO");
                Boolean activo = rs.getBoolean("ACTIVO");
                                
                medico = new Medico(id_medico, nombre, direccion, telefono, fec_nacimiento, activo, id_especialidad);
                medicos.add(medico);
            }
            return medicos;
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return medicos;
        }
    }
    
    public boolean insertMedico(Medico medico){
        try {
            sentencia_sql = "INSERT INTO `medico`(`ID_MEDICO`, `NOMBRE`, `FECHA_NACIMIENTO`, `DIRECCION`, `TELEFONO`, "
                    + "`ID_ESPECIALIDAD`, `ACTIVO`) "
                    + " VALUES (?,?,?,?,?,?,?) ";
            pst = con.prepareStatement(sentencia_sql);
            
            pst.setInt(1, medico.getId_medico());
            pst.setString(2, medico.getNombre());
            pst.setDate(3, medico.getFecha_nacimiento());
            pst.setString(4, medico.getDireccion());
            pst.setString(5, medico.getTelefono());
            pst.setInt(6, medico.getId_especialidad());
            pst.setBoolean(7, medico.isActivo());
                        
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
    
    public boolean updateMedico(Medico medico){
        try {
            sentencia_sql = "UPDATE `medico` SET `NOMBRE`=?,`FECHA_NACIMIENTO`=?,`DIRECCION`=?,"
                    + "`TELEFONO`=?,`ID_ESPECIALIDAD`=?,`ACTIVO`=? WHERE `ID_MEDICO` = ? ";
            pst = con.prepareStatement(sentencia_sql);
                        
            pst.setString(1, medico.getNombre());
            pst.setDate(2, medico.getFecha_nacimiento());
            pst.setString(3, medico.getDireccion());
            pst.setString(4, medico.getTelefono());
            pst.setInt(5, medico.getId_especialidad());
            pst.setBoolean(6, medico.isActivo());
            
            pst.setInt(7, medico.getId_medico());
                        
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
    
    public boolean deleteMedico(int id_medico){//No se elimina sino un borrado logico
        try {
            sentencia_sql = "UPDATE `medico` SET `ACTIVO`=? WHERE `ID_MEDICO` = ? ";
            pst = con.prepareStatement(sentencia_sql);
                        
            pst.setBoolean(1, false);
            pst.setInt(2, id_medico);
                                    
            resultado = pst.executeUpdate();
            if (resultado==1){//Registro actualizado correctamente
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
