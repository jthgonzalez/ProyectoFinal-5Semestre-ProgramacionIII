
<%@page import="org.models.ModelModulo"%>
<%@page import="org.dao.DaoModulos"%>
<%@page import="org.dao.DaoModulos"%>
<%@page import="org.models.ModelEmpresa"%>
<%@page import="org.dao.DaoEmpresa"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelOpcionMenu"%>
<%@page import="org.dao.DaoOpcionMenu"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
            <form id="form-work" class="" name="form-work" action="ControllerOpcionMenu" method="get">
                    <h1>Modifica Opciones</h1>
                  <div class="form-group" >  
                      <%
                          DaoOpcionMenu daoOpcionMenu = new DaoOpcionMenu();
                          String idOpcionMenu=(String) request.getAttribute("idOpcionMenu");
                          ModelOpcionMenu opcionmenu = new ModelOpcionMenu();
                         opcionmenu = daoOpcionMenu.list(idOpcionMenu);
                      %>
                      
                      <input type="hidden" name="codigo" value="<%= opcionmenu.getOpcion() %>">
                          <br><br><br>
                               <div class="col-md-4">
                          <label class="control-label" for="opcion">Opcion</label>
                          <input readonly name="opcion" value="<%= opcionmenu.getOpcion() %>" class="form-control" placeholder="Opcion" type="text">
                      </div>
                  <br><br><br>
                               <div class="col-md-4">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" value="<%=opcionmenu.getDescripcion() %>" class="form-control" placeholder="Descripcion" type="text">
                      </div>
                      <br><br><br>
                     <div class="form-group" >  
                             
                      <div class="col-md-3">
                          <label class="control-label" for="modulo">Modulo</label>
                          <select readonly name="modulo" class="form-control">
                              
                              <%
                                  String selected="";
                              DaoModulos daoModulos = new DaoModulos();
                              ModelModulo vmodulo=null;
                                List<ModelModulo> lstModulos= daoModulos.listar();
                               Iterator<ModelModulo> iteratorModulos = lstModulos.iterator();
                               while(iteratorModulos.hasNext()){
                               vmodulo = iteratorModulos.next();
                               selected="";
                               
                                if(vmodulo.getModulo() == opcionmenu.getModulo()) selected="selected";
                               %>
                               <option  value="<%=vmodulo.getModulo()%>" <%=selected%>><%=vmodulo.getDescripcion()%></option>      
                                       <%
                               }
                              %>

                          </select>
                      <%--    <input name="estado" class="form-control" placeholder="Estado" type="text"> --%>
                      </div> 
                           <br><br><br>
                               <div class="col-md-4">
                                   <label class="control-label" for="link">Link 
                                       &nbsp;<span class="fa fa-info-circle info" ></span></label>
                                   <div class="input-group">
                          <input name="link" value="<%=opcionmenu.getLink() %>" class="form-control" placeholder="Link" type="text"> 
                      </div>
                                   </div>
                         <br><br><br>
                               <div class="col-md-4">
                          <label class="control-label" for="visible">Visible</label>
                          <select name="visible" class="form-control">
                              <option value="S" selected="">S</option>
                              <option value="N">N</option>
                          </select>
                      </div>
                        <br><br><br><br>
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="update" class="btn btn-success btn-lg btn-block info">Actualizar</button>
                      </div>
                      <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerOpcionMenu?accion=read"  > Regresar</a>
                      </div>
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>