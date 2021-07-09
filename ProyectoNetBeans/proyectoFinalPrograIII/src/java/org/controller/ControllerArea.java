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
import org.dao.DaoArea;
import org.models.ModelArea;

public class ControllerArea extends HttpServlet {
    
    String listar="Area/areaConsulta.jsp";
    String add="Area/areaIngreso.jsp";
    String edit="Area/areaModifica.jsp";
    String delete="";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerArea</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerArea at " + request.getContextPath() + "</h1>");
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
        
        ModelArea area = new ModelArea();
        DaoArea daoArea = new DaoArea();
        
        switch (action){
            case "read":
                acceso = listar;
            break;
            
            case "nuevo":
                acceso=add;
            break;
                
            case "create" :                
                String descripcion = request.getParameter("descripcion");
                int estado = Integer.parseInt(request.getParameter("estado"));
                String tipo = request.getParameter("tipo");
              

                area.setDescripcion(descripcion);
                area.setEstado(estado);
                area.setTipo(tipo);
               

                daoArea.insertar(area);
                acceso = listar;
            break;
            case "editar":
                //obtenemos el id de la fila que estamos seleccionando y se la pasamos al formulario de editar
                request.setAttribute("idArea", request.getParameter("id"));
                //Redireccionamos a la pagina de edición
                acceso = edit;
            break;            
            case "update" :
                int idArea = Integer.parseInt(request.getParameter("codigo"));
                descripcion = request.getParameter("descripcion");
                estado = Integer.parseInt(request.getParameter("estado"));
                tipo = request.getParameter("tipo");
               
                
                area.setDescripcion(descripcion);
                area.setEstado(estado);
                area.setTipo(tipo);
                area.setIdArea(idArea);
                
                daoArea.modificar(area);
                acceso = listar;                
            break;
            case "delete":
                 int idAre = Integer.parseInt(request.getParameter("id"));
                 area.setIdArea(idAre);
                 daoArea.eliminar(area);
                 acceso=listar;
                break;
                 default:
                 acceso = listar;
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
