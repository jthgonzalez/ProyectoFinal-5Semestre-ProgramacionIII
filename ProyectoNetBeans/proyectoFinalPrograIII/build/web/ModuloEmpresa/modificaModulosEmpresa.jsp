
<%@page import="org.dao.DaoModuloEmpresa"%>
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
                            DaoEmpresa daoEmpresa = new DaoEmpresa();
                  
                         int idempresa= Integer.parseInt(request.getAttribute("idEmpresa").toString());
                          ModelEmpresa empresa = new ModelEmpresa();
                         empresa = daoEmpresa.list(idempresa);
                      %>
                      
                <h1>Modulos Restaurante (<%=  empresa.getNombre()%>)</h1>
                  
                  <div class="form-group" >  
               
                   <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
      <%
                     String checked = "";
                String options="";
                
                DaoModuloEmpresa daomoduloempresa = new DaoModuloEmpresa();
                        DaoModulos daoModulos = new DaoModulos();
                        List<ModelModulo> lstModulos= daoModulos.listar();
                        Iterator<ModelModulo> iteratorModulos = lstModulos.iterator();
                        ModelModulo modulos = null;
                        while (iteratorModulos.hasNext()){
                            modulos = iteratorModulos.next();
                       
                             checked = "";
                            options="off";
                         
                            if(daomoduloempresa.existeModulo(modulos,idempresa)>0){
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
            <input class="btnCheck" data-modulo="<%=modulos.getModulo() %>" id="<%=modulos.getModulo() %>" <%=checked%> data-empresa="<%=empresa.getIdEmpresa() %>"  data-checked="<%=options%>"   type="checkbox"> <%=modulos.getDescripcion() %>
        </label>
     <%--   </a> --%>
      </h4>
    </div>

  </div>
<% }%>

</div>
             <div class="col-md-3">
                          <a class="btn btn-success btn-lg" href="ControllerModulosEmpresa?accion=read"  > Guardar</a>
                      </div>
 <div class="col-md-3 ">
                          <a class="btn btn-info btn-lg" href="ControllerModulosEmpresa?accion=read"  > Regresar</a>
                      </div>
                     <br> <br>            
                      
                  </div>
          <script>
                $(document).ready(function(){
                
                var sendData= function(id,empresa,pmodulo,action){
                    $.ajax({
                        url: "ControllerModulosEmpresa",
                        method:"post",
                        data:{"accion":action,
                        "empresa": empresa,
                        "modulo": pmodulo }
                    }).done(function(response){
                            console.log(response); 
                    });
                }
                
                  $(".btnCheck").click(function(){
                     var ID = $(this).attr("id"), // opcion
                         checked = $(this).attr("data-checked"),// on/off
                         empresa= $(this).attr("data-empresa"),//rol
                         modulo= $(this).attr("data-modulo"); // modulo
                      
                      if(checked==="on"){
                          sendData(ID,empresa,modulo,"delete");
                          $(this).attr("data-checked","off");
                          $(this).removeAttr("checked")
                      }else{
                          sendData(ID,empresa,modulo,"add");
                          $(this).attr("data-checked","on");
                          $(this).attr("checked","");
                      }
                      
                      
                  });
                    
                });
            </script>  
                
            </form
            
       
            
<jsp:include page="../Pages/Footer.jsp"></jsp:include>