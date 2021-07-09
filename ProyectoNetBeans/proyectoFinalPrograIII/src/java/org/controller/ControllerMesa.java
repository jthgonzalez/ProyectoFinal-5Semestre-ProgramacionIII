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
import org.dao.DaoMesa;
import org.models.ModelMesa;


@WebServlet(name = "ControllerMesa", urlPatterns = {"/ControllerMesa"})
public class ControllerMesa extends HttpServlet {
 String listar = "Mesa/mesaConsulta.jsp";
    String add ="Mesa/mesaIngreso.jsp";
    String edit="Mesa/mesaModifica.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet ControllerMesa</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerMesa at " + request.getContextPath() + "</h1>");
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
        
        ModelMesa mesa = new ModelMesa();
        DaoMesa daoMesa = new DaoMesa();
        
        switch (action){
            case "read":
                acceso = listar;
            break;
            
            case "nuevo":
                acceso=add;
            break;
                
            case "create" :                
                String descripcion = request.getParameter("descripcion");
                int asiento = Integer.parseInt(request.getParameter("asiento"));
                String estado = request.getParameter("estado");
                String tipo = request.getParameter("tipo");
                int idArea = Integer.parseInt(request.getParameter("idarea"));

                mesa.setDescripcion(descripcion);
                mesa.setAsiento(asiento);
                mesa.setEstado(estado);
                mesa.setTipo(tipo);    
                mesa.setIdArea(idArea);

                daoMesa.insertar(mesa);
                acceso = listar;
            break;
            case "editar":
                //obtenemos el id de la fila que estamos seleccionando y se la pasamos al formulario de editar
                request.setAttribute("idMesa", request.getParameter("id"));
                //Redireccionamos a la pagina de edición
                acceso = edit;
            break;            
            case "update" :
                int idMesa = Integer.parseInt(request.getParameter("codigo"));
                descripcion = request.getParameter("descripcion");
                asiento = Integer.parseInt(request.getParameter("asiento"));
                estado = request.getParameter("estado");
                tipo = request.getParameter("tipo");               
                idArea = Integer.parseInt(request.getParameter("idarea"));
              
                mesa.setIdMesa(idMesa);
                mesa.setIdArea(idArea);
                mesa.setDescripcion(descripcion);
                mesa.setAsiento(asiento);
                mesa.setEstado(estado);
                mesa.setTipo(tipo);
               
                
                daoMesa.modificar(mesa);
                acceso = listar;                
            break;
            case "delete":
                 int idMes = Integer.parseInt(request.getParameter("id"));
                 mesa.setIdMesa(idMes);
                 daoMesa.eliminar(mesa);
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
