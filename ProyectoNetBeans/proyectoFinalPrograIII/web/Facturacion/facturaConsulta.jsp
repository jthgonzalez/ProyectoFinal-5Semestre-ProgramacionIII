

<%@page import="org.models.ModelTmpPedido"%>
<%@page import="org.dao.DaoTmpPedido"%>
<%@page import="org.models.ModelProducto"%>
<%@page import="org.dao.DaoProducto"%>
<%@page import="org.dao.DaoEmpresa"%>
<%@page import="org.dao.DaoTipoProducto"%>
<%@page import="org.dao.DaoRoles"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.models.ModelTipoProducto"%>
<%@page import="java.util.List"%>
<%@page import="org.dao.DaoRoles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/Pages/Header.jsp"></jsp:include>  
<h1>Consulta de Facturas</h1>
      <center>
       
          <div id="loader" class="pull-right">
              <a href="" class="btn btn-info">Actualizar</a>
          </div>
<div class="outer_div">
<jsp:include page="facturaDatos.jsp" />  
</div>
</center>
    	<script>
		$(document).ready(function(){
		/*setInterval(function(){
                //   load(1); 
                },5000);*/	
		});

		function load(page){
                     
			var parametros={"accion":"datos"};
			$("#loader").fadeIn('slow');
			$.ajax({
				url:'ControllerFactura',
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
            		function facturar (idcliente,idpedido)
		{
                   
            
                      
		var parametros={"accion":"update","idpedido":idpedido,"idcliente": idcliente};	
                //console.log(parametros);
                $("#loader").fadeIn('slow');
			$.ajax({
        
        url: "ControllerFactura",
        data: parametros,
		 beforeSend: function(objeto){
			$('#loader').html('<img src="images/ajax-loader.gif"> Cargando...');
		  },
               success: function(data){
		$(".outer_div").html(data).fadeIn('slow');
					$('#loader').html('');
		}
			});

		}
        </script>
    <jsp:include page="../Pages/Footer.jsp"></jsp:include>