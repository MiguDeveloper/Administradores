<%-- 
    Document   : login
    Created on : 01-abr-2016, 14:15:45
    Author     : miguelchinchay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingresar al sistema</title>

        <%@include file="metas_y_css.jsp" %>
        <script type="text/javascript" src="js/md5.pack.js"></script>
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>

        <script type="text/javascript">
            function conver_md5(campo){
                return md5(campo);
            };
            
            function Ingreso() {

                var USU = $("#inputUsuario").val();
                var PWD = $("#inputPassword").val();
                var TRANS_PWD = conver_md5(PWD);
                
                $.get('./UsuarioServlet?accion=login', {inputUsuario: USU, inputPassword: TRANS_PWD}, function(response) {
                    if (response.estadoLogeo == 'aceptado') {
                        $('#modal-message').modal('hide');

                        $('#ok').show("fast");
                        $('#ok').html(response.mensajeLogeo);
                        setTimeout(function() {
                            $('#ok').hide("fast");
                        }, 1000);
                        
                        window.open('./usuarioLst.jsp', '_top');
                        console.log("Ingresaste");
                    } else {

                        $('#error').show("fast");
                        $('#error').html(response.mensajeLogeo);
                        setTimeout(function() {
                            $('#error').hide("fast");
                        }, 1000);
                    }
                });
            }

        </script>
        <div class="container">
            <div class="card card-container">

                <img id="profile-img" class="profile-img-card" src="img/avatar-login.png" />
                <%@include file="includes/mensajes.jsp" %>

                <form class="form-signin" action="javascript:Ingreso();" method="post">
                    <span id="reauth-email" class="reauth-email"></span>
                    <input type="text" id="inputUsuario" name="inputUsuario" class="form-control" placeholder="Usuario" autofocus>
                    <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password">

                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Ingresar</button>
                </form><!-- /form -->
                <a href="#" class="forgot-password">
                    olvidaste tu clave?
                </a>

            </div><!-- /card-container -->
        </div><!-- /container -->

        <%@include file="librerias_javascript.jsp" %>

    </body>
</html>
