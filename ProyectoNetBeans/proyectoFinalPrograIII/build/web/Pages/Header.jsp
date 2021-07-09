

<%@page import="org.dao.DaoEmpresa"%>
<%@page import="org.models.ModelEmpresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Proyecto Final Programcion III">
        <meta name="author" content="umg">
        <meta content="no-cache" http-equiv="Pragma" />
        <meta content="no-cache" http-equiv="Cache-Control" />
        <meta content="no-store" http-equiv="Cache-Control" />
        <meta content="max-age=0" http-equiv="Cache-Control" />
        <meta content="1" http-equiv="Expires" />
        <title>PROYECTO FINAL</title>

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="css/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/startmin.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
                <!-- jQuery -->
        <script src="js/jquery.min.js"></script>

        <script>
            history.forward();
        </script>
    </head>
    <body>
        
        <%
            ModelEmpresa empresa=null;
         response.setHeader("Cache-Control", "no-cache");
         response.setHeader("Pragma", "no-cache");
          response.setHeader("Cache-Control", "no-store");
            response.setDateHeader("Expires",0); 
    if( (session.getAttribute("LogOut")!=null)
            ||  (session.getAttribute("g_Usuario")==null)
            ){
      //  response.sendRedirect("../Login/Login.jsp");
       // response.sendRedirect("../Login/Login.jsp");
         RequestDispatcher vista = request.getRequestDispatcher("../Login/Login.jsp"); 
       vista.forward(request, response);
    }else{
DaoEmpresa daoEmpresa = new DaoEmpresa();
int idEmpresa=Integer.parseInt(session.getAttribute("g_Empresa").toString());
 empresa= daoEmpresa.list(idEmpresa);
    }
    %>
        
        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div class="navbar-header">
                    <a class="navbar-brand" href="dashboard.jsp"><%=empresa.getNombre() %></a>
                </div>

                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <ul class="nav navbar-right navbar-top-links">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i> <%=session.getAttribute("g_Usuario") %> <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                          <%--  <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                            </li>
                            <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                            </li>---%>
                            <li class="divider"></li>
                            <li><a href="LogOut?accion=logout"><i class="fa fa-sign-out fa-fw"></i> Salir</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- /.navbar-top-links -->

                <div class="navbar-default sidebar" role="navigation">
                    
                    
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                         
                            <li class="sidebar-search"></li>
       
                            <jsp:include page="SideBar.jsp"></jsp:include>

                        </ul>
                    </div>
                    <!-- /.sidebar-collapse -->
                </div>
                <!-- /.navbar-static-side -->
            </nav>

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
  
                            