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
import org.dao.DaoCliente;
import org.models.ModelCliente;


public class ControllerPedido extends HttpServlet {
    String listar="Pedido/pedidoMesa.jsp";
    String add="Pedido/pedidoCliente.jsp";
    String create="Pedido/pedidoProducto.jsp";
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
            out.println("<title>Servlet ControllerPedido</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerPedido at " + request.getContextPath() + "</h1>");
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
                ModelCliente cliente = new ModelCliente();
        DaoCliente daoCliente = new DaoCliente();
     
        
        switch (action){
            case "read":
                acceso = listar;
            break;
            
            case "nuevo":
                int mesa=Integer.parseInt(request.getParameter("mesa"));
                int area=Integer.parseInt(request.getParameter("area"));
                request.setAttribute("gMesa", mesa);
                request.setAttribute("gArea", area);
                acceso=add;
            break;
                
            case "create" :                
                int idCliente = Integer.parseInt(request.getParameter("id"));
                mesa=Integer.parseInt(request.getParameter("mesa"));
                area=Integer.parseInt(request.getParameter("area"));
                request.setAttribute("gMesa", mesa);
                request.setAttribute("gArea", area);
                request.setAttribute("gCliente", idCliente);

                acceso = create;
            break;     
            case "cliente" :
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String nit = request.getParameter("nit");
                String telefono = request.getParameter("telefono");
                String direccion= request.getParameter("direccion");
                int idempresa = Integer.parseInt(request.getParameter("empresa"));
                mesa=Integer.parseInt(request.getParameter("mesa"));
                 area=Integer.parseInt(request.getParameter("area"));
                request.setAttribute("gMesa", mesa);
                request.setAttribute("gArea", area);

                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setNit(nit);
                cliente.setTelefono(telefono);
                cliente.setDireccion(direccion);
                cliente.setIdEmpresa(idempresa);

                daoCliente.insertar(cliente);
                acceso = add;
      
            break;
            case "delete":
               
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
