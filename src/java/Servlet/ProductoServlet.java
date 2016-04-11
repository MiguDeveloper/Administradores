package Servlet;

import Bean.ProductoBean;
import Service.ProductoService;
import ServiceImpl.ProductoServiceImpl;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ProductoServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(ProductoServlet.class.getName());

    private ProductoBean producto;
    private ProductoService productoService;
   
    private HttpSession sesion;

    private String mensaje;
    private int flgOperacion;
    private String estadoOperacion;

    private Gson gson = null;
    private PrintWriter out = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        logger.info("Requerimiento: " + accion);

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
        if (accion.equals("eliminarSesionProd")) {
            eliminarSesionProd(request, response);
            return;
        }

    }

    protected void insertar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("insertar");
        sesion = request.getSession();
        sesion.removeAttribute("actualizarProducto");
        
        String descripcion = request.getParameter("txtDescripcion") == null ? "Sin descripcion" : request.getParameter("txtDescripcion");
        int id_categoria = Integer.parseInt(request.getParameter("cboIdCategoria") == null ? "1" : request.getParameter("cboIdCategoria"));
        String moneda = request.getParameter("txtMoneda") == null ? "Sol" : request.getParameter("txtMoneda");
        double precio = Double.parseDouble(request.getParameter("txtPrecio") == null ? "0.0" : request.getParameter("txtPrecio"));
        double stock = Double.parseDouble(request.getParameter("txtStock") == null ? "0.0" : request.getParameter("txtStock"));
        int flg_estado = 1;

        try {
            producto = new ProductoBean();
            producto.setDescripcion(descripcion);
            producto.setCategoria_id(id_categoria);
            producto.setMoneda(moneda);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setFlg_estado(flg_estado);

            productoService = new ProductoServiceImpl();
            flgOperacion = productoService.insertar(producto);

            if (flgOperacion > 0) {
                mensaje = "Se creo con éxito el producto";
                estadoOperacion = "1";
            } else {
                mensaje = "Error al crear el producto";
                estadoOperacion = "0";
            }

            /* -----RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR----- */
            String tipo_proceso = "insertar";

            Map<String, String> Mensaje = new LinkedHashMap<String, String>();
            Mensaje.put("tipo_proceso", tipo_proceso);
            Mensaje.put("mensaje", mensaje);
            Mensaje.put("estadoOperacion", estadoOperacion);

            String json_data = null;
            json_data = new Gson().toJson(Mensaje);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json_data);
        } catch (Exception e) {
            logger.error("Error al insertar producto: " + e.getMessage());
        }
    }

    protected void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("buscar");
        sesion = request.getSession();
        sesion.removeAttribute("actualizarProducto");
        
        String descripcion = request.getParameter("txtDescripcion") == null ? "" : request.getParameter("txtDescripcion");

        ResultadoJson respuesta = new ResultadoJson();
        List<ProductoBean> lstProductos;
        gson = new Gson();
        out = response.getWriter();

        try {
            productoService = new ProductoServiceImpl();
            lstProductos = productoService.buscar(descripcion);
            respuesta.setLstLista(lstProductos);

        } catch (Exception e) {
            logger.error("Error al buscar" + e.getMessage());
        } finally {
            out.print(gson.toJson(respuesta));
            out.close();
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        logger.info("actualizar");
        sesion = request.getSession();
        sesion.removeAttribute("actualizarProducto");

        int id = Integer.parseInt(request.getParameter("txtId"));
        String descripcion = request.getParameter("txtDescripcion") == null ? "Sin descripcion" : request.getParameter("txtDescripcion");
        int id_categoria = Integer.parseInt(request.getParameter("cboIdCategoria") == null ? "1" : request.getParameter("cboIdCategoria"));
        String moneda = request.getParameter("txtMoneda") == null ? "Sol" : request.getParameter("txtMoneda");
        double precio = Double.parseDouble(request.getParameter("txtPrecio") == null ? "0.0" : request.getParameter("txtPrecio"));
        double stock = Double.parseDouble(request.getParameter("txtStock") == null ? "0.0" : request.getParameter("txtStock"));
        int flg_estado = 1;

        try {
            producto = new ProductoBean();
            producto.setId(id);
            producto.setDescripcion(descripcion);
            producto.setCategoria_id(id_categoria);
            producto.setMoneda(moneda);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setFlg_estado(flg_estado);

            productoService = new ProductoServiceImpl();
            flgOperacion = productoService.actualizar(producto);

            if (flgOperacion > 0) {
                mensaje = "Actulizo producto con exito";
                estadoOperacion = "1";
            } else {
                mensaje = "Error al actualizar producto";
                estadoOperacion = "0";
            }

            /* -----RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR----- */
            String tipo_proceso = "actualizar";

            Map<String, String> Mensaje = new LinkedHashMap<String, String>();
            Mensaje.put("tipo_proceso", tipo_proceso);
            Mensaje.put("mensaje", mensaje);
            Mensaje.put("estadoOperacion", estadoOperacion);

            String json_data = null;
            json_data = new Gson().toJson(Mensaje);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json_data);
        } catch (Exception e) {
            logger.error("Error al actualizar: " + e.getMessage());
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("eliminar");
        sesion = request.getSession();
        sesion.removeAttribute("actualizarProducto");
        
        int id = Integer.parseInt(request.getParameter("txtId"));

        try {
            productoService = new ProductoServiceImpl();
            flgOperacion = productoService.eliminar(id);
            if (flgOperacion > 0) {
                mensaje = "Se elimino con exito el producto";
                estadoOperacion = "1";
            } else {
                mensaje = "Error al eliminar producto";
                estadoOperacion = "0";
            }

            /* -----RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR----- */
            String tipo_proceso = "eliminar";

            Map<String, String> Mensaje = new LinkedHashMap<String, String>();
            Mensaje.put("tipo_proceso", tipo_proceso);
            Mensaje.put("mensaje", mensaje);
            Mensaje.put("estadoOperacion", estadoOperacion);

            String json_data = null;
            json_data = new Gson().toJson(Mensaje);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json_data);

        } catch (Exception e) {

        }
    }

    protected void obtenerPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("obtener por id");
        sesion = request.getSession();

        int id = Integer.parseInt(request.getParameter("txtId"));

        try {
            producto = new ProductoBean();
            productoService = new ProductoServiceImpl();
            producto = productoService.obtenerPorId(id);

            sesion.removeAttribute("actualizarProducto");
            sesion.setAttribute("actualizarProducto", producto);

            /* -----RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR----- */
            Map<String, String> Mensaje = new LinkedHashMap<String, String>();
            Mensaje.put("mensaje", "productoMnt.jsp");

            String json_data = null;
            json_data = new Gson().toJson(Mensaje);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json_data);

        } catch (Exception e) {

        }
    }

    protected void eliminarSesionProd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("eliminado sesion del producto a actualizar");
        sesion = request.getSession();
        sesion.removeAttribute("actualizarProducto");
        
        /* -----RETORNAMOS EL MENSAJE ENVIADO DEL SERVIDOR----- */
        Map<String, String> Mensaje = new LinkedHashMap<String, String>();
        Mensaje.put("mensaje", "productoMnt.jsp");

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
