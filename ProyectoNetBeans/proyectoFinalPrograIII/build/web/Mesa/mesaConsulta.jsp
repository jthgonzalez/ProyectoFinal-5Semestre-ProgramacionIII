

<%@page import="org.models.ModelMesa"%>
<%@page import="org.dao.DaoMesa"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelCliente"%>
<%@page import="org.dao.DaoCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>         
            <h1>MESA</h1>
            <a class="btn btn-success" href="ControllerMesa?accion=nuevo"  > Agregar Nuevo</a>
                <table border="1" width="1" cellspacing="1" class="table table-bordered">
                    <thead>
                        <tr>
                            <th class="text-center">Id</th>
                            <th class="text-center">Id_Area</th>
                            <th class="text-center">Descripcion</th>
                            <th class="text-center">Asiento</th>
                            <th class="text-center">Estado</th>
                            <th class="text-center">Tipo</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>
                    <%
                        DaoMesa daoMesa = new  DaoMesa();
                        List<ModelMesa> lstMesa = daoMesa.listar();
                        Iterator<ModelMesa> iteratorMesa = lstMesa.iterator();
                        ModelMesa mesa = null;
                        while (iteratorMesa.hasNext()){
                            mesa = iteratorMesa.next();                        
                    %>                     
                    <tbody>
                        <tr>
                            <td class="text-center"><%= mesa.getIdMesa()%></td>
                             <td class="text-center"><%= mesa.getIdArea()%></td>
                            <td class="text-center"><%= mesa.getDescripcion()%></td>
                            <td class="text-center"><%= mesa.getAsiento()%></td>
                            <td class="text-center"><%= mesa.getEstado()%></td>
                            <td class="text-center"><%= mesa.getTipo()%></td>
                           
                            <td class="text-center">                                
                                <a href="ControllerMesa?accion=editar&id=<%=mesa.getIdMesa()%>">Editar</a>
                                <a href="ControllerMesa?accion=delete&id=<%=mesa.getIdMesa()%>">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                   
<jsp:include page="../Pages/Footer.jsp"></jsp:include>