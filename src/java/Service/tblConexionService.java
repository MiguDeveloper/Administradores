

package Service;

import Bean.tblConexionBean;
import java.util.List;

/**
 *
 * @author miguelchinchay
 */
public interface tblConexionService {
    public int insertar(tblConexionBean tblConexion);
    public List<tblConexionBean> buscar(String ape_paterno, int numPaginaInicio, int numPaginaFin);
    public int actualizar(tblConexionBean tblConexion);
    public int eliminar(int id);
    
    public tblConexionBean obtnerPorId(int id);
    public int totalFilasTbl();
}
