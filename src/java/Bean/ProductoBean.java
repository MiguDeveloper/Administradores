
package Bean;

/**
 *
 * @author miguelchinchay
 */
public class ProductoBean {
    private int id;
    private String descripcion;
    private int categoria_id;
    private String moneda;
    private double precio;
    private double stock;
    private int flg_estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public int getFlg_estado() {
        return flg_estado;
    }

    public void setFlg_estado(int flg_estado) {
        this.flg_estado = flg_estado;
    }
    
}
