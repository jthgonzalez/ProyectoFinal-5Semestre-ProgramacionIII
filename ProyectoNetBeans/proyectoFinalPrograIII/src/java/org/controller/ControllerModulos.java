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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dao.DaoModulos;
import org.models.ModelModulo;


@WebServlet(name = "ControllerModulos", urlPatterns = {"/ControllerModulos"})
public class ControllerModulos extends HttpServlet {
    String listar = "Modulo/consultaModulos.jsp";
    String add ="Modulo/ingresaModulos.jsp";
    String edit="Modulo/modificaModulos.jsp";

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerModulos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerModulos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
       // processRequest(request, response);
       String acceso="";        
        String action = request.getParameter("accion");        
        
        ModelModulo modulos = new ModelModulo();
        DaoModulos daoModulos = new DaoModulos();
        
        switch (action){
            case "read":
                acceso = listar;
            break;
            
            case "nuevo":
                acceso=add;
            break;
                
            case "create" :                
               //int id_modulo= Integer.parseInt(request.getParameter("id_modulo"));
                String modulo = request.getParameter("modulo");
                String descripcion = request.getParameter("descripcion");
                String ayuda= request.getParameter("ayuda");
               
                

                
               // modulos.setId_modulo(id_modulo);
                modulos.setModulo(modulo);
                modulos.setDescripcion(descripcion);
                modulos.setAyuda(ayuda);

                daoModulos.insertar(modulos);
                acceso = listar;
            break;
            case "editar":
                request.setAttribute("id_modulo", request.getParameter("id"));
                acceso = edit;
            break;            
            case "update" :
                int id_modulo = Integer.parseInt(request.getParameter("codigo"));
                modulo = request.getParameter("modulo");
                descripcion= request.getParameter("descripcion");
                ayuda= request.getParameter("ayuda");
               
                modulos.setId_modulo(id_modulo);
                modulos.setModulo(modulo);
                modulos.setDescripcion(descripcion);
                modulos.setAyuda(ayuda);
               
               
                
                
                daoModulos.modificar(modulos);
                acceso = listar;                
            break;
            case "delete":
        
                id_modulo = Integer.parseInt(request.getParameter("id"));
              
                modulos.setId_modulo(id_modulo);
                daoModulos.eliminar(modulos);
                acceso = listar;
            break; 
            case "buscar":
               
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
        //processRequest(request, response);
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
