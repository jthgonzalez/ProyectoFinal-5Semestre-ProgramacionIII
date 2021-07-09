

<%@page import="org.models.ModelTipoProducto"%>
<%@page import="org.dao.DaoTipoProducto"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelEmpresa"%>
<%@page import="org.dao.DaoEmpresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
            <h1>Ingreso Producto</h1>
            <form id="form-work" class="" name="form-work" action="ControllerProducto" method="get">
      
                
                    
                  <div class="form-group" >                      
                      
                      
                      <div class="col-md-4">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="Descripcion" type="text">
                      </div>
                      <br><br><br>
                     <div class="col-md-3">
                          <label class="control-label" for="tipoproducto">Tipo de Producto</label>
                          <select required="" name="tipoproducto" class="form-control">
                              
                              <%
                              DaoTipoProducto daoTProducto = new DaoTipoProducto();
                              ModelTipoProducto pr=null;
                                List<ModelTipoProducto> lstProducto= daoTProducto.listar();
                               Iterator<ModelTipoProducto> iteratorPr = lstProducto.iterator();
                               while(iteratorPr.hasNext()){
                               pr = iteratorPr.next();
                               %>
                               <option value="<%=pr.getIdTipoProducto()%>" ><%=pr.getDescripcion()%></option>      
                                       <%
                               }
                              %>

                          </select>
                      <%--    <input name="estado" class="form-control" placeholder="Estado" type="text"> --%>
                      </div> 
                       <br><br><br>
                       <div class="col-md-4">
                          <label class="control-label" for="precio">Precio</label>
                          <input name="precio" class="form-control" placeholder="Precio" type="text">
                      </div>
                      <br><br><br>
                      <div class="col-md-4">
                          <label class="control-label" for="existencia">Existencia</label>
                          <input name="existencia" class="form-control" placeholder="Existencia" type="text">
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
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerProducto?accion=read"  > Regresar</a>
                      </div>
                        
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>
