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
import org.dao.DaoRolUsuario;
import org.models.ModelRolUsuario;


@WebServlet(name = "ControllerRolUsuario", urlPatterns = {"/ControllerRolUsuario"})
public class ControllerRolUsuario extends HttpServlet {
    String listar = "RolUsuario/consultaRol.jsp";
    String add ="RolUsuario/ingresaRol.jsp";
    String edit="RolUsuario/modificaRol.jsp";
    String ar="RolUsuario/agregarRol.jsp";

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
            out.println("<title>Servlet ControllerRolUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerRolUsuario at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
           String acceso="";        
        String action = request.getParameter("accion");        
        
        ModelRolUsuario roles = new ModelRolUsuario();
        DaoRolUsuario daoRoles = new DaoRolUsuario();
        
        switch (action){
            case "read":
                acceso = listar;
            break;
            
            case "nuevo":
                acceso=add;
            break;
                
            case "create" :                
               
                String usuario= request.getParameter("usuario");
                int empresa= Integer.parseInt(request.getParameter("empresa"));

                
                roles.setUsuario(usuario);
                roles.setIdEmpresa(empresa);

                daoRoles.insertar(roles);
                acceso = listar;
            break;
            case "editar":
                request.setAttribute("idRoles", request.getParameter("id"));
                acceso = edit;
            break; 
             case "adicionar":
                request.setAttribute("idRUsuario", request.getParameter("id"));
                acceso = ar;
            break;         
            case "update" :
                int idRol = Integer.parseInt(request.getParameter("codigo"));
                usuario= request.getParameter("usuario");
                empresa= Integer.parseInt(request.getParameter("empresa")) ;
                roles.setIdRol(idRol);
                roles.setUsuario(usuario);
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
       processRequest(request, response);
       
      
     
       
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
