<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Bean.tblConexionBean" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%@include file="css.jsp" %>

    </head>
    <body>
        <%
            tblConexionBean tblConexion = (tblConexionBean) session.getAttribute("tblConexionActualizar");
            tblConexion = tblConexion == null ? new tblConexionBean() : tblConexion;

            int id = tblConexion.getID();
            String Nombres = tblConexion.getNOMBRE() == null ? "" : tblConexion.getNOMBRE();
            String apePaterno = tblConexion.getAPE_PATERNO() == null ? "" : tblConexion.getAPE_PATERNO();
            String apeMaterno = tblConexion.getAPE_MATERNO() == null ? "" : tblConexion.getAPE_MATERNO();

            String tipoAccion = "";
            if (session.getAttribute("tblConexionActualizar") != null) {
                tipoAccion = "actualizar";
            } else {
                tipoAccion = "insertar";
            }
        %>

        <form class="form-horizontal" id="frmClientes">
            <input type="hidden" value="<%=tipoAccion%>" id="txtAccion">
            <div class="form-group">
                <label for="txtNombre" class="col-xs-3 control-label">Nombres</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="txtNombre" value="<%=Nombres%>" placeholder="nombres">
                </div>
            </div>
            <div class="form-group">
                <label for="txtApePaterno" class="col-xs-3 control-label">Apellido Paterno</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="txtApePaterno" value="<%=apePaterno%>" placeholder="apellido paterno">
                </div>
            </div>
            <div class="form-group">
                <label for="txtApeMaterno" class="col-xs-3 control-label">Apellido Materno</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="txtApeMaterno" value="<%=apeMaterno%>" placeholder="apellido materno">
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12"> 
                    <button type="submit" class="btn btn-default">Aceptar</button>
                    <button type="button" onclick="BtnCancelar()" class="btn btn-default">Cancelar</button>
                </div>
            </div>  
        </form>


        <%@include file="js.jsp" %>

        <script type='text/javascript'>

            $(document).ready(function() {
                $("#frmClientes").validate({
                    rules: {
                        txtNombre: {required: true},
                        txtApePaterno: {required: true},
                        txtApeMaterno: {required: true}
                    },
                    messages: {
                        txtNombre: 'El nombre es obligatorio',
                        txtApePaterno: 'El A. paterno es obligatorio',
                        txtApeMaterno: 'El A. materno es obligatorio'
                    },
                    submitHandler: function(form) {
                        $.ajax({
                            type: 'POST',
                            url: '../tblConexionServlet',
                            dataType: 'json',
                            data: {
                                accion: $("#txtAccion").val(),
                                id: <%=id%>,
                                txtNombre: $("#txtNombre").val(),
                                txtApe_paterno: $("#txtApePaterno").val(),
                                txtApe_materno: $("#txtApeMaterno").val()
                            },
                            success: function(response) {
                                if (response.estadoOperacion == '1' && response.tipo_proceso == 'insertar') {
                                    $('#ok').show("fast");
                                    $('#ok').html("GUARDADO CON EXITO!");
                                    setTimeout(function() {
                                        $('#ok').hide("fast");
                                    }, 1000);
                                    $('#frmClientes')[0].reset();
                                    window.parent.buscar();
                                } else if (response.estadoOperacion == '1' && response.tipo_proceso == 'actualizar') {
                                    $('#ok').show("fast");
                                    $('#ok').html("MODIFICADO CON EXITO!");
                                    setTimeout(function() {
                                        $('#ok').hide("fast");
                                    }, 1000);
                                    $('#frmClientes')[0].reset();
                                    window.parent.buscar();
                                } else {
                                    $('#error').show("fast");
                                    $('#error').html("ERROR!");
                                    setTimeout(function() {
                                        BtnCancelar();
                                    }, 1000);

                                }
                            }
                        });
                    }
                });
            });
        </script>
    </body>
</html>



