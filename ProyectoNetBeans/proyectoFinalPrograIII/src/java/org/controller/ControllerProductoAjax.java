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
import org.dao.DaoProducto;
import org.dao.DaoTmpPedido;
import org.models.ModelProducto;
import org.models.ModelTmpPedido;


@WebServlet(name = "ControllerProductoAjax", urlPatterns = {"/ControllerProductoAjax"})
public class ControllerProductoAjax extends HttpServlet {
    String listar="Ajax/productoConsulta.jsp";
    String add="Ajax/pedidoConsulta.jsp";
    String fin="Ajax/pedidoFinaliza.jsp";
    String edit="Producto/productoModifica.jsp";
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
            out.println("<title>Servlet ControllerProducto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerProducto at " + request.getContextPath() + "</h1>");
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
        
        ModelProducto pr = new ModelProducto();
        DaoProducto daoPr = new DaoProducto();
         ModelTmpPedido pedido = new ModelTmpPedido();
        DaoTmpPedido daoPedido = new DaoTmpPedido();
        
        switch (action){
            case "read":
                String q =  request.getParameter("q").toString();
                int t = Integer.parseInt(request.getParameter("t").toString());
                request.setAttribute("q", q);
                request.setAttribute("t", t);
                acceso = listar;
            break;
            
            case "nuevo":
                acceso=add;
            break;
                    case "add":
                String idprod = request.getParameter("id").toString();
                int cantidad = Integer.parseInt(request.getParameter("cantidad").toString());
                int cliente = Integer.parseInt(request.getParameter("cliente").toString());
                String usuario = request.getParameter("usuario").toString();
            request.setAttribute("idcliente", cliente);
            request.setAttribute("pusuario", usuario);
                pedido.setIdProducto(idprod);
                pedido.setCantidad(cantidad);
                pedido.setIdCliente(cliente);
                pedido.setUsuario(usuario);
                daoPedido.insertar(pedido);
                acceso = add;
            break;
            case "create" :
                int Idproducto= Integer.parseInt(request.getParameter("tipoproducto"));
                String descripcion = request.getParameter("descripcion");
                int precio= Integer.parseInt(request.getParameter("precio"));
                int existencia= Integer.parseInt(request.getParameter("existencia"));
                int estado = Integer.parseInt(request.getParameter("estado"));
                int idempresa = Integer.parseInt(request.getParameter("empresa"));
                pr.setIdtipoproducto(Idproducto);
                pr.setDescripcion(descripcion);
                pr.setPrecio(precio);
                pr.setExistencia(existencia);
                pr.setEstado(estado);
                pr.setIdempresa(idempresa);
               
                daoPr.insertar(pr);
                acceso = listar;
            break;
            case "editar":
                //obtenemos el id de la fila que estamos seleccionando y se la pasamos al formulario de editar
                request.setAttribute("idProd", request.getParameter("id"));
                //Redireccionamos a la pagina de edición
                acceso = edit;
            break;            
            case "update" :
                int idProducto = Integer.parseInt(request.getParameter("codigo"));
                int  idTipoProducto= Integer.parseInt(request.getParameter("idtipoproducto"));               
                descripcion = request.getParameter("descripcion");
                precio= Integer.parseInt(request.getParameter("precio"));
                existencia= Integer.parseInt(request.getParameter("existencia"));
                estado = Integer.parseInt(request.getParameter("estado"));
                idempresa = Integer.parseInt(request.getParameter("empresa"));
                
                 System.out.println("" + idTipoProducto);
                 System.out.println("" + idProducto);
                 
                pr.setIdproducto(idProducto);
                pr.setIdtipoproducto(idTipoProducto);
                pr.setDescripcion(descripcion);
                pr.setPrecio(precio);
                pr.setExistencia(existencia);
                pr.setEstado(estado);
                pr.setIdempresa(idempresa);              
                
                daoPr.modificar(pr);
                acceso = listar;                
            break;
            case "eliminar":
                  idprod = request.getParameter("id").toString();
                 cliente = Integer.parseInt(request.getParameter("cliente").toString());
                 usuario = request.getParameter("usuario").toString();
            request.setAttribute("idcliente", cliente);
            request.setAttribute("pusuario", usuario);
                pedido.setIdProducto(idprod);
                pedido.setIdCliente(cliente);
                pedido.setUsuario(usuario);
                daoPedido.eliminar(pedido);
                acceso = add;
                break;
            case "pedido":
                  int idcliente = Integer.parseInt(request.getParameter("id").toString());
                 int mesa = Integer.parseInt(request.getParameter("mesa").toString());
                 int area = Integer.parseInt(request.getParameter("area").toString());
                 usuario = request.getParameter("usuario").toString();
      
                pedido.setIdCliente(idcliente);
                pedido.setUsuario(usuario);
                pedido.setArea(area);
                pedido.setMesa(mesa);
             int nopedido=daoPedido.generaPedido(pedido);
             request.setAttribute("nopedido", nopedido);
                acceso = fin;
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
          String acceso="";        
        String action = request.getParameter("accion");        
        
        ModelTmpPedido pedido = new ModelTmpPedido();
        DaoTmpPedido daoPedido = new DaoTmpPedido();
        
        switch (action){
            case "add":
                String idprod = request.getParameter("id").toString();
                int cantidad = Integer.parseInt(request.getParameter("cantidad").toString());
                int cliente = Integer.parseInt(request.getParameter("cliente").toString());
                String usuario = request.getParameter("usuario").toString();
            
                pedido.setIdProducto(idprod);
                pedido.setCantidad(cantidad);
                pedido.setIdCliente(cliente);
                pedido.setUsuario(usuario);
                daoPedido.insertar(pedido);
                acceso = add;
            break;
           
              default:
                acceso = listar;
                break;
        }
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
