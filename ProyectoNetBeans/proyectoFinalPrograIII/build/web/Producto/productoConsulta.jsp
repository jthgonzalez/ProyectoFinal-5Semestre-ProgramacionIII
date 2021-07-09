

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
<jsp:include page="/Pages/Header.jsp"></jsp:include>  
      
        <h1>CONSULTA PRODUCTO</h1>
   
       
                <div class="pull-left">
                    <a class="btn btn-success"  href="ControllerProducto?accion=nuevo"  > Agregar Nuevo Producto</a>
                </div>
             
            
            <table border="1" width="1" cellspacing="1" class="table table-bordered">
                <thead >
                        <tr >
                            <th class="text-center">Id Producto</th>
                            <th class="text-center">Id Tipo Producto</th>
                            <th class="text-center">Descripcion</th>
                            <th class="text-center">Precio</th>
                            <th class="text-center">Existencia</th>
                            <th class="text-center">Estado</th>  
                             <th class="text-center hide">Empresa</th>   
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>                                        
                    <tbody>
                        
                        <%
                      //  DaoRoles daoSucursal = new  DaoSucursal();
                        DaoProducto daoProducto = new DaoProducto();
                        //este es para obtener el nombre del tipo del producto
                        DaoTipoProducto daoPr = new DaoTipoProducto();
                        // este para obtener el nombre de la empresa, jeje se me olvida 
                         DaoEmpresa daoEmpresa = new DaoEmpresa();
                         
                        List<ModelProducto> lstProducto= daoProducto.listar();
                        Iterator<ModelProducto> iteratorProducto = lstProducto.iterator();
                        ModelProducto prod = null;
                        while (iteratorProducto.hasNext()){
                            prod = iteratorProducto.next();                        
                        %> 
                        <tr>
                            <td class="text-center"><%= prod.getIdproducto()%></td>
                            <td class="text-center"><%= daoPr.getNombre(prod.getIdtipoproducto())%></td>
                            <td class="text-center"><%= prod.getDescripcion()%></td>
                            <td class="text-center"><%= prod.getPrecio()%></td>
                            <td class="text-center"><%= prod.getExistencia()%></td>
                             <td class="text-center"><%= prod.getEstado()%></td>
                           <td class="text-center hide"><%= daoEmpresa.getNombre(prod.getIdempresa())%></td> 
                                                     
                            <td class="text-center">                                
                                <a href="ControllerProducto?accion=editar&id=<%=prod.getIdproducto() %>" class="text-center">Editar</a>
                                <a href="ControllerProducto?accion=eliminar&id=<%=prod.getIdproducto() %>" class="text-center">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
    <jsp:include page="../Pages/Footer.jsp"></jsp:include>
