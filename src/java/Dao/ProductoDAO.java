
package Dao;

import Bean.ProductoBean;
import java.util.List;

/**
 *
 * @author miguelchinchay
 */
public interface ProductoDAO {
    public int insertar(ProductoBean producto);
    public List<ProductoBean> buscar(String descripcion);
    public int actualizar(ProductoBean producto);
    public int eliminar(int id);
    
    public ProductoBean obtenerPorId(int id);
}
