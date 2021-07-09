
<%@page import="java.util.Iterator"%>
<%@page import="org.models.ModelUsuario"%>
<%@page import="java.util.List"%>
<%@page import="org.dao.DaoUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>           
            <h1>Usuarios</h1>
            <div class="row">
                <div class="pull-left">
                    <a class="btn btn-success"  href="ControllerUsuario?accion=nuevo"  > Agregar Nuevo</a>
                </div>
                <div class="span8 pull-right hidden">
                    <form action="ControllerUsuario"  class="form-inline">
                        <input type="text" class="form-control small" autofocus="on" name="busqueda" placeholder="Buscar" />
                    <button type="submit" class="btn btn-default">Search</button>
                        <input type="hidden" name="accion" value="read" />
                    
                    </form>
                </div>
            </div>
           
            <table border="1" width="1" cellspacing="1" class="table table-bordered">
                <thead >
                        <tr >
                            <th class="text-center">Id</th>
                            <th class="text-center">Nombre</th>
                            <th class="text-center">Usuario</th>
                            <th class="text-center">Clave</th>
                            <th class="text-center">Estado</th>                            
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>                                        
                    <tbody>
                        <%
                        DaoUsuario daoUsuario = new  DaoUsuario();
                        String buscar = ((String) request.getAttribute("pbusqueda"));
                        List<ModelUsuario> lstUsuario = daoUsuario.listar(buscar);
                      
                        Iterator<ModelUsuario> iteratorUsuario = lstUsuario.iterator();
                        ModelUsuario usuario = null;
                        while (iteratorUsuario.hasNext()){
                            usuario = iteratorUsuario.next();                        
                        %> 
                        <tr>
                            <td class="text-center"><%= usuario.getIdUsuario()%></td>
                            <td class="text-center"><%= usuario.getNombre()%></td>
                            <td class="text-center"><%= usuario.getUsuario()%></td>
                            <td class="text-center"><%= usuario.getClave()%></td>
                            <td class="text-center"><%= usuario.getEstado()%></td>                            
                            <td class="text-center">                                
                                <a href="ControllerUsuario?accion=editar&id=<%=usuario.getIdUsuario() %>" class="text-center">Editar</a>
                                <a href="ControllerUsuario?accion=delete&id=<%=usuario.getIdUsuario() %>" class="text-center">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>