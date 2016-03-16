

package Servlet;

import Bean.tblConexionBean;
import Service.tblConexionService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

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
    private HttpSession session;
    private String mensaje;
    
    
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
        
    }
    
    protected void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void obtenerPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    protected void eliminarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
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
