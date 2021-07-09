<%-- 
    Document   : consultaRol
    Created on : 05-may-2019, 12:01:23
    Author     : LENOVO
--%>

<%@page import="org.dao.DaoEmpresa"%>
<%@page import="org.dao.DaoRolUsuario"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.models.ModelRolUsuario"%>
<%@page import="java.util.List"%>
<%@page import="org.dao.DaoRolUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/Pages/Header.jsp"></jsp:include>  
      
        <h1>ROL USUARIO</h1>
   
       
                <div class="pull-left">
                    <a class="btn btn-success"  href="ControllerRolUsuario?accion=nuevo"  > Agregar Nuevo Rol</a>
                </div>
             
            
            <table border="1" width="1" cellspacing="1" class="table table-bordered">
                <thead >
                        <tr >
                            <th class="text-center">Id Rol</th>
                            <th class="text-center">Usuario</th>
                            <th class="text-center">Empresa</th>                            
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>                                        
                    <tbody>
                        <%
                      //  DaoRoles daoSucursal = new  DaoSucursal();
                        DaoRolUsuario daoRoles = new DaoRolUsuario();
                        List<ModelRolUsuario> lstRoles= daoRoles.listar();
                        DaoEmpresa daoEmpresa = new DaoEmpresa();
                        Iterator<ModelRolUsuario> iteratorRoles = lstRoles.iterator();
                        ModelRolUsuario roles = null;
                        while (iteratorRoles.hasNext()){
                            roles = iteratorRoles.next();                        
                        %> 
                        <tr>
                            <td class="text-center"><%= roles.getIdRol()%></td>
                            <td class="text-center"><%= roles.getUsuario()%></td>
                           <td class="text-center"><%= daoEmpresa.getNombre(roles.getIdEmpresa())%></td> 
                                                     
                            <td class="text-center">                                
                                <a href="ControllerRolUsuario?accion=adicionar&id=<%=roles.getIdRol() %>" class="text-center">Agregar rol...</a>
                                <br>
                                 <a href="ControllerRolUsuario?accion=editar&id=<%=roles.getIdRol() %>" class="text-center">Editar</a>
                         
                                 <a href="ControllerRolUsuario?accion=eliminar&id=<%=roles.getIdRol() %>" class="text-center">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
    <jsp:include page="../Pages/Footer.jsp"></jsp:include>
