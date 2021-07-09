

<%@page import="org.models.ModelArea"%>
<%@page import="org.dao.DaoArea"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelMesa"%>
<%@page import="org.dao.DaoMesa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>

<h1>Ingreso de Mesa</h1>
            <form id="form-work" class="" name="form-work" action="ControllerMesa" method="get">
      
                
                    
                  <div class="form-group" >                      
                       <div class="col-md-3">
                          <label class="control-label" for="idarea">Area</label>
                          <select name="idarea" class="form-control">
                              
                              <%
                              DaoArea daoArea = new DaoArea();
                              ModelArea area=null;
                                List<ModelArea> lstarea= daoArea.listar();
                               Iterator<ModelArea> iteratorArea = lstarea.iterator();
                               while(iteratorArea.hasNext()){
                               area = iteratorArea.next();
                               %>
                               <option value="<%=area.getIdArea()%>" ><%= area.getDescripcion() %></option>      
                                       <%
                               }
                              %>

                          </select>
                    
                      </div> 
                      <br><br><br>
                      <div class="col-md-4">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="descripcion" type="text">
                      </div>
                      <br><br><br>
                      <div class="col-md-4">
                          <label class="control-label" for="asiento">Asientos</label>
                          <input name="asiento" class="form-control" placeholder="N. de Asientos" type="text">
                      </div>
                      <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="estado">Estado</label>
                            <select name="estado" class="form-control">
                              
                              <option value="LIBRE" selected="">LIBRE</option>
                              
                          </select>
                      </div>
                        <br><br><br>
                      <div class="form-group" >                      
                       <div class="col-md-3">
                          <label class="control-label" for="tipo">Tipo</label>
                          <select name="tipo" class="form-control">
                              
                           <option value="PERSONAL" selected="">PERSONAL</option>
                           <option value="FAMILIAR" selected="">FAMILIAR</option>

                          </select>
                    
                      </div> 
                             
                        <br><br><br><br>
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="create" class="btn btn-success btn-lg btn-block info">Guardar</button>
                      </div>
                              <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerMesa?accion=read"  > Regresar</a>
                      </div>
                        
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>
