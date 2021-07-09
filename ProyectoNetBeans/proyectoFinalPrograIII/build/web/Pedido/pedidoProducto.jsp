
<%@page import="org.models.ModelTipoProducto"%>
<%@page import="org.dao.DaoTipoProducto"%>
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
<br><br>

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

         
                <label>Datos del Cliente</label>
                           <table border="1" width="1" cellspacing="1" class="table table-bordered">
                    <thead>
                        <tr>
                            <th class="text-center">Id</th>
                            <th class="text-center">Nombre</th>
                            <th class="text-center">Apellido</th>
                            <th class="text-center">Nit</th>
                            <th class="text-center">Telefono</th>
                            <th class="text-center">Dirección</th>
                      
                        </tr>
                    </thead>
                    <%
                                DaoCliente daoCliente = new DaoCliente();
                          //Variable que nos envian desde el controller por medio de la instrucción setAttribute
                          int idCliente = Integer.parseInt( request.getAttribute("gCliente").toString());
                          ModelCliente cliente1 = new ModelCliente();
                          cliente1 = daoCliente.list(idCliente);
                                        
                    %>   
                    <input type="hidden" name="cliente" id="cliente" value="<%=idCliente%>"/>
                    <input type="hidden" name="usuario" id="usuario" value="<%=session.getAttribute("g_Usuario") %>"/>
                    
                    <tbody>
                        <tr>
                            <td class="text-center"><%= cliente1.getIdCliente()%></td>
                            <td class="text-center"><%= cliente1.getNombre()%></td>
                            <td class="text-center"><%= cliente1.getApellido()%></td>
                            <td class="text-center"><%= cliente1.getNit()%></td>
                            <td class="text-center"><%= cliente1.getTelefono()%></td>
                            <td class="text-center"><%= cliente1.getDireccion()%></td>
                          
                        </tr>
                    
                    </tbody>
                </table>
          

            
         
             <div class="pull-right">
                 <a class="btn btn-info" href="ControllerPedido?accion=read"  > Regresar</a>
                 &nbsp;&nbsp;&nbsp;
                  <a class="btn btn-success" href="ControllerProductoAjax?accion=pedido&id=<%=idCliente%>&usuario=<%=session.getAttribute("g_Usuario") %>&mesa=<%=idMesa%>&area=<%=idarea%>"  > Generar Pedido</a>
                  &nbsp;&nbsp;&nbsp;
                 <button class="btn btn-primary" data-toggle="modal" data-target="#myModal">Agregar Productos</button>
             </div>
             
             <label>Detalle del Pedido</label>
             
          			<!-- Modal -->
			<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">Buscar productos</h4>
				  </div>
				  <div class="modal-body">
					<form class="form-horizontal">
					  <div class="form-group">
						<div class="col-sm-4">
						  <input type="text" class="form-control" id="q" placeholder="Buscar productos" onkeyup="load(1)">
						</div>
                                              <div class="col-sm-3">
						  
                                                  <select required="" id="tipoproducto" name="tipoproducto" class="form-control" onchange="load(1)">
                               <option value="0" >Tipo Producto</option>
                              <%
                              DaoTipoProducto daoTProducto = new DaoTipoProducto();
                              ModelTipoProducto pr=null;
                                List<ModelTipoProducto> lstProducto= daoTProducto.listar();
                               Iterator<ModelTipoProducto> iteratorPr = lstProducto.iterator();
                               while(iteratorPr.hasNext()){
                               pr = iteratorPr.next();
                               %>
                               <option value="<%=pr.getIdTipoProducto()%>" ><%=pr.getDescripcion()%></option>      
                                       <%
                               }
                              %>

                          </select>
						</div>
						<button type="button" class="btn btn-default" onclick="load(1)"><span class='glyphicon glyphicon-search'></span> Buscar</button>
					  </div>
					</form>
					<div id="loader" style="position: absolute;	text-align: center;	top: 55px;	width: 100%;display:none;"></div><!-- Carga gif animado -->
					<div class="outer_div" ></div><!-- Datos ajax Final -->
				  </div>
				  <div class="modal-footer">
					<button type="button" id="mClose" class="btn btn-default" data-dismiss="modal">Cerrar</button>
					
				  </div>
				</div>
			  </div>
			</div>   
             
             
             <div id="resultados" class='col-md-12'></div><!-- Carga los datos ajax -->
             
             
      	<script>
		$(document).ready(function(){
			load(1);
                        $("form").submit(function(e){
                            e.preventDefault();
                        });
		});

		function load(page){
			var q= $("#q").val();
                        var t = $("#tipoproducto").val();
			var parametros={"accion":"read","page":page,"q":q,"t":t};
			$("#loader").fadeIn('slow');
			$.ajax({
				url:'ControllerProductoAjax',
				data: parametros,
				 beforeSend: function(objeto){
				 $('#loader').html('<img src="images/ajax-loader.gif"> Cargando...');
			  },
				success:function(data){
					$(".outer_div").html(data).fadeIn('slow');
					$('#loader').html('');
					
				}
			})
		}
	</script>
        
        	<script>
	function agregar (idprod){
       
	
			var vcantidad=$('#cantidad_'+idprod).val();
                        var vexistencia =$('#prod'+idprod).val();
                        var vcliente =$('#cliente').val();
                        var vusuario =$('#usuario').val();
                      
			//Inicia validacion
			if (isNaN(vcantidad))
			{
			alert('Esto no es un numero');
			document.getElementById('cantidad_'+idprod).focus();
			return false;
			}
                       
                        if (parseInt(vcantidad)>parseInt(vexistencia))
			{
			alert('No hay Existencia Suficiente');
			document.getElementById('cantidad_'+idprod).focus();
			return false;
			}
			
			//Fin validacion
		var parametros={"accion":"add","id":idprod,"cantidad":vcantidad,"cliente": vcliente,"usuario": vusuario};
              // console.log(parametros);
        $.ajax({
       
        url: "ControllerProductoAjax",
        data: parametros,
		 beforeSend: function(objeto){
			$("#resultados").html("Mensaje: Cargando...");
		  },
        success: function(datos){
		$("#resultados").html(datos);
                var close = document.getElementById("mClose");
                close.click();
           ///     console.log(datos);
		}
			});
		}
        // fin agregar
		
			function eliminar (idprod)
		{
                       var vcliente =$('#cliente').val();
                        var vusuario =$('#usuario').val();
                      
		var parametros={"accion":"eliminar","id":idprod,"cliente": vcliente,"usuario": vusuario};	
                //console.log(parametros);
			$.ajax({
        
        url: "ControllerProductoAjax",
        data: parametros,
		 beforeSend: function(objeto){
			$("#resultados").html("Mensaje: Cargando...");
		  },
               success: function(datos){
		$("#resultados").html(datos);
		}
			});

		}

	</script>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>