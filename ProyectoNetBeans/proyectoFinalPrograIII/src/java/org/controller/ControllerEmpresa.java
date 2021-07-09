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
import org.dao.DaoEmpresa;
import org.models.ModelEmpresa;


public class ControllerEmpresa extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerCliente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerCliente at " + request.getContextPath() + "</h1>");
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
    String listar="Empresa/empresaConsulta.jsp";
    String add="Empresa/empresaIngreso.jsp";
    String edit="Empresa/empresaModifica.jsp";
    String delete="";
        ModelEmpresa empresa = new ModelEmpresa();
        DaoEmpresa daoEmpresa = new DaoEmpresa();
        
        switch (action){
            case "read":
                acceso = listar;
            break;
            
            case "nuevo":
                acceso=add;
            break;
                
            case "create" :                
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                int estado = Integer.parseInt(request.getParameter("estado"));
                String telefono = request.getParameter("telefono");
                String direccion= request.getParameter("direccion");

                empresa.setNombre(nombre);
               empresa.setDescripcion(descripcion);
                empresa.setEstado(estado);
                empresa.setTelefono(telefono);
                empresa.setDireccion(direccion);

                daoEmpresa.insertar(empresa);
                acceso = listar;
            break;
            case "editar":
                //obtenemos el id de la fila que estamos seleccionando y se la pasamos al formulario de editar
                request.setAttribute("idEmpresa", request.getParameter("id"));
                //Redireccionamos a la pagina de edición
                acceso = edit;
            break;            
            case "update" :
                int idEmpresa = Integer.parseInt(request.getParameter("codigo"));
                nombre = request.getParameter("nombre");
                descripcion = request.getParameter("descripcion");
                estado = Integer.parseInt(request.getParameter("estado"));
                telefono = request.getParameter("telefono");
                direccion= request.getParameter("direccion");
                empresa.setIdEmpresa(idEmpresa);
                empresa.setNombre(nombre);
                empresa.setDescripcion(descripcion);
                empresa.setEstado(estado);
                empresa.setTelefono(telefono);
                empresa.setDireccion(direccion);
                
                daoEmpresa.modificar(empresa);
                acceso = listar;                
            break;
            case "delete":
                 idEmpresa = Integer.parseInt(request.getParameter("id"));
                 empresa.setIdEmpresa(idEmpresa);
                 daoEmpresa.eliminar(empresa);
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
