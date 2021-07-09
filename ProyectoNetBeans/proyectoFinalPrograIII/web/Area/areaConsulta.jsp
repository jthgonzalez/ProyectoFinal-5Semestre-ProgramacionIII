

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelArea"%>
<%@page import="org.dao.DaoArea"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
         
            <h1>Areas</h1>
            <a class="btn btn-success" href="ControllerArea?accion=nuevo"  > Agregar Area</a>
            
            
                <table border="1" width="1" cellspacing="1" class="table table-bordered">
                    <thead>
                        <tr>
                            <th class="text-center">Id_Area</th>
                            <th class="text-center">Descripcion</th>
                            <th class="text-center">Estado</th>
                            <th class="text-center">Tipo</th>
                            <th class="text-center">Acciones</th>
                        </tr>
                    </thead>
                    <%
                        DaoArea daoArea = new  DaoArea();
                        List<ModelArea> lstArea = daoArea.listar();
                        Iterator<ModelArea> iteratorArea = lstArea.iterator();
                        ModelArea area = null;
                        while (iteratorArea.hasNext()){
                            area = iteratorArea.next();                        
                    %>                     
                    <tbody>
                        <tr>
                            <td class="text-center"><%= area.getIdArea()%></td>
                            <td class="text-center"><%= area.getDescripcion()%></td>
                            <td class="text-center"><%= area.getEstado()%></td>
                            <td class="text-center"><%= area.getTipo()%></td>
                            <td class="text-center">                                
                                <a href="ControllerArea?accion=editar&id=<%=area.getIdArea()%>">Editar</a>
                                <a href="ControllerArea?accion=delete&id=<%=area.getIdArea()%>">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>
