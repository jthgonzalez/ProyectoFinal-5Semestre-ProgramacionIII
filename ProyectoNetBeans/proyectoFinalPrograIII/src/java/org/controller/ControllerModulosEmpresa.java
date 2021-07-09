/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dao.DaoModuloEmpresa;
import org.models.ModelModuloEmpresa;

/**
 *
 * @author Administrador
 */
public class ControllerModulosEmpresa extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
     
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
       
                 
        String acceso="";        
        String action = request.getParameter("accion");        
        String listar = "ModuloEmpresa/consultaModulosEmpresa.jsp";
        String modifica = "ModuloEmpresa/modificaModulosEmpresa.jsp";
        ModelModuloEmpresa modulos = new ModelModuloEmpresa();
       DaoModuloEmpresa daoModulos = new DaoModuloEmpresa();
        
        switch (action){
            case "read":
                acceso = listar;
            break;
       
            case "editar":                
               //int id_modulo= Integer.parseInt(request.getParameter("id_modulo"));
            
                int empresa =Integer.parseInt(request.getParameter("id"));
               // modulos.setId_modulo(id_modulo);
                request.setAttribute("idEmpresa",empresa);
                
                acceso = modifica;
            break;
            case "delete":
            String   modulo = request.getParameter("modulo");
                 empresa =Integer.parseInt(request.getParameter("empresa"));
               // modulos.setId_modulo(id_modulo);
                modulos.setModulo(modulo);
                modulos.setId_empresa(empresa);


                daoModulos.eliminar(modulos);
                acceso = listar;
            break;
             default:
                 acceso = listar;
                 break;
        }
                RequestDispatcher vista = request.getRequestDispatcher(acceso); 
        vista.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelModuloEmpresa empresa=new ModelModuloEmpresa();
        DaoModuloEmpresa daoModEmpresa = new DaoModuloEmpresa();
       String action = request.getParameter("accion");
       int idempresa = 0;
       String modulo="";
       String message="";

      
       switch(action){
           case "add":
               idempresa = Integer.parseInt(request.getParameter("empresa"));
               modulo = request.getParameter("modulo");
                empresa.setModulo(modulo);
                empresa.setId_empresa(idempresa);
                daoModEmpresa.insertar(empresa);
               message="Agregado";  
               break;
           case "delete":
               idempresa = Integer.parseInt(request.getParameter("empresa"));
               modulo = request.getParameter("modulo");
                empresa.setModulo(modulo);
                empresa.setId_empresa(idempresa);
                daoModEmpresa.eliminar(empresa);
               message="Eliminado";  
               break;
       
       }
      
          try (PrintWriter out = response.getWriter()) {
  
            out.println(message);
          }
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
