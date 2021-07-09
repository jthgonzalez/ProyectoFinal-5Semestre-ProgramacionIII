
<%@page import="org.models.ModelTmpPedido"%>
<%@page import="org.dao.DaoTmpPedido"%>
<%@page import="org.models.ModelProducto"%>
<%@page import="org.dao.DaoProducto"%>
<%@page import="org.dao.DaoEmpresa"%>
<%@page import="org.dao.DaoTipoProducto"%>
<%@page import="org.dao.DaoRoles"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.models.ModelTipoProducto"%>
<%@page import="java.util.List"%>
<%@page import="org.dao.DaoRoles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

      
    
       
           
            
            <table border="1" width="1" cellspacing="1" class="table table-bordered">
                <thead >
                        <tr >
                            <th class="text-center">IdProducto</th>
                            <th class="text-center">Descripcion</th>
                            <th class="text-center">Cantidad</th>
                            <th class="text-center">Precio</th>
                            <th class="text-center">Subtotal</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>                                        
                    <tbody>
                        
                        <%
                      
                        DaoTmpPedido daoPedido = new DaoTmpPedido();
                   double total=0;
                   double sumcantidad=0;
                          String usuario = request.getAttribute("pusuario").toString();
                          int idCliente =Integer.parseInt(request.getAttribute("idcliente").toString());
                         
                        List<ModelTmpPedido> lstPedido= daoPedido.listar(idCliente,usuario);
                        Iterator<ModelTmpPedido> iteratorPedido = lstPedido.iterator();
                        ModelTmpPedido pedido = null;
                        while (iteratorPedido.hasNext()){
                            pedido = iteratorPedido.next();
                            total+=pedido.getSubtotal();
                            sumcantidad+=pedido.getCantidad();
                        %> 
                        <tr>
                            <td class="text-center"><%= pedido.getIdProducto()%></td>
                            <td class="text-center"><%= pedido.getDescripcion()%></td>
                            <td class="text-center"><%= pedido.getCantidad() %></td>
                            <td class="text-center"><%= pedido.getPrecio()%></td>
                            <td class="text-center"><%= pedido.getSubtotal() %></td>
                
                               <td class='col-xs-1'><span class="pull-right">
                                   <%---    <a style="cursor: pointer;" title="Actualizar" onclick="update('<%= pedido.getIdProducto() %>')">
                                   <i class="glyphicon glyphicon-edit"></i></a> &nbsp;&nbsp;&nbsp;&nbsp; ---%>
                                   <a style="cursor: pointer;" title="Eliminar" onclick="eliminar('<%= pedido.getIdProducto()%>')">
                          <i class="glyphicon glyphicon-trash"></i></a>
                           </span></td>                       
                 
                        </tr>
                        <% } %>
                        <tr>
                            <td colspan="2" class="text-center">
                                <strong>TOTALES</strong>
                            </td>
                             <td class="text-center">
                                <strong><%=sumcantidad%></strong>
                            </td>
                              <td >

                            </td>
                              <td class="text-center">
                                <strong><%=total%></strong>
                            </td>
                             <td class="text-center">
                          
                            </td>
                        </tr>
                    </tbody>
                </table>

