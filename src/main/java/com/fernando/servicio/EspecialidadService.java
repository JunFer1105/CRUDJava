/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.fernando.servicio;

import com.fernando.modelo.Especialidad;
import com.fernando.pruebabigview.dao.EspecialidadDAO;
import com.fernando.pruebabigview.dao.FactoryDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Iterator;
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
@WebServlet(name = "EspecialidadService", urlPatterns = {"/especialidad"})
public class EspecialidadService extends HttpServlet {
    private String respuesta = "";
    private EspecialidadDAO especialidadDAO ;
    private Especialidad especialidad;
    
    protected void processRequestGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id_especialidad_str = request.getParameter("id");
        Gson gson = new Gson();
        if (id_especialidad_str==null){//No tiene parametro id por lo tanto retornamos todos
            especialidadDAO = FactoryDAO.getEspecialidadDAO();
            List especialidades = especialidadDAO.getEspecialidad();
            respuesta = gson.toJson(especialidades);
        }else{
            int id_especialidad = Integer.parseInt(id_especialidad_str);
            especialidadDAO = FactoryDAO.getEspecialidadDAO();
            especialidad = especialidadDAO.getEspecialidad(id_especialidad);
            respuesta = gson.toJson(especialidad);
        }
        response.setContentType("application/json");
        response.getWriter().write(respuesta);
        
    }
    
    protected void processRequestPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id_especialidad_str = request.getParameter("id_especialidad");
        String descripcion = request.getParameter("descripcion");
        
        int id_especialidad = Integer.parseInt(id_especialidad_str);
        
        especialidad = new Especialidad(id_especialidad,descripcion);
        especialidadDAO = FactoryDAO.getEspecialidadDAO();
        
        if (especialidadDAO.insertEspecialidad(especialidad)){
             respuesta = "Insertado";
        }else{
            respuesta = "No insertado";
        }
        response.getWriter().write(respuesta);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
