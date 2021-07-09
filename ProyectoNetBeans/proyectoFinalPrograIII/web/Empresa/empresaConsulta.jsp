
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelEmpresa"%>
<%@page import="org.dao.DaoEmpresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
         
            <h1>Empresas</h1>
            <a class="btn btn-success" href="ControllerEmpresa?accion=nuevo"  > Agregar Nuevo</a>
            
            
                <table border="1" width="1" cellspacing="1" class="table table-bordered">
                    <thead>
                        <tr>
                            <th class="text-center">Id</th>
                            <th class="text-center">Nombre</th>
                            <th class="text-center">Descripcion</th>
                            <th class="text-center">Direccion</th>
                            <th class="text-center">Telefono</th>
                            <th class="text-center">Estado</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>
                    <%
                        DaoEmpresa daoEmpresa = new  DaoEmpresa();
                        List<ModelEmpresa> lstEmpresa = daoEmpresa.listar();
                        Iterator<ModelEmpresa> iteratorEmpresa = lstEmpresa.iterator();
                        ModelEmpresa empresa = null;
                        while (iteratorEmpresa.hasNext()){
                            empresa = iteratorEmpresa.next();                        
                    %>                     
                    <tbody>
                        <tr>
                            <td class="text-center"><%= empresa.getIdEmpresa()%></td>
                            <td class="text-center"><%= empresa.getNombre()%></td>
                            <td class="text-center"><%= empresa.getDescripcion()%></td>
                            <td class="text-center"><%= empresa.getDireccion()%></td>
                            <td class="text-center"><%= empresa.getTelefono()%></td>
                            <td class="text-center"><%= empresa.getEstado()%></td>
                            <td class="text-center">                                
                                <a href="ControllerEmpresa?accion=editar&id=<%=empresa.getIdEmpresa()%>">Editar</a>
                                <a href="ControllerEmpresa?accion=delete&id=<%=empresa.getIdEmpresa()%>">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>