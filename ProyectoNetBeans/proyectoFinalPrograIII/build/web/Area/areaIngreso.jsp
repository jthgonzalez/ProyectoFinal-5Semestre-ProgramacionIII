

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
            <h1>Ingreso Area</h1>
            <form id="form-work" class="" name="form-work" action="ControllerArea" method="get">
      
                
                    
                  <div class="form-group" >                      
                      
                      <div class="col-md-4">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="Descripcion" type="text">
                      </div>
                                              <br><br><br>
                      <div class="col-md-3">
                          <label class="control-label" for="estado">Estado</label>
                          <select name="estado" class="form-control">
                              <option value="1" selected>1</option> 
                               <option value="0">0</option> 
                          </select>
                      </div>
                        <br><br><br>
                         <div class="col-md-3">
                          <label class="control-label" for="tipo">Tipo</label>
                          <select name="tipo" class="form-control">
                              <option value="FAMILIAR" selected>AREA FAMILIAR</option> 
                               <option value="PERSONAL">AREA PERSONAL</option> 
                          </select>
                      </div>
                        <br><br><br>
                      <div class="col-md-3">
                          <button type="submit" name="accion" value="create" class="btn btn-success btn-lg btn-block info">Guardar</button>
                      </div>
                           <div class="col-md-3">
                          <a class="btn btn-success btn-lg btn-block info" href="ControllerArea?accion=read"  > Regresar</a>
                      </div>
                  </div>

                
            </form>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>
