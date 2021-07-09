
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelCliente"%>
<%@page import="org.dao.DaoCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>         
            <h1>Clientes</h1>
            <a class="btn btn-success" href="ControllerCliente?accion=nuevo"  > Agregar Nuevo</a>
                <table border="1" width="1" cellspacing="1" class="table table-bordered">
                    <thead>
                        <tr>
                            <th class="text-center">Id</th>
                            <th class="text-center">Nombre</th>
                            <th class="text-center">Apellido</th>
                            <th class="text-center">Nit</th>
                            <th class="text-center">Telefono</th>
                            <th class="text-center">Dirección</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>
                    <%
                        DaoCliente daoCliente = new  DaoCliente();
                        List<ModelCliente> lstCliente = daoCliente.listar();
                        Iterator<ModelCliente> iteratorCliente = lstCliente.iterator();
                        ModelCliente cliente = null;
                        while (iteratorCliente.hasNext()){
                            cliente = iteratorCliente.next();                        
                    %>                     
                    <tbody>
                        <tr>
                            <td class="text-center"><%= cliente.getIdCliente()%></td>
                            <td class="text-center"><%= cliente.getNombre()%></td>
                            <td class="text-center"><%= cliente.getApellido()%></td>
                            <td class="text-center"><%= cliente.getNit()%></td>
                            <td class="text-center"><%= cliente.getTelefono()%></td>
                            <td class="text-center"><%= cliente.getDireccion()%></td>
                            <td class="text-center">                                
                                <a href="ControllerCliente?accion=editar&id=<%=cliente.getIdCliente()%>">Editar</a>
                                <a href="ControllerCliente?accion=delete&id=<%=cliente.getIdCliente()%>">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                   
<jsp:include page="../Pages/Footer.jsp"></jsp:include>