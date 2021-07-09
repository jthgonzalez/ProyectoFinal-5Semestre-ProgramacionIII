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
import javax.servlet.http.HttpSession;
import org.clases.funciones;
import org.dao.DaoUsuario;
import org.models.ModelUsuario;

@WebServlet(name = "ControllerUsuario", urlPatterns = {"/ControllerUsuario"})
public class ControllerUsuario extends HttpServlet {

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
     //   HttpSession session = request.getSession(); 
   String listar="Usuario/consultaUsuario.jsp";
    String add="Usuario/usuarioIngreso.jsp";
    String edit="Usuario/usuarioModifica.jsp";

    
   // String delete="";
        int vid=0;
        String nombre="";
        String vusuario="";
        int vestado,vrol;
        String vclave="";
        ModelUsuario usuario = new ModelUsuario();
        DaoUsuario daoUsuario = new DaoUsuario();
        
        switch (action){
            case "read":
                
              String busqueda = "";
                  if(request.getParameter("busqueda") != null && request.getParameter("busqueda").trim() != "")
                      busqueda= request.getParameter("busqueda").trim();
                      
              request.setAttribute("pbusqueda", busqueda);
                acceso = listar;
            break;            
            case "nuevo":
                acceso=add;
                break;
            case "editar":
                //obtenemos el id de la fila que estamos seleccionando y se la pasamos al formulario de editar
                request.setAttribute("idUsuario", request.getParameter("id"));
                //Redireccionamos a la pagina de edición
                acceso = edit;
            break;
            case "update" :
                
                vid = Integer.parseInt(request.getParameter("codigo"));
                nombre = request.getParameter("nombre");
                vusuario = request.getParameter("usuario");
                vclave = request.getParameter("clave");
                vestado =Integer.parseInt(request.getParameter("estado"));
                vrol =Integer.parseInt(request.getParameter("rol"));
   
                
                usuario.setIdUsuario(vid);
                usuario.setNombre(nombre);
                usuario.setUsuario(vusuario);
                usuario.setClave(vclave);
                usuario.setEstado(vestado);
                usuario.setRol(vrol);
   
                
               daoUsuario.modificar(usuario);
                acceso = listar;                
            break;
               case "create" :
                
               
                nombre = request.getParameter("nombre");
                vusuario = request.getParameter("usuario");
                vclave = request.getParameter("clave");
                vestado =Integer.parseInt(request.getParameter("estado"));
                vrol =Integer.parseInt(request.getParameter("rol"));
                
                usuario.setNombre(nombre);
                usuario.setUsuario(vusuario);
                usuario.setClave(vclave);
                usuario.setEstado(vestado);
                usuario.setRol(vrol);
   
                
                daoUsuario.insertar(usuario);
                acceso = listar;                
            break;
                 case "delete":
                 int idUsu = Integer.parseInt(request.getParameter("id"));
                 usuario.setIdUsuario(idUsu);
                 daoUsuario.eliminar(usuario);
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
       // processRequest(request, response);
 
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
