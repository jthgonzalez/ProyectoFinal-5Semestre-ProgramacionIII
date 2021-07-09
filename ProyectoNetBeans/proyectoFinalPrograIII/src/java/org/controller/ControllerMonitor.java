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
import org.dao.DaoMonitor;
import org.models.ModelMonitor;


public class ControllerMonitor extends HttpServlet {
  String listar="Monitor/monitorConsulta.jsp";
    String monitor="Monitor/monitorPedidos.jsp";
    String datos="Monitor/monitorDatos.jsp";
    String edit="Area/areaModifica.jsp";
    String delete="";
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
            out.println("<title>Servlet ControllerMonitor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerMonitor at " + request.getContextPath() + "</h1>");
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
        
            String acceso="";        
        String action = request.getParameter("accion");   
        
        DaoMonitor daoMonitor = new DaoMonitor();
        ModelMonitor vmonitor= new ModelMonitor();
        
         switch (action){
            case "read":
                acceso = listar;
            break;
            
            case "monitor":
                  int tipoprod = Integer.parseInt(request.getParameter("id").toString());
                  request.setAttribute("tipo", tipoprod);
                acceso=monitor;
            break;
            case "pedido":
                  tipoprod = Integer.parseInt(request.getParameter("t").toString());
                  request.setAttribute("tipo", tipoprod);
                acceso=datos;
            break;
                 case "update":
                     tipoprod = Integer.parseInt(request.getParameter("t").toString());
                  request.setAttribute("tipo", tipoprod);
                 int idpedido = Integer.parseInt(request.getParameter("idpedido").toString());
                 int iddetalle = Integer.parseInt(request.getParameter("iddetelle").toString());
                 String usuario = request.getParameter("usuario").toString();
                  
                 vmonitor.setIdpedido(idpedido);
                 vmonitor.setIddetelle(iddetalle);
                 vmonitor.setUsuario(usuario);
                 daoMonitor.modificar(vmonitor);
                acceso=datos;
            break;
           
                 default:
                 acceso = datos;
                 break;
         }
              RequestDispatcher vista = request.getRequestDispatcher(acceso); //invoca de modo directo un recurso web
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
