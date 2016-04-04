<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    if(session.getAttribute("login") == null){
        response.sendRedirect("login.jsp");
    }
    %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de clientes</title>

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
                                <li><a href="#">Primera tabla</a></li>
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

        <script type='text/javascript'>
            
            $.jgrid.no_legacy_api = true;
            $.jgrid.useJSON = true;

            var BtnNuevo = function() {
                $.ajax({
                    type: 'POST',
                    url: 'tblConexionServlet',
                    dataType: 'json',
                    data: {
                        accion: "eliminarSesion"
                    },
                    success: function(response) {
                        nVentana(response.mensaje, 'REGISTRO DE NUEVO CLIENTE', '700')
                    }
                })
            };

            var buscar = function() {
                if (lst != '') {
                    tbl.jqGrid('setGridParam',
                            {
                                url: './tblConexionServlet?accion=buscar&txtApe_Paterno=' + $('#txtBuscar').val()
                            }
                    ).trigger('reloadGrid');
                }
            };

            var BtnActualizar = function() {
                var rowId = $("#list").jqGrid('getGridParam', 'selrow');

                if (rowId) {
                    var fila = $("#list").jqGrid('getRowData', rowId);
                    $.ajax({
                        type: 'POST',
                        url: 'tblConexionServlet',
                        dataType: 'json',
                        data: {
                            accion : 'obtenerPorId',
                            id: fila.ID
                        },
                        success: function(response){
                            nVentana(response.mensaje,'Actualizar cliente','700')
                        }
                        
                    })
                } else {
                    $('#error').show("fast");
                    $('#error').html("DEBE SELECCIONAR UN REGISTRO!");
                    setTimeout(function() {
                        $('#error').hide("fast");
                    }, 1000);
                }
            }

            var del = function() {
                var rowId = $("#list").jqGrid('getGridParam', 'selrow');

                if (rowId) {
                    var fila = $("#list").jqGrid('getRowData', rowId);

                    var sUrl = './tblConexionServlet?accion=eliminar&id=' + fila.ID;
                    var jqxhr = $.getJSON(sUrl);

                    jqxhr.success(function(json) {
                        buscar();
                        $('#modal-message').modal('hide');

                        $('#ok').show("fast");
                        $('#ok').html("ELIMINADO CON EXITO!");
                        setTimeout(function() {
                            $('#ok').hide("fast");
                        }, 1000);
                    });

                    jqxhr.error(function() {
                        $('#error').show("fast");
                        $('#error').html("ERROR EJECUTANDO EL PROCESO!");
                        setTimeout(function() {
                            $('#error').hide("fast");
                        }, 1000);


                    });
                } else {
                    $('#error').show("fast");
                    $('#error').html("DEBE SELECCIONAR UN REGISTRO!");
                    setTimeout(function() {
                        $('#error').hide("fast");
                    }, 1000);
                }

            };

            var tblEstructura = function() {
                lst = tbl.jqGrid({
                    url: './tblConexionServlet?accion=buscar',
                    datatype: 'json',
                    mtype: 'POST',
                    colNames: [
                        'Id',
                        'Nombres',
                        'Apellido Paterno',
                        'Apellido Materno'
                    ],
                    colModel: [
                        {
                            name: 'ID',
                            index: '1'
                        },
                        {
                            name: 'NOMBRE',
                            index: '2',
                            align: 'center'
                        },
                        {
                            name: 'APE_PATERNO',
                            index: '3',
                            align: 'center'
                        },
                        {
                            name: 'APE_MATERNO',
                            index: '4',
                            align: 'center'
                        }
                    ],
                    height: 300,
                    width: 940,
                    shrinkToFit: false,
                    rowNum: 2,
                    pager: $("#divPaginado"),
                    viewrecords: true,
                    paging: true,
                    caption: 'Listado General de usuarios',
                    beforeRequest: function() {
                        //responsive_jqgrid($(".jqGrid"));
                    },
                    jsonReader: {
                        repeatitems: false,
                        id: '0',
                        root: 'lstLista',
                        total: 'numPaginaciones',
                        page: 'numPaginado',
                        records: 'numRegistros'
                    }
                });

            };

            $(document).ready(function() {
                
                tbl = $('#list');
                frm = $('#frmBusqueda');
                tblEstructura();
            });

        </script>

    </body>
</html>
