
<%@page import="org.dao.DaoEmpresa"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.models.ModelSucursal"%>
<%@page import="java.util.List"%>
<%@page import="org.dao.DaoSucursal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>         
            <h1>Sucursal</h1>
            
                <div class="pull-left">
                    <a class="btn btn-success"  href="ControllerSucursal?accion=nuevo"  > Agregar Nuevo</a>
                </div>
             
            
            <table border="1" width="1" cellspacing="1" class="table table-bordered">
                <thead >
                        <tr >
                            <th class="text-center">Id</th>
                            <th class="text-center">Nombre</th>
                            <th class="text-center">Empresa</th>
                            <th class="text-center">Descripcion</th>
                            <th class="text-center">Direccion</th>
                            <th class="text-center">Telefono</th>
                            <th class="text-center">Estado</th>                            
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>                                        
                    <tbody>
                        <%
                        DaoSucursal daoSucursal = new  DaoSucursal();
                        DaoEmpresa daoEmpresa = new DaoEmpresa();
                        List<ModelSucursal> lstSucursal= daoSucursal.listar();
                        Iterator<ModelSucursal> iteratorSucursal = lstSucursal.iterator();
                        ModelSucursal sucursal = null;
                        while (iteratorSucursal.hasNext()){
                            sucursal = iteratorSucursal.next();                        
                        %> 
                        <tr>
                            <td class="text-center"><%= sucursal.getIdSucursal()%></td>
                            <td class="text-center"><%= sucursal.getNombre()%></td>
                            <td class="text-center"><%= daoEmpresa.getNombre(sucursal.getIdEmpresa())%></td>
                            <td class="text-center"><%= sucursal.getDescripcion()%></td>
                            <td class="text-center"><%= sucursal.getDireccion()%></td>
                            <td class="text-center"><%= sucursal.getTelefono()%></td>
                            <td class="text-center"><%= sucursal.getEstado()%></td>                            
                            <td class="text-center">                                
                                <a href="ControllerSucursal?accion=editar&id=<%=sucursal.getIdSucursal() %>" class="text-center">Editar</a>
                                <a href="ControllerSucursal?accion=delete&id=<%=sucursal.getIdSucursal() %>" class="text-center">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
 <jsp:include page="../Pages/Footer.jsp"></jsp:include>