
<%@page import="org.models.ModelRolOpcion"%>
<%@page import="org.dao.DaoRolOpcion"%>
<%@page import="org.models.ModelModulo"%>
<%@page import="org.dao.DaoModulos"%>
<%@page import="org.models.ModelEmpresa"%>
<%@page import="org.dao.DaoEmpresa"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelRoles"%>
<%@page import="org.dao.DaoRoles"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
     
                    
                       <%
                            DaoRolOpcion daoRolOpcion = new DaoRolOpcion();
                          DaoRoles daoRoles = new DaoRoles();
                          int idRol= Integer.parseInt((String) request.getAttribute("idRol"));
                          ModelRoles roles = new ModelRoles();
                         roles = daoRoles.list(idRol);
                      %>
                      
                <h1>Opciones por Rol (<%= roles.getNombre()%>)</h1>
                  
                  <div class="form-group" >  
               
                   <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
      <%
                     String checked = "";
                String options="";
                        DaoModulos daoModulos = new DaoModulos();
                        List<ModelModulo> lstModulos= daoModulos.listar();
                        Iterator<ModelModulo> iteratorModulos = lstModulos.iterator();
                        ModelModulo modulos = null;
                        while (iteratorModulos.hasNext()){
                            modulos = iteratorModulos.next();
                       
                             checked = "";
                            options="off";
                            
                            if(daoRolOpcion.getExisteModulo(modulos,roles)>0){
                            checked="checked";
                            options="on";
                            }
                        %> 
                       
               <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <%--  <a role="button" data-toggle="collapse" data-parent="#accordion" href="<%=modulos.getModulo() %>" aria-expanded="true" aria-controls="<%=modulos.getModulo() %>">
          --%>
          <label>
            <input class="btnCheck" data-modulo="<%=modulos.getModulo() %>" id="<%=modulos.getModulo() %>M1" <%=checked%> data-rol="<%=roles.getIdRol() %>"  data-checked="<%=options%>"   type="checkbox"> <%=modulos.getDescripcion() %> (Requerido)
        </label>
     <%--   </a> --%>
      </h4>
    </div>
    <div id="<%=modulos.getModulo() %>" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading<%=modulos.getModulo() %>">
      <div class="panel-body">

            <%
               checked = "";
                options="";
                 
                       
                        List<ModelRolOpcion> lstRolOpcion= daoRolOpcion.listar(roles,modulos);
                        Iterator<ModelRolOpcion> iteratorRolOpcion = lstRolOpcion.iterator();
                        ModelRolOpcion rolopcion = null;
                        while (iteratorRolOpcion.hasNext()){
                            rolopcion = iteratorRolOpcion.next(); 
                            checked = "";
                            options="off";
                            
                            if(rolopcion.getExiste()>0){
                            checked="checked";
                            options="on";
                            }
                            


                        %>     
        <label>
            <input class="btnCheck" id="<%=rolopcion.getOpcion() %>" data-modulo="<%=modulos.getModulo() %>" data-rol="<%=roles.getIdRol() %>" data-checked="<%=options%>" <%=checked%>  type="checkbox"> <%= rolopcion.getDescripcion() %> 
        </label><br>
      <%}%>
      </div>
    </div>
  </div>
<% }%>

</div>
             <div class="col-md-3">
                          <a class="btn btn-success btn-lg" href="ControllerRolOpcion?accion=read"  > Guardar</a>
                      </div>
 <div class="col-md-3 ">
                          <a class="btn btn-info btn-lg" href="ControllerRolOpcion?accion=read"  > Regresar</a>
                      </div>
                     <br> <br>            
                      
                  </div>
          <script>
                $(document).ready(function(){
                
                var sendData= function(id,rol,pmodulo,action){
                    $.ajax({
                        url: "ControllerRolOpcion",
                        method:"post",
                        data:{"accion":action,
                        "idrol": rol,
                        "opcion": id,
                        "modulo": pmodulo }
                    }).done(function(response){
                            console.log(response); 
                    });
                }
                
                  $(".btnCheck").click(function(){
                     var ID = $(this).attr("id"), // opcion
                         checked = $(this).attr("data-checked"),// on/off
                         rol= $(this).attr("data-rol"),//rol
                         modulo= $(this).attr("data-modulo"); // modulo
                      
                      if(checked==="on"){
                          sendData(ID,rol,modulo,"delete");
                          $(this).attr("data-checked","off");
                          $(this).removeAttr("checked")
                      }else{
                          sendData(ID,rol,modulo,"add");
                          $(this).attr("data-checked","on");
                          $(this).attr("checked","");
                      }
                      
                      
                  });
                    
                });
            </script>  
                
            </form
            
       
            
<jsp:include page="../Pages/Footer.jsp"></jsp:include>