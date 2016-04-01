package Bean;

/**
 *
 * @author miguelchinchay
 */
public class UsuarioBean {
    private int ID;
    private String USUARIO;
    private String PWD;
    private String NOMBRES;
    private String APELLIDOS;
    private String EMAIL;
    private int ESTADO;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public int getESTADO() {
        return ESTADO;
    }

    public void setESTADO(int estado) {
        this.ESTADO = estado;
    }
    
}
