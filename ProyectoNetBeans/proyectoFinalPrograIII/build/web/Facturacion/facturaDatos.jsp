

<%@page import="org.models.ModelFactura"%>
<%@page import="org.dao.DaoFactura"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

      
    
       
           
            
            <table border="1" width="1" cellspacing="1" class="table table-bordered">
                <thead >
                        <tr >
                            <th class="text-center">No. Fac</th>
                            <th class="text-center">Serie</th>
                            <th class="text-center">Fecha</th>
                            <th class="text-center">Total</th>
                            <th class="text-center">IdCliente</th>
                            <th class="text-center">Cliente</th>
                            <th class="text-center">Area</th>
                            <th class="text-center">Mesa</th>
                            <th class="text-center">Accion</th>
                        </tr>
                    </thead>                                        
                    <tbody>
                        
                        <%
                      
                        DaoFactura daoFactura = new DaoFactura();

                       
                        List<ModelFactura> lstFactura= daoFactura.getListaFac();
                        Iterator<ModelFactura> iteratorFactura = lstFactura.iterator();
                        ModelFactura fac = null;
                        while (iteratorFactura.hasNext()){
                            fac = iteratorFactura.next();
                           
                        %> 
                        <tr>
                            <td class="text-center"><%= fac.getId() %></td>
                            <td class="text-center"><%= fac.getSerie() %></td>
                            <td class="text-center"><%= fac.getFecha() %></td>
                            <td class="text-center"><%= fac.getTotal() %></td>
                            <td class="text-center"><%= fac.getIdCliente() %></td>
                            <td class="text-center"><%= fac.getCliente() %></td>
                             <td class="text-center"><%= fac.getArea() %></td>
                              <td class="text-center"><%= fac.getMesa() %></td>
                
                               <td class='col-xs-1 text-center'><span class="pull-right">
                                       <a target="_blank" href="ControllerFactura?accion=print&fac=<%= fac.getId() %>&serie=<%= fac.getSerie() %>&cliente=<%= fac.getIdCliente() %>&pedido=<%=fac.getPedido()%>" style="cursor: pointer;" title="Imprimir" >
                                   <i class="glyphicon glyphicon-print"></i></a> 
                                   
                           </span></td>                       
                 
                        </tr>
                        <%}%>
                   
                    </tbody>
                </table>

