
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
            <h1>Ingreso Empresa</h1>
            <form id="form-work" class="" name="form-work" action="ControllerEmpresa" method="get">
      
                
                    
                  <div class="form-group" >                      
                      
                      <div class="col-md-4">
                          <label class="control-label" for="nombre">Nombre</label>
                          <input name="nombre" class="form-control" placeholder="Nombre" type="text">
                      </div>
                      <br><br><br>
                      <div class="col-md-4">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="Descripcion" type="text">
                      </div>
                       <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="direccion">Dirección</label>
                          <input name="direccion" class="form-control" placeholder="Dirección" type="text">
                      </div>
                       <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="telefono">Telefono</label>
                          <input name="telefono" class="form-control" placeholder="Telefono" type="text">
                      </div>
                       
                                              <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="estado">Estado</label>
                          <select name="estado" class="form-control">
                              <option value="1" selected>1</option> 
                               <option value="0">0</option> 
                          </select>
                      </div>
                        <br><br><br><br>
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="create" class="btn btn-success btn-lg btn-block info">Guardar</button>
                      </div>
                           <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerEmpresa?accion=read"  > Regresar</a>
                      </div>
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>