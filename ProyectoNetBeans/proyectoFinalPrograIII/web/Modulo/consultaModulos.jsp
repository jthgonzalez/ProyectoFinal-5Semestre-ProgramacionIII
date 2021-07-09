
<%@page import="org.dao.DaoModulos"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.models.ModelModulo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/Pages/Header.jsp"></jsp:include>  
      
        <h1>Modulos</h1>
   
       
                <div class="pull-left">
                    <a class="btn btn-success"  href="ControllerModulos?accion=nuevo"  > Agregar Nuevo Modulo</a>
                </div>
             
            
            <table border="1" width="1" cellspacing="1" class="table table-bordered">
                <thead >
                        <tr >
                            <th class="text-center">Id Modulo</th>
                            <th class="text-center">Modulo</th> 
                            <th class="text-center">Descripcion</th>
                            <th class="text-center">Ayuda</th>
                            <th class="text-center">Acciones</th>                              
                         
                        </tr>
                    </thead>                                        
                    <tbody>
                        <%
                 
                        DaoModulos daoModulos = new DaoModulos();
                        List<ModelModulo> lstModulos= daoModulos.listar();
                        Iterator<ModelModulo> iteratorModulos = lstModulos.iterator();
                        ModelModulo modulos = null;
                        while (iteratorModulos.hasNext()){
                            modulos = iteratorModulos.next();                        
                        %> 
                        <tr>
                            <td class="text-center"><%= modulos.getId_modulo()%></td>
                            <td class="text-center"><%= modulos.getModulo()%></td>
                            <td class="text-center"><%= modulos.getDescripcion()%></td>
                            <td class="text-center"><%= modulos.getAyuda()%></td>
                                                      
                            <td class="text-center">                                
                                <a href="ControllerModulos?accion=editar&id=<%=modulos.getId_modulo()%>">Editar</a>
                                <a href="ControllerModulos?accion=delete&id=<%=modulos.getId_modulo()%>">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
    <jsp:include page="../Pages/Footer.jsp"></jsp:include>
