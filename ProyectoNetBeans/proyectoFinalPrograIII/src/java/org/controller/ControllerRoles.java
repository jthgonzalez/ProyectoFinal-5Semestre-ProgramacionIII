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
import org.dao.DaoRoles;
import org.models.ModelRoles;


@WebServlet(name = "ControllerRoles", urlPatterns = {"/ControllerRoles"})
public class ControllerRoles extends HttpServlet {
    String listar = "Roles/consultaRol.jsp";
    String add ="Roles/ingresaRol.jsp";
    String edit="Roles/modificaRol.jsp";

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
            out.println("<title>Servlet ControllerRoles</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerRoles at " + request.getContextPath() + "</h1>");
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
        
        ModelRoles roles = new ModelRoles();
        DaoRoles daoRoles = new DaoRoles();
        
        switch (action){
            case "read":
                acceso = listar;
            break;
            
            case "nuevo":
                acceso=add;
            break;
                
            case "create" :                
               
                String nombre= request.getParameter("nombre");
                int empresa= Integer.parseInt(request.getParameter("empresa"));

                
                roles.setNombre(nombre);
                roles.setIdEmpresa(empresa);

                daoRoles.insertar(roles);
                acceso = listar;
            break;
            case "editar":
                request.setAttribute("idRoles", request.getParameter("id"));
                acceso = edit;
            break;            
            case "update" :
                int idRol = Integer.parseInt(request.getParameter("codigo"));
                nombre= request.getParameter("nombre");
                empresa= Integer.parseInt(request.getParameter("empresa")) ;
                roles.setIdRol(idRol);
                roles.setNombre(nombre);
                roles.setIdEmpresa(empresa);
               
                
                
                daoRoles.modificar(roles);
                acceso = listar;                
            break;
            case "eliminar":
        
                idRol = Integer.parseInt(request.getParameter("id"));
                
                //request.setAttribute("idRol", request.getParameter("id"));
                //int idRolx= Integer.parseInt((String) request.getAttribute("idRol"));
                roles.setIdRol(idRol);
                daoRoles.eliminar(roles);
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
