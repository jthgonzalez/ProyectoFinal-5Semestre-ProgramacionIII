

<%@page import="org.models.ModelArea"%>
<%@page import="org.dao.DaoArea"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
            <form id="form-work" class="" name="form-work" action="ControllerArea" method="get">
                    <h1>Modifica Area</h1>
                  <div class="form-group" >  
                      <%
                          DaoArea daoArea = new DaoArea();
                          //Variable que nos envian desde el controller por medio de la instrucción setAttribute
                          int idArea = Integer.parseInt((String) request.getAttribute("idArea"));
                          ModelArea area = new ModelArea();
                          area = daoArea.list(idArea);
                      %>
                      
                      <input type="hidden" name="codigo" value="<%= area.getIdArea()%>">
                      <div class="col-md-4">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="Descripcion" type="text" value="<%= area.getDescripcion()%>">
                      </div>
                  <br><br><br>
                    
                      <div class="col-md-3">
                          <label class="control-label" for="nit">Estado</label> <%//nit??%>
                       <select name="estado" class="form-control">
                                <%
                                    if(area.getEstado()==1){
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
                        <br><br><br>
                        <div class="col-md-3">
                          <label class="control-label" for="tipo">Tipo</label>
                       <select name="tipo" class="form-control">
                                <%
                                    if(area.getTipo()=="FAMILIAR"){
                              %>
                              <option value="FAMLIAR" selected="">FAMLIAR</option>
                              <option value="PERSONAL" >PERSONAL</option>
                              <% }else{
                                %>
                             <option value="PERSONAL" selected="">PERSONAL</option>
                             <option value="FAMILIAR" >FAMLIAR</option>
                             <%}%>
   
                          </select>
                      </div>
                        <br><br><br>
                        
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="update" class="btn btn-success btn-lg btn-block info">Actualizar</button>
                      </div>
                      <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerArea?accion=read"  > Regresar</a>
                      </div>
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>

