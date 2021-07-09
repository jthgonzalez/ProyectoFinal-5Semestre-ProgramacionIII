<%-- 
    Document   : agregarRol
    Created on : 05-may-2019, 12:34:29
    Author     : LENOVO
--%>

<%@page import="org.models.ModelRoles"%>
<%@page import="org.dao.DaoRoles"%>
<%@page import="org.models.ModelEmpresa"%>
<%@page import="org.dao.DaoEmpresa"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelRolUsuario"%>
<%@page import="org.dao.DaoRolUsuario"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
            <form id="form-work" class="" name="form-work" action="ControllerRolUsuario" method="get">
                    <h1>Agrega Rol Usuario</h1>
                  <div class="form-group" >  
                      <%
                          DaoRolUsuario daoRoles = new DaoRolUsuario();
                          int idRol= Integer.parseInt((String) request.getAttribute("idRUsuario"));
                         // int idRol=1;
                          ModelRolUsuario roles = new ModelRolUsuario();
                         roles = daoRoles.list(idRol);
                      %>
                      
                      <input type="hidden" name="codigo" value="<%= roles.getIdRol() %>">
                      <div class="col-md-3">
                          <br>
                         <h4>Nombre del usuario: <span class="label label-success" for="usuario"><%= roles.getUsuario()%></span></h4>
                      </div>
                      <br><br><br><br>
                        
                               <%
                      //  DaoRoles daoSucursal = new  DaoSucursal();
                        DaoRoles daoR = new DaoRoles();
                        List<ModelRoles> lstR= daoR.listar();
                        
                        Iterator<ModelRoles> iteratorRoles = lstR.iterator();
                        ModelRoles rol = null;
                        while (iteratorRoles.hasNext()){
                            rol = iteratorRoles.next();                        
                        %>
                       
                        <label class="control-label" for="usuario"><%=rol.getNombre()%></label>
                        <input name="" class="custom custom-checkbox" type="checkbox" placeholder="Nombre" value="<%=rol.getIdRol()%>">
                        <br>
                         
                        <%}%>
                                       
                        <br><br><br><br>
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="update" class="btn btn-success btn-lg btn-block info">Aceptar</button>
                      </div>
                      <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerRolUsuario?accion=read"  > Regresar</a>
                      </div>
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>