/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.fernando.servicio;

import com.fernando.modelo.Medico;
import com.fernando.pruebabigview.dao.FactoryDAO;
import com.fernando.pruebabigview.dao.MedicoDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juan Fernando Arango <junfer.1105@gmail.com>
 */
@MultipartConfig
@WebServlet(name = "MedicoService", urlPatterns = {"/medico"})
public class MedicoService extends HttpServlet {
    private MedicoDAO medicoDAO;
    private String respuesta;
    private Medico medico;
    
    
    protected void processRequestGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id_medico_str = request.getParameter("id");
        String id_especialidad_str = request.getParameter("id_especialidad");
        Gson gson = new Gson();
        if (id_medico_str==null && id_especialidad_str==null){//No tiene parametro id por lo tanto retornamos todos
            medicoDAO = FactoryDAO.getMedicoDAO();
            List medicos = medicoDAO.getMedico();
            respuesta = gson.toJson(medicos);
        }else if(id_especialidad_str!=null){
            int id_especialidad = Integer.parseInt(id_especialidad_str);
            medicoDAO = FactoryDAO.getMedicoDAO();
            List medicos = medicoDAO.getMedicoPorEspecialidad(id_especialidad);
            respuesta = gson.toJson(medicos);
        }
        else{
            int id_medico = Integer.parseInt(id_medico_str);
            medicoDAO = FactoryDAO.getMedicoDAO();
            medico = medicoDAO.getMedico(id_medico);
            respuesta = gson.toJson(medico);
        }
        response.setContentType("application/json");
        response.getWriter().write(respuesta);
        
    }
    
    protected void processRequestPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id_medico_str = request.getParameter("id_medico");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String fec_nacimiento_str = request.getParameter("fec_nacimiento");
        String id_especialidad_str = request.getParameter("id_especialidad");
        
        int id_medico = Integer.parseInt(id_medico_str);
        int id_especialidad = Integer.parseInt(id_especialidad_str);
        Date fec_nacimiento = Date.valueOf(fec_nacimiento_str);
        
        medico = new Medico(id_medico, nombre, direccion, telefono ,fec_nacimiento, true, id_especialidad);
        medicoDAO = FactoryDAO.getMedicoDAO();
        
        if (medicoDAO.insertMedico(medico)){
             respuesta = "Insertado";
        }else{
            respuesta = "No insertado";
        }
        response.getWriter().write(respuesta);
    }
    
    protected void processRequestPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id_medico_str = request.getParameter("id_medico");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String fec_nacimiento_str = request.getParameter("fec_nacimiento");
        String id_especialidad_str = request.getParameter("id_especialidad");
        String is_activo_str = request.getParameter("activo");
        
        Boolean is_activo = Boolean.valueOf(is_activo_str);
        
        int id_medico = Integer.parseInt(id_medico_str);
        int id_especialidad = Integer.parseInt(id_especialidad_str);
        Date fec_nacimiento = Date.valueOf(fec_nacimiento_str);
        
        medico = new Medico(id_medico, nombre, direccion, telefono ,fec_nacimiento, is_activo, id_especialidad);
        medicoDAO = FactoryDAO.getMedicoDAO();
        
        if (medicoDAO.updateMedico(medico)){
             respuesta = "Actualizado";
        }else{
            respuesta = "No actualizado";
        }
        response.getWriter().write(respuesta);
    }
    
    protected void processRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id_medico_str = request.getParameter("id_medico");
        int id_medico = Integer.parseInt(id_medico_str);
        
        medicoDAO = FactoryDAO.getMedicoDAO();
        if (medicoDAO.deleteMedico(id_medico)){ //El delete inactiva el usuario
             respuesta = "Inactivado";
        }else{
            respuesta = "No inactivado";
        }
        response.getWriter().write(respuesta);
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequestGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequestPost(request, response);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequestPut(request, response);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequestDelete(request, response);
    }
    
        
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
