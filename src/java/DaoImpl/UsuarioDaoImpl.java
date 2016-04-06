
package DaoImpl;

import Bean.UsuarioBean;
import Dao.UsuarioDAO;
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
public class UsuarioDaoImpl implements UsuarioDAO{
    
    private static Logger logger = Logger.getLogger(UsuarioDaoImpl.class.getName());
    private Connection cn;
    private CallableStatement cs;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private Conexion con;
    private String sql;
    private int flgOperacion;
    
    @Override
    public int insertar(UsuarioBean usuario) {
        logger.info("Insertar");
        sql = "CALL SP_INSERTARUSUARIO(?,?,?,?,?,?)";
        
        try{
            con = new Conexion();
            cn = con.getConexion();
            cn.setAutoCommit(false);
            cs = cn.prepareCall(sql);
            cs.setString(1, usuario.getUSUARIO());
            cs.setString(2, usuario.getPWD());
            cs.setString(3, usuario.getNOMBRES());
            cs.setString(4, usuario.getAPELLIDOS());
            cs.setString(5, usuario.getEMAIL());
            cs.setInt(6, usuario.getESTADO());
            
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
    public List<UsuarioBean> buscar(String usuario) {
        logger.info("Listando");
        sql = "CALL SP_LISTARUSUARIO(?)";
        
        List<UsuarioBean> lstUsuario = null;
        UsuarioBean objUsuario = null;
        try{
            con = new Conexion();
            cn = con.getConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            
            rs = ps.executeQuery();
            lstUsuario = new ArrayList<UsuarioBean>();
            while(rs.next()){
                objUsuario = new UsuarioBean();
                objUsuario.setID(rs.getInt("ID"));
                objUsuario.setUSUARIO(rs.getString("USUARIO"));
                objUsuario.setPWD(rs.getString("PWD"));
                objUsuario.setNOMBRES(rs.getString("NOMBRES"));
                objUsuario.setAPELLIDOS(rs.getString("APELLIDOS"));
                objUsuario.setEMAIL(rs.getString("EMAIL"));
                objUsuario.setESTADO(rs.getInt("ESTADO"));
                lstUsuario.add(objUsuario);
            }
            
        }catch(Exception e){
            logger.error("Error al buscar" + e.getMessage());
        }finally{
            con.cerrarConexion(cn);
        }
        return lstUsuario;
    }

    @Override
    public int actualizar(UsuarioBean usuario) {
        logger.info("actualizando");
        sql = "CALL SP_ACTUALIZARUSUARIO(?,?,?,?,?,?,?)";
        
        try{
            con = new Conexion();
            cn = con.getConexion();
            cn.setAutoCommit(false);
            
            cs = cn.prepareCall(sql);
            cs.setInt(1, usuario.getID());
            cs.setString(2, usuario.getUSUARIO());
            cs.setString(3, usuario.getPWD());
            cs.setString(4, usuario.getNOMBRES());
            cs.setString(5, usuario.getAPELLIDOS());
            cs.setString(6, usuario.getEMAIL());
            cs.setInt(7, usuario.getESTADO());
            
            flgOperacion = cs.executeUpdate();
            if(flgOperacion>0){
                cn.commit();
            }else{
                cn.rollback();
            }
        }catch(Exception e){
            logger.error("Error al actualizar: " + e.getMessage());
        }finally{
            con.cerrarConexion(cn);
        }
        return flgOperacion;
    }

    @Override
    public int eliminar(int id) {
        logger.info("eliminando");
        sql = "CALL SP_ELIMINARUSUARIO(?)";
        
        try{
            con = new Conexion();
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
            logger.error("Error al eliminar: " + e.getMessage());
        }finally{
            con.cerrarConexion(cn);
        }
        return flgOperacion;
    }

    @Override
    public UsuarioBean obtenerPorId(int id) {
        logger.info("Obteniendo por ID");
        sql = "CALL SP_OBTENERPORID(?)";
        
        UsuarioBean usuario = null;
        try{
            con = new Conexion();
            cn = con.getConexion();
            
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            while(rs.next()){
                usuario = new UsuarioBean();
                usuario.setID(rs.getInt("ID"));
                usuario.setUSUARIO(rs.getString("USUARIO"));
                usuario.setPWD(rs.getString("PWD"));
                usuario.setNOMBRES(rs.getString("NOMBRES"));
                usuario.setAPELLIDOS(rs.getString("APELLIDOS"));
                usuario.setEMAIL(rs.getString("EMAIL"));
                usuario.setESTADO(rs.getInt("ESTADO"));
            }
        }catch(Exception e){
            logger.error("Error al eliminar: " + e.getMessage());
        }finally{
            con.cerrarConexion(cn);
        }
        return usuario;
    }

    @Override
    public UsuarioBean login_usuario(String usuario, String pwd) {
        logger.info("Login");
        sql = "CALL SP_LOGIN_USUARIO(?,?)";
        
        UsuarioBean objUsuario = null;
        
        try{
            con =  new Conexion();
            cn = con.getConexion();
            
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pwd);
            
            rs = ps.executeQuery();
            while(rs.next()){
                objUsuario = new UsuarioBean();
                objUsuario.setID(rs.getInt("ID"));
                objUsuario.setUSUARIO(rs.getString("USUARIO"));
                objUsuario.setPWD(rs.getString("PWD"));
                objUsuario.setNOMBRES(rs.getString("NOMBRES"));
                objUsuario.setAPELLIDOS(rs.getString("APELLIDOS"));
                objUsuario.setEMAIL(rs.getString("EMAIL"));
                objUsuario.setESTADO(rs.getInt("ESTADO"));
            }
        }catch(Exception e){
            logger.error("Error al logearse: " + e.getMessage());
        }finally{
            con.cerrarConexion(cn);
        }
        return objUsuario;
    }

    @Override
    public int cargaMasivaUsers(String direccion) {
        logger.info("Carga masiva");
        
        
        sql = "LOAD DATA LOCAL INFILE '" + direccion 
                + "' INTO TABLE USUARIO "
                + "FIELDS TERMINATED BY ';' " 
                + "LINES TERMINATED BY '\r' IGNORE 1 LINES "
                + "(USUARIO,PWD,NOMBRES,APELLIDOS,EMAIL) SET ESTADO=1";
        try{
            con = new Conexion();
            cn = con.getConexion();
            cn.setAutoCommit(false);
            
            ps = cn.prepareStatement(sql);
            
            flgOperacion = ps.executeUpdate();
            if(flgOperacion>0){
                cn.commit();
            }else{
                cn.rollback();
            }
        }catch(Exception e){
            logger.error("Error carga masiva: " + e.getMessage());
        }
        
        return flgOperacion;
    }
    
}
