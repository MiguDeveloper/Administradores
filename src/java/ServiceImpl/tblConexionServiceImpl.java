

package ServiceImpl;

import Bean.tblConexionBean;
import Dao.tblConexionDAO;
import DaoImpl.tblConexionDAOImpl;
import Service.tblConexionService;
import java.util.List;

/**
 *
 * @author miguelchinchay
 */
public class tblConexionServiceImpl implements tblConexionService{
    private tblConexionDAO tblConexionDAO;
    
    @Override
    public int insertar(tblConexionBean tblConexion) {
        tblConexionDAO = new tblConexionDAOImpl();
        return tblConexionDAO.insertar(tblConexion);
    }

    @Override
    public List<tblConexionBean> buscar(String ape_paterno, int numPaginaInicio, int numPaginaFin) {
        tblConexionDAO = new tblConexionDAOImpl();
        return tblConexionDAO.buscar(ape_paterno, numPaginaInicio, numPaginaFin);
    }

    @Override
    public int actualizar(tblConexionBean tblConexion) {
        tblConexionDAO = new tblConexionDAOImpl();
        return tblConexionDAO.actualizar(tblConexion);
    }

    @Override
    public int eliminar(int id) {
        tblConexionDAO = new tblConexionDAOImpl();
        return tblConexionDAO.eliminar(id);
    }

    @Override
    public tblConexionBean obtnerPorId(int id) {
        tblConexionDAO = new tblConexionDAOImpl();
        return tblConexionDAO.obtnerPorId(id);
    }

    @Override
    public int totalFilasTbl() {
        tblConexionDAO = new tblConexionDAOImpl();
        return tblConexionDAO.totalFilasTbl();
    }
    
}
