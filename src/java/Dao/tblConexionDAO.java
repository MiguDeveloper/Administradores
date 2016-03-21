

package Dao;

import Bean.tblConexionBean;
import java.util.List;

/**
 *
 * @author miguelchinchay
 */
public interface tblConexionDAO {
    public int insertar(tblConexionBean tblConexion);
    public List<tblConexionBean> buscar(String ape_paterno, int numPaginaInicio, int numPaginaFin);
    public int actualizar(tblConexionBean tblConexion);
    public int eliminar(int id);
    
    public tblConexionBean obtnerPorId(int id);
    public int totalFilasTbl();
}
