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
        
    </head>
    <body>
        <div class="container-fluid">
            <div id="page-login" class="row">
                <div class="col-xs-12 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
                    <div class="text-right">
                        <a href="page_register_v1.html" class="txt-default">Need an account?</a>
                    </div>
                    <div class="box">
                        <div class="box-content">
                            <div class="text-center">
                                <h3 class="page-header">Lesimm login</h3>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Username</label>
                                <input type="text" class="form-control" name="username" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">Password</label>
                                <input type="password" class="form-control" name="password" />
                            </div>
                            <div class="text-center">
                                <a href="../index_v1.html" class="btn btn-primary">Sign in</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <%@include file="librerias_javascript.jsp" %>
        
    </body>
</html>
