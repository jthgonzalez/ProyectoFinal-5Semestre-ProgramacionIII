
<%@page import="org.dao.DaoModulos"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.models.ModelModulo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
<h1>Ingreso de Modulos</h1>
            <form id="form-work" class="" name="form-work" action="ControllerModulos" method="get">
                  
                <div class="form-group" >  
                <div class="col-md-4">
                          <label class="control-label" for="nombre">Modulo</label>
                          <input name="modulo" class="form-control" placeholder="Modulo" type="text">
                      </div>
                      <br><br><br>
                      <div class="col-md-4">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="Descripcion" type="text">
                      </div>
                      <br><br><br>
                     
                         <div class="col-md-3">
                          <label class="control-label" for="telefono">Ayuda</label>
                          <input name="ayuda" class="form-control" placeholder="Ayuda" type="text">
                      </div>
                       
                                              <br><br><br>
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="create" class="btn btn-success btn-lg btn-block info">Guardar</button>
                      </div>
                           <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerModulos?accion=read"  > Regresar</a>
                      </div>
                        
                  </div>
                
         </form>    
           
                     
         
  <jsp:include page="../Pages/Footer.jsp"></jsp:include>