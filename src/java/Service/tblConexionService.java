

package Service;

import Bean.tblConexionBean;
import java.util.List;

/**
 *
 * @author miguelchinchay
 */
public interface tblConexionService {
    public int insertar(tblConexionBean tblConexion);
    public List<tblConexionBean> buscar(String ape_paterno);
    public int actualizar(tblConexionBean tblConexion);
    public int eliminar(int id);
    
    public tblConexionBean obtnerPorId(int id);
}
