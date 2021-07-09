



<%@page import="org.models.ModelMonitor"%>
<%@page import="org.dao.DaoMonitor"%>
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
                            <th class="text-center">Correl</th>
                            <th class="text-center">NoPedido</th>
                            <th class="text-center">Area</th>
                            <th class="text-center">Mesa</th>
                            <th class="text-center">Producto</th>
                            <th class="text-center">Cantidad</th>
                            <th class="text-center">Accion</th>
                        </tr>
                    </thead>                                        
                    <tbody>
                        
                        <%
                      
                        DaoMonitor daoMonitor = new DaoMonitor();

                          int tipoprod =Integer.parseInt(request.getAttribute("tipo").toString());
                         
                        List<ModelMonitor> lstMonitor= daoMonitor.listar(tipoprod);
                        Iterator<ModelMonitor> iteratorMonitor = lstMonitor.iterator();
                        ModelMonitor monitor = null;
                        while (iteratorMonitor.hasNext()){
                            monitor = iteratorMonitor.next();
                           
                        %> 
                        <tr>
                            <td class="text-center"><%= monitor.getIddetelle() %></td>
                            <td class="text-center"><%= monitor.getIdpedido() %></td>
                            <td class="text-center"><%= monitor.getIdarea() %></td>
                            <td class="text-center"><%= monitor.getIdmesa() %></td>
                            <td class="text-center"><%= monitor.getDescripcion() %></td>
                            <td class="text-center"><%= monitor.getCantidad() %></td>
                
                               <td class='col-xs-1 text-center'><span class="pull-right">
                                    <a style="cursor: pointer;" title="Actualizar" onclick="update('<%= monitor.getIdpedido() %>','<%= monitor.getIddetelle() %>','<%=session.getAttribute("g_Usuario") %>')">
                                   <i class="glyphicon glyphicon-check"></i></a> 
                                   
                           </span></td>                       
                 
                        </tr>
                        <%}%>
                   
                    </tbody>
                </table>

