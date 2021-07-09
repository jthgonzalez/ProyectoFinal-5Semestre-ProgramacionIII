

<%@page import="org.models.ModelMesa"%>
<%@page import="org.dao.DaoMesa"%>
<%@page import="org.models.ModelArea"%>
<%@page import="org.dao.DaoArea"%>
<%@page import="org.models.ModelEmpresa"%>
<%@page import="org.dao.DaoEmpresa"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelCliente"%>
<%@page import="org.dao.DaoCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>         
            <h1>Seleccione el Cliente</h1>
        
            <%--Modal--%>
            <!-- Button trigger modal -->
<button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">
  Agregar Nuevo
</button>
               
                      <div class="pull-right">
<ol class="breadcrumb">
  <%
                          DaoArea daoArea = new DaoArea();
                          int idarea= Integer.parseInt(request.getAttribute("gArea").toString());
                        
                          DaoMesa daoMesa = new DaoMesa();
                          //Variable que nos envian desde el controller por medio de la instrucción setAttribute
                          int idMesa = Integer.parseInt(request.getAttribute("gMesa").toString());
                           

                        
%>   
    
<li><a href=""><%= idarea + " - " + daoArea.areaNombre(idarea) %></a></li>
<li><a href=""><%= idMesa + " - " + daoMesa.mesaNombre(idMesa) %></a></li>

</ol>
                      </div>
            <!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Ingreso de Clientes</h4>
      </div>
          <form id="form-work" class="" name="form-work" action="ControllerPedido" method="get">
      
      <div class="modal-body">
          <input type="hidden" name="area" value="<%=idarea%>"/>
          <input type="hidden" name="mesa" value="<%=idMesa%>"/>
                
                <input type="hidden" name="modal" value="S"/>
                  <div class="form-group" >                      
                      
                      <div class="">
                          <label class="control-label" for="nombre">Nombre</label>
                          <input name="nombre" class="form-control btn-lg" placeholder="Nombres" type="text">
                      </div>
                     
                      <div class="">
                          <label class="control-label" for="apellido">Apellido</label>
                          <input name="apellido" class="form-control" placeholder="Apellidos" type="text">
                      </div>
                      
                      <div class="">
                          <label class="control-label" for="nit">Nit</label>
                          <input name="nit" class="form-control" placeholder="Nit" type="text">
                      </div>
                       
                      <div class="">
                          <label class="control-label" for="telefono">Telefono</label>
                          <input name="telefono" class="form-control" placeholder="Telefono" type="text">
                      </div>
                        
                      <div class="">
                          <label class="control-label" for="direccion">Dirección</label>
                          <input name="direccion" class="form-control" placeholder="Dirección" type="text">
                      </div>
                  
                               <div class="">
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
        
                  </div>

                
      </div>
      <div class="modal-footer">
        <button type="submit" name="accion" value="cliente" class="btn btn-success ">Guardar</button>
                  
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       
      </div>
                                  </form>

    </div>
  </div>
</div>
            
            <%--Modal Fin--%>
             <a class="btn btn-info" href="ControllerPedido?accion=read"  > Regresar</a>
                <table border="1" width="1" cellspacing="1" class="table table-bordered">
                    <thead>
                        <tr>
                            <th class="text-center">Id</th>
                            <th class="text-center">Nombre</th>
                            <th class="text-center">Apellido</th>
                            <th class="text-center">Nit</th>
                            <th class="text-center">Telefono</th>
                            <th class="text-center">Dirección</th>
                            <th class="text-center">Seleccione Cliente</th>
                        </tr>
                    </thead>
                    <%
                        DaoCliente daoCliente = new  DaoCliente();
                        List<ModelCliente> lstCliente = daoCliente.listar();
                        Iterator<ModelCliente> iteratorCliente = lstCliente.iterator();
                        ModelCliente cliente = null;
                        while (iteratorCliente.hasNext()){
                            cliente = iteratorCliente.next();                        
                    %>                     
                    <tbody>
                        <tr>
                            <td class="text-center"><%= cliente.getIdCliente()%></td>
                            <td class="text-center"><%= cliente.getNombre()%></td>
                            <td class="text-center"><%= cliente.getApellido()%></td>
                            <td class="text-center"><%= cliente.getNit()%></td>
                            <td class="text-center"><%= cliente.getTelefono()%></td>
                            <td class="text-center"><%= cliente.getDireccion()%></td>
                            <td class="text-center">                                
                                <a href="ControllerPedido?accion=create&id=<%=cliente.getIdCliente()%>&area=<%=idarea%>&mesa=<%=idMesa%>">Seleccionar</a>
     
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                   
<jsp:include page="../Pages/Footer.jsp"></jsp:include>