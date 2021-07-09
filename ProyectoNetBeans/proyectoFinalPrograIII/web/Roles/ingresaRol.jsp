
<%@page import="org.models.ModelEmpresa"%>
<%@page import="org.dao.DaoEmpresa"%>
<%@page import="org.dao.DaoRoles"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.models.ModelRoles"%>
<%@page import="java.util.List"%>
<%@page import="org.dao.DaoRoles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
<h1>Ingreso Roles</h1>
            <form id="form-work" class="" name="form-work" action="ControllerRoles" method="get">
                    <div class="col-md-4">
                          <label class="control-label" for="nombre">Nombre</label>
                          <input name="nombre" class="form-control" placeholder="Nombre" type="text">
                      </div>
                      <br><br><br>
                     <div class="form-group" >  
                             
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
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerRoles?accion=read"  > Regresar</a>
                      </div>
                        
                  </div>
                
         </form>    
           
                     
         
  <jsp:include page="../Pages/Footer.jsp"></jsp:include>