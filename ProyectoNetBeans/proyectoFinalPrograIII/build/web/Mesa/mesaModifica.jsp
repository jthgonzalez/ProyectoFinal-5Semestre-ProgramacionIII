
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelMesa"%>
<%@page import="org.dao.DaoMesa"%>
<%@page import="org.models.ModelMesa"%>
<%@page import="org.dao.DaoMesa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
            <form id="form-work" class="" name="form-work" action="ControllerMesa" method="get">
                    <h1>Modifica Mesa</h1>
                  <div class="form-group" >  
                      <%
                          DaoMesa daoMesa = new DaoMesa();
                          //Variable que nos envian desde el controller por medio de la instrucción setAttribute
                          int idMesa = Integer.parseInt((String) request.getAttribute("idMesa"));
                          ModelMesa mesa = new ModelMesa();
                          mesa = daoMesa.list(idMesa);
                      %>
                      <%-- <input type="hidden" name="empresa" value="<%= mesa.getIdEmpresa() %>"> --%>
                       <input type="hidden" name="codigo" value="<%= mesa.getIdMesa()%>">
                     
                       
                       <div class="col-md-4">
                          <label class="control-label" for="idarea">Area</label>
                          <input name="idarea" readonly=""  class="form-control" placeholder="Id" type="text" value="<%= mesa.getIdArea()%>">
                      </div>
                       <br><br><br>
                     
                      <div class="col-md-4">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="descripcion" type="text" value ="<%= mesa.getDescripcion()%>">
                      </div>
                      <br><br><br>
                      <div class="col-md-4">
                          <label class="control-label" for="asiento">Asiento</label>
                          <input name="asiento" class="form-control" placeholder="asiento" type="text" value="<%= mesa.getAsiento()%>">
                      </div>
                      <br><br><br>
                     <div class="col-md-3">
                          <label class="control-label" for="estado">Estado</label>
                            <select name="estado" class="form-control">
                                <%
                                    if((mesa.getEstado()).equals("OCUPADA")){
                              %>
                              <option value="OCUPADA" selected="">OCUPADA</option>
                              <option value="LIBRE" >LIBRE</option>
                              <% }else{
                                %>
                             <option value="LIBRE" selected="">LIBRE</option>
                             <option value="OCUPADA" >OCUPADA</option>
                             <%}%>
   
                          </select>
                         <%-- <input name="estado" class="form-control" placeholder="Estado" type="text" value="<%= usuario.getEstado()%>"> --%>
                      </div>
                       <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="tipo">Tipo</label>
                          <select name="tipo" class="form-control">
                              
                              <%
                              
                               DaoMesa daotip = new DaoMesa();
                               ModelMesa tip=null;
                               List<ModelMesa> lsttip= daotip.listar();
                               Iterator<ModelMesa> iteratorTip = lsttip.iterator();

                               while(iteratorTip.hasNext()){
                               tip = iteratorTip.next();
                               %>
                               <option value="<%=tip.getTipo()%>" ><%=tip.getTipo()%></option>      
                                       <%
                               }
                              %>

                          </select>
                    
                      </div> 
                        <br><br><br>
                      
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="update" class="btn btn-success btn-lg btn-block info">Actualizar</button>
                      </div>
                      <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerMesa?accion=read"  > Regresar</a>
                      </div>
                        
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>