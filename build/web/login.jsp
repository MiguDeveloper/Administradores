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
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>

        <script type="text/javascript">
            $(document).ready(function(){
              console.log("Estas ejecutando JQuery");
            });
            
            function Ingreso() {
                
                var USU = $("#inputUsuario").val();
                var PWD = $("#inputPassword").val();

                $.get('./UsuarioServlet?accion=login', {inputUsuario : USU, inputPassword : PWD}, function(response) {
                    if (response.mensaje == 'aceptado') {
                        //document.location = "intranet.jsp";
                        //document.location = "../intranet.jsp?VenIncIntranet=bienvenida.jsp";
                        window.open('./usuarioLst.jsp', '_top');
                        console.log("Ingresaste");
                    } else {
                        console.log("Hola: " + response.mensaje);
                        alert("Estado: " + response.mensaje);
                    }
                });
            }

        </script>
        <div class="container">
            <div class="card card-container">
                <!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
                <img id="profile-img" class="profile-img-card" src="img/avatar-login.png" />
                <p id="profile-name" class="profile-name-card"></p>
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
