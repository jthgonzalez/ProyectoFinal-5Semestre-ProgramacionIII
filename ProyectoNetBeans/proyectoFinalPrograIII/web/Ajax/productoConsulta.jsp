

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
                            <th class="text-center">Id</th>
                            <th class="text-center">TipoProducto</th>
                            <th class="text-center">Descripcion</th>
                            <th class="text-center">Precio</th>
                            <th class="text-center">Existencia</th>
                            <th class="text-center">Cantidad</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>                                        
                    <tbody>
                        
                        <%
                            String val="";
                      //  DaoRoles daoSucursal = new  DaoSucursal();
                        DaoProducto daoProducto = new DaoProducto();
                        //este es para obtener el nombre del tipo del producto
                        DaoTipoProducto daoPr = new DaoTipoProducto();
                        // este para obtener el nombre de la empresa, jeje se me olvida 
                         DaoEmpresa daoEmpresa = new DaoEmpresa();
                         String q = request.getAttribute("q").toString();
                        int t = Integer.parseInt(request.getAttribute("t").toString());
                        List<ModelProducto> lstProducto= daoProducto.listartmp(q,t);
                        Iterator<ModelProducto> iteratorProducto = lstProducto.iterator();
                        ModelProducto prod = null;
                        while (iteratorProducto.hasNext()){
                            prod = iteratorProducto.next(); 
                            val="";
                            if(prod.getExistencia()==0) val="hidden";

                        %> 
                        <tr>
                            <td class="text-center"><%= prod.getIdproducto()%></td>
                            <td class="text-center"><%= daoPr.getNombre(prod.getIdtipoproducto())%></td>
                            <td class="text-center"><%= prod.getDescripcion()%></td>
                            <td class="text-center"><%= prod.getPrecio()%></td>
                            <td class="text-center">
                                <input class="hidden" id="prod<%= prod.getIdproducto()%>" value="<%=prod.getExistencia()%>" />
                                <%= prod.getExistencia()%></td>
                <td class='col-xs-1'>
			<div class="pull-right">
			<input type="text" class="form-control" style="text-align:right" id="cantidad_<%= prod.getIdproducto()%>"  value="1" >
			</div></td>
                                                     
                       <td ><span class="pull-right <%=val%>">
                               <a style="cursor: pointer;" onclick="agregar('<%= prod.getIdproducto()%>')">
                          <i class="glyphicon glyphicon-plus"></i></a>
                           </span></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>

