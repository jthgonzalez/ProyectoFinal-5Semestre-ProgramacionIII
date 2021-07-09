 <%-- 
    Document   : modificaRol
    Created on : 29-abr-2019, 21:27:10
    Author     : LENOVO
--%>
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
                    <h1>Modifica Roles</h1>
                  <div class="form-group" >  
                      <%
                          DaoRolUsuario daoRoles = new DaoRolUsuario();
                          int idRol= Integer.parseInt((String) request.getAttribute("idRoles"));
                          ModelRolUsuario roles = new ModelRolUsuario();
                         roles = daoRoles.list(idRol);
                      %>
                      
                      <input type="hidden" name="codigo" value="<%= roles.getIdRol() %>">
                      <div class="col-md-4">
                          <label class="control-label" for="nombre">Nombre</label>
                          <input name="nombre" class="form-control" placeholder="Nombre" type="text" value ="<%= roles.getUsuario()%>">
                      </div>
                
                              <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="empresa">Empresa</label>
                          <select name="empresa" class="form-control">
                              
                              <%
                                  String selected ="";
                              DaoEmpresa daoEmpresa = new DaoEmpresa();
                              ModelEmpresa empresa=null;
                                List<ModelEmpresa> lstEmpresa= daoEmpresa.listar();
                               Iterator<ModelEmpresa> iteratorEmpresa = lstEmpresa.iterator();
                               while(iteratorEmpresa.hasNext()){
                               empresa = iteratorEmpresa.next();
                               selected ="";
                               
                               if(empresa.getIdEmpresa() == roles.getIdEmpresa())
                               selected ="selected";
                               

                               %>
                               <option value="<%=empresa.getIdEmpresa()%>" <%=selected%>><%=empresa.getNombre()%></option>      
                                       <%
                               }
                              %>

                          </select>
                      <%--    <input name="estado" class="form-control" placeholder="Estado" type="text"> --%>
                      </div>                  
                        <br><br><br><br>
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="update" class="btn btn-success btn-lg btn-block info">Actualizar</button>
                      </div>
                      <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerRolUsuario?accion=read"  > Regresar</a>
                      </div>
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>