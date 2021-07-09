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
import org.dao.DaoOpcionMenu;
import org.models.ModelOpcionMenu;


public class ControllerOpcionMenu extends HttpServlet {

        String listar = "OpcionMenu/consultaOpcionMenu.jsp";
    String add ="OpcionMenu/ingresaOpcionMenu.jsp";
    String edit="OpcionMenu/modificaOpcionMenu.jsp";

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
            out.println("<title>Servlet ControllerOpcionMenu</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerOpcionMenu at " + request.getContextPath() + "</h1>");
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
        
        ModelOpcionMenu opcionmenu = new ModelOpcionMenu();
        DaoOpcionMenu daoOpcionMenu = new DaoOpcionMenu();
        
        switch (action){
            case "read":
                acceso = listar;
            break;
            
            case "nuevo":
                acceso=add;
            break;
                
            case "create" :                
               //int id_opcionmenu= Integer.parseInt(request.getParameter("id_opcionmenu"));
                String opcionm = request.getParameter("opcion");
                String modulo = request.getParameter("modulo");
                String descripcion = request.getParameter("descripcion");
                String link= request.getParameter("link");
                String visible= request.getParameter("visible");
               
                
               // opcionmenu.setId_opcionmenu(id_opcionmenu);
                opcionmenu.setOpcion(opcionm);
                opcionmenu.setDescripcion(descripcion);
                opcionmenu.setModulo(modulo);
                opcionmenu.setLink(link);
                opcionmenu.setVisible(visible);

                daoOpcionMenu.insertar(opcionmenu);
                acceso = listar;
            break;
            case "editar":
                request.setAttribute("idOpcionMenu", request.getParameter("id"));
                acceso = edit;
            break;            
            case "update" :
                 opcionm = request.getParameter("opcion");
                 modulo = request.getParameter("modulo");
                 descripcion = request.getParameter("descripcion");
                 link= request.getParameter("link");
                 visible= request.getParameter("visible");
               
                

                
               // opcionmenu.setId_opcionmenu(id_opcionmenu);
                opcionmenu.setOpcion(opcionm);
                opcionmenu.setDescripcion(descripcion);
                opcionmenu.setModulo(modulo);
                opcionmenu.setLink(link);
                opcionmenu.setVisible(visible);

                daoOpcionMenu.modificar(opcionmenu);
                acceso = listar;               
            break;
            case "eliminar":
        
                opcionm = request.getParameter("id");
              
                opcionmenu.setOpcion(opcionm);
                daoOpcionMenu.eliminar(opcionmenu);
                acceso = listar;
            break; 
            case "buscar":
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
