

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelRoles"%>
<%@page import="org.dao.DaoRoles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
            <form id="form-work" class="" name="form-work" action="ControllerUsuario" method="get">
      
                
                <h1>Ingresar Usuario</h1>
                  <div class="form-group" >                      
                      <div class="col-md-4">
                          <label class="control-label" for="nombre">Nombre</label>
                          <input name="nombre" class="form-control" placeholder="Nombres" type="text">
                      </div>
                      <br><br><br>
                      <div class="col-md-4">
                          <label class="control-label" for="usuario">Usuario</label>
                          <input name="usuario" class="form-control" placeholder="Usuario" type="text">
                      </div>
                      <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="clave">Clave</label>
                          <input name="clave" class="form-control" placeholder="Clave" type="password">
                      </div>
                                <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="rol">Rol</label>
                          <select name="rol" class="form-control">
                 
                              <%
                              DaoRoles daoRol = new DaoRoles();
                              ModelRoles rol=null;
                                List<ModelRoles> lstRol= daoRol.listar();
                               Iterator<ModelRoles> iteratorRol = lstRol.iterator();
                               while(iteratorRol.hasNext()){
                               rol = iteratorRol.next();
                               %>
                               <option value="<%=rol.getIdRol() %>" ><%= rol.getNombre()%></option>      
                                       <%
                               }
                              %>

                          </select>
                      <%--    <input name="estado" class="form-control" placeholder="Estado" type="text"> --%>
                      </div>  
                       <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="estado">Estado</label>
                          <select name="estado" class="form-control">
                              <option value="1" selected="">1</option>
                              <option value="0" >0</option>
                          </select>
                      <%--    <input name="estado" class="form-control" placeholder="Estado" type="text"> --%>
                      </div>                  
                        <br><br><br><br>
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="create" class="btn btn-success btn-lg btn-block info">Guardar</button>
                      </div>
                           <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerUsuario?accion=read"  > Regresar</a>
                      </div>
                        
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>