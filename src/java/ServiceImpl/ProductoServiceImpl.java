
package ServiceImpl;

import Bean.ProductoBean;
import Dao.ProductoDAO;
import DaoImpl.ProductoDaoImpl;
import Service.ProductoService;
import java.util.List;

/**
 *
 * @author miguelchinchay
 */
public class ProductoServiceImpl implements ProductoService{

    private ProductoDAO ProductoDAO;
    @Override
    public int insertar(ProductoBean producto) {
        ProductoDAO = new ProductoDaoImpl();
        return ProductoDAO.insertar(producto);
    }

    @Override
    public List<ProductoBean> buscar(String descripcion) {
        ProductoDAO = new ProductoDaoImpl();
        return ProductoDAO.buscar(descripcion);
    }

    @Override
    public int actualizar(ProductoBean producto) {
        ProductoDAO = new ProductoDaoImpl();
        return ProductoDAO.actualizar(producto);
    }

    @Override
    public int eliminar(int id) {
        ProductoDAO = new ProductoDaoImpl();
        return ProductoDAO.eliminar(id);
    }

    @Override
    public ProductoBean obtenerPorId(int id) {
        ProductoDAO = new ProductoDaoImpl();
        return ProductoDAO.obtenerPorId(id);
    }
    
}
