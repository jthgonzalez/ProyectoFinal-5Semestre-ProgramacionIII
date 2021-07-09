
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelModulo"%>
<%@page import="org.dao.DaoModulos"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
            <form id="form-work" class="" name="form-work" action="ControllerModulos" method="get">
                    <h1>Modificar Modulo</h1>
                  <div class="form-group" >  
                      <%
                          DaoModulos daoModulos = new DaoModulos();
                          int idModulo= Integer.parseInt((String) request.getAttribute("id_modulo"));
                          ModelModulo modulo = new ModelModulo();
                         modulo = daoModulos.list(idModulo);
                      %>
                      
                      <input type="hidden" name="codigo" value="<%= modulo.getId_modulo() %>">
                      <div class="col-md-4">
                          <label class="control-label" for="modulo">Modulo</label>
                          <input name="modulo" readonly="" class="form-control" placeholder="Modulo" type="text" value ="<%= modulo.getModulo()%>">
                      </div>
                      <br><br><br>
                      <div class="col-md-4">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="Descripcion" type="text" value="<%= modulo.getDescripcion()%>">
                      </div>
                      <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="telefono">Ayuda</label>
                          <input name="ayuda" class="form-control" placeholder="Ayuda" type="text" value="<%= modulo.getAyuda()%>">
                      </div>                 
                        <br><br><br><br>
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="update" class="btn btn-success btn-lg btn-block info">Actualizar</button>
                      </div>
                      <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerModulos?accion=read"  > Regresar</a>
                      </div>
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>