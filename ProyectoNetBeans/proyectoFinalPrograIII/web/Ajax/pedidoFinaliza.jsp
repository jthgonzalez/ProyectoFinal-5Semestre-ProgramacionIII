

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
<jsp:include page="../Pages/Header.jsp"></jsp:include>
      <%
      
int nopedido = Integer.parseInt(request.getAttribute("nopedido").toString());
      
      %>
    <center>
         <h1>PEDIDO FINALIZADO CORRECTAMENTE</h1>
        <h1>No. <%=nopedido%></h1>
        <br>
         <a class="btn btn-info" href="ControllerPedido?accion=read"  > Regresar</a>
    </center>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>