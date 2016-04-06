<%-- 
    Document   : lm-usuarios
    Created on : 04-abr-2016, 15:22:55
    Author     : miguelchinchay
--%>

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
                                <li><a href="#">Carga masiva usuarios</a></li>
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
                                        <span>Carga masiva de usuarios</span>
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
                                                
                                                <form action="./UsuarioServlet?accion=ImportaExcel" method="post" enctype="multipart/form-data" class="form-horizontal" id="frmLoadUsers">
                                                    <div class="form-group">
                                                        <label for="txtUser" class="col-xs-4 control-label">Seleccione archivo</label>
                                                        <div class="col-xs-8">
                                                            <input type="file" class="form-control" id="archivoUser" name="archivoUser">
                                                        </div>
                                                    </div>
                                                    <button id='btnCargar' type="submit" class="btn btn-default">
                                                        <span class="glyphicon glyphicon-upload" aria-hidden="true"></span> SUBIR ARCHIVO</button>
                                                </form>
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
            
            $(document).ready(function() {
                $("#frmLoadUsers").validate({
                    rules: {
                        archivoUser: {
                            required: true,
                            extension: 'xls|csv'
                        }
                    },
                    messages: {
                        archivoUser: {
                            required: 'Seleccione un archivo',
                            extension: 'Sólo esta permitido formato xls, csv'
                        }
                    }
                });
            });

        </script>
    </body>
</html>
