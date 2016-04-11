package DaoImpl;

import Bean.ProductoBean;
import Dao.ProductoDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import util.Conexion;

/**
 *
 * @author miguelchinchay
 */
public class ProductoDaoImpl implements ProductoDAO {

    private static Logger logger = Logger.getLogger(ProductoDaoImpl.class.getName());
    private Connection cn;
    private CallableStatement cs;
    private PreparedStatement ps;
    private ResultSet rs;

    private Conexion con;
    private String sql;
    private int flgOperacion;

    @Override
    public int insertar(ProductoBean producto) {
        logger.info("insertando");
        sql = "CALL SP_INSERTAR_PRODUCTO(?,?,?,?,?,?)";

        try {
            con = new Conexion();
            cn = con.getConexion();
            cn.setAutoCommit(false);

            cs = cn.prepareCall(sql);
            cs.setString(1, producto.getDescripcion());
            cs.setInt(2, producto.getCategoria_id());
            cs.setString(3, producto.getMoneda());
            cs.setDouble(4, producto.getPrecio());
            cs.setDouble(5, producto.getStock());
            cs.setInt(6, producto.getFlg_estado());

            flgOperacion = cs.executeUpdate();
            if (flgOperacion > 0) {
                cn.commit();
            } else {
                cn.rollback();
            }

        } catch (Exception e) {
            logger.error("Error al insertar producto: " + e.getMessage());
        } finally {
            con.cerrarConexion(cn);
        }
        return flgOperacion;
    }

    @Override
    public List<ProductoBean> buscar(String descripcion) {
        logger.info("Buscando producto");
        sql = "CALL SP_BUSCAR_PRODUCTO(?)";

        List<ProductoBean> lstProductos = null;
        ProductoBean producto = null;

        try {
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, descripcion);

            rs = ps.executeQuery();
            lstProductos = new ArrayList<ProductoBean>();
            while (rs.next()) {
                producto = new ProductoBean();
                producto.setId(rs.getInt("id"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setCategoria_id(rs.getInt("categoria_id"));
                producto.setMoneda(rs.getString("moneda"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getDouble("stock"));
                producto.setFlg_estado(rs.getInt("flg_estado"));

                lstProductos.add(producto);
            }
        } catch (Exception e) {
            logger.error("Error al buscar producto: " + e.getMessage());
        } finally {
            con.cerrarConexion(cn);
        }
        return lstProductos;
    }

    @Override
    public int actualizar(ProductoBean producto) {
        logger.info("Actualizando producto");
        sql = "CALL SP_ACTUALIZAR_PRODUCTO(?,?,?,?,?,?,?)";

        try {
            con = new Conexion();
            cn = con.getConexion();
            cn.setAutoCommit(false);

            cs = cn.prepareCall(sql);
            cs.setInt(1, producto.getId());
            cs.setString(2, producto.getDescripcion());
            cs.setInt(3, producto.getCategoria_id());
            cs.setString(4, producto.getMoneda());
            cs.setDouble(5, producto.getPrecio());
            cs.setDouble(6, producto.getStock());
            cs.setInt(7, producto.getFlg_estado());

            flgOperacion = cs.executeUpdate();
            if (flgOperacion > 0) {
                cn.commit();
            } else {
                cn.rollback();
            }
        } catch (Exception e) {
            logger.error("Error al actualizar producto: " + e.getMessage());
        } finally {
            con.cerrarConexion(cn);
        }
        return flgOperacion;
    }

    @Override
    public int eliminar(int id) {
        logger.info("Eliminando producto");
        sql = "CALL SP_ELIMINAR_PRODUCTO(?)";

        try {
            con = new Conexion();
            cn = con.getConexion();
            cn.setAutoCommit(false);

            cs = cn.prepareCall(sql);
            cs.setInt(1, id);

            flgOperacion = cs.executeUpdate();
            if (flgOperacion > 0) {
                cn.commit();
            } else {
                cn.rollback();
            }
        } catch (Exception e) {
            logger.error("Error al eliminar producto: " + e.getMessage());
        } finally {
            con.cerrarConexion(cn);
        }
        return flgOperacion;
    }

    @Override
    public ProductoBean obtenerPorId(int id) {
        logger.info("Obteniendo por ID");
        sql = "CALL SP_OBTENERPORID_PRODUCTO(?)";

        ProductoBean producto = null;

        try {
            con = new Conexion();
            cn = con.getConexion();

            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                producto = new ProductoBean();
                producto.setId(rs.getInt("id"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setCategoria_id(rs.getInt("categoria_id"));
                producto.setMoneda(rs.getString("moneda"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getDouble("stock"));
                producto.setFlg_estado(rs.getInt("flg_estado"));
            }
        } catch (Exception e) {
            logger.error("Error al obtener por ID producto: " + e.getMessage());
        } finally {
            con.cerrarConexion(cn);
        }
        return producto;
    }

}
