package Servlet;

import Bean.UsuarioBean;
import Service.UsuarioService;
import ServiceImpl.UsuarioServiceImpl;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.log4j.Logger;
import util.ResultadoJson;

/**
 *
 * @author miguelchinchay
 */
public class UsuarioServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(UsuarioServlet.class.getName());
    private UsuarioService usuarioService;
    private UsuarioBean usuario;

    private int flgOperacion;
    private Gson gson = null;
    private PrintWriter out = null;
    private HttpSession sesion;
    private String mensaje;
    private String estadoOperacion;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        logger.info("Requerimiento: " + accion);

        if (accion != null) {
            if (accion.equals("insertar")) {
                insertar(request, response);
                return;
            }
            if (accion.equals("buscar")) {
                buscar(request, response);
                return;
            }
            if (accion.equals("actualizar")) {
                actualizar(request, response);
                return;
            }
            if (accion.equals("eliminar")) {
                eliminar(request, response);
                return;
            }
            if (accion.equals("obtenerPorId")) {
                obtenerPorId(request, response);
                return;
            }
            if (accion.equals("login")) {
                login(request, response);
                return;
            }
            if (accion.equals("cerrarSesion")) {
                cerrarSesion(request, response);
                return;
            }
            if (accion.equals("ImportaExcel")) {
                importaExcel(request, response);
                return;
            }
            if(accion.equals("guardarExcel")){
                guardarExcel(request, response);
                return;
            }
            if (accion.equals("eliminarSesion")) {
                eliminarSesion(request, response);
                return;
            }
        }

    }

    protected void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Insertar");
        sesion = request.getSession();

        sesion.removeAttribute("actualizarUsuario");

        String user = request.getParameter("txtUsuario") == null ? "" : request.getParameter("txtUsuario");
        String pwd = request.getParameter("txtPwd") == null ? "" : request.getParameter("txtPwd");
        String nombres = request.getParameter("txtNombres") == null ? "" : request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos") == null ? "" : request.getParameter("txtApellidos");
        String email = request.getParameter("txtEmail") == null ? "" : request.getParameter("txtEmail");
        int estado = 1;

        try {
            usuario = new UsuarioBean();
            usuario.setUSUARIO(user);
            usuario.setPWD(pwd);
            usuario.setNOMBRES(nombres);
            usuario.setAPELLIDOS(apellidos);
            usuario.setEMAIL(email);
            usuario.setESTADO(estado);

            usuarioService = new UsuarioServiceImpl();
            flgOperacion = usuarioService.insertar(usuario);

            if (flgOperacion > 0) {
                mensaje = "Se creo el usuario con éxito";
                estadoOperacion = "1";
            } else {
                mensaje = "Error no pudo crearse el usuario";
                estadoOperacion = "0";
            }

            /* -----RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR----- */
            String tipo_proceso = "insertar";

            Map<String, String> Mensaje = new LinkedHashMap<String, String>();
            Mensaje.put("mensaje", mensaje);
            Mensaje.put("estadoOperacion", estadoOperacion);
            Mensaje.put("tipo_proceso", tipo_proceso);

            String json_data = null;
            json_data = new Gson().toJson(Mensaje);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json_data);

        } catch (Exception e) {
            logger.error("Error al insertar usuario: " + e.getMessage());
        }

    }

    protected void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Buscar");

        sesion = request.getSession();
        sesion.removeAttribute("actualizarUsuario");

        String usuario = request.getParameter("txtUsuario") == null ? "" : request.getParameter("txtUsuario");

        List<UsuarioBean> lstUsuario = null;
        ResultadoJson respuesta = new ResultadoJson();
        gson = new Gson();
        out = response.getWriter();

        try {
            usuarioService = new UsuarioServiceImpl();
            lstUsuario = usuarioService.buscar(usuario);
            respuesta.setLstLista(lstUsuario);

        } catch (Exception e) {
            logger.error("Error al insertar: " + e.getMessage());
        } finally {
            out.print(gson.toJson(respuesta));
            out.close();
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Actualizando");

        sesion = request.getSession();
        sesion.removeAttribute("actualizarUsuario");

        int id = Integer.parseInt(request.getParameter("txtId"));
        String user = request.getParameter("txtUsuario") == null ? "" : request.getParameter("txtUsuario");
        String pwd = request.getParameter("txtPwd") == null ? "" : request.getParameter("txtPwd");
        String nombres = request.getParameter("txtNombres") == null ? "" : request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos") == null ? "" : request.getParameter("txtApellidos");
        String email = request.getParameter("txtEmail") == null ? "" : request.getParameter("txtEmail");
        int estado = 1;

        try {
            usuario = new UsuarioBean();
            usuario.setID(id);
            usuario.setUSUARIO(user);
            usuario.setPWD(pwd);
            usuario.setNOMBRES(nombres);
            usuario.setAPELLIDOS(apellidos);
            usuario.setEMAIL(email);
            usuario.setESTADO(estado);

            usuarioService = new UsuarioServiceImpl();
            flgOperacion = usuarioService.actualizar(usuario);

            if (flgOperacion > 0) {
                mensaje = "Se actualizo el usuario con éxito";
                estadoOperacion = "1";
            } else {
                mensaje = "Error no pudo actualizarse el usuario";
                estadoOperacion = "0";
            }

            /* -----RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR----- */
            String tipo_proceso = "actualizar";

            Map<String, String> Mensaje = new LinkedHashMap<String, String>();
            Mensaje.put("mensaje", mensaje);
            Mensaje.put("estadoOperacion", estadoOperacion);
            Mensaje.put("tipo_proceso", tipo_proceso);

            String json_data = null;
            json_data = new Gson().toJson(Mensaje);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json_data);

        } catch (Exception e) {
            logger.error("Error al actualizar: " + e.getMessage());
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Eliminar");

        sesion = request.getSession();
        sesion.removeAttribute("actualizarUsuario");

        int id = Integer.parseInt(request.getParameter("txtId"));
        gson = new Gson();
        out = response.getWriter();

        try {
            usuarioService = new UsuarioServiceImpl();
            flgOperacion = usuarioService.eliminar(id);
            if (flgOperacion > 0) {
                mensaje = "Se elimino usuario con exito";
            } else {
                mensaje = "Error no pudo eliminarse el usuario";
            }
        } catch (Exception e) {
            logger.error("Error al actualizar: " + e.getMessage());
        } finally {
            out.print(gson.toJson(mensaje));
            out.close();
        }
    }

    protected void obtenerPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Obtener por id");
        sesion = request.getSession();

        int id = Integer.parseInt(request.getParameter("txtID"));
        usuario = new UsuarioBean();

        try {
            usuarioService = new UsuarioServiceImpl();
            usuario = usuarioService.obtenerPorId(id);
            sesion.removeAttribute("actualizarUsuario");
            sesion.setAttribute("actualizarUsuario", usuario);

            /* -----RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR----- */
            Map<String, String> Mensaje = new LinkedHashMap<String, String>();
            Mensaje.put("mensaje", "usuarioMnt.jsp");
            String json_data = null;
            json_data = new Gson().toJson(Mensaje);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json_data);

        } catch (Exception e) {
            logger.error("Error al obtener por id" + e.getMessage());
        }
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Logeandose");
        sesion = request.getSession();

        sesion.removeAttribute("actualizarUsuario");
        sesion.removeAttribute("login");

        String user = request.getParameter("inputUsuario") == null ? "" : request.getParameter("inputUsuario");
        String pwd = request.getParameter("inputPassword") == null ? "" : request.getParameter("inputPassword");

        usuario = null;

        try {
            usuarioService = new UsuarioServiceImpl();
            usuario = usuarioService.login_usuario(user, pwd);

            String estadoLogin = "";
            String msgLogeo = "";

            if (usuario == null) {
                estadoLogin = "denegado";
                msgLogeo = "Usuario o contraseña invalidos";
            } else {
                estadoLogin = "aceptado";
                msgLogeo = "Usuario valido";
                sesion.removeAttribute("login");
                sesion.setAttribute("login", usuario);
            }

            /* -----RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR----- */
            Map<String, String> Mensaje = new LinkedHashMap<String, String>();
            Mensaje.put("estadoLogeo", estadoLogin);
            Mensaje.put("mensajeLogeo", msgLogeo);
            String json_data = null;
            json_data = new Gson().toJson(Mensaje);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json_data);

        } catch (Exception e) {
            logger.error("Error al hacer login: " + e.getMessage());
        }
    }

    protected void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Cerrando sesion");
        sesion = request.getSession();

        try {
            sesion.removeAttribute("login");
            sesion.invalidate();
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            logger.error("Error al cerrar sesion: " + e.getMessage());
        }

    }

    protected void importaExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Carga masica");
        sesion = request.getSession();
        sesion.removeAttribute("actualizarUsuario");
        
        String UPLOAD_DIRECTORY = getServletContext().getRealPath("includes/archivos");
        String ruta = null;
        String msg = null;
        
        ServletOutputStream salida = response.getOutputStream();
        salida.println(UPLOAD_DIRECTORY + "      ");
        salida.println("antes del if   ");
        
        if(ServletFileUpload.isMultipartContent(request)){
            salida.println("despues del if ");
            try{
                salida.println("despues del try cacht   ");
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);
                salida.println("Antes de for..");
                for (FileItem item : multiparts) {
                    salida.println("despues del for   ");
                    if (!item.isFormField()) {
                        salida.println("dentro del for despues del if  ");
                        String name = new File(item.getName()).getName();
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                        ruta = UPLOAD_DIRECTORY + File.separator + name;
                        salida.println("   ruta real   " + ruta + "    ");
                    }
                    salida.println("despues del if dentro del for   ");
                }
                msg = "Lista de usuarios cargo con Exito";
                salida.println(msg);
            }catch(Exception e){
                msg = "Error " + e;
                salida.println(msg);
            }
        }else{
            msg = "No se puede cargar";
            salida.println(msg);
        }
        
        sesion = request.getSession();
        sesion.removeAttribute("usuariosImportados");
        sesion.removeAttribute("msgCargaMasiva");
        ////////////////////////////////////////////////////////
        sesion.setAttribute("msgCargaMasiva", msg);
        sesion.setAttribute("usuariosImportados", readExcel(ruta));
        response.sendRedirect("lee-importado.jsp");
        
    }
    
    public List<UsuarioBean> readExcel(String excel_file) {
        List<UsuarioBean> lstUsuarios = null;
        lstUsuarios = new ArrayList<UsuarioBean>();
        try {
            Workbook workbook = Workbook.getWorkbook(new File(excel_file));
            //Gets the sheet
            Sheet sheet = workbook.getSheet(0);//el cero indica que es la hoja nro 1

            for (int x = 1; x < sheet.getRows(); x++) {
                jxl.Cell user = sheet.getCell(0, x);
                jxl.Cell pwd = sheet.getCell(1, x);
                jxl.Cell nombre = sheet.getCell(2, x);
                jxl.Cell apellido = sheet.getCell(3, x);
                jxl.Cell email = sheet.getCell(4, x);

                usuario = new UsuarioBean();
                
                usuario.setUSUARIO(user.getContents());
                usuario.setPWD(pwd.getContents());
                usuario.setNOMBRES(nombre.getContents());
                usuario.setAPELLIDOS(apellido.getContents());
                usuario.setEMAIL(email.getContents());
                usuario.setESTADO(1);
                lstUsuarios.add(usuario);
            }
            workbook.close();
        } catch (Exception e) {
            System.out.println("Error readExcel ->" + e);
        }
        return lstUsuarios;
    }
    
    protected void guardarExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Guardando excel");
        sesion = request.getSession();
        sesion.removeAttribute("actualizarUsuario");
        
        try{
            if(sesion.getAttribute("usuariosImportados") != null){
                List<UsuarioBean> lstUsuarios = (List<UsuarioBean>) sesion.getAttribute("usuariosImportados");
                for(int i=0; i<lstUsuarios.size(); i++){
                    usuario = new UsuarioBean();
                    usuario = lstUsuarios.get(i);
                    usuario.setESTADO(1);
                    
                    usuarioService = new UsuarioServiceImpl();
                    flgOperacion = usuarioService.insertar(usuario);
                }
            }
            sesion = request.getSession();
            sesion.removeAttribute("usuariosImportados");
            sesion.removeAttribute("msgCargaMasiva");
            sesion.setAttribute("msgCargaMasiva", "<strong>Listo</strong>, usuarios subidos correctamente");
            if(flgOperacion>0){
                mensaje = "Usuarios subidos correctamente";
            }else{
                mensaje = "Error al subir los usuarios";
            }
            response.sendRedirect("carga-masiva-correcta.jsp");
        }catch(Exception e){
            logger.error("Error al guardar excel: " + e.getMessage());
        }
    }

    protected void eliminarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("eliminado sesion");
        sesion = request.getSession();

        sesion.removeAttribute("actualizarUsuario");

        /* -----RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR----- */
        Map<String, String> Mensaje = new LinkedHashMap<String, String>();
        Mensaje.put("mensaje", "usuarioMnt.jsp");
        String json_data = null;
        json_data = new Gson().toJson(Mensaje);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json_data);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
