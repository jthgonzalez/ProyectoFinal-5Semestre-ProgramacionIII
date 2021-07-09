
<%@page import="org.models.ModelModulo"%>
<%@page import="org.models.ModelOpcionMenu"%>
<%@page import="org.dao.DaoModulos"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.models.ModelOpcionMenu"%>
<%@page import="java.util.List"%>
<%@page import="org.dao.DaoOpcionMenu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
<h1>Ingresar Opciones</h1>
            <form id="form-work"  name="form-work" action="ControllerOpcionMenu" method="get">
                    <div class="col-md-4">
                          <label class="control-label" for="opcion">Opcion</label>
                          <input name="opcion" class="form-control" placeholder="Opcion" type="text">
                      </div>
                      <br><br><br>
                               <div class="col-md-4">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="Descripcion" type="text">
                      </div>
                      <br><br><br>
                     <div class="form-group" >  
                             
                      <div class="col-md-3">
                          <label class="control-label" for="modulo">Modulo</label>
                          <select name="modulo" class="form-control">
                              
                              <%
                              DaoModulos daoModulos = new DaoModulos();
                              ModelModulo vmodulo=null;
                                List<ModelModulo> lstModulos= daoModulos.listar();
                               Iterator<ModelModulo> iteratorModulos = lstModulos.iterator();
                               while(iteratorModulos.hasNext()){
                               vmodulo = iteratorModulos.next();
                               %>
                               <option value="<%=vmodulo.getModulo()%>" ><%=vmodulo.getDescripcion()%></option>      
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
                        <!--  <input name="link" class="form-control" placeholder="Link" type="text"> -->
                               <select name="link" class="form-control" placeholder="Link" >
                              
                              <option value="ControllerCliente?accion=read">Consultar Clientes</option>
                              <option value="ControllerEmpresa?accion=read">Consultar Empresas</option>
                              <option value="ControllerModulos?accion=read">Consultar Modulos</option>
                              <option value="ControllerModulosEmpresa?accion=read">Modulos por Empresa</option>
                              <option value="ControllerOpcionMenu?accion=read">Opciones del Menu</option>
                              <option value="ControllerRolOpcion?accion=read">Opciones por Rol</option>
                              <option value="ControllerProducto?accion=read">Consultar Productos</option>
                              <option value="ControllerRoles?accion=read">Consultar Roles</option>
                              <option value="ControllerTipoProducto?accion=read">Tipo de Productos</option>
                              <option value="ControllerUsuario?accion=read">Consultar Usuarios</option>
                              
                                <option value="ControllerCliente?accion=nuevo">Agregar Clientes</option>
                              <option value="ControllerEmpresa?accion=nuevo">Agregar Empresas</option>
                              <option value="ControllerModulos?accion=nuevo">Agregar Modulos</option>
                              <option value="ControllerModulosEmpresa?accion=nuevo">Agregar Empresa</option>
                              <option value="ControllerOpcionMenu?accion=nuevo">Agregar Opciones del Menu</option>
                              <option value="ControllerRolOpcion?accion=nuevo">Agregar Opciones por Rol</option>
                              <option value="ControllerProducto?accion=nuevo">Agregar Productos</option>
                              <option value="ControllerRoles?accion=nuevo">Agregar Roles</option>
                              <option value="ControllerTipoProducto?accion=nuevo">Agregar Tipo de Productos</option>
                              <option value="ControllerUsuario?accion=nuevo">Agregar Usuarios</option>
                              
                              
                          </select>
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
                          <button type="submit" name="accion" value="create" class="btn btn-success btn-lg btn-block info">Guardar</button>
                      </div>
                           <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerOpcionMenu?accion=read"  > Regresar</a>
                      </div>
                        
                  </div>
                
         </form>    
           
                     
         
  <jsp:include page="../Pages/Footer.jsp"></jsp:include>