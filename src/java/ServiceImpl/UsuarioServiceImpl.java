
package ServiceImpl;

import Bean.UsuarioBean;
import Dao.UsuarioDAO;
import DaoImpl.UsuarioDaoImpl;
import Service.UsuarioService;
import java.util.List;

/**
 *
 * @author miguelchinchay
 */
public class UsuarioServiceImpl implements UsuarioService{
    
    private UsuarioDAO usuarioDAO;
    
    @Override
    public int insertar(UsuarioBean usuario) {
        usuarioDAO = new UsuarioDaoImpl();
        return usuarioDAO.insertar(usuario);
    }

    @Override
    public List<UsuarioBean> buscar(String usuario) {
        usuarioDAO = new UsuarioDaoImpl();
        return usuarioDAO.buscar(usuario);
    }

    @Override
    public int actualizar(UsuarioBean usuario) {
        usuarioDAO = new UsuarioDaoImpl();
        return usuarioDAO.actualizar(usuario);
    }

    @Override
    public int eliminar(int id) {
        usuarioDAO = new UsuarioDaoImpl();
        return usuarioDAO.eliminar(id);
    }

    @Override
    public UsuarioBean obtenerPorId(int id) {
        usuarioDAO = new UsuarioDaoImpl();
        return usuarioDAO.obtenerPorId(id);
    }

    @Override
    public UsuarioBean login_usuario(String usuario, String pwd) {
        usuarioDAO = new UsuarioDaoImpl();
        return usuarioDAO.login_usuario(usuario, pwd);
    }
    
}
