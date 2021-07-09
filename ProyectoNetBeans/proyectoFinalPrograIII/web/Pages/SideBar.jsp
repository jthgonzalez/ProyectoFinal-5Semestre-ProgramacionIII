

<%@page import="java.util.Iterator"%>
<%@page import="org.models.ModelOpcionMenu"%>
<%@page import="java.util.List"%>
<%@page import="org.dao.DaoOpcionMenu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
                    HttpSession sesion = request.getSession();
                    
                    int idEmpresa = Integer.parseInt(sesion.getAttribute("g_Empresa").toString());
                        DaoOpcionMenu daoOpcionMenu = new DaoOpcionMenu();
                        List<ModelOpcionMenu> lstOpcionMenu= daoOpcionMenu.opcionesPadre(sesion.getAttribute("g_Usuario").toString(),idEmpresa);
                        Iterator<ModelOpcionMenu> iteratorOpcionesMenu= lstOpcionMenu.iterator();
                        ModelOpcionMenu opcionpadre = null;
                        while (iteratorOpcionesMenu.hasNext()){
                            opcionpadre = iteratorOpcionesMenu.next(); 
                            %>
                            <li class="">
                                <a href="#">

                                    <i class="<%=opcionpadre.getIcon() %> fa-fw"></i><%=opcionpadre.getDescripcion() %><span class="fa arrow"></span>
                                </a>
                                <ul class="nav nav-second-level">
                                    
                                    <%
                        List<ModelOpcionMenu> lstOpcionHijo= daoOpcionMenu.opcionesHijo(sesion.getAttribute("g_Usuario").toString(),opcionpadre.getOpcion(),idEmpresa);
                        Iterator<ModelOpcionMenu> iteratorOpcionesHijo= lstOpcionHijo.iterator();
                        ModelOpcionMenu opcionhijo = null;
                        while (iteratorOpcionesHijo.hasNext()){
                            opcionhijo = iteratorOpcionesHijo.next(); 
                                    
                                    %>
                                    
                                    <li>
                                        <a class="" href="<%=opcionhijo.getLink() %>"><%= opcionhijo.getDescripcion() %></a>
                                    </li>
                                <% } %>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                        <% } %>
                       