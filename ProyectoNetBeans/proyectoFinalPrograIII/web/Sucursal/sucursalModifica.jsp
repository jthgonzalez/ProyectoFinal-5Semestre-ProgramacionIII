

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelEmpresa"%>
<%@page import="org.dao.DaoEmpresa"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.models.ModelSucursal"%>
<%@page import="org.dao.DaoSucursal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
            <form id="form-work" class="" name="form-work" action="ControllerSucursal" method="get">
                    <h1>Modifica Sucursal</h1>
                  <div class="form-group" >  
                      <%
                          DaoSucursal daoSucursal = new DaoSucursal();
                          //Variable que nos envian desde el controller por medio de la instrucción setAttribute
                          int idSucursal= Integer.parseInt((String) request.getAttribute("idSucursal"));
                          ModelSucursal sucursal = new ModelSucursal();
                         sucursal = daoSucursal.list(idSucursal);
                      %>
                      
                      <input type="hidden" name="codigo" value="<%= sucursal.getIdSucursal() %>">
                      <div class="col-md-4">
                          <label class="control-label" for="nombre">Nombre</label>
                          <input name="nombre" class="form-control" placeholder="Nombre" type="text" value ="<%= sucursal.getNombre()%>">
                      </div>
                      <br><br><br>
                      <div class="col-md-4">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="Descripcion" type="text" value="<%= sucursal.getDescripcion()%>">
                      </div>
                      <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="direccion">Direccion</label>
                          <input name="direccion" class="form-control" placeholder="Direccion" type="text" value="<%= sucursal.getDireccion()%>">
                      </div>
                          <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="telefono">Telefono</label>
                          <input name="telefono" class="form-control" placeholder="Telefono" type="text" value="<%= sucursal.getTelefono()%>">
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
                               
                               if(empresa.getIdEmpresa() == sucursal.getIdEmpresa())
                               selected ="selected";
                               

                               %>
                               <option value="<%=empresa.getIdEmpresa()%>" <%=selected%>><%=empresa.getNombre()%></option>      
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
                                    if(sucursal.getEstado()==1){
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
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerSucursal?accion=read"  > Regresar</a>
                      </div>
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>