<%-- 
    Document   : ws
    Created on : 07-abr-2016, 15:46:13
    Author     : miguelchinchay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prueba web Service</title>
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
                                <li><a href="#">Lista de usuarios</a></li>
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
                                        <span>Mantenimiento a Usuarios</span>
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
            $(document).ready(function(){
                $.ajax({
                    type : 'get',
                    url : "http://api.geonames.org/astergdemJSON",
                    dataType : 'jsonp',
                    async: true,
                    data : {
                        formatted : true,
                        lat : '10.00',
                        lng :'76.0',
                        username : 'demo',
                        style : 'full'
                    },
                    success: function(respuesta){
                        console.log(respuesta);
                    }
                })
            });
        </script>
    </body>
</html>
