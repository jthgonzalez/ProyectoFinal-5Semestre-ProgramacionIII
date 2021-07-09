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
import javax.servlet.http.HttpSession;
import org.clases.funciones;
import org.dao.DaoUsuario;
import org.models.ModelUsuario;


public class LogIn extends HttpServlet {

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
            out.println("<title>Servlet LogIn</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LogIn at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
      //  processRequest(request, response);
                    String acceso="";        
        String action = request.getParameter("accion");
        HttpSession session = request.getSession(true);
        String existe="dashboard.jsp";
        String fail="Login/Login.jsp";


        String vusuario="";
        String vclave="";
        int idEmpresa=0;
        ModelUsuario usuario = new ModelUsuario();
        DaoUsuario daoUsuario = new DaoUsuario();
        funciones fn = new funciones();
        switch (action){
         
               case "login" :
                vusuario = request.getParameter("usuario");
                vclave = request.getParameter("password");

                usuario.setUsuario(vusuario);
                usuario.setClave(vclave);

                if(daoUsuario.validarusuario(usuario)){
                    session.setAttribute("g_Usuario", vusuario);
                    session.setAttribute("LogOut", null);
                    idEmpresa = daoUsuario.getEmpresa(vusuario);
                    session.setAttribute("g_Empresa", idEmpresa);
                acceso = existe;
                }else{
                    acceso=fail;
                }
            break;
          
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso); //invoca de modo directo un recurso web
        vista.forward(request, response);
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
