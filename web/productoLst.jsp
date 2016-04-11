
<%
    if (session.getAttribute("login") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de productos</title>
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
                                <li><a href="#">Lista de productos</a></li>
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
                                        <span>Listado general de productos</span>
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
                                        <div class="col-xs-12">
                                            <div class="col-xs-10">
                                                <div class="form-inline">
                                                    <div class="form-grouP has-feedback">
                                                        <div class="input-group">
                                                            <span class="input-group-addon"><i class="fa fa-search"></i> BUSCAR</span>
                                                            <input type="text" class="form-control" id="txtBuscar" onKeyUp="buscar();">
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>

                                            <%@include file="includes/mensajes.jsp" %>

                                            <!-- tabla para el jqGrid -->
                                            <div class="col-md-12" style="margin-bottom:1%; margin-top:1%;">
                                                <div class="jqGrid">
                                                    <table id="list"></table>
                                                    <div id="divPaginado" class="scroll"></div>
                                                </div>
                                            </div>


                                            <!-- Lista de botones de accion -->
                                            <div class="col-xs-10">
                                                <button id='btnNuevo' type="button" class="btn btn-default" onclick="BtnNuevo()"><i class="fa fa-plus-square"></i> Nuevo</button>
                                                <button id='btnEditar' type="button"  class="btn btn-default"  onclick='BtnActualizar()'><i class="fa fa-pencil-square"></i> Editar</button>
                                                <button id='btnEliminar' type="button"  class="btn btn-default"  onclick='del()'><i class="fa fa-trash-o"></i> Eliminar</button>
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
            $.jgrid.no_legacy_api = true;
            $.jgrid.useJSON = true;
            
            var lst = '';
            var tbl = '';
            
            var tblEstructura = function(){
                lst = tbl.jqGrid({
                    url : "./ProductoServlet?accion=buscar",
                    datatype : 'json',
                    mtype :'POST',
                    colNames : [
                        'id',
                        'Producto',
                        'Categoria',
                        'Moneda',
                        'Precio',
                        'Stock',
                        'Estado'
                    ],
                    colModel : [
                        {
                            name : 'id',
                            index : 1
                        },
                        {
                            name : 'descripcion',
                            index : 2
                        },
                        {
                            name : 'categoria_id',
                            index : 3
                        },
                        {
                            name : 'moneda',
                            index : 4
                        },
                        {
                            name : 'precio',
                            index : 5
                        },
                        {
                            name : 'stock',
                            index : 6
                        },
                        {
                            name : 'flg_estado',
                            index : 7
                        }
                    ],
                    height: 300,
                    width: 940,
                    shrinkToFit: true,
                    rowNum: 20,
                    loadOnce: true,
                    viewrecords: true,
                    gridview: true,
                    caption: 'LISTADO GENERAL DE PRODUCTOS',
                    beforeRequest: function() {
                        //responsive_jqgrid($(".jqGrid"));
                    },
                    jsonReader: {
                        repeatitems: true,
                        root: 'lstLista'
                    }
                })
            };
            $(document).ready(function(){
                tbl = $("#list");
                tblEstructura();
            });
        </script>
    </body>
</html>
