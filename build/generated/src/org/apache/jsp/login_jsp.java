package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/metas_y_css.jsp");
    _jspx_dependants.add("/includes/mensajes.jsp");
    _jspx_dependants.add("/librerias_javascript.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Ingresar al sistema</title>\n");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("<meta name=\"description\" content=\"Sistemas de administracion\">\n");
      out.write("<meta name=\"author\" content=\"Miguel chinchay\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<link href=\"plugins/bootstrap/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("<link href=\"plugins/jquery-ui/jquery-ui.min.css\" rel=\"stylesheet\">\n");
      out.write("<link href=\"http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css\" rel=\"stylesheet\">\n");
      out.write("<link href='http://fonts.googleapis.com/css?family=Righteous' rel='stylesheet' type='text/css'>\n");
      out.write("<link href=\"plugins/fancybox/jquery.fancybox.css\" rel=\"stylesheet\">\n");
      out.write("<link href=\"plugins/fullcalendar/fullcalendar.css\" rel=\"stylesheet\">\n");
      out.write("<link href=\"plugins/justified-gallery/justifiedGallery.css\" rel=\"stylesheet\">\n");
      out.write("<link href=\"css/style_v1.css\" rel=\"stylesheet\">\n");
      out.write("<link href=\"plugins/chartist/chartist.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("<!-- Estilos jqGrid -->\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tema/jquery-ui-1.8.16.custom.css\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tema/ui.jqgrid-bootstrap-ui.css\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tema/ui.jqgrid-bootstrap.css\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/jqgrid/ui.jqgrid.css\"/>\n");
      out.write("\n");
      out.write("<!-- jqGrid and jQuery (necessary for Bootstrap's JavaScript plugins) -->\n");
      out.write("<script src=\"plugins/jquery/jquery-1.11.3.min.js\"></script>\n");
      out.write("<script src=\"plugins/jqueryvalidate/jquery.validate.min.js\"></script>\n");
      out.write("<script src=\"plugins/jqueryvalidate/additional-methods.min.js\"></script>\n");
      out.write("<script src=\"plugins/jquery-ui/jquery-ui.min.js\"></script>\n");
      out.write("\n");
      out.write("<!-- jqGrid -->\n");
      out.write("<script type=\"text/javascript\" src=\"js/jqgrid/i18n/grid.locale-es.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jqgrid/jquery.jqGrid.min.js\"></script>\n");
      out.write("\n");
      out.write("<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->\n");
      out.write("<!--[if lt IE 9]>\n");
      out.write("                <script src=\"http://getbootstrap.com/docs-assets/js/html5shiv.js\"></script>\n");
      out.write("                <script src=\"http://getbootstrap.com/docs-assets/js/respond.min.js\"></script>\n");
      out.write("<![endif]-->\n");
      out.write("\n");
      out.write("\n");
      out.write("        <script type=\"text/javascript\" src=\"js/md5.pack.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/login.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            function conver_md5(campo){\n");
      out.write("                return md5(campo);\n");
      out.write("            };\n");
      out.write("            \n");
      out.write("            function Ingreso() {\n");
      out.write("\n");
      out.write("                var USU = $(\"#inputUsuario\").val();\n");
      out.write("                var PWD = $(\"#inputPassword\").val();\n");
      out.write("                var TRANS_PWD = conver_md5(PWD);\n");
      out.write("                \n");
      out.write("                $.get('./UsuarioServlet?accion=login', {inputUsuario: USU, inputPassword: TRANS_PWD}, function(response) {\n");
      out.write("                    if (response.estadoLogeo == 'aceptado') {\n");
      out.write("                        $('#modal-message').modal('hide');\n");
      out.write("\n");
      out.write("                        $('#ok').show(\"fast\");\n");
      out.write("                        $('#ok').html(response.mensajeLogeo);\n");
      out.write("                        setTimeout(function() {\n");
      out.write("                            $('#ok').hide(\"fast\");\n");
      out.write("                        }, 1000);\n");
      out.write("                        \n");
      out.write("                        window.open('./usuarioLst.jsp', '_top');\n");
      out.write("                        console.log(\"Ingresaste\");\n");
      out.write("                    } else {\n");
      out.write("\n");
      out.write("                        $('#error').show(\"fast\");\n");
      out.write("                        $('#error').html(response.mensajeLogeo);\n");
      out.write("                        setTimeout(function() {\n");
      out.write("                            $('#error').hide(\"fast\");\n");
      out.write("                        }, 1000);\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </script>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"card card-container\">\n");
      out.write("\n");
      out.write("                <img id=\"profile-img\" class=\"profile-img-card\" src=\"img/avatar-login.png\" />\n");
      out.write("                ");
      out.write("\n");
      out.write("<div class=\"col-xs-12\" style=\"margin-top:1%; margin-bottom:-1%;\">\n");
      out.write("    <div id=\"ok\" class=\"alert alert-success\" style=\"display:none;\">\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div id=\"error\" class=\"alert alert-danger\" style=\"display:none;\">\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div id=\"advertencia\" class=\"alert alert-warning\" style=\"display:none;\">\n");
      out.write("    </div>\n");
      out.write("</div>");
      out.write("\n");
      out.write("\n");
      out.write("                <form class=\"form-signin\" action=\"javascript:Ingreso();\" method=\"post\">\n");
      out.write("                    <span id=\"reauth-email\" class=\"reauth-email\"></span>\n");
      out.write("                    <input type=\"text\" id=\"inputUsuario\" name=\"inputUsuario\" class=\"form-control\" placeholder=\"Usuario\" autofocus>\n");
      out.write("                    <input type=\"password\" id=\"inputPassword\" name=\"inputPassword\" class=\"form-control\" placeholder=\"Password\">\n");
      out.write("\n");
      out.write("                    <button class=\"btn btn-lg btn-primary btn-block btn-signin\" type=\"submit\">Ingresar</button>\n");
      out.write("                </form><!-- /form -->\n");
      out.write("                <a href=\"#\" class=\"forgot-password\">\n");
      out.write("                    olvidaste tu clave?\n");
      out.write("                </a>\n");
      out.write("\n");
      out.write("            </div><!-- /card-container -->\n");
      out.write("        </div><!-- /container -->\n");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("<!-- Include all compiled plugins (below), or include individual files as needed -->\n");
      out.write("<script src=\"plugins/bootstrap/bootstrap.min.js\"></script>\n");
      out.write("<script src=\"plugins/justified-gallery/jquery.justifiedGallery.min.js\"></script>\n");
      out.write("<script src=\"plugins/tinymce/tinymce.min.js\"></script>\n");
      out.write("<script src=\"plugins/tinymce/jquery.tinymce.min.js\"></script>\n");
      out.write("<!-- All functions for this theme + document.ready processing -->\n");
      out.write("\n");
      out.write("\n");
      out.write("<script src=\"js/devoops.js\"></script>\n");
      out.write("<script src=\"js/funciones.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- Modal -->\n");
      out.write("<div class=\"modal \" id=\"myModal22\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n");
      out.write("    <div class=\"modal-dialog\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n");
      out.write("                <h4 class=\"modal-title\" id=\"myModalLabel\"></h4>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\" id=\"modal-body-gen\">\n");
      out.write("                  \n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <!-- /.modal-content -->\n");
      out.write("    </div>\n");
      out.write("    <!-- /.modal-dialog -->\n");
      out.write("</div>\n");
      out.write("<!-- /.modal -->\n");
      out.write("\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
