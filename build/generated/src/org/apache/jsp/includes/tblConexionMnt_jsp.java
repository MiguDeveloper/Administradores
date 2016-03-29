package org.apache.jsp.includes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Bean.tblConexionBean;

public final class tblConexionMnt_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/includes/css.jsp");
    _jspx_dependants.add("/includes/js.jsp");
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("<meta name=\"description\" content=\"description\">\n");
      out.write("<meta name=\"author\" content=\"DevOOPS\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\n");
      out.write("<link href=\"../plugins/bootstrap/bootstrap.css\" rel=\"stylesheet\">\n");
      out.write("<link href=\"../plugins/jquery-ui/jquery-ui.min.css\" rel=\"stylesheet\">\n");
      out.write("<link href=\"http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css\" rel=\"stylesheet\">\n");
      out.write("<link href='http://fonts.googleapis.com/css?family=Righteous' rel='stylesheet' type='text/css'>\n");
      out.write("<link href=\"../plugins/fullcalendar/fullcalendar.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("\n");
      out.write("<script src=\"../plugins/jquery/jquery-1.11.3.min.js\"></script>\n");
      out.write("\n");
      out.write("<!-- Include all compiled plugins (below), or include individual files as needed -->\n");
      out.write("<script src=\"../plugins/bootstrap/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("<script src=\"../plugins/jqueryvalidate/jquery.validate.min.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

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
        
      out.write("\n");
      out.write("\n");
      out.write("        <form class=\"form-horizontal\" id=\"frmClientes\">\n");
      out.write("            <input type=\"hidden\" value=\"");
      out.print(tipoAccion);
      out.write("\" id=\"txtAccion\">\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label for=\"txtNombre\" class=\"col-xs-3 control-label\">Nombres</label>\n");
      out.write("                <div class=\"col-xs-9\">\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"txtNombre\" value=\"");
      out.print(Nombres);
      out.write("\" placeholder=\"nombres\">\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label for=\"txtApePaterno\" class=\"col-xs-3 control-label\">Apellido Paterno</label>\n");
      out.write("                <div class=\"col-xs-9\">\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"txtApePaterno\" value=\"");
      out.print(apePaterno);
      out.write("\" placeholder=\"apellido paterno\">\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <label for=\"txtApeMaterno\" class=\"col-xs-3 control-label\">Apellido Materno</label>\n");
      out.write("                <div class=\"col-xs-9\">\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"txtApeMaterno\" value=\"");
      out.print(apeMaterno);
      out.write("\" placeholder=\"apellido materno\">\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <div class=\"col-xs-12\"> \n");
      out.write("                    <button type=\"submit\" class=\"btn btn-default\">Aceptar</button>\n");
      out.write("                    <button type=\"button\" onclick=\"BtnCancelar()\" class=\"btn btn-default\">Cancelar</button>\n");
      out.write("                </div>\n");
      out.write("            </div>  \n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<script src=\"../js/devoops.js\"></script>\n");
      out.write("<script src=\"../js/funciones.js\"></script>");
      out.write("\n");
      out.write("\n");
      out.write("        <script type='text/javascript'>\n");
      out.write("\n");
      out.write("            $(document).ready(function() {\n");
      out.write("                $(\"#frmClientes\").validate({\n");
      out.write("                    rules: {\n");
      out.write("                        txtNombre: {required: true},\n");
      out.write("                        txtApePaterno: {required: true},\n");
      out.write("                        txtApeMaterno: {required: true}\n");
      out.write("                    },\n");
      out.write("                    messages: {\n");
      out.write("                        txtNombre: 'El nombre es obligatorio',\n");
      out.write("                        txtApePaterno: 'El A. paterno es obligatorio',\n");
      out.write("                        txtApeMaterno: 'El A. materno es obligatorio'\n");
      out.write("                    },\n");
      out.write("                    submitHandler: function(form) {\n");
      out.write("                        $.ajax({\n");
      out.write("                            type: 'POST',\n");
      out.write("                            url: '../tblConexionServlet',\n");
      out.write("                            dataType: 'json',\n");
      out.write("                            data: {\n");
      out.write("                                accion: $(\"#txtAccion\").val(),\n");
      out.write("                                id: ");
      out.print(id);
      out.write(",\n");
      out.write("                                txtNombre: $(\"#txtNombre\").val(),\n");
      out.write("                                txtApe_paterno: $(\"#txtApePaterno\").val(),\n");
      out.write("                                txtApe_materno: $(\"#txtApeMaterno\").val()\n");
      out.write("                            },\n");
      out.write("                            success: function(response) {\n");
      out.write("                                if (response.estadoOperacion == '1' && response.tipo_proceso == 'insertar') {\n");
      out.write("                                    $('#ok').show(\"fast\");\n");
      out.write("                                    $('#ok').html(\"GUARDADO CON EXITO!\");\n");
      out.write("                                    setTimeout(function() {\n");
      out.write("                                        $('#ok').hide(\"fast\");\n");
      out.write("                                    }, 1000);\n");
      out.write("                                    $('#frmClientes')[0].reset();\n");
      out.write("                                    window.parent.buscar();\n");
      out.write("                                } else if (response.estadoOperacion == '1' && response.tipo_proceso == 'actualizar') {\n");
      out.write("                                    $('#ok').show(\"fast\");\n");
      out.write("                                    $('#ok').html(\"MODIFICADO CON EXITO!\");\n");
      out.write("                                    setTimeout(function() {\n");
      out.write("                                        $('#ok').hide(\"fast\");\n");
      out.write("                                    }, 1000);\n");
      out.write("                                    $('#frmClientes')[0].reset();\n");
      out.write("                                    window.parent.buscar();\n");
      out.write("                                } else {\n");
      out.write("                                    $('#error').show(\"fast\");\n");
      out.write("                                    $('#error').html(\"ERROR!\");\n");
      out.write("                                    setTimeout(function() {\n");
      out.write("                                        BtnCancelar();\n");
      out.write("                                    }, 1000);\n");
      out.write("\n");
      out.write("                                }\n");
      out.write("                            }\n");
      out.write("                        });\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
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
