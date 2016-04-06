
package Dao;

import Bean.UsuarioBean;
import java.util.List;

/**
 *
 * @author miguelchinchay
 */
public interface UsuarioDAO {
    public int insertar(UsuarioBean usuario);
    public List<UsuarioBean> buscar(String usuario);
    public int actualizar(UsuarioBean usuario);
    public int eliminar(int id);
    
    public UsuarioBean obtenerPorId(int id);
    
    public UsuarioBean login_usuario(String usuario, String pwd);
    public int cargaMasivaUsers(String direccion);
}
