

package DaoImpl;

import Bean.tblConexionBean;
import Dao.tblConexionDAO;
import util.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import java.util.List;

/**
 *
 * @author miguelchinchay
 */
public class tblConexionDAOImpl implements tblConexionDAO{
    
    private static Logger logger = Logger.getLogger(tblConexionDAOImpl.class.getName());
    
    private Connection cn;
    private CallableStatement cs;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private Conexion con;
    private String sql;
    private int flgOperacion;
    
    @Override
    public int insertar(tblConexionBean tblConexion) {
        logger.info("insertar tblConexion");
        sql = "call SP_INSERTAR_TBLCONEXION(?,?,?)";
        
        try{
            con = new Conexion();
            cn = con.getConexion();
            cn.setAutoCommit(false);
            
            cs = cn.prepareCall(sql);
            cs.setString(1, tblConexion.getNOMBRE());
            cs.setString(2, tblConexion.getAPE_PATERNO());
            cs.setString(3, tblConexion.getAPE_MATERNO());
            
            flgOperacion = cs.executeUpdate();
            if(flgOperacion>0){
                cn.commit();
            }else{
                cn.rollback();
            }
        }catch(Exception e){
            logger.error("Error al insertar: " + e.getMessage());
        }finally{
            con.cerrarConexion(cn);
        }
        
        return flgOperacion;
    }

    @Override
    public List<tblConexionBean> buscar(String ape_paterno, int numPaginaInicio, int numPaginaFin) {
        logger.info("Listando tblConexion");
        sql = "CALL SP_LISTAR_TBLCONEXION(?,?,?)";
        
        List<tblConexionBean> lsttblConexion = null;
        tblConexionBean tblConexion = null;
        
        try{
            con = new Conexion();
            cn = con.getConexion();
            
            ps = cn.prepareStatement(sql);
            ps.setString(1, ape_paterno);
            ps.setInt(2, numPaginaInicio);
            ps.setInt(3, numPaginaFin);
            
            rs = ps.executeQuery();
            lsttblConexion = new ArrayList<tblConexionBean>(); 
            while(rs.next()){
                tblConexion = new tblConexionBean();
                tblConexion.setID(rs.getInt("ID"));
                tblConexion.setNOMBRE(rs.getString("NOMBRE"));
                tblConexion.setAPE_PATERNO(rs.getString("APE_PATERNO"));
                tblConexion.setAPE_MATERNO(rs.getString("APE_MATERNO"));
                lsttblConexion.add(tblConexion);
            }
        }catch(Exception e){
            logger.error("Error al listar" + e.getMessage());
        }finally{
            con.cerrarConexion(cn);
        }
        
        return lsttblConexion;
    }

    @Override
    public int actualizar(tblConexionBean tblConexion) {
        logger.info("Actualizando tblConexion");
        sql = "CALL SP_ACTUALIZAR_TBLCONEXION(?,?,?,?)";
        
        try{
            con = new Conexion();
            cn = con.getConexion();
            cn.setAutoCommit(false);
            
            cs = cn.prepareCall(sql);
            cs.setInt(1, tblConexion.getID());
            cs.setString(2, tblConexion.getNOMBRE());
            cs.setString(3, tblConexion.getAPE_PATERNO());
            cs.setString(4, tblConexion.getAPE_PATERNO());
            
            flgOperacion = cs.executeUpdate();
            if(flgOperacion>0){
                cn.commit();
            }else{
                cn.rollback();
            }
        }catch(Exception e){
            logger.error("Error al actualizar " + e.getMessage());
        }finally{
            con.cerrarConexion(cn);
        }
        
        return flgOperacion;
    }

    @Override
    public int eliminar(int id) {
        logger.info("Eliminar tblConexion");
        sql = "CALL SP_ELIMINAR_TBLCONEXION(?)";
        
        try{
            con =  new Conexion();
            cn = con.getConexion();
            cn.setAutoCommit(false);
            
            cs = cn.prepareCall(sql);
            cs.setInt(1, id);
            
            flgOperacion = cs.executeUpdate();
            if(flgOperacion>0){
                cn.commit();
            }else{
                cn.rollback();
            }
        }catch(Exception e){
            logger.error("Error al eliminar " + e.getMessage());
        }finally{
            con.cerrarConexion(cn);
        }
        
        return flgOperacion;
    }

    @Override
    public tblConexionBean obtnerPorId(int id) {
        logger.info("Obtener por id");
        sql = "CALL SP_OBTENERPORID(?)";
        
        tblConexionBean tblConexion = null;
        try{
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            while(rs.next()){
                tblConexion = new tblConexionBean();
                tblConexion.setID(rs.getInt("ID"));
                tblConexion.setNOMBRE(rs.getString("NOMBRE"));
                tblConexion.setAPE_PATERNO(rs.getString("APE_PATERNO"));
                tblConexion.setAPE_MATERNO(rs.getString("APE_MATERNO"));
            }
        }catch(Exception e){
            logger.error("Error al eliminar " + e.getMessage());
        }finally{
            con.cerrarConexion(cn);
        }
        
        return tblConexion;
       
    }

    @Override
    public int totalFilasTbl() {
        logger.info("Obteniendo el total");
        sql = "CALL SP_TOTAL_TABLA()";
        
        int numTotal = 0;
        try{
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            while(rs.next()){
                numTotal = rs.getInt("TOTAL");
            }
        }catch(Exception e){
            logger.error("Error al obtener totales: " + e.getMessage());
        }finally{
            con.cerrarConexion(cn);
        }
        return numTotal;
    }
    
}
