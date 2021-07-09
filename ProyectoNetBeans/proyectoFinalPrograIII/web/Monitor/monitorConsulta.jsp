

<%@page import="org.dao.DaoEmpresa"%>
<%@page import="org.dao.DaoTipoProducto"%>
<%@page import="org.dao.DaoRoles"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.models.ModelTipoProducto"%>
<%@page import="java.util.List"%>
<%@page import="org.dao.DaoRoles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/Pages/Header.jsp"></jsp:include>  
      
        <h1>TIPO MONITOR</h1>
   
       
         
            
            <table border="1" width="1" cellspacing="1" class="table table-bordered">
                <thead >
                        <tr >
                            <th class="text-center">TipoProducto</th>
                            <th class="text-center">Descripcion</th> 
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>                                        
                    <tbody>
                        <%
                      //  DaoRoles daoSucursal = new  DaoSucursal();
                        DaoTipoProducto daoPr = new DaoTipoProducto();
                        List<ModelTipoProducto> lstPr= daoPr.listar();
                        DaoEmpresa daoEmpresa = new DaoEmpresa();
                        Iterator<ModelTipoProducto> iteratorPr = lstPr.iterator();
                        ModelTipoProducto pr = null;
                        while (iteratorPr.hasNext()){
                            pr = iteratorPr.next();                        
                        %> 
                        <tr>
                            <td class="text-center"><%= pr.getIdTipoProducto()%></td>
                            <td class="text-center"><%= pr.getDescripcion()%></td>
                                                     
                            <td class="text-center">                                
                                <a target="_blank" href="ControllerMonitor?accion=monitor&id=<%=pr.getIdTipoProducto() %>" class="text-center">Monitor</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
    <jsp:include page="../Pages/Footer.jsp"></jsp:include>
