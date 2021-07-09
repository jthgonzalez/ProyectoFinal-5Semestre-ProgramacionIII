

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelRoles"%>
<%@page import="org.dao.DaoRoles"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.models.ModelUsuario"%>
<%@page import="org.dao.DaoUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
            <form id="form-work" class="" name="form-work" action="ControllerUsuario" method="get">
                    <h1>Modifica Usuario</h1>
                  <div class="form-group" >  
                      <%
                          DaoUsuario daoUsuario = new DaoUsuario();
                          //Variable que nos envian desde el controller por medio de la instrucción setAttribute
                          int idUsuario= Integer.parseInt((String) request.getAttribute("idUsuario"));
                          ModelUsuario usuario = new ModelUsuario();
                          usuario = daoUsuario.list(idUsuario);
                      %>
                      
                      <input type="hidden" name="codigo" value="<%= usuario.getIdUsuario() %>">
                      <div class="col-md-4">
                          <label class="control-label" for="nombre">Nombre</label>
                          <input name="nombre" class="form-control" placeholder="Nombres" type="text" value ="<%= usuario.getNombre()%>">
                      </div>
                      <br><br><br>
                      <div class="col-md-4">
                          <label class="control-label" for="usuario">Usuario</label>
                          <input readonly name="usuario" class="form-control" placeholder="Usuario" type="text" value="<%= usuario.getUsuario()%>">
                      </div>
                      <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="clave">Clave</label>
                          <input name="clave" class="form-control" placeholder="Clave" type="password" value="<%= usuario.getClave()%>">
                      </div>
                                           <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="rol">Rol</label>
                          <select name="rol" class="form-control">
                 
                              <%
                                  String selected ="";
                              DaoRoles daoRol = new DaoRoles();
                 
                              ModelRoles rol=null;
                                List<ModelRoles> lstRol= daoRol.listar();
                               Iterator<ModelRoles> iteratorRol = lstRol.iterator();
                               while(iteratorRol.hasNext()){
                               rol = iteratorRol.next();
                                          selected ="";
                               
                               if(rol.getIdRol() == daoUsuario.getIdRol(usuario))
                               selected ="selected";
                               

                               %>
                               <option value="<%=rol.getIdRol() %>" <%=selected%>><%=rol.getNombre()%></option>      
                                       <%
                               }
                               
                              %>

                          </select>
                      <%--    <input name="estado" class="form-control" placeholder="Estado" type="text"> --%>
                      </div> 
                       <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="estadp">Estado</label>
                            <select name="estado" class="form-control">
                                <%
                                    if(usuario.getEstado()==1){
                              %>
                              <option value="1" selected="">1</option>
                              <option value="0" >0</option>
                              <% }else{
                                %>
                             <option value="0" selected="">0</option>
                             <option value="1" >1</option>
                             <%}%>
   
                          </select>
                         <%-- <input name="estado" class="form-control" placeholder="Estado" type="text" value="<%= usuario.getEstado()%>"> --%>
                      </div>
                        <br><br><br><br>
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="update" class="btn btn-success btn-lg btn-block info">Actualizar</button>
                      </div>
                      <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerUsuario?accion=read"  > Regresar</a>
                      </div>
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>
