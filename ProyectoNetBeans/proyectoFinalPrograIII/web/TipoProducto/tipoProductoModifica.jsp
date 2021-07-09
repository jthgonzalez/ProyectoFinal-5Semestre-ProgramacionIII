
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelEmpresa"%>
<%@page import="org.dao.DaoEmpresa"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.models.ModelTipoProducto"%>
<%@page import="org.dao.DaoTipoProducto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
            <form id="form-work" class="" name="form-work" action="ControllerTipoProducto" method="get">
                    <h1>Modifica Tipo de Producto</h1>
                  <div class="form-group" >  
                      <%
                          DaoTipoProducto daoPr = new DaoTipoProducto();
                          //Variable que nos envian desde el controller por medio de la instrucción setAttribute
                          int idPr= Integer.parseInt((String) request.getAttribute("idTipo"));
                          ModelTipoProducto pr = new ModelTipoProducto();
                         pr = daoPr.list(idPr);
                      %>
                      
                      <input type="hidden" name="codigo" value="<%= pr.getIdTipoProducto() %>">
                     
                      <div class="col-md-4">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="Descripcion" type="text" value="<%= pr.getDescripcion()%>">
                      </div>
                      <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="estado">Estado</label>
                            <select name="estado" class="form-control">
                                <%
                                    if(pr.getEstado()==1){
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
                          <label class="control-label hide" for="empresa">Empresa</label>
                          <select name="empresa" class="form-control hide">
                              
                              <%
                                  String selected ="";
                              DaoEmpresa daoEmpresa = new DaoEmpresa();
                              ModelEmpresa empresa=null;
                                List<ModelEmpresa> lstEmpresa= daoEmpresa.listar();
                               Iterator<ModelEmpresa> iteratorEmpresa = lstEmpresa.iterator();
                               while(iteratorEmpresa.hasNext()){
                               empresa = iteratorEmpresa.next();
                               selected ="";
                               
                               if(empresa.getIdEmpresa() == pr.getIdEmpresa())
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
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerTipoProducto?accion=read"  > Regresar</a>
                      </div>
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>