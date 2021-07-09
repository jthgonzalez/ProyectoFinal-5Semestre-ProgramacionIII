
<%@page import="org.models.ModelEmpresa"%>
<%@page import="org.dao.DaoEmpresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
            <form id="form-work" class="" name="form-work" action="ControllerEmpresa" method="get">
                    <h1>Modifica Empresa</h1>
                  <div class="form-group" >  
                      <%
                          DaoEmpresa daoEmpresa = new DaoEmpresa();
                          //Variable que nos envian desde el controller por medio de la instrucción setAttribute
                          int idEmpresa = Integer.parseInt((String) request.getAttribute("idEmpresa"));
                          ModelEmpresa empresa = new ModelEmpresa();
                          empresa = daoEmpresa.list(idEmpresa);
                      %>
                      
                      <input type="hidden" name="codigo" value="<%= empresa.getIdEmpresa()%>">
                      <div class="col-md-4">
                          <label class="control-label" for="nombre">Nombre</label>
                          <input name="nombre" class="form-control" placeholder="Nombre" type="text" value ="<%= empresa.getNombre()%>">
                      </div>
                      <br><br><br>
                      <div class="col-md-4">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="Descripcion" type="text" value="<%= empresa.getDescripcion()%>">
                      </div>
                  <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="direccion">Dirección</label>
                          <input name="direccion" class="form-control" placeholder="Dirección" type="text" value="<%= empresa.getDireccion()%>">
                      </div>
                       <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="telefono">Telefono</label>
                          <input name="telefono" class="form-control" placeholder="Telefono" type="text" value="<%= empresa.getTelefono()%>">
                      </div>
                    
                              <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="nit">Estado</label>
                       <select name="estado" class="form-control">
                                <%
                                    if(empresa.getEstado()==1){
                              %>
                              <option value="1" selected="">1</option>
                              <option value="0" >0</option>
                              <% }else{
                                %>
                             <option value="0" selected="">0</option>
                             <option value="1" >1</option>
                             <%}%>
   
                          </select>
                      </div>
                        <br><br><br><br>
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="update" class="btn btn-success btn-lg btn-block info">Actualizar</button>
                      </div>
                      <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerEmpresa?accion=read"  > Regresar</a>
                      </div>
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>
