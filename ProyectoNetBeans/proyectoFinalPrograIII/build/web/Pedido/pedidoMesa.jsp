

<%@page import="org.models.ModelMesa"%>
<%@page import="org.dao.DaoMesa"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.models.ModelArea"%>
<%@page import="org.dao.DaoArea"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Pages/Header.jsp"></jsp:include>
     
            <h1>Pedido</h1>
            <div class="col-xs-1 pull-right">
                <a href="" class="btn btn-info">Actualizar</a>
            </div>
 <ul class="nav nav-tabs">
     <%
         int i=0;
         String clas="";
         DaoArea daoArea = new  DaoArea();
                        List<ModelArea> lstArea = daoArea.areasMesas();
                        Iterator<ModelArea> iteratorArea = lstArea.iterator();
                        ModelArea area = null;
                        while (iteratorArea.hasNext()){
                            area = iteratorArea.next(); 
clas="";
if(i==0)clas="active";

i=1;
                            %>
  <li role="presentation" class="<%=clas%>" >
      <a href="#_area<%= area.getIdArea() %>"><%= area.getDescripcion() %></a>
  </li>
  
  <% } %>
</ul>
            
 <div class="tab-content">
     <%
         i=0;

         area= null;
         iteratorArea = lstArea.iterator();
       while (iteratorArea.hasNext()){
                            area = iteratorArea.next(); 
         clas="";
if(i==0)clas="active";
i=1;
                            %>
                            
                            
     <div role="tabpanel" class="tab-pane <%=clas%>" id="_area<%= area.getIdArea() %>">
         <br>
    <div class="row">
        <%
           String bakcolor="";
           String target="";
                        DaoMesa daoMesa = new  DaoMesa();
                        List<ModelMesa> lstMesa = daoMesa.listarMesas(area.getIdArea());
                        Iterator<ModelMesa> iteratorMesa = lstMesa.iterator();
                        ModelMesa mesa = null;
                        while (iteratorMesa.hasNext()){
                            mesa = iteratorMesa.next();
                            bakcolor="#19A25D";
                            target="ControllerPedido?accion=nuevo&area="+area.getIdArea()+"&mesa="+mesa.getIdMesa();
                            if((mesa.getEstado()).equals("OCUPADA")){
                                bakcolor="red";
                                target="";
                            } 

                   
        %>
        <div class="col-xs-6 col-md-3" >
            <a  href="<%=target%>" class="thumbnail text-center " style="margin: auto; padding: auto;">
            <div class="text-center "  style="margin: auto; padding: auto; height: 180px; width: 100%; display: block; background: <%=bakcolor%>; color: #000">
                <h1 style="margin:0"><%= mesa.getIdMesa() %> - <%= mesa.getDescripcion() %></h1>
            </div>
        </a>
      </div>
<% } %>
      </div>
    </div>
     
     
     <% } %>
     </div>
</div>
<script>
$('.nav-tabs a').click(function (e) {
  e.preventDefault()
  $(this).tab('show')
});

</script>
<jsp:include page="../Pages/Footer.jsp"></jsp:include>
