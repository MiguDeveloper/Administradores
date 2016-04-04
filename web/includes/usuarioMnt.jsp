
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Bean.UsuarioBean" %>
<%
    if(session.getAttribute("login") == null){
        response.sendRedirect("login.jsp");
    }
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de usuarios</title>
        <%@include file="css.jsp" %>
    </head>
    <body>
        <%
            UsuarioBean usuario = (UsuarioBean) session.getAttribute("actualizarUsuario");
            usuario = usuario == null ? new UsuarioBean() : usuario;

            int id = usuario.getID();
            String user = usuario.getUSUARIO() == null ? "" : usuario.getUSUARIO();
            String pwd = usuario.getPWD() == null ? "" : usuario.getPWD();
            String nombres = usuario.getNOMBRES() == null ? "" : usuario.getNOMBRES();
            String apellidos = usuario.getAPELLIDOS() == null ? "" : usuario.getAPELLIDOS();
            String email = usuario.getEMAIL() == null ? "" : usuario.getEMAIL();

            String tipoAccion = "";
            
            if (session.getAttribute("actualizarUsuario") != null) {
                tipoAccion = "actualizar";
            } else {
                tipoAccion = "insertar";
            }
        %>
        <%@include file="mensajes.jsp" %>

        <form class="form-horizontal" id="frmUsuarios">
            <input type="hidden" value="<%=tipoAccion%>" id="txtAccion">

            <div class="form-group">
                <label for="txtUser" class="col-xs-3 control-label">User</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="txtUser" name="txtUser" value="<%=user%>" placeholder="usuario">
                </div>
            </div>
            <div class="form-group">
                <label for="txtPwd" class="col-xs-3 control-label">Password</label>
                <div class="col-xs-9">
                    <input type="password" class="form-control" id="txtPwd" name="txtPwd" value="<%=pwd%>" placeholder="password">
                </div>
            </div>
            <div class="form-group">
                <label for="txtNombre" class="col-xs-3 control-label">Nombres</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="txtNombre" name="txtNombre" value="<%=nombres%>" placeholder="nombres">
                </div>
            </div>
            <div class="form-group">
                <label for="txtApellidos" class="col-xs-3 control-label">Apellidos</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="txtApellidos" name="txtApellidos" value="<%=apellidos%>" placeholder="apellidos">
                </div>
            </div>
            <div class="form-group">
                <label for="txtEmail" class="col-xs-3 control-label">Email</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="txtEmail" name="txtEmail" value="<%=email%>" placeholder="email">
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
            function BtnCancelar() {
                window.parent.cierra();
            }

            $(document).ready(function() {
                $("#frmUsuarios").validate({
                    rules: {
                        txtUser : {required: true},
                        txtPwd : {required: true},
                        txtNombre : {required: true},
                        txtApellidos : {required: true},
                        txtEmail : {required: true, email: true}
                    },
                    messages: {
                        txtUser : 'El usuario es obligatorio',
                        txtPwd : 'El password es obligatorio',
                        txtNombre : 'El nombre es obligatorio',
                        txtApellidos : 'El A. paterno es obligatorio',
                        txtEmail : {required: 'El email es obligatorio', email: 'Ingrese un email valido'}
                    },
                    submitHandler: function(form) {
                        $.ajax({
                            type: 'POST',
                            url: '../UsuarioServlet',
                            dataType: 'json',
                            data: {
                                accion: $("#txtAccion").val(),
                                txtId : <%=id%>,
                                txtUsuario : $("#txtUser").val(),
                                txtPwd : $("#txtPwd").val(),
                                txtNombres : $("#txtNombre").val(),
                                txtApellidos : $("#txtApellidos").val(),
                                txtEmail : $("#txtEmail").val(),
                            },
                            success: function(response) {
                                if (response.estadoOperacion == '1' && response.tipo_proceso == 'insertar') {
                                    $('#ok').show("fast");
                                    $('#ok').html("GUARDADO CON EXITO!");
                                    setTimeout(function() {
                                        $('#ok').hide("fast");
                                    }, 1000);
                                    window.parent.buscar();
                                } else if (response.estadoOperacion == '1' && response.tipo_proceso == 'actualizar') {
                                    $('#ok').show("fast");
                                    $('#ok').html("MODIFICADO CON EXITO!");
                                    setTimeout(function() {
                                        $('#ok').hide("fast");
                                    }, 1000);
                                    
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
