
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
<html>
    
<head> 
<link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
                <!-- jQuery -->
        <script src="js/jquery.min.js"></script>
        <script src="js/FullScrean.js"></script>
</head> 
      <body>
      <center>
      <%
      
int tipo= Integer.parseInt(request.getParameter("id").toString());
      
      %>
      <input id="tipoproducto" value="<%=tipo%>" type="hidden">

       
<div id="loader"></div>
<div class="outer_div"></div>
</center>
    	<script>
		$(document).ready(function(){
		setInterval(function(){
                   load(1); 
                },5000);	
		});

		function load(page){
                        var t = $("#tipoproducto").val();
			var parametros={"accion":"pedido","t":t};
			$("#loader").fadeIn('slow');
			$.ajax({
				url:'ControllerMonitor',
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
            		function update (idpedido,iddetelle,vusuario)
		{
                   
                  var t = $("#tipoproducto").val();
                      
		var parametros={"accion":"update","idpedido":idpedido,"iddetelle": iddetelle,"usuario": vusuario,"t":t};	
                //console.log(parametros);
                $("#loader").fadeIn('slow');
			$.ajax({
        
        url: "ControllerMonitor",
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
</body>
        </html>