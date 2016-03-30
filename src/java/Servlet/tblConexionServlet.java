

package Servlet;

import Bean.tblConexionBean;
import Service.tblConexionService;
import ServiceImpl.tblConexionServiceImpl;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import util.ResultadoJson;

/**
 *
 * @author miguelchinchay
 */
public class tblConexionServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(tblConexionServlet.class.getName());
    private tblConexionService tblConexionService;
    private tblConexionBean tblConexion;
    
    private int flgOperacion;
    private Gson gson = null;
    private PrintWriter out = null;
    private HttpSession sesion;
    private String mensaje;
    private String estadoOperacion;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
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
            if (accion.equals("obtenerPorId")) {
                obtenerPorId(request, response);
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
            if (accion.equals("eliminarSesion")){
                eliminarSesion(request, response);
                return;
            }
        }
    }
    
    protected void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Insertando tblConexion");
        sesion = request.getSession();
        
        String nombres = request.getParameter("txtNombre") == null ? "" : request.getParameter("txtNombre");
        String ape_paterno = request.getParameter("txtApe_paterno") == null ? "" : request.getParameter("txtApe_paterno");
        String ape_materno = request.getParameter("txtApe_materno") == null ? "" : request.getParameter("txtApe_materno");
        
        try{
            tblConexion = new tblConexionBean();
            tblConexion.setNOMBRE(nombres);
            tblConexion.setAPE_PATERNO(ape_paterno);
            tblConexion.setAPE_MATERNO(ape_materno);
            
            tblConexionService = new tblConexionServiceImpl();
            flgOperacion = tblConexionService.insertar(tblConexion);
            
            if(flgOperacion>0){
                mensaje = "Se creo con éxito el usuario";
                estadoOperacion = "1";
            }else{
                mensaje = "Error no pudo crearse el usuario";
                estadoOperacion = "0";
            }
            
            sesion.removeAttribute("tblConexionActualizar");
            
            //--------------------------RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR-----------------------
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
            
        }catch(Exception e){
            logger.error("Error al insertar cliente " + e.getMessage());
        }
        
        
    }
    
    protected void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Buscando en tblConexion");
        sesion = request.getSession();
        
        String ape_paterno = request.getParameter("txtApe_Paterno") == null ? "" : request.getParameter("txtApe_Paterno");
        List<tblConexionBean> lsttblConexion = new ArrayList<tblConexionBean>();
        
        int numPagina = Integer.parseInt(request.getParameter("page"));
        int numRegistros = Integer.parseInt(request.getParameter("rows"));
        
        int numPaginaInicio = numPagina * numRegistros - numRegistros;
        int numPaginafin = numPagina * numRegistros;
        
        int totalRegistros = 0;
        
        gson = new Gson();
        out = response.getWriter();
        ResultadoJson resultado = new ResultadoJson();
        
        try{
            tblConexionService = new tblConexionServiceImpl();
            totalRegistros = tblConexionService.totalFilasTbl();
            
            lsttblConexion = tblConexionService.buscar(ape_paterno,numPaginaInicio,numPaginafin);
            
            resultado.setLstLista(lsttblConexion);
            resultado.setNumPaginado(numPagina);
            resultado.setNumRegistros(totalRegistros);
            resultado.setNumPaginaciones((int) Math.floor(resultado.getNumRegistros() / numRegistros));
            
            int iRes = totalRegistros % numRegistros;
            int iNu = 0;
            
            if(iRes <= 0){
                iNu = resultado.getNumRegistros() / numRegistros;
                resultado.setNumPaginaciones(iNu);
            }else{
                iNu = ((int) Math.floor(totalRegistros / numRegistros));
                resultado.setNumPaginaciones(iNu+1);
            }
            sesion.removeAttribute("tblConexionActualizar");
            
            
        }catch(Exception e){
            logger.error("Error al buscar: " + e.getMessage());
        }finally{
            out.print(gson.toJson(resultado));
            out.close();
        }
    }

    protected void obtenerPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Obteniendo por ID");
        sesion = request.getSession();
        
        int id = Integer.parseInt(request.getParameter("id") == null ? "1" : request.getParameter("id"));
        
        try{
            tblConexion = new tblConexionBean();
            
            tblConexionService = new tblConexionServiceImpl();
            tblConexion = tblConexionService.obtnerPorId(id);
            
            sesion.removeAttribute("tblConexionActualizar");
            sesion.setAttribute("tblConexionActualizar", tblConexion);
            
            //--------------------------RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR-----------------------
            Map<String, String> Mensaje = new LinkedHashMap<String, String>();
            Mensaje.put("mensaje", "tblConexionMnt.jsp");
            String json_data = null;
            json_data = new Gson().toJson(Mensaje);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json_data);
            
        }catch(Exception e){
            logger.error("Error al obtener por id: " + e.getMessage());
        }
        
    }
    
    protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Actualizando tblConexion");
        sesion = request.getSession();
        
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("txtNombre") == null ? "" : request.getParameter("txtNombre");
        String ape_paterno = request.getParameter("txtApe_paterno") == null ? "" : request.getParameter("txtApe_paterno");
        String ape_materno = request.getParameter("txtApe_materno") == null ? "" : request.getParameter("txtApe_materno");
        
        try{
            tblConexion = new tblConexionBean();
            tblConexion.setID(id);
            tblConexion.setNOMBRE(nombre);
            tblConexion.setAPE_PATERNO(ape_paterno);
            tblConexion.setAPE_MATERNO(ape_materno);
            
            tblConexionService = new tblConexionServiceImpl();
            
            flgOperacion = tblConexionService.actualizar(tblConexion);
            String estadoOperacion = "";
            
            if(flgOperacion>0){
                mensaje = "Se actualizo con éxito la tblConexion";
                estadoOperacion = "1";
            }else{
                mensaje = "Error no pudo actualizarse la tabla tblConexion";
                estadoOperacion = "0";
            }
            
            sesion.removeAttribute("tblConexionActualizar");
            //--------------------------RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR-----------------------
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
            
        }catch(Exception e){
            logger.error("Error al actualizar " + e.getMessage());
        }
    }
    
    protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Eliminando");
        sesion = request.getSession();
        
        int id = Integer.parseInt(request.getParameter("id") == null ? "1" : request.getParameter("id"));
        
        try{
            tblConexionService = new tblConexionServiceImpl();
            flgOperacion = tblConexionService.eliminar(id);
            if(flgOperacion>0){
                mensaje = "Se elimino con éxito";
                estadoOperacion = "1";
            }else{
                mensaje = "Error al eliminar";
                estadoOperacion = "0";
            }
            sesion.removeAttribute("tblConexionActualizar");
            
            //--------------------------RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR-----------------------
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
        }catch(Exception e){
            
        }
    }
    
    protected void eliminarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("eliminando sesion");
        sesion.removeAttribute("tblConexionActualizar");
        
        //--------------------------RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR-----------------------
        Map<String, String> Mensaje = new LinkedHashMap<String, String>();
        Mensaje.put("mensaje", "tblConexionMnt.jsp");
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
