

<%@page import="net.sf.jasperreports.engine.JRPrintPage"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporter"%>
<%@page import="net.sf.jasperreports.engine.JRExporter"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.config.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <%
          response.setHeader("Cache-Control", "no-cache");
          response.setHeader("Cache-Control", "no-store");
          response.setHeader("Pragma", "no-cache");
          response.setDateHeader("Expires",0);
          
response.setContentType("application/pdf");

try{
  //  ServletOutputStream salida= response.getOutputStream();
    Conexion con = new Conexion();
           Map par = new HashMap<String,Object>();
           par.put("idCliente", Integer.parseInt(request.getParameter("cliente").toString()));
           par.put("nofac", Integer.parseInt(request.getParameter("fac").toString()));
           par.put("serie", request.getParameter("serie").toString());
           
      JasperReport reporte =(JasperReport) JRLoader.loadObjectFromFile(getServletContext().getRealPath("Reportes/rep_factura.jasper"));

      JasperPrint jp = JasperFillManager.fillReport(reporte, par, con.open());
    JRExporter exportar = new JRPdfExporter();
    exportar.setParameter(JRExporterParameter.JASPER_PRINT,jp);
  
   exportar.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
   
   
   exportar.exportReport();
}catch(Exception ex){
    ex.printStackTrace();
}

       %>
    </body>
</html>
