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
import org.dao.DaoSucursal;
import org.models.ModelSucursal;


public class ControllerSucursal extends HttpServlet {

           
   String listar="Sucursal/sucursalConsulta.jsp";
    String add="Sucursal/sucursalIngreso.jsp";
    String edit="Sucursal/sucursalModifica.jsp";
    
    
    
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
            out.println("<title>Servlet ControllerUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerUsuario at " + request.getContextPath() + "</h1>");
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

    String delete="";
        int vid=0;
        int id_empresa=0;
        String nombre="";
        String vdescripcion="";
        int vestado;
        String vdireccion="";
        String vtelefono="";
        ModelSucursal sucursal = new ModelSucursal();
        DaoSucursal daoSucursal = new DaoSucursal();
        
        switch (action){
            case "read":
                /*
              String busqueda = "";
                  if(request.getParameter("busqueda") != null && request.getParameter("busqueda").trim() != "")
                      busqueda= request.getParameter("busqueda").trim();
                      
              request.setAttribute("pbusqueda", busqueda);
              */
                acceso = listar;
            break;            
            case "nuevo":
                acceso=add;
                break;
            case "editar":
                //obtenemos el id de la fila que estamos seleccionando y se la pasamos al formulario de editar
                request.setAttribute("idSucursal", request.getParameter("id"));
                //Redireccionamos a la pagina de edición
                acceso = edit;
            break;
            case "update" :
                
                vid = Integer.parseInt(request.getParameter("codigo"));
                nombre = request.getParameter("nombre");
                vdescripcion = request.getParameter("descripcion");
                vdireccion = request.getParameter("direccion");
                vtelefono = request.getParameter("telefono");
                vestado =Integer.parseInt(request.getParameter("estado"));
                id_empresa =Integer.parseInt(request.getParameter("empresa"));
   
                
                sucursal.setIdSucursal(vid);
                sucursal.setIdEmpresa(id_empresa);
                sucursal.setNombre(nombre);
                sucursal.setDireccion(vdireccion);
                sucursal.setDescripcion(vdescripcion);
                sucursal.setTelefono(vtelefono);
                sucursal.setEstado(vestado);
   
                
                daoSucursal.modificar(sucursal);
                acceso = listar;                
            break;
               case "create" :
                
              
                nombre = request.getParameter("nombre");
                vdescripcion = request.getParameter("descripcion");
                vdireccion = request.getParameter("direccion");
                vtelefono = request.getParameter("telefono");
                vestado =Integer.parseInt(request.getParameter("estado"));
                id_empresa =Integer.parseInt(request.getParameter("empresa"));
   
                
                
                sucursal.setIdEmpresa(id_empresa);
                sucursal.setNombre(nombre);
                sucursal.setDireccion(vdireccion);
                sucursal.setDescripcion(vdescripcion);
                sucursal.setTelefono(vtelefono);
                sucursal.setEstado(vestado);
   
                
                daoSucursal.insertar(sucursal);
                acceso = listar;                
            break;
                 case "delete":
                 int idSucursal = Integer.parseInt(request.getParameter("id"));
                 sucursal.setIdSucursal(idSucursal);
                 daoSucursal.eliminar(sucursal);
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
