
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;



/**
 *
 * @author miguelchinchay
 */
public class Conexion {
    /* 
    declaramos como una constante y asi obtimizamos el uso de la memoria al instanciar la clase.
    Cuando usamos “static final” se dice que creamos una constante de clase, un atributo común a todos 
    los objetos de esa clase. 
    */
    private static final Logger logger = Logger.getLogger(Conexion.class.getName());
    private Connection con= null;
    private String dbUrl;
    private String dbUser;
    private String dbPwd;
    
    public Conexion() {
        logger.info("iniciamos la conexion");
        
        //cargamos el driver para la conexion
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            System.out.println("Error driver: " + e.getMessage());
            logger.error("Error no podemos cargar el driver: " + e.getMessage());
        }
        
        //inicializamos las variables y accedemos a la base de datos
        try{
            dbUrl = "jdbc:mysql://localhost:3306/db_bootstrap_administrador";
            dbUser = "root";
            dbPwd = "";
            
            
            con =  DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            
            //Comprobamos la conexion
            String sql = "SELECT*FROM TBL_TEST_CONEXION";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.print(rs.getString("NOMBRE") + " ");
                System.out.print(rs.getString("APE_PATERNO") + " ");
                System.out.println(rs.getString("APE_MATERNO"));
            }
            
            
        }catch(SQLException e){
            System.out.println("Error  bucle: " + e.getMessage());
            logger.error("Error al establecer la conexion: " + e.getMessage());
        }
    }
    
    public Connection getConexion(){
        logger.info("Enviando conexion");
        return con;
    }
    
    public void cerrarConexion(Connection con){
        logger.info("Cerrando la conexion");
        try{
            con.close();
        }catch(SQLException e){
            //logger.error("Error al cerrar la conexion: " + e.getMessage());
        }
    }
    
    public static void main(String[] args){
        Conexion con = new Conexion();
    }
}
