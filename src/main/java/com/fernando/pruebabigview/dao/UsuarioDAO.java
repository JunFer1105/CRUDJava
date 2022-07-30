/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fernando.pruebabigview.dao;

import com.fernando.modelo.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Fernando Arango <junfer.1105@gmail.com>
 */
public class UsuarioDAO {
    private PreparedStatement pst;
    private Connection con;
    private String sentencia_sql;
    private ResultSet rs;
    private int resultado;
    private Usuario usuario;
    
    
    public UsuarioDAO(){
        con = Conexion.getConexion();
    }
    
    public Usuario getUsuarioLogin(String email, String password){
        Usuario usuario = null;
        try {
            sentencia_sql = "SELECT `ID_USUARIO`, `NOMBRE_USUARIO`, `EMAIL`, `PASSWORD`, `ACTIVO`, `FECHA_INSERCION` "
                    + " FROM `usuario` WHERE `EMAIL` = ?  AND `PASSWORD` = ? ";
            pst = con.prepareStatement(sentencia_sql);
            pst.setString(1, email);
            pst.setString(2, password);
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                int id_usuario = rs.getInt("ID_USUARIO");
                String nombre_usuario = rs.getString("NOMBRE_USUARIO");
                boolean activo = rs.getBoolean("ACTIVO");
                Date fec_insercion = rs.getDate("FECHA_INSERCION");
                usuario = new Usuario(id_usuario, nombre_usuario, email, activo, fec_insercion);
            }
            return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return usuario;
        }  
    }
}
