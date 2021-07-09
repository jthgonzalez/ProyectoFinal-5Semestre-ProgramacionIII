

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelEmpresa"%>
<%@page import="org.dao.DaoEmpresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>

<h1>Ingreso de Clientes</h1>
            <form id="form-work" class="" name="form-work" action="ControllerCliente" method="get">
      
                
                    
                  <div class="form-group" >                      
                      <input type="hidden" name="modal" value="N"/>
                      <div class="col-md-4">
                          <label class="control-label" for="nombre">Nombre</label>
                          <input name="nombre" class="form-control" placeholder="Nombres" type="text">
                      </div>
                      <br><br><br>
                      <div class="col-md-4">
                          <label class="control-label" for="apellido">Apellido</label>
                          <input name="apellido" class="form-control" placeholder="Apellidos" type="text">
                      </div>
                      <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="nit">Nit</label>
                          <input name="nit" class="form-control" placeholder="Nit" type="text">
                      </div>
                       <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="telefono">Telefono</label>
                          <input name="telefono" class="form-control" placeholder="Telefono" type="text">
                      </div>
                        <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="direccion">Dirección</label>
                          <input name="direccion" class="form-control" placeholder="Dirección" type="text">
                      </div>
                        <br><br><br>
                               <div class="col-md-3">
                          <label class="control-label hide" for="empresa">Empresa</label>
                          <select name="empresa" class="form-control hide">
                              
                              <%
                              DaoEmpresa daoEmpresa = new DaoEmpresa();
                              ModelEmpresa empresa=null;
                                List<ModelEmpresa> lstEmpresa= daoEmpresa.listar();
                               Iterator<ModelEmpresa> iteratorEmpresa = lstEmpresa.iterator();
                               while(iteratorEmpresa.hasNext()){
                               empresa = iteratorEmpresa.next();
                               %>
                               <option value="<%=empresa.getIdEmpresa()%>" ><%=empresa.getNombre()%></option>      
                                       <%
                               }
                              %>

                          </select>
                      <%--    <input name="estado" class="form-control" placeholder="Estado" type="text"> --%>
                      </div>
                        <br><br><br><br>
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="create" class="btn btn-success btn-lg btn-block info">Guardar</button>
                      </div>
                              <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerCliente?accion=read"  > Regresar</a>
                      </div>
                        
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>
