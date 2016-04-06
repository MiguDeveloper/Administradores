<%-- 
    Document   : lee-importado
    Created on : 06-abr-2016, 11:51:02
    Author     : miguelchinchay
--%>


<%@page import="java.util.List"%>
<%@page import="Bean.UsuarioBean" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    if (session.getAttribute("login") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carga masiva de usuarios</title>
        <%@include file="metas_y_css.jsp" %>

    </head>
    <body>
        <%@include file="includes/nav_sup.jsp" %>
        
        <!--Start Container-->
        <div id="main" class="container-fluid">
            <div class="row">

                <%@include file="includes/menu_izquierda.jsp" %>

                <!--Start Content-->
                <div id="content" class="col-xs-12 col-sm-10">

                    <!-- Start Migas -->
                    <div class="row">
                        <div id="breadcrumb" class="col-xs-12 col-sm-12">
                            <a href="#" class="show-sidebar">
                                <i class="fa fa-bars"></i>
                            </a>
                            <ol class="breadcrumb pull-left">
                                <li><a href="index.html">Home</a></li>
                                <li><a href="#">Cargar usuarios masivo</a></li>
                            </ol>
                        </div>
                    </div>
                    <!-- End Migas -->

                    <!-- Start tabla -->
                    <div class="row">
                        <div class="col-xs-12 col-sm-12" >

                            <!-- Start box -->
                            <div class="box">
                                <div class="box-header">
                                    <div class="box-name">
                                        <i class="fa fa-search"></i>
                                        <span>Lista de usuarios a cargar masivamente</span>
                                    </div>
                                    <div class="box-icons">
                                        <a class="collapse-link">
                                            <i class="fa fa-chevron-up"></i>
                                        </a>
                                        <a class="expand-link">
                                            <i class="fa fa-expand"></i>
                                        </a>
                                    </div>
                                </div>

                                <!-- Start box-content -->
                                <div class="box-content">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-6">

                                            <%@include file="includes/mensajes.jsp" %>

                                            <!-- formulario LOAD -->
                                            <div class="col-md-12" style="margin-bottom:1%; margin-top:1%;">
                                                <div class="alert alert-success" role="alert">${msgCargaMasiva}</div>
                                                <button type="button" class="btn btn-success btn-xs" onclick="guardarMasivoUsers()">
                                                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Confirmar usuarios</button>
                                                <button type="button" class="btn btn-default btn-xs" onclick="cancelar()">
                                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Cancelar</button>
                                            </div>

                                            <div class="col-md-12" style="margin-bottom:1%; margin-top:1%;">
                                                <table width="100%" class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th>Usuario</th>
                                                            <th>Pwd</th>
                                                            <th>Nombres</th>
                                                            <th>Apellidos</th>
                                                            <th>Email</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            if (session.getAttribute("usuariosImportados") != null) {
                                                                List<UsuarioBean> lstUsuarios = (List<UsuarioBean>) session.getAttribute("usuariosImportados");
                                                                for (int i = 0; i < lstUsuarios.size(); i++) {
                                                                    UsuarioBean usuario = lstUsuarios.get(i);

                                                        %>
                                                        <tr>
                                                            <td><%=usuario.getUSUARIO()%></td>
                                                            <td><%=usuario.getPWD()%></td>
                                                            <td><%=usuario.getNOMBRES()%></td>
                                                            <td><%=usuario.getAPELLIDOS()%></td>
                                                            <td><%=usuario.getEMAIL()%></td>
                                                        </tr>

                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>



                                        </div>
                                    </div>
                                </div>
                                <!-- End box-content -->

                            </div>
                            <!-- End box -->

                        </div>
                    </div>
                    <!-- End tabla -->

                </div>
                <!--End Content-->

            </div>
        </div>
        <!--End Container-->

        <%@include file="librerias_javascript.jsp" %>
        <script type="text/javascript">

            function guardarMasivoUsers(){
                document.location = "UsuarioServlet?accion=guardarExcel";
            }
            function cancelar(){
                document.location = "cargar-usuarios.jsp";
            }
        </script>
    </body>
</html>

