
<%@page import="org.dao.DaoRoles"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.models.ModelRoles"%>
<%@page import="java.util.List"%>
<%@page import="org.dao.DaoRoles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>  
      
        <h1>Opciones por Rol</h1>
   
       
              
        
            
            <table border="1" width="1" cellspacing="1" class="table table-bordered">
                <thead >
                        <tr >
                            <th class="text-center">Id Rol</th>
                            <th class="text-center">Nombre</th>                           
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>                                        
                    <tbody>
                        <%
                      //  DaoRoles daoSucursal = new  DaoSucursal();
                        DaoRoles daoRoles = new DaoRoles();
                        List<ModelRoles> lstRoles= daoRoles.listar();
                        Iterator<ModelRoles> iteratorRoles = lstRoles.iterator();
                        ModelRoles roles = null;
                        while (iteratorRoles.hasNext()){
                            roles = iteratorRoles.next();                        
                        %> 
                        <tr>
                            <td class="text-center"><%= roles.getIdRol()%></td>
                            <td class="text-center"><%= roles.getNombre()%></td>
                       
                            <td class="text-center">                                
                                <a href="ControllerRolOpcion?accion=editar&id=<%=roles.getIdRol() %>" class="text-center">Opciones</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
    <jsp:include page="../Pages/Footer.jsp"></jsp:include>
