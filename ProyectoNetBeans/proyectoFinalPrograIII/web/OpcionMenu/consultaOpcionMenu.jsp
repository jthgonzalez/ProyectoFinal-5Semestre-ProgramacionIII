
<%@page import="org.dao.DaoModulos"%>
<%@page import="org.dao.DaoOpcionMenu"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.models.ModelOpcionMenu"%>
<%@page import="java.util.List"%>
<%@page import="org.dao.DaoOpcionMenu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/Pages/Header.jsp"></jsp:include>  
      
        <h1>Opciones Menu</h1>
   
       
                <div class="pull-left">
                    <a class="btn btn-success"  href="ControllerOpcionMenu?accion=nuevo"  > Agregar Opcion</a>
                </div>
             
            
            <table border="1" width="1" cellspacing="1" class="table table-bordered">
                <thead >
                        <tr >
                            <th class="text-center">Opcion</th>
                            <th class="text-center">Descripcion</th>
                            <th class="text-center">Modulo</th>
                            <th class="text-center">Visible</th> 
                            <th class="text-center">Link</th>                            
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>                                        
                    <tbody>
                        <%
                      //  DaoOpcionMenu daoSucursal = new  DaoSucursal();
                        DaoOpcionMenu daoOpcionMenu = new DaoOpcionMenu();
                        List<ModelOpcionMenu> lstOpcionMenu= daoOpcionMenu.listar();
                        DaoModulos daoModulo = new DaoModulos();
                        Iterator<ModelOpcionMenu> iteratorOpcionMenu = lstOpcionMenu.iterator();
                        ModelOpcionMenu opcionmenu = null;
                        while (iteratorOpcionMenu.hasNext()){
                            opcionmenu = iteratorOpcionMenu.next();                        
                        %> 
                        <tr>
                            <td class="text-center"><%= opcionmenu.getOpcion()%></td>
                            <td class="text-center"><%= opcionmenu.getDescripcion()%></td>
                           <td class="text-center"><%= daoModulo.ModuloId(opcionmenu.getModulo())%></td> 
                           <td class="text-center"><%= opcionmenu.getVisible()%></td>
                             <td class="text-center"><%= opcionmenu.getLink()%></td>                        
                            <td class="text-center">                                
                                <a href="ControllerOpcionMenu?accion=editar&id=<%=opcionmenu.getOpcion() %>" class="text-center">Editar</a>
                                <a href="ControllerOpcionMenu?accion=eliminar&id=<%=opcionmenu.getOpcion() %>" class="text-center">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
    <jsp:include page="../Pages/Footer.jsp"></jsp:include>
