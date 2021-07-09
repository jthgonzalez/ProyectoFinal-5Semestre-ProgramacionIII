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
import org.dao.DaoTipoProducto;
import org.models.ModelTipoProducto;


@WebServlet(name = "ControllerTipoProducto", urlPatterns = {"/ControllerTipoProducto"})
public class ControllerTipoProducto extends HttpServlet {
     String listar="TipoProducto/tipoProductoConsulta.jsp";
    String add="TipoProducto/tipoProductoIngreso.jsp";
    String edit="TipoProducto/tipoProductoModifica.jsp";
    String delete="";

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
            out.println("<title>Servlet ControllerTipoProducto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerTipoProducto at " + request.getContextPath() + "</h1>");
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
        
        ModelTipoProducto pr = new ModelTipoProducto();
        DaoTipoProducto daoPr = new DaoTipoProducto();
        
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
                int idempresa = Integer.parseInt(request.getParameter("empresa"));

                pr.setDescripcion(descripcion);
                pr.setEstado(estado);
                pr.setIdEmpresa(idempresa);
               
                daoPr.insertar(pr);
                acceso = listar;
            break;
            case "editar":
                //obtenemos el id de la fila que estamos seleccionando y se la pasamos al formulario de editar
                request.setAttribute("idTipo", request.getParameter("id"));
                //Redireccionamos a la pagina de edición
                acceso = edit;
            break;            
            case "update" :
                int idTipoProducto = Integer.parseInt(request.getParameter("codigo"));
                estado = Integer.parseInt(request.getParameter("estado"));
                descripcion = request.getParameter("descripcion");
                idempresa = Integer.parseInt(request.getParameter("empresa"));
                pr.setIdTipoProducto(idTipoProducto);
                pr.setEstado(estado);
                pr.setDescripcion(descripcion);
               
                pr.setIdEmpresa(idempresa);
                
                daoPr.modificar(pr);
                acceso = listar;                
            break;
            case "eliminar":
                 int idPr = Integer.parseInt(request.getParameter("id"));
                 pr.setIdTipoProducto(idPr);
                 daoPr.eliminar(pr);
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
